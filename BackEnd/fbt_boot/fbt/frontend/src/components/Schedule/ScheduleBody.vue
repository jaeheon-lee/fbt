<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <!-- 캘린더 옵션 설정 -->
          <v-row>
            <v-col cols="1">
              <v-btn icon class="ml-2" @click="$refs.calendar.prev()">
                <v-icon>mdi-chevron-left</v-icon>
              </v-btn>
            </v-col>
            <v-col cols="3">
              <v-select
                v-model="type"
                :items="types"
                dense
                outlined
                hide-details
                label="type"
              ></v-select>
            </v-col>
            <v-col cols="2">
              <span v-if="$refs.calendar">{{ $refs.calendar.title }}</span>
            </v-col>
            <v-col cols="1">
              <v-btn icon class="ml-2" @click="$refs.calendar.next()">
                <v-icon>mdi-chevron-right</v-icon>
              </v-btn>
            </v-col>
          </v-row>
          <!-- 캘린더 옵션 설정 끝 -->
          <!-- date-picker -->
          <v-row class="py-0 my-0 px-0 mx-5">
            <v-sheet elevation="3" width="100%">
              <v-calendar
                ref="calendar"
                v-model="value"
                :weekdays="weekday"
                :type="type"
                :events="events"
                :event-overlap-mode="mode"
                :event-overlap-threshold="30"
                :event-color="getEventColor"
                @change="getEventsInit"
                @click:event="showMatchScheduleInfoByClick"
                width="100"
                color="primary"
              ></v-calendar>
            </v-sheet>
          </v-row>
          <!-- date-picker 끝 -->
          <!-- 선택된 일정의 경기 정보 & 경기작성/수정 버튼 -->
          <div id="matchInfo" v-if="infoActive">
            <!-- 팀일정일 때 -->
            <v-row class="py-0 my-0 mt-5 px-0 mx-5" v-if="isManager">
              <v-col class="ma-0 pa-0">
                <span>경기 정보</span>
                <span
                  class="float-right"
                  style="cursor:pointer"
                  @click="openWritePage"
                  v-if="controlWriteBtn"
                  >경기결과 작성하기</span
                >
                <span
                  class="float-right"
                  style="cursor:pointer"
                  @click="openUpdatePage"
                  v-if="controlUpdateBtn"
                  >경기결과 수정하기</span
                >
              </v-col>
            </v-row>
            <!-- 팀일정일 때 끝 -->
            <!-- 개인일정일 때 -->
            <v-row class="py-0 my-0 mt-5 px-0 mx-5" v-if="isUser">
              <v-col class="ma-0 pa-0">
                <span>경기 정보</span>
                <span
                  class="float-right"
                  style="cursor:pointer"
                  @click="openWritePage"
                  v-if="controlUserWriteBtn"
                  >팀 평가하기</span
                >
                <span
                  class="float-right"
                  style="cursor:pointer"
                  @click="openUpdatePage"
                  v-if="controlUserUpdateBtn"
                  >팀 평가 수정하기</span
                >
              </v-col>
            </v-row>
            <!-- 개인일정일 때 끝 -->
            <v-row class="py-0 my-0 px-0 mx-5">
              <v-divider color="white"></v-divider>
            </v-row>
            <v-row class="py-0 my-0 px-0 mx-2">
              <v-col cols="12">
                <vote-match-list
                  :votes="votes"
                  :awayVote="awayVote"
                  :header="sendingHeader"
                  :isByAssign="isByAssign"
                  @refresh="showMatchScheduleInfoByRefresh"
                  @refresh-monthly="getEventFresh"
                  v-if="loading == false"
                ></vote-match-list>
                <progress-circle v-else></progress-circle>
              </v-col>
            </v-row>
          </div>
          <!-- 선택된 일정의 경기 정보 끝 -->
          <!-- 선택된 일정의 경기 결과 작성 페이지 -->
          <div id="matchResult" v-if="writeActive && !infoActive">
            <v-row class="py-0 my-0 mt-5 px-0 mx-5">
              <v-col class="ma-0 pa-0">
                <span>경기 결과 작성</span>
              </v-col>
            </v-row>
            <v-row class="py-0 my-0 px-0 mx-5">
              <v-divider color="white"></v-divider>
            </v-row>
            <v-row class="py-0 my-0 px-0 mx-2">
              <v-col cols="12">
                <match-result-insert
                  :votes="votes"
                  :isUpdate="isUpdate"
                ></match-result-insert>
              </v-col>
            </v-row>
          </div>
          <!-- 선택된 일정의 경기 결과 작성 페이지 끝-->
          <!-- 선택된 일정의 용병 경기 결과 작성 페이지 -->
          <div id="empMatchResult" v-if="empWriteActive && !infoActive">
            <v-row class="py-0 my-0 mt-5 px-0 mx-5">
              <v-col class="ma-0 pa-0">
                <span>팀 평가하기</span>
              </v-col>
            </v-row>
            <v-row class="py-0 my-0 px-0 mx-5">
              <v-divider color="white"></v-divider>
            </v-row>
            <v-row class="py-0 my-0 px-0 mx-2">
              <v-col cols="12">
                <team-score-insert
                  :teamScoreByEmp="teamScoreByEmp"
                  :matchScheduleId="votes[0].matchSchedule.matchScheduleId"
                ></team-score-insert>
              </v-col>
            </v-row>
          </div>
          <!-- 선택된 일정의 용병 경기 결과 작성 페이지 끝-->
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/schedule/ScheduleBody.js"></script>
