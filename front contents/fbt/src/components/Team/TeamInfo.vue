<template>
  <v-container fluid class="ma-0 pa-0 mb-6">
    <v-row justify="center" class="mb-2">
      팀 상세정보
    </v-row>
    <v-row class="mx-10">
      <v-divider color="grey"></v-divider>
    </v-row>
    <v-row justify="center">
      <v-card width="100%">
        <!-- 팀명, 활동지역, 회원수, 유니폼 -->
        <v-row class="mx-10 my-0 pt-5 pb-1">
          <!-- 팀명, 활동지역, 회원수 -->
          <v-col cols="8" class="my-0 py-0">
            <v-row class="mb-2">
              <v-icon class="mr-1">mdi-hexagon</v-icon>
              팀명: {{ team.teamName }}
            </v-row>
            <v-row class="mb-2">
              <v-icon class="mr-1">mdi-hexagon</v-icon>
              활동 지역: {{ team.area }}
            </v-row>
            <v-row class="mb-2">
              <v-icon class="mr-1">mdi-hexagon</v-icon>
              총 회원수: {{ team.totalNum }} 명
            </v-row>
            <v-row class="mb-2">
              <v-icon class="mr-1">mdi-hexagon</v-icon>
              유니폼 색: {{ team.uniformColor }}
            </v-row>
            <v-row class="mb-2" v-if="this.teamMemberId">
              <v-icon class="mr-1">mdi-hexagon</v-icon>
              연락처: {{ phoneNum }}
            </v-row>
          </v-col>
          <!-- 팀명, 활동지역, 회원수 끝 -->
          <!-- 유니폼 -->
          <v-col cols="4" class="pt-0">
            <v-img
              contain
              height="120"
              :src="require('@/assets/image/util/vest.svg')"
              class="mt-0 mb-2"
              v-if="team.uniformType == 1"
            ></v-img>
            <v-img
              contain
              height="120"
              :src="require('@/assets/image/util/shirt.svg')"
              v-else
            ></v-img>
          </v-col>
          <!-- 유니폼 끝 -->
        </v-row>
        <v-row class="mx-10 my-0">
          <v-divider></v-divider>
        </v-row>
        <!-- 팀명, 활동지역, 회원수, 유니폼 끝 -->
        <!-- 팀 과거 내역 -->
        <div v-if="team.teamScores.length > 0">
          <!-- 경기력-->
          <v-row class="mx-10 my-0 px-0 py-0">
            <v-col class="mt-1 float-left">
              경기력: {{ team.teamAbility | showTeamScore }}
            </v-col>
            <v-col>
              <v-rating
                class="float-right"
                empty-icon="mdi-star-outline"
                full-icon="mdi-star"
                half-icon="mdi-star-half-full"
                half-increments
                hover
                length="5"
                readonly
                size="16"
                v-model="teamAbility"
              ></v-rating>
            </v-col>
          </v-row>
          <!-- 경기력 끝-->
          <!-- 매너점수-->
          <v-row class="mx-10 my-0 px-0 py-0">
            <v-col class="py-0 mt-1 float-left">
              매너: {{ team.teamManner | showTeamScore }}
            </v-col>
            <v-col class="pt-0 pb-1">
              <v-rating
                class="float-right"
                empty-icon="mdi-star-outline"
                full-icon="mdi-star"
                half-icon="mdi-star-half-full"
                half-increments
                hover
                length="5"
                readonly
                size="16"
                v-model="teamManner"
              ></v-rating>
            </v-col>
          </v-row>
          <v-row class="mx-9 my-0">
            <v-divider></v-divider>
          </v-row>
          <!-- 매너점수 끝-->
          <!-- 매너점수 / 팀 최근 전적 상세보기 -->
          <v-expansion-panels class="px-9" accordion>
            <!-- 매너 점수 상세보기 -->
            <v-expansion-panel>
              <v-expansion-panel-header class="px-4">
                매너 상세보기
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left">1. 약속을 잘 지켰어요.</span>
                    <span class="float-right">
                      {{ this.avgManners.mannerPromise }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left">2. 연락이 잘 됐어요.</span>
                    <span class="float-right">
                      {{ this.avgManners.mannerContact }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left"
                      >3. 경기 중 룰을 잘 지켰어요.</span
                    >
                    <span class="float-right">
                      {{ this.avgManners.mannerRule }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left"
                      >4. 경기 중 몸싸움이 심하지 않았어요.</span
                    >
                    <span class="float-right">
                      {{ this.avgManners.mannerBodyFight }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left"
                      >5. 반말/욕설을 하지 않았어요.</span
                    >
                    <span class="float-right">
                      {{ this.avgManners.mannerSlang }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left"
                      >6. 담배를 아무데서나 피지 않았어요.</span
                    >
                    <span class="float-right">
                      {{ this.avgManners.mannerSmoking }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left"
                      >7. 태클을 심하게 하지 않았어요.</span
                    >
                    <span class="float-right">
                      {{ this.avgManners.mannerTackle }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left"
                      >8. 심판을 돌아가며 봐줬어요.</span
                    >
                    <span class="float-right">
                      {{ this.avgManners.mannerReferee }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left"
                      >9. 유니폼 혹은 조끼를 잘 입고왔어요.</span
                    >
                    <span class="float-right">
                      {{ this.avgManners.mannerUniform }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left"
                      >10. 대여료 입금을 제시간에 했어요.</span
                    >
                    <span class="float-right">
                      {{ this.avgManners.mannerPayment }}
                    </span>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="mx-4 my-0 px-0 py-0">
                    <span class="float-left">11. 뒷정리를 잘 했어요.</span>
                    <span class="float-right">
                      {{ this.avgManners.mannerArrangement }}
                    </span>
                  </v-col>
                </v-row>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <!-- 매너 점수 상세보기 끝 -->
            <!-- 팀 최근 전적 -->
            <v-expansion-panel>
              <v-expansion-panel-header class="px-4">
                팀 최근 전적 상세보기
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <!-- 테이블 헤더 -->
                <v-row class="text-center">
                  <v-col offset="2" cols="4" class="my-0 py-0">
                    일자
                  </v-col>
                  <v-col cols="4" class="my-0 py-0">
                    결과
                  </v-col>
                </v-row>
                <v-row>
                  <v-divider class="mt-1 mb-2"></v-divider>
                </v-row>
                <!-- 테이블 헤더 끝 -->
                <!-- 테이블 바디 -->
                <v-row
                  class="text-center"
                  v-for="(ms, i) in team.matchSchedules"
                  :key="i"
                >
                  <v-col offset="2" cols="4" class="my-0 py-0">
                    {{ ms.startTime.split(" ")[0] }}
                  </v-col>
                  <v-col cols="4" class="my-0 py-0">
                    {{ matchResult(ms, team) }}
                  </v-col>
                </v-row>
                <!-- 테이블 바디 끝 -->
              </v-expansion-panel-content>
            </v-expansion-panel>
            <!-- 팀 최근 전적 끝 -->
          </v-expansion-panels>
          <!-- 매너점수 / 팀 최근 전적 상세보기 끝 -->
        </div>
        <!-- 팀 과거 내역 끝 -->
        <!-- 팀 과거가 없을 때 -->
        <v-row justify="center" class="my-3" v-else>
          팀 경기기록이 없습니다.
        </v-row>
        <!-- 팀 과거가 없을 때 끝 -->
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "team-info",
  props: {
    teamId: Number,
    teamMemberId: String
  },
  data() {
    return {
      // 서버로부터 받을 객체
      team: {
        teamScores: [],
        teamMembers: [{}]
      },

      // 매너점수 항목별 평균 점수
      avgManners: {
        mannerArrangement: 0,
        mannerBodyFight: 0,
        mannerContact: 0,
        mannerPayment: 0,
        mannerPromise: 0,
        mannerReferee: 0,
        mannerRule: 0,
        mannerSlang: 0,
        mannerSmoking: 0,
        mannerTackle: 0,
        mannerUniform: 0
      },

      loadingCompleted: false,
      loading: false,
      error: false
    };
  },
  created() {
    this.getTeamInfo();
  },
  computed: {
    teamAbility: function() {
      if (this.team) return this.team.teamAbility / 2;
      return 0;
    },
    teamManner: function() {
      if (this.team) return this.team.teamManner / 2;
      return 0;
    },
    phoneNum: function() {
      if (this.loadingCompleted) return this.team.teamMembers[0].user.phoneNum;
      else return null;
    }
  },
  methods: {
    getTeamInfo() {
      this.loading = true;
      this.$axios
        .get("/team/3/" + this.teamId + "/" + this.teamMemberId)
        .then(response => {
          this.team = response.data;
          // 매너 점수 항목별 평균을 구한다
          if (this.team.teamScores) {
            this.countAvgManner();
          }
          this.loadingCompleted = true;
        })
        .catch(error => {
          console.log(error);
          this.error = true;
          alert("팀 정보 불러오는 데 실패했습니다.");
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 매너점수 상세보기: 항목별 평균 구하기
    countAvgManner() {
      // 먼저 각 합계를 구한다
      for (let i = 0; i < this.team.teamScores.length; i++) {
        this.avgManners.mannerPromise += this.team.teamScores[i].mannerPromise;
        this.avgManners.mannerContact += this.team.teamScores[i].mannerContact;
        this.avgManners.mannerRule += this.team.teamScores[i].mannerRule;
        // eslint-disable-next-line prettier/prettier
        this.avgManners.mannerBodyFight += this.team.teamScores[i].mannerBodyFight;
        this.avgManners.mannerSlang += this.team.teamScores[i].mannerSlang;
        this.avgManners.mannerSmoking += this.team.teamScores[i].mannerSmoking;
        this.avgManners.mannerUniform += this.team.teamScores[i].mannerUniform;
        this.avgManners.mannerPayment += this.team.teamScores[i].mannerPayment;
        // eslint-disable-next-line prettier/prettier
              this.avgManners.mannerArrangement += this.team.teamScores[i].mannerArrangement;
        this.avgManners.mannerReferee += this.team.teamScores[i].mannerReferee;
        this.avgManners.mannerTackle += this.team.teamScores[i].mannerTackle;
      }
      // 합계를 평가 수로 나누어 평균을 구한다
      this.avgManners.mannerPromise /= this.team.teamScores.length;
      this.avgManners.mannerContact /= this.team.teamScores.length;
      this.avgManners.mannerRule /= this.team.teamScores.length;
      this.avgManners.mannerBodyFight /= this.team.teamScores.length;
      this.avgManners.mannerSlang /= this.team.teamScores.length;
      this.avgManners.mannerSmoking /= this.team.teamScores.length;
      this.avgManners.mannerUniform /= this.team.teamScores.length;
      this.avgManners.mannerPayment /= this.team.teamScores.length;
      this.avgManners.mannerArrangement /= this.team.teamScores.length;
      this.avgManners.mannerReferee /= this.team.teamScores.length;
      this.avgManners.mannerTackle /= this.team.teamScores.length;
    },
    // 해당 팀이 해당 일정에서 홈인지 어웨인지 파악하고 승패 리턴
    matchResult(ms, team) {
      if (ms.matchResult) {
        let homeTeamId = ms.homeTeam.teamId; // 일정에서 홈팀
        let teamId = team.teamId; // 출력하고자 하는 팀
        let score = ms.matchResult.homeScore + " : " + ms.matchResult.awayScore;
        let win = "승리 " + "(" + score + ")";
        let lose = "패배 " + "(" + score + ")";
        if (teamId == homeTeamId) {
          console.log(1);
          // 승이면 승이라고, 패면 패라고 리턴
          if (ms.matchResult.homeResult == 1) {
            return win;
          } else {
            return lose;
          }
        } else {
          if (ms.matchResult.awayResult == 1) {
            return win;
          } else {
            return lose;
          }
        }
      }
    }
  },
  filters: {
    // ======================= 받은 점수 =========================//
    showTeamScore(value) {
      if (value < 2) {
        return "하하";
      } else if (value < 4) {
        return "중하";
      } else if (value < 6) {
        return "중중";
      } else if (value < 8) {
        return "중상";
      } else {
        return "상상";
      }
    }
  }
};
</script>
