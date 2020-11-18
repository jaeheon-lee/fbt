import Axios from "axios";
import VoteMatchList from "@/components/VoteMatch/VoteMatchList.vue";

export default {
  name: "schedule",
  components: {
    "vote-match-list": VoteMatchList
  },
  data() {
    return {
      // 캘린더 기본 변수
      type: "month",
      types: ["month", "week", "day"],
      mode: "stack",
      modes: ["stack", "column"],
      weekday: [1, 2, 3, 4, 5, 6, 0],
      value: "",
      events: [],
      colors: [
        "blue",
        "indigo",
        "deep-purple",
        "cyan",
        "green",
        "orange",
        "grey darken-1"
      ],
      // event 출력 관련 변수
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      // 검색어 보내는 변수
      searchKey: {
        teamId: null,
        startTime: null,
        endTime: null
      },
      // 기본변수
      loading: false,
      errored: false,

      //로그인 변수 -- 임시 변수
      userInfo: {
        email: "bioman2@gmail.com",
        teamId: 1,
        teamMemberId: "1-bioman2@gmail.com",
        teamName: "왕십리FC",
        nickName: "nick2"
      }
    };
  },
  created() {
    this.login();
  },
  mounted() {
    // this.getEvents;
  },
  destoryed() {
    this.selectedEvent = {};
  },
  methods: {
    //해당 월 일정 가져오기
    getEvents({ start, end }) {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.searchKey.teamId = teamId;
      this.searchKey.startTime = start.date;
      this.searchKey.endTime = end.date;
      this.loading = true;

      const events = [];
      Axios.get(
        "/match-schedule/" +
          teamId +
          "?startTime=" +
          start.date +
          "&endTime=" +
          end.date
      )
        .then(response => {
          const matchSchedule = response.data;
          for (let i = 0; i < matchSchedule.length; i++) {
            const name = matchSchedule[i].matchType + "경기";
            events.push({
              name: name,
              start: matchSchedule[i].startTime.slice(0, 16),
              end: matchSchedule[i].endTime.slice(0, 16),
              color: this.colors[this.rnd(0, this.colors.length - 1)],
              matchScheduleId: matchSchedule[i].matchScheduleId,
              eventId: i
              // timed: false
            });
          }
        })
        .catch(() => {
          this.errored = true;
          console.log(1);
        })
        .finally(() => {
          this.loading = false;
        });
      this.events = events;
    },
    // 일정에 부여된 색 가져오기
    getEventColor(event) {
      return event.color;
    },
    // 랜덤 숫자 부여하기
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    // 일정 클릭 시 일정 정보 출력
    showEvent({ nativeEvent, event }) {
      this.selectedEvent = event;
      this.selectedElement = nativeEvent.target;
      this.selectedOpen = true;
      nativeEvent.stopPropagation();
    },
    // 로그인 -- 임시 메소드
    login() {
      sessionStorage.setItem("userInfo", JSON.stringify(this.userInfo));
    }
  }
};
