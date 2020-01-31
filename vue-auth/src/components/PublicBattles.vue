<template>
  <div>
    <app-nav></app-nav>
    <h3 class="text-center">Daily Startup Battles</h3>
    <hr />
    <transition name="fade" mode="out-in">
      <spinner v-if="!publicBattles" />
      <ul v-else>
        <li class="col-sm-4" v-for="battle in publicBattles" :key="battle.id">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">{{ battle.name }}</h3>
            </div>
            <div class="panel-body">
              <p>
                <span class="badge alert-info">Sponsor:</span>
                {{ battle.sponsor }}
              </p>
              <p>
                <span class="badge alert-danger">SeedFund:</span>
                <strong>${{ battle.seedFund }}</strong>
              </p>
            </div>
          </div>
        </li>
      </ul>
    </transition>
    <div class="col-sm-12">
      <div class="jumbotron text-center" v-if="isLoggedIn()">
        <h2>View Private Startup Battles</h2>
        <router-link class="btn btn-lg btn-success" to="/private-battles">Private Startup Battles</router-link>
      </div>
      <div class="jumbotron text-center" v-else>
        <h2>Get Access to Private Startup Battles by Logging In</h2>
      </div>
    </div>
  </div>
</template>

<script>
import AppNav from "./AppNav";
import spinner from "./TheSpinner";
import { isLoggedIn } from "@/utils/auth";
import { getPublicBattles } from "@/api/battles-api";
export default {
  name: "publicBattles",
  components: {
    AppNav,
    spinner
  },
  data() {
    return {
      publicBattles: ""
    };
  },
  methods: {
    isLoggedIn() {
      return isLoggedIn();
    },
    getPublicBattles() {
      getPublicBattles().then(battles => {
        this.publicBattles = battles;
      });
    }
  },
  mounted() {
    this.getPublicBattles();
  }
};
</script>

<style lang="less" scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
</style>