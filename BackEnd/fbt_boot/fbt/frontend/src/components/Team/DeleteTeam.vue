<template>
  <v-container fluid class="ma-0 pa-0">
    <v-card>
      <!-- 구단주일 때 -->
      <div v-if="isOwner">
        <v-card-title class="justify-center">
          팀을 해체하시겠습니까?
        </v-card-title>
        <v-card-text>
          <v-row justify="center">
            <v-btn color="#6920A3" class="mr-8" @click="deleteTeam">
              예
            </v-btn>
            <v-btn color="#AD1457" @click="cancelDelete">
              아니오
            </v-btn>
          </v-row>
        </v-card-text>
      </div>
      <!-- 구단주일 때 끝 -->
      <!-- 구단주일 아닐 때 -->
      <v-card-text v-else class="py-5">
        <v-row justify="center" class="mb-5">
          팀 해체는 구단주의 권한입니다.
        </v-row>
        <v-row justify="center">
          <v-btn color="#6920A3" class="mr-8" @click="cancelDelete">
            돌아가기
          </v-btn>
        </v-row>
      </v-card-text>
      <!-- 구단주일 아닐 때 끝 -->
    </v-card>
  </v-container>
</template>

<script>
export default {
  name: "delete-team",
  data() {
    return {
      isOwner: false
    }
  },
  mounted() {
    this.checkOwner();
  },
  methods: {
    deleteTeam() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.$axios
        .delete("/team/1/" + teamId)
        .then(() => {
          sessionStorage.removeItem("userInfo");
          location.href = this.$http + "/loginSignin";
        })
        .catch(error => {
          console.log(error);
          alert("팀 삭제에 실패했습니다.");
        });
    },
    cancelDelete() {
      this.$emit("cancel-delete");
    },
    checkOwner() {
      let memberLevel = JSON.parse(sessionStorage.getItem("userInfo")).memberLevel;
      if (memberLevel < 4) this.isOwner = false;
      else this.isOwner = true;
    }
  }
}
</script>

<style>

</style>