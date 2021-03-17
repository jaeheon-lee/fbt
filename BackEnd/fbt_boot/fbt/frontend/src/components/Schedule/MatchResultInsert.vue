<template>
  <div>
    <v-container fluid class="py-0 my-0 px-0 mx-0">
      <v-stepper v-model="e1">
        <v-stepper-header>
          <v-stepper-step :complete="e1 > 1" step="1">
            경기결과작성
          </v-stepper-step>

          <v-divider></v-divider>

          <v-stepper-step :complete="e1 > 2" step="2">
            용병점수 입력
          </v-stepper-step>

          <v-divider></v-divider>

          <v-stepper-step step="3">
            상대팀점수 입력
          </v-stepper-step>
        </v-stepper-header>

        <v-stepper-items>
          <v-stepper-content class="ma-0 pa-0 px-4 py-4" step="1">
            <v-card class="mb-12 py-3" color="#1E1E1E">
              <!-- 스코어 입력 -->
              <div class="mb-3" v-if="controlScoreInsert">
                <!-- 스코어 입력 라벨 -->
                <v-row class="py-0 my-0 px-0 mx-5">
                  1. 스코어 입력
                </v-row>
                <!-- 스코어 입력 라벨 끝-->
                <v-row class="ma-0 pa-0 mx-5 mb-3">
                  <v-divider color="white">inset</v-divider>
                </v-row>
                <v-row justify="center" class="ma-0 pa-0 mx-5 mb-3">
                  <v-radio-group
                    class="ma-0 pa-0"
                    row
                    hide-details="false"
                    dense
                    v-model="matchResult.isSet"
                    v-if="isInnerMatch"
                  >
                    <v-radio label="일반스코어" value = "0" />
                    <v-radio label="세트스코어" value = "1" />
                  </v-radio-group>
                </v-row>
                <!-- 스코어 입력 본문 -->
                <v-row fluid justify="center" class="ma-0 pa-0 mx-5">
                  <span class="mr-3">
                    <v-img
                      :src="getEmbUrl(vote.matchSchedule.homeTeam)"
                      height="30"
                      width="30"
                      contain
                    />
                  </span>
                  <span class="mr-3">{{
                    vote.matchSchedule.homeTeam.teamName
                  }}</span>
                  <input
                    class=" mr-3 pl-3 text-center score"
                    type="number"
                    placeholder="0"
                    v-model="matchResult.homeScore"
                    v-if="isInnerMatch"
                    style="width: 10%"
                  />
                  <span class="mr-3">vs</span>
                  <input
                    class="mr-3 pl-3 text-center score"
                    type="number"
                    placeholder="0"
                    v-model="matchResult.awayScore"
                    v-if="isInnerMatch"
                    style="width: 10%"
                  />
                  <span class="mr-3" v-if="!awayTeamSelectActive">{{
                    awayTeamName
                  }}</span>
                  <span v-if="!awayTeamSelectActive">
                    <v-img
                      :src="getEmbUrl(vote.matchSchedule.awayTeam)"
                      height="30"
                      width="30"
                      contain
                    />
                  </span>
                </v-row>
                <!-- 스코어 입력 본문 끝-->
              </div>
              <!-- 스코어 입력 끝-->
              <!-- 팀원 출결 관리 -->
              <div>
                <!-- 팀원 출결 라벨 -->
                <v-row class="py-0 my-0 px-0 mx-5">
                  <v-col class="ma-0 pa-0">
                    <span>2. 팀원 출결 입력</span>
                  </v-col>
                </v-row>
                <v-row class="ma-0 pa-0 mx-5">
                  <v-divider color="white">inset</v-divider>
                </v-row>
                <!-- 팀원 출결 라벨 끝-->
                <!-- 팀원 출결 표 헤더 -->
                <v-row class="ma-0 pa-0 mx-5 text-center">
                  <v-col cols="1">
                    번호
                  </v-col>
                  <v-col cols="2">
                    닉네임
                  </v-col>
                  <v-col cols="2">
                    출결
                  </v-col>
                  <v-col cols="2">
                    경기 평점
                  </v-col>
                  <v-col cols="2">
                    골
                  </v-col>
                  <v-col cols="2">
                    도움
                  </v-col>
                  <v-col cols="1">
                    <v-icon
                      @click="dialogTeamMember = true"
                      style="cursor:pointer;"
                      >mdi-account-multiple-plus</v-icon
                    >
                  </v-col>
                </v-row>
                <!-- 팀원 출결 표 헤더 끝-->
                <!-- 팀원 출결 표 바디-->
                <v-row
                  class="ma-0 pa-0 mx-5 text-center"
                  v-for="(teamMember, i) in teamMembers"
                  :key="i"
                >
                  <!-- 번호 -->
                  <v-col cols="1">
                    {{ i + 1 }}
                  </v-col>
                  <!-- 닉네임 -->
                  <v-col cols="2">
                    {{ teamMember.teamMember.nickName }}
                  </v-col>
                  <!-- 출결 -->
                  <v-col cols="2">
                    <select
                      class="table-select"
                      v-model="teamMember.attendance"
                    >
                      <option class="options" value="2">출석</option>
                      <option class="options" value="1">지각</option>
                      <option class="options" value="0">결석</option>
                    </select>
                  </v-col>
                  <!-- 경기  평점 -->
                  <v-col cols="2">
                    <input
                      class="pl-3 text-center table-input"
                      type="number"
                      min="0"
                      max="10"
                      placeholder="0"
                      v-model="teamMember.matchScore"
                    />
                  </v-col>
                  <!-- 골 -->
                  <v-col cols="2">
                    <input
                      class="pl-3 text-center table-input"
                      type="number"
                      min="0"
                      placeholder="0"
                      v-model="teamMember.goal"
                    />
                  </v-col>
                  <!-- 도움 -->
                  <v-col cols="2">
                    <input
                      class="pl-3 text-center table-input"
                      type="number"
                      min="0"
                      placeholder="0"
                      v-model="teamMember.assist"
                    />
                  </v-col>
                </v-row>
                <!-- 팀원 출결 표 바디 끝-->
                <!-- 팀원 출결 본문 끝-->
              </div>
              <!-- 팀원 출결 끝 -->
              <!-- 용병/지인 출결 -->
              <div class="my-3">
                <!-- 용병/지인 출결 라벨 -->
                <v-row class="py-0 my-0 px-0 mx-5">
                  <v-col class="ma-0 pa-0">
                    <span>3. 용병/지인 출결 입력</span>
                  </v-col>
                </v-row>
                <v-row class="ma-0 pa-0 mx-5">
                  <v-divider color="white">inset</v-divider>
                </v-row>
                <!-- 용병/지인 출결 라벨 끝-->
                <!-- 용병/지인 출결 표 -->
                <div v-if="friendEmploys.length > 0">
                  <!-- 용병/지인 출결 표 헤더 -->
                  <v-row class="ma-0 pa-0 mx-5 text-center">
                    <v-col cols="1">
                      번호
                    </v-col>
                    <v-col cols="3">
                      이메일
                    </v-col>
                    <v-col cols="2">
                      출결
                    </v-col>
                    <v-col cols="2">
                      경기 평점
                    </v-col>
                    <v-col cols="2">
                      골
                    </v-col>
                    <v-col cols="2">
                      도움
                    </v-col>
                  </v-row>
                  <!-- 용병/지인 출결 표 헤더 끝-->
                  <!-- 용병/지인 출결 표 바디-->
                  <v-row
                    class="ma-0 pa-0 mx-5 text-center"
                    v-for="(friendEmploy, i) in friendEmploys"
                    :key="i"
                  >
                    <!-- 번호 -->
                    <v-col cols="1">
                      {{ i + 1 }}
                    </v-col>
                    <!-- 이메일 -->
                    <v-col cols="3">
                      {{ friendEmploy.user.email }}
                    </v-col>
                    <!-- 출결 -->
                    <v-col cols="2">
                      <select
                        class="table-select"
                        v-model="friendEmploy.attendance"
                      >
                        <option class="options" value="2">출석</option>
                        <option class="options" value="1">지각</option>
                        <option class="options" value="0">결석</option>
                      </select>
                    </v-col>
                    <!-- 경기 평정 -->
                    <v-col cols="2">
                      <input
                        class="pl-3 text-center table-input"
                        type="number"
                        min="0"
                        max="10"
                        placeholder="0"
                        v-model="friendEmploy.matchScore"
                      />
                    </v-col>
                    <!-- 골 -->
                    <v-col cols="2">
                      <input
                        class="pl-3 text-center table-input"
                        type="number"
                        min="0"
                        placeholder="0"
                        v-model="friendEmploy.goal"
                      />
                    </v-col>
                    <!-- 도움 -->
                    <v-col cols="2">
                      <input
                        class="pl-3 text-center table-input"
                        type="number"
                        min="0"
                        placeholder="0"
                        v-model="friendEmploy.assist"
                      />
                    </v-col>
                  </v-row>
                  <!-- 용병/지인 출결 표 바디 끝-->
                </div>
                <!-- 용병/지인 출결 표 끝 -->
                <!-- 용병/지인이 없을 때 -->
                <div v-else>
                  <v-row fluid justify="center">
                    용병/지인이 없습니다.
                  </v-row>
                </div>
                <!-- 용병/지인이 없을 때  끝-->
              </div>
              <!-- 용병/지인 출결 끝 -->
            </v-card>

            <v-btn class="float-right" color="primary" @click="e1 = 2">
              다음
            </v-btn>
          </v-stepper-content>

          <v-stepper-content step="2">
            <v-card class="mb-12 py-3" color="#1E1E1E">
              <!-- 용병/지인 경기력&매너점수 입력 -->
              <div v-if="friendEmploys.length > 0">
                <!-- 용병/지인 경기력 점수 입력 -->
                <div class="mb-3">
                  <!-- 용병/지인 경기력 점수 입력 라벨 -->
                  <v-row class="py-0 my-0 px-0 mx-5">
                    1. 용병/지인 경기력 점수 입력
                  </v-row>
                  <v-row class="ma-0 pa-0 mx-5 mb-3">
                    <v-divider color="white">inset</v-divider>
                  </v-row>
                  <!-- 용병/지인 경기력 점수 입력 라벨 끝-->
                  <!-- 용병/지인 경기력 점수 입력 -->
                  <v-row
                    class="ma-0 pa-0 mx-5 mb-3"
                    v-for="(friendEmploy, i) in friendEmploys"
                    :key="i"
                  >
                    <v-col>
                      <!-- 이메일 칸 -->
                      <v-row
                        class="ml-2"
                        @click="toggleAbility(i)"
                        style="cursor:pointer;"
                      >
                        {{ friendEmploy.user.email }}
                        <v-icon v-if="abilityActive != i"
                          >mdi-chevron-right</v-icon
                        >
                        <v-icon v-else>mdi-chevron-down</v-icon>
                      </v-row>
                      <v-divider color="grey">inset</v-divider>
                      <!-- 이메일 칸 끝-->
                      <!-- 매너 입력 칸 -->
                      <v-expand-transition>
                        <div v-if="abilityActive == i">
                          <v-row class="ma-0 pa-0 ml-2">
                            <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                              1. 공격 능력
                            </v-col>
                            <v-col class="ma-0 pa-0" cols="7">
                              <v-slider
                                max="9"
                                min="1"
                                step="2"
                                ticks="always"
                                tick-size="5"
                                :tick-labels="tickLabels"
                                v-model="friendEmploy.empScore.forward"
                              ></v-slider>
                            </v-col>
                          </v-row>
                          <v-row class="ma-0 pa-0 ml-2">
                            <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                              2. 미드필터 능력
                            </v-col>
                            <v-col class="ma-0 pa-0" cols="7">
                              <v-slider
                                max="9"
                                min="1"
                                step="2"
                                ticks="always"
                                tick-size="5"
                                :tick-labels="tickLabels"
                                v-model="friendEmploy.empScore.middle"
                              ></v-slider>
                            </v-col>
                          </v-row>
                          <v-row class="ma-0 pa-0 ml-2">
                            <v-col class="mx-0 my-2 px-0 py-0" cols="5">
                              3. 수비 능력
                            </v-col>
                            <v-col class="ma-0 pa-0" cols="7">
                              <v-slider
                                max="9"
                                min="1"
                                step="2"
                                ticks="always"
                                tick-size="5"
                                :tick-labels="tickLabels"
                                v-model="friendEmploy.empScore.defence"
                              ></v-slider>
                            </v-col>
                          </v-row>
                        </div>
                      </v-expand-transition>
                    </v-col>
                  </v-row>
                  <!-- 용병/지인 경기력 끝-->
                </div>
                <!-- 용병/지인 경기력 점수 입력 끝 -->
                <!-- 용병/지인 매너 점수 입력 -->
                <div class="mb-3">
                  <!-- 용병/지인 매너 점수 입력 라벨 -->
                  <v-row class="py-0 my-0 px-0 mx-5">
                    2. 용병/지인 매너 점수 입력
                  </v-row>
                  <v-row class="ma-0 pa-0 mx-5 mb-3">
                    <v-divider color="white">inset</v-divider>
                  </v-row>
                  <!-- 용병/지인 매너 점수 입력 라벨 끝-->
                  <!-- 용병/지인 매너 점수 입력 본문 -->
                  <v-row
                    class="ma-0 pa-0 mx-5 mb-3"
                    v-for="(friendEmploy, i) in friendEmploys"
                    :key="i"
                  >
                    <v-col>
                      <!-- 이메일 칸 -->
                      <v-row
                        class="ml-2"
                        @click="toggleManner(i)"
                        style="cursor:pointer;"
                      >
                        {{ friendEmploy.user.email }}
                        <v-icon v-if="mannerActive != i"
                          >mdi-chevron-right</v-icon
                        >
                        <v-icon v-else>mdi-chevron-down</v-icon>
                      </v-row>
                      <v-divider color="grey">inset</v-divider>
                      <!-- 이메일 칸 끝-->
                      <!-- 매너 입력 칸 -->
                      <v-expand-transition>
                        <div v-if="mannerActive == i">
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
                                v-model="friendEmploy.empScore.mannerPromise"
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
                                v-model="friendEmploy.empScore.mannerContact"
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
                                v-model="friendEmploy.empScore.mannerRule"
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
                                v-model="friendEmploy.empScore.mannerBodyFight"
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
                                v-model="friendEmploy.empScore.mannerSlang"
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
                                v-model="friendEmploy.empScore.mannerSmoking"
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
                                v-model="friendEmploy.empScore.mannerTackle"
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
                                v-model="friendEmploy.empScore.mannerReferee"
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
                                v-model="friendEmploy.empScore.mannerUniform"
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
                                v-model="friendEmploy.empScore.mannerPayment"
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
                                v-model="friendEmploy.empScore.mannerArrangement"
                              ></v-slider>
                            </v-col>
                          </v-row>
                        </div>
                      </v-expand-transition>
                      <!-- 매너 입력 칸 끝 -->
                    </v-col>
                  </v-row>
                  <!-- 용병/지인 매너 점수 입력 본문 끝 -->
                </div>
                <!-- 용병/지인 매너 점수 입력 끝 -->
              </div>
              <!-- 용병/지인 경기력&매너점수 입력 끝 -->
              <!-- 용병/지인이 없을 때 -->
              <div v-else>
                <v-row fluid justify="center">
                  용병/지인이 없습니다.
                </v-row>
              </div>
              <!-- 용병/지인이 없을 때  끝-->
            </v-card>

            <v-btn class="float-right" text @click="e1 = 1">
              이전
            </v-btn>
            <v-btn class="float-right" color="primary" @click="e1 = 3">
              다음
            </v-btn>
          </v-stepper-content>

          <v-stepper-content step="3">
            <v-card class="mb-12 py-3" color="#1E1E1E">
              <!-- 상대팀 경기력/매너 점수 입력 -->
              <div v-if="controlAwayTeamResult">
                <!-- 상대팀 경기력 점수 입력 -->
                <div class="mb-3">
                  <!-- 상대팀 경기력 점수 입력 라벨 -->
                  <v-row class="py-0 my-0 px-0 mx-5">
                    1. 상대팀 경기력 점수 입력
                  </v-row>
                  <v-row class="ma-0 pa-0 mx-5 mb-3">
                    <v-divider color="white">inset</v-divider>
                  </v-row>
                  <!-- 상대팀 점수 입력 라벨 끝-->
                  <!-- 상대팀 점수 입력 표 헤더 -->
                  <v-row class="ma-0 pa-0 mx-5 text-center">
                    <v-col cols="4">
                      상대팀
                    </v-col>
                    <v-col cols="7">
                      경기력
                    </v-col>
                  </v-row>
                  <v-row class="ma-0 pa-0 mx-5 text-center">
                    <v-divider color="grey"></v-divider>
                  </v-row>
                  <!-- 상대팀 경기력 점수 입력 표 헤더 끝-->
                  <!-- 상대팀 경기력 표 바디-->
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
                  <!-- 상대팀 경기력 표 바디 끝-->
                </div>
                <!-- 상대팀 경기력 점수 입력 끝-->
                <!-- 상대팀 매너 점수 입력 -->
                <div class="mb-3">
                  <!-- 상대팀 매너 점수 입력 라벨 -->
                  <v-row class="py-0 my-0 px-0 mx-5">
                    2. 상대팀 매너 점수 입력
                  </v-row>
                  <v-row class="ma-0 pa-0 mx-5 mb-3">
                    <v-divider color="white">inset</v-divider>
                  </v-row>
                  <!-- 상대팀 매너 점수 입력 라벨 끝-->
                  <!-- 상대팀 매너 점수 입력 본문 -->
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
                <!-- 상대팀 매너 점수 입력 끝 -->
                <!-- 용병/지인 매너 점수 입력 끝 -->
              </div>
              <!-- 상대팀 경기력/매너 점수 입력 끝 -->
              <!-- 상대팀이 없을 때 -->
              <div v-else>
                <v-row fluid justify="center">
                  상대팀이 없습니다.
                </v-row>
              </div>
              <!-- 상대팀이 없을 때  끝-->
            </v-card>

            <v-btn class="float-right" text @click="e1 = 2">
              이전
            </v-btn>
            <v-btn
              class="float-right"
              color="primary"
              @click="submitMatchResult"
              v-if="!isUpdate"
            >
              확인
            </v-btn>
            <v-btn
              class="float-right"
              color="primary"
              @click="updateMatchResult"
              v-else
            >
              수정
            </v-btn>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>
      <!-- 팀원 검색 Dialog -->
      <v-dialog v-model="dialogTeamMember" width="100%">
        <v-card>
          <v-card-title class="headline"
            >추가로 온 팀원을 선택해주세요</v-card-title
          >
          <v-card-text>
            <v-row>
              <v-col cols="11">
                <v-text-field
                  placeholder="팀원 이름을 입력해주세요."
                  v-model="inputTeamMemberName"
                  @keydown.enter="searchTeamMember"
                />
              </v-col>
              <v-col id="magnify" cols="1" class="ma-0 mt-9 pa-0">
                <i
                  class="material-icons md-18"
                  @click.stop="searchTeamMember"
                  style="cursor:pointer;"
                  >search</i
                >
              </v-col>
            </v-row>
            <v-row class="text-center">
              <v-col cols="3">
                이름
              </v-col>
              <v-col cols="4">
                이메일
              </v-col>
              <v-col cols="3">
                닉네임
              </v-col>
            </v-row>
            <v-divider></v-divider>
            <v-row
              id="friend-list"
              class="text-center"
              v-for="(teamMember, i) in searchedTeamMember"
              :key="i"
            >
              <v-col cols="3">
                {{ teamMember.user.name }}
              </v-col>
              <v-col cols="4">
                {{ teamMember.user.email }}
              </v-col>
              <v-col cols="3">
                {{ teamMember.nickName }}
              </v-col>
              <v-col cols="1" class="ma-0 mt-2 mb-3 pa-0 text-center">
                <v-btn
                  class="ma-0 ml-8 pa-0"
                  elevation="2"
                  color="#6920A3"
                  small
                  @click="addTeamMember(teamMember)"
                  style="font-size: 0.65em"
                  >추가</v-btn
                >
              </v-col>
            </v-row>
          </v-card-text>
          <v-card-actions class="justify-end">
            <v-btn
              class="ma-0 ml-8 pa-0"
              elevation="2"
              color="#6920A3"
              @click="dialogTeamMember = false"
              style="font-size: 0.65em"
              >확인</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-dialog>
      <!-- 팀원 검색 Dialog 끝 -->
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/schedule/MatchResultInsert.js"></script>
<style>
@media only screen and (max-width: 959px) {
  .v-stepper:not(.v-stepper--vertical) .v-stepper__label {
    display: -webkit-box; /* OLD - iOS 6-, Safari 3.1-6 */
    display: -moz-box; /* OLD - Firefox 19- (buggy but mostly works) */
    display: -ms-flexbox; /* TWEENER - IE 10 */
    display: -webkit-flex; /* NEW - Chrome */
    display: flex; /* NEW, Spec - Opera 12.1, Firefox 20+ */
  }
  @media only screen and (max-width: 959px) {
    .v-stepper:not(.v-stepper--vertical) .v-stepper__step__step {
      margin-right: 10px;
    }
  }
}
</style>
<style scoped src="@/assets/css/schedule/MatchResultInsert.css"></style>
