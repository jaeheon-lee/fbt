<template>
  <div>
    <v-container fluid class="pt-5 pb-0 my-0 px-0 mx-0">
      <v-row justify="center" fluid align="start" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="py-0 my-0 px-0 mx-0">
          <!-- 팀 프로필 -->
          <v-row>
            <v-card
              class="mx-auto mb-5"
              width="100%"
              style="background-color:#AD1457;"
            >
              <v-row justify="center">
                <v-col cols="5" style="text-align:center;">
                  <v-avatar size="120" color="#212121">
                    <v-img
                      :src="getImageUrl()"
                      alt="팀프로필"
                      width="90px"
                      height="90px"
                      contain
                      v-if="isImage"
                    />
                    <v-icon style="font-size: 6em" v-else>
                      mdi-account-circle
                    </v-icon>
                  </v-avatar>
                </v-col>
                <v-col cols="6" style="font-size:15px" px-0>
                  <v-card-title class="pa-1">{{ getUserName }}</v-card-title>
                  <v-card-text class="text--primary pa-1">
                    <v-row> 주활동지역: {{ team.area }} </v-row>
                    <v-row>
                      경기력: {{ team.teamAbility | showTeamScore }}
                    </v-row>
                    <v-row> 매너: {{ team.teamManner | showTeamScore }} </v-row>
                  </v-card-text>
                </v-col>
              </v-row>
            </v-card>
          </v-row>
          <!-- 팀 프로필 끝 -->
          <!-- 향후 일정 -->
          <v-row style="border-bottom: 1px white solid;">
            <v-col
              class="my-0 py-0 px-0"
              style="cursor:pointer;"
              @click="controlFutureMatch"
            >
              <span class="float-left">향후 일정(최대 5개)</span>
              <span class="float-right">
                <v-icon v-if="activeFutureMatch">mdi-chevron-down</v-icon>
                <v-icon v-else>mdi-chevron-right</v-icon>
              </span>
            </v-col>
          </v-row>
          <v-expand-transition>
            <vote-match-list
              :header="header"
              :votes="votes"
              v-if="activeFutureMatch && votes.length > 0"
            ></vote-match-list>
          </v-expand-transition>
          <v-card
            class="py-5 text-center"
            v-if="activeFutureMatch && votes.length == 0"
          >
            일정이 없습니다.
          </v-card>
          <!-- 향후 일정 끝 -->
          <!-- 최근 5경기 전적 -->
          <v-row class="mt-5" style="border-bottom: 1px white solid;">
            <v-col
              class="my-0 py-0 px-0"
              style="cursor:pointer;"
              @click="controlPastMatch"
            >
              <span class="float-left">최근 5경기 전적</span>
              <span class="float-right">
                <v-icon v-if="activePastMatch">mdi-chevron-down</v-icon>
                <v-icon v-else>mdi-chevron-right</v-icon>
              </span>
            </v-col>
          </v-row>
          <v-expand-transition>
            <v-row v-if="activePastMatch">
              <v-expansion-panels
                class="px-9"
                accordion
                v-if="team.matchSchedules.length > 0"
              >
                <v-expansion-panel
                  v-for="(match, i) in team.matchSchedules"
                  :key="i"
                  @click="showEndMatchInfoByTeam(match.matchScheduleId, i)"
                >
                  <v-expansion-panel-header class="px-4">
                    {{ match.startTime.split(" ")[0] }} 경기
                    {{ match.matchResult.homeScore }} :
                    {{ match.matchResult.awayScore }}
                    {{ showMatchResult(match) }}
                  </v-expansion-panel-header>
                  <v-expansion-panel-content v-if="activeMatchResult == i">
                    <vote-match-list
                      :header="header"
                      :votes="votes"
                    ></vote-match-list>
                  </v-expansion-panel-content>
                </v-expansion-panel>
              </v-expansion-panels>
              <!-- 없다면 -->
              <v-card v-else>
                최근 전적이 없습니다.
              </v-card>
            </v-row>
          </v-expand-transition>
          <!-- 최근 5경기 전적 끝 -->
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<style scoped src="../assets/css/all.css"></style>

<script>
import VoteMatchList from "@/components/VoteMatch/VoteMatchList.vue";

export default {
  name: "team-home",
  components: {
    "vote-match-list": VoteMatchList
  },
  data: () => ({
    // data
    userInfo: null,
    team: {},
    votes: [],

    // window control variable
    activeFutureMatch: true, // 향후 일정
    activePastMatch: false, // 최근 5경기 전적
    activeMatchResult: null,

    header: "teamHome"
  }),
  created() {
    this.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
    this.showTeamInfo();
    this.showFutureSchedule();
  },
  computed: {
    isImage: function() {
      if (this.team.emblem) return true;
      else return false;
    },
    getUserName: function() {
      return this.team.teamName;
    }
  },
  methods: {
    //FT01
    showTeamInfo() {
      this.$axios
        // eslint-disable-next-line prettier/prettier
        .get("/team/3/" + this.userInfo.teamId + "/" + this.userInfo.teamMemberId)
        .then(response => {
          this.team = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    //FS07
    showEndMatchInfoByTeam(matchScheduleId, i) {
      this.loading = true;
      let teamId = this.userInfo.teamId;
      if (!teamId) teamId = 0;
      this.$axios
        .get("/match-schedule/5/" + matchScheduleId + "/" + teamId)
        .then(response => {
          let matchSchedule = response.data;
          let vote = {};
          vote.matchSchedule = matchSchedule;
          // 끝난 경기인지에 대한 정보 담고
          vote.isEndMatch = false;
          // 리스트에 보낼 통에 담고
          this.votes = [];
          this.votes.push(vote);
          this.activeMatchResult = i;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          setTimeout(() => {
            this.loading = false;
          }, 1000);
        });
    },
    // 향후 일정 출력(FS17)
    showFutureSchedule() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.$axios
        .get("/match-schedule/9/" + teamId)
        .then(response => {
          let matchSchedules = response.data;
          this.votes = [];
          for (let i = 0; i < matchSchedules.length; i++) {
            let vote = { isEndMatch: false, matchSchedule: matchSchedules[i] };
            this.votes.push(vote);
          }
          console.log(this.votes);
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    getImageUrl() {
      return require("@/assets/image/emblem/" + this.team.emblem);
    },
    controlPastMatch() {
      if (this.activePastMatch) {
        this.activePastMatch = false;
      } else {
        this.activeFutureMatch = false;
        this.activePastMatch = true;
      }
    },
    controlFutureMatch() {
      this.showFutureSchedule();
      if (this.activeFutureMatch) {
        this.activeFutureMatch = false;
      } else {
        this.activePastMatch = false;
        this.activeFutureMatch = true;
      }
    },
    // 전적에서 승패 표시
    showMatchResult(match) {
      let teamId = this.userInfo.teamId;
      let homeTeamId = match.homeTeam.teamId;
      if (teamId == homeTeamId) {
        if (match.matchResult.homeResult == 1) return "(승)";
        else if (match.matchResult.homeResult == -1) return "(패)";
        else return "(무)";
      } else {
        if (match.matchResult.homeResult == 1) return "(패)";
        else if (match.matchResult.homeResult == -1) return "(승)";
        else return "(무)";
      }
    }
  },
  filters: {
    // ======================= 받은 점수 =========================//
    showTeamScore(value) {
      if (value == 0) {
        return "";
      } else if (value < 2) {
        return "하하";
      } else if (value < 4) {
        return "중하";
      } else if (value < 6) {
        return "중중";
      } else if (value < 8) {
        return "중상";
      } else {
        return "상상";
      }
    }
  }
};
</script>
