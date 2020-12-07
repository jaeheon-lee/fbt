<template>
  <div class="py-0 my-0 px-0 mx-5">
    <!-- 페이지 라벨 -->
    <div>
      <v-row>
        <span>팀원 관리</span>
      </v-row>
      <v-row>
        <v-divider color="white"></v-divider>
      </v-row>
    </div>
    <!-- 페이지 라벨 -->
    <!-- 회원테이블 -->
    <div>
      <v-data-table></v-data-table>
    </div>
    <!-- 회원테이블 끝 -->
  </div>
</template>

<script>
export default {
  name: "member-manage",
  data: () => ({
    // 팀원 목록
    teamMembers: []
  }),
  mounted() {
    this.getTeamMember();
  },
  methods: {
    getTeamMember() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      console.log("id: " + teamId);
      this.$axios
        .get("/team-member/2/" + teamId)
        .then(response => {
          this.teamMembers = response.data;
          console.log(this.teamMembers);
        })
        .catch(error => {
          console.log(error);
          alert("팀원 목록 불러오기에 실패했습니다.");
        });
    }
  }
}
</script>