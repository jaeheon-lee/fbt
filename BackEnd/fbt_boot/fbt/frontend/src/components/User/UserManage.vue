<template>
  <div class="py-0 my-0 px-0 mx-5">
    <!-- 페이지 라벨 -->
    <div>
      <v-row>
        <span>내 정보</span>
      </v-row>
      <v-row>
        <v-divider color="white"></v-divider>
      </v-row>
    </div>
    <!-- 페이지 라벨 -->
    <!-- 정보 수정 -->
    <sign-in-body :header="header"></sign-in-body>
    <!-- 정보 수정 끝 -->
  </div>
</template>

<script>
import SigninBody from "@/components/signin/SigninBody.vue";
export default {
  name: "user-manage",
  components: {
    "sign-in-body": SigninBody
  },
  data() {
    return {
      header: "update"
    }
  }
}
</script>

<style>

</style>