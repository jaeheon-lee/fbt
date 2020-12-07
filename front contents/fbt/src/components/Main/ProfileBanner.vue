<template>
  <v-card height="64" @click="$router.push({ name: 'home' })">
    <v-list-item height="64" link align-center>
      <v-list-item height="64" justify-center pa-0>
        <v-list-item-avatar>
          <v-img src="@/assets/image/손흥민.jpg" v-if="userInfo.image" />
          <v-icon v-else>mdi-account-circle</v-icon>
        </v-list-item-avatar>
        <v-list-item-content pa-0>
          <v-list-item-title class="title" style="font-family:nexon!important">
            {{ name }}
            <span
              class="mt-2 float-right"
              style="font-size:0.4em"
              @click.stop="logout"
            >
              로그아웃
            </span>
          </v-list-item-title>
          <v-list-item-subtitle>{{ email }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  name: "profile-banner",
  props: {
    userInfo: Object
  },
  data: () => ({}),
  computed: {
    name: function() {
      return this.userInfo.name;
    },
    email: function() {
      return this.userInfo.email;
    }
  },
  methods: {
    logout() {
      sessionStorage.removeItem("userInfo");
      location.href = this.$http + "/loginSignin";
    }
  }
};
</script>

<style></style>
