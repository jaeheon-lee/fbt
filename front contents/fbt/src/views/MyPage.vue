<template>
  <v-container fluid class="pt-5 pb-0 my-0 px-0 mx-0">
    <v-row justify="center" fluid align="start" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="py-0 my-0 px-0 mx-0">
        <!-- 가이드 버튼 -->
        <v-row
          justify="center"
          fluid
          align="center"
          class="py-0 my-0 px-0 mx-0 mb-7"
        >
          <!-- 내 정보: page = 1 -->
          <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
            <v-btn
              tile
              dark
              large
              width="100%"
              class="mx-0 px-0"
              :class="{ 'btn-on-purple': page == 1 }"
              @click="page = 1"
              >내 정보</v-btn
            >
          </v-col>
          <!-- 가입 팀관리: page = 2 -->
          <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
            <v-btn
              tile
              dark
              large
              width="100%"
              class="mx-0 px-0"
              :class="{ 'btn-on-purple': page == 2 }"
              style="border:0px;"
              @click="page = 2"
              >가입 팀관리</v-btn
            >
          </v-col>
        </v-row>
        <!-- 가이드 버튼 끝 -->
        <user-manage v-if="page == 1"></user-manage>
        <belonged-team-list v-else></belonged-team-list>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import UserManage from "@/components/User/UserManage.vue";
import BelongedTeamList from "@/components/User/BelongedTeamList.vue";

export default {
  name: "my-page",
  components: {
    "user-manage": UserManage,
    "belonged-team-list": BelongedTeamList
  },
  data() {
    return {
      page: 1
    }
  }
}
</script>
<style scroped src="@/assets/css/common/barButton.css"></style>
