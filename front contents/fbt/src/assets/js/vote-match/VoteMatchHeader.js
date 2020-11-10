import VoteMatchBody from "@/components/VoteMatch/VoteMatchBody.vue";
export default {
  name: "VoteMatchBody",
  components: {
    "vote-match-body": VoteMatchBody
  },
  data() {
    return {
      // 투표 중과 투표 완료 구분 변수 동시에 버튼에 색 입히기
      isEnd: false
    };
  },
  methods: {
    //투표 중 파트 열기
    openVoteOn() {
      this.isEnd = false;
    },
    openVoteDone() {
      this.isEnd = true;
    }
  }
};
