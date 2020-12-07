<template>
  <v-container fluid class="py-0 my-7 px-0 mx-0">
    <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
        <!-- Page Label -->
        <v-row class="py-0 my-0 px-0 mx-5">
          <v-col cols="12" class="pa-0 ma-0 mb-2">
            <span>개인 일정</span>
            <v-divider style="border-color: #C4C4C4 !important;">
              inset
            </v-divider>
          </v-col>
        </v-row>
        <!-- Page Label 끝 -->
      </v-col>
    </v-row>
    <!-- 스케줄 바디 -->
    <schedule-body :isUser="true"></schedule-body>
    <!-- 스케줄 바디 끝 -->
  </v-container>
</template>

<script>
import ScheduleBody from "@/components/Schedule/ScheduleBody.vue";
export default {
  name: "schedule-user",
  components: {
    "schedule-body": ScheduleBody
  },
  data: () => ({})
};
</script>

<style></style>
