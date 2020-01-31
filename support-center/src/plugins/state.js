export default {
  install(Vue, state) {
    console.log('Installing plugin state.js...');
    Vue.prototype.$state = state;
  }
};
