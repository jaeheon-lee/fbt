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
    <v-card class="my-5">
      <!-- header -->
      <v-row class="text-center">
        <v-col offset="2" cols="2">닉네임</v-col>
        <v-col cols="2">등급</v-col>
        <v-col cols="2">강퇴</v-col>
        <v-col cols="2">권한 설정</v-col>
      </v-row>
      <v-row id="header-border" class="mx-15"></v-row>
      <!-- header 끝 -->
      <!-- body -->
      <div v-for="(member, i) in teamMembers" :key="i" class="text-center">
        <v-row>
          <v-col offset="2" cols="2" class="mt-1">{{ member.nickName }}</v-col>
          <v-col cols="2" class="mt-1">{{
            member.memberLevel | levelToString
          }}</v-col>
          <v-col cols="2">
            <v-btn
              color="#AD1457"
              small
              v-if="controlExpelBtn(member)"
              @click="expelTeamMember(member)"
            >
              강퇴
            </v-btn>
          </v-col>
          <v-col cols="2">
            <v-menu
              :close-on-click="true"
              :close-on-content-click="true"
              :offset-x="true"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="#6920A3"
                  small
                  v-bind="attrs"
                  v-on="on"
                  v-if="controlAuthBtn(member)"
                >
                  권한설정
                </v-btn>
              </template>
              <v-list>
                <v-list-item v-for="(auth, j) in auths" :key="j">
                  <span
                    class="auth-menu"
                    v-if="controlAuthMenu(j)"
                    @mouseover="menuHover(j)"
                    @click="changeMemberLevel(member, j)"
                    :class="{ hover: isHover == j }"
                    >{{ auth }}</span
                  >
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>
        </v-row>
        <v-row class="mx-15">
          <v-divider></v-divider>
        </v-row>
      </div>
      <!-- body 끝 -->
    </v-card>
    <!-- 회원테이블 끝 -->
  </div>
</template>

<script>
export default {
  name: "member-manage",
  data: () => ({
    // 팀원 목록
    teamMembers: [],

    // 권한 목록
    auths: ["일반 회원", "부매니저", "매니저", "감독", "구단주"],
    isHover: null
  }),
  mounted() {
    this.getTeamMember();
  },
  methods: {
    // FTM01
    getTeamMember() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.$axios
        .get("/team-member/2/" + teamId)
        .then(response => {
          this.teamMembers = response.data;
        })
        .catch(error => {
          console.log(error);
          alert("팀원 목록 불러오기에 실패했습니다.");
        });
    },
    // FTM03
    changeMemberLevel(member, j) {
      let teamMembers = [];
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      let teamMember1 = {
        teamMemberId: userInfo.teamMemberId,
        memberLevel: userInfo.memberLevel
      };
      teamMembers.push(teamMember1);
      let teamMember2 = {
        teamMemberId: member.teamMemberId,
        memberLevel: j
      };
      teamMembers.push(teamMember2);
      this.$axios
        .put("/team-member/1", teamMembers)
        .then(response => {
          let result = response.data;
          if (result == 0) {
            alert("등급 수정이 완료됐습니다");
          } else {
            alert("이양이 완료됐습니다. 다시 로그인해주세요");
            //로그아웃
            sessionStorage.removeItem("userInfo");
            //리다이렉트
            window.location.href = this.$http + "/loginSignin";
          }
        })
        .catch(error => {
          console.log(error);
          alert("등급 수정에 실패했습니다.");
        })
        .finally(() => {
          this.getTeamMember();
        });
    },
    //FTM04
    expelTeamMember(member) {
      this.$axios
        .delete("/team-member/1/" + member.teamMemberId)
        .then(() => {
          alert("강퇴가 완료됐습니다.")
        })
        .catch(error => {
          console.log(error);
          alert("강퇴에 실패했습니다.")
        })
        .finally(() => {
          this.getTeamMember();
        })
    },
    // ------------- 버튼 컨트롤 메소드 ---------- //
    // 강퇴 버튼
    controlExpelBtn(member) {
      // eslint-disable-next-line prettier/prettier
      let loginMemberLever = parseInt(JSON.parse(sessionStorage.getItem("userInfo")).memberLevel);
      // 당사자보다 낮으면 강퇴 권한이 없다
      if (loginMemberLever <= member.memberLevel) return false;
      // 등급이 매니저 미만이면 권한이 없다.
      if (loginMemberLever < 2) return false;
      return true;
    },

    // 권한 버튼
    controlAuthBtn(member) {
      let loginMemberLever = parseInt(
        JSON.parse(sessionStorage.getItem("userInfo")).memberLevel
      );
      // 등급이 매니저 미만이면 권한이 없다
      if (loginMemberLever < 2) return false;
      // 자신보다 같거나 높은 등급의 사람에 대한 권한 없다
      if (loginMemberLever <= member.memberLevel) return false;
      return true;
    },
    // 권한 메뉴
    controlAuthMenu(j) {
      // eslint-disable-next-line prettier/prettier
      let loginMemberLever = parseInt(JSON.parse(sessionStorage.getItem("userInfo")).memberLevel);
      // 매니저의 경우 이양만 가능
      if (loginMemberLever == 2) {
        if (j > 2) return false;
      }
      // 자신의 등급 초과는 설정 불가능
      if (loginMemberLever < j) return false;
      return true;
    },
    // ------------- css 컨트롤 메소드 ----------------------//
    menuHover(j) {
      this.isHover = j;
    }
  },
  filters: {
    levelToString(value) {
      switch (value) {
        case 0:
          return "일반 회원";
        case 1:
          return "부매니저";
        case 2:
          return "매니저";
        case 3:
          return "감독";
        case 4:
          return "구단주";
      }
    }
  }
};
</script>
<style scoped>
#header-border {
  border-bottom: 1px solid white;
}

.auth-menu {
  cursor: pointer;
  border-bottom: 1px solid gray;
}

.hover {
  opacity: 0.5;
}
</style>
