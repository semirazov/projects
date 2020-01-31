import Vue from 'vue';
import 'babel-polyfill';
import App from './App.vue';
import router from './router';
import './global-components';
import VueFetch, {$fetch} from './plugins/fetch'
import VueState from './plugins/state';

let state = {
  user: null
};


Vue.use(VueFetch, {
  baseUrl: 'http://localhost:3000'
});

Vue.use(VueState, {
  state
});

async function main() {

  // Get user info
  try {
    state.user = await $fetch('user')
  } catch (e) {
    console.warn(e)
  }

  console.log(state.user);

  new Vue({
    data: state,
    el: '#app',
    render: h => h(App),
    router
  });
}

main();

