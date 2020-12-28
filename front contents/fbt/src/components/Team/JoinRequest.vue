<template>
  <div class="py-0 my-0 px-0 mx-5">
    <!-- 페이지 라벨 -->
    <div>
      <v-row>
        <span>가입 요청</span>
      </v-row>
      <v-row>
        <v-divider color="white"></v-divider>
      </v-row>
    </div>
    <!-- 페이지 라벨 -->
    <!-- 가입 요청 회원 테이블 -->
    <v-card class="my-5">
      <!-- header -->
      <v-row class="text-center">
        <v-col offset="1" cols="3">이메일</v-col>
        <v-col cols="2">포지션</v-col>
        <v-col cols="2">활동지역</v-col>
        <v-col cols="3">가입수락</v-col>
      </v-row>
      <v-row id="header-border" class="mx-15"></v-row>
      <!-- header 끝 -->
      <!-- Body -->
      <div v-if="!loading && !empty" class="py-1">
        <v-row
          v-for="(member, i) in members"
          :key="i"
          class="text-center table-body"
        >
          <v-col offset="1" cols="3">
            <span class="float-left">
              <v-icon @click="closeUserInfo" v-if="activeUserInfo == i"
                >mdi-chevron-down</v-icon
              >
              <v-icon @click="openUserInfo(member, i)" v-else
                >mdi-chevron-right</v-icon
              >
            </span>
            <span>{{ member.user.email }}</span>
          </v-col>
          <v-col cols="2">{{ member.user.position }}</v-col>
          <v-col cols="2">{{ member.user.area }}</v-col>
          <v-col cols="3">
            <v-btn
              class="mr-2"
              small
              color="#6920A3"
              @click="acceptApplyMember(member)"
            >
              수락
            </v-btn>
            <v-btn small color="#AD1457" @click="refuseApplyMember(member)">
              거절
            </v-btn>
          </v-col>
        </v-row>
        <v-expand-transition>
          <v-row justify="center" v-if="activeUserInfo == i">
            <user-info style="width: 70%" :email="email"></user-info>
          </v-row>
        </v-expand-transition>
        <v-row class="mx-15 border"></v-row>
      </div>
      <!-- Body 끝 -->
      <!-- progress circle -->
      <div v-else-if="loading">
        <progress-circle></progress-circle>
      </div>
      <!-- progress circle 끝 -->
      <!-- empty -->
      <empty :ment="ment" v-else></empty>
      <!-- empty 끝 -->
    </v-card>
    <!-- 가입 요청 회원 테이블 끝 -->
  </div>
</template>

<script>
import ProgressCircle from "@/components/Common/ProgressCircle.vue";
import Empty from "@/components/Common/Empty.vue";
import UserInfo from "@/components/User/UserInfo.vue";

export default {
  name: "join-request",
  components: {
    "progress-circle": ProgressCircle,
    empty: Empty,
    "user-info": UserInfo
  },
  data() {
    return {
      // users vo
      members: [], // 신청한 사람들

      // toggle 관련 변수
      activeUserInfo: null,
      email: null,
      i: -1,

      // 기본변수
      empty: false, // 아무것도 없을 때
      ment: "신청내역이", // 아무것도 없을 때 내보내는 멘트
      loading: false,
      error: false
    };
  },
  mounted() {
    this.showApplyMemberByTeam();
  },
  methods: {
    //FTM05
    showApplyMemberByTeam() {
      this.loading = true;
      this.initBasicVar();
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.$axios
        .get("/team-member/3/" + teamId)
        .then(response => {
          this.members = response.data;
          if (this.members.length == 0) this.empty = true;
        })
        .catch(error => {
          console.log(error);
          this.error = true;
          alert("가입 신청 내역을 불러오는 데 실패했습니다.");
        })
        .finally(() => {
          setTimeout(() => {
            this.loading = false;
          }, 1000);
        });
    },
    //FTM06
    acceptApplyMember(member) {
      this.$axios
        .put("/team-member/2/" + member.teamMemberId)
        .then(() => {
          alert("수락했습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("수락에 실패했습니다.");
        })
        .finally(() => {
          this.showApplyMemberByTeam();
        });
    },
    //FTM07
    refuseApplyMember(member) {
      this.$axios
        .delete("/team-member/1/" + member.teamMemberId)
        .then(() => {
          alert("거절했습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("거절에 실패했습니다.");
        })
        .finally(() => {
          this.showApplyMemberByTeam();
        });
    },
    // 기본변수 초기화
    initBasicVar() {
      this.empty = false;
      this.error = false;
    },
    // ------------- 창 관련 메소드 --------------------//
    openUserInfo(member, i) {
      this.activeUserInfo = i;
      this.email = member.user.email;
      this.i = i;
    },
    closeUserInfo() {
      this.activeUserInfo = null;
      this.email = null;
      this.i = -1;
    }
  }
};
</script>

<style>
#header-border {
  border-bottom: 1px solid white;
}

.border {
  border-bottom: 1px solid gray;
}
</style>
