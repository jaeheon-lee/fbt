import axios from "axios";
import KakaoLink from "@/components/Common/KakaoLink.vue";

export default {
  name: "voteMatchList",
  props: {
    // 어느 부모페이지에서 왔는지
    header: String,
    // From VoteMatch
    isEnd: Boolean,
    isManager: Boolean,
    // From ScheduleBody & VoteMatch
    votes: Array
  },
  components: {
    "kakao-link": KakaoLink
  },
  data() {
    return {
      // 투표 변수
      voteMatchResults: [],

      // 상세 정보 관련 변수
      activeDetail: null, // 해당 인덱스면 해당 인덱스를 가진 카드의 상세 정보 창을 연다.

      // 지인 초대 관련 변수
      inputEmail: null, //검색할 이메일
      friends: [],

      activeMemberList: null, //해당 인덱스이면 토글 연다
      activeFriendList: null, //해당 인덱스이면 토글 연다
      openType: -1, // 0이면 명단만을 연다 , 1이면 초대만을 연다

      // 일반 버튼 관련 변수
      attendBtnActive: false, // voteStatus = 1 이면 true => 참불 버튼은 연다
      waitBtnActive: false, // 지인 초대 버튼을 연다
      cancelBtnActive: false, // true면 참석 취소하기, false면 대기하기

      //관리자 버튼 관련 변수
      additionBtnActive: false, // 추가인원받기 버튼

      //일정에서 정보 보기할 때 투표 관련 창을 숨기는 변수
      isOnlySchedule: false, // true면 투표 관련된 UI를 숨긴다.

      // 경기참여명단 관련 변수
      activeAttendList: null,

      // 받은 점수 관련 변수
      activeScoreList: null,

      // 카카오 공유하기 관련 변수
      dialogKakao: null,
      sendingVote: null,

      //기본 변수
      errored: false,
      loading: true
    };
  },
  mounted() {},
  computed: {
    votes2: function() {
      return this.votes;
    }
  },
  watch: {
    // 진행 중, 마감으로 각각 넘어갈 때 상세보기 창이 열려 있으면 창 닫기
    isEnd: function() {
      this.activeDetail = null;
    }
  },
  methods: {
    // ========================== 버튼 눌렀을 때 메소드 ===============================//
    // 투표하기 (FV05, FV06)
    doVote(vote, result) {
      // 필요한 정보를 담는다.
      // eslint-disable-next-line prettier/prettier
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo")).teamMemberId;
      let voteMatchResult = {
        voteMatchId: vote.voteMatchId,
        teamMember: {
          teamMemberId: teamMemberId
        },
        attendance: result
      };
      vote.voteMatchResult = voteMatchResult;

      // 수정인지 등록인지 판별한다
      // eslint-disable-next-line prettier/prettier
      let idx = vote.voteMatchResults.map(x=> x.teamMember.teamMemberId).indexOf(teamMemberId);
      // 투표를 하지 않았다면 => 등록 (Fv05, FV06)
      if (idx == -1) {
        axios
          .post("/vote-match-result", vote)
          .then(() => {
            alert("투표가 완료됐습니다.");
          })
          .catch(() => {
            alert("투표 중 오류가 발생했습니다.");
          })
          .finally(() => {
            this.refresh();
          });
      } else {
        // 이미 투표를 했다면 => 수정 (FV05)
        axios
          .put("/vote-match-result", voteMatchResult)
          .then(() => {
            alert("투표가 수정됐습니다.");
          })
          .catch(() => {
            alert("수정 중 오류가 발생했습니다.");
          })
          .finally(() => {
            this.refresh();
          });
      }
    },
    // 투표 마감하기 FV07
    endVote(vote) {
      vote.voteStatus = 1;
      axios
        .put("/vote-match/1", vote)
        .then(() => {
          alert("투표가 마감됐습니다.");
          this.sendingVote = vote;
          this.dialogKakao = true;
        })
        .catch(() => {
          alert("투표 마감에 실패했습니다.");
        })
        .finally(() => {
          this.refresh();
        });
    },
    // 경기 취소하기 FV08
    deleteVoteMatch(vote) {
      axios
        .delete("/match-schedule/" + vote.matchSchedule.matchScheduleId)
        .then(() => {
          alert("취소가 완료됐습니다.");
        })
        .catch(() => {
          alert("취소에 실패했습니다.");
        })
        .finally(() => {
          this.refresh();
        });
    },
    // 지인찾기 FV09
    searchFriend() {
      axios
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
      let invite = {
        teamMember: {
          teamMemberId: JSON.parse(sessionStorage.getItem("userInfo"))
            .teamMemberId
        },
        user: {
          email: email
        },
        voteMatch: {
          voteMatchId: voteMatchId
        }
      };
      axios
        .post("/vote-match/invite", invite)
        .then(() => {
          alert("초대가 완료됐습니다.");
          this.dialogKakao = true;
        })
        .catch(() => {
          alert("초대에 실패했습니다.");
        });
    },
    // 추가 인원받기: FV10
    updateSetting(type, vote) {
      let notion = "";
      let voteMatchSetting = vote.voteMatchSetting;
      switch (type) {
        case "setWaiting":
          voteMatchSetting.waiting = true;
          notion = "추가인원하기가 설정되었습니다.";
          break;
      }
      axios
        .put("/vote-match-setting", voteMatchSetting)
        .then(() => {
          alert(notion);
        })
        .catch(() => {
          alert("설정에 실패했습니다.");
        })
        .finally(() => {
          this.refresh();
        });
    },
    // 매칭 등록으로 넘어가기(FV11, FV12, FV13)
    moveToRegister(vote, i) {
      const router = this.$router;
      let name = "";
      // 용병, 상대팀, 양도 등록페이지로 넘어가기
      switch (i) {
        case 0: // FV12
          name = "employ";
          break;
        case 1: // FV11
          name = "search";
          break;
        case 2: //FV13
          name = "assign";
          break;
      }
      router.push({
        name: name,
        params: {
          menu: 1, // 등록페이지 인덱스
          matchScheduleId: vote.matchSchedule.matchScheduleId // 불러오기할 때 필요한 것
        }
      });
    },
    // 투표 수정하기 (FV14)
    updateVoteMatch(vote) {
      this.$router.push({
        name: "voteMatchUpdate",
        params: {
          vote: vote
        }
      });
    },
    // 새로고침
    refresh() {
      this.$parent.showVoteInfo();
    },
    // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team) {
        return require("@/assets/image/emblem/" + team.emblem);
      } else {
        return require("@/assets/image/emblem/emptyFC.png");
      }
    },
    // 이메일, 닉네임으로 구분
    getAttendName(entry) {
      if (entry.user) {
        return entry.user.email;
      } else {
        return entry.teamMember.nickName;
      }
    },
    // 점수 부여한 사람 이메일, 팀명으로 출력
    getGiverName(teamScore) {
      if (teamScore.teamGiver) {
        return teamScore.teamGiver.teamName;
      } else {
        return teamScore.user.email;
      }
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
    // ========================== 창 컨트롤 메소드===============================//
    // 상세정보 창 여닫기
    openDetail(i) {
      if (this.activeDetail == i) this.activeDetail = null;
      else this.activeDetail = i;
    },
    // 명단보기 창 여닫기
    openMemberList(i) {
      if (this.activeMemberList == i) this.activeMemberList = null;
      // 명단이 열려있다면 명단 닫기
      else {
        if (this.activeFriendList == i) this.activeFriendList = null; // 명단도 열려 있다면 명단 닫기
        this.activeMemberList = i;
        this.openType = 0;
      }
    },
    // 참여명단보기 창 여닫기
    openAttendList(i) {
      if (this.activeAttendList == i) this.activeAttendList = null;
      // 명단이 열려있다면 명단 닫기
      else {
        this.activeAttendList = i;
      }
    },
    // 받은점수보기 창 여닫기
    openScoreList(i) {
      if (this.activeScoreList == i) this.activeScoreList = null;
      // 명단이 열려있다면 명단 닫기
      else {
        this.activeScoreList = i;
      }
    },
    closeMemberList() {
      this.activeMemberList = null;
    },
    // 지인 초대 창 여닫기
    openFriendList(i) {
      if (this.activeFriendList == i) this.activeFriendList = null;
      // 초대가 열려있다면 초대 닫기
      else {
        if (this.activeMemberList == i) this.activeMemberList = null; // 명단도 열려 있다면 명단 닫기
        this.activeFriendList = i;
        this.openType = 1;
      }
    },
    closeFriendList() {
      this.activeFriendList = null;
    },
    closeKaokao() {
      this.dialogKakao = false;
      this.$emit("close");
    },
    // ========================== 버튼 컨트롤 메소드===============================//
    // 스케줄 페이지에서 참불 버튼 관리
    controlAttendBtnOnSchedule(vote) {
      //1. 투표 마감날이 지나면 표시 하지 않는다.
      let today = new Date();
      today = this.$moment(today).format("YYYY-MM-DD HH:mm:ss");
      if (vote.dueDate < today) return false;

      //2. 투표가 마감 되면 표시 하지 않는다.
      if (vote.voteStatus == 1) return false;
      else return true;
    },
    // 대기, 참석취소 버튼 관리
    controlWaitBtn(vote) {
      // 1. 투표 마감날 전 또는 경기 시작시간 지나면 표시하지 않는다.
      let today = new Date();
      today = this.$moment(today).format("YYYY-MM-DD HH:mm:ss");
      if (vote.dueDate > today || vote.matchSchedule.startTime < today)
        return false;

      //2. 다음 로직 따른다
      // eslint-disable-next-line prettier/prettier
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo")).teamMemberId;
      // 로그인한 사람이 투표했는지, 했으면 해당 인덱스를 안 했으면 -1 리턴
      let index = vote.voteMatchResults
        .map(x => x.teamMember.teamMemberId)
        .indexOf(teamMemberId);
      // 참석을 눌렀는지
      let attendance = -1;
      if (index != -1) attendance = vote.voteMatchResults[index].attendance;
      if (vote.voteMatchSetting.waiting == true && attendance != 1) {
        // 대기 허용이고 참석을 누르지 않았다면 대기 버튼 출력
        return true;
      } else {
        // 대기 불용이거나 참석을 누르지 않았다면 대기 버튼 숨기기
        return false;
      }
    },
    controlCancelBtn(vote) {
      //1.
      let today = new Date();
      today = this.$moment(today).format("YYYY-MM-DD HH:mm:ss");
      if (vote.dueDate > today || vote.matchSchedule.startTime < today)
        return false;

      //2. 다음 로직을 따른다
      // eslint-disable-next-line prettier/prettier
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo")).teamMemberId;
      // 로그인한 사람이 투표했는지, 했으면 해당 인덱스를 안 했으면 -1 리턴
      let index = vote.voteMatchResults
        .map(x => x.teamMember.teamMemberId)
        .indexOf(teamMemberId);
      // 참석을 눌렀는지
      let attendance = -1;
      if (index != -1) attendance = vote.voteMatchResults[index].attendance;
      // 이미 투표 & 참석=>  취소 가능
      if (attendance == 1) {
        return true;
      } else {
        // 아니면 취소 불가
        return false;
      }
    },
    //추가인원받기 버튼 조절 메소드
    controlAdditionBtn(vote) {
      let cancelNumber = vote.voteMatchSetting.cancelNumber;
      let totalAttend = vote.totalAttend;
      let waiting = vote.voteMatchSetting.waiting;
      // eslint-disable-next-line prettier/prettier
      if (vote.voteStatus == 1 && totalAttend > cancelNumber && !waiting){
        // 마감 + 목표인원 도달했을 때 + 대기인원 안 받을 때
        return true;
      } else {
        return false;
      }
    },
    // 상대팀찾기 버튼 조절 변수
    controlSearchBtn(vote) {
      let cancelNumber = vote.voteMatchSetting.cancelNumber;
      let totalAttend = vote.totalAttend;
      let awayTeam = vote.matchSchedule.awayTeam;
      // eslint-disable-next-line prettier/prettier
      if (vote.voteStatus == 1 && totalAttend < cancelNumber && !awayTeam){
        // 마감되고 목표인원 도달하지 않았을 때 + 상대팀이 없을 때
        return true;
      } else {
        return false;
      }
    },
    // 양도하기 버튼 조절 변수
    controlAssignmentBtn(vote) {
      let cancelNumber = vote.voteMatchSetting.cancelNumber;
      let totalAttend = vote.totalAttend;
      let homeTeamId = vote.matchSchedule.homeTeam.teamId;
      let sessionTeamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      // eslint-disable-next-line prettier/prettier
      if (vote.voteStatus == 1 && totalAttend < cancelNumber && homeTeamId == sessionTeamId){
        // 마감되고 목표인원 도달하지 않았을 때 + 홈팀일 때
        return true;
      } else {
        return false;
      }
    },
    // 용병찾기 버튼 조절 메소드
    controlEmployBtn(vote) {
      let cancelNumber = vote.voteMatchSetting.cancelNumber;
      let totalAttend = vote.totalAttend;
      if (vote.voteStatus == 1 && totalAttend < cancelNumber) {
        // 마감됨 + 목표인원에 도달하지 않았을 때
        return true;
      } else {
        return false;
      }
    },
    // 투표수정하기 버튼 조절 메소드
    controlUpdateBtn(vote) {
      // 투표 마감 전에만 수정 가능하다
      if (vote.voteStatus == 0) return true;
      else return false;
    }
    /* 투표 페이지 관련 메소드 -------------------------------------------*/
  },
  filters: {
    extractSecond(value) {
      if (value) {
        return value.slice(0, 16);
      }
    },
    showOnlyTime(value) {
      return value.slice(11, 16);
    },
    showMatchType(value) {
      let matchType = "축구";
      if (value.split(":")[0] < 11) matchType = "풋살";
      return value + " " + matchType + " 경기";
    },
    isEmp(value) {
      if (value) {
        if (value.empDueDate) return "용병 경기";
        else return "팀 경기";
      }
    },
    showAwayTeamName(value) {
      if (value) {
        return value.teamName;
      } else {
        return "미정";
      }
    },
    showParking(value) {
      if (value == 1) return "주차가능";
      else return "주차불가능";
    },
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
    // ================== 경기 참여 명단 ============================//
    countTotalNum(entries) {
      return entries.length;
    },
    countAttendNum(entries) {
      return entries.filter(entry => entry.attendance == 2).length;
    },
    countLateNum(entries) {
      return entries.filter(entry => entry.attendance == 1).length;
    },
    countAbsentNum(entries) {
      return entries.filter(entry => entry.attendance == 0).length;
    },
    countMemberNum(entries) {
      return entries.filter(entry => entry.type == 0).length;
    },
    countFriendNum(entries) {
      return entries.filter(entry => entry.type == 1).length;
    },
    countEmpNum(entries) {
      return entries.filter(entry => entry.type == 2).length;
    },
    entryTypeFilter(value) {
      switch (value) {
        case 0:
          return "팀원";
        case 1:
          return "지인";
        case 2:
          return "용병";
      }
    },
    entryAttendFilter(value) {
      switch (value) {
        case 0:
          return "결석";
        case 1:
          return "지각";
        case 2:
          return "출석";
      }
    },
    // ======================= 받은 점수 =========================//
    showTeamScore(value) {
      if (value < 2) {
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
