<template>
  <v-container fluid class="mx-0 my-0 px-0 py-2">
    <v-row justify="center">
      {{ mention }}
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "empty",
  props: {
    ment: String
  },
  computed: {
    mention: function() {
      return this.ment + " 없습니다.";
    }
  }
}
</script>