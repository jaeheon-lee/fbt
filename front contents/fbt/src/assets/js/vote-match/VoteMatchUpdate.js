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
        cost: null,
        writer: null,
        content: null,
        stadiumName: null,
        stadiumType: null, //
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
    if (this.vote) {
      this.voteMatch = this.vote;
      this.matchSchedule = this.vote.matchSchedule;
      this.voteMatchSetting = this.vote.voteMatchSetting;
      this.targetStadium =
        this.matchSchedule.stadiumAddress +
        " " +
        this.matchSchedule.stadiumName;
    }
    console.log(this.voteMatch);
  },
  mounted() {},
  computed: {
    targetDate: {
      get: function() {
        if (this.matchSchedule.startTime) {
          return this.$moment(this.matchSchedule.startTime).format(
            "yyyy-MM-DDThh:mm"
          );
        }
      },
      set: function(newVar) {
        this.dateFomatter(0, newVar);
      }
    },
    targetDueDate: {
      get: function() {
        if (this.vote.dueDate) {
          return this.$moment(this.vote.dueDate).format("yyyy-MM-DDThh:mm");
        }
      },
      set: function(newVar) {
        this.dateFomatter(1, newVar);
      }
    },
    cancelNumber: {
      get: function() {
        if (this.voteMatchSetting.cancelNumber == -1) return 0;
        else return this.voteMatchSetting.cancelNumber;
      },
      set: function(newVal) {
        this.voteMatchSetting.cancelNumber = newVal;
      }
    }
  },
  methods: {
    // 경기 투표 수정(FV14)
    updateVoteMatch() {
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
    // 받은 date값 변환
    dateFomatter(i, date) {
      let dateTime = this.$moment(date).format("YYYY-MM-DD HH:mm:ss");
      if (i == 0) this.matchSchedule.startTime = dateTime;
      // 경기일시이면
      else this.voteMatch.dueDate = dateTime; // 마감일시면
    },

    // T006: 팀 검색
    searchTeams() {
      this.loading = true;
      axios
        .get(
          "/team/" +
            this.inputTeamName +
            "?homeTeamId=" +
            this.matchSchedule.homeTeam.teamId
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
