import axios from "axios";
import MapVue from "@/components/Map/Map.vue";
import KakaoLink from "@/components/Common/KakaoLink.vue";

export default {
  name: "vote-match-update",
  props: {
    vote: Object
  },
  components: {
    "map-vue": MapVue,
    "kakao-link": KakaoLink
  },
  data() {
    return {
      // MatchSchedule VO 받기 위한 정보
      matchSchedule: {
        startTime: null,
        duration: null,
        cost: -1,
        writer: null,
        homeContent: null,
        awayContent: null,
        stadiumName: null,
        stadiumType: -1, //
        stadiumAddress: null,
        stadiumX: null,
        stadiumY: null,
        stadiumParking: null,
        stadiumShower: null, //
        matchType: null,
        homeTeam: {
          teamId: null,
          teamName: null
        },
        awayTeam: {
          teamId: null,
          teamName: null
        },
        searchId: null
      },
      // VoteMatch VO 받기 위한 정보
      voteMatch: {
        voteMatchId: null,
        memo: null, //
        dueDate: null, //
        voteStatus: 0,
        writer: null,
        team: {
          teamId: null
        },
        matchSchedule: {},
        voteMatchSetting: {}
      },
      // VoteMatchSetting VO 받기 위한 정보
      voteMatchSetting: {
        type: 0,
        cancelNumber: -1,
        isFirst: false,
        waiting: false,
        friendEmp: false,
        selfMinNumber: -1,
        selfMaxNumber: -1,
        empMinNumber: -1,
        awayMinNumber: -1,
        assignCost: -1,
        empCost: -1,
        searchCost: -1
      },

      //Input 별 부가 Dialog 중 해당 Dialog가 열리도록 토글 변수
      dialogAwayTeam: false, // 상대팀 부분
      dialogStadium: false, // 경기장 부분

      // 상대팀 유형 관련 변수
      awayTeamType: 0, //{0: 미정, 1: 자체, 2: 상대팀}
      inputTeamName: null, // 검색할 팀 명 받기
      awayTeams: [], //검색한 팀을 받을 JSON

      // 경기 시간 관련 변수
      matchDateTime: null, // 경기일시 받는 변수
      duration: null, // 경기 시간 받는 변수

      // 경기 장소 관련 변수
      targetStadium: null, // 선택된 경기장

      // 경기 타입 관련 변수
      matchTypes: ["11:11", "10:10", "9:9", "8:8", "7:7", "6:6", "5:5"], //select option properties
      matchTypeSelected: false, //Class 적용 여부 결정

      // 경기장 주차 관련 변수
      parkingSelected: false, //Class 적용 여부 결정

      // 경기장 타입 관련 변수
      // eslint-disable-next-line prettier/prettier
      stadiumTypes: ['흙', '잔디', '인조잔디'], // select option properties
      stadiumTypeSelected: false, //Class 적용 여부 결정

      // 샤워여부 관련 변수
      stadiumShowerSelected: false, //Class 적용 여부 결정

      // 투표 기타 설정 관련 변수

      // 카카오공유하기 관련 변수
      dialogKakao: false,

      // 에러 | 로딩 변수
      submitting: false,
      errored: false
    };
  },
  created() {
    console.log(this.vote.matchSchedule);
    if (this.vote) {
      this.voteMatch = this.vote;
      this.matchSchedule = this.vote.matchSchedule;
      this.voteMatchSetting = this.vote.voteMatchSetting;
      this.targetStadium =
        this.matchSchedule.stadiumAddress +
        " " +
        this.matchSchedule.stadiumName;
    }
  },
  mounted() {},
  computed: {
    setStartTime: {
      get: function() {
        if (this.matchSchedule.startTime) {
          let startTime = this.$moment(this.matchSchedule.startTime).format(
            "yyyy-MM-DDThh:mm:ss"
          );
          return startTime;
        }
      },
      set: function(newVal) {
        let result = this.checkStartTime(newVal);
        if (result)
          this.matchSchedule.startTime = this.$moment(newVal).format(
            "YYYY-MM-DD HH:mm:ss"
          )
        else this.matchSchedule.startTime = null;
      }
    },
    setDuration: {
      get: function() {
        return this.matchSchedule.duration;
      },
      set: function(newVal) {
        let result = this.checkDuration(newVal);
        if (result) this.matchSchedule.duration = newVal;
      }
    },
    setDueDate: {
      get: function() {
        if (this.voteMatch.dueDate) {
          let dueDate = this.$moment(this.voteMatch.dueDate).format(
            "yyyy-MM-DDThh:mm:ss"
          );
          return dueDate;
        }
      },
      set: function(newVal) {
        this.voteMatch.dueDate = this.$moment(newVal).format(
          "YYYY-MM-DD HH:mm:ss"
        );
      }
    },
    //V05-2
    setContent: {
      get: function() {
        let isHomeTeam = this.isHomeTeam();
        if (isHomeTeam) {
          return this.matchSchedule.homeContent;
        } else {
          return this.matchSchedule.awayContent;
        }
      },
      set: function(newVal) {
        let isHomeTeam = this.isHomeTeam();
        if (isHomeTeam) {
          this.matchSchedule.homeContent = newVal;
        } else {
          this.matchSchedule.awayContent = newVal;
        }
      }
    }
  },
  methods: {
    // V05-3
    updateVoteMatch() {
      let result = this.checkInsertValidation();
      if (!result) return false;
      let dueDateValid = this.checkDueDate(this.voteMatch.dueDate);
      if (!dueDateValid) return false;

      this.vote.matchSchedule = this.matchSchedule;
      this.vote.voteMatchSetting = this.voteMatchSetting;
      this.submitting = true;
      let teamName = JSON.parse(sessionStorage.getItem("userInfo")).teamName;
      axios
        .put("/vote-match/2?teamName=" + teamName, this.voteMatch)
        .then(() => {
          alert("수정이 완료됐습니다.");
          // 카카오 공유하기 기능 사용 여부를 묻는다.
          this.dialogKakao = true;
        })
        .catch(() => {
          alert("수정에 실패했습니다.");
          this.errored = true;
        })
        .finally(() => {
          this.submitting = false;
        });
    },

    //V05-2
    isHomeTeam() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      if (this.matchSchedule.homeTeam.teamId == teamId) {
        return true;
      } else {
        return false;
      }
    },

    /**
     * V01-2
     * 경기일시의 유효성을 확인한다. 유효성은 입력날짜가 지금 시간보다 후여야 한다.
     * 경기일시를 현재 날짜 이후로만 설정하도록 유도한다
     * @param dateTime : 입력받은 날짜
     * @author 강제영
     * @version 1.0
     */
    checkStartTime(dateTime) {
      let input = new Date(dateTime);
      let today = new Date();
      if (input < today) {
        alert("지금 시간보다 늦은 시간으로 설정해주세요.");
        document.getElementById("startTime").value = null;
        return false;
      }
      return true;
    },

    /**
     * V01-3
     * 경기 시간 유효성 검사
     * 경기 시간을 숫자, 1이상으로 유도한다.
     * @param {*} duration : 입력받은 경기 시간
     */
    checkDuration(duration) {
      if (duration.length == 0) return false;
      if (duration < 1) {
        alert("최소 경기시간은 1시간입니다.");
        document.getElementById("duration").value = this.matchSchedule.duration;
        return false;
      }
      if (isNaN(duration)) {
        alert("숫자만 입력해야 합니다.");
        document.getElementById("duration").value = this.matchSchedule.duration;
        return false;
      }
      return true;
    },

    /**
     * V01-3
     * 투표 마감일 유효성 검사
     * 투표 마감일을 지금 시간보다 늦게, 경기 시간보다 빠르게 유도한다
     * @param dueDate: 입력 받은 투표 기한
     */
    checkDueDate(dueDate) {
      let input = new Date(dueDate);
      let today = new Date();
      let startTime = new Date(this.matchSchedule.startTime);
      if (this.matchSchedule.startTime == null) {
        alert("경기시간부터 입력해주세요.");
        document.getElementById("dueDate").value = null;
        return false;
      }
      if (input < today) {
        alert("투표마감일을 지금 시간보다 늦게 설정해주세요.");
        document.getElementById("dueDate").value = null;
        return false;
      }
      if (input > startTime) {
        alert("투표마감일을 경기 시간보다 빠르게 설정해주세요.");
        document.getElementById("dueDate").value = null;
        return false;
      }
      return true;
    },

    /**
     * V01-5
     * 필수입력란 입력했는지  확인한다.
     */
    checkInsertValidation() {
      if (!this.matchSchedule.startTime) {
        alert("경기 일시를 입력해주세요.");
        return false;
      }
      if (!this.matchSchedule.duration) {
        alert("경기 시간을 입력해주세요.");
        return false;
      }
      if (!this.matchSchedule.stadiumName) {
        alert("경기장 정보를 입력해주세요.");
        return false;
      }
      if (!this.voteMatch.dueDate) {
        alert("투표마감일을 입력해주세요.");
        return false;
      }
      if (!this.matchSchedule.matchType) {
        alert("경기타입을 입력해주세요.");
        return false;
      }
      return true;
    },

    // Map에서 받은  targetStadium 적용하기
    setTargetStadium(place) {
      this.matchSchedule.stadiumName = place.place_name;
      if (place.road_address_name) {
        this.matchSchedule.stadiumAddress = place.road_address_name;
      } else {
        this.matchSchedule.stadiumAddress = place.address_name;
      }
      this.matchSchedule.stadiumX = place.x;
      this.matchSchedule.stadiumY = place.y;
      this.targetStadium =
        this.matchSchedule.stadiumAddress + " " + place.place_name;
      this.dialogStadium = false;
    },
    closeKaokao() {
      this.dialogKakao = false;
      this.$router.push("/voteMatchManager");
    }
  }
};
