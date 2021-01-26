import EmployList from "@/components/Employ/EmployList.vue";
import axios from "axios";

export default {
  name: "search-employ",
  components: {
    "employ-list": EmployList
  },
  data() {
    return {
      // 검색 결과 받을 변수
      searchedEmploys: [],
      // 용병 신청 버튼 관리 변수
      whichBtnActive: [],
      // 필터 변수
      filter: {
        date: null,
        timeRange1: null,
        timeRange2: null,
        dateTime1: null,
        dateTime2: null,
        siDo: null,
        siGun: null,
        stadiumAddress: null,
        stadiumName: null,
        matchType: null,
        stadiumType: null,
        costType: -1,
        order: 0,
        page: 0
      },
      // 검색 조건 토글 변수
      activeFilter: true, // 화면을 처음 열면 필터 먼저 연다
      // 시간 구간 관련 변수
      activeTimeRange: false, // 클릭 시 시간 구간 출력
      // 시군구 콤보셀렉트 변수
      whichGu: null,
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
      ],
      //경기장 타입 정보
      // eslint-disable-next-line prettier/prettier
      matchTypes: ["11:11", "10:10", "9:9", "8:8", "7:7", "6:6", "5:5"], //select option properties
      // eslint-disable-next-line prettier/prettier
      stadiumTypes: ['흙', '잔디', '인조잔디'], // select option properties
      // 정렬 기준 정보
      orderList: [
        { label: "등록일 최신순", value: 0 },
        { label: "실력 오름차순", value: 1 },
        { label: "매너 오름차순", value: 2 }
      ],
      // 기본변수
      loading: false,
      errored: false,
      empty: false,
      isFirstSearch: false,
      isLast: false
    };
  },
  created() {
    window.addEventListener("scroll", this.handleScroll);
  },
  mounted() {
    this.doSearch();
  },
  destroyed() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  computed: {
    //시도 설정 시, 시군 목록 다르게
    selectArea() {
      let index = this.area0.indexOf(this.filter.siDo);
      return this.areaList[index];
    }
  },
  methods: {
    //스크롤 이벤트 : 스크롤 바가 맨 밑에 있을 때
    handleScroll() {
      if (
        window.innerHeight + window.scrollY >= document.body.offsetHeight - 1 &&
        !this.isLast &&
        !this.loading &&
        !this.empty
      ) {
        this.arrangeFilter();
      }
    },
    //
    cleanSearch() {
      this.searchedEmploys = [];
      this.filter.page = 0;
      this.isLast = false;
      this.empty = false;
      this.isFirstSearch = false;
      this.arrangeFilter();
    },
    // 검색하기 (FE02)
    arrangeFilter() {
      // 경기 시간 통합
      let timeRange1 = this.filter.timeRange1;
      let timeRange2 = this.filter.timeRange2;
      if (timeRange1) {
        // eslint-disable-next-line prettier/prettier
        if (timeRange1.length == 1) this.filter.timeRange1 = "0" + timeRange1;
        // eslint-disable-next-line prettier/prettier
        this.filter.dateTime1 = this.filter.date + " " + timeRange1 + ":00";
        this.filter.date = null;
      }
      if (timeRange2) {
        // eslint-disable-next-line prettier/prettier
        if (timeRange2.length == 1) this.filter.timeRange2 = "0" + timeRange2;
        // eslint-disable-next-line prettier/prettier
        this.filter.dateTime2 = this.filter.date + " " + timeRange2 + ":00";
        this.filter.date = null;
      }
      // 경기 위치 통합
      this.filter.stadiumAddress = this.filter.siDo;
      let siGun = this.filter.siGun;
      if (siGun) {
        this.filter.stadiumAddress = this.filter.siDo + " " + siGun;
      }

      // 필터 창 닫기
      this.activeFilter = false;
      // 검색
      this.doSearch();
    },
    // 필터 검색(FE02)
    doSearch() {
      this.loading = true;
      // eslint-disable-next-line prettier/prettier
      axios
        .post("/employ/2", this.filter)
        .then(response => {
          if (this.isFirstSearch) {
            setTimeout(() => {
              this.searchedEmploys = this.searchedEmploys.concat(response.data);
            }, 1000);
          } else {
            this.searchedEmploys = this.searchedEmploys.concat(response.data);
          }
          // 결과가 없는지
          if (response.data.length == 0 && !this.isFirstSearch) {
            this.empty = true;
          }
          // 마지막 게시물인지
          if (response.data.length < 2 && this.isFirstSearch) {
            this.isLast = true;
          } else {
            // 아니면 page를 증가시킨다
            this.filter.page += 2;
          }
          for (let j = 0; j < response.data.length; j++) {
            this.controlEmployApplyBtn(response.data[j]);
          }
          this.isFirstSearch = true;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          setTimeout(() => {
            this.loading = false;
          }, 1000);
        });
    },
    // 용병 검색 결과 출력 시, 이미 검색한 항목에 대해서 표시
    // 이메일이 같으면 false, 다르면 true
    // 마감 버튼 조절 메소드
    controlEmployApplyBtn(employ) {
      // 마감조건 : 마감일이 지났거나 이미 신청하지 않은 글
      let employRes = employ.employResults;
      // 마감일이 지났는지
      let dueDate = new Date(employ.dueDate);
      let today = new Date();
      if (dueDate < today) {
        this.whichBtnActive.push(2);
        return;
      }
      // 글이 완료됐는지
      if (employ.matchSchedule.isConfirmed == 1) {
        this.whichBtnActive.push(2);
        return;
      }

      // 신청하지 않았는지
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      for (let i = 0; i < employRes.length; i++) {
        if (employRes[i].user.email == email) {
          this.whichBtnActive.push(1);
          return;
        }
      }
      this.whichBtnActive.push(0);
    },
    // 필터 초기화
    initFilter() {
      this.filter = {
        date: null,
        timeRange1: null,
        timeRange2: null,
        dateTime1: null,
        dateTime2: null,
        siDo: null,
        siGun: null,
        stadiumAddress: null,
        stadiumName: null,
        matchType: null,
        stadiumType: null,
        costType: -1,
        order: 0
      };
    }
  }
};
