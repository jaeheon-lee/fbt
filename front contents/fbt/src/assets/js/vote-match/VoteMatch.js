import axios from "axios";
export default {
  name: "voteMatch",
  components: {},
  data() {
    return {
      // 투표 변수
      votes: [],

      //기본 변수
      errored: false,
      loading: true
    };
  },
  mounted() {
    this.showVoteInfo();
  },
  methods: {
    // 진행 중인 투표 출력 (V001)
    showVoteInfo() {
      // eslint-disable-next-line prettier/prettier
      axios
        .get("/vote-match/1")
        .then(response => {
          this.votes = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team.emblem) {
        return require("@/assets/image/emblem/" + team.emblem);
      } else {
        return require("@/assets/image/emblem/emptyFC.png");
      }
    }
  }
};