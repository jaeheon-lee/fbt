<template>
  <div>
    <v-row justify="center" class="mt-7">
      <v-btn @click="sendMsg">click</v-btn>
    </v-row>
    <v-row justify="center" class="mt-7">
      <v-btn @click="logIn">login</v-btn>
    </v-row>
    <v-row justify="center" class="mt-7">
      <v-btn @click="logout">logout</v-btn>
    </v-row>
    <v-row justify="center" class="mt-7">
      <v-btn @click="unLink">unLink</v-btn>
    </v-row>
    <v-row justify="center" class="mt-7">
      <v-btn @click="test">test</v-btn>
    </v-row>
    <div id="textDiv"></div>
    <div id=""></div>
  </div>
</template>

<script>
export default {
  name: "kakao-msg",
  data() {
    return {
      state: "",
      naverLogin: null
    };
  },
  mounted() {
    
  },
  methods: {
    test() {
      let text = "123dasdsa45\n\n";
      text += "뛰웠습니다.";
      var createInput = document.createElement("textarea");
      // 가상으로 가져올 태그에 만들어준 input 태그를 붙여줍니다.
      document.getElementById("textDiv").appendChild(createInput);
      // 만든 input 태그의 value 값에 복사할 텍스트 값을 넣어줍니다.
      createInput.value = text;
      // 복사 기능을 수행한 후
      createInput.select();
      document.execCommand("copy");
      // 가상으로 붙여주었던 input 태그를 제거해줍니다.
      document.getElementById("textDiv").removeChild(createInput);
      // let url = encodeURI(encodeURIComponent("http://localhost:8080/voteMatch"));
      // let title = encodeURI("11:11 축구 경기 모집");
      // var shareURL = "https://share.naver.com/web/shareView.nhn?url=" + url + "&title=" + title;
      // window.open(shareURL);
    },
    sendMsg() {
      window.Kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: "11:11 경기 투표 생성",
          description: "11:11 축구 경기 생성. 빠른 투표바랍니다.",
          imageUrl: "@/assets/emblem/답십리FC201018.png",
          link: {
            webUrl: "http://localhost:8080/voteMatch",
            mobileWebUrl: "http://localhost:8080/voteMatch"
          }
        }
      });
    },
    logIn() {},
    logout() {
      window.Kakao.Auth.logout(function() {
        console.log(window.Kakao.Auth.getAccessToken());
      });
    },
    unLink() {
      window.Kakao.API.request({
        url: "/v1/user/unlink",
        success: function(response) {
          console.log(response);
        },
        fail: function(error) {
          console.log(error);
        }
      });
    }
  }
};
</script>

<style></style>
