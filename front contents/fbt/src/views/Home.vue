<template>
  <div>
    <v-container fluid class="pt-5 pb-0 my-0 px-0 mx-0">
      <v-row justify="center" fluid align="start" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="py-0 my-0 px-0 mx-0">
          <v-row>
            <!-- 개인 프로필 -->
            <v-card
              class="mx-auto"
              width="100%"
              style="background-color:#AD1457;"
            >
              <v-row justify="center">
                <v-col cols="5" style="text-align:center;">
                  <v-avatar size="106px">
                    <v-img
                      :src="getImageUrl()"
                      alt="개인프로필"
                      v-if="isImage"
                    />
                    <v-icon style="font-size: 6em" v-else>
                      mdi-account-circle
                    </v-icon>
                  </v-avatar>
                </v-col>
                <v-col cols="6" style="font-size:15px" px-0>
                  <v-card-title class="pa-1">{{ getUserName }}</v-card-title>
                  <v-card-text class="text--primary pa-1">
                    <div>
                      주 포지션:
                      <span>LFW</span>
                      <span>,</span>
                      <span>LFW</span>
                    </div>
                    <div>주발: Left</div>
                    <div>가입일: 2020/09/01</div>
                  </v-card-text>
                </v-col>
              </v-row>
            </v-card>
            <!-- 개인 프로필 끝 -->
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <teamCardComponent></teamCardComponent>
    <searchTeamComponent></searchTeamComponent>
    <teamCreateButtonComponent></teamCreateButtonComponent>
  </div>
</template>
<style scoped src="../assets/css/all.css"></style>

<script>
import teamCardComponent from "../components/TeamCardComponent";
import searchTeamComponent from "../components/SearchTeamComponent";
import teamCreateButtonComponent from "../components/TeamCreateButtonComponent";

export default {
  name: "user-home",
  components: {
    teamCardComponent,
    searchTeamComponent,
    teamCreateButtonComponent
  },
  data: () => ({
    userInfo: null
  }),
  created() {
    this.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
  },
  computed: {
    isImage: function () {
      if (this.userInfo.image) return true;
      else return false;
    },
    getUserName: function() {
      return this.userInfo.name;
    }
  },
  methods: {
    getImageUrl() {
      return require("@/assets/image/user/" + this.imgUrl);
    }
  }
};
</script>
