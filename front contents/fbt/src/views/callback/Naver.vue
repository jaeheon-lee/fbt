<template>
  <div></div>
</template>

<script>
export default {
  name: "naver-callback",
  data: () => ({
    // 로그인 관련 변수
    email: null,
    pass: null,
    id: null,
    type: 0,

    // 로그인 후 받은 vo
    user: {}
  }),
  mounted() {
    const naverLogin = new window.naver.LoginWithNaverId({
      clientId: "A_OmJxJFCT9rk_ECJ0Uw",
      isPopup: true,
      callbackHandle: true
    });
    naverLogin.init();
    naverLogin.getLoginStatus(status => {
      if (status) {
        this.id = naverLogin.user.getId() + "";
        this.type = 2;
        this.apiLogin();
      } else {
        console.log("AccessToken이 올바르지 않습니다.");
      }
    });
  },
  methods: {
    // FBT 로그인
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
            })
          } else {
            alert("로그인됐습니다.");
            let userInfo = {
              email: this.user.email,
              teams: this.user.teams
            };
            sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
            this.$router.push("/home");
          }
        })
        .catch(error => {
          alert("로그인에 실패했습니다.")
          console.log(error);
        });
    },
  }
};
</script>
