<template>
  <v-container fluid class="py-0 my-7 px-0 mx-0">
    <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
        <!-- page label -->
        <!-- Page Label -->
        <v-row class="py-0 my-0 px-0 mx-5">
          <v-col cols="12" class="pa-0 ma-0 mb-2">
            <span>전체 알림</span>
            <v-divider style="border-color: #C4C4C4 !important;">
              inset
            </v-divider>
          </v-col>
        </v-row>
        <!-- page label end -->
        <!-- 알림 리스트 -->
        <v-row justify="center" class="mt-3">
          <v-card
            class="mx-7 pa-3 mb-2"
            v-for="(notice, i) in notices"
            :key="i"
            style="width:75%"
            @click="navigate(notice)"
          >
            <!-- 팀에서 보낸 알림일 때 -->
            <v-avatar
              color="white"
              size="38"
              rounded=""
              v-if="notice.giverTeam"
            >
              <v-img
                :src="
                  require('@/assets/image/emblem/' + notice.giverTeam.emblem)
                "
              ></v-img>
            </v-avatar>
            <!-- 팀이 아닐 때 -->
            <v-avatar color="primary" size="38" rounded="" v-else>
              <span class="white--text">FBT</span>
            </v-avatar>

            <!-- 알림 내용 -->
            <span class="ml-2 mr-5" style="font-size:0.7em">{{
              notice.regDate | filterRegDate
            }}</span>
            <span>{{ notice.content }} </span>

            <!-- 지인 투표 창 -->
            <v-dialog v-model="activateVote">
              <v-row justify="center">
                <vote-match-list
                  class="justify-center"
                  :votes="votes"
                  :header="header"
                  @refresh="showVoteMatchInfoById(notice)"
                  style="width: 90%"
                ></vote-match-list>
              </v-row>
              <v-row justify="end">
                <v-btn
                  color="#6920A3"
                  @click="activateVote = false"
                  style="margin-right: 5%"
                  >닫기</v-btn
                >
              </v-row>
            </v-dialog>
          </v-card>
        </v-row>
        <!-- 알림 리스트 -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import VoteMatchList from "@/components/VoteMatch/VoteMatchList.vue";
export default {
  name: "notice",
  components: {
    "vote-match-list": VoteMatchList
  },
  data() {
    return {
      notices: [],
      activateVote: false,

      // 지인 투표 관련 변수
      votes: [],
      header: "voteMatchFriend"
    };
  },
  mounted() {
    this.showNotices();
  },
  methods: {
    //FN01
    showNotices() {
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      this.$axios
        .get("/notice/1/" + email)
        .then(response => {
          this.notices = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    //FV01
    showVoteMatchInfoById(notice) {
      let voteMatchId = notice.voteMatch.voteMatchId;
      this.$axios
        .get("/vote-match/4/" + voteMatchId)
        .then(response => {
          let vote = response.data;
          // 지인 투표라는 표시를 남긴다
          vote.isFriend = true;
          // 리스트 객체에 담아 보낸다
          this.votes = [];
          this.votes.push(vote);
          // 다이아로그를 연다
          this.activateVote = true;
        })
        .catch(error => {
          console.log(error);
        });
    },
    // 창 이동인지, 지인투표인지 방향 설정
    navigate(notice) {
      if (notice.pageName) {
        //페이지 이름이 있으면 창이동
        this.moveToPage(notice.pageName, notice.takerTeam);
      } else {
        this.showVoteMatchInfoById(notice);
      }
    },
    moveToPage(page, takerTeam) {
      let pageCom = page.split("-");
      let pageName = pageCom[0];
      let params = {};
      if (pageCom.length > 1) {
        let pageCon = pageCom[1];
        switch (pageCon) {
          case "other":
            this.setTeam(takerTeam);
            break;
          case "endVoteMatch":
            this.setTeam(takerTeam);
            params = {
              isEndR: true
            };
            break;
          case "applySearch":
          case "applyAssign":
            this.setTeam(takerTeam);
            params = {
              type: pageCon,
              pageR: 3,
              registeredStageR: 2
            };
            break;
          case "acceptSearch":
          case "refuseAssign":
          case "failAssign":
            this.setTeam(takerTeam);
            params = {
              type: pageCon,
              pageR: 4,
              appliedStageR: 2
            };
            break;
          case "refuseSearch":
          case "acceptAssign":
          case "failSearch":
            this.setTeam(takerTeam);
            params = {
              type: pageCon,
              pageR: 4,
              appliedStageR: 3
            };
            break;
          case "fillNumber":
            this.setTeam(takerTeam);
            params = {
              type: pageCon,
              pageR: 3,
              registeredStageR: 5
            };
            break;

          case "acceptEmployApply":
            params = {
              type: pageCon,
              pageR: 2,
              appliedStageR: 3
            };
            break;
          case "refuseEmployApply":
            params = {
              type: pageCon,
              pageR: 2,
              appliedStageR: 2
            };
            break;

          case "completeSearch":
          case "completeAssign":
            this.setTeam(takerTeam);
            pageName = "scheduleManager";
            break;

          case "completeEmployApply":
            pageName = "scheduleUser";
            break;

          case "cancelSearchApply":
          case "cancelAssignApply":
          case "cancelEmployApply":
            return null;
        }
      }
      this.$router.push({
        name: pageName,
        params: params
      });
    },
    // 팀 세팅 메소드
    setTeam(takerTeam) {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      let index = userInfo.teams
        .map(x => parseInt(x.teamId))
        .indexOf(takerTeam.teamId);
      let team = userInfo.teams[index];
      userInfo.teamId = team.teamId;
      userInfo.teamMemberId = team.teamMemberId;
      userInfo.nickName = team.nickName;
      userInfo.teamName = team.teamName;
      userInfo.memberLevel = team.memberLevel;
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
    }
  },
  filters: {
    filterRegDate(value) {
      let date = new Date(value);
      let month = date.getMonth() + 1;
      let day = date.getDate();
      return month + " 월 " + day + " 일";
    }
  }
};
</script>

<style></style>
