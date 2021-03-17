<template>
  <!-- 대기하기, 참석취소 버튼 -->
  <v-row class="justify-center">
    <v-btn
      small
      width="35px"
      color="#AD1457"
      class="mr-1"
      v-if="controlWaitBtn(vote)"
      @click.stop="doWait(vote)"
      >대기하기</v-btn
    >
    <v-btn
      small
      width="75px"
      color="#AD1457"
      class="mr-1"
      v-else
      @click.stop="cancelAttend()"
      >참석취소하기</v-btn
    >
  </v-row>
</template>

<script>
export default {
  name: "wait-cancel-btn",
  props: {
    vote: Object,
    awayVote: Object
  },
  data() {
    return {};
  },
  methods: {
    // 대기, 참석취소 버튼 관리
    /**
     * 대기버튼 출력 조건
     * 1) 자기 팀의 entry에 없다
     */
    controlWaitBtn() {
      if (!this.vote || !this.awayVote) return false;
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo")).teamMemberId;
      let entries = this.findMyTeamEntries();
      for (let i=0;i<entries.length;i++) {
        if (entries[i].teamMember && entries[i].teamMember.teamMemberId == teamMemberId) {
          return false;
        }
      }
      return true;
    },
    findMyTeamEntries() {
      if (!this.awayVote) return this.vote.matchSchedule.entries;
      let myTeamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let homeTeamId = this.vote.matchSchedule.homeTeam.teamId;
      if (myTeamId == homeTeamId) return this.vote.matchSchedule.entries;
      else return this.awayVote.matchSchedule.entries;
    },

    doWait(vote) {
      let entry = this.setEntry(vote);
      this.$axios
        .post("/match-schedule/4", entry)
        .then(() => {
          alert("대기가 완료됐습니다.");
        })
        .catch(() => {
          alert("대기에 실패했습니다.");
        })
        .finally(() => {
          this.$emit("refresh");
        })
    },
    setEntry(vote) {
      let matchSchedule = vote.matchSchedule;
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      let entry = {
        teamMember: {
          teamMemberId: userInfo.teamMemberId
        },
        matchSchedule: {
          matchScheduleId: matchSchedule.matchScheduleId,
        },
        team: {
          teamId: userInfo.teamId
        },
        type: 3
      }
      return entry;
    },
    cancelAttend() {
      let entries = this.findMyTeamEntries();
      let myEntryId = this.findMyEntry(entries);
      this.$axios
        .delete("/entry/" + myEntryId)
        .then(() => {
          alert("참석을 취석했습니다.");
        })
        .catch(() => {
          alert("참석취소에 실패했습니다.");
        })
        .finally(() => {
          this.$emit("refresh");
        })
    },
    findMyEntry(entries) {
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo")).teamMemberId;
      for(let i=0;i<entries.length;i++) {
        if(entries[i].teamMember && entries[i].teamMember.teamMemberId == teamMemberId) {
          return entries[i].entryId;
        }
      }
    }
  }
};
</script>

<style></style>
