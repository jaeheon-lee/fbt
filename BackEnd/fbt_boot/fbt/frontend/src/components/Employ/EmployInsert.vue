<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <!-- 용병 생성 관련 공지 -->
      <v-row fluid justify="center" class="py-0 my-3 px-0 mx-5">
        투표 페이지 혹은 "저장된 경기 불러오기"를 통해 등록해주세요
      </v-row>
      <!-- 용병 찾기 등록 Label -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>용병 찾기 등록</span>
          <span class="ml-5" style="font-size: 0.8em"
            >* 가 표시된 항목은 필수항목입니다.</span
          >
          <span
            class="mr-2 float-right"
            @click="loadMatchSchedule"
            style="cursor:pointer;"
            >저장된 경기 정보 불러오기</span
          >
          <!-- 저장된 경기 정보 불러오기 dialog -->
          <v-dialog v-model="dialogMatchSchedule">
            <v-row fluid justify="center" class="ma-0 pa-0">
              <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
                <v-card>
                  <v-card-title class="headline"
                    >원하시는 경기를 선택해주세요</v-card-title
                  >
                  <v-card-text>
                    <v-row class="text-center">
                      <v-col cols="3">
                        경기일시
                      </v-col>
                      <v-col cols="5">
                        장소
                      </v-col>
                      <v-col cols="2">
                        경기정보
                      </v-col>
                      <v-col cols="1"></v-col>
                      <v-col cols="1" class="ma-0 pa-0">
                        <i
                          id="member-close"
                          class="mt-2 material-icons md-18"
                          style="cursor:pointer"
                          @click="dialogMatchSchedule = false"
                          >close</i
                        >
                      </v-col>
                    </v-row>
                    <v-divider></v-divider>
                    <v-row
                      v-for="(vote, i) in votes"
                      :key="i"
                      class="text-center"
                      style="color:white; vertical-align:middle;"
                    >
                      <v-col cols="3">
                        {{ vote.matchSchedule.startTime }}
                      </v-col>
                      <v-col cols="5">
                        <div>{{ vote.matchSchedule.stadiumAddress }}</div>
                        <div>{{ vote.matchSchedule.stadiumName }}</div>
                      </v-col>
                      <v-col cols="2">
                        <div>{{ vote.matchSchedule.matchType }}</div>
                        <div>{{ vote.matchSchedule.cost }} 만원</div>
                      </v-col>
                      <v-col cols="1">
                        <v-btn
                          class="mt-1"
                          color="#6920A3"
                          @click="selectMatch(vote.matchSchedule)"
                          >선택</v-btn
                        >
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-dialog>
        </v-col>
      </v-row>
      <!-- 매치 생성 본문 -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-divider color="white"></v-divider>
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
                    :disabled="updateDisabled == true"
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
                    type="text"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    :disabled="updateDisabled == true"
                    v-model="targetDuration"
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
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    type="text"
                    placeholder="경기장을 선택해주세요"
                    v-model="targetStadium"
                    :disabled="updateDisabled == true"
                    @click="dialogStadium = true"
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
          <!--경기타입비용-->
          <!-- 경기타입비용 Label -->
          <v-row class="mx-0 px-0">
            <v-col cols="6" class="text-left mx-0 pl-0 pr-1 px-0 pb-2"
              >경기타입</v-col
            >
            <v-col
              cols="6"
              class="text-left mx-0 px-0 pb-2"
              style="padding-left:2px;padding-right:2px;"
              >비용</v-col
            >
          </v-row>
          <!-- 경기타입비용 Input -->
          <v-row class="mx-0 px-0">
            <!-- 경기타입 input -->
            <v-col cols="6" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <select
                  v-model="targetType"
                  :disabled="updateDisabled == true"
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
              cols="6"
              class="py-0"
              style="padding-left:2px;padding-right:2px;"
            >
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <v-col offset="2" cols="1" class="ma-0 pa-0">
                  <input
                    id="cost"
                    type="text"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    :disabled="updateDisabled == true"
                    v-model="targetCost"
                  />
                </v-col>
                <v-col cols="1" class="ma-0 pa-0 text-center">
                  만원
                </v-col>
              </v-row>
            </v-col>
          </v-row>
          <!--경기타입비용 끝-->
          <!-- 경기장타입, 경기장샤워, 경기장주차 -->
          <!-- 경기장타입, 경기장샤워, 경기장주차 Label-->
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
            <v-col cols="4" class="text-left mx-0 pr-0 pl-1 px-0 pb-2"
              >주차여부</v-col
            >
          </v-row>
          <!-- 경기장타입, 경기장샤워, 경기장주차 Input-->
          <v-row class="mx-0 px-0">
            <!-- 경기장 타입 input -->
            <v-col cols="4" class="pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <select
                  v-model="targetStadiumType"
                  :disabled="updateDisabled == true"
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
                  v-model="targetStadiumShower"
                  :disabled="updateDisabled == true"
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
            <!-- 경기장주차 input -->
            <v-col cols="4" class="pa-0 pl-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <select
                  v-model="targetStadiumParking"
                  :disabled="updateDisabled == true"
                  :class="{
                    'before-selected': !parkingSelected,
                    'after-selected': parkingSelected
                  }"
                  @change="parkingSelected = true"
                >
                  <option class="options" disabled selected value="null"
                    >주차여부</option
                  >
                  <option class="options" value="1">주차가능</option>
                  <option class="options" value="0">주차불가</option>
                </select>
              </v-row>
            </v-col>
          </v-row>
          <!-- 경기장타입, 샤워여부, 투표마감일 끝 -->
          <!-- 마감시간, 비용, 필요 용병 수 -->
          <!-- 마감시간, 비용, 필요 용병 수 Label-->
          <v-row class="mx-0 px-0">
            <v-col cols="4" class="text-left mx-0 px-0 pb-2">마감 시간*</v-col>
            <v-col cols="4" class="text-left mx-0 px-0 pb-2"
              >용병 부담 비용*</v-col
            >
            <v-col cols="4" class="text-left mx-0 px-0 pb-2"
              >필요 용병 수*</v-col
            >
          </v-row>
          <!-- 대기가능시간, 최소인원, 마감시간 Input -->
          <v-row class="mx-0 px-0">
            <!-- 마감시간 input -->
            <v-col cols="4" class="text-left pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <input
                  type="datetime-local"
                  style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                  @change="dateFomatter(1, null, $event)"
                />
              </v-row>
            </v-col>
            <!-- 용병 부담 비용 input -->
            <v-col cols="4" class="text-left pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <input
                  type="text"
                  class="mr-1"
                  style="display:inline-block;width:10%;text-align:center;color:#ffffff"
                  v-model="employ.cost"
                />
                만원
              </v-row>
            </v-col>
            <!-- 필요 용병 수 input -->
            <v-col cols="4" class="text-left pa-0 pr-1">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <input
                  type="text"
                  class="mr-1"
                  style="display:inline-block;width:10%;text-align:center;color:#ffffff"
                  v-model="employ.reqNumber"
                />
                명
              </v-row>
            </v-col>
          </v-row>
          <!-- 대기가능시간, 최소인원, 마감시간 끝 -->
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
                  v-model="employ.matchSchedule.homeContent"
                  cols="10000"
                  placeholder="일정에 남기고 싶은 말을 입력해주세요"
                  style="color:#ffffff"
                ></textarea>
              </v-row>
            </v-col>
          </v-row>
          <!--내용 끝 -->
          <!--용병글 내용-->
          <!-- 용병글  내용 Label-->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left mx-0 px-0 pb-2"
              >용병글 내용</v-col
            >
          </v-row>
          <!--용병글 내용 Input-->
          <v-row class="mx-0 px-0">
            <v-col cols="12" class="text-left pa-0">
              <v-row
                class="ma-0 pa-3"
                justify="center"
                style="border:2px solid #AD1457;border-radius:25px;"
              >
                <textarea
                  v-model="employ.content"
                  cols="10000"
                  placeholder="용병글에 남기고 싶은 말을 입력해주세요"
                  style="color:#ffffff"
                ></textarea>
              </v-row>
            </v-col>
          </v-row>
          <!--내용 끝 -->
          <!-- 제출 버튼 -->
          <v-row class="mx-0 px-0 pt-3 justify-center">
            <v-btn elevation="3" @click="addEmploy">용병찾기 등록하기</v-btn>
          </v-row>
          <!-- 제출 버튼 끝 -->
        </v-col>
      </v-row>
      <v-dialog v-model="dialogNaver">
        <naver-link
          :type="'employ'"
          :matchSchedule="employ.matchSchedule"
          :employCost="employ.cost"
          @close="closeNaver"
        ></naver-link>
      </v-dialog>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/employ/EmployInsert.js"></script>
<style scoped src="@/assets/css/common/Insert.css"></style>
