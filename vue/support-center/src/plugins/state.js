export default {
  install(Vue, state) {
    console.log('Installing plugin state.js...');
    Object.defineProperty(Vue.prototype, '$state', {
      get: () => state
    });
  }
};
