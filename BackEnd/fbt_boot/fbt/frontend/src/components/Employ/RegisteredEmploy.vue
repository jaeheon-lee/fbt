<template>
  <div>
    <v-container fluid class="ma-0 pa-0">
      <!-- 용병 찾기 현황 Label -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 my-2"
          v-if="registeredStage == 1"
        >
          <span>용병 찾는 중</span>
        </v-col>
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 my-2"
          v-if="registeredStage == 2"
        >
          <span>용병 찾기 실패</span>
        </v-col>
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 my-2"
          v-if="registeredStage == 3"
        >
          <span>용병 찾기 완료</span>
        </v-col>
      </v-row>
      <!-- 용병 찾기 현황 Label 끝-->
      <employ-list
        :registeredStage="registeredStage"
        :appliedStage="appliedStage"
      ></employ-list>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/employ/RegisteredEmploy.js"></script>
