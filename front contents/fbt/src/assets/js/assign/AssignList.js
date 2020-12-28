import axios from "axios";
import TeamInfo from "@/components/Team/TeamInfo.vue";

export default {
  name: "assign-list",
  props: {
    registeredStage: null,
    appliedStage: null,
    searchedAssigns: Array,
    whichBtnActive: Array
  },
  components: {
    "team-info": TeamInfo
  },
  data() {
    return {
      // 매치 변수
      assigns: [],
      // 토글 변수
      activeDetail: null, // 상세보기 토글
      activeTeamList: null, // 신청 팀리스트 토글
      activeTeamInfo: null, // 팀상세보기 토글
      activeRegisterTeam: null, // 등록팀 상세보기 토글

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
    // FA04, FA10
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
    // FA07, FA12, FA13
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
    // FA05, FA11
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
    // FA06
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
    // FA08
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
          this.refreshRegistered();
        });
    },
    //FA09
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
    // FA14
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
    // FA03
    doApply(assign) {
      let teamIdTaker = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let teamIdGiver = assign.teamGiver.teamId;
      // 자기글에 신청하면 다시 되돌린다
      if (teamIdTaker == teamIdGiver) {
        alert("자기글입니다.");
        return false;
      }
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo")).teamMemberId;
      let assignmentRes = {
        assignmentId: assign.assignmentId,
        teamTaker: {
          teamId: teamIdTaker
        },
        teamMember: {
          teamMemberId: teamMemberId
        }
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
    //FM18
    updateAssign(assign) {
      this.$router.push({
        name: "assignUpdate",
        params: {
          pushedAssign: assign
        }
      });
    },
    // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team) {
        return require("@/assets/image/emblem/" + team.emblem);
      } else {
        return require("@/assets/image/emblem/emptyFC.svg");
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
    //등록팀 상세정보보기 창 여닫기
    openTeamDetail(i) {
      if (this.activeRegisterTeam == i) this.activeRegisterTeam = null;
      else this.activeRegisterTeam = i;
    },
    // 팀 상세정보 창 여닫기
    controlTeamInfoToggle(j) {
      if (this.activeTeamInfo == j) this.activeTeamInfo = null;
      else this.activeTeamInfo = j;
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
    },
    // ======================= 받은 점수 =========================//
    showTeamScore(value) {
      if (value == 0) {
        return "";
      } else if (value < 2) {
        return "하하";
      } else if (value < 4) {
        return "중하";
      } else if (value < 6) {
        return "중중";
      } else if (value < 8) {
        return "중상";
      } else {
        return "상상";
      }
    }
  }
};
