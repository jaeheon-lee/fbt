<template>
  <v-container fluid class="py-0 my-5 px-0 mx-0">
    <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
        <span>가입한 팀</span>
      </v-col>
    </v-row>
    <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
        <v-divider class="mb-1 px-5"></v-divider>
        <v-carousel
          cycle
          height="100%"
          hide-delimiter-background
          show-arrows-on-hover
          align="center"
          justify="center"
        >
          <v-carousel-item
            v-for="(slide, i) in slides"
            :key="i"
            style="position:relative text-align:center;"
          >
            <v-img
              :src="imageTrans(slide)"
              aspect-ratio="1.5"
              class="mx-0 my-0"
              style="opacity:0.5;"
            >
            </v-img>
            <div
              class="mt-4"
              style="opacity:0.7; text-shadow:1.5px 1.5px #cccccc; left: 0;top:0; width: 100%;font-size:30px;position:absolute;"
            >
              {{ imageNameProcessing(slide) }}
            </div>
          </v-carousel-item>
        </v-carousel>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data() {
    return {
      colors: ["#AD145750", "#AD145750", "#AD145750", "#AD145750", "#AD145750"],
      slides: [
        "레알마드리드.jpg",
        "맨시티.jpg",
        "토트넘.jpg",
        "FC 바르셀로나.jpg",
        "맨체스터유나이티드.jpg"
      ]
    };
  },
  methods: {
    imageTrans(pic) {
      return require("../assets/image/" + pic);
    },
    imageNameProcessing(pic) {
      return pic.split(".")[0];
    }
  }
};
</script>
