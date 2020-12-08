import VoteMatchList from "@/components/VoteMatch/VoteMatchList.vue";
export default {
  name: "voteMatch",
  components: {
    "vote-match-list": VoteMatchList
  },
  props: {
    isManager: Boolean
  },
  data() {
    return {
      //vote vot
      votes: [],
      // 투표 중과 투표 완료 구분 변수 동시에 버튼에 색 입히기
      isEnd: false
    };
  },
  mounted() {
    this.showVoteInfo();
  },
  watch: {
    // 투표 진행 중 or 마감을 눌렀을 때 해당 리스트 출력
    isEnd: function() {
      this.showVoteInfo();
    }
  },
  computed: {
    sendingHeader: function() {
      if (this.isManager) return "voteMatchManager";
      else return "voteMatch";
    },
    controlMY: function() {
      if (this.isManager) return "my-0";
      else return "my-7";
    }
  },
  methods: {
    // 팀별 투표 출력 (FV01, FV02)
    showVoteInfo() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let voteStatus = 0;
      if (this.isEnd) voteStatus = 1; // 진행이냐 마감에 따라 다르게 출력하기
      // 진행이냐 마감 & isFriendEmp 여부에 따라 지인투표 버튼 조절
      this.$axios
        .get("/vote-match/1/" + teamId + "?voteStatus=" + voteStatus)
        .then(response => {
          this.votes = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    }
  }
};
