<template>
  <v-container fluid class="ma-0 pa-0">
    <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
        <v-row justify="center">
          <v-card width="60%">
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
            <v-row class="mx-10 my-0">
              <v-divider></v-divider>
            </v-row>
            <!-- 매너점수 끝-->
            <!-- 매너점수 상세보기 -->
            <v-expansion-panels class="px-7">
              <v-expansion-panel>
                <v-expansion-panel-header>
                  매너 상세보기
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-row>
                    <v-col class="mx-4 my-0 px-0 py-0">
                      <span class="float-left">1. 약속을 잘 지켰어요.</span>
                      <span class="float-right">{{  }}</span>
                    </v-col>
                  </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
            <!-- 매너점수 상세보기 끝 -->
          </v-card>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "team-info",
  props: {
    teamId: Number
  },
  data() {
    return {
      team: {},

      loading: false,
      error: false
    };
  },
  mounted() {
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
    }
  },
  methods: {
    getTeamInfo() {
      this.loading = true;
      this.$axios
        .get("/team/3/" + 2)
        .then(response => {
          this.team = response.data;
          console.log(this.team);
          // let a = "mannerPromise";
          if (this.team.teamScores) {
            let mannerKeys = Object.keys(this.team.teamScores[0]).slice(0, 11);
            for (let i = 0; i < mannerKeys.length; i++) {
              console.log(mannerKeys[i]);
            }
          }
        })
        .catch(error => {
          console.log(error);
          this.error = true;
          alert("팀 정보 불러오는 데 실패했습니다.");
        })
        .finally(() => {
          this.loading = false;
        });
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
