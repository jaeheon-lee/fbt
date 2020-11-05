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

      // 지인 초대 관련 변수
      inputEmail: null, //검색할 이메일
      friends: [],

      activeMemberList: null, //해당 인덱스이면 토글 연다
      activeFriendList: null, //해당 인덱스이면 토글 연다

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
      let voteStatus = 0;
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
    },
    // 투표하기 (생성, 수정) V002 | V003
    doVote(voteMatchId, result) {
      this.voteMatchResult.voteMatchId = voteMatchId;
      this.voteMatchResult.teamMember.teamMemberId = JSON.parse(
        sessionStorage.getItem("userInfo")
      ).teamMemberId;
      this.voteMatchResult.attendance = result;
      axios
        .post("/vote-match-result", this.voteMatchResult)
        .then(alert("투표가 완료되었습니다."))
        .catch(() => {
          alert("투표 진행 중 오류가 발생했습니다.");
        });
    },
    // 지인찾기 V004
    searchFriend() {
      axios
        .get(
          "/user/1?email=" +
            this.inputEmail +
            "&teamId=" +
            JSON.parse(sessionStorage.getItem("userInfo")).teamId
        )
        .then(response => {
          this.loading = true;
          this.friends = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 지인초대하기
    inviteFriend(email, voteMatchId) {
      let invite = {
        teamMember: {
          teamMemberId: JSON.parse(sessionStorage.getItem("userInfo"))
            .teamMemberId
        },
        user: {
          email: email
        },
        voteMatch: {
          voteMatchId: voteMatchId
        }
      };
      axios
        .post("/vote-match/invite", invite)
        .then(() => {
          alert("초대가 완료됐습니다.");
        })
        .catch(() => {
          alert("초대에 실패했습니다.");
        });
    },
    // 명단보기 창 여닫기
    openMemberList(i) {
      this.activeMemberList = i;
    },
    closeMemberList() {
      this.activeMemberList = null;
    },
    openFriendList(i) {
      this.activeFriendList = i;
    },
    closeFriendList() {
      this.activeFriendList = null;
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
    },
    attendanceFliter(value) {
      if (value == 1) {
        //참이라면
        return "참";
      } else {
        // 불이라면
        return "불";
      }
    }
  }
};
