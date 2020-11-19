<template>
  <div>
    <v-container fluid class="ma-0 pa-0">
      <!-- 등록 매치 현황 Label -->
      <v-row fluid justify="center" class="ma-0 pa-0 mx-5 my-4">
        여러 인원파악신청에 대해서 수락하시면, 투표를 통해 최소인원 이상 참석이
        가능한 팀이 선착순으로 매치가 완료됩니다.
      </v-row>
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>등록 매치 현황</span>
        </v-col>
      </v-row>
      <!-- 등록 매치 현황 Label 끝-->
      <!-- 매치 생성 본문 -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-divider color="white"></v-divider>
          <v-card
            id="vote-list"
            v-for="(search, i) in searches"
            :key="i"
            elevation="2"
            class="ma-0 pa-0 mb-3"
          >
            <v-row class="ma-0 pa-0">
              <v-col cols="12" class="text-left" style="font-size: 0.75em">
                매칭 마감일시 : {{ search.dueDate | extrackSecond }}
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
                  :src="getEmbUrl(search.teamGiver)"
                  aspect-ratio="1.2"
                  contain
                />
                <!-- 홈팀 이름 -->
                {{ search.teamGiver.teamName }}
              </v-col>
              <!-- 경기 간략 정보 -->
              <v-col id="match-info" cols="4" md="4" class="pt-1 text-center">
                <div id="match-type" class="match-type">
                  <!-- N:N 축구 | 풋살 경기 표시 -->
                  {{ search.matchSchedule.matchType | showMatchType }}
                </div>
                <div id="match-score"></div>
                <div id="match-info-simple">
                  <!-- 경기 날짜 -->
                  <p>{{ search.matchSchedule.startTime.split(" ")[0] }}</p>
                  <!-- 경기 시간 -->
                  <p>
                    {{ search.matchSchedule.startTime | showOnlyTime }} ~
                    {{ search.matchSchedule.endTime | showOnlyTime }}
                    {{ search.matchSchedule.duration }}시간
                  </p>
                  <!-- 경기장 정보 -->
                  <p>{{ search.matchSchedule.stadiumName }}</p>
                </div>
              </v-col>
              <!-- 어웨이 엠블럼 -->
              <v-col cols="2" class="pt-1 text-center" style="font-size: 1.5em">
                <v-img
                  class="mb-5"
                  :src="getEmbUrl()"
                  aspect-ratio="1.2"
                  contain
                />
                <!-- 어웨이 팀이름 -->
                미정
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
                            search.matchSchedule.matchType | showMatchType
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
                          >{{ search.matchSchedule.cost }}만원</v-row
                        ></v-col
                      >
                      <!-- 경기장 주차여부 -->
                      <v-col cols="4" class="pa-0 pl-1"
                        ><v-row
                          class="ma-0 pa-3"
                          justify="center"
                          style="border:2px solid #AD1457;border-radius:25px;"
                          >{{
                            search.matchSchedule.stadiumParking | showParking
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
                          >{{ search.content | content }}</v-row
                        ></v-col
                      >
                    </v-row>
                    <!--내용 끝 -->
                  </div>
                </v-expand-transition>
              </v-col>
            </v-row>
            <!-- 경기 상세정보 끝 -->
            <!-- 관리자 버튼 영역 -->
            <v-row class="ma-0 pa-0 pb-3 justify-center">
              <v-btn
                class="mr-7"
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                @click="deleteSearch(search)"
                >매치글삭제</v-btn
              >
              <v-btn
                elevation="3"
                width="20%"
                small
                color="#6920A3"
                @click="renewSearch(search)"
                >매치글 끌어올리기</v-btn
              >
            </v-row>
            <!-- 관리자 버튼 영역 끝 -->
          </v-card>
        </v-col>
      </v-row>
      <!-- 매치 생성 본문 끝-->
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/search/RegisteredSearch.js"></script>
