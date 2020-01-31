<template>
  <form @submit.prevent="submit">
    <section class="content">
      <h2 class="title"></h2>
      <slot />

      <div class="actions">
        <slot name="actions" />
      </div>

      <div class="error" v-if="error">{{error}}</div>
    </section>

    <transition name="fade">
      <Loading v-if="busy" class="overlay" />
    </transition>
  </form>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      required: true
    },
    operation: {
      type: Function,
      required: true
    },
    valid: {
      type: Boolean,
      required: true
    }
  },

  data() {
    return {
      busy: false,
      error: null
    };
  },

  methods: {
    async submit() {
      this.error = null;
      this.busy = true;
      try {
        await this.operation();
      } catch (error) {
        this.error = error;
      }

      this.busy = false;
    }
  }
};
</script>