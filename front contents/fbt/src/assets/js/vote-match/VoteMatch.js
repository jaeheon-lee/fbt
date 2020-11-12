import VoteMatchBody from "@/components/VoteMatch/VoteMatchBody.vue";
export default {
  name: "voteMatch",
  components: {
    "vote-match-body": VoteMatchBody
  },
  data() {
    return {
      // 투표 중과 투표 완료 구분 변수 동시에 버튼에 색 입히기
      isEnd: false,
      //로그인 변수 -- 임시 변수
      userInfo: {
        email: "bioman2@gmail.com",
        teamId: 1,
        teamMemberId: "1-bioman2@gmail.com",
        teamName: "왕십리FC"
      }
    };
  },
  created() {
    this.login();
  },
  methods: {
    // 로그인 -- 임시 메소드
    login() {
      sessionStorage.setItem("userInfo", JSON.stringify(this.userInfo));
    }
  }
};
