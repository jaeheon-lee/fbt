<template>
  <div>
    <v-container fluid class="ma-0 pa-0">
      <!-- 등록 매치 현황 Label -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 mb-2"
          v-if="appliedStage == 1"
        >
          <span>용병신청</span>
        </v-col>
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 mb-2"
          v-if="appliedStage == 2"
        >
          <span>용병신청거절</span>
        </v-col>
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 mb-2"
          v-if="appliedStage == 3"
        >
          <span>용병신청수락</span>
        </v-col>
      </v-row>
      <!-- 등록 매치 현황 Label 끝-->
      <employ-list
        :appliedStage="appliedStage"
        :registeredStage="registeredStage"
      ></employ-list>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/employ/AppliedEmploy.js"></script>
