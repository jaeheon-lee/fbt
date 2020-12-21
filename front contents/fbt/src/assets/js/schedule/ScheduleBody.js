import axios from "axios";
import VoteMatchList from "@/components/VoteMatch/VoteMatchList.vue";
import MatchResultInsert from "@/components/Schedule/MatchResultInsert.vue";
import TeamScoreInsert from "@/components/Schedule/TeamScoreInsert.vue";

export default {
  name: "schedule-body",
  props: {
    isManager: Boolean,
    isUser: Boolean
  },
  components: {
    "vote-match-list": VoteMatchList,
    "match-result-insert": MatchResultInsert,
    "team-score-insert": TeamScoreInsert
  },
  data() {
    return {
      //vote vo
      votes: [],
      // 캘린더 기본 변수
      type: "month",
      types: ["month", "week", "day"],
      mode: "stack",
      modes: ["stack", "column"],
      weekday: [1, 2, 3, 4, 5, 6, 0],
      value: "",
      events: [],
      colors: ["green", "grey darken-1"],
      event: {},

      // 검색어 보내는 변수
      searchKey: {
        teamId: null,
        startTime: null,
        endTime: null
      },

      // 페이지 관리 변수
      infoActive: false, //일정 정보 페이지
      writeActive: false, // 팀 결과 작성 페이지
      empWriteActive: false, // 용병 경기 결과 작성 페이지

      // 용병경기결과 작성 변수
      isEmp: false,

      //수정, 작성 결정 변수
      isUpdate: false,

      // 기본변수
      loading: false,
      errored: false
    };
  },
  computed: {
    sendingHeader: function() {
      if (this.isManager) return "scheduleManager";
      else return "schedule";
    },
    controlWriteBtn: function() {
      let vote = this.votes[0];
      if (vote && (this.isManager || this.isEmp)) {
        if (vote.isEndMatch && vote.matchSchedule.entries.length == 0) return true;
      }
      return false;
    },
    controlUpdateBtn: function() {
      let vote = this.votes[0];
      if (vote && (this.isManager || this.isEmp)) {
        if (vote.isEndMatch && vote.matchSchedule.entries.length > 0) return true;
      }
      return false;
    }
  },
  methods: {
    // ===================== 켈린더 관련 메소드 =========================== //
    //해당 월 일정 가져오기
    getEvents({ start, end }) {
      this.loading = true;
      const events = [];
      if (this.isUser) {
        // FS12
        let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
        axios
          .get(
            "/match-schedule/6/" +
              email +
              "?startTime=" +
              start.date +
              "&endTime=" +
              end.date
          )
          .then(response => {
            const matchSchedule = response.data;
            // 일정에 필요한 정보 담기: 이름, 시작, 끝, 색, 매치아이디, 끝난 경기인지
            this.setMatchScheduleInfo(matchSchedule, events);
          })
          .catch(() => {
            this.errored = true;
          })
          .finally(() => {
            this.loading = false;
          });
      } else {
        // FS02
        let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
        axios
          .get(
            "/match-schedule/" +
              teamId +
              "?startTime=" +
              start.date +
              "&endTime=" +
              end.date
          )
          .then(response => {
            const matchSchedule = response.data;
            // 일정에 필요한 정보 담기: 이름, 시작, 끝, 색, 매치아이디, 끝난 경기인지
            this.setMatchScheduleInfo(matchSchedule, events);
          })
          .catch(() => {
            this.errored = true;
          })
          .finally(() => {
            this.loading = false;
          });
      }
      this.events = events;
    },
    setMatchScheduleInfo(matchSchedule, events) {
      let today = new Date();
      for (let i = 0; i < matchSchedule.length; i++) {
        let isEndMatch = false;
        const name = matchSchedule[i].matchType + "경기";
        let startTime = new Date(matchSchedule[i].startTime);
        if (startTime < today) isEndMatch = true;
        events.push({
          name: name,
          start: matchSchedule[i].startTime.slice(0, 16),
          color: this.colors[this.selectColorIdx(isEndMatch)],
          matchScheduleId: matchSchedule[i].matchScheduleId,
          eventId: i,
          isEndMatch: isEndMatch,
          awayTeamId: matchSchedule[i].awayTeam.teamId
          // timed: false
        });
      }
    },
    // 일정에 부여된 색 가져오기
    getEventColor(event) {
      return event.color;
    },
    // 랜덤 숫자 부여하기
    selectColorIdx(isEndMatch) {
      if (isEndMatch) return 1;
      // 끝났으면 회색
      else return 0; // 끝나지 않았으면 그린
    },
    // ===================== 경기 정보 관련 메소드 =========================== //
    // 스케쥴Id별 투표 출력(FS07, FV03)
    showMatchScheduleInfo({ event }) {
      this.infoActive = true;
      this.event = event;
      console.log(this.event);
      let matchScheduleId = this.event.matchScheduleId;
      let isEndMatch = this.event.isEndMatch;
      if (isEndMatch) {
        // FS07
        this.showEndMatchInfo(matchScheduleId);
      } else {
        //FS03
        this.showNotEndMatchInfo(matchScheduleId);
      }
    },
    //FS07
    showEndMatchInfo(matchScheduleId) {
      this.isEmp = false;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      if (!teamId) teamId = 0;
      axios
        .get("/match-schedule/5/" + matchScheduleId + "/" + teamId)
        .then(response => {
          let matchSchedule = response.data;
          let vote = {};
          vote.matchSchedule = matchSchedule;
          // 끝난 경기인지에 대한 정보 담고
          vote.isEndMatch = this.event.isEndMatch;
          // 용병이 용병 뛴 경리를 보는지 확인 for 경기 결과 작성
          let entries = matchSchedule.entries;
          let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
          for (let i = 0; i < entries.length; i++) {
            // 타입이 용병이고 그 용병의 이메일이 현재 접속자와 같다면
            if (entries[i].type == 2 && email == entries[i].user.email) {
              this.isEmp = true;
            }
          }
          // 리스트에 보낼 통에 담고
          this.votes = [];
          this.votes.push(vote);
          // 화면 이동
          location.href = "#matchInfo";
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
          // nativeEvent.stopPropagation();
        });
    },
    //FS03
    showNotEndMatchInfo(matchScheduleId) {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let awayTeamId = this.event.awayTeamId;
      axios
        // eslint-disable-next-line prettier/prettier
        .get("/vote-match/2/" + matchScheduleId + "/" + teamId + "/" + awayTeamId)
        .then(response => {
          let vote = response.data;
          // 끝난 경기인지에 대한 정보 담고
          vote.isEndMatch = this.event.isEndMatch;
          // 리스트에 보낼 통에 담고
          this.votes = [];
          this.votes.push(vote);
          // 화면 이동
          location.href = "#matchInfo";
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
          // nativeEvent.stopPropagation();
        });
    },
    // ===================== 경기 결과 작성 관련 메소드 =========================== //
    openWritePage() {
      if (this.isEmp) {
        // 용병이 작성 버튼을 눌렀으면
        this.infoActive = false;
        this.empWriteActive = true;
        location.href = "#empMatchResult";
      } else {
        // 팀이 작성 버튼을 눌렀으면
        this.infoActive = false;
        this.writeActive = true;
        location.href = "#matchResult";
      }
    },
    openUpdatePage() {
      if (this.isEmp) {
        // 용병이 작성 버튼을 눌렀으면
        this.infoActive = false;
        this.empWriteActive = true;
        location.href = "#empMatchResult";
      } else {
        // 팀이 작성 버튼을 눌렀으면
        this.infoActive = false;
        this.writeActive = true;
        this.isUpdate = true;
        location.href = "#matchResult";
      }
    }
  }
};
