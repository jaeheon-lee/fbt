<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <div class="map_wrap">
          <div
            id="map"
            style="width:100%;height:100%;position:relative;overflow:hidden;"
          ></div>

          <div id="menu_wrap" class="bg_white">
            <div class="option">
              <div>
                키워드 :
                <input
                  type="text"
                  id="keyword"
                  v-model="keywordStadium"
                  size="15"
                  @keydown.enter="searchMap"
                />
                <button @click="searchMap">검색하기</button>
              </div>
            </div>
            <hr />
            <ul
              id="placesList"
              class="pa-0"
              v-for="(place, i) in places"
              :key="i"
            >
              <li
                class="item"
                v-html="getListItem(i, place)"
                @click="selectPlace(place)"
              />
            </ul>
            <div id="pagination"></div>
          </div>
        </div>
      </v-row>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/map/Map.js"></script>
<style src="@/assets/css/map/Map.css"></style>
