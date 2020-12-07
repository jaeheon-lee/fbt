<template>
  <div>
    <v-container fluid class="ma-0 pa-0">
      <!-- 양도 리스트 -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-divider color="white"></v-divider>
          <v-card
            id="vote-list"
            v-for="(assign, i) in assigns"
            :key="i"
            elevation="2"
            class="ma-0 pa-0 mb-3"
          >
            <v-row class="ma-0 pa-0">
              <v-col cols="12" class="text-left" style="font-size: 0.75em">
                양도 마감일시 : {{ assign.dueDate | extrackSecond }}
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
                  :src="getEmbUrl(assign.matchSchedule.homeTeam)"
                  aspect-ratio="1.2"
                  contain
                />
                <!-- 홈팀 이름 -->
                {{ assign.matchSchedule.homeTeam.teamName }}
              </v-col>
              <!-- 경기 간략 정보 -->
              <v-col id="match-info" cols="4" md="4" class="pt-1 text-center">
                <div id="match-type" class="match-type">
                  <!-- N:N 축구 | 풋살 경기 표시 -->
                  {{ assign.matchSchedule.matchType | showMatchType }}
                </div>
                <div id="match-score"></div>
                <div id="match-info-simple">
                  <!-- 경기 날짜 -->
                  <p>{{ assign.matchSchedule.startTime.split(" ")[0] }}</p>
                  <!-- 경기 시간 -->
                  <p>
                    {{ assign.matchSchedule.startTime | showOnlyTime }} ~
                    {{ assign.matchSchedule.endTime | showOnlyTime }}
                    {{ assign.matchSchedule.duration }}시간
                  </p>
                  <!-- 경기장 정보 -->
                  <p>{{ assign.matchSchedule.stadiumName }}</p>
                </div>
              </v-col>
              <!-- 어웨이 엠블럼 -->
              <v-col cols="2" class="pt-1 text-center" style="font-size: 1.5em">
                <v-img
                  class="mb-5"
                  :src="getEmbUrl(assign.matchSchedule.awayTeam)"
                  aspect-ratio="1.2"
                  contain
                />
                <!-- 어웨이 팀이름 -->
                {{ getAwayTeamName(assign.matchSchedule) }}
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
            <v-row>
              <v-col offset="2" cols="8">
                <v-expand-transition>
                  <div id="match-info-detail" v-if="activeDetail == i">
                    <!--경기타입비용주차-->
                    <!-- 경기타입,비용,주차  라벨-->
                    <v-row class="mx-0 px-0">
                      <v-col
                        cols="4"
                        class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                        >경기타입</v-col
                      >
                      <v-col
                        cols="4"
                        class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                        style="padding-left:2px;padding-right:2px;"
                        >비용</v-col
                      >
                      <v-col
                        cols="4"
                        class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                        >주차</v-col
                      >
                    </v-row>
                    <v-row class="mx-0 px-0">
                      <!-- 경기타입, 비용, 주차 본문 -->
                      <!-- 경기 타입 -->
                      <v-col cols="4" class="pa-0 pr-1"
                        ><v-row
                          class="ma-0 pa-3"
                          justify="center"
                          style="border:2px solid #AD1457;border-radius:25px;"
                          >{{
                            assign.matchSchedule.matchType | showMatchType
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
                          >{{ assign.matchSchedule.cost }}만원</v-row
                        ></v-col
                      >
                      <!-- 경기장 주차여부 -->
                      <v-col cols="4" class="pa-0 pl-1"
                        ><v-row
                          class="ma-0 pa-3"
                          justify="center"
                          style="border:2px solid #AD1457;border-radius:25px;"
                          >{{
                            assign.matchSchedule.stadiumParking | showParking
                          }}</v-row
                        ></v-col
                      >
                    </v-row>
                    <!--경기타입비용주차 끝-->
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
                          >{{ assign.content | content }}</v-row
                        ></v-col
                      >
                    </v-row>
                    <!--내용 끝 -->
                  </div>
                </v-expand-transition>
              </v-col>
            </v-row>
            <!-- 경기 상세정보 끝 -->
            <!-- 신청 팀리스트 -->
            <v-row
              style="color:rgba(235, 255, 0,0.7)"
              v-if="registeredStage == 2"
            >
              <v-col offset="2" cols="10" class="mt-0">
                <span
                  id="match-info-detail-btn"
                  @click="openTeamList(i)"
                  style="cursor:pointer;"
                  >신청 팀리스트 보기</span
                >
              </v-col>
            </v-row>
            <!-- 팀리스트 본문 -->
            <v-row v-if="registeredStage == 2">
              <v-col offset="2" cols="8">
                <v-expand-transition>
                  <div id="match-info-detail" v-if="activeTeamList == i">
                    <!--팀 리스트 라벨-->
                    <v-row class="mx-0 px-0 text-center">
                      <v-col cols="3" class="mx-0 pl-2 pr-1 px-0 pb-2"
                        >팀명</v-col
                      >
                      <v-col cols="2" class="mx-0 pl-2 pr-1 px-0 pb-2"
                        >활동지역</v-col
                      >
                      <v-col cols="2" class="mx-0 pl-2 pr-1 px-0 pb-2"
                        >실력</v-col
                      >
                      <v-col cols="2" class="mx-0 pl-2 pr-1 px-0 pb-2"
                        >경기매너점수</v-col
                      >
                      <v-col cols="3" class="mx-0 pl-2 pr-1 px-0 pb-2"
                        >양도</v-col
                      >
                    </v-row>
                    <!-- 팀 리스트 라벨 끝 -->
                    <v-divider color="white"></v-divider>
                    <!-- 팀리스트 본문 -->
                    <v-row
                      class="mx-0 px-0 text-center"
                      v-for="(res, j) in assign.assignmentReservations"
                      :key="j"
                    >
                      <!-- 팀명 -->
                      <v-col cols="3">{{ res.teamTaker.teamName }}</v-col>
                      <!-- 활동지역 -->
                      <v-col cols="2">{{ res.teamTaker.area }}</v-col>
                      <!-- 실력 -->
                      <v-col cols="2">
                        <!-- 여기에 실력 머스타치 넣기 -->
                      </v-col>
                      <!-- 경기매너점수 -->
                      <v-col cols="2">
                        <!-- 여기에 경기매너점수 머스타치 넣기 -->
                      </v-col>
                      <!-- 인원파악신청|거절 버튼 -->
                      <!-- 신청만 했다면 -->
                      <v-col cols="3" v-if="registeredStage == 2 && res.reservationStatus == 0">
                        <v-row class="justify-center">
                          <v-btn
                            class="ma-0 pa-0 mr-2 justify-center"
                            elevation="2"
                            small
                            color="#6920A3"
                            @click="acceptApply(res, assign)"
                            >수락</v-btn
                          >
                          <v-btn
                            class="ma-0 pa-0 justify-center"
                            elevation="2"
                            small
                            color="#AD1457"
                            @click="refuseApply(res, assign)"
                            >거절</v-btn
                          >
                        </v-row>
                      </v-col>
                      <!-- 거절됐다면 -->
                      <v-col cols="3" v-if="registeredStage == 2 && res.reservationStatus == -1">
                        <v-row class="justify-center">
                          거절됨
                        </v-row>
                      </v-col>
                    </v-row>
                    <!-- 팀리스트 본문  끝-->
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
                          >{{ assign.content | content }}</v-row
                        ></v-col
                      >
                    </v-row>
                    <!--내용 끝 -->
                  </div>
                </v-expand-transition>
              </v-col>
            </v-row>
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
                @click="doApply(assign)"
                >양도 신청</v-btn
              >
            </v-row>
            <!-- 매치 등록 시 -->
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
                @click="deleteAssign(assign)"
                v-if="registeredStage == 1"
                >양도글삭제</v-btn
              >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                @click="renewAssign(assign)"
                v-if="registeredStage == 1"
                >양도글 끌어올리기</v-btn
              >
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                v-if="registeredStage > 2"
                @click="deleteAssign(assign)"
                >목록 삭제</v-btn
              >
            </v-row>
            <!-- 신청 매치  -->
            <v-row class="ma-0 pa-0 pb-3 justify-center">
              <!-- 인원파악신청 버튼: 3단계까지 -->
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#AD1457"
                v-if="appliedStage == 1 && appliedStage !=null"
                @click="deleteAssignRes(assign)"
                >양도신청 취소</v-btn
              >
              <!-- 투표 만들기 버튼: 2단계에서 -->
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                v-if="appliedStage == 2"
                @click="deleteAssignRes(assign)"
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
<script scoped src="@/assets/js/assign/AssignList.js"></script>
