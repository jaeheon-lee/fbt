<template>
  <v-app id="inspire">
    <div style="height:100%">
      <v-navigation-drawer v-model="drawer" app>
        <v-card height="64" @click="$router.push({ name: 'home' })">
          <v-list-item height="64" link align-center>
            <v-list-item height="64" justify-center pa-0>
              <v-list-item-avatar>
                <v-img src="./assets/image/손흥민.jpg"></v-img>
              </v-list-item-avatar>
              <v-list-item-content pa-0>
                <v-list-item-title
                  class="title"
                  style="font-family:nexon!important"
                  >손흥민</v-list-item-title
                >
                <v-list-item-subtitle>ggapdol2@gmail.com</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-divider></v-divider>
          </v-list-item>
        </v-card>
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
              @click="$router.push({ name: item.target })"
              style="font-family:nexon"
            >
              <v-list-item-content>
                <v-list-item-title v-text="subItem.title"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
        </v-list>
      </v-navigation-drawer>
      <v-app-bar app color="#3D195B" dark>
        <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title>FootBall Together</v-toolbar-title>
      </v-app-bar>
      <v-main style="background-color:#34373a;height:100%;">
        <router-view></router-view>
      </v-main>

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
            <div col cols="2" class="footerWrap">
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
              @click="$router.push({ name: 'vote' })"
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
    </div>
  </v-app>
</template>
<style scoped src="./assets/css/all.css"></style>
<style scoped src="./assets/css/footer.css"></style>

<script>
export default {
  props: {
    source: String
  },
  data: () => ({
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
    items: [
      {
        action: "sports_soccer",
        target: "team",
        title: "팀 선택",
        items: [
          {
            title: "FC 답십리"
          },
          {
            title: "FC 왕십리"
          },
          { title: "FC 신답" }
        ]
      },
      {
        action: "receipt_long",
        title: "관리자 메뉴",
        target: "managerMenu",
        items: [
          { title: "투표" },
          { title: "매치" },
          { title: "용병" },
          { title: "양도" },
          { title: "일정" },
          { title: "관리" }
        ]
      },
      {
        action: "people_alt",
        target: "allTeamMemberMenu",
        title: "팀 메뉴",
        items: [
          { title: "팀홈" },
          { title: "투표" },
          { title: "일정" },
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
          { title: "개인 전체일정" },
          { title: "팀 찾기" },
          { title: "팀 만들기" },
          { title: "용병" },
          { title: "전체 알림" },
          { title: "개인정보" }
        ]
      }
    ]
  }),
  methods: {}
};
</script>
