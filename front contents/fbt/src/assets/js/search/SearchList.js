import axios from "axios";
export default {
  name: "search-list",
  props: {
    registeredStage: null,
    appliedStage: null,
    searchedSearch: Array
  },
  data() {
    return {
      // 매치 변수
      searches: [],
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
      if (this.registeredStage != -1) this.refreshRegistered();
    },
    appliedStage: function() {
      if (this.appliedStage != -1) this.refreshApplied();
    },
    searchedSearch: function() {
      this.searches = this.searchedSearch;
    }
  },
  methods: {
    // 팀별 매치 출력 (M002-1) => 매치 중 & 매치실패
    showRegisteredSearchByTeam() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let status = 0; // 매치 실패
      if (this.registeredStage == 1) status = 1; // 매치 중
      // 매치 중인 search를 불러오기
      axios
        .get("/search/1/" + teamId + "/" + status)
        .then(response => {
          this.searches = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 인원신청 수락 시 출력 (M002-2)
    showRegisteredSearchAppliedByTeam() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let reservationStatus = 0;
      if (this.registeredStage != null) {
        // 등록 매치일 때 단계별 출력해야할 것 배분
        switch (this.registeredStage) {
          case 2: // 인원파악수락
            reservationStatus = 0;
            break;
          case 3: // 인원파악중
            reservationStatus = 1;
            break;
          case 5: //매치 성공
            reservationStatus = 2;
        }
      } else if (this.appliedStage != null) {
        // 신청 매치일 때 단계별 출력해야할 것 배분
        switch (this.appliedStage) {
          case 1: //신청
            reservationStatus = 0;
            break;
          case 2: //인원파악 중
            reservationStatus = 1;
            break;
          case 3: //매치 실패
            reservationStatus = -1;
            break;
          case 4: // 매치 성공
            reservationStatus = 2;
            break;
        }
      }
      let isApply = 0;
      if (this.registeredStage == null) isApply = 1;

      axios
        .get("/search/2/" + teamId + "/" + reservationStatus + "/" + isApply)
        .then(response => {
          this.searches = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 매치글 삭제 (M009)
    deleteSearch(search) {
      axios
        .delete("/search/" + search.searchId)
        .then(() => {
          this.refreshRegistered();
          alert("삭제했습니다.");
        })
        .catch(() => {
          alert("삭제에 실패했습니다.");
        });
    },
    // 매치글 끌어올리기 (M010)
    renewSearch(search) {
      axios
        .put("/search/1/" + search.searchId)
        .then(() => {
          this.refreshRegistered();
          alert("끌어올렸습니다.");
        })
        .catch(() => {
          alert("끌어올리기에 실패했습니다.");
        });
    },
    // 인원파악신청 수락하기(M006)
    acceptApply(searchRes, search) {
      let selectedSearch = search;
      searchRes.reservationStatus = 1;
      selectedSearch.searchReservations = [];
      selectedSearch.searchReservations.push(searchRes);
      // eslint-disable-next-line prettier/prettier
      selectedSearch.matchSchedule.writer = JSON.parse(sessionStorage.getItem("userInfo")).nickName;
      axios
        .put("/search-reservation/1", selectedSearch)
        .then(() => {
          axios
            .put("/search-reservation/2", selectedSearch)
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
        .catch(() => {});
    },
    //인원파악신청 거절하기(M006)
    refuseApply(searchRes, search, i) {
      let selectedSearch = search;
      searchRes.reservationStatus = -1;
      selectedSearch.searchReservations = [];
      selectedSearch.searchReservations.push(searchRes);
      axios
        .put("/search-reservation/1", selectedSearch)
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
    // 매치 예약 삭제 (M013)
    deleteSearchRes(search) {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      axios
        .delete("/search-reservation/" + search.searchId + "/" + teamId)
        .then(() => {
          this.refreshApplied();
          alert("취소했습니다.");
        })
        .catch(() => {
          alert("취소에 실패했습니다.");
        });
    },
    // 인원피악신청하기
    doApply(search) {
      let teamIdTaker = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let teamIdGiver = search.teamGiver.teamId;
      // 자기글에 신청하면 다시 되돌린다
      if (teamIdTaker == teamIdGiver) {
        alert("자기글입니다.");
        return false;
      }
      let writer = JSON.parse(sessionStorage.getItem("userInfo")).nickName;
      let searchRes = {
        searchId: search.searchId,
        teamTaker: {
          teamId: teamIdTaker
        },
        writer: writer
      };
      axios
        .post("/search-reservation/", searchRes)
        .then(() => {
          this.$parent.$parent.page = 4;
          alert("인원파악신청을 완료했습니다.");
        })
        .catch(() => {
          alert("인원파악신청에 실패했습니다.");
        });
    },
    // 매치 확정하기
    completeSearch(search) {
      console.log(search);
      // axios
      //   .put("/search-reservation/1")
      //   .then(() => {
      //     alert("매치가 확정됐습니다.");
      //   })
      //   .catch(error => {
      //     console.log(error);
      //     alert("매치 확정에 실패했습니다.");
      //   });
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
    // DB 조정 후 등록 매치 업데이트
    refreshRegistered() {
      switch (this.registeredStage) {
        case 1:
          this.showRegisteredSearchByTeam();
          break;
        case 2:
          this.showRegisteredSearchAppliedByTeam();
          break;
        case 3:
          this.showRegisteredSearchAppliedByTeam();
          break;
        case 4:
          this.showRegisteredSearchByTeam();
          break;
        case 5:
          this.showRegisteredSearchAppliedByTeam();
          break;
      }
    },
    // 신청 매치 리프레시
    refreshApplied() {
      this.showRegisteredSearchAppliedByTeam();
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
    // 인원신청 or 마감된 글 버튼 조절 메소드
    controlSearchApplyBtn(search) {
      // 마감조건 : 마감일이 지났거나 매치가 완료된 글
      let searchRes = search.searchReservations;
      let isEnd = false;
      for (let i = 0; i < searchRes.length; i++) {
        if (searchRes[i].reservationStatus == 2) {
          isEnd = true;
        }
      }
      let dueDate = new Date(search.dueDate);
      let today = new Date();
      if (dueDate < today || isEnd) {
        return false;
      }
      return true;
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
