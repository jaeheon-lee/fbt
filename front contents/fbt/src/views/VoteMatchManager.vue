<template>
  <div>
    <!-- 투표 현황 | 생성 버튼 -->
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
                :class="{ 'btn-on-red': !isInsert }"
                @click="isInsert = false"
                >투표현황</v-btn
              >
            </v-col>
            <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                :class="{ 'btn-on-red': isInsert }"
                @click="isInsert = true"
                >투표생성</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <vote-match-insert v-if="isInsert"></vote-match-insert>
    <vote-match :isManager="isManager" v-else></vote-match>
  </div>
</template>
<script scroped src="@/assets/js/vote-match/VoteMatchManager.js"></script>
<style scroped src="@/assets/css/common/barButton.css"></style>
