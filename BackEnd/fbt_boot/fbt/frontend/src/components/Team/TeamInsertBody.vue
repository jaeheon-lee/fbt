<template>
  <v-container>
    <v-row justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="py-0 my-0 px-0 mx-0">
        <!-- 팀만들기 라벨 -->
        <v-row class="py-0 my-5 px-0 mx-5">
          <v-col cols="12" class="pa-0 ma-0 mb-2">
            <span>팀 만들기</span>
            <v-divider color="white"></v-divider>
          </v-col>
        </v-row>
        <!-- 팀만들기 라벨 끝 -->
        <!-- 팀만들기 칸 -->
        <form>
          <v-row>
            <v-col
              offset-lg="2"
              lg="8"
              offset-md="2"
              md="8"
              offset-sm="2"
              sm="8"
              xs="12"
            >
              <!-- page1 -->
              <v-card class="mx-5 py-4" v-if="page == 1">
                <!-- 팀이름-->
                <v-row class="pt-6">
                  <v-text-field
                    class="mx-15"
                    label="팀명"
                    placeholder="팀명을 입력해주세요"
                    dense
                    counter="50"
                    :rules="[rules.required]"
                    @keyup="checkDuple"
                    v-model="team.teamName"
                  ></v-text-field>
                </v-row>
                <v-row
                  class="ml-12 my-0 py-0 red--text"
                  style="font-size: 0.8em"
                  v-if="teamNameDuple"
                >
                  중복된 팀명입니다.
                </v-row>
                <!-- 팀이름 끝 -->
                <!-- 팀엠블럼 -->
                <v-row class="pt-6">
                  <v-file-input
                    class="mx-7 pr-8"
                    label="팀 엠블럼"
                    placeholder="팀 엠블럼 이미지를 업로드해주세요"
                    dense
                    prepend-icon="mdi-camera"
                    accept="image/png, image/jpeg, image/jpg"
                    show-size
                    :rules="[rules.image]"
                    v-model="emblemFile"
                  ></v-file-input>
                </v-row>
                <!-- 팀엠블럼 끝 -->
                <!-- 주활동지역 -->
                <v-row justify="center" class="mt-6">
                  <span style="width: 35%;" class="mr-7">
                    <v-select
                      dense
                      placeholder="활동지역 시/도"
                      outlined
                      :items="area0"
                      label="활동지역 시/도"
                      hide-details
                      v-model="siDo"
                    ></v-select>
                  </span>
                  <span style="width: 35%;">
                    <v-select
                      dense
                      placeholder="활동지역 시/구/군"
                      outlined
                      :items="selectArea"
                      label="활동지역 시/구/군"
                      hide-details
                      v-model="siGun"
                    ></v-select>
                  </span>
                </v-row>
                <!-- 주활동지역 끝 -->
                <!-- 주경기장 -->
                <v-row class="pt-6">
                  <v-text-field
                    class="mx-15"
                    label="주경기장"
                    placeholder="주경기장을 입력해주세요"
                    readonly
                    dense
                    v-model="team.stadiumInfo"
                    @click="dialogStadium = true"
                  ></v-text-field>
                  <!-- 경기장 Dialog : Map.vue -->
                  <v-dialog v-model="dialogStadium">
                    <v-row fluid justify="center">
                      <v-col xl="6" lg="8" cols="12">
                        <v-card>
                          <v-card-title class="headline"
                            >원하시는 장소를 목록에서 클릭해주세요</v-card-title
                          >
                          <v-card-text>
                            <map-vue @target="setTargetStadium"></map-vue>
                          </v-card-text>
                          <v-card-actions class="justify-end">
                            <v-btn
                              class="ma-0 ml-8 pa-0"
                              elevation="2"
                              color="#6920A3"
                              @click="dialogStadium = false"
                              style="font-size: 0.85em"
                              >확인</v-btn
                            >
                          </v-card-actions>
                        </v-card>
                      </v-col>
                    </v-row>
                  </v-dialog>
                </v-row>
                <!-- 주경기장 끝 -->
                <!-- 유니폼 유형 -->
                <v-row class="pt-6">
                  <v-select
                    class="mx-14 my-0"
                    dense
                    placeholder="유니폼 유형을 선택해주세요"
                    outlined
                    :items="uniformType"
                    item-text="label"
                    item-value="value"
                    label="유니폼 유형"
                    hide-details
                    v-model="team.uniformType"
                  ></v-select>
                </v-row>
                <!-- 유니폼 유형 끝 -->
                <!-- 유니폼 색깔 -->
                <v-row class="pt-6">
                  <v-select
                    class="mx-14 my-0"
                    dense
                    placeholder="유니폼 색을 선택해주세요"
                    outlined
                    :items="uniformColor"
                    label="유니폼 색"
                    hide-details
                    v-model="team.uniformColor"
                  ></v-select>
                </v-row>
                <!-- 유니폼 색깔 끝 -->
                <!-- 창립일자 -->
                <v-row class="pt-6">
                  <v-text-field
                    class="mx-15"
                    label="창립일"
                    placeholder="창립일을 입력해주세요"
                    dense
                    type="date"
                    v-model="team.foundingDate"
                  ></v-text-field>
                </v-row>
                <!-- 창립일자 끝 -->
                <!-- 계좌 -->
                <v-row justify="center" class="pt-6">
                  <span style="width: 25%;" class="ml-5 mr-7">
                    <v-select
                      dense
                      placeholder="은행명"
                      outlined
                      :items="bank"
                      label="은행명"
                      hide-details
                      v-model="team.bank"
                    ></v-select>
                  </span>
                  <span style="width: 45%;" class="mr-7">
                    <v-text-field
                      label="계좌번호"
                      placeholder="계좌번호을 입력해주세요"
                      dense
                      v-model="team.account"
                    ></v-text-field>
                  </span>
                </v-row>
                <!-- 계좌 끝 -->
                <!-- 닉네임 -->
                <v-row class="pt-6">
                  <v-text-field
                    class="mx-15"
                    label="닉네임"
                    placeholder="사용할 닉네임을 입력해주세요"
                    dense
                    counter="45"
                    :rules="[rules.required]"
                    v-model="teamMember.nickName"
                  ></v-text-field>
                </v-row>
                <!-- 닉네임 끝 -->
                <v-row justify="end" class="mt-2 mr-11">
                  <v-btn color="#6920A3" @click="submit">확인</v-btn>
                </v-row>
              </v-card>
            </v-col>
          </v-row>
        </form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import MapVue from "@/components/Map/Map.vue";
export default {
  name: "team-insert-body",
  components: {
    "map-vue": MapVue
  },
  data() {
    return {
      // team vo
      team: {
        teamName: null,
        emblem: null,
        area: null,
        stadiumInfo: null,
        uniformColor: null,
        uniformType: null,
        foundingDate: null,
        account: null,
        bank: null
      },
      // teamMember vo
      teamMember: {
        teamMemberId: null,
        memberLevel: 4,
        regStatus: 1,
        nickName: null,
        team: {
          teamId: null
        },
        user: {
          email: null
        }
      },

      emblemFile: null,
      page: 1,
      rules: {
        required: value => !!value || "필수항목",
        image: value =>
          !value ||
          value.size < 10000000 ||
          "이미지 크기는 10 MB 미만이어야 합니다"
      },
      teamNameDuple: false,
      siDo: null,
      siGun: null,
      dialogStadium: false,
      uniformType: [
        { label: "유니폼", value: 0 },
        { label: "조끼", value: 1 }
      ],
      // eslint-disable-next-line prettier/prettier
      uniformColor: ["검정", "하얀", "빨강", "주황", "노랑", "초록", "파랑", "남색", "보라"],
      bank: ["신한", "국민", "우리", "기업", "카카오뱅크"],
      // 팀 활동지역 위치 시군구 정보
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
    };
  },
  computed: {
    //시도 설정 시, 시군 목록 다르게
    selectArea: function() {
      let index = this.area0.indexOf(this.siDo);
      return this.areaList[index];
    }
  },
  methods: {
    // 팀명 중복 확인
    checkDuple() {
      if (this.team.teamName) {
        this.$axios
          .get("/team/4/" + this.team.teamName)
          .then(response => {
            if (this.team.teamName == response.data) this.teamNameDuple = true;
            else this.teamNameDuple = false;
          })
          .catch(error => {
            console.log(error);
          });
      }
    },
    // Map에서 받은  targetStadium 적용하기
    setTargetStadium(place) {
      this.team.stadiumInfo = place.place_name;
      this.dialogStadium = false;
    },
    //FT02
    submit() {
      this.team.area = this.siDo + " " + this.siGun;
      this.teamMember.user.email = JSON.parse(
        sessionStorage.getItem("userInfo")
      ).email;
      var fd = new FormData();
      fd.append("file", this.emblemFile);
      fd.append(
        "team",
        new Blob([JSON.stringify(this.team)], { type: "application/json" })
      );
      fd.append(
        "teamMember",
        new Blob([JSON.stringify(this.teamMember)], { type: "application/json" })
      );
      this.$axios
        .post("/team/1", fd, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(() => {
          alert("팀 등록이 완료됐습니다.");
        })
        .catch(error => {
          console.log(error);
          alert("팀 등록에 실패했습니다.");
        });
    }
  }
};
</script>

<style></style>
