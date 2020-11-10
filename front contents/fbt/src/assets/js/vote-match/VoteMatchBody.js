import axios from "axios";
export default {
  name: "voteMatchOn",
  props: {
    isEnd: Boolean
  },
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

      // 상세 정보 관련 변수
      activeDetail: null, // 해당 인덱스면 해당 인덱스를 가진 카드의 상세 정보 창을 연다.

      // 지인 초대 관련 변수
      inputEmail: null, //검색할 이메일
      friends: [],

      activeMemberList: null, //해당 인덱스이면 토글 연다
      activeFriendList: null, //해당 인덱스이면 토글 연다
      openType: -1, // 0이면 명단만을 연다 , 1이면 초대만을 연다

      //기본 변수
      errored: false,
      loading: true
    };
  },
  mounted() {
    this.showVoteInfo();
  },
  watch: {
    // 투표 진행 중 or 마감을 눌렀을 때 해당 리스트 출력
    isEnd: function() {
      this.showVoteInfo();
      this.activeDetail = null;
    }
  },
  methods: {
    // 진행 중인 투표 출력 (V001)
    showVoteInfo() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let voteStatus = 0;
      if (this.isEnd) voteStatus = 1;
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
    doVote(voteMatchId, result, i) {
      this.voteMatchResult.voteMatchId = voteMatchId;
      this.voteMatchResult.teamMember.teamMemberId = JSON.parse(
        sessionStorage.getItem("userInfo")
      ).teamMemberId;
      this.voteMatchResult.attendance = result;
      axios
        .post("/vote-match-result", this.voteMatchResult)
        .then(response => {
          alert("투표가 완료되었습니다.");
          if (response.data == 1) {
            // 수정이면 객체 수정하여 동적으로 반영하기
            let voteMatchResultId =
              this.voteMatchResult.voteMatchId +
              "-" +
              this.voteMatchResult.teamMember.teamMemberId;
            for (var j = 0; j < this.votes[i].voteMatchResults.length; j++) {
              if (
                this.votes[i].voteMatchResults[j].voteMatchResultId ==
                voteMatchResultId
              ) {
                this.votes[i].voteMatchResults[j].attendance = result;
              }
            }
          }
        })
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
    // 상세정보 창 여닫기
    openDetail(i) {
      if (this.activeDetail == i) this.activeDetail = null;
      else this.activeDetail = i;
    },
    // 명단보기 창 여닫기
    openMemberList(i) {
      if (this.activeMemberList == i) this.activeMemberList = null;
      // 명단이 열려있다면 명단 닫기
      else {
        if (this.activeFriendList == i) this.activeFriendList = null; // 명단도 열려 있다면 명단 닫기
        this.activeMemberList = i;
        this.openType = 0;
      }
    },
    closeMemberList() {
      this.activeMemberList = null;
    },
    // 지인 초대 창 여닫기
    openFriendList(i) {
      if (this.activeFriendList == i) this.activeFriendList = null;
      // 초대가 열려있다면 초대 닫기
      else {
        if (this.activeMemberList == i) this.activeMemberList = null; // 명단도 열려 있다면 명단 닫기
        this.activeFriendList = i;
        this.openType = 1;
      }
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
