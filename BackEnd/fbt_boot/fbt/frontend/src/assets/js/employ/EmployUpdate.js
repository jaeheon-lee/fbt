export default {
  name: "employ-update",
  props: {
    pushedEmploy: Object
  },
  data() {
    return {
      // Search VO 받기 위한 정보
      employ: {
        employId: null,
        cost: null,
        reqNumber: null,
        content: null,
        regDate: null,
        dueDate: null
      }
    };
  },
  created() {
    this.employ = this.pushedEmploy;
  },
  computed: {
    targetDueDate: {
      get: function() {
        if (this.employ.dueDate) {
          return this.$moment(this.employ.dueDate).format("yyyy-MM-DDThh:mm");
        }
      },
      set: function(newVar) {
        this.employ.dueDate = this.$moment(newVar).format(
          "YYYY-MM-DD HH:mm:ss"
        );
      }
    }
  },
  methods: {
    //FE14
    updateEmploy() {
      this.$axios
        .put("/employ/2", this.employ)
        .then(() => {
          alert("수정이 완료됐습니다.");
        })
        .catch(() => {
          alert("수정에 실패했습니다.");
        })
        // eslint-disable-next-line prettier/prettier
        .finally(() => {
          this.$router.push({
            name: "employManager",
            params: {
              menu: 2
            }
          });
        });
    }
  }
};
