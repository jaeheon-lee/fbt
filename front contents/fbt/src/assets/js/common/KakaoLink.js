export default {
  name: "kakao-link",
  props: {
    dialogKakao: Boolean,
    voteMatch: Object, // 투표 생성 시 받는 vo
    vote: Object, // 투표 마감 시 받는 vo
    updateVoteMatch: Object // 투표 수정 시 받는 vo
  },
  data() {
    return {};
  },
  methods: {
    // U02-2
    writeLink() {
      let title = "";
      let desc = "";
      let webUrl = "";
      let mobileWebUrl = "";
      let btnTitle = "";
      if (this.voteMatch) {
        // 생성이면 아래의 글로 공유한다.
        title = this.voteMatch.matchSchedule.matchType + " 경기 투표 생성";
        desc = this.voteMatch.dueDate.slice(0, -2) + "까지 투표부탁드립니다.";
        webUrl = "http://localhost:8080/voteMatch";
        mobileWebUrl = "http://localhost:8080/voteMatch";
        btnTitle = "투표하러 가기";
      } else if (this.vote) {
        // 마감이면 아래의 글로 공유한다
        title =
          this.vote.matchSchedule.startTime.slice(0, -3) +
          "일자 경기 투표 마감";
        desc = "일정확인해주세요";
        webUrl = "http://localhost:8080/schedule";
        mobileWebUrl = "http://localhost:8080/schedule";
        btnTitle = "일정 확인하러 가기";
      } else if (this.updateVoteMatch) {
        // 수정이면 아래의 글로 공유한다
        title = this.updateVoteMatch.matchSchedule.matchType + " 경기 투표 수정";
        desc = this.updateVoteMatch.dueDate.slice(0, -2) + "까지 투표부탁드립니다.";
        webUrl = "http://localhost:8080/voteMatch";
        mobileWebUrl = "http://localhost:8080/voteMatch";
        btnTitle = "투표하러 가기";
      }

      this.shareLink(title, desc, webUrl, mobileWebUrl, btnTitle);
    },

    // U02-3
    shareLink(title, desc, webUrl, mobileWebUrl, btnTitle) {
      window.Kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: title,
          description: desc,
          imageUrl: "@/assets/emblem/답십리FC201018.png",
          link: {
            webUrl: webUrl,
            mobileWebUrl: mobileWebUrl
          }
        },
        buttons: [
          {
            title: btnTitle,
            link: {
              webUrl: webUrl,
              mobileWebUrl: mobileWebUrl
            }
          }
        ]
      });
      this.$emit("close");
    },

    // 그냥 창닫기
    close() {
      this.$emit("close");
    }
  }
};
