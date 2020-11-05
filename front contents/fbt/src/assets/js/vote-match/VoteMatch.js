import VoteMatchOn from "@/components/VoteMatch/VoteMatchOn.vue";
import VoteMatchDone from "@/components/VoteMatch/VoteMatchDone.vue";
export default {
  name: "voteMatch",
  components: {
    "vote-match-on": VoteMatchOn,
    "vote-match-done": VoteMatchDone
  },
  data() {
    return {
      // 투표 중과 투표 완료 구분 변수
      isEnd: false,

      //로그인 변수
      userInfo: {
        email: "bioman2@gmail.com",
        teamId: 1,
        teamMemberId: "1-bioman2@gmail.com"
      }
    };
  },
  mounted() {
    this.login();
  },
  methods: {
    //투표 중 파트 열기
    openVoteOn() {
      this.isEnd = false;
    },
    openVoteDone() {
      this.isEnd = true;
    },
    /*------- 추후 사라질 메소드 ------------ */
    // 로그인
    login() {
      sessionStorage.setItem("userInfo", JSON.stringify(this.userInfo));
    }
  },
  filters: {}
};
