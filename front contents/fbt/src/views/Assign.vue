<template>
  <div>
    <v-container fluid class="ma-0 mt-7 pa-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-row
            justify="center"
            fluid
            align="center"
            class="py-0 my-0 px-0 mx-0"
          >
            <!-- 매치 등록: page = 1 -->
            <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                :class="{ 'btn-on-purple': page == 1 }"
                @click="page = 1"
                >양도 등록</v-btn
              >
            </v-col>
            <!-- 매치 검색: page = 2 -->
            <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                :class="{ 'btn-on-purple': page == 2 }"
                style="border:0px;"
                @click="page = 2"
                >양도 검색</v-btn
              >
            </v-col>
          </v-row>
          <v-row
            justify="center"
            fluid
            align="center"
            class="py-0 my-0 px-0 mx-0"
          >
            <!-- 등록 매치 현황: page = 3 -->
            <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                :class="{ 'btn-on-purple': page == 3 }"
                @click="page = 3"
                >등록 양도 현황</v-btn
              >
            </v-col>
            <!-- 신청 매치 현황: page = 3 -->
            <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                style="border:0px;"
                :class="{ 'btn-on-purple': page == 4 }"
                @click="page = 4"
                >신청 양도 현황</v-btn
              >
            </v-col>
          </v-row>
          <!-- 등록매치 5단계 버튼: page = 3 -->
          <v-row
            fluid
            justify="center"
            class="ma-0 mt-1 pa-0 text-center"
            v-if="page == 3"
          >
            <v-col offset="1" cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': registeredStage == 1 }"
                @click="registeredStage = 1"
                >양도중</v-btn
              >
            </v-col>
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': registeredStage == 2 }"
                @click="registeredStage = 2"
                >양도신청</v-btn
              >
            </v-col>
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': registeredStage == 3 }"
                @click="registeredStage = 3"
                >양도실패</v-btn
              >
            </v-col>
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': registeredStage == 4 }"
                @click="registeredStage = 4"
                >양도완료</v-btn
              >
            </v-col>
          </v-row>
          <!-- 등록 매치 5단계 버튼 끝 -->
          <!-- 신청매치 5단계 버튼: page = 4 -->
          <v-row
            fluid
            justify="center"
            class="ma-0 mt-1 pa-0 text-center"
            v-if="page == 4"
          >
            <v-col offset="1" cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': appliedStage == 1 }"
                @click="appliedStage = 1"
                >양도 신청</v-btn
              >
            </v-col>
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': appliedStage == 2 }"
                @click="appliedStage = 2"
                >양도 신청 거절</v-btn
              >
            </v-col>
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': appliedStage == 3 }"
                @click="appliedStage = 3"
                >양도 신청 수락</v-btn
              >
            </v-col>
          </v-row>
          <!-- 신청 매치 5단계 버튼 끝 -->
        </v-col>
      </v-row>
    </v-container>
    <assign-insert
      :matchScheduleId="matchScheduleId"
      v-if="page == 1"
      @close="page = 3"
    >
    </assign-insert>
    <search-assign v-if="page == 2"></search-assign>
    <registered-assign
      :registeredStage="registeredStage"
      :appliedStage="null"
      @change-registered-stage="changeRegisteredStage"
      v-if="page == 3"
    ></registered-assign>
    <applied-assign
      :registeredStage="null"
      :appliedStage="appliedStage"
      v-if="page == 4"
    ></applied-assign>
  </div>
</template>
<script scoped src="@/assets/js/assign/Assign.js"></script>
<style scroped src="@/assets/css/common/barButton.css"></style>
