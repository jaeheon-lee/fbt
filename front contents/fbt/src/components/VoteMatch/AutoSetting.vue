<template>
  <div>
    <!--투표이후 자동화 설정-->
    <v-divider class="mt-4 mb-2"></v-divider>
    <v-expansion-panels focusable hover>
      <v-expansion-panel
        class="ma-0 pa-0"
        style="background-color:rgb(52, 55, 58,0.1);"
      >
        <v-expansion-panel-header hide-actions class="ma-0 pa-0">
          <v-row class="mx-0 px-0">
            <v-col
              cols="11"
              style="align-self:center;"
              class="text-left mx-0 px-0 pl-1"
              >(선택사항 1) 투표 이후 자동화 설정</v-col
            >
            <v-col cols="1" class="text-right mx-0 px-0">
              <v-icon>keyboard_arrow_down</v-icon>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content
          style="background-color:#121212;font-size: 0.7rem;line-height:100%;color:rgba(255, 255, 255, 0.9)"
        >
          <v-checkbox
            class="ma-0 pa-0 mt-4"
            style="font-size:15px!important;"
            hide-details
            v-model="selectedYoungByung"
            label="용병 모집"
            value="youngByung"
          ></v-checkbox>
          <v-checkbox
            class="ma-0 pa-0 mt-2"
            style="font-size:15px!important;"
            hide-details
            v-model="selectedAwayTeam"
            label="상대팀 구하기"
            value="awayTeam"
          ></v-checkbox>
          <!-- 기준 설정 1 selectedYoungByung&&selectedAwayTeam-->
          <v-row
            v-if="selectedYoungByung && selectedAwayTeam"
            class="ma-0 pa-0 mt-1 align:center"
            style="font-size:0.75rem;background-color:rgb(255,255,255,0.1)"
          >
            <v-row class="ma-2 pa-0 mt-4" style="width:100%;font-size:13px">
              <v-col cols="8" class="ma-0 pa-1" style="align-self: center;"
                >- 경기 취소 기준 인원</v-col
              >
              <v-col cols="3" class="ma-0 pa-1">
                <v-row
                  class="ma-0"
                  justify="center"
                  style="padding:2px;border:2px solid #AD1457;"
                >
                  <select
                    v-model="selectedMinBasisNumber"
                    v-if="selectedMinBasisNumber == ''"
                    style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                  <select
                    v-else
                    v-model="selectedMinBasisNumber"
                    style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                </v-row>
              </v-col>
              <v-col
                cols="1"
                class="ma-0 pa-1 text-center"
                style="align-self: center;"
                >(?)</v-col
              >
            </v-row>

            <v-row class="ma-2 pa-0" style="width:100%;font-size:13px">
              <v-col cols="8" class="ma-0 pa-1" style="align-self: center;">
                <div>- 경기 가능 최소 기준 인원</div>
                <div>(with 용병 & 다른 팀)</div>
              </v-col>
              <v-col cols="3" class="ma-0 pa-1">
                <v-row
                  class="ma-0"
                  justify="center"
                  style="padding:2px;border:2px solid #AD1457;"
                >
                  <select
                    v-model="selectedMinBasisNumber"
                    v-if="selectedMinBasisNumber == ''"
                    style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                  <select
                    v-else
                    v-model="selectedMinBasisNumber"
                    style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                </v-row>
              </v-col>
              <v-col
                cols="1"
                class="ma-0 pa-1 text-center"
                style="align-self: center;"
                >(?)</v-col
              >
            </v-row>

            <v-row class="ma-2 pa-0" style="width:100%;font-size:13px">
              <v-col cols="8" class="ma-0 pa-1" style="align-self: center;">
                <div>- 자체 경기 최소기준 인원</div>
                <div>(with 용병)</div>
              </v-col>
              <v-col cols="3" class="ma-0 pa-1">
                <v-row
                  class="ma-0"
                  justify="center"
                  style="padding:2px;border:2px solid #AD1457;"
                >
                  <select
                    v-model="selectedMinBasisNumber"
                    v-if="selectedMinBasisNumber == ''"
                    style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                  <select
                    v-else
                    v-model="selectedMinBasisNumber"
                    style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                </v-row>
              </v-col>
              <v-col
                cols="1"
                class="ma-0 pa-1 text-center"
                style="align-self: center;"
                >(?)</v-col
              >
            </v-row>

            <v-row class="ma-2 pa-0 mb-2" style="width:100%;font-size:13px">
              <v-col cols="8" class="ma-0 pa-1" style="align-self: center;">
                <div>- 자체 경기 기준 인원</div>
                <div>(without 용병)</div>
              </v-col>
              <v-col cols="3" class="ma-0 pa-1">
                <v-row
                  class="ma-0"
                  justify="center"
                  style="padding:2px;border:2px solid #AD1457;"
                >
                  <select
                    v-model="selectedMinBasisNumber"
                    v-if="selectedMinBasisNumber == ''"
                    style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                  <select
                    v-else
                    v-model="selectedMinBasisNumber"
                    style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                </v-row>
              </v-col>
              <v-col
                cols="1"
                class="ma-0 pa-1 text-center"
                style="align-self: center;"
                >(?)</v-col
              >
            </v-row>
          </v-row>
          <!-- 기준 설정 1 끝 -->
          <!-- 기준 설정 2 selectedYoungByung&&selectedAwayTeam-->
          <v-row
            v-if="selectedYoungByung && !selectedAwayTeam"
            class="ma-0 pa-0 mt-1 align:center"
            style="font-size:0.75rem;background-color:rgb(255,255,255,0.1)"
          >
            <v-row class="ma-2 pa-0" style="width:100%;font-size:13px">
              <v-col cols="8" class="ma-0 pa-1" style="align-self: center;">
                <div>- 자체 경기 최소기준 인원</div>
                <div>(with 용병)</div>
              </v-col>
              <v-col cols="3" class="ma-0 pa-1">
                <v-row
                  class="ma-0"
                  justify="center"
                  style="padding:2px;border:2px solid #AD1457;"
                >
                  <select
                    v-model="selectedMinBasisNumber"
                    v-if="selectedMinBasisNumber == ''"
                    style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                  <select
                    v-else
                    v-model="selectedMinBasisNumber"
                    style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                </v-row>
              </v-col>
              <v-col
                cols="1"
                class="ma-0 pa-1 text-center"
                style="align-self: center;"
                >(?)</v-col
              >
            </v-row>

            <v-row class="ma-2 pa-0 mb-2" style="width:100%;font-size:13px">
              <v-col cols="8" class="ma-0 pa-1" style="align-self: center;">
                <div>- 자체 경기 기준 인원</div>
                <div>(without 용병)</div>
              </v-col>
              <v-col cols="3" class="ma-0 pa-1">
                <v-row
                  class="ma-0"
                  justify="center"
                  style="padding:2px;border:2px solid #AD1457;"
                >
                  <select
                    v-model="selectedMinBasisNumber"
                    v-if="selectedMinBasisNumber == ''"
                    style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                  <select
                    v-else
                    v-model="selectedMinBasisNumber"
                    style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                </v-row>
              </v-col>
              <v-col
                cols="1"
                class="ma-0 pa-1 text-center"
                style="align-self: center;"
                >(?)</v-col
              >
            </v-row>
          </v-row>
          <!-- 기준 설정 2 끝 -->

          <!-- 기준 설정 3 -->
          <v-row
            v-if="!selectedYoungByung && selectedAwayTeam"
            class="ma-0 pa-0 mt-1 align:center"
            style="font-size:0.75rem;background-color:rgb(255,255,255,0.1)"
          >
            <v-row class="ma-2 pa-0 mt-4" style="width:100%;font-size:13px">
              <v-col cols="8" class="ma-0 pa-1" style="align-self: center;"
                >- 경기 취소 기준 인원</v-col
              >
              <v-col cols="3" class="ma-0 pa-1">
                <v-row
                  class="ma-0"
                  justify="center"
                  style="padding:2px;border:2px solid #AD1457;"
                >
                  <select
                    v-model="selectedMinBasisNumber"
                    v-if="selectedMinBasisNumber == ''"
                    style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                  <select
                    v-else
                    v-model="selectedMinBasisNumber"
                    style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                </v-row>
              </v-col>
              <v-col
                cols="1"
                class="ma-0 pa-1 text-center"
                style="align-self: center;"
                >(?)</v-col
              >
            </v-row>

            <v-row class="ma-2 pa-0 mb-2" style="width:100%;font-size:13px">
              <v-col cols="8" class="ma-0 pa-1" style="align-self: center;">
                <div>- 자체 경기 기준 인원</div>
                <div>(without 용병)</div>
              </v-col>
              <v-col cols="3" class="ma-0 pa-1">
                <v-row
                  class="ma-0"
                  justify="center"
                  style="padding:2px;border:2px solid #AD1457;"
                >
                  <select
                    v-model="selectedMinBasisNumber"
                    v-if="selectedMinBasisNumber == ''"
                    style="color:rgb(255, 255, 255, 0.5);text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                  <select
                    v-else
                    v-model="selectedMinBasisNumber"
                    style="color:#ffffff;vertical-align:middle;text-align-last:center;"
                  >
                    <option disabled value>명</option>
                    <option
                      style="text-align-last:center;color:#ffffff;"
                      v-for="minBasisNumber in minBasisNumbers"
                      v-bind:key="minBasisNumber"
                      >{{ minBasisNumber }}명</option
                    >
                  </select>
                </v-row>
              </v-col>
              <v-col
                cols="1"
                class="ma-0 pa-1 text-center"
                style="align-self: center;"
                >(?)</v-col
              >
            </v-row>
          </v-row>
          <!-- 기준 설정 3 끝 -->
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <!--투표이후 자동화 설정 끝-->
  </div>
</template>
<script>
export default {
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

      awayTeam: "미정",
      completeresult: {
        address: ""
      },
      contents: "",

      matchCosts: [
        0,
        1,
        2,
        3,
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
      selectedPark: "",
    }
  }
}
</script>