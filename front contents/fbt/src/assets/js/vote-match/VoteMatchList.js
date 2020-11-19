import axios from "axios";
export default {
  name: "voteMatchList",
  props: {
    isEnd: Boolean,
    isManager: Boolean,
    selectedEvent: Object,
    selectedOpen: Boolean
  },
  data() {
    return {
      // 투표 변수
      votes: [],
      voteMatchResults: [],

      // 상세 정보 관련 변수
      activeDetail: null, // 해당 인덱스면 해당 인덱스를 가진 카드의 상세 정보 창을 연다.

      // 지인 초대 관련 변수
      inputEmail: null, //검색할 이메일
      friends: [],

      activeMemberList: null, //해당 인덱스이면 토글 연다
      activeFriendList: null, //해당 인덱스이면 토글 연다
      openType: -1, // 0이면 명단만을 연다 , 1이면 초대만을 연다

      // 일반 버튼 관련 변수
      attendBtnActive: false, // voteStatus = 1 이면 true => 참불 버튼은 연다
      waitBtnActive: false, // 지인 초대 버튼을 연다
      cancelBtnActive: false, // true면 참석 취소하기, false면 대기하기

      //관리자 버튼 관련 변수
      additionBtnActive: false, // 추가인원받기 버튼

      //기본 변수
      errored: false,
      loading: true
    };
  },
  mounted() {
    this.refresh();
  },
  computed: {
    votes2: function() {
      return this.votes;
    }
  },
  watch: {
    // 투표 진행 중 or 마감을 눌렀을 때 해당 리스트 출력
    isEnd: function() {
      this.showVoteInfo();
      this.activeDetail = null;
    },
    selectedOpen: function() {
      if (this.selectedOpen == true) this.showVoteInfoBySchedueId();
      this.votes = [];
    }
  },
  methods: {
    /* ------------------ 공통 메소드 --------------------------*/
    // 투표하기 (생성, 수정, 대기) V002 | V003
    doVote(voteMatchId, result) {
      let voteMatchResult = {
        voteMatchResultId: null,
        voteMatchId: null,
        teamMember: {
          teamMemberId: null,
          user: {
            email: null
          }
        },
        email: null,
        attendance: null
      };
      voteMatchResult.voteMatchId = voteMatchId;
      voteMatchResult.teamMember.teamMemberId = JSON.parse(
        sessionStorage.getItem("userInfo")
      ).teamMemberId;
      voteMatchResult.attendance = result;
      let voteMatchResultId =
        voteMatchId + "-" + voteMatchResult.teamMember.teamMemberId;
      voteMatchResult.voteMatchResultId = voteMatchResultId;
      axios
        .post("/vote-match-result", voteMatchResult)
        .then(response => {
          // 상황 별 알림
          if (response.data == 0) {
            alert("투표가 완료됐습니다.");
          } else if (response.data == 1) {
            alert("투표가 수정됐습니다.");
          } else {
            alert("이미 같은 항목으로 투표하셨습니다. 명단을 확인해주세요.");
          }
        })
        .catch(() => {
          alert("투표 진행 중 오류가 발생했습니다.");
        })
        .finally(() => {
          this.refresh();
        });
    },
    // 투표 마감하기 V007
    endVote(voteMatchId) {
      let voteMatch = {
        voteMatchId: null
      };
      voteMatch.voteMatchId = voteMatchId;
      axios
        .put("/vote-match/1", voteMatch)
        .then(() => {
          alert("투표가 마감됐습니다.");
        })
        .catch(() => {
          alert("투표 마감에 실패했습니다.");
        })
        .finally(() => {
          this.refresh();
        });
    },
    // 지인찾기 V013
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
    // 지인초대하기 V004
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
    // 추가 인원받기: V010
    updateSetting(type, vote) {
      let voteMatchSetting = {
        voteMatchId: null,
        type: null,
        cancelMember: null,
        isFirst: null,
        waiting: null,
        friendEmp: null,
        selfMinNumber: null,
        selfMaxNumber: null,
        empDueDate: null,
        empMinNumber: null,
        awayMinNumber: null,
        awayDueDate: null,
        assignCost: null,
        empCost: null,
        searchCost: null
      };
      let notion = "";
      voteMatchSetting.voteMatchId = vote.voteMatchId;
      switch (type) {
        case "setWaiting":
          voteMatchSetting.waiting = 1;
          notion = "추가인원하기가 설정되었습니다.";
          break;
      }
      axios
        .put("/vote-match-setting", voteMatchSetting)
        .then(() => {
          alert(notion);
        })
        .catch(() => {
          alert("설정에 실패했습니다.");
        })
        .finally(() => {
          this.refresh();
        });
    },
    // 경기 취소하기 V009
    deleteVoteMatch(vote) {
      axios
        .delete("/match-schedule/" + vote.matchSchedule.matchScheduleId)
        .then(() => {
          alert("취소가 완료됐습니다.");
        })
        .catch(() => {
          alert("취소에 실패했습니다.");
        })
        .finally(() => {});
      // 창 닫기
      this.$emit("close", vote.matchSchedule.matchScheduleId);
    },
    // 새로고침
    refresh() {
      if (this.selectedEvent) this.showVoteInfoBySchedueId();
      else this.showVoteInfo();
    },
    // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team.emblem) {
        return require("@/assets/image/emblem/" + team.emblem);
      } else {
        return require("@/assets/image/emblem/emptyFC.png");
      }
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
    },
    controlAdditionBtn(vote) {
      let cancelNumber = vote.voteMatchSetting.cancelNumber;
      let totalAttend = vote.totalAttend;
      let waiting = vote.voteMatchSetting.waiting;
      // eslint-disable-next-line prettier/prettier
      if ((vote.voteStatus == 1 || totalAttend > cancelNumber) && waiting != 1){
        // (마감되거나, 목표인원 도달했을 때) + 대기인원 안 받을 때
        return true;
      } else {
        return false;
      }
    },
    // 상대팀찾기 버튼 조절 변수
    controlSearchBtn(vote) {
      let cancelNumber = vote.voteMatchSetting.cancelNumber;
      let totalAttend = vote.totalAttend;
      let awayTeamId = vote.matchSchedule.awayTeam.teamId;
      // eslint-disable-next-line prettier/prettier
      if (vote.voteStatus == 1 && totalAttend < cancelNumber && awayTeamId == 0){
        // 마감되고 목표인원 도달하지 않았을 때 + 상대팀이 없을 때
        return true;
      } else {
        return false;
      }
    },
    // 양도하기 버튼 조절 변수
    controlAssignmentBtn(vote) {
      let cancelNumber = vote.voteMatchSetting.cancelNumber;
      let totalAttend = vote.totalAttend;
      let homeTeamId = vote.matchSchedule.homeTeam.teamId;
      let sessionTeamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      // eslint-disable-next-line prettier/prettier
      if ((vote.voteStatus == 1 || totalAttend < cancelNumber) && homeTeamId == sessionTeamId){
        // (마감되거나, 목표인원 도달하지 않았을 때) + 홈팀일 때
        return true;
      } else {
        return false;
      }
    },
    // 매칭 등록으로 넘어가기
    searchTeam(vote) {
      const router = this.$router;
      router.push({
        name: "search",
        params: {
          menu: 1,
          matchScheduleId: vote.matchSchedule.matchScheduleId
        }
      });
    },
    /* 투표 페이지 관련 메소드 -------------------------------------------*/
    // 팀별 투표 출력 (V001)
    showVoteInfo() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let voteStatus = 0;
      if (this.isEnd) voteStatus = 1; // 진행이냐 마감에 따라 다르게 출력하기
      // 진행이냐 마감이냐에 따라 참불 버튼 조절
      this.controlAttendBtnOnVote();
      // 진행이냐 마감 & isFriendEmp 여부에 따라 지인투표 버튼 조절
      axios
        .get("/vote-match/1/" + teamId + "?voteStatus=" + voteStatus)
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

    // 투표 페이지에서 진행이냐 마감이냐에 따라 참불 버튼 조절
    controlAttendBtnOnVote() {
      if (this.isEnd) this.attendBtnActive = false;
      // 마김이면 안 보이기
      else this.attendBtnActive = true; // 진행이면 보이기
    },
    /* ----------------------- 일정 페이지 관련 메소드 ------------------------------- */
    // 스케쥴Id별 투표 출력(V014, V015)
    showVoteInfoBySchedueId() {
      let matchScheduleId = this.selectedEvent.matchScheduleId;
      console.log("id: " + matchScheduleId);
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo"))
        .teamMemberId;
      axios
        .get("/vote-match/2/" + matchScheduleId)
        .then(response => {
          if (this.votes.length > 0) this.votes = []; // 이미 있으면 없애기
          this.votes.push(response.data);
          let vote = this.votes[0];
          console.log(vote);
          // 참불 버튼 관리
          this.controlAttendBtnOnSchedule(vote);
          // 대기, 취소 버튼 관리
          for (let i = 0; i < vote.voteMatchResults.length; i++) {
            this.controlWaitBtn(vote, teamMemberId, i);
            this.controlCancelBtn(vote, teamMemberId, i);
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 스케줄 페이지에서 참불 버튼 관리
    controlAttendBtnOnSchedule(vote) {
      if (vote.voteStatus == 1) this.attendBtnActive = false;
      else this.attendBtnActive = true;
    },
    // 대기, 참석취소 버튼 관리
    controlWaitBtn(vote, teamMemberId, i) {
      if (vote.voteMatchSetting.waiting == true) {
        // 대기 허용이면 대기 버튼 출력
        this.waitBtnActive = true;
        if (
          teamMemberId == vote.voteMatchResults[i].teamMember.teamMemberId &&
          vote.voteMatchResults[i].attendance == 1
        ) {
          // 단, 이미 참석을 눌렀으면 대기 버튼 숨기기
          this.waitBtnActive = false;
        }
      } else {
        // 대기 불용이면 대기 버튼 숨기기
        this.waitBtnActive = false;
      }
    },
    controlCancelBtn(vote, teamMemberId, i) {
      if (
        // 이미 투표 & 참석=>  취소 가능
        teamMemberId == vote.voteMatchResults[i].teamMember.teamMemberId &&
        vote.voteMatchResults[i].attendance == 1
      ) {
        this.cancelBtnActive = true;
      } else {
        // 아니면 취소 불가
        this.cancelBtnActive = false;
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
      return value + " " + matchType + " 경기";
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
      } else if (value == 2) {
        // 불이라면
        return "불";
      } else {
        return "대기";
      }
    }
  }
};
