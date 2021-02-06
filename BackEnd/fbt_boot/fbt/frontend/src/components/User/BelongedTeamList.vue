<template>
  <div class="py-0 my-0 px-0 mx-5">
    <!-- 페이지 라벨 -->
    <div>
      <v-row>
        <span>가입 팀리스트</span>
      </v-row>
      <v-row>
        <v-divider color="white"></v-divider>
      </v-row>
    </div>
    <!-- 페이지 라벨 -->
    <!-- 팀리스트 -->
    <v-card class="my-5">
      <!-- header -->
      <v-row class="text-center">
        <v-col offset="2" cols="4">팀명</v-col>
        <v-col cols="2">탈퇴</v-col>
      </v-row>
      <v-row id="header-border" class="mx-15"></v-row>
      <!-- header 끝 -->
      <!-- body -->
      <div v-for="(team, i) in teams" :key="i" class="text-center">
        <v-row>
          <v-col offset="2" cols="4" class="mt-2">{{ team.teamName }}</v-col>
          <v-col cols="2" class="mt-1">
            <v-btn color="#AD1457" @click="activePassCheck = true">탈퇴</v-btn>
          </v-col>
          <!-- 비밀번호 입력창 -->
          <v-dialog v-model="activePassCheck">
            <v-card>
              <v-card-title class="justify-center">
                <span>정말 탈퇴하겠습니까?</span>
              </v-card-title>
              <v-card-text>
                <v-row justify="center">
                  <v-btn
                    class="mt-3 mr-3"
                    color="#6920A3"
                    @click="quitTeam(team)"
                    >예</v-btn
                  >
                  <v-btn
                    class="mt-3"
                    color="#AD1457"
                    @click="activePassCheck = false"
                  >
                    아니오
                  </v-btn>
                </v-row>
              </v-card-text>
            </v-card>
          </v-dialog>
          <!-- 비밀번호 입력창 끝 -->
        </v-row>
        <v-row class="mx-15">
          <v-divider></v-divider>
        </v-row>
      </div>
      <!-- body 끝 -->
    </v-card>
    <!-- 팀리스트 끝 -->
  </div>
</template>

<script>
export default {
  name: "belonged-team-list",
  data() {
    return {
      teams: [],
      email: null,
      pass: null,

      activePassCheck: false
    };
  },
  mounted() {
    this.showBelongedTeam();
  },
  methods: {
    showBelongedTeam() {
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      this.$axios
        .get("/user/6/" + email)
        .then(response => {
          this.teams = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    quitTeam(team) {
      let teamId = team.teamId;
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      let teamMemberId = teamId + "-" + email;
      this.$axios
        .delete(
          "/team-member/1/" +
            teamMemberId +
            "?email=" +
            email +
            "&pass=" +
            this.pass
        )
        .then(() => {
          alert("탈퇴했습니다. 다시 로그인해주세요");
          sessionStorage.removeItem("userInfo");
          location.href = this.$http + "/loginSignin";
        })
        .catch(error => {
          console.log(error);
          alert("탈퇴에 실패했습니다.");
        });
    }
  }
};
</script>

<style scoped>
#header-border {
  border-bottom: 1px solid white;
}
</style>
