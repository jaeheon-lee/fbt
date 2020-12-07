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
          <span>투표 마감일시 : {{ vote.dueDate | extrackSecond }}</span>
        </v-col>
      </v-row>
      <!-- 기본 정보 -->
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
            :src="getEmbUrl(vote.matchSchedule.homeTeam)"
            aspect-ratio="1.2"
            contain
          />
          <!-- 홈팀 이름 -->
          {{ vote.matchSchedule.homeTeam.teamName }}
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
          <div class="my-1" style="font-size: 1.5em" v-if="vote.isEndMatch">
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
            class="mb-5"
            :src="getEmbUrl(vote.matchSchedule.awayTeam)"
            aspect-ratio="1.2"
            contain
          />
          <!-- 어웨이 팀이름 -->
          {{ vote.matchSchedule.awayTeam | showAwayTeamName }}
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
                          vote.matchSchedule.matchType | showMatchType
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
                  <!--내용-->
                  <!-- 내용 라벨 -->
                  <v-row class="mx-0 px-0" v-if="!vote.isEndMatch">
                    <v-col cols="12" class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                      >투표 메모</v-col
                    >
                  </v-row>
                  <!-- 내용 본문 -->
                  <v-row class="mx-0 px-0" v-if="!vote.isEndMatch">
                    <v-col cols="12" class="text-left pa-0"
                      ><v-row
                        class="ma-0 pa-3"
                        justify="center"
                        style="border:2px solid #AD1457;border-radius:25px;"
                        >{{ vote.memo }}</v-row
                      ></v-col
                    >
                  </v-row>
                  <!--내용 끝 -->
                </div>
              </v-expand-transition>
            </v-col>
          </v-row>
        </v-expand-transition>
        <!-- 경기 상세정보 본문-끝-->
      </div>
      <!-- 경기상세 정보 끝 -->
      <!--투표 & 취소, 대기버튼-->
      <v-row class="ma-0 pa-0 mt-0" v-if="!vote.isEndMatch">
        <v-col offset="2" cols="8">
          <!-- 투표 버튼 -->
          <v-row class="justify-center" v-if="controlAttendBtnOnSchedule(vote)">
            <v-btn
              small
              width="35px"
              color="#AD1457"
              class="mr-1"
              @click.stop="doVote(vote.voteMatchId, 1, i)"
              >참 석</v-btn
            >
            <v-btn
              small
              width="35px"
              color="rgba(235, 255, 0,0.7)"
              class="ml-1"
              @click.stop="doVote(vote.voteMatchId, 0, i)"
              >불참석</v-btn
            >
          </v-row>
          <!--투표버튼 끝-->
          <!-- 대기하기, 참석취소 버튼 -->
          <v-row
            class="justify-center"
            v-if="controlWaitBtn(vote) || controlCancelBtn(vote)"
          >
            <v-btn
              small
              width="35px"
              color="#AD1457"
              class="mr-1"
              v-if="controlWaitBtn(vote)"
              @click.stop="doVote(vote.voteMatchId, 2, i)"
              >대기하기</v-btn
            >
            <v-btn
              small
              width="75px"
              color="#AD1457"
              class="mr-1"
              v-if="controlCancelBtn(vote)"
              @click.stop="doVote(vote.voteMatchId, 0, i)"
              >참석취소하기</v-btn
            >
          </v-row>
        </v-col>
      </v-row>
      <!-- 지인 | 명단보기 버튼 -->
      <v-row v-if="!vote.isEndMatch">
        <v-col class="py-0" offset="2" cols="8">
          <v-row
            id="vote-btn"
            class="mx-0 mt-0 pt-0"
            style="color:rgba(235, 255, 0,0.7)"
          >
            <v-col class="float-left">
              <span @click="openMemberList(i)" style="cursor:pointer;"
                >투표 명단 보기</span
              >
            </v-col>
            <v-col>
              <span
                class="float-right"
                style="cursor:pointer;"
                @click="openFriendList(i)"
                v-if="
                  vote.voteMatchSetting.friendEmp == 1 &&
                    controlAttendBtnOnSchedule(vote)
                "
                >지인 초대하기</span
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <!-- 경기 참여 명단 -->
      <div class="ma-0 pa-0" v-if="vote.isEndMatch">
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
      <div class="ma-0 pa-0" v-if="vote.isEndMatch">
        <!-- 팀이 받은 점수 라벨 -->
        <v-row style="color:rgba(235, 255, 0,0.7)">
          <v-col offset="2" cols="10" class="my-0 py-0">
            <span class="mx-3" @click="openScoreList(i)" style="cursor:pointer;"
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
        <!-- 경기 참여 명단 본문 끝 -->
      </div>
      <!-- 팀이 받은 점수 끝 -->
      <!-- 지인 | 명단보기 버튼 끝-->
      <!-- 명단 | 지인 초대 부분 -->
      <v-row v-if="!vote.isEndMatch">
        <v-col offset="2" cols="8">
          <!-- 명단 -->
          <v-expand-transition>
            <div v-if="activeMemberList == i && openType == 0">
              <v-divider></v-divider>
              <!--투표 인원 내용-->
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
              <v-row justify="end" class="ma-0 pa-0 mt-3 mb-5">
                <div>경기참석 인원: {{ vote.totalAttend }}명</div>
              </v-row>
              <!--투표 인원 내용 끝-->
              <v-row class="text-center">
                <v-col cols="5">
                  아이디
                </v-col>
                <v-col cols="4">
                  이름
                </v-col>
                <v-col cols="2">
                  참/불
                </v-col>
                <v-col cols="1" class="ma-0 pa-0">
                  <i
                    id="member-close"
                    class="material-icons md-18"
                    @click="closeMemberList()"
                    >close</i
                  >
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <v-row
                v-for="(result, i) in vote.voteMatchResults"
                :key="i"
                class="text-center"
              >
                <v-col cols="5">
                  {{ result.teamMember.user.email }}
                </v-col>
                <v-col cols="4">
                  {{ result.user.name }}
                </v-col>
                <v-col cols="2">
                  {{ result.attendance | attendanceFliter }}
                </v-col>
              </v-row>
            </div>
          </v-expand-transition>
          <!-- 지인 초대 -->
          <v-expand-transition>
            <div v-if="activeFriendList == i && openType == 1">
              <v-divider></v-divider>
              <v-row class="pt-3">
                <v-col class="pr-0 pt-0" cols="10">
                  <v-text-field
                    class="ma-0 pa-0"
                    v-model="inputEmail"
                    placeholder="검색할 이메일을 입력해주세요."
                    @keydown.enter="searchFriend"
                    dense
                  />
                </v-col>
                <v-col cols="1" class="pt-1 pr-0 mx-0">
                  <i class="material-icons md-18" @click.stop="searchFriend"
                    >search</i
                  >
                </v-col>
                <v-col cols="1" class="pa-0 pt-1">
                  <i class="material-icons md-18" @click="closeFriendList()"
                    >close</i
                  >
                </v-col>
              </v-row>
              <v-row class="text-center">
                <v-col offset="2" cols="3">
                  e-mail
                </v-col>
                <v-col cols="2">
                  이름
                </v-col>
              </v-row>
              <v-row
                id="friend-list"
                class="text-center"
                v-for="(friend, j) in friends"
                :key="j"
              >
                <v-col offset="2" cols="3">
                  {{ friend.email }}
                </v-col>
                <v-col cols="2">
                  {{ friend.name }}
                </v-col>
                <v-col cols="1" class="ma-0 mb-3 pa-0 text-center"
                  ><v-btn
                    class="ma-0 ml-8 pa-0"
                    elevation="2"
                    color="#6920A3"
                    @click="inviteFriend(friend.email, vote.voteMatchId)"
                    style="font-size: 0.65em"
                    >초대하기</v-btn
                  >
                </v-col>
              </v-row>
            </div>
          </v-expand-transition>
        </v-col>
      </v-row>
      <!-- 명단 | 지인 초대 부분 끝 -->
      <!-- 관리자 버튼 영역 -->
      <v-row v-if="header.slice(-7, header.length) == 'Manager'">
        <v-col offset="2" cols="8">
          <v-row class="ma-0 pa-0" v-if="controlManagerBtnByPeriod(vote)">
            <v-col class="justify-center" v-if="vote.voteStatus == 0">
              <v-btn
                elevation="3"
                width="100%"
                small
                class="justify-center"
                @click="endVote(vote)"
                >투표마감하기</v-btn
              >
            </v-col>
            <v-col class="justify-center" v-if="controlAdditionBtn(vote)">
              <v-btn
                elevation="3"
                width="100%"
                small
                class="justify-center"
                @click="updateSetting('setWaiting', vote)"
                >추가인원받기</v-btn
              >
            </v-col>
            <v-col class="justify-center">
              <v-btn
                elevation="3"
                width="100%"
                small
                @click="deleteVoteMatch(vote)"
                >경기취소하기</v-btn
              >
            </v-col>
            <v-col class="justify-center">
              <v-btn
                elevation="3"
                width="100%"
                small
                @click="moveToRegister(vote, 0)"
                >용병찾기</v-btn
              >
            </v-col>
            <v-col class="justify-center" v-if="controlSearchBtn(vote)">
              <v-btn
                elevation="3"
                width="100%"
                small
                @click="moveToRegister(vote, 1)"
                >상대팀찾기</v-btn
              >
            </v-col>
            <v-col class="justify-center">
              <v-btn
                elevation="3"
                width="100%"
                small
                @click="moveToRegister(vote, 2)"
                v-if="controlAssignmentBtn(vote)"
                >양도하기</v-btn
              >
            </v-col>
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
