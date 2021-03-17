<template>
  <div>
    <!-- 명단보기 버튼 -->
    <v-row
      id="vote-btn"
      class="mx-0 mt-0 pt-0"
      style="color:rgba(235, 255, 0,0.7)"
    >
      <v-col class="float-left">
        <span @click="controlHomeMemberList(i)" style="cursor:pointer;"
          >홈팀 엔트리 보기</span
        >
      </v-col>
      <v-col>
        <span
          class="float-right"
          style="cursor:pointer;"
          @click="controlAwayMemberList(i)"
          v-if="controlAwayMemberListBtn"
          >어웨이팀 엔트리 보기</span
        >
      </v-col>
    </v-row>
    <!-- 지인 | 명단보기 버튼 끝 -->
    <!-- 홈팀 명단 -->
    <v-expand-transition>
      <div v-if="activeHomeMemberList == i && openType == 0">
        <v-divider></v-divider>
        <!-- 명단 라벨 -->
        <v-row justify="center" class="mt-3">[홈팀 엔트리 명단]</v-row>
        <!--투표 인원 내용-->
        <v-row class="justify-space-between mx-5 mt-4">
          <div>총인원: {{ homeEntryDescs[0] }}명</div>
          <div>팀원: {{ homeEntryDescs[1] }}명</div>
          <div>지인: {{ homeEntryDescs[2] }}명</div>
          <div>용병: {{ homeEntryDescs[3] }}명</div>
        </v-row>
        <v-divider class="mt-3" color="white"></v-divider>
        <!--투표 인원 내용 끝-->
        <v-row class="text-center mx-5">
          <v-col offset="1" cols="6">
            닉네임/이메일
          </v-col>
          <v-col cols="2">
            참/대기
          </v-col>
          <v-col cols="3">
            
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-row
          v-for="(entry, i) in homeEntries"
          :key="i"
          class="text-center mx-5 py-1"
        >
          <v-col offset="1" cols="6" class="pt-3">
            {{ showNickEmail(entry) }}
          </v-col>
          <v-col cols="2" class="pt-3">
            {{ entry.type | typeFilter }}
          </v-col>
          <v-col cols="3">
            <v-btn 
              color="#6920A3" 
              small class="mb-1" 
              v-if="entry.type == 3 && header == 'scheduleManager'" 
              @click="joinEntry(entry)"
              >엔트리 추가
            </v-btn>
          </v-col>
        </v-row>
      </div>
    </v-expand-transition>
    <!-- 홈팀 명단 끝 -->
    <!-- 어웨이팀 명단 -->
    <v-expand-transition>
      <div v-if="activeAwayMemberList == i && openType == 1">
        <v-divider></v-divider>
        <!-- 명단 라벨 -->
        <v-row justify="center" class="mt-3">[어웨이팀 투표 명단]</v-row>
        <!--투표 인원 내용-->
        <v-row class="justify-space-between mx-5 mt-4">
          <div>총인원: {{ awayEntryDescs[0] }}명</div>
          <div>팀원: {{ awayEntryDescs[1] }}명</div>
          <div>지인: {{ awayEntryDescs[2] }}명</div>
          <div>용병: {{ awayEntryDescs[3] }}명</div>
        </v-row>
        <v-divider class="mt-3" color="white"></v-divider>
        <!--투표 인원 내용 끝-->
        <v-row class="text-center mx-5">
          <v-col offset="1" cols="6">
            닉네임/이메일
          </v-col>
          <v-col cols="2">
            참/대기
          </v-col>
          <v-col cols="3">
            
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-row
          v-for="(entry, i) in awayEntries"
          :key="i"
          class="text-center mx-5"
        >
          <v-col offset="1" cols="6">
            {{ showNickEmail(entry) }}
          </v-col>
          <v-col cols="2">
            {{ entry.type | typeFilter }}
          </v-col>
          <v-col cols="3">
            <v-btn color="#6920A3" small class="mb-1" v-if="entry.type == 3 && header == 'scheduleManager'" @click="joinEntry(entry)">
              엔트리 추가
            </v-btn>
          </v-col>
        </v-row>
      </div>
    </v-expand-transition>
    <!-- 어웨이팀 명단 끝 -->
  </div>
</template>

<script>
export default {
  name: "member-list",
  props: {
    vote: Object,
    i: Number,
    awayVote: Object,
    header: String
  },
  data() {
    return {
      // 엔트리 변수
      homeEntries: [],
      awayEntries: [],
      // 엔트리 요약 변수: 순서대로 총원, 팀원, 지인, 용병
      homeEntryDescs: [0, 0, 0, 0],
      awayEntryDescs: [0, 0, 0, 0],
      // 지인 초대 관련 변수
      activeHomeMemberList: null,
      activeAwayMemberList: null,
      openType: null
    };
  },
  mounted() {
    this.setEntries();
  },
  watch: {
    vote: function() {
      this.setEntries();
      this.activeHomeMemberList = null;
      this.activeAwayMemberList = null;
    }
  },
  computed: {
    controlAwayMemberListBtn: function() {
      let homeTeamId = this.vote.matchSchedule.homeTeam.teamId;
      let awayTeamId = this.vote.matchSchedule.awayTeam.teamId;
      if (homeTeamId == awayTeamId) return false;
      return true;
    }
  },
  methods: {
    // 엔트리 할당
    setEntries() {
      this.homeEntries = this.vote.matchSchedule.entries;
      this.setHomeEntryDesc(this.homeEntries);
      if (this.awayVote.matchSchedule) {
        this.awayEntries = this.awayVote.matchSchedule.entries;
        this.setAwayEntryDesc(this.awayEntries);
      }
    },
    // 지인이면 이메일, 팀원이면 닉네임으로 출력하기
    showNickEmail(entry) {
      let type = entry.type;
      if (type == 0 || type == 3) { // 팀원
        return entry.teamMember.nickName;
      } else if (type == 1) { // 지인
        return entry.user.email + "(지인)";
      } else { // 용병
        return entry.user.email + "(용병)";
      }
    },
    // 홈팀명단보기 창 여닫기
    controlHomeMemberList(i) {
      if (this.activeHomeMemberList == i) this.activeHomeMemberList = null;
      // 명단이 열려있다면 명단 닫기
      else {
        if (this.activeAwayMemberList == i) this.activeAwayMemberList = null; // 명단도 열려 있다면 명단 닫기
        this.activeHomeMemberList = i;
        this.openType = 0;
      }
    },
    // 어웨팀명단보기 창 여닫기
    controlAwayMemberList(i) {
      if (this.activeAwayMemberList == i) this.activeAwayMemberList = null;
      // 초대가 열려있다면 초대 닫기
      else {
        if (this.activeHomeMemberList == i) this.activeHomeMemberList = null; // 명단도 열려 있다면 명단 닫기
        this.activeAwayMemberList = i;
        this.openType = 1;
      }
    },
    // 엔트리 요약 출력 메소드
    setHomeEntryDesc(entries) {
      this.homeEntryDescs = [0, 0, 0, 0];
      for (let i=0;i<entries.length;i++) {
        let type = entries[i].type;
        this.homeEntryDescs[0]++;
        if (type == 0) { // 팀원
          this.homeEntryDescs[1]++;
        } else if (type == 1) { // 지인
          this.homeEntryDescs[2]++;
        } else { // 용병
          this.homeEntryDescs[3]++;
        }
      }
    },
    setAwayEntryDesc(entries) {
      this.awayEntryDescs = [0, 0, 0, 0];
      for (let i=0;i<entries.length;i++) {
        let type = entries[i].type;
        this.awayEntryDescs[0]++;
        if (type == 0) { // 팀원
          this.awayEntryDescs[1]++;
        } else if (type == 1) { // 지인
          this.awayEntryDescs[2]++;
        } else { // 용병
          this.awayEntryDescs[3]++;
        }
      }
    },
    // 엔트리 추가
    joinEntry(entry) {
      console.log(entry);
      this.$axios
        .put("/match-schedule/5", entry)
        .then(() => {
          alert("엔트리에 추가됐습니다.");
        })
        .catch(() => {
          alert("엔트리 추가에 실패했습니다.");
        })
        .finally(() => {
          this.$emit("refresh");
        })
    }
    
  },
  filters: {
    typeFilter(value) {
      if (value < 3) {
        //팀원
        return "참";
      } else {
        return "대기";
      }
    },
    showTotalEntries(value) {
      let totalEntry = value.length;
      return totalEntry;
    }
  }
};
</script>

<style></style>
