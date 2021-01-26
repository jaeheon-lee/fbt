<template>
  <div>
    <v-container fluid class="ma-0 pa-0">
      <!-- 용병 찾기 리스트 -->
      <!-- empty -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5" v-if="empty">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-divider color="white" ></v-divider>
          <empty :ment="'양도가'"></empty>
        </v-col>
      </v-row>
      <!-- 양도 리스트 -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5" v-else>
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-divider color="white"></v-divider>
          <v-card
            id="vote-list"
            v-for="(employ, i) in employs"
            :key="i"
            elevation="2"
            class="ma-0 pa-0 mb-3"
          >
            <v-row class="ma-0 pa-0">
              <v-col cols="12" class="text-left" style="font-size: 0.75em">
                용병찾기 마감일시 : {{ employ.dueDate | extrackSecond }}
              </v-col>
            </v-row>
            <v-row>
              <!-- 홈팀 엠블럼 -->
              <v-col
                offset="2"
                cols="2"
                class="pt-1 text-center"
                style="font-size: 1.5em"
              >
                <v-img
                  class="mb-5"
                  :src="getEmbUrl(employ.matchSchedule.homeTeam)"
                  aspect-ratio="1.2"
                  contain
                />
                <!-- 홈팀 이름 -->
                <v-icon v-if="controlStar(employ) == true">mdi-star</v-icon>
                <span>{{ employ.matchSchedule.homeTeam.teamName }}</span>
              </v-col>
              <!-- 경기 간략 정보 -->
              <v-col id="match-info" cols="4" md="4" class="pt-1 text-center">
                <v-row fluid justify="center">
                  <!-- N:N 축구 | 풋살 경기 표시 -->
                  {{ employ.matchSchedule.matchType | showMatchType }}
                </v-row>
                <v-row fluid justify="center"></v-row>
                <v-row fluid justify="center">
                  <!-- 경기 날짜 -->
                  {{ employ.matchSchedule.startTime.split(" ")[0] }}
                </v-row>
                <v-row fluid justify="center">
                  <!-- 경기 시간 -->
                  {{ employ.matchSchedule.startTime | showOnlyTime }} ~
                  {{ employ.matchSchedule.endTime | showOnlyTime }}
                  ({{ employ.matchSchedule.duration }}시간)
                </v-row>
                <v-row fluid justify="center">
                  <!-- 경기장 정보 -->
                  {{ employ.matchSchedule.stadiumName }}
                </v-row>
                <v-row fluid justify="center" class="mt-3">
                  <!-- 용병 찾기 현황 정보 -->
                  모집인원: {{ employ.reqNumber }}명 / 현재인원
                  {{ employ.currentNum }} 명
                </v-row>
              </v-col>
              <!-- 어웨이 엠블럼 -->
              <v-col cols="2" class="pt-1 text-center" style="font-size: 1.5em">
                <v-img
                  class="mb-5"
                  :src="getEmbUrl(employ.matchSchedule.awayTeam)"
                  aspect-ratio="1.2"
                  contain
                />
                <!-- 어웨이 팀이름 -->
                <v-icon v-if="controlStar(employ) == false">mdi-star</v-icon>
                <span>{{ getAwayTeamName(employ.matchSchedule) }}</span>
              </v-col>
            </v-row>
            <!-- 경기 상세정보 -->
            <v-row style="color:rgba(235, 255, 0,0.7)">
              <v-col offset="2" cols="10" class="mt-3">
                <span
                  id="match-info-detail-btn"
                  @click="openDetail(i)"
                  style="cursor:pointer;"
                  >상세내용 보기</span
                >
              </v-col>
            </v-row>
            <!-- 상세정보: 목표인원, 신청인원 -->
            <v-expand-transition>
              <v-row v-if="activeDetail == i">
                <v-col offset="2" cols="8">
                  <!--경기타입비용주차-->
                  <!-- 경기타입,비용,주차  라벨-->
                  <v-row class="mx-0 px-0">
                    <v-col cols="4" class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      >경기타입</v-col
                    >
                    <v-col
                      cols="4"
                      class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      style="padding-left:2px;padding-right:2px;"
                      >비용</v-col
                    >
                    <v-col cols="4" class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      >용병 부담 비용</v-col
                    >
                  </v-row>
                  <!-- 경기타입, 비용, 주차 본문 -->
                  <v-row class="mx-0 px-0">
                    <!-- 경기 타입 -->
                    <v-col cols="4" class="pa-0 pr-1"
                      ><v-row
                        class="ma-0 pa-3"
                        justify="center"
                        style="border:2px solid #AD1457;border-radius:25px;"
                        >{{
                          employ.matchSchedule.matchType | showMatchType
                        }}</v-row
                      ></v-col
                    >
                    <!-- 경기 비용 -->
                    <v-col
                      cols="4"
                      class="py-0"
                      style="padding-left:2px;padding-right:2px;"
                      ><v-row
                        class="ma-0 pa-3"
                        justify="center"
                        style="border:2px solid #AD1457;border-radius:25px;"
                        >{{ employ.matchSchedule.cost }} 만원</v-row
                      ></v-col
                    >
                    <!-- 용밤 부담 비용-->
                    <v-col cols="4" class="pa-0 pl-1"
                      ><v-row
                        class="ma-0 pa-3"
                        justify="center"
                        style="border:2px solid #AD1457;border-radius:25px;"
                        >{{ employ.cost }} 만원</v-row
                      ></v-col
                    >
                  </v-row>
                  <!--경기타입비용주차 끝-->
                  <!-- 경기장 타입, 경기장 샤워여부, 경기장 주차여부 -->
                  <!-- 경기장 타입,샤워,주차  라벨-->
                  <v-row class="mx-0 px-0">
                    <v-col cols="4" class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      >경기장 타입</v-col
                    >
                    <v-col
                      cols="4"
                      class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      style="padding-left:2px;padding-right:2px;"
                      >샤워여부</v-col
                    >
                    <v-col cols="4" class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      >주차여부</v-col
                    >
                  </v-row>
                  <v-row class="mx-0 px-0">
                    <!-- 경기장 타입 -->
                    <v-col cols="4" class="pa-0 pl-1"
                      ><v-row
                        class="ma-0 pa-3"
                        justify="center"
                        style="border:2px solid #AD1457;border-radius:25px;"
                        >{{ employ.matchSchedule.stadiumType }}</v-row
                      ></v-col
                    >
                    <!-- 경기장 샤워여부 -->
                    <v-col cols="4" class="pa-0 pl-1"
                      ><v-row
                        class="ma-0 pa-3"
                        justify="center"
                        style="border:2px solid #AD1457;border-radius:25px;"
                        >{{
                          employ.matchSchedule.stadiumShower | showShower
                        }}</v-row
                      ></v-col
                    >
                    <!-- 경기장 주차여부 -->
                    <v-col cols="4" class="pa-0 pl-1"
                      ><v-row
                        class="ma-0 pa-3"
                        justify="center"
                        style="border:2px solid #AD1457;border-radius:25px;"
                        >{{
                          employ.matchSchedule.stadiumParking | showParking
                        }}</v-row
                      ></v-col
                    >
                  </v-row>
                  <!-- 경기장 타입, 경기장 샤워여부, 경기장 주차여부  끝-->
                  <!--내용-->
                  <!-- 내용 라벨 -->
                  <v-row class="mx-0 px-0">
                    <v-col cols="12" class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      >내용</v-col
                    >
                  </v-row>
                  <!-- 내용 본문 -->
                  <v-row class="mx-0 px-0">
                    <v-col cols="12" class="text-left pa-0"
                      ><v-row
                        class="ma-0 pa-3"
                        justify="center"
                        style="border:2px solid #AD1457;border-radius:25px;"
                        >{{ employ.content | content }}</v-row
                      ></v-col
                    >
                  </v-row>
                  <!--내용 끝 -->
                </v-col>
              </v-row>
            </v-expand-transition>
            <!-- 경기 상세정보 끝 -->
            <div v-if="registeredStage != null">
              <!-- 신청 리스트 -->
              <v-row style="color:rgba(235, 255, 0,0.7)">
                <v-col offset="2" cols="10" class="mt-0">
                  <span
                    id="match-info-detail-btn"
                    @click="openTeamList(i)"
                    style="cursor:pointer;"
                    >신청 용병 보기</span
                  >
                </v-col>
              </v-row>
              <!-- 신청 리스트 본문 -->
              <v-row>
                <v-col offset="2" cols="8">
                  <v-expand-transition>
                    <div id="match-info-detail" v-if="activeTeamList == i">
                      <!-- 신청 요약 -->
                      <v-row
                        fluid
                        justify="center"
                        class="mx-0 px-0 text-center"
                      >
                        <span class="mr-2"
                          >모집인원: {{ employ.reqNumber }}명</span
                        >
                        <span class="mr-2"
                          >신청인원: {{ employ.currentNum }}명</span
                        >
                        <span class="mr-2"
                          >수락인원: {{ employ.acceptNum }}명</span
                        >
                        <span class="mr-2"
                          >거절인원: {{ employ.refuseNum }}명</span
                        >
                      </v-row>
                      <!--팀 리스트 라벨-->
                      <v-row class="mx-0 px-0 text-center">
                        <v-col cols="1"></v-col>
                        <v-col cols="3" class="mx-0 pl-2 pr-1 px-0 pb-2"
                          >이메일</v-col
                        >
                        <v-col cols="3" class="mx-0 pl-2 pr-1 px-0 pb-2"
                          >활동지역</v-col
                        >
                        <v-col cols="1" class="mx-0 pl-2 pr-1 px-0 pb-2"
                          >실력</v-col
                        >
                        <v-col cols="1" class="mx-0 pl-2 pr-1 px-0 pb-2"
                          >매너</v-col
                        >
                        <v-col cols="3" class="mx-0 pl-2 pr-1 px-0 pb-2"
                          >조치</v-col
                        >
                      </v-row>
                      <!-- 팀 리스트 라벨 끝 -->
                      <v-divider color="white"></v-divider>
                      <!-- 신청 회원리스트 본문 -->
                      <div v-for="(res, j) in employ.employResults" :key="j">
                        <v-row
                          class="mx-0 px-0 text-center"
                          @click="controlUserInfoToggle(j)"
                          style="cursor:pointer;"
                        >
                          <!-- 팀명 -->
                          <!-- 아이콘 -->
                          <v-col cols="1">
                            <v-icon v-if="activeUserInfo == j">
                              mdi-chevron-down
                            </v-icon>
                            <v-icon v-else>
                              mdi-chevron-right
                            </v-icon>
                          </v-col>
                          <v-col
                            cols="3"
                            style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"
                            >{{ res.user.email }}</v-col
                          >
                          <!-- 활동지역 -->
                          <v-col cols="3">{{ res.user.area }}</v-col>
                          <!-- 실력 -->
                          <v-col cols="1" class="px-0">
                            {{ res.user.empAbility | showTeamScore }}
                          </v-col>
                          <!-- 경기매너점수 -->
                          <v-col cols="1" class="px-0">
                            {{ res.user.empManner | showTeamScore }}
                          </v-col>
                          <!-- 인원파악신청|거절 버튼 -->
                          <!-- 신청만 했다면 -->
                          <v-col
                            cols="3"
                            v-if="
                              registeredStage == 1 && res.empResultStatus == 0
                            "
                          >
                            <v-row class="justify-center">
                              <v-btn
                                class="ma-0 pa-0 mr-2 justify-center"
                                elevation="2"
                                small
                                color="#6920A3"
                                @click.stop="modifyApply(res, employ, 0)"
                                >수락</v-btn
                              >
                              <v-btn
                                class="ma-0 pa-0 justify-center"
                                elevation="2"
                                small
                                color="#AD1457"
                                @click.stop="modifyApply(res, employ, 1)"
                                >거절</v-btn
                              >
                            </v-row>
                          </v-col>
                          <!-- 수락했다면 -->
                          <v-col cols="3" v-if="res.empResultStatus > 0">
                            <v-row class="justify-center">
                              수락됨
                            </v-row>
                          </v-col>
                          <!-- 거절했다면 -->
                          <v-col cols="3" v-if="res.empResultStatus == -1">
                            <v-row class="justify-center">
                              거절됨
                            </v-row>
                          </v-col>
                        </v-row>
                        <!-- 신청 회원리스트 본문  끝-->
                        <!-- 회원 상세보기 창 -->
                        <v-row>
                          <v-expand-transition>
                            <user-info
                              class="my-5"
                              :email="res.user.email"
                              v-if="activeUserInfo == j"
                            ></user-info>
                          </v-expand-transition>
                        </v-row>
                      </div>
                      <!--팀리스트 끝-->
                      <!--내용-->
                      <!-- 내용 라벨 -->
                      <v-row class="mx-0 px-0">
                        <v-col
                          cols="12"
                          class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                          >내용</v-col
                        >
                      </v-row>
                      <!-- 내용 본문 -->
                      <v-row class="mx-0 px-0">
                        <v-col cols="12" class="text-left pa-0"
                          ><v-row
                            class="ma-0 pa-3"
                            justify="center"
                            style="border:2px solid #AD1457;border-radius:25px;"
                            >{{ employ.content | content }}</v-row
                          ></v-col
                        >
                      </v-row>
                      <!--내용 끝 -->
                    </div>
                  </v-expand-transition>
                </v-col>
              </v-row>
            </div>
            <!-- 신청 팀리스트 끝 -->
            <!-- 관리자 버튼 영역 -->
            <!-- 매치 검색 시 -->
            <v-row
              class="ma-0 pa-0 pb-3 justify-center"
              v-if="registeredStage == null && appliedStage == null"
            >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                @click="doApply(employ)"
                v-if="whichBtnActive[i] == 0"
                >용병 신청</v-btn
              >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                disabled
                v-else-if="whichBtnActive[i] == 2"
                >마감된 글</v-btn
              >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                disabled
                v-else
                >이미 신청한 항목</v-btn
              >
            </v-row>
            <!-- 등록 용병 창 -->
            <v-row
              class="ma-0 pa-0 pb-3 justify-center"
              v-if="registeredStage != null"
            >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                @click="deleteEmploy(employ)"
                v-if="registeredStage == 1"
                >용병찾기글삭제</v-btn
              >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                @click="renewEmploy(employ)"
                v-if="registeredStage == 1"
                >용병찾기글 끌어올리기</v-btn
              >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                @click="updateEmploy(employ)"
                v-if="registeredStage < 3"
                >수정하기</v-btn
              >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                v-if="registeredStage > 1"
                @click="deleteEmploy(employ)"
                >목록 삭제</v-btn
              >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                v-if="registeredStage == 3"
                @click="completeEmploy(employ)"
                >용병 확정하기</v-btn
              >
            </v-row>
            <!-- 신청 용병 창 -->
            <v-row class="ma-0 pa-0 pb-3 justify-center">
              <!-- 인원파악신청 버튼: 3단계까지 -->
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#AD1457"
                v-if="appliedStage == 1 && appliedStage != null"
                @click="deleteEmployRes(employ)"
                >용병신청 취소</v-btn
              >
              <!-- 투표 만들기 버튼: 2단계에서 -->
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                v-if="appliedStage >= 2"
                @click="deleteEmployRes(employ)"
                >목록 삭제</v-btn
              >
            </v-row>
            <!-- 관리자 버튼 영역 끝 -->
          </v-card>
        </v-col>
      </v-row>
      <!-- 양도 리스트 본문 끝-->
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/employ/EmployList.js"></script>
