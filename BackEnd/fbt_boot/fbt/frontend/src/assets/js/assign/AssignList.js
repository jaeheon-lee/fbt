import axios from "axios";
import TeamInfo from "@/components/Team/TeamInfo.vue";
import CheckCompleteApply from "@/components/dialog/CheckCompleteApply.vue";

export default {
  name: "assign-list",
  props: {
    registeredStage: null,
    appliedStage: null,
    searchedAssigns: Array,
    whichBtnActive: Array
  },
  components: {
    "team-info": TeamInfo,
    "check-complete-apply": CheckCompleteApply
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

      activeCheckComplete: false, // 팀 확정 전 의사묻는 다이어로그

      // 완료된 팀 정보 관련 변수
      acceptedTeamId: null,
      acceptedTeamMemberId: null,

      // get 변수
      loading: false,
      errored: false,
      empty: false
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
      this.empty = false;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let status = 0; // 매치 실패
      if (this.registeredStage == 1) status = 1; // 양도 중
      // 매치 중인 search를 불러오기
      axios
        .get("/assignment/1/" + teamId + "/" + status)
        .then(response => {
          this.assigns = response.data;
          if (this.assigns.length == 0) this.empty = true;
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
      this.empty = false;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let reservationStatus = 0;
      if (this.registeredStage != null) {
        // 등록 양도일 때 단계별 출력해야할 것 배분
        switch (this.registeredStage) {
          case 2: // 인원파악수락
            reservationStatus = 0;
            break;
          case 3: // 인원파악중
            reservationStatus = 1;
            break;
          case 5: //양도 성공
            reservationStatus = 2;
        }
      } else if (this.appliedStage != null) {
        // 신청 양도일 때 단계별 출력해야할 것 배분
        switch (this.appliedStage) {
          case 1: //양도 신청
            reservationStatus = 0;
            break;
          case 2: //인원파악 중
            reservationStatus = 1;
            break;
          case 3: //양도 실패
            reservationStatus = -1;
            break;
          case 4: // 양도 성공
            reservationStatus = 2;
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
          if (this.assigns.length == 0) this.empty = true;
          // 양도완료 출력이면 awayTeam을 완료 팀으로 임시로 대체한다
          if (this.registeredStage == 5 || this.appliedStage == 4) {
            for (let i = 0; i < this.assigns.length; i++) {
              let index = this.assigns[i].assignmentReservations
                .map(x => x.reservationStatus)
                .indexOf(2);
              // eslint-disable-next-line prettier/prettier
              this.assigns[i].matchSchedule.homeTeam = this.assigns[i].assignmentReservations[index].teamTaker;
              // eslint-disable-next-line prettier/prettier
              this.acceptedTeamId = this.assigns[i].assignmentReservations[index].teamTaker.teamId;
              // eslint-disable-next-line prettier/prettier
              this.acceptedTeamMemberId = this.assigns[i].assignmentReservations[index].teamMember.teamMemberId;
            }
          }
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
    // FA16
    completeAssign() {
      this.activeCheckComplete = true;
    },
    closeDialog() {
      this.activeCheckComplete = false;
    },
    // FA08
    acceptApply(assignmentRes, assign) {
      assignmentRes.reservationStatus = 1;
      assign.assignmentReservations = [];
      assign.assignmentReservations.push(assignmentRes);
      assign.matchSchedule.writer = JSON.parse(sessionStorage.getItem("userInfo")).nickName;
      let teamName = JSON.parse(sessionStorage.getItem("userInfo")).teamName;
      axios
        .put("/assignment-reservation/1/?teamName=" + teamName, assign)
        .then(() => {
          axios
          .put("/assignment-reservation/2", assign)
          .then(() => {
            alert("인원파악신청을 수락하고 자동으로 투표를 생성했습니다.");
          })
          .catch(() => {
            alert("인원파악신청 수락에 실패했습니다.");
          })
          .finally(() => {
            this.refreshRegistered();
          });
        })
        .catch(() => {})
    },
    //FA09
    refuseApply(assignmentRes, assign, i) {
      let selectedAssign = assign;
      assignmentRes.reservationStatus = -1;
      selectedAssign.assignmentReservations = [];
      selectedAssign.assignmentReservations.push(assignmentRes);
      let teamName = JSON.parse(sessionStorage.getItem("userInfo")).teamName;
      axios
        .put("/assignment-reservation/1?teamName=" + teamName, selectedAssign)
        .then(() => {
          this.refreshRegistered();
          if (i == 0) alert("인원파악신청을 거절했습니다.");
          else alert("인원파악을 중단했습니다.");
        })
        .catch(() => {
          if (i == 0) alert("인원파악신청 거절에 실패했습니다.");
          else alert("인원파악 중단에 실패했습니다.");
        });
    },
    // FA14
    deleteAssignRes(assign, i) {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let teamName = JSON.parse(sessionStorage.getItem("userInfo")).teamName;
      let msgTeamTakerId = assign.teamGiver.teamId;
      let msgTaker = assign.teamMember.teamMemberId;
      axios
        .delete(
          "/assign-reservation/" +
            assign.assignmentId +
            "/" +
            teamId +
            "?teamName=" +
            teamName +
            "&msgTeamTakerId=" +
            msgTeamTakerId +
            "&msgTaker=" +
            msgTaker
        )
        .then(() => {
          this.refreshApplied();
          if (i == 0) alert("취소했습니다.");
          else alert("삭제했습니다.");
        })
        .catch(() => {
          if (i == 0) alert("취소에 실패했습니다.");
          else alert("삭제했습니다.");
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
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo"))
        .teamMemberId;
      let teamName = JSON.parse(sessionStorage.getItem("userInfo")).teamName;
      let assignmentRes = {
        assignmentId: assign.assignmentId,
        teamTaker: {
          teamId: teamIdTaker,
          teamName: teamName
        },
        teamMember: {
          teamMemberId: teamMemberId
        }
      };
      assign.assignmentReservations = [];
      assign.assignmentReservations.push(assignmentRes);
      axios
        .post("/assignment-reservation/", assign)
        .then(() => {
          this.$parent.$parent.page = 4;
          alert("양도신청을 완료했습니다.");
        })
        .catch(() => {
          alert("양도신청에 실패했습니다.");
        });
    },
    //FA15
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
        return this.$emblem + team.emblem;
      } else {
        return this.$emblem + "emptyFC.svg";
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
    // DB 조정 후 등록 매치 업데이트
    refreshRegistered() {
      switch (this.registeredStage) {
        case 1:
          this.showRegisteredAssignByTeam();
          break;
        case 2:
          this.showRegisteredAssignAppliedByTeam();
          break;
        case 3:
          this.showRegisteredAssignAppliedByTeam();
          break;
        case 4:
          this.showRegisteredAssignByTeam();
          break;
        case 5:
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
