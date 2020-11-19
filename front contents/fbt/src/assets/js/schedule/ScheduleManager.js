import ScheduleBody from "@/components/Schedule/ScheduleBody.vue";

export default {
  name: "schedule-manager",
  components: {
    "schedule-body": ScheduleBody
  },
  data() {
    return {
      // 관리자 선언 변수
      isManager: false,

      //로그인 변수 -- 임시 변수
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
    this.isManager = true;
  },
  destroyed() {
    this.isManager = false;
  },
  methods: {
    // 로그인 -- 임시 메소드
    login() {
      sessionStorage.setItem("userInfo", JSON.stringify(this.userInfo));
    }
  }
};
