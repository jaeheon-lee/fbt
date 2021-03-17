<template>
  <v-container fluid class="ma-0 pa-0">
    <v-row fluid justify="center" class="ma-0 pa-0">
      <v-card width="60%" class="py-5">
        <v-row justify="center">
          <p v-if="search">입금 등 매치하기 위한 모든 사항을 확인했습니까?</p>
          <p v-else>입금 등 양도하기 위한 모든 사항을 확인했습니까?</p>
        </v-row>
        <v-row justify="center">
          <v-btn
            class="mr-7"
            color="#6920A3"
            @click="completeSearch"
            v-if="search"
          >
            예
          </v-btn>
          <v-btn class="mr-7" color="#6920A3" @click="completeAssign" v-else>
            예
          </v-btn>
          <v-btn color="#AD1457" @click="close">
            아니오
          </v-btn>
        </v-row>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "check-complete-apply",
  props: {
    search: Object,
    assign: Object
  },
  data() {
    return {
      selectedAssign: null,
    };
  },
  methods: {
    // 매치 확정하기 (FM!5)
    completeSearch() {
      let teamName = JSON.parse(sessionStorage.getItem("userInfo")).teamName;
      this.$axios
        .put("/search/2?teamName=" + teamName, this.search)
        .then(() => {
          alert("매치가 확정됐습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("매치 확정에 실패했습니다.");
        })
        .finally(() => {
          this.$router.push("scheduleManager");
        });
    },
    // FA16
    completeAssign() {
      console.log(1)
      let teamName = JSON.parse(sessionStorage.getItem("userInfo")).teamName;
      this.$axios
        .put("/assignment/3?teamName=" + teamName, this.assign)
        .then(() => {
          alert("양도가 확정됐습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("양도 확정에 실패했습니다.");
        })
        .finally(() => {
          this.$router.push("scheduleManager");
        });
    },
    // 창 닫기
    close() {
      this.$emit("close");
    }
  }
};
</script>

<style></style>
