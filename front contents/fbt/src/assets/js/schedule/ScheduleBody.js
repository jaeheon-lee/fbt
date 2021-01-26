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
      // awayVote vo
      awayVote: {},

      // 캘린더 기본 변수
      type: "month",
      types: ["month", "week", "day"],
      mode: "stack",
      modes: ["stack", "column"],
      weekday: [1, 2, 3, 4, 5, 6, 0],
      value: "",
      events: [],
      colors: ["green", "grey darken-1"],

      // 새로 고침을 위한 변수
      start: null, //월 초일
      end: null, // 월 말일
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

      // 용병 수정 시, 기존 평가 변수
      teamScoreByEmp: {},

      // 양도를 통해 확정한 일정인 경우
      isByAssign: false,

      // 기본변수
      loading: false,
      errored: false
    };
  },
  computed: {
    sendingHeader: function() {
      if (this.isManager) return "scheduleManager";
      else if (this.isUser) return "scheduleUser";
      else return "schedule";
    },
    controlWriteBtn: function() {
      let vote = this.votes[0];
      if (!vote) return false;
      if (!vote.isEndMatch) return false;
      if (vote.matchSchedule.entries.length > 0) return false;
      return true;
    },
    controlUpdateBtn: function() {
      let vote = this.votes[0];
      if (!vote) return false;
      if (!vote.isEndMatch) return false;
      if (vote.matchSchedule.entries.length == 0) return false;
      return true;
    },
    controlUserWriteBtn: function() {
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      let vote = this.votes[0];
      // 정보가 아예 없으면 미출력
      if (!vote) return false;
      // 끝난 경기가 아니면 미출력
      if (!vote.isEndMatch) return false;
      // 이미 작성했는지 확인
      let teamScores = vote.matchSchedule.teamScores;
      for (let i = 0; i < teamScores.length; i++) {
        let user = teamScores[i].user;
        if (!user) continue;
        if (email == teamScores[i].user.email) {
          return false;
        }
      }
      return true;
    },
    controlUserUpdateBtn: function() {
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      let vote = this.votes[0];
      if (!vote) return false;
      if (!vote.isEndMatch) return false;
      let teamScores = vote.matchSchedule.teamScores;
      for (let i = 0; i < teamScores.length; i++) {
        if (teamScores[i].user) {
          if (email == teamScores[i].user.email) {
            this.teamScoreByEmp = teamScores[i];
            return true;
          }
        }
      }
      return false;
    }
  },
  methods: {
    // ===================== 켈린더 관련 메소드 =========================== //
    // 처음 해당 월 일정 가져오기
    getEventsInit({ start, end }) {
      this.start = start;
      this.end = end;
      this.getEvents(start, end);
    },
    // 새로고침으로 해당 월 일정 가져오기
    getEventFresh() {
      this.infoActive = false;
      this.getEvents(this.start, this.end);
    },
    getEvents(start, end) {
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
        let homeTeamId = null;
        let awayTeamId = null;
        if (matchSchedule[i].homeTeam)
          homeTeamId = matchSchedule[i].homeTeam.teamId;
        if (matchSchedule[i].awayTeam)
          awayTeamId = matchSchedule[i].awayTeam.teamId;
        if (startTime < today) isEndMatch = true;
        events.push({
          name: name,
          start: matchSchedule[i].startTime.slice(0, 16),
          color: this.colors[this.selectColorIdx(isEndMatch)],
          matchScheduleId: matchSchedule[i].matchScheduleId,
          eventI: i,
          isEndMatch: isEndMatch,
          homeTeamId: homeTeamId,
          awayTeamId: awayTeamId
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
    // 클릭했을 때 투표 출력
    showMatchScheduleInfoByClick({ event }) {
      this.event = event;
      this.showMatchScheduleInfo(event);
    },
    // 새로고침으로 투표 재출력
    showMatchScheduleInfoByRefresh() {
      this.showMatchScheduleInfo(this.event);
    },
    // 스케쥴Id별 투표 출력(FS07, FV03)
    showMatchScheduleInfo(event) {
      this.infoActive = true;
      this.event = event;
      let matchScheduleId = this.event.matchScheduleId;
      let isEndMatch = this.event.isEndMatch;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      if (isEndMatch) {
        // FS07
        if (teamId) this.showEndMatchInfoByTeam(matchScheduleId, teamId);
        else this.showEndMatchInfoByUser(matchScheduleId);
      } else {
        //FS03
        teamId = this.event.homeTeamId;
        this.showNotEndMatchInfo(matchScheduleId, teamId);
        if (this.event.homeTeamId != this.event.awayTeamId) {
          this.showAwayVoteMatchInfo(this.event.awayTeamId, matchScheduleId);
        }
      }
    },
    //FS07
    showEndMatchInfoByTeam(matchScheduleId, teamId) {
      this.isEmp = false;
      if (!teamId) teamId = 0;
      axios
        .get("/match-schedule/5/" + matchScheduleId + "/" + teamId)
        .then(response => {
          let matchSchedule = response.data;
          let vote = {};
          vote.matchSchedule = matchSchedule;
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
    //FS13
    showEndMatchInfoByUser(matchScheduleId) {
      this.isEmp = true;
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      axios
        .get("/match-schedule/8/" + matchScheduleId + "/" + email)
        .then(response => {
          let matchSchedule = response.data;
          let vote = {};
          vote.matchSchedule = matchSchedule;
          // 끝난 경기인지에 대한 정보 담고
          vote.isEndMatch = this.event.isEndMatch;
          // 리스트에 보낼 통에 담고
          this.votes = [];
          this.votes.push(vote);

          this.teamScoreByEmp.matchSchedule = vote.matchSchedule;
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
    showNotEndMatchInfo(matchScheduleId, teamId) {
      axios
        .get("/vote-match/2/" + matchScheduleId + "/" + teamId)
        .then(response => {
          let vote = response.data;
          if (vote) {
            // 끝난 경기인지에 대한 정보 담고
            vote.isEndMatch = this.event.isEndMatch;
            // 리스트에 보낼 통에 담고
            this.votes = [];
            this.votes.push(vote);
            // 화면 이동
            location.href = "#matchInfo";
            this.isByAssign = false;
          } else {
            this.showOnlyMatchSchedule(matchScheduleId);
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
          // nativeEvent.stopPropagation();
        });
    },
    showOnlyMatchSchedule(matchScheduleId) {
      this.$axios
        .get("/match-schedule/2/" + matchScheduleId)
        .then(response => {
          let vote = {};
          vote.matchSchedule = response.data;
          this.votes = [];
          this.votes.push(vote);
          this.isByAssign = true;
          // 화면 이동
          location.href = "#matchInfo";
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },

    showAwayVoteMatchInfo(awayTeamId, matchSchedule) {
      axios
        .get("/vote-match/2/" + matchSchedule + "/" + awayTeamId)
        .then(response => {
          this.awayVote = response.data;
          // 끝난 경기인지에 대한 정보 담고
          this.awayVote.isEndMatch = this.event.isEndMatch;
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
