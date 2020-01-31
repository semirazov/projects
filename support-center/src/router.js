import Home from './components/Home.vue';
import FAQ from './components/FAQ.vue';
import Login from './components/Login.vue';
import VueRouter from 'vue-router';
import Vue from 'vue';

Vue.use(VueRouter);

const routes = [
  {
    name: 'home',
    path: '/',
    component: Home
  },
  {
    name: 'faq',
    path: '/faq',
    component: FAQ
  },
  {
    name: 'login',
    path: '/login',
    component: Login
  }
];

export default new VueRouter({
  routes,
  mode: 'history'
});
