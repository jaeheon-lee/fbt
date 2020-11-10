<template>
  <div>
    <!-- 투표 현황 | 생성 버튼 -->
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-row
            justify="center"
            fluid
            align="center"
            class="py-0 my-0 px-0 mx-0"
          >
            <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn tile dark large width="100%" class="mx-0 px-0"
                >투표현황</v-btn
              >
            </v-col>
            <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                color="#AD1457"
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                >투표생성</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
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
          <!-- 경기 종류 Label -->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="textc-left mx-0 pl-0 pr-1 pb-2">
              경기 종류
            </v-col>
          </v-row>
          <!-- 경기 종류 Radio -->
          <v-row class="mx-0 px-0">
            <v-col cols="4">
              자체 경기<input type="radio" name="typeSelf" id="typeSelf">
            </v-col>
          </v-row>
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
                    v-model="userInfo.teamName"
                    disabled
                  />
                </v-col>
                <!-- <v-dialog v-model="dialog" max-width="290px">
                  <v-card>
                    <v-card-title class="headline"
                      >Use Google's location service?</v-card-title
                    >
                  </v-card>
                </v-dialog> -->
              </v-row>
            </v-col>
            <v-col cols="6" class="pa-0 pl-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col cols="12" class="ma-0 pa-0">
                  <input
                    @click.stop="dialog = true"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    type="text"
                    v-model="awayTeam"
                  />
                </v-col>
                <v-dialog v-model="dialog" max-width="290px">
                  <v-card>
                    <v-card-title class="headline"
                      >Use Google's location service?</v-card-title
                    >
                  </v-card>
                </v-dialog>
              </v-row>
            </v-col>
          </v-row>
          <!--팀 끝-->
          <!--경기 일시-->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left mx-0 px-0 pb-2">경기 일시</v-col>
          </v-row>
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left pa-0">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col cols="12" class="ma-0 pa-0">
                  <input
                    v-if="matchDateTime == ''"
                    v-model="matchDateTime"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff80"
                    type="datetime-local"
                  />
                  <input
                    v-else
                    v-model="matchDateTime"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    type="datetime-local"
                  />
                </v-col>
                <v-dialog v-model="dialog" max-width="290px">
                  <v-card>
                    <v-card-title class="headline"
                      >Use Google's location service?</v-card-title
                    >
                  </v-card>
                </v-dialog>
              </v-row>
            </v-col>
          </v-row>
          <!-- 경기 일시 끝 -->
          <!-- 경기장 정보-->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left mx-0 px-0 pb-2"
              >경기장 정보</v-col
            >
          </v-row>
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left pa-0">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col class="ma-0 pa-0">
                  <input
                    v-if="completeresult.address == ''"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff80"
                    type="text"
                    v-model="completeresult.address"
                    @click="dialogStadium = true"
                    placeholder="경기장 주소를 입력하세요."
                  />
                  <input
                    v-else
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    type="text"
                    v-model="completeresult.address"
                    @click="dialogStadium = true"
                    placeholder="경기장 주소를 입력하세요."
                  />
                </v-col>
                <v-dialog
                  v-model="dialogStadium"
                  style="background-color:#162525;"
                >
                  <vue-daum-postcode
                    @complete="completeresult = $event"
                    :q="completeresult.address"
                    :auto-close="true"
                    :animation="true"
                    :no-shorthand="true"
                    :no-auto-mapping="true"
                    :please-read-guide="3"
                    :please-read-guide-timer="2"
                    :max-suggest-items="3"
                    :show-more-h-name="true"
                    :hide-map-btn="true"
                    :hide-eng-btn="true"
                    :always-show-eng-addr="true"
                    :zonecode-only="true"
                    :theme="{
                      bgColor: '#162525',
                      searchBgColor: '#162525',
                      contentBgColor: '#162525',
                      pageBgColor: '#162525',
                      textColor: '#FFFFFF',
                      queryTextColor: '#FFFFFF',
                      outlineColor: '#44444'
                    }"
                  />
                </v-dialog>
              </v-row>
            </v-col>
          </v-row>
          <!--경기장 정보 끝 -->
          <!--경기타입비용주차-->
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

          <v-row class="mx-0 px-0">
            <v-col cols="4" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <select
                  v-model="selectedMatchType"
                  v-if="selectedMatchType == ''"
                  style="color:rgb(255, 255, 255, 0.5);vertical-align:middle;text-align-last:center;"
                >
                  <option disabled value>경기타입</option>
                  <option
                    style="text-align-last:center;color:#ffffff;"
                    v-for="matchType in matchTypes"
                    v-bind:key="matchType"
                    >{{ matchType }}</option
                  >
                </select>
                <select
                  v-else
                  v-model="selectedMatchType"
                  style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                >
                  <option disabled value>경기타입</option>
                  <option
                    style="text-align-last:center;color:#ffffff;"
                    v-for="matchType in matchTypes"
                    v-bind:key="matchType"
                    >{{ matchType }}</option
                  >
                </select>
              </v-row>
            </v-col>
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
                <select
                  v-model="selectedCost"
                  v-if="selectedCost == ''"
                  style="color:rgb(255, 255, 255, 0.5);vertical-align:middle;text-align-last:center;"
                >
                  <option disabled value>경기비용</option>
                  <option
                    style="text-align-last:center;color:#ffffff;"
                    v-for="matchCost in matchCosts"
                    v-bind:key="matchCost"
                    >{{ matchCost }}만원</option
                  >
                </select>
                <select
                  v-else
                  v-model="selectedCost"
                  style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                >
                  <option disabled value>경기비용</option>
                  <option
                    style="text-align-last:center;color:#ffffff;"
                    v-for="matchCost in matchCosts"
                    v-bind:key="matchCost"
                    >{{ matchCost }}만원</option
                  >
                </select>
              </v-row>
            </v-col>

            <v-col cols="4" class="pa-0 pl-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <select
                  v-model="selectedPark"
                  v-if="selectedPark == ''"
                  style="color:rgb(255, 255, 255, 0.5);vertical-align:middle;text-align-last:center;"
                >
                  <option disabled value>주차</option>
                  <option style="text-align-last:center;">주차가능</option>
                  <option style="text-align-last:center;">주차불가</option>
                </select>
                <select
                  v-else
                  v-model="selectedPark"
                  style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                >
                  <option disabled value>주차</option>
                  <option style="text-align-last:center;">주차가능</option>
                  <option style="text-align-last:center;">주차불가</option>
                </select>
              </v-row>
            </v-col>
          </v-row>
          <!--경기타입비용주차-->
          <!--내용-->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left mx-0 px-0 pb-2">내용</v-col>
          </v-row>
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left pa-0">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <textarea
                  v-model="contents"
                  cols="10000"
                  placeholder="하고 싶은 말을 쓰세요."
                  style="color:#ffffff"
                ></textarea>
              </v-row>
            </v-col>
          </v-row>
          <!--내용 끝 -->
          <!--투표이후 자동화 설정-->
          <v-divider class="mt-4 mb-2"></v-divider>
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
                    >(선택사항 1) 투표 이후 자동화 설정</v-col
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
                  v-model="selectedYoungByung"
                  label="용병 모집"
                  value="youngByung"
                ></v-checkbox>
                <v-checkbox
                  class="ma-0 pa-0 mt-2"
                  style="font-size:15px!important;"
                  hide-details
                  v-model="selectedAwayTeam"
                  label="상대팀 구하기"
                  value="awayTeam"
                ></v-checkbox>
                <!-- 기준 설정 1 selectedYoungByung&&selectedAwayTeam-->
                <v-row
                  v-if="selectedYoungByung && selectedAwayTeam"
                  class="ma-0 pa-0 mt-1 align:center"
                  style="font-size:0.75rem;background-color:rgb(255,255,255,0.1)"
                >
                  <v-row
                    class="ma-2 pa-0 mt-4"
                    style="width:100%;font-size:13px"
                  >
                    <v-col
                      cols="8"
                      class="ma-0 pa-1"
                      style="align-self: center;"
                      >- 경기 취소 기준 인원</v-col
                    >
                    <v-col cols="3" class="ma-0 pa-1">
                      <v-row
                        class="ma-0"
                        justify="center"
                        style="padding:2px;border:2px solid #AD1457;"
                      >
                        <select
                          v-model="selectedMinBasisNumber"
                          v-if="selectedMinBasisNumber == ''"
                          style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                        <select
                          v-else
                          v-model="selectedMinBasisNumber"
                          style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                      </v-row>
                    </v-col>
                    <v-col
                      cols="1"
                      class="ma-0 pa-1 text-center"
                      style="align-self: center;"
                      >(?)</v-col
                    >
                  </v-row>

                  <v-row class="ma-2 pa-0" style="width:100%;font-size:13px">
                    <v-col
                      cols="8"
                      class="ma-0 pa-1"
                      style="align-self: center;"
                    >
                      <div>- 경기 가능 최소 기준 인원</div>
                      <div>(with 용병 & 다른 팀)</div>
                    </v-col>
                    <v-col cols="3" class="ma-0 pa-1">
                      <v-row
                        class="ma-0"
                        justify="center"
                        style="padding:2px;border:2px solid #AD1457;"
                      >
                        <select
                          v-model="selectedMinBasisNumber"
                          v-if="selectedMinBasisNumber == ''"
                          style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                        <select
                          v-else
                          v-model="selectedMinBasisNumber"
                          style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                      </v-row>
                    </v-col>
                    <v-col
                      cols="1"
                      class="ma-0 pa-1 text-center"
                      style="align-self: center;"
                      >(?)</v-col
                    >
                  </v-row>

                  <v-row class="ma-2 pa-0" style="width:100%;font-size:13px">
                    <v-col
                      cols="8"
                      class="ma-0 pa-1"
                      style="align-self: center;"
                    >
                      <div>- 자체 경기 최소기준 인원</div>
                      <div>(with 용병)</div>
                    </v-col>
                    <v-col cols="3" class="ma-0 pa-1">
                      <v-row
                        class="ma-0"
                        justify="center"
                        style="padding:2px;border:2px solid #AD1457;"
                      >
                        <select
                          v-model="selectedMinBasisNumber"
                          v-if="selectedMinBasisNumber == ''"
                          style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                        <select
                          v-else
                          v-model="selectedMinBasisNumber"
                          style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                      </v-row>
                    </v-col>
                    <v-col
                      cols="1"
                      class="ma-0 pa-1 text-center"
                      style="align-self: center;"
                      >(?)</v-col
                    >
                  </v-row>

                  <v-row
                    class="ma-2 pa-0 mb-2"
                    style="width:100%;font-size:13px"
                  >
                    <v-col
                      cols="8"
                      class="ma-0 pa-1"
                      style="align-self: center;"
                    >
                      <div>- 자체 경기 기준 인원</div>
                      <div>(without 용병)</div>
                    </v-col>
                    <v-col cols="3" class="ma-0 pa-1">
                      <v-row
                        class="ma-0"
                        justify="center"
                        style="padding:2px;border:2px solid #AD1457;"
                      >
                        <select
                          v-model="selectedMinBasisNumber"
                          v-if="selectedMinBasisNumber == ''"
                          style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                        <select
                          v-else
                          v-model="selectedMinBasisNumber"
                          style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                      </v-row>
                    </v-col>
                    <v-col
                      cols="1"
                      class="ma-0 pa-1 text-center"
                      style="align-self: center;"
                      >(?)</v-col
                    >
                  </v-row>
                </v-row>
                <!-- 기준 설정 1 끝 -->
                <!-- 기준 설정 2 selectedYoungByung&&selectedAwayTeam-->
                <v-row
                  v-if="selectedYoungByung && !selectedAwayTeam"
                  class="ma-0 pa-0 mt-1 align:center"
                  style="font-size:0.75rem;background-color:rgb(255,255,255,0.1)"
                >
                  <v-row class="ma-2 pa-0" style="width:100%;font-size:13px">
                    <v-col
                      cols="8"
                      class="ma-0 pa-1"
                      style="align-self: center;"
                    >
                      <div>- 자체 경기 최소기준 인원</div>
                      <div>(with 용병)</div>
                    </v-col>
                    <v-col cols="3" class="ma-0 pa-1">
                      <v-row
                        class="ma-0"
                        justify="center"
                        style="padding:2px;border:2px solid #AD1457;"
                      >
                        <select
                          v-model="selectedMinBasisNumber"
                          v-if="selectedMinBasisNumber == ''"
                          style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                        <select
                          v-else
                          v-model="selectedMinBasisNumber"
                          style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                      </v-row>
                    </v-col>
                    <v-col
                      cols="1"
                      class="ma-0 pa-1 text-center"
                      style="align-self: center;"
                      >(?)</v-col
                    >
                  </v-row>

                  <v-row
                    class="ma-2 pa-0 mb-2"
                    style="width:100%;font-size:13px"
                  >
                    <v-col
                      cols="8"
                      class="ma-0 pa-1"
                      style="align-self: center;"
                    >
                      <div>- 자체 경기 기준 인원</div>
                      <div>(without 용병)</div>
                    </v-col>
                    <v-col cols="3" class="ma-0 pa-1">
                      <v-row
                        class="ma-0"
                        justify="center"
                        style="padding:2px;border:2px solid #AD1457;"
                      >
                        <select
                          v-model="selectedMinBasisNumber"
                          v-if="selectedMinBasisNumber == ''"
                          style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                        <select
                          v-else
                          v-model="selectedMinBasisNumber"
                          style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                      </v-row>
                    </v-col>
                    <v-col
                      cols="1"
                      class="ma-0 pa-1 text-center"
                      style="align-self: center;"
                      >(?)</v-col
                    >
                  </v-row>
                </v-row>
                <!-- 기준 설정 2 끝 -->

                <!-- 기준 설정 3 -->
                <v-row
                  v-if="!selectedYoungByung && selectedAwayTeam"
                  class="ma-0 pa-0 mt-1 align:center"
                  style="font-size:0.75rem;background-color:rgb(255,255,255,0.1)"
                >
                  <v-row
                    class="ma-2 pa-0 mt-4"
                    style="width:100%;font-size:13px"
                  >
                    <v-col
                      cols="8"
                      class="ma-0 pa-1"
                      style="align-self: center;"
                      >- 경기 취소 기준 인원</v-col
                    >
                    <v-col cols="3" class="ma-0 pa-1">
                      <v-row
                        class="ma-0"
                        justify="center"
                        style="padding:2px;border:2px solid #AD1457;"
                      >
                        <select
                          v-model="selectedMinBasisNumber"
                          v-if="selectedMinBasisNumber == ''"
                          style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                        <select
                          v-else
                          v-model="selectedMinBasisNumber"
                          style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                      </v-row>
                    </v-col>
                    <v-col
                      cols="1"
                      class="ma-0 pa-1 text-center"
                      style="align-self: center;"
                      >(?)</v-col
                    >
                  </v-row>

                  <v-row
                    class="ma-2 pa-0 mb-2"
                    style="width:100%;font-size:13px"
                  >
                    <v-col
                      cols="8"
                      class="ma-0 pa-1"
                      style="align-self: center;"
                    >
                      <div>- 자체 경기 기준 인원</div>
                      <div>(without 용병)</div>
                    </v-col>
                    <v-col cols="3" class="ma-0 pa-1">
                      <v-row
                        class="ma-0"
                        justify="center"
                        style="padding:2px;border:2px solid #AD1457;"
                      >
                        <select
                          v-model="selectedMinBasisNumber"
                          v-if="selectedMinBasisNumber == ''"
                          style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                        <select
                          v-else
                          v-model="selectedMinBasisNumber"
                          style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                        >
                          <option disabled value>명</option>
                          <option
                            style="text-align-last:center;color:#ffffff;"
                            v-for="minBasisNumber in minBasisNumbers"
                            v-bind:key="minBasisNumber"
                            >{{ minBasisNumber }}명</option
                          >
                        </select>
                      </v-row>
                    </v-col>
                    <v-col
                      cols="1"
                      class="ma-0 pa-1 text-center"
                      style="align-self: center;"
                      >(?)</v-col
                    >
                  </v-row>
                </v-row>
                <!-- 기준 설정 3 끝 -->
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
          <!--투표이후 자동화 설정 끝-->
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
                  v-model="friendInvite"
                  label="지인 초청 가능"
                  value="friendInvite"
                ></v-checkbox>
                <v-checkbox
                  class="ma-0 pa-0 mt-2"
                  style="font-size:15px!important;"
                  hide-details
                  v-model="targetNumberAndFinish"
                  label="목표 인원 도달시 선착순 마감(대기가능)"
                  value="targetNumberAndFinish"
                ></v-checkbox>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
          <!--기타 설정 끝-->
          <v-row class="ma-0 pa-4 justify-center">
            <v-btn large color="#AD1457">투표 등록하기</v-btn>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script scroped src="@/assets/js/vote-match/VoteMatchManager.js"></script>
