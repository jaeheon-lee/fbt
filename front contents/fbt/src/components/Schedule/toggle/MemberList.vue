<template>
  <div>
    <!-- 명단보기 버튼 -->
    <v-row
      id="vote-btn"
      class="mx-0 mt-0 pt-0"
      style="color:rgba(235, 255, 0,0.7)"
    >
      <v-col class="float-left">
        <span @click="controlHomeMemberList(i)" style="cursor:pointer;"
          >홈팀 투표 명단 보기</span
        >
      </v-col>
      <v-col>
        <span
          class="float-right"
          style="cursor:pointer;"
          @click="controlAwayMemberList(i)"
          v-if="vote.matchSchedule.awayTeam"
          >어웨이팀 투표 명단 보기</span
        >
      </v-col>
    </v-row>
    <!-- 지인 | 명단보기 버튼 끝 -->
    <!-- 홈팀 명단 -->
    <v-expand-transition>
      <div v-if="activeHomeMemberList == i && openType == 0">
        <v-divider></v-divider>
        <!-- 명단 라벨 -->
        <v-row justify="center" class="mt-3">[홈팀 투표 명단]</v-row>
        <!--투표 인원 내용-->
        <v-row class="justify-space-between mx-5 mt-4">
          <div>총인원: {{ vote.totalNum }}명</div>
          <div>투표인원: {{ vote.votedNum }}명</div>
          <div>불참: {{ vote.abscentNum }}명</div>
          <div>참여: {{ vote.attendNum }}명</div>
        </v-row>
        <v-row justify="end" class="mx-5 my-3">
          <div>지인: {{ vote.friendNum }}명</div>
        </v-row>
        <v-divider></v-divider>
        <v-row justify="end" class="mx-5 mt-3 mb-5">
          <div>경기참석 인원: {{ vote.totalAttend }}명</div>
        </v-row>
        <!--투표 인원 내용 끝-->
        <v-row class="text-center mx-5">
          <v-col offset="2" cols="5">
            닉네임/이메일
          </v-col>
          <v-col cols="2">
            참/불
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-row
          v-for="(result, i) in vote.voteMatchResults"
          :key="i"
          class="text-center mx-5"
        >
          <v-col offset="2" cols="5">
            {{ showNickEmail(result) }}
          </v-col>
          <v-col cols="2">
            {{ result.attendance | attendanceFliter }}
          </v-col>
        </v-row>
      </div>
    </v-expand-transition>
    <!-- 홈팀 명단 끝 -->
    <!-- 어웨이팀 명단 -->
    <v-expand-transition>
      <div v-if="activeAwayMemberList == i && openType == 1">
        <v-divider></v-divider>
        <!-- 명단 라벨 -->
        <v-row justify="center" class="mt-3">[어웨이팀 투표 명단]</v-row>
        <!--투표 인원 내용-->
        <v-row class="justify-space-between mx-5 mt-4">
          <div>총인원: {{ awayVote.totalNum }}명</div>
          <div>투표인원: {{ awayVote.votedNum }}명</div>
          <div>불참: {{ awayVote.abscentNum }}명</div>
          <div>참여: {{ awayVote.attendNum }}명</div>
        </v-row>
        <v-row justify="end" class="mx-5 my-3">
          <div>지인: {{ awayVote.friendNum }}명</div>
        </v-row>
        <v-divider></v-divider>
        <v-row justify="end" class="mx-5 mt-3 mb-5">
          <div>경기참석 인원: {{ awayVote.totalAttend }}명</div>
        </v-row>
        <!--투표 인원 내용 끝-->
        <v-row class="text-center mx-5">
          <v-col offset="2" cols="5">
            닉네임/이메일
          </v-col>
          <v-col cols="2">
            참/불
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-row
          v-for="(result, i) in awayVote.voteMatchResults"
          :key="i"
          class="text-center mx-5"
        >
          <v-col offset="2" cols="5">
            {{ showNickEmail(result) }}
          </v-col>
          <v-col cols="2">
            {{ result.attendance | attendanceFliter }}
          </v-col>
        </v-row>
      </div>
    </v-expand-transition>
    <!-- 어웨이팀 명단 끝 -->
  </div>
</template>

<script>
export default {
  name: "member-list",
  props: {
    vote: Object,
    i: Number,
    awayVote: Object
  },
  data() {
    return {
      // 지인 초대 관련 변수
      activeHomeMemberList: null,
      activeAwayMemberList: null,
      openType: null
    };
  },
  computed: {},
  methods: {
    // 지인이면 이메일, 팀원이면 닉네임으로 출력하기
    showNickEmail(result) {
      if (result.teamMember) {
        // 팀원이면
        return result.teamMember.nickName;
      } else {
        //지인이면
        return result.user.email;
      }
    },
    // 홈팀명단보기 창 여닫기
    controlHomeMemberList(i) {
      if (this.activeHomeMemberList == i) this.activeHomeMemberList = null;
      // 명단이 열려있다면 명단 닫기
      else {
        if (this.activeAwayMemberList == i) this.activeAwayMemberList = null; // 명단도 열려 있다면 명단 닫기
        this.activeHomeMemberList = i;
        this.openType = 0;
      }
    },
    // 어웨팀명단보기 창 여닫기
    controlAwayMemberList(i) {
      if (this.activeAwayMemberList == i) this.activeAwayMemberList = null;
      // 초대가 열려있다면 초대 닫기
      else {
        if (this.activeHomeMemberList == i) this.activeHomeMemberList = null; // 명단도 열려 있다면 명단 닫기
        this.activeAwayMemberList = i;
        this.openType = 1;
      }
    },
  },
  filters: {
    attendanceFliter(value) {
      if (value == 1) {
        //참이라면
        return "참";
      } else if (value == 0) {
        // 불이라면
        return "불";
      } else {
        return "대기";
      }
    },
  }
};
</script>

<style></style>
