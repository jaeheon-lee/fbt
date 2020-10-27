export default {
  name: "voteMatch",
  components: {},
  data() {
    return {
      votes: [
        {
          reg_date: "",
          due_date: "2020-09-22 09:00",
          match_date: "2020-09-30 09:00",
          match_place: "서울시 성동구 서울숲 공원"
        },
        {
          reg_date: "2020-09-19 09:00",
          due_date: "2020-09-30 09:00",
          match_date: "2020-10-06 09:00",
          match_place: "서울시 성동구 서울숲 공원"
        }
      ]
    };
  },
  mounted() {}
};
