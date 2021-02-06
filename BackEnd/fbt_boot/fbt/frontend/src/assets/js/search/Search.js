import SearchInsert from "@/components/Search/SearchInsert.vue";
import RegisteredSearch from "@/components/Search/RegisteredSearch.vue";
import AppliedSearch from "@/components/Search/AppliedSearch.vue";
import SearchSearch from "@/components/Search/SearchSearch.vue";
export default {
  name: "search",
  props: {
    menu: Number,
    matchScheduleId: Number,
    // 알림에서 넘어올 때 변수
    type: String,
    pageR: Number,
    registeredStageR: Number,
    appliedStageR: Number
  },
  components: {
    "search-insert": SearchInsert,
    "registered-search": RegisteredSearch,
    "applied-search": AppliedSearch,
    "search-search": SearchSearch
  },
  data() {
    return {
      // 어느 페이지(상위 4개)를 볼 것인가?
      page: 2,
      //// 어느 페이지(어느 단계 5개)를 볼 것인가?
      registeredStage: 1,
      appliedStage: 1
    };
  },
  created() {
    if (this.menu) this.page = this.menu;
    if (this.type) this.navigatePage();
  },
  methods: {
    navigatePage() {
      switch (this.type) {
        case "applySearch":
        case "fillNumber":
          this.page = this.pageR;
          this.registeredStage = this.registeredStageR;
          break;
        case "acceptSearch":
        case "refuseSearch":
        case "failSearch":
          this.page = this.pageR;
          this.appliedStage = this.appliedStageR;
          break;
      }
    }
  }
};
