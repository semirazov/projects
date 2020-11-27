<template>
  <div class="app-layout">
    <header class="header">
      <div>
        <img src="./assets/logo.svg" alt class="img" />
      </div>
    </header>

    <NavMenu />

    <transition name="fade" mode="out-in"> <router-view /> </transition>
  </div>
</template>

<script>
import NavMenu from './components/NavMenu.vue';

export default {
  components: {
    NavMenu
  }
};
</script>

<style lang="stylus">
@import './style/main'

header
  .img
    width 64px
    height 64px
</style>
