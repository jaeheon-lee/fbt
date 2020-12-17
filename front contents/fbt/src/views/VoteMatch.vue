<template>
  <div>
    <v-container fluid class="py-0 px-0 mx-0" :class="controlMY">
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
                @click="isEnd = false"
                :class="{ 'btn-on-red': !isEnd }"
                >투표중</v-btn
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
                @click="isEnd = true"
                :class="{ 'btn-on-red': isEnd }"
                >투표완료</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
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
          <vote-match-list
            :header="sendingHeader"
            :isEnd="isEnd"
            :votes="votes"
            @refresh="showVoteInfo"
          ></vote-match-list>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<style scroped src="@/assets/css/common/barButton.css"></style>
<style scroped src="@/assets/css/vote-match/VoteMatch.css"></style>
<script scoped src="@/assets/js/vote-match/VoteMatch.js"></script>
