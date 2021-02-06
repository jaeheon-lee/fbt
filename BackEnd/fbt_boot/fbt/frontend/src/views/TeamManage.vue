<template>
  <v-container fluid class="py-0 my-7 px-0 mx-0">
    <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
        <!-- 가이드 버튼 -->
        <v-row
          justify="center"
          fluid
          align="center"
          class="py-0 my-0 px-0 mx-0"
        >
          <!-- 팀원 관리: page = 1 -->
          <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
            <v-btn
              tile
              dark
              large
              width="100%"
              class="mx-0 px-0"
              :class="{ 'btn-on-purple': page == 1 }"
              @click="page = 1"
              >팀원 관리</v-btn
            >
          </v-col>
          <!-- 가입 요청: page = 2 -->
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
              >가입 요청</v-btn
            >
          </v-col>
        </v-row>
        <v-row
          justify="center"
          fluid
          align="center"
          class="py-0 my-0 px-0 mx-0 mb-7"
        >
          <!-- 팀정보 수정: page = 3 -->
          <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
            <v-btn
              tile
              dark
              large
              width="100%"
              class="mx-0 px-0"
              :class="{ 'btn-on-purple': page == 3 }"
              @click="page = 3"
              >팀정보 수정</v-btn
            >
          </v-col>
          <!-- 팀 해체: page = 4 -->
          <v-col cols="6" class="py-0 my-0 px-0 mx-0" justify="center">
            <v-btn
              tile
              dark
              large
              width="100%"
              class="mx-0 px-0"
              style="border:0px;"
              :class="{ 'btn-on-purple': page == 4 }"
              @click="openDelete"
              >팀 해체</v-btn
            >
          </v-col>
        </v-row>
        <!-- 가이드 버튼 끝 -->
        <member-manage v-if="page == 1"></member-manage>
        <join-request v-if="page == 2"></join-request>
        <update-team-info v-if="page == 3"></update-team-info>
        <v-dialog v-model="deleteDialog">
          <delete-team @cancel-delete="cancelDelete"></delete-team>
        </v-dialog>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import MemberManage from "@/components/Team/MemberManage.vue";
import JoinRequest from "@/components/Team/JoinRequest.vue";
import UpdateTeamInfo from "@/components/Team/UpdateTeamInfo.vue";
import DeleteTeam from "@/components/Team/DeleteTeam.vue";
export default {
  name: "team-manage",
  components: {
    "member-manage": MemberManage,
    "join-request": JoinRequest,
    "update-team-info": UpdateTeamInfo,
    "delete-team": DeleteTeam
  },
  data: () => ({
    page: 1,
    deleteDialog: false
  }),
  methods: {
    openDelete() {
      this.page = 4;
      this.deleteDialog = true;
    },
    cancelDelete() {
      this.deleteDialog = false;
      this.page = 1;
    }
  }
};
</script>
<style scroped src="@/assets/css/common/barButton.css"></style>
