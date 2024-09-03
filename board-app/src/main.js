import Vue from "vue";
import BootstrapVue from "bootstrap-vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";

// https://bootstrap-vue.js.org/
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.config.productionTip = false;
Vue.use(BootstrapVue);

// Set the base URL for Axios globally
axios.defaults.baseURL = 'http://localhost:8080';

new Vue({
  router, // 라우터 객체를 넘겨준다
  render: h => h(App)
}).$mount("#app");
