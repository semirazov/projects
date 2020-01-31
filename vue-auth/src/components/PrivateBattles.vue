<template>
  <div>
    <app-nav></app-nav>
    <h3 class="text-center">Secret Startup Battles</h3>
    <hr />
    <transition name="fade" mode="out-in">
      <spinner v-if="!privateBattles" />
      <ul v-else>
        <li class="col-sm-4" v-for="battle in privateBattles" :key="battle.id">
          <div class="panel panel-danger">
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
      <div class="jumbotron text-center">
        <h2>View Public Startup Battles</h2>
        <router-link class="btn btn-lg btn-success" to="/">Public Startup Battles</router-link>
      </div>
    </div>
  </div>
</template>
<script>
import AppNav from "./AppNav";
import { isLoggedIn } from "@/utils/auth";
import { getPrivateBattles } from "@/api/battles-api";
import spinner from "./TheSpinner";
export default {
  name: "privateBattles",
  components: {
    AppNav,
    spinner
  },
  data() {
    return {
      privateBattles: ""
    };
  },
  methods: {
    isLoggedIn() {
      return isLoggedIn();
    },
    getPrivateBattles() {
      getPrivateBattles().then(battles => {
        this.privateBattles = battles;
      });
    }
  },
  mounted() {
    this.getPrivateBattles();
  }
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
</style>