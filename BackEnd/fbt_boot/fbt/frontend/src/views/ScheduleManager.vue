<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <!-- Page Label -->
          <v-row class="py-0 my-0 px-0 mx-5">
            <v-col cols="12" class="pa-0 ma-0 mb-2">
              <span>팀 일정(관리자)</span>
              <v-divider style="border-color: #C4C4C4 !important;">
                inset
              </v-divider>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <schedule-body :isManager="isManager"></schedule-body>
  </div>
</template>
<script scoped src="@/assets/js/schedule/ScheduleManager.js"></script>
