import AssignInsert from "@/components/Assign/AssignInsert.vue";
import SearchAssign from "@/components/Assign/SearchAssign.vue";
import RegisteredAssign from "@/components/Assign/RegisteredAssign.vue";
import AppliedAssign from "@/components/Assign/AppliedAssign.vue";

export default {
  name: "assign",
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
    "assign-insert": AssignInsert,
    "search-assign": SearchAssign,
    "registered-assign": RegisteredAssign,
    "applied-assign": AppliedAssign
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
      console.log(this.appliedStageR);
      switch (this.type) {
        case "applyAssign":
          this.page = this.pageR;
          this.registeredStage = this.registeredStageR;
          break;
        case "acceptAssign":
        case "refuseAssign":
          this.page = this.pageR;
          this.appliedStage = this.appliedStageR;
          break;
      }
    }
  }
};
