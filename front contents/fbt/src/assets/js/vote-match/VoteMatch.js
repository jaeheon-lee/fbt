import axios from "axios";
export default {
  name: "voteMatch",
  components: {},
  data() {
    return {
      // 투표 변수
      votes: [],
      voteMatchResult: {
        voteMatchId: null,
        teamMember: {
          teamMemberId: 5
        },
        email: null,
        attendance: null
      },

      //기본 변수
      errored: false,
      loading: true,

      //로그인 변수
      userInfo: {
        email: "bioman5@gmail.com",
        teamId: 1,
        teamMemberId: 5
      }
    }
  },
  mounted() {
    this.showVoteInfo();
    this.login();
  },
  methods: {
    // 진행 중인 투표 출력 (V001)
    showVoteInfo() {
      // eslint-disable-next-line prettier/prettier
      axios
        .get("/vote-match/1")
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
    },
    // 투표 참석하기
    addVote(voteMatchId, result) {
      this.voteMatchResult.voteMatchId = voteMatchId;
      this.voteMatchResult.teamMember.teamMemberId = JSON.parse(sessionStorage.getItem("userInfo")).teamMemberId;
      this.voteMatchResult.attendance = result;
      alert(JSON.stringify(this.voteMatchResult));
      axios
        .post("/vote-match-result", this.voteMatchResult)
        .then(alert("투표가 완료되었습니다."))
        .catch(() => {
          alert("투표 진행 중 오류가 발생했습니다.");
        });
    },
    /*------- 추후 사라질 메소드 ------------ */
    // 로그인
    login() {
      sessionStorage.setItem("userInfo", JSON.stringify(this.userInfo));
    }
  },
  filters: {
    showOnlyTime(value) {
      return value.slice(11, 16);
    },
    showMatchType(value) {
      return value + " : " + value;
    },
    showParking(value) {
      if (value == 1) return "주차가능";
      else return "주차불가능";
    }
  }
};
