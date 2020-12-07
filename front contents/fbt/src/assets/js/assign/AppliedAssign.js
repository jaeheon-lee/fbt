import AssignList from "@/components/Assign/AssignList.vue";

export default {
  name: "applied-assign",
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
   
  }
};
