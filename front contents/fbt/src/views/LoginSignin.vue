<template>
  <v-container>
    <v-row justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="py-0 my-0 px-0 mx-0">
        <!-- FBT 로고 -->
        <v-row class="mt-8">
          <v-col id="logo" offset="2" cols="8" class="text-center">
            FOOTBALLTOGETHER
          </v-col>
        </v-row>
        <!-- FBT 로고 끝 -->
        <!-- FBT 슬로건 -->
        <v-row class="mt-2">
          <v-col id="slogan" offset="2" cols="8" class="text-center">
            EASY TO MANAGE. HAPPY TO PLAY
          </v-col>
        </v-row>
        <!-- FBT 슬로건 끝 -->
        <!-- 로그인 창 -->
        <div v-if="isLogin">
          <!-- 로그인 버튼 -->
          <div class="mt-10">
            <v-row justify="center" class="mt-2">
              <v-btn
                width="35%"
                color="#6820A3"
                elevation="3"
                @click="dialogLogin = true"
              >
                이메일로 로그인
              </v-btn>
            </v-row>
            <v-row justify="center" class="mt-2">
              <v-btn
                width="35%"
                color="#FEE500"
                class="black--text"
                elevation="3"
                @click="kakaoLogin"
              >
                카카오 아이디로 로그인
              </v-btn>
            </v-row>
            <v-row justify="center" class="mt-2">
              <div id="naverIdLogin" style="display:none;"></div>
              <v-btn
                width="35%"
                color="#1EC800"
                elevation="3"
                @click="naverLogin"
              >
                네이버 아이디로 로그인
              </v-btn>
            </v-row>
          </div>
          <!-- 로그인 버튼 끝 -->
        </div>
        <!-- 이메일 로그인 dialog -->
        <v-dialog v-model="dialogLogin">
          <v-card>
            <v-card-title class="headline">
              <v-row>
                <v-col>
                  <span>이메일과 비밀번호를 입력해주세요</span>
                  <span style="float:right;">
                    <v-icon
                      class="pb-1"
                      style="cursor:pointer;"
                      @click="dialogLogin = false"
                    >
                      close
                    </v-icon>
                  </span>
                </v-col>
              </v-row>
            </v-card-title>
            <v-card-text>
              <v-row justify="center" class="mt-3 mb-5">
                <span style="width:50%">
                  <v-text-field
                    dense
                    hide-details
                    label="e-mail"
                    v-model="email"
                    autofocus
                  ></v-text-field>
                </span>
              </v-row>
              <v-row justify="center" class="mb-5">
                <span style="width:50%">
                  <form>
                    <v-text-field
                      dense
                      hide-details
                      label="password"
                      type="Password"
                      v-model="pass"
                      @keydown.enter.prevent="emailLogin"
                    ></v-text-field>
                  </form>
                </span>
              </v-row>
              <v-row justify="center">
                <v-btn width="50%" color="#6920A3" @click="emailLogin"
                  >Log-In</v-btn
                >
              </v-row>
            </v-card-text>
          </v-card>
        </v-dialog>
        <!-- 이메일 로그인 dialog 끝 -->
        <!-- 로그인 창 끝 -->
        <!-- 회원가입 창 -->
        <div v-if="1 == 0">
          <!-- 회원가입 버튼 -->
          <div class="mt-10">
            <v-row justify="center" class="mt-2">
              <v-btn
                width="35%"
                color="#6820A3"
                elevation="3"
                @click="$router.push('/signin')"
              >
                이메일로 회원가입
              </v-btn>
            </v-row>
            <v-row justify="center" class="mt-2">
              <v-btn
                width="35%"
                color="#FEE500"
                class="black--text"
                elevation="3"
              >
                카카오 아이디로 회원가입
              </v-btn>
            </v-row>
            <v-row justify="center" class="mt-2">
              <v-btn width="35%" color="#1EC800" elevation="3">
                네이버 아이디로 회원가입
              </v-btn>
            </v-row>
          </div>
          <!-- 회원가입 버튼 끝 -->
        </div>
        <!-- 회원가입 창 끝 -->
        <!-- 회원가입하기 -->
        <v-row justify="center" class="mt-12 footer">
          <span class="mr-3 grey--text">처음이신가요??</span>
          <span style="cursor:pointer" @click="moveToSignin">회원가입</span>
        </v-row>
        <!-- 회원가입하기 끝 -->
        <!-- 로그인하기 -->
        <v-row justify="center" class="mt-12 footer" v-if="1 == 0">
          <span class="mr-3 grey--text">이미 회원이신가요??</span>
          <span style="cursor:pointer" @click="isLogin = true">로그인</span>
        </v-row>
        <!-- 로그인하기 끝 -->
        <!-- 비밀번호 찾기 -->
        <v-row justify="center" class="mt-2 footer">
          <span class="mr-3 grey--text">비밀번호를 잊어버리셨나요?</span>
          <span style="cursor:pointer">비밀번호 재설정</span>
        </v-row>
        <!-- 비밀번호 찾기 끝 -->
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "login",
  props: {
    fromNaverLogin: Boolean,
    naverId: String,
    naverType: Number
  },
  data: () => ({
    // 로그인 관련 변수
    email: null,
    pass: null,
    id: null,
    type: 0,
    // 이메일 로그인 창 변수
    dialogLogin: false,

    // 로그인 후 받은 vo
    user: {},

    isLogin: true,
    isSignin: false,

    // url 관련 변수
    isFromUrl: false,
    urlFactor: {}
  }),
  mounted() {
    // 네이버 로그인 후 다시 온 것을 체크
    console.log(this.naverType);
    if (this.fromNaverLogin) {
      this.id = this.naverId;
      this.type = this.naverType;
      this.apiLogin();
    } else {
      // 네이버 로그인 초기화 & 버튼 삽입
      var naverLogin = new window.naver.LoginWithNaverId({
        clientId: "A_OmJxJFCT9rk_ECJ0Uw",
        callbackUrl: this.$http + "/naver",
        isPopup: false,
        loginButton: { color: "green", type: 3, height: 60 }
      });
      naverLogin.init();
      this.$emit("a");
    }
  },
  methods: {
    // ======================== 로그인 =============================//
    // 이메일을 통한 로그인
    emailLogin() {
      if (this.email == null || this.pass == null) {
        alert("이메일 또는 비밀번호가 입력되지 않았습니다. 확인해주세요");
        return false;
      }
      this.$axios
        .get("/user/3/" + this.email + "/" + this.pass)
        .then(response => {
          this.user = response.data;
          // 소셜로그인 했는데 회원이 아니라면
          if (this.user.email == null) {
            alert("일치하는 계정이 없습니다. 확인해주세요");
            // 회원이라면
          } else {
            alert("로그인됐습니다.");
            this.dialogLogin = false;
            this.setUserInfoInSession();
          }
        })
        .catch(error => {
          alert("로그인에 실패했습니다.");
          console.log(error);
        });
    },
    // 소셜을 통한 FBT 로그인
    apiLogin() {
      this.$axios
        .get("/user/2/" + this.type + "/" + this.id)
        .then(response => {
          this.user = response.data;
          // 소셜로그인 했는데 회원이 아니라면
          if (this.user.email == null) {
            alert("아직 회원등록이 안 됐습니다. 회원가입 페이지로 가겠습니다.");
            this.$router.push({
              name: "signin",
              params: {
                id: this.id,
                type: this.type
              }
            });
            // 회원이라면
          } else {
            alert("로그인됐습니다.");
            this.setUserInfoInSession();
          }
        })
        .catch(error => {
          alert("로그인에 실패했습니다.");
          console.log(error);
        });
    },
    setUserInfoInSession() {
      sessionStorage.setItem("userInfo", JSON.stringify(this.user));
      this.checkIsFromUrl();
    },

    // 카카오 로그인: 토큰 받기
    kakaoLogin() {
      window.Kakao.Auth.login({
        success: auth => {
          console.log("정상적으로 로그인됐습니다.", auth);
          this.getKakaoInfo();
        },
        fail: err => {
          console.log(err);
        }
      });
    },
    // 아이디 정보 받고 api login
    getKakaoInfo() {
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: response => {
          this.id = response.id + "";
          this.type = 1;
          this.apiLogin();
        },
        fail: error => {
          console.log(error);
        }
      });
    },

    // 네이버 로그인
    naverLogin() {
      var naverLogin = new window.naver.LoginWithNaverId({
        clientId: "A_OmJxJFCT9rk_ECJ0Uw",
        callbackUrl: this.$http + "/naver",
        isPopup: false,
        loginButton: { color: "green", type: 3, height: 60 }
      });
      naverLogin.init();
      naverLogin.authorize();
    },
    // 회원가입 창으로 이동
    moveToSignin() {
      this.$router.push("signin");
    },
    // ====================== Url 링크를 통한 경우 =============================/
    setSessionStorageFromUrl() {
      let urlInfo = JSON.stringify(this.urlFactor);
      sessionStorage.setItem("urlInfo", urlInfo);
      this.checkIsUrlInfo();
    },
    checkIsFromUrl() {
      this.urlFactor = this.$route.query;
      if (this.urlFactor.destination) {
        this.isFromUrl = true;
        this.setSessionStorageFromUrl();
      } else {
        location.href = this.$http + "/home";
      }
    },
    checkIsUrlInfo() {
      let urlInfo = JSON.parse(sessionStorage.getItem("urlInfo"));
      if (urlInfo) {
        this.selectTeam(urlInfo);
      }
    },
    selectTeam(urlInfo) {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      if (!userInfo) return false;
      let teams = userInfo.teams;
      if (teams.length > 0) {
        for (let i = 0; i < teams.length; i++) {
          let teamId = teams[i].teamId;
          console.log(teamId, urlInfo.teamId);
          if (teamId == urlInfo.teamId) {
            this.setTeamByUrl(teams[i], urlInfo);
            break;
          }
        }
      }
    },
    setTeamByUrl(team, urlInfo) {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      userInfo.teamId = team.teamId;
      userInfo.teamMemberId = team.teamMemberId;
      userInfo.nickName = team.nickName;
      userInfo.teamName = team.teamName;
      userInfo.memberLevel = team.memberLevel;
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
      this.moveToDestination(urlInfo);
    },
    moveToDestination(urlInfo) {
      location.href = this.$http + "/" + urlInfo.destination;
    }
  }
};
</script>
<style scoped>
#logo {
  width: 80%;
  border: 7px solid #3fdc9d;
  border-radius: 12px;
  font-size: 2.3em;
}

#slogan {
  font-size: 1.5em;
}

.footer {
  font-size: 0.89em;
}
</style>
