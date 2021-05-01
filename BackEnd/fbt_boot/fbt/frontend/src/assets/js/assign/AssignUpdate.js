export default {
  name: "assign-update",
  props: {
    pushedAssign: Object
  },
  data() {
    return {
      // Assign VO 받기 위한 정보
      assignment: {
        assignmentId: null,
        waitingTime: null,
        minNumber: null,
        regDate: null,
        dueDate: null,
        content: null
      }
    };
  },
  created() {
    this.assignment = this.pushedAssign;
  },
  computed: {
    targetDueDate: {
      get: function() {
        if (this.assignment.dueDate) {
          return this.$moment(this.assignment.dueDate).format(
            "yyyy-MM-DDThh:mm"
          );
        }
      },
      set: function(newVar) {
        this.assignment.dueDate = this.$moment(newVar).format(
          "YYYY-MM-DD HH:mm:ss"
        );
      }
    }
  },
  methods: {
    /* 공통 -----------------------------------------------------------------*/
    // FA15
    updateAssign() {
      this.$axios
        .put("/assignment/2", this.assignment)
        .then(() => {
          alert("수정이 완료됐습니다.");
        })
        .catch(() => {
          alert("수정에 실패했습니다.");
        })
        // eslint-disable-next-line prettier/prettier
        .finally(() => {
          this.$router.push({
            name: "assign",
            params: {
              menu: 3
            }
          });
        });
    }
  }
};
