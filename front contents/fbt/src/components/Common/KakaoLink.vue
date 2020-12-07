<template>
  <div>
    <v-card>
      <v-card-title class="headline"
        >단톡방에 투표 소식을 공유하시겠습니까?</v-card-title
      >
      <v-card-text>
        <v-row class="mt-5" justify="center">
          <v-btn class="mr-7" elevation="2" color="#6920A3" @click="shareLink"
            >예</v-btn
          >
          <v-btn elevation="2" color="#AD1457" @click="close">아니오</v-btn>
        </v-row>
      </v-card-text>
    </v-card>
  </div>
</template>

<script scoped src="@/assets/js/common/KakaoLink.js"></script>
