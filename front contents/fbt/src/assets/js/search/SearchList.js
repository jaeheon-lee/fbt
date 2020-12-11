import axios from "axios";
import TeamInfo from "@/components/Team/TeamInfo.vue";
import CheckCompleteSearch from "@/components/dialog/CheckCompleteSearch.vue";

export default {
  name: "search-list",
  props: {
    registeredStage: null,
    appliedStage: null,
    searchedSearch: Array,
    whichBtnActive: Array
  },
  components: {
    "team-info": TeamInfo,
    "check-complete-search": CheckCompleteSearch
  },
  data() {
    return {
      // 매치 변수
      searches: [],
      // 토글 변수
      activeDetail: null, // 상세보기 토글
      activeTeamList: null, // 신청 팀리스트 토글
      activeTeamInfo: null, // 팀상세보기 토글

      // 다이어로그 변수
      activeCheckComplete: false, // 팀 확정 전 의사묻는 다이어로그

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
    // FM04, FM12
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
    // FM07, FM10, FM16
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
          // 매치완료 출력이면 awayTeam을 완료 팀으로 임시로 대체한다
          if (this.registeredStage == 5) {
            for (let i = 0; i < this.searches.length; i++) {
              // eslint-disable-next-line prettier/prettier
              this.searches[i].matchSchedule.awayTeam = this.searches[i].searchReservations[0].teamTaker;
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
    // FM05, FM13
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
    // FM06
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
    // FM08
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
    // FM09, FM11
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
    // FM13
    cancelSearch(search, doAssign) {
      this.deleteSearch(search);
      if (doAssign == 1) {
        const router = this.$router;
        router.push({
          name: "assign",
          params: {
            menu: 1,
            matchScheduleId: search.matchSchedule.matchScheduleId
          }
        });
      }
    },
    // FM17
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
    // FM03
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
    //FM18
    updateSearch(search) {
      this.$router.push({
        name: "searchUpdate",
        params: {
          pushedSearch: search
        }
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
    // 팀 상세정보 창 여닫기
    controlTeamInfoToggle(j) {
      if (this.activeTeamInfo == j) this.activeTeamInfo = null;
      else this.activeTeamInfo = j;
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