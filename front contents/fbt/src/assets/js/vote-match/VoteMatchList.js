import axios from "axios";
import KakaoLink from "@/components/Common/KakaoLink.vue";
import VoteBtn from "@/components/VoteMatch/button/VoteBtn.vue";
import WaitCancelBtn from "@/components/Schedule/button/WaitCancelBtn.vue";
import FriendMemberList from "@/components/VoteMatch/toggle/FriendMemberList.vue";
import MemberList from "@/components/Schedule/toggle/MemberList.vue";

export default {
  name: "voteMatchList",
  props: {
    // 어느 부모페이지에서 왔는지
    header: String,
    // From VoteMatch
    isEnd: Boolean,
    isManager: Boolean,
    // ScheduleBody
    awayVote: Object,
    // From ScheduleBody & VoteMatch
    votes: Array
  },
  components: {
    "kakao-link": KakaoLink,
    "vote-btn": VoteBtn,
    "wait-cancel-btn": WaitCancelBtn,
    "friend-member-list": FriendMemberList,
    "member-list": MemberList
  },
  data() {
    return {
      // 투표 변수
      voteMatchResults: [],

      // 용병 점수 변수
      empScores: [],

      // 상세 정보 관련 변수
      activeDetail: null, // 해당 인덱스면 해당 인덱스를 가진 카드의 상세 정보 창을 연다.

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
      activeEmpScoreList: null, //empScore

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
    // 투표하기 (FV05, FV06, FS05 ,FS06)
    doVote(vote, result) {
      // 필요한 정보를 담는다.
      // 지인 투표인지 확인하고 지인과 팀원 투표에 따라 변수를 달리 담는다
      let voteMatchResult = {
        voteMatchId: null,
        attendance: null,
        teamMember: {
          teamMemberId: null
        },
        user: {
          email: null
        }
      };
      voteMatchResult.voteMatchId = vote.voteMatchId;
      voteMatchResult.attendance = result;
      if (!vote.isFriend) {
        voteMatchResult.teamMember.teamMemberId = JSON.parse(
          sessionStorage.getItem("userInfo")
        ).teamMemberId;
        voteMatchResult.user = null;
      } else {
        voteMatchResult.user.email = JSON.parse(
          sessionStorage.getItem("userInfo")
        ).email;
        voteMatchResult.teamMember = null;
      }
      vote.voteMatchResult = voteMatchResult;
      // 알람에 필요한 정보를 받는다
      let teamName = JSON.parse(sessionStorage.getItem("userInfo")).teamName;

      // 수정인지 등록인지 판별한다. 지인과 팀원에 따라
      let idx = -1;
      let teamMemberId = JSON.parse(sessionStorage.getItem("userInfo"))
        .teamMemberId;
      let email =
        JSON.parse(sessionStorage.getItem("userInfo")).email + " (지인)";
      console.log(vote);
      if (vote.voteMatchResults) {
        if (!vote.isFriend) {
          //팀원이면
          for (let i = 0; i < vote.voteMatchResults.length; i++) {
            let member = vote.voteMatchResults[i].teamMember;
            if (!member) continue;
            if (member.teamMemberId == teamMemberId) {
              idx = i;
              break;
            }
          }
        } else {
          //지인이면
          for (let i = 0; i < vote.voteMatchResults.length; i++) {
            let user = vote.voteMatchResults[i].user;
            if (!user) continue;
            if (user.email == email) {
              idx = i;
              break;
            }
          }
        }
      }
      // 투표를 하지 않았다면 => 등록 (Fv05, FV06)
      if (idx == -1) {
        axios
          .post("/vote-match-result?teamName=" + teamName, vote)
          .then(() => {
            alert("투표가 완료됐습니다.");
          })
          .catch(() => {
            alert("투표 중 오류가 발생했습니다.");
          })
          .finally(() => {
            this.refresh(vote.matchSchedule.matchScheduleId);
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
            this.refresh(vote.matchSchedule.matchScheduleId);
          });
      }
      this.$emit("refresh");
    },
    // 투표 마감하기 FV07
    endVote(vote) {
      let teamName = JSON.parse(sessionStorage.getItem("userInfo")).teamName;
      vote.voteStatus = 1;
      axios
        .put("/vote-match/1?teamName=" + teamName, vote)
        .then(() => {
          alert("투표가 마감됐습니다.");
          this.sendingVote = vote;
          this.dialogKakao = true;
        })
        .catch(() => {
          alert("투표 마감에 실패했습니다.");
        })
        .finally(() => {
          this.refresh(vote.matchSchedule.matchScheduleId);
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
          this.refresh(vote.matchSchedule.matchScheduleId);
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
          this.refresh(vote.matchSchedule.matchScheduleId);
        });
    },
    // 매칭 등록으로 넘어가기(FV11, FV12, FV13)
    moveToRegister(vote, i) {
      const router = this.$router;
      let name = "";
      // 용병, 상대팀, 양도 등록페이지로 넘어가기
      switch (i) {
        case 0: // FV12
          name = "employManager";
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
    // 투표 상 경기 확정하기 (FV16)
    confirmMatchSchedule(vote) {
      let matchScheduleId = vote.matchSchedule.matchScheduleId;
      let homeTeamId = vote.matchSchedule.homeTeam.teamId;
      this.$axios
        // eslint-disable-next-line prettier/prettier
        .put("/match-schedule/1/" + matchScheduleId + "/" + homeTeamId)
        .then(() => {
          alert("경기가 확정됐습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("경기확정에 실패했습니다.");
        })
        .finally(() => {
          this.refresh();
        });
    },
    // FU02
    showEmpScore(matchScheduleId) {
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      this.$axios
        .get("/user/5/" + email + "/" + matchScheduleId)
        .then(response => {
          this.empScores = response.data;
          for (let i = 0; i < this.empScores.length; i++) {
            let empScore = this.empScores[i];
            let empManner =
              empScore.mannerPromise +
              empScore.mannerContact +
              empScore.mannerRule +
              empScore.mannerBodyFight +
              empScore.mannerSlang +
              empScore.mannerSmoking +
              empScore.mannerUniform +
              empScore.mannerPayment +
              empScore.mannerArrangement +
              empScore.mannerReferee +
              empScore.mannerTackle;
            empManner /= 11;
            let empAbility =
              empScore.forward + empScore.middle + empScore.defence;
            empAbility /= 3;
            empScore.empManner = empManner;
            empScore.empAbility = empAbility;
            this.empScores[i] = empScore;
          }
        })
        .catch(error => {
          console.log(error);
          alert("점수를 받아오는 데 실패했습니다.");
        })
        .finally();
    },
    //FS18
    updateSchedule(vote) {
      this.$router.push({
        name: "scheduleUpdate",
        params: {
          vote: vote
        }
      });
    },
    //FS19
    deleteSchedule(vote) {
      let matchScheduleId = vote.matchSchedule.matchScheduleId;
      this.$axios
        .delete("/match-schedule/" + matchScheduleId)
        .then(() => {
          alert("일정이 삭제되었습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("일정 삭제에 실패했습니다.");
        })
        .finally(() => {
          this.$emit("refresh-monthly");
        });
    },
    // 새로고침
    refresh(matchScheduleId) {
      this.$emit("refresh", null, matchScheduleId);
    },
    // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team) {
        return require("@/assets/image/emblem/" + team.emblem);
      } else {
        return require("@/assets/image/emblem/emptyFC.svg");
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
    // ========================== 창 컨트롤 메소드===============================//
    // 상세정보 창 여닫기
    openDetail(i) {
      if (this.activeDetail == i) this.activeDetail = null;
      else this.activeDetail = i;
    },
    // 참여명단보기 창 여닫기
    openAttendList(i) {
      if (this.activeAttendList == i) this.activeAttendList = null;
      // 명단이 열려있다면 명단 닫기
      else {
        this.activeAttendList = i;
      }
    },
    // 팀 받은점수보기 창 여닫기
    openTeamScoreList(i) {
      if (this.activeScoreList == i) this.activeScoreList = null;
      // 명단이 열려있다면 명단 닫기
      else {
        this.activeScoreList = i;
      }
    },
    // 용병 받은점수보기 창 여닫기
    controlEmpScoreList(i, vote) {
      if (this.activeEmpScoreList == i) {
        this.activeEmpScoreList = null;
      } else {
        let matchScheduleId = vote.matchSchedule.matchScheduleId;
        this.showEmpScore(matchScheduleId);
        this.activeEmpScoreList = i;
      }
    },
    //스코어 창 여닫기
    controlScore(vote) {
      if (vote.matchSchedule.matchResult) return true;
      else return false;
    },
    // 카카오 링크
    closeKaokao() {
      this.dialogKakao = false;
      this.$emit("close");
    },
    // ========================== 버튼 컨트롤 메소드===============================//
    // 관리버튼 전체 관리
    controlManagerBtn(vote) {
      if (
        this.header.slice(-7, this.header.length) == "Manager" &&
        !vote.isEndMatch
      )
        return true;
      else return false;
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
