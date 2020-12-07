import axios from "axios";
export default {
  name: "assign-list",
  props: {
    registeredStage: null,
    appliedStage: null,
    searchedAssigns: Array
  },
  data() {
    return {
      // 매치 변수
      assigns: [],
      // 토글 변수
      activeDetail: null, // 상세보기 토글
      activeTeamList: null, // 신청 팀리스트 토글

      // get 변수
      loading: false,
      errored: false
    };
  },
  mounted() {
    if (this.registeredStage != null) this.refreshRegistered();
    if (this.appliedStage != null) this.refreshApplied();
  },
  watch: {
    registeredStage: function() {
      if (this.registeredStage != null) this.refreshRegistered();
    },
    appliedStage: function() {
      if (this.appliedStage != null) this.refreshApplied();
    },
    searchedAssigns: function() {
      this.assigns = this.searchedAssigns;
    }
  },
  methods: {
    // 팀별 양도 출력 (A002-1) => 양도 중 & 매치실패
    showRegisteredAssignByTeam() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let status = 0; // 매치 실패
      if (this.registeredStage == 1) status = 1; // 양도 중
      // 매치 중인 search를 불러오기
      axios
        .get("/assignment/1/" + teamId + "/" + status)
        .then(response => {
          this.assigns = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 양도신청 수락 시 출력 (A002-2)
    showRegisteredAssignAppliedByTeam() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let reservationStatus = 0;
      if (this.registeredStage != null) {
        // 등록 양도일 때 단계별 출력해야할 것 배분
        switch (this.registeredStage) {
          case 2: // 양도 신청
            reservationStatus = 0;
            break;
          case 4: //양도 성공
            reservationStatus = 1;
        }
      } else if (this.appliedStage != null) {
        // 신청 양도일 때 단계별 출력해야할 것 배분
        switch (this.appliedStage) {
          case 1: //양도 신청
            reservationStatus = 0;
            break;
          case 2: //양도 거절
            reservationStatus = -1;
            break;
          case 3: //양도 수락
            reservationStatus = 1;
            break;
        }
      }
      let isApply = 0;
      if (this.registeredStage == null) isApply = 1;
      axios
        // eslint-disable-next-line prettier/prettier
        .get("/assignment/2/" + teamId + "/" + reservationStatus + "/" + isApply)
        .then(response => {
          this.assigns = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 양도글 삭제 (A009)
    deleteAssign(assign) {
      axios
        .delete("/assignment/" + assign.assignmentId)
        .then(() => {
          this.refreshRegistered();
          alert("삭제했습니다.");
        })
        .catch(() => {
          alert("삭제에 실패했습니다.");
        });
    },
    // 양도글 끌어올리기 (A010)
    renewAssign(assign) {
      let assignmentId = assign.assignmentId;
      axios
        .put("/assignment/1/" + assignmentId)
        .then(() => {
          this.refreshRegistered();
          alert("끌어올렸습니다.");
        })
        .catch(() => {
          alert("끌어올리기에 실패했습니다.");
        });
    },
    // 양도신청 수락하기(M006)
    acceptApply(assignmentRes, assign) {
      assignmentRes.reservationStatus = 1;
      assign.assignmentReservations = [];
      assign.assignmentReservations.push(assignmentRes);
      axios
        .put("/assignment-reservation/1/", assign)
        .then(() => {
          this.refreshRegistered();
          alert("양도신청을 수락했습니다.");
        })
        .catch(() => {
          alert("양도신청 수락에 실패했습니다.");
        });
    },
    //양도신청 거절하기(M006)
    refuseApply(assignmentRes, assign) {
      assignmentRes.reservationStatus = -1;
      assign.assignmentReservations = [];
      assign.assignmentReservations.push(assignmentRes);
      axios
        .put("/assignment-reservation/1", assign)
        .then(() => {
          this.refreshRegistered();
          alert("양도신청을 거절했습니다.");
        })
        .catch(() => {
          alert("양도신청 거절에 실패했습니다.");
        });
    },
    // 매치 예약 삭제 (M013)
    deleteAssignRes(assign) {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      axios
        .delete("/assign-reservation/" + assign.assignmentId + "/" + teamId)
        .then(() => {
          this.refreshApplied();
          alert("취소했습니다.");
        })
        .catch(() => {
          alert("취소에 실패했습니다.");
        });
    },
    // 양도신청하기
    doApply(assign) {
      let teamIdTaker = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let teamIdGiver = assign.teamGiver.teamId;
      // 자기글에 신청하면 다시 되돌린다
      if (teamIdTaker == teamIdGiver) {
        alert("자기글입니다.");
        return false;
      }
      let writer = JSON.parse(sessionStorage.getItem("userInfo")).nickName;
      let assignmentRes = {
        assignmentId: assign.assignmentId,
        teamTaker: {
          teamId: teamIdTaker
        },
        writer: writer
      };
      axios
        .post("/assignment-reservation/", assignmentRes)
        .then(() => {
          this.$parent.$parent.page = 4;
          alert("양도신청을 완료했습니다.");
        })
        .catch(() => {
          alert("양도신청에 실패했습니다.");
        });
    },
    // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team) {
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
    // 팀리스트 창 여닫기
    openTeamList(i) {
      if (this.activeTeamList == i) this.activeTeamList = null;
      else this.activeTeamList = i;
    },
    // DB 조정 후 등록 양도 업데이트
    refreshRegistered() {
      switch (this.registeredStage) {
        case 1:
          this.showRegisteredAssignByTeam();
          break;
        case 2:
          this.showRegisteredAssignAppliedByTeam();
          break;
        case 3:
          this.showRegisteredAssignByTeam();
          break;
        case 4:
          this.showRegisteredAssignAppliedByTeam();
          break;
      }
    },
    // 신청 양도 리프레시
    refreshApplied() {
      this.showRegisteredAssignAppliedByTeam();
    },
    // 투표 생성 페이지로 이동하기
    moveToVoteMatchInsert(search, i) {
      let matchSchedule = search.matchSchedule;
      matchSchedule.homeTeam = search.teamGiver;
      matchSchedule.awayTeam = search.searchReservations[i].teamTaker;
      const router = this.$router;
      router.push({
        name: "voteMatchInsert",
        params: {
          matchScheduleProps: matchSchedule,
          searchId: search.searchId
        }
      });
    },
    // 매치 취소하고 양도하기
    cancelAssign(search) {
      this.deleteSearch(search);
      const router = this.$router;
      router.push({
        name: "assign",
        params: {
          menu: 1,
          matchScheduleId: search.matchSchedule.matchScheduleId
        }
      });
    },
    // 어웨이 이름
    getAwayTeamName(matchSchedule) {
      if (matchSchedule.awayTeam) {
        return matchSchedule.awayTeam.teamName;
      } else {
        return "미정";
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
    content(value) {
      if (value) return value;
      else return "내용이 없습니다.";
    }
  }
};
