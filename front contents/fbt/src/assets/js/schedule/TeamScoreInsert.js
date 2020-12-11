export default {
  name: "team-score-insert",
  props: {
    matchScheduleId: Number
  },
  data() {
    return {
      // ============= 받는 vo =====================//
      // vote vo
      vote: {},
      // team vo
      team: {},

      // ============= 보낼 vo =====================//
      // 팀 경기, 매너점수
      teamScore: {
        mannerPromise: 0,
        mannerContact: 0,
        mannerRule: 0,
        mannerBodyFight: 0,
        mannerSlang: 0,
        mannerSmoking: 0,
        mannerUniform: 0,
        mannerPayment: 0,
        mannerArrangement: 0,
        mannerReferee: 0,
        mannerTackle: 0,
        teamAbility: 0,
        forward: 0,
        middle: 0,
        defence: 0,
        matchSchedule: {
          matchScheduleId: null
        },
        teamGiver: {
          teamId: null
        },
        teamTaker: {
          teamId: null
        },
        user: {
          email: null
        }
      },

      // ============== 페이지 관리 변수들 ====================//
      // 경기력 관련 변수
      tickLabels: ["하하", "중하", "중중", "중상", "상상"],
      abilityActive: null,

      // 매너 관련 변수
      mannerLabels: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      mannerActive: null
    };
  },
  created() {
    let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
    this.teamScore.matchSchedule.matchScheduleId = this.matchScheduleId;
    this.teamScore.user.email = email;
  },
  mounted() {
    this.findEmpTeam();
  },
  computed: {},
  methods: {
    // 용병 신청한 팀 찾기
    findEmpTeam() {
      this.$axios
        .get("/employ/3/" + this.matchScheduleId)
        .then(response => {
          this.team = response.data;
          this.teamScore.teamTaker.teamId = this.team.teamId;
        })
        .catch(error => {
          console.log(error);
          alert("용병 팀 정보를 불러오는 데 실패했습니다.")
        })
    },
    // ============== 제출====================== //
    submitMatchResult() {
      this.$axios
        .post("/match-schedule/3", this.teamScore)
        .then(() => {
          alert("입력이 완료됐습니다.");
        })
        .catch(() => {
          this.errored = true;
          alert("입력에 실패했습니다.");
        })
        .finally(() => {
          location.reload();
        });
    }
  }
};
