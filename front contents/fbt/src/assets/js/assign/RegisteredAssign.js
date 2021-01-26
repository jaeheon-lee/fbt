import AssignList from "@/components/Assign/AssignList.vue";

export default {
  name: "registered-assign",
  props: {
    registeredStage: Number,
    appliedStage: Number
  },
  components: {
    "assign-list": AssignList
  },
  data() {
    return {
    };
  },
  mounted() {
  },
  methods: {
    changeRegisteredStage(stage) {
      this.$emit("change-registered-stage", stage);
    }
  }
};
