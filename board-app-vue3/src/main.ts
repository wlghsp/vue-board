import { createApp } from 'vue';
import { BootstrapVue3 } from 'bootstrap-vue-3';
import App from './App.vue';
import router from './router';
import axios from 'axios';

// https://bootstrap-vue-3.netlify.app/
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';

// Set the base URL for Axios globally
axios.defaults.baseURL = 'http://localhost:8080';

// Create the Vue app instance
const app = createApp(App);

// Use BootstrapVue3 plugin
app.use(BootstrapVue3);

// Use the router instance
app.use(router);

// Mount the app to the DOM
app.mount('#app');