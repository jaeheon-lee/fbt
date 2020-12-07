import SearchEmploy from "@/components/Employ/SearchEmploy.vue";
import AppliedEmploy from "@/components/Employ/AppliedEmploy.vue";

export default {
  name: "employ",
  props: {
    menu: Number,
    matchScheduleId: Number
  },
  components: {
    "search-employ": SearchEmploy,
    "applied-employ": AppliedEmploy
  },
  data() {
    return {
      // 어느 페이지(상위 4개)를 볼 것인가?
      page: 1,
      //// 어느 페이지(어느 단계 5개)를 볼 것인가?
      registeredStage: 1,
      appliedStage: 1
    };
  },
  created() {
    if (this.menu) this.page = this.menu;
  },
  methods: {}
};
