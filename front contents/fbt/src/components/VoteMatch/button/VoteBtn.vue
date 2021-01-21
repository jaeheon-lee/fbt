<template>
  <v-row class="justify-center" v-if="controlAttendBtn(vote)">
    <v-btn
      small
      width="35px"
      color="#AD1457"
      class="mr-1"
      @click.stop="doVote(vote, 1)"
      >참 석</v-btn
    >
    <v-btn
      small
      width="35px"
      color="rgba(235, 255, 0,0.7)"
      class="ml-1"
      @click.stop="doVote(vote, 0)"
      >불참석</v-btn
    >
  </v-row>
</template>

<script>
export default {
  name: "vote-btn",
  props: {
    vote: Object
  },
  data() {
    return {}
  },
  methods: {
    // 투표 참불 버튼 관리
    controlAttendBtn(vote) {
      //1. 투표 마감날이 지나면 표시 하지 않는다.
      let today = new Date();
      today = this.$moment(today).format("YYYY-MM-DD HH:mm:ss");
      if (vote.dueDate < today) return false;

      //2. 투표가 마감 되면 표시 하지 않는다.
      if (vote.voteStatus == 1) return false;
      return true;
    },
    // V03
    doVote(vote, result) {
      this.$emit("do-vote", vote, result);
    }
  }
};
</script>

<style></style>
