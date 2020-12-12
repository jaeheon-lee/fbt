// import axios from "axios";
import MapVue from "@/components/Map/Map.vue";
import Axios from "axios";
import NaverLink from "@/components/Common/NaverLink.vue";

export default {
  name: "employ-insert",
  props: {
    matchScheduleId: Number
  },
  components: {
    "map-vue": MapVue,
    "naver-link": NaverLink
  },
  data() {
    return {
      // Search VO 받기 위한 정보
      employ: {
        employId: null,
        teamGiver: {
          teamId: null
        },
        matchSchedule: {
          matchScheduleId: null,
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
          }
        },
        teamMember: {
          teamMemberId: null
        },
        cost: null,
        reqNumber: null,
        content: null,
        regDate: null,
        dueDate: null
      },
      // 경기 불어올 때 받는 변수
      votes: [],
      //Input 별 부가 Dialog 중 해당 Dialog가 열리도록 토글 변수
      dialogStadium: false, // 경기장 부분
      dialogMatchSchedule: false, // 저장된 경기일정 부분
      //Input disable 관련 변수
      updateDisabled: false, // 정보 받아오면 true, 직접 쓰면 false => 정보 수정 불가능하게
      //  경기장 관련 변수
      targetStadium: null, // 경기장 주소 및 이름 출력 변수
      // 경기 타입 관련 변수
      matchTypes: ["11:11", "10:10", "9:9", "8:8", "7:7", "6:6", "5:5"], //select option properties
      matchTypeSelected: false, //Class 적용 여부 결정
      // 경기장 타입 관련 변수
      stadiumTypeSelected: false, //Class 적용 여부 결정
      // eslint-disable-next-line prettier/prettier
      stadiumTypes: ['흙', '잔디', '인조잔디'], // select option properties
      // 샤워여부 관련 변수
      stadiumShowerSelected: false, //Class 적용 여부 결정
      // 경기장 주차 관련 변수
      parkingSelected: false, //Class 적용 여부 결정
      // 직접 등록이면 일정등록까지 한다
      isDirect: true,

      // 네이버 공유 관련 변수
      dialogNaver: false
    };
  },
  mounted() {
    if (this.matchScheduleId) this.showMatchScheduleInfo();
    this.insertInfoFromSessionProps();
  },
  computed: {
    // 경기 일시 출력
    targetDate: {
      get: function() {
        if (this.employ.matchSchedule.startTime) {
          return this.$moment(this.employ.matchSchedule.startTime).format(
            "yyyy-MM-DDThh:mm"
          );
        }
      },
      set: function(newVar) {
        this.dateFomatter(0, newVar, null);
      }
    },
    // 경기 시간
    targetDuration: {
      get: function() {
        if (this.employ.matchSchedule.duration) {
          return this.employ.matchSchedule.duration;
        }
      },
      set: function(newVar) {
        this.employ.matchSchedule.duration = newVar;
      }
    },
    // 경기 타입 출력
    targetType: {
      get: function() {
        if (this.employ.matchSchedule.matchType)
          return this.employ.matchSchedule.matchType;
        else return null;
      },
      set: function(newVar) {
        this.employ.matchSchedule.matchType = newVar;
      }
    },
    // 경기 비용 출력
    targetCost: {
      get: function() {
        if (this.employ.matchSchedule.cost)
          return this.employ.matchSchedule.cost;
      },
      set: function(newVar) {
        this.employ.matchSchedule.cost = newVar;
      }
    },
    // 경기장 타입 출력
    targetStadiumType: {
      get: function() {
        if (this.employ.matchSchedule.stadiumType)
          return this.employ.matchSchedule.stadiumType;
        else return null;
      },
      set: function(newVar) {
        this.employ.matchSchedule.stadiumType = newVar;
      }
    },
    // 경기장 샤워 출력
    targetStadiumShower: {
      get: function() {
        if (this.employ.matchSchedule.stadiumShower)
          return this.employ.matchSchedule.stadiumShower;
        else return 0;
      },
      set: function(newVar) {
        this.employ.matchSchedule.stadiumShower = newVar;
      }
    },
    // 경기장 주차 출력
    targetStadiumParking: {
      get: function() {
        if (this.employ.matchSchedule.stadiumParking)
          return this.employ.matchSchedule.stadiumParking;
        else return null;
      },
      set: function(newVar) {
        this.employ.matchSchedule.stadiumParking = newVar;
      }
    }
  },
  methods: {
    /* 공통 -----------------------------------------------------------------*/
    // 세션 | props 정보 vo에 넣기
    insertInfoFromSessionProps() {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      this.employ.teamGiver.teamId = userInfo.teamId;
      this.employ.teamMember.teamMemberId = userInfo.teamMemberId;
      this.employ.matchSchedule.matchScheduleId = this.matchScheduleId;
      this.employ.matchSchedule.writer = userInfo.nickName;
    },
    // 받은 date값 변환
    dateFomatter(i, date, event) {
      if (i == 0) {
        let dateTime = this.$moment(date).format("YYYY-MM-DD HH:mm:ss");
        this.employ.matchSchedule.startTime = dateTime;
      }
      // 경기일시이면
      else {
        let dateTime = this.$moment(event.target.value).format(
          "YYYY-MM-DD HH:mm:ss"
        );
        this.employ.dueDate = dateTime; // 마감일시면
      }
    },
    // (FE01)
    addEmploy() {
      // eslint-disable-next-line prettier/prettier
      Axios
        .post("/employ", this.employ)
        .then(() => {
          alert("용병 찾기 등록이 완료됐습니다.");
          this.dialogNaver = true;
        })
        .catch(() => {
          alert("용병 찾기 등록에 실패했습니다.");
        })
        // eslint-disable-next-line prettier/prettier
        .finally(() => {

        })
    },
    // 불러왔을 때, Class 변수 true
    selectedOn() {
      this.stadiumTypeSelected = true;
      this.matchTypeSelected = true;
      this.stadiumShowerSelected = true;
      this.parkingSelected = true;
    },
    closeNaver() {
      this.dialogNaver = false;
      this.$emit("close");
    },
    /* 투표 완료 후 버튼으로 넘어온 경우 --------------------------------------*/
    showMatchScheduleInfo() {
      // eslint-disable-next-line prettier/prettier
      Axios
        .get("/match-schedule/2/" + this.matchScheduleId)
        .then(response => {
          this.employ.matchSchedule = response.data;
          this.updateDisabled = true;
          this.targetStadium =
            this.employ.matchSchedule.stadiumAddress +
            " " +
            this.employ.matchSchedule.stadiumName;
          this.selectedOn();
          this.isDirect = false; // 직접이 아니므로 일정 등록을 하지 않는다.
        })
        .catch(() => {
          alert("정보를 불러오는 데 실패했습니다.");
        });
    },
    /* 지정된 경기 불러오기 --------------------------------------*/
    // FV15
    loadMatchSchedule() {
      this.dialogMatchSchedule = true;
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      // eslint-disable-next-line prettier/prettier
      Axios
        .get("/vote-match/3/" + teamId + "/" + 2)
        .then(response => {
          this.votes = response.data;
          this.selectedOn();
          this.isDirect = false;
          this.updateDisabled = true;
        })
        .catch(() => {
          alert("정보를 불러오는 데 실패했습니다.");
        });
    },
    selectMatch(matchSchedule) {
      this.employ.matchSchedule = matchSchedule;
      this.targetStadium =
        this.employ.matchSchedule.stadiumAddress +
        " " +
        this.employ.matchSchedule.stadiumName;
      this.dialogMatchSchedule = false;
    },
    /* 직접 쓰기 --------------------------------------*/
    // Map에서 받은  targetStadium 적용하기
    setTargetStadium(place) {
      this.employ.matchSchedule.stadiumName = place.place_name;
      if (place.road_address_name) {
        this.employ.matchSchedule.stadiumAddress = place.road_address_name;
      } else {
        this.employ.matchSchedule.stadiumAddress = place.address_name;
      }
      this.employ.matchSchedule.stadiumX = place.x;
      this.employ.matchSchedule.stadiumY = place.y;
      this.targetStadium =
        this.employ.matchSchedule.stadiumAddress + " " + place.place_name;
      this.dialogStadium = false;
    }
  }
};
