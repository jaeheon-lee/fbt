<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
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
                @click="openVoteOn"
                :class="{ 'btn-on': !isEnd }"
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
                @click="openVoteDone"
                :class="{ 'btn-on': isEnd }"
                >투표완료</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <vote-match-body v-if="!isEnd"></vote-match-body>
  </div>
</template>
<style scroped src="@/assets/css/vote-match/VoteMatchBody.css"></style>
<script scoped src="@/assets/js/vote-match/VoteMatchBody.js"></script>
