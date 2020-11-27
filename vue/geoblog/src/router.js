import VueRouter from 'vue-router';
import Vue from 'vue';
import GeoBlog from './components/GeoBlog';
import Login from './components/Login';
import NotFound from './components/NotFound';

Vue.use(VueRouter);

const routes = [
  {
    name: 'home',
    path: '/',
    component: GeoBlog
  },
  {
    name: 'login',
    path: '/login',
    component: Login
  },
  {
    path: '*',
    component: NotFound
  }
];

const router = new VueRouter({
  routes,
  mode: 'history'
});

export default router;
