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
        this.moveBackToLogin();
      } else {
        console.log("AccessToken이 올바르지 않습니다.");
      }
    });
  },
  methods: {
    // 다시 로그인 페이지로
    moveBackToLogin() {
      this.$router.push({
        name: "loginSignin",
        params: {
          fromNaverLogin: true,
          naverId: this.id,
          naverType: this.type
        }
      });
    }
  }
};
</script>
