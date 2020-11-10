import Vue from "vue";
import VueDaumPostcode from "vue-daum-postcode";

Vue.use(VueDaumPostcode);
export default {
  name: "vote-match-manager",
  component: {
  },
  data() {
    return {
      openDialog: null,



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
      
      
      dialogStadium: false,
      awayTeam: "미정",
      completeresult: {
        address: ""
      },
      matchDateTime: "",
      contents: "",
      matchTypes: ["11:11", "10:10", "9:9", "8:8", "7:7", "6:6", "5:5"],
      matchCosts: [
        0,
        1,
        2,
        3,
        4,
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
        teamName: "왕십리FC"
      }
    };
  },
  created() {
    this.login();
  },
  methods: {
    // 로그인 -- 임시 메소드
    login() {
      sessionStorage.setItem("userInfo", JSON.stringify(this.userInfo));
    }
  },
  watch: {
    completeresult: function(newValue) {
      if (newValue != "") {
        this.dialogStadium = false;
      }
    }
  }
};
