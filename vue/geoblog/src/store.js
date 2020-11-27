import Vuex from 'vuex';
import Vue from 'vue';

Vue.use(Vuex);

const store = new Vuex.Store({
  state() {
    return {
      user: {
        profile: {
          displayName: 'Mr. Danil'
        }
      }
    };
  },
  getters: {
    user: state => state.user,
    userPicture: state => null
  },
  mutations: {
    user(state, user) {
      state.user = user;
    }
  },
  actions: {
    login({ commit }) {
      const userData = {
        profile: {
          displayName: 'Mr. Danil'
        }
      };
      commit('user', userData);
    },
    logout({ commit }) {
      commit('user', null);
    }
  }
});

export default store;
