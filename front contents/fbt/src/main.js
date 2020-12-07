import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import moment from "moment";
import VueMomentJS from "vue-momentjs";

//Event Bus
Vue.prototype.$EventBus = new Vue();

// http root 지정
Vue.prototype.$http = "http://localhost:8080";

Vue.config.productionTip = false;

// axios root 지정
axios.defaults.baseURL = "http://localhost:7777";

Vue.prototype.$axios = axios;

// moment 라이브러리 전역 설정
Vue.use(VueMomentJS, moment);

// kakao 서비스 이용을 위한 초기화
window.Kakao.init("26baced2916af3cfb52abda49077cd56");

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
