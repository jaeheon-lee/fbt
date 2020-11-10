import Vue from "vue";
import VueDaumPostcode from "vue-daum-postcode";

Vue.use(VueDaumPostcode);
export default {
  name: "vote-match-manager",
  component: {
  },
  data() {
    return {
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
      selectedPark: ""
    };
  },
  watch: {
    completeresult: function(newValue) {
      if (newValue != "") {
        this.dialogStadium = false;
      }
    }
  }
};
