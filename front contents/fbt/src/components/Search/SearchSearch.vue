<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <!-- 검색 라벨 -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>매치 검색</span>
          <span
            class="mr-2 float-right"
            style="cursor:pointer;"
            @click="activeFilter = !activeFilter"
            >검색 조건
            <v-icon>mdi-chevron-down</v-icon>
          </span>
          <v-divider color="white"></v-divider>
        </v-col>
      </v-row>
      <!-- 검색 랍레 끝-->
      <!-- 검색 조건 -->
      <v-row
        fluid
        justify="center"
        class="py-0 my-0 px-0 mx-5"
        v-if="activeFilter"
      >
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <v-card>
            <!-- 일시 -->
            <v-row class="py-0 my-0 px-0 mx-5">
              <!-- 일시 라벨 -->
              <v-col cols="2" class="mt-1">
                <span>일시: </span>
              </v-col>
              <!-- 달력 -->
              <v-col cols="4">
                <input
                  type="date"
                  class="filter-input"
                  style="width:100%;"
                  v-model="filter.date"
                />
              </v-col>
              <!-- 시간 -->
              <v-col cols="3" v-if="!activeTimeRange">
                <v-btn
                  class="ma-0 pa-0 px-2"
                  color="#6920A3"
                  style="height: 2em"
                  @click="activeTimeRange = true"
                  >시간구간 설정</v-btn
                >
              </v-col>
              <v-col cols="3" v-if="activeTimeRange">
                <input
                  type="text"
                  class="filter-input mr-2"
                  style="width:20%;"
                  v-model="filter.timeRange1"
                />
                <span class="mr-2">시</span>
                <v-icon id="tilde" class="mr-3">mdi-tilde md-16</v-icon>
                <input
                  type="text"
                  class="filter-input mr-2"
                  style="width:20%;"
                  v-model="filter.timeRange2"
                />
                <span class="mr-2">시</span>
              </v-col>
            </v-row>
            <v-divider></v-divider>
            <!-- 일시 끝-->
            <!-- 경기장 위치 -->
            <v-row class="py-0 my-0 px-0 mx-5">
              <v-col cols="2" class="mt-1">
                <span>경기장 위치: </span>
              </v-col>
              <v-col cols="4">
                <v-select
                  class="ma-0 pa-0"
                  style="height: 1em"
                  :items="area0"
                  label="시/도 선택"
                  v-model="filter.siDo"
                ></v-select>
              </v-col>
              <v-col cols="4">
                <v-select
                  class="ma-0 pa-0"
                  style="height: 1em"
                  :items="selectArea"
                  label="구/군 선택"
                  v-model="filter.siGun"
                ></v-select>
              </v-col>
            </v-row>
            <v-divider></v-divider>
            <!-- 경기장 위치 끝-->
            <!-- 경기장 이름-->
            <v-row class="py-0 my-0 px-0 mx-5">
              <v-col cols="2" class="mt-1">
                <span>경기장 이름: </span>
              </v-col>
              <v-col cols="4">
                <input
                  type="text"
                  class="filter-input mr-2"
                  style="width:100%;"
                  placeholder="경기장 이름"
                  v-model="filter.stadiumName"
                />
              </v-col>
            </v-row>
            <v-divider></v-divider>
            <!-- 경기장 이름 끝-->
            <!-- 경기 타입 -->
            <v-row class="py-0 my-0 px-0 mx-5">
              <v-col cols="2" class="mt-1">
                <span>경기장 타입: </span>
              </v-col>
              <v-col cols="4">
                <v-select
                  class="ma-0 pa-0"
                  style="height: 1em"
                  :items="matchTypes"
                  label="경기 타입"
                  v-model="filter.matchType"
                ></v-select>
              </v-col>
              <v-col cols="4">
                <v-select
                  class="ma-0 pa-0"
                  style="height: 1em"
                  :items="stadiumTypes"
                  label="경기장 타입"
                  v-model="filter.stadiumType"
                ></v-select>
              </v-col>
            </v-row>
            <v-divider></v-divider>
            <!-- 경기 타입 끝-->
            <!-- 비용 유무 -->
            <v-row class="py-0 my-0 px-0 mx-5">
              <v-col cols="2" class="mt-1">
                <span>비용 유무: </span>
              </v-col>
              <v-col cols="4">
                <v-radio-group
                  class="ma-0 mt-1 pa-0"
                  row
                  hide-details="false"
                  v-model="filter.costType"
                >
                  <v-radio label="유" value="1" />
                  <v-radio label="무" value="0" />
                  <v-radio label="상관없음" value="-1" />
                </v-radio-group>
              </v-col>
            </v-row>
            <v-divider></v-divider>
            <!-- 비용 유무 끝-->
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
      <!-- 검색 결과 라벨 -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>검색 결과</span>
        </v-col>
      </v-row>
      <!-- 검색 결과 라벨 끝-->
      <search-list
        :searchedSearch="searchedSearch"
        :registeredStage="null"
        :appliedStage="null"
        :whichBtnActive="whichBtnActive"
      ></search-list>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/search/SearchSearch.js"></script>
<style scoped src="@/assets/css/common/Search.css"></style>
