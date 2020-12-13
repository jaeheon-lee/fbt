// import axios from "axios";
import MapVue from "@/components/Map/Map.vue";

export default {
  name: "search-update",
  props: {
    pushedSearch: Object
  },
  components: {
    "map-vue": MapVue
  },
  data() {
    return {
      // Search VO 받기 위한 정보
      search: {
        searchId: null,
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
          },
          voteMatches: []
        },
        teamMember: {
          teamMemberId: null
        },
        searchRegDate: null,
        waitingTime: null,
        minNumber: null,
        dueDate: null,
        content: null
      },

      // 네이버 공유 관련 변수
      dialogNaver: false
    };
  },
  created() {
    this.search = this.pushedSearch;
  },
  computed: {
    targetDueDate: {
      get: function() {
        if (this.search.dueDate) {
          return this.$moment(this.search.dueDate).format("yyyy-MM-DDThh:mm");
        }
      },
      set: function(newVar) {
        this.search.dueDate = this.$moment(newVar).format(
          "YYYY-MM-DD HH:mm:ss"
        );
      }
    }
  },
  methods: {
    /* 공통 -----------------------------------------------------------------*/
    // FM18
    updateSearch() {
      this.$axios
        .put("/search/3", this.search)
        .then(() => {
          alert("수정이 완료됐습니다.");
        })
        .catch(() => {
          alert("수정에 실패했습니다.");
        })
        // eslint-disable-next-line prettier/prettier
        .finally(() => {
          this.$router.push({
            name: "search",
            params: {
              menu: 3
            }
          });
        });
    }
  }
};
