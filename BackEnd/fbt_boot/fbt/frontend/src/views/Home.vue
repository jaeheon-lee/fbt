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
                  <v-menu offset-x>
                    <template v-slot:activator="{ on }">
                      <v-avatar size="106px" v-on="on" style="cursor:pointer;">
                        <v-img
                          :src="getImageUrl()"
                          alt="개인프로필"
                          v-if="isImage"
                        />
                        <v-icon style="font-size: 6em" v-else>
                          mdi-account-circle
                        </v-icon>
                      </v-avatar>
                      <v-icon style="margin-top:25%">mdi-camera-enhance</v-icon>
                    </template>
                    <v-btn @click="changeImg">
                      이미지 바꾸기
                    </v-btn>
                    <v-file-input
                      id="fileInput"
                      v-model="imageFile"
                      @change="uploadImg"
                      style="display: none;"
                    ></v-file-input>
                  </v-menu>
                </v-col>
                <v-col cols="6" style="font-size:15px" px-0>
                  <v-card-title class="pa-1">{{ getUserName }}</v-card-title>
                  <v-card-text class="text--primary pa-1">
                    <div>
                      주 포지션:
                      <span>{{ user.position }}</span>
                    </div>
                    <div>주발: {{ user.mainFoot }}</div>
                    <div>가입일: {{ user.userRegDate | filterRegDate }}</div>
                  </v-card-text>
                </v-col>
              </v-row>
            </v-card>
            <!-- 개인 프로필 끝 -->
            <!-- 팀 케라셀 -->
            <teamCardComponent></teamCardComponent>
            <!-- 팀 케라셀 끝 -->
            <!-- 팀찾기 -->
            <v-btn
              width="100%"
              color="#6920A3"
              @click="moveToSearchTeam"
              class="mb-5"
            >
              팀 찾기
            </v-btn>
            <!-- 팀찾기 끝 -->
            <!-- 팀만들기 -->
            <v-btn
              width="100%"
              color="#ebff0080"
              @click="moveToTeamInsert"
              class="mb-5"
            >
              팀 만들기
            </v-btn>
            <!-- 팀만들기 끝 -->
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<style scoped src="../assets/css/all.css"></style>

<script>
import teamCardComponent from "@/components/User/TeamCardComponent.vue";

export default {
  name: "user-home",
  components: {
    teamCardComponent
  },
  data: () => ({
    userInfo: null,
    user: {},

    //이미지
    imageFile: null
  }),
  created() {
    this.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
    this.showUserInfo();
  },
  computed: {
    isImage: function() {
      if (this.user.image) return true;
      else return false;
    },
    getUserName: function() {
      return this.userInfo.name;
    }
  },
  methods: {
    //FU01
    showUserInfo() {
      this.$axios
        .get("/users/4/" + this.userInfo.email)
        .then(response => {
          this.user = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    //FU07
    changeImg() {
      document.getElementById("fileInput").click();
    },
    //FU07
    uploadImg() {
      let file = new FormData();
      file.append(
        "user",
        new Blob([JSON.stringify(this.user)], { type: "application/json" })
      );
      file.append("image", this.imageFile);
      this.$axios
        .put("/team-member/3", file)
        .then(response => {
          alert("이미지를 변경했습니다.");
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
          userInfo.image = response.data;
          sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
          location.reload();
        })
        .catch(error => {
          console.log(error);
          alert("이미지 변경에 실패했습니다.");
        });
    },
    getImageUrl() {
      return this.$user + this.user.image;
    },
    moveToSearchTeam() {
      this.$router.push({
        name: "searchTeam"
      });
    },
    moveToTeamInsert() {
      this.$router.push({
        name: "teamInsert"
      });
    }
  },
  filters: {
    filterRegDate(value) {
      if (value) return value.split(" ")[0];
    }
  }
};
</script>
