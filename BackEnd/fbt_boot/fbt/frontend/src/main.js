import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import moment from "moment";
import VueMomentJS from "vue-momentjs";
import ProgressCircle from "@/components/Common/ProgressCircle.vue";
import Empty from "@/components/Common/Empty.vue";
import Last from "@/components/Common/Last.vue";

// 전역 컴포넌트
Vue.component("progress-circle", ProgressCircle);
Vue.component("empty", Empty);
Vue.component("last", Last);

//Event Bus
Vue.prototype.$EventBus = new Vue();

const url = "http://fbt.fbtogether.com";
// const url = "http://ec2-18-220-155-106.us-east-2.compute.amazonaws.com";
// const url = "http://localhost:80";

//image url 전역 상수
Vue.prototype.$emblem = url + "/emblem/";
Vue.prototype.$user = url + "/user/";

// Vue.prototype.$emblem = "http://ec2-18-220-155-106.us-east-2.compute.amazonaws.com:8080/emblem/";
// Vue.prototype.$user = "http://ec2-18-220-155-106.us-east-2.compute.amazonaws.com:8080/user/";

// http root 지정
// aws build / local tomcat
// Vue.prototype.$http = url + "/fbt";
Vue.prototype.$http = url;
//dev
// Vue.prototype.$http = "http://localhost:8080";

Vue.config.productionTip = false;

// axios root 지정
// aws build / local tomcat
// axios.defaults.baseURL = url + "/fbt";
axios.defaults.baseURL = url;
// dev
Vue.prototype.$axios = axios; 
// axios.defaults.baseURL = "http://localhost:7777";

// moment 라이브러리 전역 설정
Vue.use(VueMomentJS, moment);

// kakao 공유하기 서비스 이용을 위한 초기화
window.Kakao.init("26baced2916af3cfb52abda49077cd56");

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
