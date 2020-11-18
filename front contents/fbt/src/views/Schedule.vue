<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <!-- Page Label -->
          <v-row class="py-0 my-0 px-0 mx-5">
            <v-col cols="12" class="pa-0 ma-0 mb-2">
              <span>팀 일정</span>
              <v-divider style="border-color: #C4C4C4 !important;">
                inset
              </v-divider>
            </v-col>
          </v-row>
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
            <v-sheet width="100%">
              <v-calendar
                ref="calendar"
                v-model="value"
                :weekdays="weekday"
                :type="type"
                :events="events"
                :event-overlap-mode="mode"
                :event-overlap-threshold="30"
                :event-color="getEventColor"
                @change="getEvents"
                @click:event="showEvent"
                width="100"
                color="primary"
              ></v-calendar>
            </v-sheet>
          </v-row>
          <v-dialog v-model="selectedOpen">
            <v-row fluid justify="center" class="py-0 my-0 px-0 mx-0">
              <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
                <v-row>
                  <v-col cols="12">
                    <vote-match-list
                      :selectedEvent="selectedEvent"
                      :selectedOpen="selectedOpen"
                    ></vote-match-list>
                  </v-col>
                </v-row>
                <v-row justify="end" class="mr-1">
                  <v-btn
                    elevation="3"
                    color="#6920A3"
                    @click="selectedOpen = false"
                    >닫기</v-btn
                  >
                </v-row>
              </v-col>
            </v-row>
          </v-dialog>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/schedule/Schedule.js"></script>
