<template>
  <nav class="menu">
    <router-link :to="{name:'home'}" exact>Home</router-link>
    <router-link :to="{name:'faq'}">FAQ</router-link>
    <div class="spacer"></div>

      <a>{{this.$state.state.user.username}}</a>
      <span @click="logout">Logout</span>

    <router-link  :to="{name:'login'}">Login</router-link>
  </nav>
</template>

<script>
  export default {
    mounted() {
      console.log(this.$state.state.user)

    },

    methods: {
      async logout() {
        const result = await this.$fetch('logout');
        if (result.status === 'ok') {
          this.$state.user = null
        }
      }
    },

    computed: {
      username() {
        return this.$state.user ? this.$state.state.user : 'nihuya';
      }
    },
  }
</script>

<style lang="stylus" scoped>
  @import '../style/imports';

  nav
    a
      font-size 24px

  .router-link-active
    border-bottom-color $primary-color
</style>
