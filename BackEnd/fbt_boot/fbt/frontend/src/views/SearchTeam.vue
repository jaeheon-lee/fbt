<template>
  <v-container>
    <v-row justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="py-0 my-0 px-0 mx-0">
        <!-- 팀찾기 라벨 -->
        <v-row class="py-0 my-5 px-0 mx-5">
          <v-col cols="12" class="pa-0 ma-0 mb-2">
            <span>팀찾기</span>
            <span
              class="mr-2 float-right"
              style="cursor:pointer;"
              @click="activeFilter = !activeFilter"
              >검색 조건
              <v-icon v-if="!activeFilter">mdi-chevron-right</v-icon>
              <v-icon v-else>mdi-chevron-down</v-icon>
            </span>
            <v-divider color="white"></v-divider>
          </v-col>
        </v-row>
        <!-- 팀찾기 라벨 끝 -->
        <!-- 검색 조건 -->
        <v-row
          fluid
          justify="center"
          class="py-0 my-0 px-0 mx-5"
          v-if="activeFilter"
        >
          <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
            <v-card>
              <!-- 팀 이름-->
              <v-row class="py-0 my-0 px-0 mx-5">
                <v-col cols="2" class="mt-1">
                  <span>팀 이름: </span>
                </v-col>
                <v-col cols="4">
                  <input
                    type="text"
                    class="filter-input mr-2"
                    style="margin-top: 0.2em; width:100%; color: white; border-bottom: solid 1px white;"
                    placeholder="팀 이름"
                    v-model="filter.teamName"
                  />
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <!-- 팀 이름 끝-->
              <!-- 팀 활동지역 -->
              <v-row class="py-0 my-0 px-0 mx-5">
                <v-col cols="2" class="mt-1">
                  <span>팀 활동지역: </span>
                </v-col>
                <v-col cols="4">
                  <v-select
                    class="ma-0 pa-0"
                    style="height: 1em"
                    :items="area0"
                    label="시/도"
                    v-model="filter.siDo"
                  ></v-select>
                </v-col>
                <v-col cols="4">
                  <v-select
                    class="ma-0 pa-0"
                    style="height: 1em"
                    :items="selectArea"
                    label="시/군/구"
                    v-model="filter.siGun"
                  ></v-select>
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <!-- 경기 타입 끝-->
              <!-- 정렬 기준 -->
              <v-row class="py-0 my-0 px-0 mx-5">
                <v-col cols="2" class="mt-1">
                  <span>정렬 기준: </span>
                </v-col>
                <v-col cols="4">
                  <v-select
                    class="ma-0 pa-0"
                    style="height: 1em"
                    :items="orderList"
                    item-text="label"
                    item-value="value"
                    label="정렬 기준"
                    v-model="filter.order"
                  ></v-select>
                </v-col>
              </v-row>
              <!-- 정렬 기준 끝-->
              <v-row fluid justify="center" class="mx-0 my-2 pt-2 pb-4">
                <v-btn
                  elevation="3"
                  class="mr-5"
                  @click="arrangeFilter"
                  color="#6920A3"
                  >검색하기</v-btn
                >
                <v-btn elevation="3" @click="initFilter" color="#AD1467"
                  >초기화</v-btn
                >
              </v-row>
            </v-card>
          </v-col>
        </v-row>
        <!-- 검색 조건 끝 -->
        <!-- 팀 리스트 -->
        <v-row justify="center">
          <v-card
            id="vote-list"
            v-for="(team, i) in teams"
            :key="i"
            elevation="2"
            class="ma-0 pa-0 mx-5 mb-3"
            width="70%"
          >
            <v-row class="pt-4">
              <!-- 홈팀 엠블럼 -->
              <v-col class="text-center pt-10" cols="2">
                <v-icon
                  style="cursor:pointer;"
                  v-if="activeTeamInfo == i"
                  @click="activeTeamInfo = null"
                  >mdi-chevron-down</v-icon
                >
                <v-icon
                  style="cursor:pointer;"
                  @click="activeTeamInfo = i"
                  v-else
                  >mdi-chevron-right</v-icon
                >
              </v-col>
              <v-col cols="3" class="my-0 py-0">
                <v-img
                  class="mb-5"
                  :src="getEmbUrl(team)"
                  aspect-ratio="1.6"
                  contain
                />
              </v-col>
              <v-col cols="4" class="my-0 py-0 pl-6">
                <!-- 홈팀 이름 -->
                <v-row> 팀명 : {{ team.teamName }} </v-row>
                <v-row class="my-2"> 활동지역: {{ team.area }} </v-row>
                <v-row>
                  유니폼: {{ team.uniformColor }}
                  {{ team.uniformType | showUniformType }}</v-row
                >
              </v-col>
              <v-col cols="1">
                <v-btn
                  class="mt-5"
                  color="#6920A3"
                  @click="controlNickInsert(i)"
                  v-if="checkTeamMember(team)"
                  >팀원 신청</v-btn
                >
                <v-btn class="mt-5" color="#6920A3" disabled v-else
                  >자기팀</v-btn
                >
              </v-col>
            </v-row>
            <!-- 팀상세정보 -->
            <v-row class="mx-5">
              <v-expand-transition>
                <team-info
                  :teamId="team.teamId"
                  v-if="activeTeamInfo == i"
                ></team-info>
              </v-expand-transition>
            </v-row>
            <v-row justify="center" class="mx-5" v-if="activeNickName == i">
              <span class="mr-5 pt-2">닉네임</span>
              <span>
                <v-text-field
                  dense
                  placeholder="닉네임을 입력해주세요"
                  @keyup.enter="applyTeamMember(team)"
                  v-model="nickName"
                ></v-text-field>
              </span>
              <span class="ml-2 pt-2">
                <v-icon @click="applyTeamMember(team)"
                  >mdi-pencil-box-outline</v-icon
                >
              </span>
            </v-row>
            <!-- 팀상세정보 끝 -->
          </v-card>
        </v-row>
        <!-- 팀 리스트 끝 -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TeamInfo from "@/components/Team/TeamInfo.vue";
export default {
  name: "search-team",
  components: {
    "team-info": TeamInfo
  },
  data() {
    return {
      //filter vo
      filter: {
        teamName: null,
        siDo: null,
        siGun: null,
        area: null,
        order: 0
      },

      // team vo
      teams: [],

      // 닉네임
      activeNickName: null,
      nickName: null,

      // 위치 시군구 정보
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
      // 정렬 기준 정보
      orderList: [
        { label: "실력 오름차순", value: 0 },
        { label: "매너 오름차순", value: 1 }
      ],
      // 토글 관련 변수
      activeFilter: true,
      activeTeamInfo: null
    };
  },
  mounted() {
    this.arrangeFilter();
  },
  computed: {
    //시도 설정 시, 시군 목록 다르게
    selectArea() {
      let index = this.area0.indexOf(this.filter.siDo);
      return this.areaList[index];
    }
  },
  methods: {
    // 검색하기 (FM02)
    arrangeFilter() {
      this.filter.area = this.filter.siDo + " " + this.filter.siGun;
      this.$axios
        .post("/team/2", this.filter)
        .then(response => {
          this.teams = response.data;
        })
        .catch(error => {
          console.log(error);
          alert("팀 불러오는 데 실패했습니다");
        })
        .finally(() => {
          this.activeFilter = false;
        });
    },
    applyTeamMember(team) {
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      let teamMember = {
        teamMemberId: team.teamId + "-" + email,
        memberLevel: 1,
        regStatus: 0,
        nickName: this.nickName,
        team: {
          teamId: team.teamId
        },
        user: {
          email: email
        }
      };
      this.$axios
        .post("/team-member/1", teamMember)
        .then(() => {
          alert("신청이 완료됐습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("신청에 실패했습니다.");
        })
        .finally(() => {
          this.arrangeFilter();
          this.activeNickName = null;
          this.nickName = null;
        })
    },
    initFilter() {
      this.filter = {
        teamName: null,
        siDo: null,
        siGun: null,
        order: 0
      };
    },
   // 엠블럼 이미지 가져오기
    getEmbUrl(team) {
      if (team) {
        return this.$emblem + this.team.emblem;
      } else {
        return this.$emblem + "emptyFC.svg";
      }
    },
    // 닉네임 창 여닫기
    controlNickInsert(i) {
      if (this.activeNickName == i) this.activeNickName = null;
      else this.activeNickName = i;
    },
    // 팀원 신청 조절 메소드
    checkTeamMember(team) {
      let teamMembers = team.teamMembers;
      let email = JSON.parse(sessionStorage.getItem("userInfo")).email;
      for (let i = 0; i < teamMembers.length; i++) {
        let email2 = teamMembers[i].teamMemberId.split("-")[1];
        if (email == email2) {
          return false;
        }
      }
      return true;
    }
  },
  filters: {
    showUniformType(value) {
      if (value == 1) return "조끼";
      else return "유니폼";
    }
  }
};
</script>

<style></style>
