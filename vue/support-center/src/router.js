import Home from './components/Home';
import FAQ from './components/FAQ';
import Login from './components/Login';
import VueRouter from 'vue-router';
import Vue from 'vue';
import state from './state';
import TickersLayout from './components/TickersLayout';
import Tickets from './components/Tickets';
import NewTicket from './components/NewTicket';
import Ticket from './components/Ticket';
import NotFound from './components/NotFound';

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
    component: Login,
    meta: {
      guest: true
    }
  },
  {
    name: 'tickets',
    path: '/tickets',
    component: TickersLayout,
    meta: {
      private: true
    },
    children: [
      { path: '', name: 'tickets', component: Tickets },
      { path: 'new', name: 'new-ticket', component: NewTicket },
      { path: ':id', name: 'ticket', component: Ticket, props: true }
    ]
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

router.beforeEach((to, from, next) => {
  console.log(to.name);
  if (to.matched.some(r => r.meta.private) && !state.user) {
    next({
      name: 'login',
      params: {
        wantedRoute: to.fullPath
      }
    });
  } else if (to.matched.some(r => r.meta.guest) && state.user) {
    next({ name: 'home' });
  } else {
    next();
  }
});

export default router;
