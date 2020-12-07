import axios from "axios";
export default {
  name: "employ-list",
  props: {
    registeredStage: null,
    appliedStage: null,
    searchedEmploys: Array,
    ApplyAble: Array
  },
  data() {
    return {
      // 매치 변수
      employs: [],
      // 토글 변수
      activeDetail: null, // 상세보기 토글
      activeTeamList: null, // 신청 팀리스트 토글,

      // get 변수
      loading: false,
      errored: false
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
    // 팀별 용병 신청글 출력 (A002-1) => 용변 신청 중 & 용병실패 & 성공
    showRegisteredEmployByTeam() {
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
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 개인별별 용병 신청글 출력 (A002-2) => 용변 신청 중 & 용병실패 & 성공
    showAppliedEmployByUser() {
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
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 용병 찾기 글 삭제 (E009)
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
    // 용병글 끌어올리기 (E010)
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
    // 용병신청 수락하기(M006)
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
    // 매치 예약 삭제 (M013)
    deleteEmployRes(employ) {
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      axios
        .delete("/employ-result/" + employ.employId + "/" + email)
        .then(() => {
          this.showAppliedEmployByUser();
          alert("취소했습니다.");
        })
        .catch(() => {
          alert("취소에 실패했습니다.");
        });
    },
    // 용병신청하기
    doApply(employ) {
      let teamIdTaker = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      let teamIdGiver = employ.teamGiver.teamId;
      // 자기글에 신청하면 다시 되돌린다
      if (teamIdTaker == teamIdGiver) {
        alert("자기 팀글입니다.");
        return false;
      }
      // let writer = JSON.parse(sessionStorage.getItem("userInfo")).nickName;
      let employRes = {
        employId: employ.employId,
        user: {
          email: email
        }
      };
      axios
        .post("/employ-result/", employRes)
        .then(() => {
          this.$parent.$parent.page = 2;
          alert("용병신청을 완료했습니다.");
        })
        .catch(() => {
          alert("용병신청에 실패했습니다.");
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
    showShower(value) {
      if (value == 1) return "샤워가능";
      else return "샤워불가";
    },
    content(value) {
      if (value) return value;
      else return "내용이 없습니다.";
    }
  }
};
