<template>
  <v-row class="ma-0 pa-0">
    <v-col cols="3" class="text-center">
      {{ vote.reg_date }}
    </v-col>
    <v-col cols="3" class="text-center">{{ vote.due_date }}</v-col>
    <v-col cols="3" class="text-center">{{ vote.match_date }}</v-col>
    <v-col cols="3" class="text-center">{{ vote.match_place }}</v-col>
  </v-row>
</template>
<script>
export default {
  data() {
    return {
      vote: {}
    };
  }
};
</script>
