<template>
  <div>
    <v-container fluid class="ma-0 pa-0">
      <!-- 등록 매치 현황 Label -->
      <v-row
        fluid
        justify="center"
        class="ma-0 pa-0 mx-5 my-4"
        v-if="registeredStage < 3"
      >
        여러 인원파악신청에 대해서 수락하시면, 투표를 통해 최소인원 이상 참석이
        가능한 팀이 선착순으로 양도가 완료됩니다.
      </v-row>
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 mb-2"
          v-if="registeredStage == 1"
        >
          <span>양도 중</span>
        </v-col>
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 mb-2"
          v-if="registeredStage == 2"
        >
          <span>인원파악신청</span>
        </v-col>
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 mb-2"
          v-if="registeredStage == 3"
        >
          <span>인원파악중</span>
        </v-col>
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 my-2"
          v-if="registeredStage == 4"
        >
          <span>양도실패</span>
        </v-col>
        <v-col
          xl="6"
          lg="8"
          cols="12"
          class="pa-0 ma-0 my-2"
          v-if="registeredStage == 5"
        >
          <span>양도성공</span>
        </v-col>
      </v-row>
      <!-- 등록 매치 현황 Label 끝-->
      <assign-list
        :registeredStage="registeredStage"
        @change-registered-stage="changeRegisteredStage"
      ></assign-list>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/assign/RegisteredAssign.js"></script>
