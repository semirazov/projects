import Vue from 'vue';
import 'babel-polyfill';
import App from './App.vue';
import router from './router';
import './global-components';
import VueFetch, { $fetch } from './plugins/fetch';
import * as filters from './filters/filters';
import store from './store';
import { sync } from 'vuex-router-sync'

sync(store, router)

Vue.use(VueFetch, {
  baseUrl: 'http://localhost:3000'
});

for (let key in filters) {
  Vue.filter(key, filters[key]);
}

async function main() {
  new Vue({
    el: '#app',
    render: h => h(App),
    router,
    store
  });
}

main();
