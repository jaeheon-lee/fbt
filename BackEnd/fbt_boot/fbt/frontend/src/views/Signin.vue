<template>
  <v-container>
    <v-row justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="py-0 my-0 px-0 mx-0">
        <!-- 회원가입 라벨 -->
        <v-row class="py-0 my-5 px-0 mx-5">
          <v-col cols="12" class="pa-0 ma-0 mb-2">
            <span>회원가입</span>
            <v-divider color="white"></v-divider>
          </v-col>
        </v-row>
        <!-- 회원가입 라벨 끝 -->
        <sign-in-body :id="id" :type="type" :header="header"></sign-in-body>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import SigninBody from "@/components/signin/SigninBody.vue";
export default {
  name: "sign-in",
  components: {
    "sign-in-body": SigninBody
  },
  props: {
    id: String,
    type: Number
  },
  data() {
    return {
      header: "insert"
    }
  }
};
</script>
