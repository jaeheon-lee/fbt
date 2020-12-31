import SearchEmploy from "@/components/Employ/SearchEmploy.vue";
import AppliedEmploy from "@/components/Employ/AppliedEmploy.vue";

export default {
  name: "employ",
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
    if (this.type) {
      this.page = this.pageR;
      this.registeredStage = this.registeredStageR;
      this.appliedStage = this.appliedStageR;
    }
  },
  methods: {}
};
