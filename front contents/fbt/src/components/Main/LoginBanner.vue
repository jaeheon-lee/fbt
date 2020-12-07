<template>
  <v-card height="64" @click="$router.push({ name: 'loginSignin' })">
    <v-list-item height="64" link align-center>
      <v-list-item height="64" justify-center pa-0>
        <v-list-item-avatar style="line-height:2">
          <v-icon>mdi-login-variant</v-icon>
        </v-list-item-avatar>
        <v-list-item-content pa-0>
          <v-list-item-title class="title" style="font-family:nexon!important;line-height:2"
            >로그인</v-list-item-title
          >
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  name: "login-banner"
};
</script>

<style></style>
