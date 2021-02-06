<template>
  <!-- 대기하기, 참석취소 버튼 -->
  <v-row class="justify-center">
    <v-btn
      small
      width="35px"
      color="#AD1457"
      class="mr-1"
      v-if="controlWaitBtn(vote)"
      @click.stop="doVote(vote, 2)"
      >대기하기</v-btn
    >
    <v-btn
      small
      width="75px"
      color="#AD1457"
      class="mr-1"
      v-else
      @click.stop="cancelAttend(vote, 0)"
      >참석취소하기</v-btn
    >
  </v-row>
</template>

<script>
export default {
  name: "wait-cancel-btn",
  props: {
    vote: Object
  },
  data() {
    return {};
  },
  methods: {
    // 대기, 참석취소 버튼 관리
    controlWaitBtn(vote) {
      // 투표한 사람이 아무도 없으면 대기버튼
      if (!vote.voteMatchResults) return true;
      //2. 다음 로직 따른다
      // eslint-disable-next-line prettier/prettier
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo")).teamMemberId;
      // 로그인한 사람이 투표했는지, 했으면 해당 인덱스를 안 했으면 -1 리턴
      let index = -1;
      if (vote.voteMatchResults.length > 0) {
        index = vote.voteMatchResults
          .map(x => x.teamMember.teamMemberId)
          .indexOf(teamMemberId);
      }
      // 참석을 눌렀는지
      let attendance = -1;
      if (index != -1) attendance = vote.voteMatchResults[index].attendance;
      if (vote.voteMatchSetting.waiting == true && attendance != 1) {
        // 대기 허용이고 참석을 누르지 않았다면 대기 버튼 출력
        return true;
      } else {
        // 대기 불용이거나 참석을 누르지 않았다면 대기 버튼 숨기기
        return false;
      }
    },
    doVote(vote, result) {
      this.$emit("do-vote", vote, result);
    },
    cancelAttend(vote, result) {
      this.$emit("update-vote", vote, result);
    }
  }
};
</script>

<style></style>
