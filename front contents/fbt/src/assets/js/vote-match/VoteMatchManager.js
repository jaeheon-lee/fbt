import VoteMatchInsert from "@/components/VoteMatch/VoteMatchInsert.vue";
import VoteMatch from "@/views/VoteMatch.vue";

export default {
  name: "vote-match-manager",
  components: {
    // eslint-disable-next-line prettier/prettier
    'vote-match-insert': VoteMatchInsert,
    "vote-match": VoteMatch
  },
  data() {
    return {
      // 투표 현황과 투표 생성 구분 변수 동시에 버튼에 색 입히기
      isInsert: false,
      // 관리자 계정일 때만 볼 수 있도록
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
    this.isManager = true;
  },
  mounted() {
    this.isManager = true;
  },
  destroyed() {
    this.isManager = false;
  },
  methods: {}
};
