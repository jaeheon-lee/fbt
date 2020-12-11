export default {
  name: "match-result-insert",
  props: {
    votes: Array
  },
  data() {
    return {
      // ============= 받는 vo =====================//
      // vote vo
      vote: {},
      // 팀원 리스트
      teamMembers: [],
      // 용병/지인 리스트
      friendEmploys: [],

      // ============= 보낼 vo =====================//
      // 스코어 결과
      matchResult: {
        matchSchedule: {
          matchScheduleId: null
        },
        homeResult: null,
        awayResult: null,
        homeScore: 0,
        awayScore: 0,
        isSet: "0"
      },
      //엔트리
      teamEntries: [], //팀원 엔트리 임시 보관
      empEntries: [], // 용병 엔트리 임시 보관
      // 용병 경기, 매너점수
      empScores: [],
      // 팀 경기, 매너점수
      teamScore: {
        mannerPromise: 0,
        mannerContact: 0,
        mannerRule: 0,
        mannerBodyFight: 0,
        mannerSlang: 0,
        mannerSmoking: 0,
        mannerUniform: 0,
        mannerPayment: 0,
        mannerArrangement: 0,
        mannerReferee: 0,
        mannerTackle: 0,
        teamAbility: 0,
        forward: 0,
        middle: 0,
        defence: 0,
        matchSchedule: {
          matchScheduleId: null
        },
        teamGiver: {
          teamId: null
        },
        teamTaker: {
          teamId: null
        },
        user: {
          email: null
        }
      },

      // ============== 페이지 관리 변수들 ====================//
      // stepper 페이지 변수
      e1: 1,

      // 팀 선택 관련 변수
      awayTeamSelectActive: false, // true면 팀 선택 창 등록

      // 불러오기 버튼 관리
      loadMemberBtnClicked: false,
      loadFriendEmployBtnClicked: false,

      // 상대팀 유형 관련 변수
      dialogAwayTeam: false, // 상대팀 부분 dailog
      awayTeamType: "1", //{1: 자체, 2: 상대팀}
      inputTeamName: null, // 검색할 팀 명 받기
      awayTeams: [], //검색한 팀을 받을 JSON

      //팀원 추가 관련 변수
      dialogTeamMember: false,
      inputTeamMemberName: null,
      searchedTeamMember: [],
      teamMemberAdded: [],

      // 경기력 관련 변수
      tickLabels: ["하하", "중하", "중중", "중상", "상상"],
      abilityActive: null,

      // 매너 관련 변수
      mannerLabels: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      mannerActive: null
    };
  },
  created() {
    if (this.votes.length > 0) {
      this.vote = this.votes[0];
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.matchResult.matchSchedule.matchScheduleId = this.vote.matchSchedule.matchScheduleId;
      this.teamScore.matchSchedule.matchScheduleId = this.vote.matchSchedule.matchScheduleId;
      this.teamScore.teamGiver.teamId = teamId;
      if (this.vote.matchSchedule.awayTeam) {
        if (teamId == this.vote.matchSchedule.homeTeam.teamId) {
          this.teamScore.teamTaker.teamId = this.vote.matchSchedule.awayTeam.teamId;
        } else {
          this.teamScore.teamTaker.teamId = this.vote.matchSchedule.homeTeam.teamId;
        }
      }
    }
  },
  mounted() {
    this.vote = this.votes[0];
  },
  computed: {
    awayTeamName: function() {
      if (!this.vote.matchSchedule.awayTeam) {
        this.awayTeamSelectActive = true;
        return "미정";
      } else {
        return this.vote.matchSchedule.awayTeam.teamName;
      }
    },
    teamMembersLoaded: function() {
      return this.teamMembers;
    },
    controlAwayTeamResult: function() {
      if (this.vote.matchSchedule.awayTeam) {
        let awayTeamId = this.vote.matchSchedule.awayTeam.teamId;
        let homeTeamId = this.vote.matchSchedule.homeTeam.teamId;
        if (awayTeamId == 0 || awayTeamId == homeTeamId) {
          return false;
        }
        return true;
      }
    },
    isInnerMatch: function() {
      let homeTeam = this.vote.matchSchedule.homeTeam;
      let awayTeam = this.vote.matchSchedule.awayTeam;
      // eslint-disable-next-line prettier/prettier
      // 내전이라면
      if (awayTeam && homeTeam.teamId == awayTeam.teamId) {
        return false; // 스코어창을 숨긴다
      }
      // 내전이 아니라면
      return true; // 스코어창을 출력
    }
  },
  methods: {
    // ============== 스코어 입력 ====================== //
    // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team) {
        if (team.emblem) return require("@/assets/image/emblem/" + team.emblem);
      } else {
        return require("@/assets/image/emblem/emptyFC.png");
      }
    },
    // ============== 팀원 출결 입력 ====================== //
    // 투표로부터 팀원 정보 가져오기
    loadTeamMemebers() {
      // let voteMatchId = this.vote.voteMatchId;
      let voteMatchId = '1-1';
      this.loading = true;
      this.$axios
        .get("/match-schedule/3/" + voteMatchId)
        .then(response => {
          this.teamMembers = response.data;
          for (let i = 0; i < this.teamMembers.length; i++) {
            let entry = {
              attendance: 2,
              type: 0,
              matchScore: 0,
              goal: 0,
              assist: 0,
              matchSchedule: {
                matchScheduleId: this.vote.matchSchedule.matchScheduleId
              },
              teamMember: {
                teamMemberId: null
              },
              user: {
                email: null
              }
            };
            entry.teamMember.teamMemberId = this.teamMembers[i].teamMemberId;
            this.teamEntries.push(entry);
          }
          this.loadMemberBtnClicked = true;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // ============== 용병/지인 출결 입력 ====================== //
    loadEmpFriend() {
      // let voteMatchId = this.vote.voteMatchId;
      let voteMatchId = '1-1';
      let matchScheduleId = this.vote.matchSchedule.matchScheduleId;
      this.loading = true;
      this.$axios
        .get("/match-schedule/4/" + voteMatchId + "/" + matchScheduleId)
        .then(response => {
          let friendEmployMap = response.data;
          let friendList = friendEmployMap.friendList;
          let employList = friendEmployMap.employList;

          for (let i = 0; i < friendList.length; i++) {
            // entry에 삽입
            let entry = {
              attendance: 2,
              type: 1,
              matchScore: 0,
              goal: 0,
              assist: 0,
              matchSchedule: {
                matchScheduleId: this.vote.matchSchedule.matchScheduleId
              },
              teamMember: {
                teamMemberId: null
              },
              user: {
                email: friendList[i].email
              }
            };
            this.empEntries.push(entry);
            // empScore에 삽입
            let empScore = {
              mannerPromise: 0,
              mannerContact: 0,
              mannerRule: 0,
              mannerBodyFight: 0,
              mannerSlang: 0,
              mannerSmoking: 0,
              mannerUniform: 0,
              mannerPayment: 0,
              mannerArrangement: 0,
              mannerReferee: 0,
              mannerTackle: 0,
              empAbility: 0,
              forward: 0,
              middle: 0,
              defence: 0,
              matchSchedule: {
                matchScheduleId: this.vote.matchSchedule.matchScheduleId
              },
              teamGiver: {
                teamId: JSON.parse(sessionStorage.getItem("userInfo")).teamId
              },
              user: {
                email: friendList[i].email
              }
            };
            this.empScores.push(empScore);
          }
          for (let i = 0; i < employList.length; i++) {
            // entry에 삽입
            let entry = {
              attendance: 2,
              type: 2,
              matchScore: 0,
              goal: 0,
              assist: 0,
              matchSchedule: {
                matchScheduleId: this.vote.matchSchedule.matchScheduleId
              },
              teamMember: {
                teamMemberId: null
              },
              user: {
                email: employList[i].email
              }
            };
            this.empEntries.push(entry);
            // empScore에 삽입
            let empScore = {
              mannerPromise: 0,
              mannerContact: 0,
              mannerRule: 0,
              mannerBodyFight: 0,
              mannerSlang: 0,
              mannerSmoking: 0,
              mannerUniform: 0,
              mannerPayment: 0,
              mannerArrangement: 0,
              mannerReferee: 0,
              mannerTackle: 0,
              empAbility: 0,
              forward: 0,
              middle: 0,
              defence: 0,
              matchSchedule: {
                matchScheduleId: this.vote.matchSchedule.matchScheduleId
              },
              teamGiver: {
                teamId: JSON.parse(sessionStorage.getItem("userInfo")).teamId
              },
              user: {
                email: employList[i].email
              }
            };
            this.empScores.push(empScore);
          }
          this.friendEmploys = friendList.concat(employList);
          this.loadFriendEmployBtnClicked = true;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // ============== 상대 유형 입력 ====================== //
    // 상대팀 유형 메소드
    // 1: Radio를 통해 바꾸기 (자체 or 미정)
    chooseAwayTeamTypeByRadio() {
      if (this.awayTeamType == 1) {
        this.vote.matchSchedule.awayTeam = this.vote.matchSchedule.homeTeam;
      }
    },
    // 2: 상대팀명 검색을 통해 상대팀 지정하기(상대팀)
    selectAwayTeam(awayTeam) {
      this.vote.matchSchedule.awayTeam = awayTeam;
      this.awayTeamSelectActive = false;
      this.dialogAwayTeam = false;
    },
    //3. 상대팀명 찾기
    // T006: 팀 검색
    searchTeams() {
      this.loading = true;
      this.$axios
        .get(
          "/team/" +
            this.inputTeamName +
            "?homeTeamId=" +
            this.vote.matchSchedule.homeTeam.teamId
        )
        .then(response => {
          this.awayTeams = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    //4. 최종 확인하기
    selectTeamOk() {
      if (this.awayTeamType == "1") {
        this.chooseAwayTeamTypeByRadio();
        this.teamScore.teamTaker.teamId = this.vote.matchSchedule.awayTeam.teamId;
      }
      this.awayTeamSelectActive = false;
      this.dialogAwayTeam = false;
    },
    // ============== 팀원 검색====================== //
    searchTeamMember() {
      this.loading = true;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.$axios
        .get("/team-member/1/" + teamId + "/" + this.inputTeamMemberName)
        .then(response => {
          // 기존의 결과 비우기
          this.searchedTeamMember = [];
          // 임시로 받기
          let teamMemberTemp = response.data;
          //임시로 받은 것 중에서 기존에 없느 사람만 추가 + 추가했는지 여부를 알리는 리스트에 false 넣기
          for (let i = 0; i < teamMemberTemp.length; i++) {
            let teamMemberId1 = teamMemberTemp[i].teamMemberId;
            this.teamMemberAdded.push(false);
            // eslint-disable-next-line prettier/prettier
            let index = this.teamMembers.map(x => x.teamMemberId).indexOf(teamMemberId1);
            if (index == -1) this.searchedTeamMember.push(teamMemberTemp[i]);
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 해당 팀 멤버 추가
    addTeamMember(teamMember, i) {
      this.teamMembers.push(teamMember);
      let entry = {
        attendance: 2,
        type: null,
        matchScore: 0,
        goal: 0,
        assist: 0,
        matchSchedule: {
          matchScheduleId: this.vote.matchSchedule.matchScheduleId
        },
        teamMember: {
          teamMemberId: null
        },
        user: {
          email: null
        }
      };
      entry.teamMember = teamMember;
      this.teamEntries.push(entry);
      this.teamMemberAdded[i] = true;
    },
    // ============== 용병 경기력/매너 입력====================== //
    toggleAbility(i) {
      if (this.abilityActive == i) this.abilityActive = null;
      else this.abilityActive = i;
    },
    toggleManner(i) {
      if (this.mannerActive == i) this.mannerActive = null;
      else this.mannerActive = i;
    },
    // ============== 제출====================== //
    submitMatchResult() {
      let entries = this.teamEntries.concat(this.empEntries);
      let matchResultCollection = {
        entries: entries,
        empScores: this.empScores,
        teamScore: this.teamScore,
        matchResult: this.matchResult
      };
      this.$axios
        .post("/match-schedule/2", matchResultCollection)
        .then(() => {
          alert("입력이 완료됐습니다.");
        })
        .catch(() => {
          this.errored = true;
          alert("입력에 실패했습니다.");
        })
        .finally(() => {
          // const router = this.$router;
          // router.push({
          //   name: "scheduleManager"
          // });
        });
    }
  }
};
