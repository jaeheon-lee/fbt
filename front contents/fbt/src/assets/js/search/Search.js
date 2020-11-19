import SearchInsert from "@/components/Search/SearchInsert.vue";
import RegisteredSearch from "@/components/Search/RegisteredSearch.vue";
export default {
  name: "search",
  props: {
    menu: Number,
    matchScheduleId: Number
  },
  components: {
    "search-insert": SearchInsert,
    "registered-search": RegisteredSearch
  },
  data() {
    return {
      // 어느 페이지(상위 4개)를 볼 것인가?
      page: 2,
      //// 어느 페이지(단계 5개)를 볼 것인가?
      stage: 1,

      userInfo: {
        email: "bioman2@gmail.com",
        teamId: 1,
        teamMemberId: "1-bioman2@gmail.com",
        teamName: "왕십리FC",
        nickName: "nick2"
      }
    };
  },
  created() {
    this.login();
    if (this.menu) this.page = this.menu;
  },
  methods: {
    // 해당 버튼 활성화 시, 버튼 색 입히기
    // 로그인 -- 임시 메소드
    login() {
      sessionStorage.setItem("userInfo", JSON.stringify(this.userInfo));
    }
  }
};
