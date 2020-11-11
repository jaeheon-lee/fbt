import axios from "axios";
import MapVue from "@/components/Map/Map.vue";

export default {
  name: "vote-match-manager",
  components: {
    "map-vue": MapVue
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
        stadiumType: null,
        stadiumAddress: null,
        stadiumX: null,
        stadiumY: null,
        stadiumParking: null,
        stadiumShower: null,
        matchType: null,
        homeTeam: {
          teamId: null,
          teamName: null
        },
        awayTeam: {
          teamId: null,
          teamName: null
        }
      },
      // VoteMatch VO 받기 위한 정보
      VoteMatch: {
        voteMatchId: null,
        contents: null,
        dueDate: null,
        voteStatus: 0,
        writer: null
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

      // 에러 | 로딩 변수
      loading: false,
      errored: false,

      dialog: false,
      team: "FC답십리",
      friendInvite: false,
      targetNumberAndFinish: false,
      minBasisNumbers: [
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20,
        21,
        22,
        23,
        24
      ],
      selectedMinBasisNumber: "",
      selectedYoungByung: false,
      selectedAwayTeam: false,

      awayTeam: "미정",
      completeresult: {
        address: ""
      },
      contents: "",

      matchCosts: [
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20
      ],
      selectedMatchType: "",
      selectedCost: "",
      selectedPark: "",

      //로그인 변수 -- 임시 변수
      userInfo: {
        email: "bioman2@gmail.com",
        teamId: 1,
        teamMemberId: "1-bioman2@gmail.com",
        teamName: "왕십리FC",
        nickName: "nick1"
      }
    };
  },
  created() {
    this.login();
    this.insertInfoFromSession();
  },
  mounted() {},
  methods: {
    // Session 내용 중 Insert에 필요한 정보 받기
    insertInfoFromSession() {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      this.matchSchedule.writer = userInfo.nickName;
      this.matchSchedule.homeTeam.teamId = userInfo.teamId;
      this.matchSchedule.homeTeam.teamName = userInfo.teamName;
    },
    // 상대팀 유형 메소드
    // 1: Radio를 통해 바꾸기 (자체 or 미정)
    chooseAwayTeamTypeByRadio() {
      if (this.awayTeamType == 0) this.matchSchedule.awayTeam.teamName = "미정";
      if (this.awayTeamType == 1)
        this.matchSchedule.awayTeam.teamName = this.matchSchedule.homeTeam.teamName;
    },
    // 2: 상대팀명 검색을 통해 상대팀 지정하기(상대팀)
    selectAwayTeam(teamId, teamName) {
      this.matchSchedule.awayTeam.teamId = teamId;
      this.matchSchedule.awayTeam.teamName = teamName;
      this.dialogAwayTeam = false;
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

    // 로그인 -- 임시 메소드
    login() {
      sessionStorage.setItem("userInfo", JSON.stringify(this.userInfo));
    }
  },
  computed: {}
};
