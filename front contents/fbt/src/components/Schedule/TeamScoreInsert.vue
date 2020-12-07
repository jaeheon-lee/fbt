<template>
  <div>
    <v-container fluid class="py-0 my-0 px-0 mx-0">
      <v-card class="mb-12 py-3" color="#1E1E1E">
        <!-- 용병경기팀 경기력/매너 점수 입력 -->
        <!-- 용병경기팀 경기력 점수 입력 -->
        <div class="mb-3">
          <!-- 용병경기팀 경기력 점수 입력 라벨 -->
          <v-row class="py-0 my-0 px-0 mx-5">
            1. 용병경기팀 경기력 점수 입력
          </v-row>
          <v-row class="ma-0 pa-0 mx-5 mb-3">
            <v-divider color="white">inset</v-divider>
          </v-row>
          <!-- 용병경기팀 점수 입력 라벨 끝-->
          <!-- 용병경기팀 점수 입력 표 헤더 -->
          <v-row class="ma-0 pa-0 mx-5 text-center">
            <v-col cols="4">
              용병경기팀
            </v-col>
            <v-col cols="7">
              경기력
            </v-col>
          </v-row>
          <v-row class="ma-0 pa-0 mx-5 text-center">
            <v-divider color="grey"></v-divider>
          </v-row>
          <!-- 용병경기팀 경기력 점수 입력 표 헤더 끝-->
          <!-- 용병경기팀 경기력 표 바디-->
          <!-- 공격 -->
          <v-row class="ma-0 pa-0 mx-5 text-center">
            <v-col class="pt-5" cols="4">
              공격 능력
            </v-col>
            <!-- 경기력 -->
            <v-col cols="7">
              <v-slider
                max="9"
                min="1"
                step="2"
                ticks="always"
                tick-size="5"
                :tick-labels="tickLabels"
                v-model="teamScore.forward"
              ></v-slider>
            </v-col>
          </v-row>
          <!-- 미드필드 -->
          <v-row class="ma-0 pa-0 mx-5 text-center">
            <v-col class="pt-5" cols="4">
              미드필드 능력
            </v-col>
            <!-- 경기력 -->
            <v-col cols="7">
              <v-slider
                max="9"
                min="1"
                step="2"
                ticks="always"
                tick-size="5"
                :tick-labels="tickLabels"
                v-model="teamScore.middle"
              ></v-slider>
            </v-col>
          </v-row>
          <!-- 수비 -->
          <v-row class="ma-0 pa-0 mx-5 text-center">
            <v-col class="pt-5" cols="4">
              수비 능력
            </v-col>
            <!-- 경기력 -->
            <v-col cols="7">
              <v-slider
                max="9"
                min="1"
                step="2"
                ticks="always"
                tick-size="5"
                :tick-labels="tickLabels"
                v-model="teamScore.defence"
              ></v-slider>
            </v-col>
          </v-row>
          <!-- 용병경기팀 경기력 표 바디 끝-->
        </div>
        <!-- 용병경기팀 경기력 점수 입력 끝-->
        <!-- 용병경기팀 매너 점수 입력 -->
        <div class="mb-3">
          <!-- 용병경기팀 매너 점수 입력 라벨 -->
          <v-row class="py-0 my-0 px-0 mx-5">
            2. 용병경기팀 매너 점수 입력
          </v-row>
          <v-row class="ma-0 pa-0 mx-5 mb-3">
            <v-divider color="white">inset</v-divider>
          </v-row>
          <!-- 용병경기팀 매너 점수 입력 라벨 끝-->
          <!-- 용병경기팀 매너 점수 입력 본문 -->
          <v-row class="ma-0 pa-0 mx-5 mb-3">
            <v-col>
              <!-- 매너 입력 칸 -->
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  1. 약속을 잘 지켰어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerPromise"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  2. 연락이 잘 됐어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerContact"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  3. 경기 중 룰을 잘 지켰어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerRule"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  4. 경기 중 몸싸움이 심하지 않았어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerBodyFight"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  5. 반말/욕설을 하지 않았어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerSlang"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  6. 담배를 아무데서나 안 폈어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerSmoking"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  7. 태클을 심하게 하지 않았어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerTackle"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  8. 심판을 돌아가며 봐줬어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerReferee"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  9. 유니폼 혹은 조끼를 잘 입고 왔어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerUniform"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  10. 대여료 입금을 제 시간에 했어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerPayment"
                  ></v-slider>
                </v-col>
              </v-row>
              <v-row class="ma-0 pa-0 ml-2">
                <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                  11. 뒷정리를 잘 했어요.
                </v-col>
                <v-col class="ma-0 pa-0" cols="7">
                  <v-slider
                    max="10"
                    min="0"
                    step="1"
                    ticks="always"
                    tick-size="5"
                    :tick-labels="mannerLabels"
                    v-model="teamScore.mannerArrangement"
                  ></v-slider>
                </v-col>
              </v-row>
              <!-- 매너 입력 칸 끝 -->
            </v-col>
          </v-row>
          <!-- 용병/지인 매너 점수 입력 본문 끝 -->
        </div>
        <!-- 용병경기팀 매너 점수 입력 끝 -->
        <!-- 용병/지인 매너 점수 입력 끝 -->
        <!-- 용병경기팀 경기력/매너 점수 입력 끝 -->
      </v-card>

      <v-btn class="float-right" text @click="e1 = 2">
        이전
      </v-btn>
      <v-btn class="float-right" color="primary" @click="submitMatchResult">
        확인
      </v-btn>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/schedule/TeamScoreInsert.js"></script>
<style scoped src="@/assets/css/schedule/MatchResultInsert.css"></style>
