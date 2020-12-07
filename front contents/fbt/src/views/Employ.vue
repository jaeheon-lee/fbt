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
                >용병경기 찾기</v-btn
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
                >용병경기 신청 현황</v-btn
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
                :class="{ 'btn-on-purple': appliedStage == 1 }"
                @click="appliedStage = 1"
                >용병 신청</v-btn
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
                >용병신청거절</v-btn
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
                >용병신청수락</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <search-employ v-if="page == 1"></search-employ>
    <applied-employ
      :registeredStage="null"
      :appliedStage="appliedStage"
      v-if="page == 2"
    ></applied-employ>
  </div>
</template>
<script scoped src="@/assets/js/employ/Employ.js"></script>
<style scroped src="@/assets/css/common/barButton.css"></style>
