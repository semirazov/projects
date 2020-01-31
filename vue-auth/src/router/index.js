import Vue from "vue";
import Router from "vue-router";
import PublicBattles from "@/components/PublicBattles";
import PrivateBattles from "@/components/PrivateBattles";
import Callback from '@/components/callback';
import {requireAuth} from '@/utils/auth'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: "/",
      name: "public-battles",
      component: PublicBattles
    },
    {
      path: "/private-battles",
      name: "private-battles",
      beforeEnter: requireAuth,
      component: PrivateBattles
    },
    {
      path: '/callback',
      component: Callback,
    },
  ]
});
