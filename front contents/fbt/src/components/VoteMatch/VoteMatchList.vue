<template>
  <div>
    <!-- 각 투표 리스트 출력 부분 -->
    <v-card
      class="my-3 pb-5"
      id="vote-list"
      v-for="(vote, i) in votes2"
      :key="i"
      elevation="2"
    >
      <!-- 마감일시 -->
      <v-row class="ma-0 pa-0">
        <v-col cols="12" class="text-left" style="font-size: 0.75em">
          <span
            class="mr-2"
            v-if="vote.voteStatus == 0 && vote.matchSchedule.isConfirmed == 0"
            >투표 마감일시 : {{ vote.dueDate | extractSecond }}</span
          >
          <span
            v-if="vote.voteStatus == 1 && vote.matchSchedule.isConfirmed == 0"
          >
            (마감됨)
          </span>
          <span
            v-if="vote.voteStatus == 1 && vote.matchSchedule.isConfirmed == 1"
          >
            (확정됨)
          </span>
        </v-col>
      </v-row>
      <!-- 마감일시 끝 -->
      <!-- 기본 정보 -->
      <v-row>
        <!-- 홈팀 엠블럼 -->
        <v-col
          offset="2"
          cols="2"
          class="pt-1 text-center justify-center"
          style="font-size: 1.5em"
        >
          <v-img
            class="mb-5 justify-center"
            :src="getEmbUrl(vote.matchSchedule.homeTeam)"
            aspect-ratio="1.2"
            contain
          />
          <!-- 홈팀 이름 -->
          <span>
            {{ vote.matchSchedule.homeTeam.teamName }}
          </span>
        </v-col>
        <!-- 경기 간략 정보 -->
        <v-col id="match-info" cols="4" md="4" class="pt-0 text-center">
          <div id="match-type" class="match-type" style="color:#3FDC9D;">
            <!-- N:N 축구 | 풋살 경기 표시 -->
            {{ vote.matchSchedule.matchType | showMatchType }}
            <p />
            <!-- 팀 | 용병경기 표시 -->
            {{ vote.voteMatchSetting | isEmp }}
          </div>
          <!-- 스코어 -->
          <div class="my-1" style="font-size: 1.5em" v-if="controlScore(vote)">
            <span>{{ vote.matchSchedule.matchResult.homeScore }}</span>
            <span class="mx-5">:</span>
            <span>{{ vote.matchSchedule.matchResult.awayScore }}</span>
          </div>
          <div id="match-info-simple" style="color:#A0A2A3">
            <!-- 경기 날짜 -->
            <v-row justify="center">{{
              vote.matchSchedule.startTime.split(" ")[0]
            }}</v-row>
            <!-- 경기 시간 -->
            <v-row justify="center">
              {{ vote.matchSchedule.startTime | showOnlyTime }} ~
              {{ vote.matchSchedule.endTime | showOnlyTime }}
              {{ vote.matchSchedule.duration }}시간
            </v-row>
            <!-- 경기장 정보 -->
            <v-row justify="center">{{ vote.matchSchedule.stadiumName }}</v-row>
          </div>
        </v-col>
        <!-- 어웨이 엠블럼 -->
        <v-col cols="2" class="pt-1 text-center" style="font-size: 1.5em">
          <v-img
            class="ml-2 mb-5"
            :src="getEmbUrl(vote.matchSchedule.awayTeam)"
            aspect-ratio="1.2"
            contain
          />
          <!-- 어웨이 팀이름 -->
          <span class="ml-2">
            {{ vote.matchSchedule.awayTeam | showAwayTeamName }}
          </span>
        </v-col>
      </v-row>
      <!-- 기본 정보 끝 -->
      <!-- 경기상세 정보 -->
      <div class="ma-0 pa-0">
        <!-- 경기 상세정보 라벨-->
        <v-row style="color:rgba(235, 255, 0,0.7)">
          <v-col offset="2" cols="10" class="mt-3 py-0">
            <span class="mx-3" @click="openDetail(i)" style="cursor:pointer;"
              >상세내용 보기</span
            >
          </v-col>
        </v-row>
        <!-- 경기 상세정보 라벨 끝-->
        <!-- 경기 상세정보 본문-->
        <v-expand-transition>
          <v-row v-if="activeDetail == i" class="mx-3 mb-2">
            <v-col offset="2" cols="8" class="my-0 py-0">
              <v-expand-transition>
                <div id="match-info-detail">
                  <!--경기장타입비용주차-->
                  <!-- 경기장타입,비용,주차  라벨-->
                  <v-row class="mx-0 px-0">
                    <v-col cols="4" class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      >경기장타입</v-col
                    >
                    <v-col
                      cols="4"
                      class="textc-left mx-0 pl-2 px-0 pb-2"
                      style="padding-left:2px;padding-right:2px;"
                      >비용</v-col
                    >
                    <v-col cols="4" class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      >주차</v-col
                    >
                  </v-row>
                  <v-row class="mx-0 px-0">
                    <!-- 경기장타입, 비용, 주차 본문 -->
                    <!-- 경기장 타입 -->
                    <v-col cols="4" class="pa-0 pr-1"
                      ><v-row
                        class="ma-0 pa-3"
                        justify="center"
                        style="border:2px solid #AD1457;border-radius:25px;"
                        >{{ vote.matchSchedule.stadiumType }}</v-row
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
                        >{{
                          vote.matchSchedule.stadiumParking | showParking
                        }}</v-row
                      ></v-col
                    >
                  </v-row>
                  <!--경기타입비용주차 끝-->
                  <!-- 투표 내용 - 투표페이지-->
                  <div v-if="header.includes('voteMatch')">
                    <!-- 투표 내용 라벨 -->
                    <v-row class="mx-0 px-0">
                      <v-col cols="12" class="textc-left mx-0 px-2 pb-2"
                        >투표 메모</v-col
                      >
                    </v-row>
                    <!-- 투표 내용 라벨 끝 -->
                    <!-- 투표 내용 본문 -->
                    <v-row class="mx-0 px-0">
                      <v-col cols="12" class="text-left pa-0"
                        ><v-row
                          class="ma-0 pa-3"
                          justify="center"
                          style="border:2px solid #AD1457;border-radius:25px;"
                          >{{ vote.memo }}</v-row
                        ></v-col
                      >
                    </v-row>
                    <!-- 투표 내용 본문 끝-->
                  </div>
                  <!-- 투표 내용 끝 -->
                </div>
              </v-expand-transition>
            </v-col>
          </v-row>
        </v-expand-transition>
        <!-- 경기 상세정보 본문-끝-->
      </div>
      <!-- 경기상세 정보 끝 -->
      <!--투표 참/불 버튼 - 투표페이지-->
      <v-row class="ma-0 pa-0 mt-0" v-if="header.includes('voteMatch')">
        <v-col offset="2" cols="8">
          <!-- 투표 버튼 -->
          <vote-btn :vote="vote" @do-vote="doVote"></vote-btn>
          <!--투표버튼 끝-->
        </v-col>
      </v-row>
      <!-- 대기, 참석취소 버튼 - 일정페이지 -->
      <v-row
        class="ma-0 pa-0 mt-0"
        v-if="header == 'schedule' && !vote.isEndMatch"
      >
        <v-col offset="2" cols="8">
          <wait-cancel-btn :vote="vote" @do-vote="doVote"></wait-cancel-btn>
        </v-col>
      </v-row>
      <!-- 지인 | 명단보기 - 투표페이지 -->
      <v-row v-if="header.includes('voteMatch')">
        <v-col class="py-0" offset="2" cols="8">
          <friend-member-list :vote="vote" :i="i"></friend-member-list>
        </v-col>
      </v-row>
      <!-- 지인 | 명단보기 끝-->
      <!-- 투표 명단보기 - 팀일정페이지 -->
      <v-row v-if="header.includes('schedule') && !vote.isEndMatch">
        <v-col class="py-0" offset="2" cols="8">
          <member-list :vote="vote" :i="i" :awayVote="awayVote"></member-list>
        </v-col>
      </v-row>
      <!-- 투표 명단보기 끝 -->
      <!-- 경기 참여 명단 - 일정페이지(확정됐을 때) + 개인일정-->
      <div
        class="ma-0 pa-0"
        v-if="header.includes('schedule') && vote.isEndMatch"
      >
        <!-- 경기 참여 명단 라벨 -->
        <v-row style="color:rgba(235, 255, 0,0.7)">
          <v-col offset="2" cols="10">
            <span
              class="mx-3"
              @click="openAttendList(i)"
              style="cursor:pointer;"
              >경기 참여 명단 보기</span
            >
          </v-col>
        </v-row>
        <!-- 경기 참여 명단 라벨 끝 -->
        <!-- 경기 참여 명단 본문 -->
        <v-row class="ma-0 pa-0">
          <v-expand-transition>
            <v-col offset="2" cols="8" v-if="activeAttendList == i">
              <!-- 명단 -->
              <v-divider></v-divider>
              <!--참여 인원 내용-->
              <v-row class="justify-space-between ma-0 mx-4 pa-0 mt-4">
                <div>
                  총인원: {{ vote.matchSchedule.entries | countTotalNum }}명
                </div>
                <div>
                  출석: {{ vote.matchSchedule.entries | countAttendNum }}명
                </div>
                <div>
                  지각: {{ vote.matchSchedule.entries | countLateNum }}명
                </div>
                <div>
                  결석: {{ vote.matchSchedule.entries | countAbsentNum }}명
                </div>
              </v-row>
              <v-row class="justify-space-between ma-0 mx-4 pa-0 mt-4">
                <div>
                  팀원: {{ vote.matchSchedule.entries | countMemberNum }}명
                </div>
                <div>
                  지인: {{ vote.matchSchedule.entries | countFriendNum }}명
                </div>
                <div>
                  용병: {{ vote.matchSchedule.entries | countEmpNum }}명
                </div>
              </v-row>
              <v-divider class="my-4"></v-divider>
              <!--투표 인원 내용 끝-->
              <v-row class="text-center">
                <v-col cols="6">
                  닉네임/이메일
                </v-col>
                <v-col cols="2">
                  구분
                </v-col>
                <v-col cols="2">
                  출결
                </v-col>
                <v-col class="ma-0 py-2">
                  <i
                    id="member-close"
                    class="material-icons md-18 float-right"
                    @click="activeAttendList = null"
                    >close</i
                  >
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <v-row
                v-for="(entry, i) in vote.matchSchedule.entries"
                :key="i"
                class="text-center"
              >
                <v-col cols="6">
                  {{ getAttendName(entry) }}
                </v-col>
                <v-col cols="2">
                  {{ entry.type | entryTypeFilter }}
                </v-col>
                <v-col cols="2">
                  {{ entry.attendance | entryAttendFilter }}
                </v-col>
              </v-row>
            </v-col>
          </v-expand-transition>
        </v-row>
        <!-- 경기 참여 명단 본문 끝 -->
      </div>
      <!-- 경기 참여 명단 끝 -->
      <!-- 팀이 받은 점수 -->
      <div class="ma-0 pa-0" v-if="header != 'scheduleUser' && vote.isEndMatch">
        <!-- 팀이 받은 점수 라벨 -->
        <v-row style="color:rgba(235, 255, 0,0.7)">
          <v-col offset="2" cols="10" class="my-0 py-0">
            <span
              class="mx-3"
              @click="openTeamScoreList(i)"
              style="cursor:pointer;"
              >받은 점수 보기</span
            >
          </v-col>
        </v-row>
        <!-- 팀이 받은 점수 라벨 끝 -->
        <!-- 팀이 받은 점수 본문 -->
        <v-row class="ma-0 pa-0">
          <v-expand-transition>
            <v-col offset="2" cols="8" v-if="activeScoreList == i">
              <!-- 명단 -->
              <v-divider></v-divider>
              <!--투표 인원 내용 끝-->
              <v-row class="text-center">
                <v-col cols="6">
                  부여한 사람
                </v-col>
                <v-col cols="2">
                  경기력
                </v-col>
                <v-col cols="2">
                  매너
                </v-col>
                <v-col class="ma-0 py-2">
                  <i
                    id="member-close"
                    class="material-icons md-18 float-right"
                    @click="activeScoreList = null"
                    >close</i
                  >
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <v-row
                v-for="(teamScore, i) in vote.matchSchedule.teamScores"
                :key="i"
                class="text-center"
              >
                <v-col cols="6">
                  {{ getGiverName(teamScore) }}
                </v-col>
                <v-col cols="2">
                  {{ teamScore.teamAbility | showTeamScore }}
                </v-col>
                <v-col cols="2">
                  {{ teamScore.teamManner | showTeamScore }}
                </v-col>
              </v-row>
            </v-col>
          </v-expand-transition>
        </v-row>
      </div>
      <!-- 팀이 받은 점수 끝 -->
      <!-- 용병/지인이 받은 점수 -->
      <div
        class="ma-0 pa-0 mt-2"
        v-if="header == 'scheduleUser' && vote.isEndMatch"
      >
        <!-- 용병/지인이 받은 점수 라벨 -->
        <v-row style="color:rgba(235, 255, 0,0.7)">
          <v-col offset="2" cols="10" class="my-0 py-0">
            <span
              class="mx-3"
              @click="controlEmpScoreList(i, vote)"
              style="cursor:pointer;"
              >받은 점수 보기</span
            >
          </v-col>
        </v-row>
        <!-- 용병/지인이 받은 점수 라벨 끝 -->
        <!--용병/지인이 받은 점수 본문 -->
        <v-row class="ma-0 pa-0">
          <v-expand-transition>
            <v-col offset="2" cols="8" v-if="activeEmpScoreList == i">
              <!-- 명단 -->
              <v-divider></v-divider>
              <!--투표 인원 내용 끝-->
              <v-row class="text-center">
                <v-col cols="6">
                  부여한 사람
                </v-col>
                <v-col cols="2">
                  경기력
                </v-col>
                <v-col cols="2">
                  매너
                </v-col>
                <v-col class="ma-0 py-2">
                  <i
                    id="member-close"
                    class="material-icons md-18 float-right"
                    @click="activeEmpScoreList = null"
                    >close</i
                  >
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <v-row
                v-for="(empScore, j) in empScores"
                :key="j"
                class="text-center"
              >
                <v-col cols="6">
                  {{ getGiverName(empScore) }}
                </v-col>
                <v-col cols="2">
                  {{ empScore.empManner | showTeamScore }}
                </v-col>
                <v-col cols="2">
                  {{ empScore.empAbility | showTeamScore }}
                </v-col>
              </v-row>
            </v-col>
          </v-expand-transition>
        </v-row>
      </div>
      <!--용병/지인이 받은 점수 점수 끝 -->
      <!-- 관리자 버튼 영역 -->
      <v-row v-if="controlManagerBtn(vote)">
        <v-col offset="2" cols="8">
          <v-row justify="center" class="ma-0 pa-0">
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              @click="endVote(vote)"
              v-if="vote.voteStatus == 0"
              >투표마감하기</v-btn
            >
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              @click="updateSetting('setWaiting', vote)"
              v-if="controlAdditionBtn(vote)"
              >추가인원받기</v-btn
            >
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              @click="deleteVoteMatch(vote)"
              >경기취소하기</v-btn
            >
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              v-if="header == 'voteMatchManager'"
              @click="confirmMatchSchedule(vote)"
              >경기 확정하기</v-btn
            >
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              @click="updateVoteMatch(vote)"
              v-if="controlUpdateBtn(vote)"
              >투표수정하기</v-btn
            >
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              @click="moveToRegister(vote, 0)"
              v-if="controlEmployBtn(vote)"
              >용병찾기</v-btn
            >
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              @click="moveToRegister(vote, 1)"
              v-if="controlSearchBtn(vote)"
              >상대팀찾기</v-btn
            >
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              @click="moveToRegister(vote, 2)"
              v-if="controlAssignmentBtn(vote)"
              >양도하기</v-btn
            >
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              @click="updateSchedule(vote)"
              v-if="header == 'scheduleManager'"
              >일정 수정하기</v-btn
            >
            <v-btn
              elevation="3"
              width="16%"
              small
              class="mr-2"
              @click="deleteSchedule(vote)"
              v-if="header == 'scheduleManager'"
              >일정 삭제하기</v-btn
            >
          </v-row>
        </v-col>
      </v-row>
      <!-- 관리자 버튼 영역 끝 -->
    </v-card>
    <!-- 카카오 공유하기 창 -->
    <v-dialog v-model="dialogKakao">
      <kakao-link :vote="sendingVote" @close="closeKaokao"></kakao-link>
    </v-dialog>
    <!-- 카카오 공유하기 창 끝 -->
  </div>
</template>
<style scroped src="@/assets/css/vote-match/VoteMatchList.css"></style>
<script scoped src="@/assets/js/vote-match/VoteMatchList.js"></script>
