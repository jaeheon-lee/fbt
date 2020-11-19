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
            <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                :class="{ 'btn-on-purple': page == 1 }"
                @click="page = 1"
                >매치 등록</v-btn
              >
            </v-col>
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
                >매치 검색</v-btn
              >
            </v-col>
          </v-row>
          <v-row
            justify="center"
            fluid
            align="center"
            class="py-0 my-0 px-0 mx-0"
          >
            <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                :class="{ 'btn-on-purple': page == 3 }"
                @click="page = 3"
                >등록 매치 현황</v-btn
              >
            </v-col>
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
                >신청 매치 현황</v-btn
              >
            </v-col>
          </v-row>
          <v-row
            fluid
            justify="center"
            class="ma-0 mt-1 pa-0 text-center"
            v-if="page > 2"
          >
            <v-col offset="1" cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': stage == 1 }"
                @click="stage = 1"
                >매치중</v-btn
              >
            </v-col>
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': stage == 2 }"
                @click="stage = 2"
                >인원파악신청</v-btn
              >
            </v-col>
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': stage == 3 }"
                @click="stage = 3"
                >인원파악중</v-btn
              >
            </v-col>
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': stage == 4 }"
                @click="stage = 4"
                >매치실패</v-btn
              >
            </v-col>
            <v-col cols="2" class="ma-0 pa-0">
              <v-btn
                tile
                dark
                width="100%"
                class="ma-0 pa-0"
                :class="{ 'btn-on-purple': stage == 5 }"
                @click="stage = 5"
                >매치완료</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <search-insert
      :matchScheduleId="matchScheduleId"
      v-if="page == 1"
    ></search-insert>
    <div v-if="page == 2">2</div>
    <registered-search :stage="stage" v-if="page == 3"></registered-search>
  </div>
</template>
<script scoped src="@/assets/js/search/Search.js"></script>
<style scroped src="@/assets/css/common/barButton.css"></style>
