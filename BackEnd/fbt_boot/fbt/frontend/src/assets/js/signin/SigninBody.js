export default {
  name: "sign-in-body",
  props: {
    id: String,
    type: Number,
    header: String
  },
  data: () => ({
    // 수정, 가입 조절 변수
    doChangePass: true,

    user: {
      email: null,
      pass: "",
      pass2: "",
      name: null,
      phoneNum: null,
      mainFoot: null,
      position: null,
      height: null,
      weight: null,
      wasPro: null,
      gender: null,
      bornDate: null,
      area: null,
      apiKey: null,
      apiType: null
    },
    getYear: null,
    getMonth: null,
    getDay: null,
    siDo: null,
    siGun: null,
    rules: {
      required: value => !!value || "필수항목",
      phone: value => {
        const pattern = /^\d{3}-\d{3,4}-\d{4}$/;
        return pattern.test(value) || "xxx-xxxx-xxxx 형식으로 입력해주세요";
      }
    },
    // 이메일 중복 체크
    emailDuple: false,
    // 페이지 넘기기
    page: 1,
    // 선택지
    mainFoot: ["오른발", "왼발"],
    position: ["FW", "MF", "DF", "GK"],
    wasPro: [
      { label: "프로", value: 1 },
      { label: "아마", value: 0 }
    ],
    gender: [
      { label: "남자", value: 0 },
      { label: "여자", value: 1 }
    ],
    year: [],
    month: [],
    day: [],
    // 경기장 위치 시군구 정보
    // eslint-disable-next-line prettier/prettier
    area0: ["서울","인천","대전","광주","대구","울산","부산","경기","강원","충북","충남","전북","전남","경북","경남","제주특별자치도"],
    // eslint-disable-next-line prettier/prettier
    areaList: [
      // eslint-disable-next-line prettier/prettier
      ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"],
      // eslint-disable-next-line prettier/prettier
      ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"],
      // eslint-disable-next-line prettier/prettier
      ["대덕구","동구","서구","유성구","중구"],
      // eslint-disable-next-line prettier/prettier
      ["광산구","남구","동구","북구","서구"],
      // eslint-disable-next-line prettier/prettier
      ["남구","달서구","동구","북구","서구","수성구","중구","달성군"],
      // eslint-disable-next-line prettier/prettier
      ["남구","동구","북구","중구","울주군"],
      // eslint-disable-next-line prettier/prettier
      ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"],
      // eslint-disable-next-line prettier/prettier
      ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"],
      // eslint-disable-next-line prettier/prettier
      ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"],
      // eslint-disable-next-line prettier/prettier
      ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"],
      // eslint-disable-next-line prettier/prettier
      ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"],
      // eslint-disable-next-line prettier/prettier
      ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"],
      // eslint-disable-next-line prettier/prettier
      ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"],
      // eslint-disable-next-line prettier/prettier
      ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"],
      // eslint-disable-next-line prettier/prettier
      ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"],
      // eslint-disable-next-line prettier/prettier
      ["서귀포시","제주시","남제주군","북제주군"]
    ]
  }),
  mounted() {
    // input setting
    let today = new Date();
    let toyear = parseInt(today.getFullYear());
    let startYear = toyear - 5;
    let endYear = toyear - 70;
    for (let i = startYear; i >= endYear; i--) {
      this.year.push(i);
    }
    for (let i = 1; i < 13; i++) {
      this.month.push(i);
    }
    for (let i = 1; i < 32; i++) {
      this.day.push(i);
    }
    // 수정인지, 회원가입인지
    if (this.header == "update") {
      this.showUserInfo();
      this.doChangePass = false;
    }
  },
  computed: {
    isSame: function() {
      if (this.user.pass && this.user.pass2) {
        if (this.user.pass != this.user.pass2) {
          return "비밀번호를 다시 확인해주세요";
        }
      }
      return "";
    },
    //시도 설정 시, 시군 목록 다르게
    selectArea() {
      let index = this.area0.indexOf(this.siDo);
      return this.areaList[index];
    }
  },
  methods: {
    // 아이디 중복 여부
    checkDuple() {
      if (this.user.email) {
        this.$axios
          .get("/users/1/" + this.user.email)
          .then(response => {
            if (this.user.email == response.data) {
              this.emailDuple = true;
            } else {
              this.emailDuple = false;
            }
          })
          .catch(() => {});
      }
    },
    //FU01
    showUserInfo() {
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      this.$axios
        .get("/users/4/" + email)
        .then(response => {
          this.user = response.data;
          let bornDate = this.user.bornDate.split("-");
          this.getYear = parseInt(bornDate[0]);
          this.getMonth = parseInt(bornDate[1]);
          this.getDay = parseInt(bornDate[2]);
          let area = this.user.area.split(" ");
          this.siDo = area[0];
          this.siGun = area[1];
        })
        .catch(error => {
          console.log(error);
        });
    },
    //FU04
    changePass() {
      this.doChangePass = true;
    },
    //FU05
    updateUser() {
      this.dataHandle();
      this.$axios
        .put("/users/1", this.user)
        .then(() => {
          alert("정보를 수정했습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("수정에 실패했습니다");
        })
        .finally(() => {
          location.reload();
        });
    },
    // FU06
    submitUser() {
      let result = this.checkValidation();
      if (!result) return false;

      // 1. 받은 변수 중 병합할 것 합치기
      this.dataHandle();

      // 2. 소셜로그인 시, 받은 id를 apikey로 넘기기
      if (this.id) this.user.apiKey = this.id;
      if (this.type) this.user.apiType = this.type;

      console.log(this.user);
      // 3. 등록
      this.$axios
        .post("/user", this.user)
        .then(() => {
          alert("회원가입이 완료됐습니다.");
          this.$router.push("/loginSignin");
        })
        .catch(() => {
          alert("회원가입에 실패했습니다.");
        });
    },
    checkValidation() {
      let user = this.user;
      if (!user.email) {
        alert("이메일을 입력해주세요");
        return false;
      }
      if (!user.pass) {
        alert("비밀번호을 입력해주세요");
        return false;
      }
      if (user.pass != user.pass2) {
        alert("비밀번호을 확인해주세요");
        return false;
      }
      if (user.pass.length < 8) {
        alert("비밀번호를 8자 이상으로 해주세요");
        return false;
      }
      if (!user.name) {
        alert("이름을 입력해주세요");
        return false;
      }
      if (!this.getYear || !this.getMonth || !this.getDay) {
        alert("생년월일을 입력해주세요");
        return false;
      }
      if (this.emailDuple) {
        alert("중복된 이메일입니다.");
        return false;
      }
      return true;
    },
    dataHandle() {
      // eslint-disable-next-line prettier/prettier
      let bornDateWritten = false;
      if (this.getYear && this.getMonth && this.getDay) bornDateWritten = true;
      let bornDate = "";
      if (bornDateWritten) {
        bornDate = new Date(this.getYear + "-" + this.getMonth + "-" + this.getDay);
      }
      this.user.bornDate = this.$moment(bornDate).format("yyyy-MM-DD");
      if (this.siDo || this.siGun) {
        this.user.area = this.siDo + " " + this.siGun;
      }
    }
  }
};
