import VoteMatchList from "@/components/VoteMatch/VoteMatchList.vue";
export default {
  name: "voteMatch",
  components: {
    "vote-match-list": VoteMatchList
  },
  props: {
    isManager: Boolean,
    isEndR: Boolean
  },
  data() {
    return {
      //vote vot
      votes: [],
      // 투표 중과 투표 완료 구분 변수 동시에 버튼에 색 입히기
      isEnd: false,

      // 기본 변수
      loading: false,
      errored: false,
      empty: false
    };
  },
  created() {
    if (this.isEndR) this.isEnd = this.isEndR;
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
    /**
     * V02-1
     * 투표 상태 설정: isEnd = false (투표 중) / = true (투표 완료)
     */
    setIsEnd(status) {
      this.isEnd = status;
    },
    /**
     * V02-2
     * 상위 컴포넌트에 따라 voteStatus를 0(진행 중)과 1(마감) 지정한다
     * 해당 teamId와 voteStatus를 보낸다
     * 백으로부터 받은 투표 데이터를 voteMatchList.vue에 보내어 출력한다.
     * @author 강제영
     * @version 1.0
     */
    showVoteInfo() {
      this.empty = false;
      this.loading = true;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let voteStatus = 0;
      if (this.isEnd) voteStatus = 1; // 진행이냐 마감에 따라 다르게 출력하기
      // 진행이냐 마감 & isFriendEmp 여부에 따라 지인투표 버튼 조절
      this.$axios
        .get("/vote-match/1/" + teamId + "?voteStatus=" + voteStatus)
        .then(response => {
          this.votes = response.data;
          if (this.votes.length == 0) this.empty = true;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          setTimeout(() => {
            this.loading = false;
          }, 1000);
        });
    }
  }
};
