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
                >용병 찾기 등록</v-btn
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
                >용병 찾기 현황</v-btn
              >
            </v-col>
          </v-row>
          <!-- 용병 등록 2단계 버튼: page = 3 -->
          <v-row
            fluid
            justify="center"
            class="ma-0 mt-1 pa-0 text-center"
            v-if="page == 2"
          >
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': registeredStage == 1 }"
                @click="registeredStage = 1"
                >용병 찾는 중</v-btn
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
                >용병 찾기 실패</v-btn
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
                >용병 찾기 완료</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <employ-insert
      :matchScheduleId="matchScheduleId"
      v-if="page == 1"
      @close="page = 2"
    ></employ-insert>
    <registered-employ
      :registeredStage="registeredStage"
      :appliedStage="null"
      v-if="page == 2"
    ></registered-employ>
  </div>
</template>
<script scoped src="@/assets/js/employ/EmployManager.js"></script>
<style scroped src="@/assets/css/common/barButton.css"></style>
