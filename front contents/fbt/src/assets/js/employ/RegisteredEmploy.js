import EmployList from "@/components/Employ/EmployList.vue";

export default {
  name: "registered-employ",
  props: {
    registeredStage: Number,
    appliedStage: Number
  },
  components: {
    "employ-list": EmployList
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
