<template>
  <v-container fluid class="py-0 my-7 px-0 mx-0">
    <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
        <!-- 페이지 라벨 -->
        <v-row class="mx-5">
          일정 수정하기
        </v-row>
        <v-row class="mx-5">
          <v-divider color="white"></v-divider>
        </v-row>
        <!-- 페이지 끝-->
        <!-- 수정 관련 알림 -->
        <v-row justify="center" class="mt-3">
          <p>
            경기 일정이 이미 확정되었기 때문에, 핵심 정보 이외 부가정보만
            수정가능합니다.
          </p>
        </v-row>
        <!-- 수정 관련 알림 끝-->
        <!-- 경기장타입, 샤워여부, 주차 여부 -->
        <!-- 경기장타입, 샤워여부, 주차여부 Label-->
        <v-row class="mx-5 px-0">
          <v-col cols="4" class="text-left mx-0 pl-0 pr-1 px-0 pb-2"
            >경기장 타입</v-col
          >
          <v-col
            cols="4"
            class="text-left mx-0 px-0 pb-2"
            style="padding-left:2px;padding-right:2px;"
            >샤워여부</v-col
          >
          <v-col
            cols="4"
            class="text-left mx-0 px-0 pb-2"
            style="padding-left:2px;padding-right:2px;"
            >주차여부</v-col
          >
        </v-row>
        <!-- 경기장타입, 샤워여부 Input-->
        <v-row class="mx-5 px-0">
          <!-- 경기장 타입 input -->
          <v-col cols="4" class="pa-0 pr-1">
            <v-row
              class="ma-0 pa-3"
              justify="center"
              style="border:2px solid #AD1457;border-radius:25px;"
            >
              <select
                v-model="matchSchedule.stadiumType"
                :class="{
                  'before-selected': !stadiumTypeSelected,
                  'after-selected': stadiumTypeSelected
                }"
                @change="stadiumTypeSelected = true"
              >
                <option class="options" disabled selected value="null"
                  >경기장타입</option
                >
                <option
                  class="options"
                  v-for="stadiumType in stadiumTypes"
                  v-bind:key="stadiumType"
                  :value="stadiumType"
                  >{{ stadiumType }}</option
                >
              </select>
            </v-row>
          </v-col>
          <!-- 샤워여부 input -->
          <v-col cols="4" class="pa-0 pr-1">
            <v-row
              class="ma-0 pa-3"
              justify="center"
              style="border:2px solid #AD1457;border-radius:25px;"
            >
              <select
                v-model="matchSchedule.stadiumShower"
                :class="{
                  'before-selected': !stadiumShowerSelected,
                  'after-selected': stadiumShowerSelected
                }"
                @change="stadiumShowerSelected = true"
              >
                <option class="options" disabled selected value="null"
                  >샤워여부</option
                >
                <option class="options" value="1">샤워가능</option>
                <option class="options" value="0">샤워불가</option>
              </select>
            </v-row>
          </v-col>
          <!-- 주차여부 -->
          <v-col cols="4" class="pa-0 pl-1">
            <v-row
              class="ma-0 pa-3"
              justify="center"
              style="border:2px solid #AD1457;border-radius:25px;"
            >
              <select
                v-model="matchSchedule.stadiumParking"
                :class="{
                  'before-selected': !parkingSelected,
                  'after-selected': parkingSelected
                }"
                @change="parkingSelected = true"
              >
                <option class="options" disabled selected value="null"
                  >주차</option
                >
                <option class="options" value="1">주차가능</option>
                <option class="options" value="0">주차불가</option>
              </select>
            </v-row>
          </v-col>
        </v-row>
        <!-- 경기장타입, 샤워여부 끝 -->
        <!-- 투표 등록 btn -->
        <v-row justify="center" class="ma-0 pa-4">
          <v-btn large color="#AD1457" @click="updateSchedule"
            >일정 수정하기</v-btn
          >
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "schedule-update",
  props: {
    vote: Object
  },
  data() {
    return {
      // matchSchedule VO
      matchSchedule: {},

      // CSS Binding Variable
      stadiumTypeSelected: false,
      stadiumShowerSelected: false,
      parkingSelected: false,

      // Variable regarding StadiumType
      // eslint-disable-next-line prettier/prettier
      stadiumTypes: ['흙', '잔디', '인조잔디']
    };
  },
  created() {
    this.matchSchedule = this.vote.matchSchedule;
  },
  methods: {
    updateSchedule() {
      console.log(this.matchSchedule);
      let updatingTeamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.$axios
        .put(
          "/match-schedule/4?updatingTeamId=" + updatingTeamId,
          this.matchSchedule
        )
        .then(() => {
          alert("수정됐습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("수정에 실패했습니다.");
        })
        .finally(() => {
          this.$router.push({
            name: "scheduleManager"
          })
        });
    }
  }
};
</script>

<style scoped src="@/assets/css/common/Insert.css"></style>
