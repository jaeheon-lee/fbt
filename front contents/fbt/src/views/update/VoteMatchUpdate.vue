<template>
  <div>
    <!-- 투표 생성 section -->
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>투표 생성</span>
        </v-col>
      </v-row>
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-divider></v-divider>
          <!--홈 | 어웨이 팀 Label-->
          <v-row class="mx-0 px-0">
            <v-col cols="6" class="textc-left mx-0 pl-0 pr-1 pb-2">홈팀</v-col>
            <v-col cols="6" class="text-left mx-0 pr-0 pl-1 pb-2"
              >어웨이팀</v-col
            >
          </v-row>
          <!-- 홈 | 어웨이 Input[text] -->
          <v-row class="mx-0 px-0">
            <v-col cols="6" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col cols="12" class="ma-0 pa-0">
                  <input
                    name="HomeTeamName"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    type="text"
                    v-model="matchSchedule.homeTeam.teamName"
                    disabled
                  />
                </v-col>
              </v-row>
            </v-col>
            <!-- 상대팀 input[text] -->
            <v-col cols="6" class="pa-0 pl-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col cols="12" class="ma-0 pa-0">
                  <input
                    @click.stop="dialogAwayTeam = true"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    type="text"
                    v-model="matchSchedule.awayTeam.teamName"
                    placeholder="자체 or 상대팀을 지정해주세요"
                    readonly
                  />
                </v-col>
                <!-- 상대팀 검색 Dialog -->
                <v-dialog v-model="dialogAwayTeam" max-width="330px">
                  <v-card>
                    <v-card-title class="headline"
                      >상대팀의 유형은 무엇입니까?</v-card-title
                    >
                    <v-card-text>
                      <v-row>
                        <v-col>
                          <v-radio-group
                            v-model="awayTeamType"
                            @change="chooseAwayTeamTypeByRadio"
                            row
                          >
                            <v-radio label="미정" value="0" />
                            <v-radio label="자체" value="1" />
                            <v-radio label="상대팀" value="2" />
                          </v-radio-group>
                        </v-col>
                      </v-row>
                      <v-row v-if="awayTeamType == 2">
                        <v-col>
                          <v-row>
                            <v-col cols="11">
                              <v-text-field
                                placeholder="상대팀명을 입력해주세요."
                                v-model="inputTeamName"
                                @keydown.enter="searchTeams"
                              />
                            </v-col>
                            <v-col id="magnify" cols="1" class="ma-0 mt-9 pa-0">
                              <i
                                class="material-icons md-18"
                                @click.stop="searchTeams"
                                >search</i
                              >
                            </v-col>
                          </v-row>
                          <v-row class="text-center">
                            <v-col cols="7">
                              팀명
                            </v-col>
                          </v-row>
                          <v-divider></v-divider>
                          <v-row
                            id="friend-list"
                            class="text-center"
                            v-for="(awayTeam, i) in awayTeams"
                            :key="i"
                          >
                            <v-col cols="7">
                              {{ awayTeam.teamName }}
                            </v-col>
                            <v-col
                              cols="1"
                              class="ma-0 mt-2 mb-3 pa-0 text-center"
                            >
                              <v-btn
                                class="ma-0 ml-8 pa-0"
                                elevation="2"
                                color="#6920A3"
                                small
                                @click="
                                  selectAwayTeam(
                                    awayTeam.teamId,
                                    awayTeam.teamName
                                  )
                                "
                                style="font-size: 0.65em"
                                >선택</v-btn
                              >
                            </v-col>
                          </v-row>
                        </v-col>
                      </v-row>
                    </v-card-text>
                    <v-card-actions class="justify-end">
                      <v-btn
                        class="ma-0 ml-8 pa-0"
                        elevation="2"
                        color="#6920A3"
                        @click="dialogAwayTeam = false"
                        style="font-size: 0.65em"
                        >확인</v-btn
                      >
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>
            </v-col>
          </v-row>
          <!--팀 끝-->
          <!--경기 일시-->
          <!-- 경기 일시 라벨 -->
          <v-row class="mx-0 px-0">
            <v-col cols="8" class="text-left mx-0 px-0 pb-2">경기 일시</v-col>
            <v-col cols="4" class="text-left mx-0 px-0 pb-2">경기 시간</v-col>
          </v-row>
          <!-- 경기 일시 input -->
          <v-row class="mx-0 px-0">
            <v-col cols="8" class="pa-0 pr-2">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col cols="12" class="ma-0 pa-0">
                  <input
                    type="datetime-local"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    v-model="targetDate"
                  />
                </v-col>
              </v-row>
            </v-col>
            <!-- 경기 시간 input -->
            <v-col cols="4" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col offset="2" cols="5" class="ma-0 pa-0">
                  <input
                    id="match-hour"
                    type="text"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    v-model="matchSchedule.duration"
                  />
                </v-col>
                <v-col cols="3" class="ma-0 pa-0 text-center">
                  시간
                </v-col>
              </v-row>
            </v-col>
          </v-row>
          <!-- 경기 일시 끝 -->
          <!-- 경기장 정보-->
          <!-- 경기장 Label -->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left mx-0 px-0 pb-2"
              >경기장 정보</v-col
            >
          </v-row>
          <!-- 경기장 Input -->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left pa-0">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <!-- 경기장 input -->
                <v-col class="ma-0 pa-0">
                  <input
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff80"
                    type="text"
                    v-model="targetStadium"
                    @click="dialogStadium = true"
                    placeholder="경기장을 검색하세요."
                  />
                </v-col>
                <!-- 경기장 Dialog : Map.vue -->
                <v-dialog v-model="dialogStadium">
                  <v-row fluid justify="center">
                    <v-col xl="6" lg="8" cols="12">
                      <v-card>
                        <v-card-title class="headline"
                          >원하시는 장소를 목록에서 클릭해주세요</v-card-title
                        >
                        <v-card-text>
                          <map-vue @target="setTargetStadium"></map-vue>
                        </v-card-text>
                        <v-card-actions class="justify-end">
                          <v-btn
                            class="ma-0 ml-8 pa-0"
                            elevation="2"
                            color="#6920A3"
                            @click="dialogStadium = false"
                            style="font-size: 0.85em"
                            >확인</v-btn
                          >
                        </v-card-actions>
                      </v-card>
                    </v-col>
                  </v-row>
                </v-dialog>
              </v-row>
            </v-col>
          </v-row>
          <!--경기장 정보 끝 -->
          <!--경기타입비용주차-->
          <!-- 경기타입비용주차 Label -->
          <v-row class="mx-0 px-0">
            <v-col cols="4" class="text-left mx-0 pl-0 pr-1 px-0 pb-2"
              >경기타입</v-col
            >
            <v-col
              cols="4"
              class="text-left mx-0 px-0 pb-2"
              style="padding-left:2px;padding-right:2px;"
              >비용</v-col
            >
            <v-col cols="4" class="text-left mx-0 pr-0 pl-1 px-0 pb-2"
              >주차</v-col
            >
          </v-row>
          <!-- 경기타입비용주차 Input -->
          <v-row class="mx-0 px-0">
            <!-- 경기타입 input -->
            <v-col cols="4" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <select
                  v-model="matchSchedule.matchType"
                  :class="{
                    'before-selected': !matchTypeSelected,
                    'after-selected': matchTypeSelected
                  }"
                  @change="matchTypeSelected = true"
                >
                  <option class="options" disabled selected value="null"
                    >경기타입</option
                  >
                  <option
                    class="options"
                    v-for="matchType in matchTypes"
                    v-bind:key="matchType"
                    :value="matchType"
                    >{{ matchType }}</option
                  >
                </select>
              </v-row>
            </v-col>
            <!-- 비용 Input -->
            <v-col
              cols="4"
              class="py-0"
              style="padding-left:2px;padding-right:2px;"
            >
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col offset="2" cols="5" class="ma-0 pa-0">
                  <input
                    id="cost"
                    type="text"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    v-model="matchSchedule.cost"
                  />
                </v-col>
                <v-col cols="3" class="ma-0 pa-0 text-center">
                  만원
                </v-col>
              </v-row>
            </v-col>

            <v-col cols="4" class="pa-0 pl-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <select
                  v-model="matchSchedule.stadiumParking"
                  :class="{
                    'before-selected': !parkingSelected,
                    'after-selected': parkingSelected
                  }"
                  @change="parkingSelected = true"
                >
                  <option class="options" disabled selected value="null"
                    >주차</option
                  >
                  <option class="options" value="1">주차가능</option>
                  <option class="options" value="0">주차불가</option>
                </select>
              </v-row>
            </v-col>
          </v-row>
          <!--경기타입비용주차 끝-->
          <!-- 경기장타입, 샤워여부 -->
          <!-- 경기장타입, 샤워여부 Label-->
          <v-row class="mx-0 px-0">
            <v-col cols="4" class="text-left mx-0 pl-0 pr-1 px-0 pb-2"
              >경기장 타입</v-col
            >
            <v-col
              cols="4"
              class="text-left mx-0 px-0 pb-2"
              style="padding-left:2px;padding-right:2px;"
              >샤워여부</v-col
            >
          </v-row>
          <!-- 경기장타입, 샤워여부 Input-->
          <v-row class="mx-0 px-0">
            <!-- 경기장 타입 input -->
            <v-col cols="4" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <select
                  v-model="matchSchedule.stadiumType"
                  :class="{
                    'before-selected': !stadiumTypeSelected,
                    'after-selected': stadiumTypeSelected
                  }"
                  @change="stadiumTypeSelected = true"
                >
                  <option class="options" disabled selected value="null"
                    >경기장타입</option
                  >
                  <option
                    class="options"
                    v-for="stadiumType in stadiumTypes"
                    v-bind:key="stadiumType"
                    :value="stadiumType"
                    >{{ stadiumType }}</option
                  >
                </select>
              </v-row>
            </v-col>
            <!-- 샤워여부 input -->
            <v-col cols="4" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <select
                  v-model="matchSchedule.stadiumShower"
                  :class="{
                    'before-selected': !stadiumShowerSelected,
                    'after-selected': stadiumShowerSelected
                  }"
                  @change="stadiumShowerSelected = true"
                >
                  <option class="options" disabled selected value="null"
                    >샤워여부</option
                  >
                  <option class="options" value="1">샤워가능</option>
                  <option class="options" value="0">샤워불가</option>
                </select>
              </v-row>
            </v-col>
          </v-row>
          <!-- 경기장타입, 샤워여부 끝 -->
          <!-- 투표마감일, 최소인원 -->
          <!-- 투표마감일, 최소인원 label -->
          <v-row class="mx-0 px-0">
            <v-col cols="4" class="text-left mx-0 pl-0 pr-1 px-0 pb-2"
              >투표마감일</v-col
            >
            <v-col
              cols="4"
              class="text-left mx-0 px-0 pb-2"
              style="padding-left:2px;padding-right:2px;"
              >최소인원</v-col
            >
          </v-row>
          <!-- 투표마감일, 최소인원 label 끝 -->
          <!-- 투표마감일, 최소인원 input -->
          <v-row class="mx-0 px-0">
            <v-col cols="4" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <input
                  type="datetime-local"
                  style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                  v-model="targetDueDate"
                />
              </v-row>
            </v-col>
            <v-col cols="4" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col offset="2" cols="5" class="ma-0 pa-0">
                  <input
                    id="cancelNumber"
                    type="text"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    v-model="cancelNumber"
                  />
                </v-col>
                <v-col cols="3" class="ma-0 pa-0 text-center">
                  명
                </v-col>
              </v-row>
            </v-col>
          </v-row>
          <!-- 투표마감일, 최소인원 input 끝 -->
          <!-- 투표마감일, 최소인원 끝 -->
          <!--일정 내용-->
          <!--일정 내용 Label-->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left mx-0 px-0 pb-2">일정 내용</v-col>
          </v-row>
          <!--일정 내용 Input-->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left pa-0">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <textarea
                  v-model="matchSchedule.content"
                  cols="10000"
                  placeholder="일정에 남기고 싶은 말을 입력해주세요"
                  style="color:#ffffff"
                ></textarea>
              </v-row>
            </v-col>
          </v-row>
          <!--일정 내용 끝 -->
          <!--투표 메모-->
          <!--투표 메모 Label-->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left mx-0 px-0 pb-2">투표 메모</v-col>
          </v-row>
          <!--투표 메모 Input-->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left pa-0">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <textarea
                  v-model="voteMatch.memo"
                  cols="10000"
                  placeholder="투표 관련해서 팀에게 하고 싶은 말을 쓰세요."
                  style="color:#ffffff"
                ></textarea>
              </v-row>
            </v-col>
          </v-row>
          <!--일정 내용 끝 -->
          <!--기타 설정-->
          <v-divider class="mt-1 mb-1"></v-divider>
          <v-expansion-panels focusable hover>
            <v-expansion-panel
              class="ma-0 pa-0"
              style="background-color:rgb(52, 55, 58,0.1);"
            >
              <v-expansion-panel-header hide-actions class="ma-0 pa-0">
                <v-row class="mx-0 px-0">
                  <v-col
                    cols="11"
                    style="align-self:center;"
                    class="text-left mx-0 px-0 pl-1"
                    >(선택사항 2) 기타 설정</v-col
                  >
                  <v-col cols="1" class="text-right mx-0 px-0">
                    <v-icon>keyboard_arrow_down</v-icon>
                  </v-col>
                </v-row>
              </v-expansion-panel-header>

              <v-expansion-panel-content
                style="background-color:#121212;font-size: 0.7rem;line-height:100%;color:rgba(255, 255, 255, 0.9)"
              >
                <v-checkbox
                  class="ma-0 pa-0 mt-4"
                  style="font-size:15px!important;"
                  hide-details
                  v-model="voteMatchSetting.friendEmp"
                  label="지인 초청 가능"
                  :value="voteMatchSetting.friendEmp"
                ></v-checkbox>
                <v-checkbox
                  class="ma-0 pa-0 mt-2"
                  style="font-size:15px!important;"
                  hide-details
                  v-model="voteMatchSetting.isFirst"
                  label="목표 인원 도달시 선착순 마감"
                  :value="voteMatchSetting.isFirst"
                ></v-checkbox>
                <v-checkbox
                  class="ma-0 pa-0 mt-2"
                  style="font-size:15px!important;"
                  hide-details
                  v-model="voteMatchSetting.waiting"
                  label="선착순 마감 시 대기가능"
                  :value="voteMatchSetting.waiting"
                ></v-checkbox>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
          <!--기타 설정 끝-->
          <!-- 투표 등록 btn -->
          <v-row class="ma-0 pa-4 justify-center">
            <v-btn large color="#AD1457" @click="updateVoteMatch"
              >투표 수정하기</v-btn
            >
          </v-row>
        </v-col>
      </v-row>
      <v-dialog v-model="dialogKakao">
        <kakao-link
          :dialogKakao="dialogKakao"
          :updateVoteMatch="voteMatch"
          @close="closeKaokao"
        ></kakao-link>
      </v-dialog>
    </v-container>
  </div>
</template>
<script scroped src="@/assets/js/vote-match/VoteMatchUpdate.js"></script>
<style scoped src="@/assets/css/common/Insert.css"></style>
