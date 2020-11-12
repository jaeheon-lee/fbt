import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import moment from "moment";
import VueMomentJS from "vue-momentjs"

Vue.config.productionTip = false;

axios.defaults.baseURL = "http://localhost:7777";

Vue.use(VueMomentJS, moment);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");