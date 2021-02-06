<template>
  <v-container fluid class="ma-0 pa-0 mb-6">
    <v-row justify="center" class="mb-2">
      회원 상세정보
    </v-row>
    <v-row class="mx-10">
      <v-divider color="grey"></v-divider>
    </v-row>
    <v-row justify="center">
      <v-card width="100%">
        <!-- 이름, 활동지역, 용병참석횟수-->
        <v-row class="mx-10 my-0 pt-5 pb-1">
          <!-- 이름, 활동지역, 용병참석횟수, 주발, 포지션-->
          <v-col cols="12" class="my-0 py-0">
            <v-row class="mx-1 mb-2">
              <v-col cols="8" class="my-0 py-0">
                <span>
                  <v-icon class="mr-1">mdi-hexagon</v-icon>
                  이름: {{ user.name }}
                </span>
              </v-col>
              <v-col class="my-0 py-0">
                <span>
                  <v-icon class="mr-1">mdi-hexagon</v-icon>
                  주발: {{ user.mainFoot }}
                </span>
              </v-col>
            </v-row>
            <v-row class="mx-1 mb-2">
              <v-col cols="8" class="my-0 py-0">
                <span>
                  <v-icon class="mr-1">mdi-hexagon</v-icon>
                  활동 지역: {{ user.area }}
                </span>
              </v-col>
              <v-col class="my-0 py-0">
                <span>
                  <v-icon class="mr-1">mdi-hexagon</v-icon>
                  포지션: {{ user.position }}
                </span>
              </v-col>
            </v-row>
            <v-row class="mx-1 mb-2">
              <v-col cols="12" class="my-0 py-0">
                <v-icon class="mr-1">mdi-hexagon</v-icon>
                용병 참석횟수: {{ user.totalEmpAttend }} 회
              </v-col>
            </v-row>
            <v-row class="mx-1 mb-2">
              <v-col cols="12" class="my-0 py-0">
                <v-icon class="mr-1">mdi-hexagon</v-icon>
                연락처: {{ user.phoneNum }}
              </v-col>
            </v-row>
          </v-col>
          <!-- 이름, 활동지역, 용병참석횟수, 주발, 포지션 끝-->
        </v-row>
        <v-row class="mx-10 my-0">
          <v-divider></v-divider>
        </v-row>
        <!-- 용병 과거 내역 -->
        <!--  v-if="user.empScores.length > 0" -->
        <div v-if="user.empScores && user.empScores.length > 0">
          <!-- 경기력-->
          <v-row class="mx-10 my-0 px-0 py-0">
            <v-col class="mt-1 float-left">
              경기력: {{ user.empAbility | showEmpScore }}
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
              매너: {{ user.empManner | showEmpScore }}
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
          <!-- 매너점수 / 용병 최근 전적 상세보기 -->
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
                    <span class="float-left">3. 경기 중 룰을 잘 지켰어요.</span>
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
                    <span class="float-left">8. 심판을 돌아가며 봐줬어요.</span>
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
          </v-expansion-panels>
          <!-- 매너점수 / 팀 최근 전적 상세보기 끝 -->
        </div>
        <!-- 용병 과거 내역 끝 -->
        <!-- 용병 과거가 없을 때 -->
        <v-row justify="center" class="my-3" v-else>
          용병경기 기록이 없습니다.
        </v-row>
        <!-- 용병 과거가 없을 때 끝 -->
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "user-info",
  props: {
    email: String
  },
  data() {
    return {
      // 서버로부터 받을 객체
      user: {},

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
    this.getUserInfo();
  },
  computed: {
    teamAbility: {
      get: function() {
        if (this.user) return this.user.empAbility / 2;
        return 0;
      },
      set: function() {}
    },
    teamManner: {
      get: function() {
        if (this.user) return this.user.empManner / 2;
        return 0;
      },
      set: function() {}
    }
  },
  methods: {
    //FU01
    getUserInfo() {
      this.loading = true;
      this.$axios
        .get("/user/4/" + this.email)
        .then(response => {
          this.user = response.data;
          // 매너 점수 항목별 평균을 구한다
          if (this.user.empScores) {
            this.countAvgManner();
          }
          this.loadingCompleted = true;
        })

        .catch(error => {
          console.log(error);
          this.error = true;
          alert("회원 정보 불러오는 데 실패했습니다.");
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 매너점수 상세보기: 항목별 평균 구하기
    countAvgManner() {
      // 먼저 각 합계를 구한다
      for (let i = 0; i < this.user.empScores.length; i++) {
        this.avgManners.mannerPromise += this.user.empScores[i].mannerPromise;
        this.avgManners.mannerContact += this.user.empScores[i].mannerContact;
        this.avgManners.mannerRule += this.user.empScores[i].mannerRule;
        // eslint-disable-next-line prettier/prettier
        this.avgManners.mannerBodyFight += this.user.empScores[i].mannerBodyFight;
        this.avgManners.mannerSlang += this.user.empScores[i].mannerSlang;
        this.avgManners.mannerSmoking += this.user.empScores[i].mannerSmoking;
        this.avgManners.mannerUniform += this.user.empScores[i].mannerUniform;
        this.avgManners.mannerPayment += this.user.empScores[i].mannerPayment;
        // eslint-disable-next-line prettier/prettier
              this.avgManners.mannerArrangement += this.user.empScores[i].mannerArrangement;
        this.avgManners.mannerReferee += this.user.empScores[i].mannerReferee;
        this.avgManners.mannerTackle += this.user.empScores[i].mannerTackle;
      }
      // 합계를 평가 수로 나누어 평균을 구한다
      this.avgManners.mannerPromise /= this.user.empScores.length;
      this.avgManners.mannerContact /= this.user.empScores.length;
      this.avgManners.mannerRule /= this.user.empScores.length;
      this.avgManners.mannerBodyFight /= this.user.empScores.length;
      this.avgManners.mannerSlang /= this.user.empScores.length;
      this.avgManners.mannerSmoking /= this.user.empScores.length;
      this.avgManners.mannerUniform /= this.user.empScores.length;
      this.avgManners.mannerPayment /= this.user.empScores.length;
      this.avgManners.mannerArrangement /= this.user.empScores.length;
      this.avgManners.mannerReferee /= this.user.empScores.length;
      this.avgManners.mannerTackle /= this.user.empScores.length;
    }
  },
  filters: {
    // ======================= 받은 점수 =========================//
    showEmpScore(value) {
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
