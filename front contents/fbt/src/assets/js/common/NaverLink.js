export default {
  name: "naver-link",
  props: {
    type: String,
    matchSchedule: Object,
    employCost: String
  },
  data() {
    return {
      teamInfo: {}
    };
  },
  methods: {
    // U03-2
    getInfoForWrite() {
      let teamId = JSON.parse(sessionStorage.getItem("userInfo")).teamId;
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      this.$axios
        .get("/team/2/" + teamId + "/" + email)
        .then(response => {
          this.teamInfo = response.data;
          this.write();
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        })
        .finally(() => {
          this.close();
        })
    },
    // U03-1
    close() {
      this.$emit("close");
    },
    //U03-5
    shareLink() {
      //3. 공유하기 위한 타이틀/url 작성
      let title = "";
      if (this.matchSchedule.matchType.split(":")[0] < 11) {
        title = this.matchSchedule.matchType + " 풋살";
      } else {
        title = this.matchSchedule.matchType + " 축구";
      }

      let url = "";
      switch (this.type) {
        case "search":
          url = "search";
          title += " 경기 매칭";
          break;
        case "assign":
          url = "assign";
          title += " 경기 양도";
          break;
        case "employ":
          url = "employ?employ_id=4";
          title += " 경기 용병 모집";
          break;
      }

      let encodedUrl = encodeURI(
        encodeURIComponent("http://localhost:8080/" + url)
      );
      let encodedTitle = encodeURI(title);
      var shareURL =
        "https://share.naver.com/web/shareView.nhn?url=" +
        encodedUrl +
        "&title=" +
        encodedTitle;
      window.open(shareURL);
    },
    // U03-3
    write() {
      // 1. 자동글 완성을 위한 변수 정리
      // eslint-disable-next-line prettier/prettier
      let matchDateTime = this.matchSchedule.startTime.slice(0, 13) + " ~ " + this.matchSchedule.endTime.slice(11, 13);
      let stadiumName = this.matchSchedule.stadiumName;
      let teamManner = this.covertIntToString(this.teamInfo.teamManner);
      let teamAbility = this.covertIntToString(this.teamInfo.teamAbility);
      let phoneNum = this.teamInfo.teamMembers[0].user.phoneNum;
      let originCost = this.matchSchedule.cost;
      let cost = this.classifyCost();
      let introduction = this.showIntroduction();

      // 2. 자동 글 완성
      let text = "1. 일정\n\n";
      text += "   " + matchDateTime + "\n\n";
      text += "2. 구장\n\n";
      text += "   " + stadiumName + "\n\n";
      text += "3. 실력&매너\n\n";
      text += "   " + "실력: " + teamAbility + ", 매너: " + teamManner + "\n\n";
      text += "4. 연락처\n\n";
      text += "   " + phoneNum + "\n\n";
      text += "5. 원 구장비\n\n";
      text += "   " + originCost + " 만원\n\n";
      text += "6. 비용\n\n";
      text += "   " + cost + " 만원\n\n";
      text += "7. 팀소개\n\n";
      text += "   " + introduction;

      // 2. 복사
      this.copy(text);
    },
    // U03-4
    copy(text) {
      var createInput = document.createElement("textarea");
      // 가상으로 가져올 태그에 만들어준 input 태그를 붙여줍니다.
      document.getElementById("textDiv").appendChild(createInput);
      // 만든 input 태그의 value 값에 복사할 텍스트 값을 넣어줍니다.
      createInput.value = text;
      // 복사 기능을 수행한 후
      createInput.select();
      document.execCommand("copy");
      // 가상으로 붙여주었던 input 태그를 제거해줍니다.
      document.getElementById("textDiv").removeChild(createInput);
      // api 실행
      this.shareLink();
    },
    covertIntToString(value) {
      if (value < 2) {
        return "하하";
      } else if (value < 4) {
        return "중하";
      } else if (value < 6) {
        return "중중";
      } else if (value < 8) {
        return "중상";
      } else {
        return "상상";
      }
    },
    classifyCost() {
      switch (this.type) {
        case "search":
          var cost = (this.matchSchedule.cost / 2).toFixed(2);
          return Math.ceil(cost * 100) / 100;
        case "assign":
          return this.matchSchedule.cost;
        case "employ":
          return this.employCost;
      }
    },
    showIntroduction() {
      let uniformType = "";
      if (this.teamInfo.uniformType == 0) uniformType = "유니폼";
      else uniformType = "조끼";
      let introduction = "";
      introduction += this.teamInfo.foundingDate + "에 설립된 ";
      introduction += this.teamInfo.teamName + "는 ";
      introduction += this.teamInfo.area + "에서 주로 활동하고 ";
      introduction += this.teamInfo.uniformColor + "색의 ";
      introduction += uniformType + "를 착용합니다. ";

      return introduction;
    }
  }
};
