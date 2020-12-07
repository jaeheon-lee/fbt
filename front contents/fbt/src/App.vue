<template>
  <v-app id="inspire">
    <div style="height:100%">
      <!-- 네비게이션 -->
      <v-navigation-drawer v-model="drawer" app>
        <login-banner v-if="!userInfo"></login-banner>
        <profile-banner :userInfo="userInfo" v-else></profile-banner>
        <v-list dense>
          <v-list-group
            v-for="item in items"
            :key="item.title"
            :prepend-icon="item.action"
            no-action
            color="#ffffff"
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title v-text="item.title"></v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
              v-for="subItem in item.items"
              :key="subItem.title"
              style="font-family:nexon"
            >
              <v-list-item-content>
                <v-list-item-title
                  v-text="subItem.title"
                  @click="moveOrSet(item, subItem)"
                  :class="teamClass(item, subItem)"
                  style="cursor: pointer;"
                ></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
        </v-list>
      </v-navigation-drawer>
      <!-- 네비게이션 끝 -->
      <!-- 헤더 -->
      <v-app-bar app color="#3D195B" dark>
        <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title>FootBall Together</v-toolbar-title>
      </v-app-bar>
      <v-main style="background-color:#34373a;height:100%;">
        <router-view></router-view>
      </v-main>
      <!-- 헤더 끝 -->
      <!-- 풋터 -->
      <v-footer app class="pa-0 mx-0">
        <v-container pa-0 mx-0 fluid>
          <v-row justify="center" class="ma-0">
            <v-menu top :offset-y="offset">
              <template v-slot:activator="{ on, attrs }">
                <div col cols="2" class="footerWrap" v-bind="attrs" v-on="on">
                  <div class="footerIcon">
                    <img src="./assets/image/알림.svg" />
                  </div>
                  <div class="footerCon">
                    <span>알림</span>
                  </div>
                </div>
              </template>
              <v-list style="width: 100%; max-width: 300px;">
                <v-list-item
                  style=" overflow: hidden;text-overflow: ellipsis; "
                  v-for="(notice, index) in notices"
                  :key="index"
                  @click.stop
                >
                  <v-list-item-title>{{ notice.title }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
            <div
              col
              cols="2"
              class="footerWrap"
              @click="$router.push({ name: 'schedule' })"
            >
              <div class="footerIcon">
                <img src="./assets/image/일정.svg" />
              </div>
              <div class="footerCon">
                <span>일정</span>
              </div>
            </div>
            <div col cols="2" class="footerWrap">
              <div class="footerIcon">
                <img src="./assets/image/홈으로.svg" />
              </div>
              <div class="footerCon">
                <span>팀홈</span>
              </div>
            </div>
            <div
              col
              cols="2"
              class="footerWrap"
              @click="$router.push({ name: 'voteMatch' })"
            >
              <div class="footerIcon">
                <img src="./assets/image/투표.svg" />
              </div>
              <div class="footerCon">
                <span>투표</span>
              </div>
            </div>
          </v-row>
          <v-row
            justify="end"
            class="ma-0 px-0"
            style="background-color:#3D195B;"
          >
            <v-btn v-for="icon in icons" :key="icon" dark icon>
              <v-icon size="20px">{{ icon }}</v-icon>
            </v-btn>
          </v-row>
        </v-container>
      </v-footer>
      <!-- 풋터  끝 -->
    </div>
  </v-app>
</template>
<style scoped src="./assets/css/all.css"></style>
<style scoped src="./assets/css/footer.css"></style>

<script>
import ProfileBanner from "@/components/Main/ProfileBanner.vue";
import LoginBanner from "@/components/Main/LoginBanner.vue";
export default {
  name: "app",
  props: {
    source: String
  },
  components: {
    "profile-banner": ProfileBanner,
    "login-banner": LoginBanner
  },
  data: () => ({
    // 로그인 관련 변수
    userInfo: null,
    icons: ["mdi-facebook", "mdi-twitter", "mdi-linkedin", "mdi-instagram"],
    drawer: null,
    user: null,
    notices: [
      {
        title:
          "Click MeClick MeClick MeClick MeClick MeClick MeClick MeClick MeClick Me"
      },
      { title: "Click Me" },
      { title: "Click Me" },
      { title: "Click Me 2" }
    ],
    offset: true,
    // 네이게이션 관련 변수
    items: [
      {
        action: "sports_soccer",
        target: "team",
        title: "팀 선택",
        items: []
      },
      {
        action: "receipt_long",
        title: "관리자 메뉴",
        target: "managerMenu",
        items: [
          { title: "투표", target: "voteMatchManager" },
          { title: "매치", target: "search" },
          { title: "용병", target: "employManager" },
          { title: "양도", target: "assign" },
          { title: "일정", target: "scheduleManager" },
          { title: "관리", target: "teamManage" }
        ]
      },
      {
        action: "people_alt",
        target: "allTeamMemberMenu",
        title: "팀 메뉴",
        items: [
          { title: "팀홈" },
          { title: "투표", target: "voteMatch" },
          { title: "일정", target: "schedule" },
          { title: "활동" },
          { title: "알림" }
        ]
      },
      {
        action: "face",
        title: "개인 메뉴",
        target: "privateMenu",
        items: [
          { title: "홈" },
          { title: "개인 전체일정", target: "scheduleUser" },
          { title: "팀 찾기" },
          { title: "팀 만들기" },
          { title: "용병", target: "employ" },
          { title: "전체 알림" },
          { title: "개인정보" }
        ]
      }
    ]
  }),
  mounted() {
    let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
    if (userInfo) {
      // SessionStorage로부터 userInfo 담기
      this.userInfo = userInfo;
      // 팀 목록 삽입
      let teams = this.userInfo.teams;
      if (teams) {
        for (let i = 0; i < teams.length; i++) {
          this.items[0].items.push({
            title: teams[i].teamName,
            teamId: teams[i].teamId,
            teamMemberId: teams[i].teamMemberId,
            nickName: teams[i].nickName,
            index: i
          });
        }
        // 팀 선택 기본 값: 첫 번째
        this.userInfo.teamId = teams[0].teamId;
        this.userInfo.teamMemberId = teams[0].teamMemberId;
        this.userInfo.nickName = teams[0].nickName;
        this.userInfo.teamName = teams[0].teamName;
        sessionStorage.setItem("userInfo", JSON.stringify(this.userInfo));
      }
    } else {
      this.userInfo = null;
    }
  },
  methods: {
    // 페이지 이동인지 팀세팅인지 가이드하는 메소드
    moveOrSet(item, subItem) {
      if (item.target == "team") {
        this.setTeam(subItem);
      } else {
        this.movePage(subItem);
      }
    },
    // 페이지 이동 메소드
    movePage(subItem) {
      this.$router.push({ name: subItem.target });
    },
    // 팀 세팅 메소드
    setTeam(subItem) {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      userInfo.teamId = subItem.teamId;
      userInfo.teamMemberId = subItem.teamMemberId;
      userInfo.nickName = subItem.nickName;
      userInfo.teamName = subItem.title;
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
      this.$router.push("/").catch(error => {
        if (error.name == "NavigationDuplicated") {
          location.reload();
        }
      });
    },
    // 팀 선택 시 클래스 바인딩
    teamClass(item, subItem) {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      if (userInfo != null) {
        if (item.target == "team" && subItem.teamId == userInfo.teamId) {
          return "teamChosen";
        }
      }
    }
  }
};
</script>
<style scoped>
.teamChosen {
  opacity: 0.5;
}
</style>
