import axios from "axios";
export default {
  name: "voteMatchOn",
  components: {},
  data() {
    return {
      // 투표 변수
      votes: [],
      voteMatchResult: {
        voteMatchId: null,
        teamMember: {
          teamMemberId: 5,
          user: {
            email: null
          }
        },
        email: null,
        attendance: null
      },
      voteMatchResults: [],

      //기본 변수
      errored: false,
      loading: true
    };
  },
  mounted() {
    this.showVoteInfo();
  },
  methods: {
    // 진행 중인 투표 출력 (V001)
    showVoteInfo() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let voteStatus = 1;
      // eslint-disable-next-line prettier/prettier
      axios
        .get("/vote-match/" + teamId + "?voteStatus=" + voteStatus)
        .then(response => {
          this.votes = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team.emblem) {
        return require("@/assets/image/emblem/" + team.emblem);
      } else {
        return require("@/assets/image/emblem/emptyFC.png");
      }
    }
  },
  filters: {
    extrackSecond(value) {
      return value.slice(0, 16);
    },
    showOnlyTime(value) {
      return value.slice(11, 16);
    },
    showMatchType(value) {
      let matchType = "축구";
      if (value < 11) matchType = "풋살";
      return value + " : " + value + " " + matchType + " 경기";
    },
    isEmp(value) {
      if (value.empDueDate) return "용병 경기";
      else return "팀 경기";
    },
    showParking(value) {
      if (value == 1) return "주차가능";
      else return "주차불가능";
    }
  }
};
