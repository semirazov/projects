<template>
  <nav class="menu">
    <router-link :to="{ name: 'home' }" exact>Home</router-link>
    <router-link :to="{ name: 'faq' }">FAQ</router-link>
    <div class="spacer"></div>

    <template v-if="$state.user">
      <router-link :to="{ name: 'tickets' }">Tickets</router-link>
      <a>{{ $state.user.username }}</a>
      <a @click="logout">Logout</a>
    </template>
    <router-link v-else :to="{ name: 'login' }">Login</router-link>
  </nav>
</template>

<script>
export default {
  methods: {
    async logout() {
      const result = await this.$fetch('logout');
      if (result.status === 'ok') {
        this.$state.user = null;
        await this.$router.replace({ name: 'home' });
      }
    }
  }
};
</script>

<style lang="stylus" scoped>
@import '../style/imports';

nav
  a
    font-size 24px

.router-link-active
  border-bottom-color $primary-color
</style>
