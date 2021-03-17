import axios from "axios";
import UserInfo from "@/components/User/UserInfo.vue";
export default {
  name: "employ-list",
  props: {
    registeredStage: null,
    appliedStage: null,
    searchedEmploys: Array,
    whichBtnActive: Array
  },
  components: {
    "user-info": UserInfo
  },
  data() {
    return {
      // 매치 변수
      employs: [],
      // 토글 변수
      activeDetail: null, // 상세보기 토글
      activeTeamList: null, // 신청 용병 리스트 토글,
      activeUserInfo: null, // 회원상세보기 토글
      activeRegisterTeam: null, // 등록팀 상세보기 토글

      // get 변수
      loading: false,
      errored: false,
      empty: false
    };
  },
  mounted() {
    if (this.registeredStage != null) this.showRegisteredEmployByTeam();
    if (this.appliedStage != null) this.showAppliedEmployByUser();
  },
  watch: {
    registeredStage: function() {
      if (this.registeredStage != null) this.showRegisteredEmployByTeam();
    },
    appliedStage: function() {
      if (this.appliedStage != null) this.showAppliedEmployByUser();
    },
    searchedEmploys: function() {
      this.employs = this.searchedEmploys;
    }
  },
  methods: {
    // FE04, FE09, FE10
    showRegisteredEmployByTeam() {
      this.empty = false;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let status = 0;
      // 어떤 상태의 용병 찾기를 불러올지 navi
      switch (this.registeredStage) {
        case 1: // 용병찾기 중
          status = 0;
          break;
        case 2: // 용병 찾기 실패
          status = 1;
          break;
        case 3: // 용병 찾기 완료
          status = 2;
          break;
      }
      // 매치 중인 search를 불러오기
      axios
        .get("/employ/1/" + teamId + "/" + status)
        .then(response => {
          this.employs = response.data;
          if (this.employs.length == 0) this.empty = true;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // FE11
    showAppliedEmployByUser() {
      this.empty = false;
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      let empResultStatus = 0;
      // 어떤 상태의 용병 찾기를 불러올지 navi
      switch (this.appliedStage) {
        case 1: // 용병찾기 중
          empResultStatus = 0;
          break;
        case 2: // 용병 찾기 실패
          empResultStatus = -1;
          break;
        case 3: // 용병 찾기 완료
          empResultStatus = 1;
          break;
      }
      // 매치 중인 search를 불러오기
      axios
        .get("/employ/2/" + email + "/" + empResultStatus)
        .then(response => {
          this.employs = response.data;
          if (this.employs.length == 0) this.empty = true;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // FE05
    deleteEmploy(employ) {
      axios
        .delete("/employ/" + employ.employId)
        .then(() => {
          this.showRegisteredEmployByTeam();
          alert("삭제했습니다.");
        })
        .catch(() => {
          alert("삭제에 실패했습니다.");
        });
    },
    // FE06
    renewEmploy(employ) {
      let employId = employ.employId;
      axios
        .put("/employ/1/" + employId)
        .then(() => {
          this.showRegisteredEmployByTeam();
          alert("끌어올렸습니다.");
        })
        .catch(() => {
          alert("끌어올리기에 실패했습니다.");
        });
    },
    // FE07
    modifyApply(employRes, employ, i) {
      employRes.empResultStatus = 1;
      if (i == 1) employRes.empResultStatus = -1;
      employ.employResults = [];
      employ.employResults.push(employRes);
      axios
        .put("/employ-result/1/", employ)
        .then(() => {
          this.showRegisteredEmployByTeam();
          if (i == 0) alert("용병신청을 수락했습니다.");
          else alert("용병신청을 거절했습니다.");
        })
        .catch(() => {
          if (i == 0) alert("용병신청 수락에 실패했습니다.");
          else alert("용병신청 거절에 실패했습니다.");
        });
    },
    // FE12 
    deleteEmployRes(employ) {
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      let takerTeamId = employ.teamGiver.teamId;
      let takerEmail = employ.teamMember.teamMemberId.split("-")[1];
      let startTime = employ.matchSchedule.startTime;
      let teamName = employ.teamGiver.teamName;
      axios
        .delete(
          "/employ-result/" +
            employ.employId +
            "/" +
            email +
            "?takerTeamId=" +
            takerTeamId +
            "&teamName=" +
            teamName +
            "&takerEmail=" +
            takerEmail +
            "&startTime=" +
            startTime
        )
        .then(() => {
          this.showAppliedEmployByUser();
          alert("취소했습니다.");
        })
        .catch(() => {
          alert("취소에 실패했습니다.");
        });
    },
    // 용병신청하기(FE03)
    doApply(employ) {
      let teamIdTaker = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      let teamIdGiver = employ.teamGiver.teamId;
      // 자기글에 신청하면 다시 되돌린다
      if (teamIdTaker == teamIdGiver) {
        alert("자기 팀글입니다.");
        return false;
      }
      let employRes = {
        employId: employ.employId,
        user: {
          email: email
        }
      };
      employ.employResults = [];
      employ.employResults.push(employRes);
      axios
        .post("/employ-result?", employ)
        .then(() => {
          this.$parent.$parent.page = 2;
          alert("용병신청을 완료했습니다.");
        })
        .catch(() => {
          alert("용병신청에 실패했습니다.");
        });
    },
    //FE14
    updateEmploy(employ) {
      this.$router.push({
        name: "employUpdate",
        params: {
          pushedEmploy: employ
        }
      });
    },
    //FE15
    completeEmploy(employ) {
      let employReses = [];
      for (let i = 0; i < employ.employResults.length; i++) {
        if(employ.employResults[i].empResultStatus == 1) {
          employ.employResults[i].empResultStatus = 2;
          employReses.push(employ.employResults[i]);
        }
      }
      employ.employResults = employReses;
      this.$axios
        .put("/employ-result/1?isComplete=" + true, employ)
        .then(() => {
          alert("용병이 확정되었습니다. 일정을 확인해주세요.")
        })
        .catch(error => {
          console.log(error);
          alert("용병 확정에 실패했습니다.");
        })
        .finally(() => {
          this.$router.push({
            name: "scheduleManager"
          });
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
    // 회원 상세보기 창 여닫기
    controlUserInfoToggle(j) {
      if (this.activeUserInfo == j) this.activeUserInfo = null;
      else this.activeUserInfo = j;
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
    },
    // 구하는 팀 표시 조절 메소드
    controlStar(employ) {
      let teamGiverId = employ.teamGiver.teamId;
      let homeTeamId = employ.matchSchedule.homeTeam.teamId;
      if (teamGiverId == homeTeamId) return true;
      else return false;
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
    showShower(value) {
      if (value == 1) return "샤워가능";
      else return "샤워불가";
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
