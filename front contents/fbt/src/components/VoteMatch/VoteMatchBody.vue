<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5" v-if="!isEnd">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>진행중인 투표</span>
          <v-divider id="divider-vote"> inset </v-divider>
        </v-col>
      </v-row>
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5" v-else>
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>마감된 투표</span>
          <v-divider id="divider-vote"> inset </v-divider>
        </v-col>
      </v-row>
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <!-- 각 투표 리스트 출력 부분 -->
          <v-card
            id="vote-list"
            v-for="(vote, i) in votes"
            :key="i"
            elevation="2"
          >
            <v-row class="ma-0 pa-0">
              <v-col cols="12" class="text-left" style="font-size: 0.75em">
                투표 마감일시 : {{ vote.dueDate | extrackSecond }}
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
                  :src="getEmbUrl(vote.matchSchedule.homeTeam)"
                  aspect-ratio="1.2"
                  contain
                />
                <!-- 홈팀 이름 -->
                {{ vote.matchSchedule.homeTeam.teamName }}
              </v-col>
              <!-- 경기 간략 정보 -->
              <v-col id="match-info" cols="4" md="4" class="pt-1 text-center">
                <div id="match-type" class="match-type">
                  <!-- N:N 축구 | 풋살 경기 표시 -->
                  {{ vote.matchSchedule.matchType | showMatchType }}
                  <p />
                  <!-- 팀 | 용병경기 표시 -->
                  {{ vote.voteMatchSetting | isEmp }}
                </div>
                <div id="match-score"></div>
                <div id="match-info-simple">
                  <!-- 경기 날짜 -->
                  <p>{{ vote.matchSchedule.startTime.split(" ")[0] }}</p>
                  <!-- 경기 시간 -->
                  <p>
                    {{ vote.matchSchedule.startTime | showOnlyTime }} ~
                    {{ vote.matchSchedule.endTime | showOnlyTime }}
                    {{ vote.matchSchedule.duration }}시간
                  </p>
                  <!-- 경기장 정보 -->
                  <p>{{ vote.matchSchedule.stadiumName }}</p>
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
                {{ vote.matchSchedule.awayTeam.teamName }}
              </v-col>
            </v-row>
            <!-- 경기 상세정보 -->
            <v-row style="color:rgba(235, 255, 0,0.7)">
              <v-col offset="2" cols="10" class="mt-3">
                <span id="match-info-detail-btn" @click="openDetail(i)"
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
                    <v-row class="mx-0 px-0">
                      <v-col
                        cols="12"
                        class="textc-left mx-0 pl-2 pr-1 px-0 pb-2"
                        >투표 메모</v-col
                      >
                    </v-row>
                    <!-- 내용 본문 -->
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
                    <!--내용 끝 -->
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
                    <v-row justify="end" class="ma-0 pa-0 my-3">
                      <div>경기참석 인원: {{ vote.totalAttend }}명</div>
                    </v-row>
                    <!--투표 인원 내용 끝-->
                  </div>
                </v-expand-transition>
                <!--투표버튼-->
                <div v-if="!isEnd">
                  <v-row class="justify-center ma-0 pa-0 mt-4">
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
                  <!-- 지인 | 명단보기 버튼 -->
                  <v-row
                    id="vote-btn"
                    class="mt-0 pt-0"
                    style="color:rgba(235, 255, 0,0.7)"
                  >
                    <v-col cols="2">
                      <span @click="openMemberList(i)">명단 보기</span>
                    </v-col>
                    <v-col
                      offset="8"
                      cols="2"
                      class="text-right"
                      v-if="vote.voteMatchSetting.friendEmp == 1"
                    >
                      <span @click="openFriendList(i)">지인 초대하기</span>
                    </v-col>
                  </v-row>
                </div>
              </v-col>
            </v-row>
            <!-- 명단 | 지인 초대 부분 -->
            <div id="member-invite" v-if="!isEnd">
              <!-- 칸 띄우기 -->
              <v-row class="ma-0 pa-0">
                <v-col cols="12"></v-col>
              </v-row>
              <v-divider></v-divider>
              <!-- 명단 -->
              <v-expand-transition>
                <div
                  id="voted-Member-list"
                  v-if="activeMemberList == i && openType == 0"
                >
                  <v-row class="text-center">
                    <v-col offset="2" cols="3">
                      아이디
                    </v-col>
                    <v-col cols="3">
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
                    <v-col offset="2" cols="3">
                      {{ result.teamMember.user.email }}
                    </v-col>
                    <v-col cols="3">
                      {{ result.user.name }}
                    </v-col>
                    <v-col cols="2">
                      {{ result.attendance | attendanceFliter }}
                    </v-col>
                  </v-row>
                </div>
              </v-expand-transition>
              <!-- 지인 초대 -->
              <v-divider></v-divider>
              <v-expand-transition>
                <div
                  class="ml-6"
                  id="invite-friend"
                  v-if="activeFriendList == i && openType == 1"
                >
                  <v-row>
                    <v-col offset="2" cols="7">
                      <v-text-field
                        v-model="inputEmail"
                        placeholder="검색할 이메일을 입력해주세요."
                        @keydown.enter="searchFriend"
                      />
                    </v-col>
                    <v-col cols="1" class="ma-0 mt-9 pa-0">
                      <i class="material-icons md-18" @click.stop="searchFriend"
                        >search</i
                      >
                    </v-col>
                    <v-col>
                      <v-col cols="1" class="ma-0 pa-0">
                        <i
                          id="member-close"
                          class="material-icons md-18"
                          @click="closeFriendList()"
                          >close</i
                        >
                      </v-col>
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
                    <v-col cols="1" class="ma-0 mb-3 pa-0 text-center">
                      <v-btn
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
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<style scroped src="@/assets/css/vote-match/VoteMatchBody.css"></style>
<script scoped src="@/assets/js/vote-match/VoteMatchBody.js"></script>
