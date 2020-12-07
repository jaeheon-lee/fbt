import SearchList from "@/components/Search/SearchList.vue";

export default {
  name: "registered-search",
  props: {
    registeredStage: Number,
    appliedStage: Number
  },
  components: {
    "search-list": SearchList
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
