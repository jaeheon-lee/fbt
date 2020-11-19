import axios from "axios";
export default {
  name: "registered-search",
  props: {
    stage: Number
  },
  data() {
    return {
      // 매치 변수
      searches: [],
      // 토글 변수
      activeDetail: null, // 상세보기 토글
    };
  },
  mounted() {
    this.showSearch();
  },
  methods: {
    // 팀별 투표 출력 (V001)
    showSearch() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      // 매치 중인 search를 불러오기
      axios
        .get("/search/1/" + teamId)
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
    // 매치글 삭제
    deleteSearch(search) {
      axios
        .delete("/search/" + search.searchId)
        .then(() => {
          this.showSearch();
          alert("삭제했습니다.");
        })
        .catch(() => {
          alert("삭제에 실패했습니다.");
        });
    },
    // 매치글 끌어올리기
    renewSearch(search) {
      axios
        .put("/search/1/" + search.searchId)
        .then(() => {
          this.showSearch();
          alert("끌어올렸습니다.");
        })
        .catch(() => {
          alert("끌어올리기에 실패했습니다.");
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
