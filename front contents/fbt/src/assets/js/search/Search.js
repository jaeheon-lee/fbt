import SearchInsert from "@/components/Search/SearchInsert.vue";
import RegisteredSearch from "@/components/Search/RegisteredSearch.vue";
import AppliedSearch from "@/components/Search/AppliedSearch.vue";
import SearchSearch from "@/components/Search/SearchSearch.vue";
export default {
  name: "search",
  props: {
    menu: Number,
    matchScheduleId: Number
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
  },
  methods: {
  }
};
