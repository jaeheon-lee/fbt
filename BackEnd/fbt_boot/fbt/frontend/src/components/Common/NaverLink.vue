<template>
  <div>
    <v-card>
      <v-card-title class="headline"
        >카페에 해당글을 올리시겠습니까?</v-card-title
      >
      <v-card-text>
        <v-row class="mt-5" justify="center">
          <div>
            글과 관련된 정보는 보통 카페 올리는 형식에 맞게 클립보드에
            복사했습니다.
          </div>
        </v-row>
        <v-row class="mt-5" justify="center">
          <div>공유 시, 덧글에 복사해주세요.</div>
        </v-row>
        <v-row class="mt-5" justify="center">
          <div>자동 완성된 덧글 확인 부탁드립니다.</div>
          <div id="textDiv"></div>
        </v-row>
        <v-row class="mt-5" justify="center">
          <v-btn class="mr-7" elevation="2" color="#6920A3" @click="getInfoForWrite"
            >예</v-btn
          >
          <v-btn elevation="2" color="#AD1457" @click="close">아니오</v-btn>
        </v-row>
      </v-card-text>
    </v-card>
  </div>
</template>

<script scoped src="@/assets/js/common/NaverLink.js"></script>
