<template>
  <div class="row">
    <component
      :is="element"
      class="input"
      :class="inputClass"
      :name="name"
      :type="type"
      :value.prop="text"
      :placeholder="placeholder"
      v-bind="$attrs"
      @input="update"
    />
  </div>
</template>

<script>
export default {
  props: {
    name: {
      type: String
    },
    type: {
      type: String,
      default: 'text'
    },
    text: {
      required: true
    },
    placeholder: {
      type: String
    },
    invalid: {
      type: Boolean,
      default: false
    }
  },

  model: {
    prop: 'text',
    event: 'update'
  },

  computed: {
    inputClass() {
      return {
        invalid: this.invalid
      };
    },
    element() {
      return this.type === 'textarea' ? 'textarea' : 'input';
    }
  },
  methods: {
    update(event) {
      this.$emit('update', event.currentTarget.value);
    }
  }
};
</script>
