import EmployInsert from "@/components/Employ/EmployInsert.vue";
import RegisteredEmploy from "@/components/Employ/RegisteredEmploy.vue";

export default {
  name: "employ-manager",
  props: {
    menu: Number,
    matchScheduleId: Number
  },
  components: {
    "employ-insert": EmployInsert,
    "registered-employ": RegisteredEmploy
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
  methods: {}
};
