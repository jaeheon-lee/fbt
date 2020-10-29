<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-row
            justify="center"
            fluid
            align="center"
            class="py-0 my-0 px-0 mx-0"
          >
            <v-col cols="4" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                color="#AD1457"
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                >홈 투표중</v-btn
              >
            </v-col>
            <v-col cols="4" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                style="border:0px;"
                class="mx-0 px-0"
                >어웨이 투표중</v-btn
              >
            </v-col>
            <v-col cols="4" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                style="border:0px;"
                >투표완료</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>

    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>홈팀 진행중 투표</span>
        </v-col>
      </v-row>
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-expansion-panels focusable hover color="red">
            <!-- 각 투표 리스트 출력 부분 -->
            <v-expansion-panel v-for="(vote, i) in votes" :key="i">
              <v-expansion-panel-header
                hide-actions
                class="ma-0 pa-0"
                style="min-height:12px!important;background-color:#121212;font-size: 0.65rem;line-height:100%;color:rgba(255, 255, 255, 0.7)"
              >
                <v-row class="ma-0 pa-0">
                  <!-- 경기 날짜 -->
                  <v-col cols="12">
                    {{ vote.matchSchedule.startTime.split(" ")[0] }}
                  </v-col>
                  <!-- 홈팀 이름 -->
                  <v-col
                    offset="1"
                    cols="2"
                    class="pt-6 text-center"
                    style="font-size: 0.8rem"
                  >
                    {{ vote.matchSchedule.homeTeam.teamName }}
                  </v-col>
                  <!-- 홈팀 엠블럼 -->
                  <v-col cols="2">
                    <v-img
                      :src="getEmbUrl(vote.matchSchedule.homeTeam)"
                      aspect-ratio="2"
                      contain
                    />
                  </v-col>
                  <!-- VS -->
                  <v-col
                    cols="2"
                    class="pt-6 text-center"
                    style="font-size:1rem"
                  >
                    vs
                  </v-col>
                  <!-- 어웨이 엠블럼 -->
                  <v-col cols="2">
                    <v-img
                      :src="getEmbUrl(vote.matchSchedule.awayTeam)"
                      aspect-ratio="2"
                      contain
                    />
                  </v-col>
                  <!-- 어웨이 팀이름 -->
                  <v-col
                    cols="2"
                    class="pt-6 text-center"
                    style="font-size: 0.8rem"
                  >
                    {{ vote.matchSchedule.awayTeam.teamName }}
                  </v-col>
                </v-row>
              </v-expansion-panel-header>
              <!-- 토글 부분 -->
              <v-expansion-panel-content
                style="background-color:#121212;font-size: 0.75rem;line-height:100%;color:rgba(255, 255, 255, 0.9)"
              >
                <v-row class="ma-0 pa-0">
                  <v-col cols="4"></v-col>
                  <v-col cols="8" class="text-right mx-0 px-0"
                    >투표 마감일시 : {{ vote.dueDate }}</v-col
                  >
                </v-row>
                <v-divider></v-divider>
                <!--경기 일시-->
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left mx-0 px-0 pb-2"
                    >경기 일시</v-col
                  >
                </v-row>
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left pa-0">
                    <v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                    >
                      {{ vote.matchSchedule.startTime }}  ~ {{ vote.matchSchedule.endTime | showOnlyTime }}
                      {{ vote.matchSchedule.duration }}시간
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
                  <v-col cols="12" class="text-left pa-0"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >{{ vote.matchSchedule.stadiumAddress }}</v-row
                    ></v-col
                  >
                </v-row>
                <!--경기장 정보 끝 -->
                <!--경기타입비용주차-->
                <v-row class="mx-0 px-0">
                  <v-col cols="4" class="textc-left mx-0 pl-0 pr-1 px-0 pb-2"
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
                  <!-- 경기 타입 -->
                  <v-col cols="4" class="pa-0 pr-1"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >{{ vote.matchSchedule.matchType | showMatchType }}</v-row
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
                      >{{ vote.matchSchedule.cost }}만원</v-row
                    ></v-col
                  >
                  <!-- 경기장 주차여부 -->
                  <v-col cols="4" class="pa-0 pl-1"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >{{ vote.matchSchedule.stadiumParking | showParking }}</v-row
                    ></v-col
                  >
                </v-row>
                <!--경기타입비용주차 끝-->
                <!--내용-->
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left mx-0 px-0 pb-2">내용</v-col>
                </v-row>
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left pa-0"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >{{ vote.contents }}</v-row
                    ></v-col
                  >
                </v-row>
                <!--내용 끝 -->
                <!--투표내용-->
                <v-row class="justify-space-between ma-0 pa-0 mt-4">
                  <div>총인원: {{ vote.totalNum }}명</div>
                  <div>투표인원: {{ vote.votedNum }}명</div>
                  <div>불참: {{ vote.abscentNum }}명</div>
                  <div>참여: {{ vote.attendNum }}명</div>
                </v-row>
                <v-row justify="end" class="ma-0 pa-0 my-3">
                  <div>지인: {{ vote.friendNum }}명</div>
                </v-row>
                <v-divider></v-divider>
                <v-row justify="end" class="ma-0 pa-0 my-3">
                  <div>경기참석 인원: {{ vote.totalAttend }}명</div>
                </v-row>
                <!--투표내용 끝-->
                <!--투표버튼-->
                <v-row class="justify-center ma-0 pa-0 mt-4">
                  <v-btn
                    small
                    width="35px"
                    color="#AD1457"
                    class="mr-1"
                    @click="addVote(vote.voteMatchId, 1)"
                    >참 석</v-btn
                  >
                  <v-btn
                    small
                    width="35px"
                    color="rgba(235, 255, 0,0.7)"
                    class="ml-1"
                    >불참석</v-btn
                  >
                </v-row>
                <!--투표버튼 끝-->
                <v-row class="justify-end mt-5">
                  <div>지인 참석이 가능한 경기입니다.</div>
                </v-row>
                <v-row class="justify-end mt-1">
                  <div style="color:rgba(235, 255, 0,0.7)">지인 초청하기</div>
                </v-row>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<style scroped src="@/assets/css/vote-match/VoteMatch.css"></style>
<script scoped src="@/assets/js/vote-match/VoteMatch.js"></script>
