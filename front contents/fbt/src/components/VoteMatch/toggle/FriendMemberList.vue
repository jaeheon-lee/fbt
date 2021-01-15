<template>
  <div>
    <!-- 지인 | 명단보기 버튼 -->
    <v-row
      id="vote-btn"
      class="mx-0 mt-0 pt-0"
      style="color:rgba(235, 255, 0,0.7)"
    >
      <v-col class="float-left">
        <span @click="controlMemberList(i)" style="cursor:pointer;"
          >투표 명단 보기</span
        >
      </v-col>
      <v-col>
        <span
          class="float-right"
          style="cursor:pointer;"
          @click="controlFriendList(i)"
          v-if="
            vote.voteMatchSetting.friendEmp == 1 &&
              vote.voteStatus == 0 &&
              !vote.isFriend
          "
          >지인 초대하기</span
        >
      </v-col>
    </v-row>
    <!-- 지인 | 명단보기 버튼 끝 -->
    <!-- 명단 -->
    <v-expand-transition>
      <div v-if="activeMemberList == i && openType == 0">
        <v-divider></v-divider>
        <!--투표 인원 내용-->
        <v-row class="justify-space-between mx-5 mt-4">
          <div>총인원: {{ vote.totalNum }}명</div>
          <div>투표인원: {{ vote.votedNum }}명</div>
          <div>불참: {{ vote.abscentNum }}명</div>
          <div>참여: {{ vote.attendNum }}명</div>
        </v-row>
        <v-row justify="end" class="mx-5 my-3">
          <div>지인: {{ vote.friendNum }}명</div>
        </v-row>
        <v-divider></v-divider>
        <v-row justify="end" class="mx-5 mt-3 mb-5">
          <div>경기참석 인원: {{ vote.totalAttend }}명</div>
        </v-row>
        <!--투표 인원 내용 끝-->
        <v-row class="text-center mx-5">
          <v-col cols="10">
            닉네임/이메일
          </v-col>
          <v-col cols="2">
            참/불
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-row
          v-for="(result, i) in vote.voteMatchResults"
          :key="i"
          class="text-center mx-5"
        >
          <v-col cols="10">
            {{ showNickEmail(result) }}
          </v-col>
          <v-col cols="2">
            {{ result.attendance | attendanceFliter }}
          </v-col>
        </v-row>
      </div>
    </v-expand-transition>
    <!-- 명단 끝 -->
    <!-- 지인 초대 -->
    <v-expand-transition>
      <div v-if="activeFriendList == i && openType == 1">
        <v-divider></v-divider>
        <v-row class="pt-3">
          <v-col class="pr-0 pt-0" cols="10">
            <v-text-field
              class="ma-0 pa-0"
              v-model="inputEmail"
              placeholder="검색할 이메일을 입력해주세요."
              @keydown.enter="searchFriend"
              dense
            />
          </v-col>
          <v-col cols="1" class="pt-1 pr-0 mx-0">
            <i class="material-icons md-18" @click.stop="searchFriend"
              >search</i
            >
          </v-col>
          <v-col cols="1" class="pa-0 pt-1">
            <i class="material-icons md-18" @click="activeFriendList = null"
              >close</i
            >
          </v-col>
        </v-row>
        <v-row class="text-center">
          <v-col offset="2" cols="4">
            e-mail
          </v-col>
          <v-col cols="2">
            이름
          </v-col>
        </v-row>
        <v-row class="mx-3 mb-3">
          <v-divider color="white"></v-divider>
        </v-row>
        <v-row
          id="friend-list"
          class="text-center my-1"
          v-for="(friend, j) in friends"
          :key="j"
        >
          <v-col offset="2" cols="4">
            {{ friend.email }}
          </v-col>
          <v-col cols="2">
            {{ friend.name }}
          </v-col>
          <v-col cols="1" class="ma-0 mb-3 pa-0 text-center"
            ><v-btn
              class="ma-0 ml-8 pa-0"
              elevation="2"
              color="#6920A3"
              @click="inviteFriend(friend.email, vote.voteMatchId)"
              style="font-size: 0.65em"
              >초대하기</v-btn
            >
          </v-col>
        </v-row>
      </div>
    </v-expand-transition>
    <!-- 지인 초대 끝 -->
  </div>
</template>

<script>
export default {
  name: "friend-member-list",
  props: {
    vote: Object,
    i: Number
  },
  data() {
    return {
      // 지인 초대 관련 변수
      inputEmail: null, //검색할 이메일
      friends: [],
      activeMemberList: null,
      activeFriendList: null,
      openType: null
    };
  },
  computed: {},
  methods: {
    // 지인찾기 FV09
    searchFriend() {
      this.$axios
        .get(
          "/user/1?email=" +
            this.inputEmail +
            "&teamId=" +
            JSON.parse(sessionStorage.getItem("userInfo")).teamId
        )
        .then(response => {
          this.loading = true;
          this.friends = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 지인초대하기 FV09
    inviteFriend(email, voteMatchId) {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      let content = userInfo.name + "님이 " + userInfo.teamName + "의 경기에 초청했습니다.";
      content += " 클릭하여 확인해주세요.";
      let notice = {
        giverTeam: {
          teamId: userInfo.teamId
        },
        giverUser: {
          email: userInfo.email
        },
        takerUser: {
          email: email
        },
        voteMatch: {
          voteMatchId: voteMatchId
        },
        content: content
      };
      notice.takerUsers = [];
      notice.takerUsers.push(notice.takerUser);
      this.$axios
        .post("/notice/1", notice)
        .then(() => {
          alert("초대가 완료됐습니다.");
          this.dialogKakao = true;
        })
        .catch(() => {
          alert("초대에 실패했습니다.");
        });
    },
    // 지인이면 이메일, 팀원이면 닉네임으로 출력하기
    showNickEmail(result) {
      if (result.teamMember) {
        // 팀원이면
        return result.teamMember.nickName;
      } else {
        //지인이면
        return result.user.email;
      }
    },
    // 명단보기 창 여닫기
    controlMemberList(i) {
      if (this.activeMemberList == i) this.activeMemberList = null;
      // 명단이 열려있다면 명단 닫기
      else {
        if (this.activeFriendList == i) this.activeFriendList = null; // 명단도 열려 있다면 명단 닫기
        this.activeMemberList = i;
        this.openType = 0;
      }
    },
    // 지인 초대 창 여닫기
    controlFriendList(i) {
      if (this.activeFriendList == i) this.activeFriendList = null;
      // 초대가 열려있다면 초대 닫기
      else {
        if (this.activeMemberList == i) this.activeMemberList = null; // 명단도 열려 있다면 명단 닫기
        this.activeFriendList = i;
        this.openType = 1;
      }
    },
  },
  filters: {
    attendanceFliter(value) {
      if (value == 1) {
        //참이라면
        return "참";
      } else if (value == 0) {
        // 불이라면
        return "불";
      } else {
        return "대기";
      }
    },
  }
};
</script>

<style></style>
