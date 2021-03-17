export default {
  name: "match-result-insert",
  props: {
    votes: Array,
    isUpdate: Boolean
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
    this.setBasicInfo();
  },
  mounted() {
    this.vote = this.votes[0];
    
    if (this.isUpdate) {
      this.loadRemainInfo();
    } else {
      this.getEntryByTeamSchedule();
    }
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
    controlScoreInsert: function() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let homeTeamId = this.vote.matchSchedule.homeTeam.teamId;
      if (teamId == homeTeamId) return true;
      else return false;
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
    setBasicInfo() {
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
    // ============== 스코어 입력 ====================== //
    // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team) {
        return this.$emblem + team.emblem;
      } else {
        return this.$emblem + "emptyFC.svg";
      }
    },
    // ============== 엔트리 불러오기 ====================== //
    getEntryByTeamSchedule() {
      let matchScheduleId = this.vote.matchSchedule.matchScheduleId;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.$axios
        .get("/entry/1/" + teamId + "/" + matchScheduleId)
        .then(response => {
          let entries = response.data;
          this.classifyEntry(entries);
        })
        .catch(() => {
          alert("엔트리를 불러오는 데 실패했습니다.");
        });
    },
    // 타입별 분류
    classifyEntry(entries) {
      console.log(entries);
      for(let i=0;i<entries.length;i++) {
        let entry = entries[i];
        if(entry.type == 0) {
          this.teamMembers.push(entry)
        } else if (entry.type == 1 || entry.type == 2) {
          if (!this.isUpdate) this.setEmptyEmpScore(entry);
          this.friendEmploys.push(entry);
        }
      }
    },
    // 용병/지인 경기력/매너 껍데기 넣기
    setEmptyEmpScore(entry) {
      
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
          email: entry.user.email
        },
        team: {
          teamId: JSON.parse(sessionStorage.getItem("userInfo")).teamId
        }
      };
      entry.empScore = empScore;
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

          this.searchedTeamMember = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    // 해당 팀 멤버 추가
    addTeamMember(teamMember) {
      let result = this.checkIsInEntry(teamMember);
      if (!result) {
        alert("이미 추가된 맴버입니다.");
        return false;
      }
      let entry = {
        attendance: 2,
        type: null,
        matchScore: 0,
        goal: 0,
        assist: 0,
        matchSchedule: {
          matchScheduleId: this.vote.matchSchedule.matchScheduleId
        },
        user: {
          email: null
        },
        team: {
          teamId: JSON.parse(sessionStorage.getItem("userInfo")).teamId
        }
      };
      entry.isNew = true;
      entry.teamMember = teamMember;
      this.teamMembers.push(entry);
    },
    // 기존 엔트리에 있는지 체크
    checkIsInEntry(teamMember) {
      for (let i=0;i<this.teamMembers.length;i++) {
        let remainId = this.teamMembers[i].teamMember.teamMemberId;
        if (remainId == teamMember.teamMemberId) {
          return false;
        }
      }
      return true;
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
    //FS08
    submitMatchResult() {
      let isHomeTeam = this.checkIsHomeTeam();
      let entries = this.teamMembers.concat(this.friendEmploys);
      let matchResultCollection = {
        entries: entries,
        teamScore: this.teamScore,
        matchResult: this.matchResult,
        isHomeTeam: isHomeTeam
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
          location.reload();
        });
    },
    // 홈팀인지 아닌지 체크하는 변수
    checkIsHomeTeam() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let matchSchedule = this.vote.matchSchedule;
      let homeTeamId = matchSchedule.homeTeam.teamId;
      if (teamId == homeTeamId) return true;
      else return false;
    },
    // ============== 수정 시 ====================== //
    //FS11
    loadRemainInfo() {
      let matchScheduleId = this.vote.matchSchedule.matchScheduleId;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      this.$axios
        .get("/match-schedule/7/" + matchScheduleId + "/" + teamId)
        .then(response => {
          let collection = response.data;
          this.matchResult = collection.matchResult;
          this.matchResult.isSet = String(this.matchResult.isSet);
          this.teamScore = collection.teamScore;
          let entries = collection.entries;
          this.classifyEntry(entries);
        });
    },
    updateMatchResult() {
      let entries = this.teamMembers.concat(this.friendEmploys);
      let matchResultCollection = {
        entries: entries,
        teamScore: this.teamScore,
        matchResult: this.matchResult
      };
      console.log(matchResultCollection);
      this.$axios
        .put("/match-schedule/2", matchResultCollection)
        .then(() => {
          alert("수정이 완료됐습니다.");
        })
        .catch(() => {
          alert("수정에 실패했습니다.");
        })
        .finally(() => {
          location.reload();
        });
    }
  }
};
