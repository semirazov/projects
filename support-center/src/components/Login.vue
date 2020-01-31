<template>
  <main class="login">
    <h1>Please login to continue</h1>
    <SmartForm class="form" :title="title" :operation="operation" :valid="valid">
      <FormInput v-model="username" name="username" placeholder="Username"/>
      <FormInput v-model="password" name="password" placeholder="Password"/>

      <template v-if="mode=== 'signup'">
        <FormInput v-model="password2" name="verify-password" placeholder="Retype password"/>
        <FormInput v-model="email" name="email" placeholder="E-mail"/>
      </template>

      <div slot="actions">
        <template v-if="mode === 'login'">
          <button type="button" class="secondary" @click="mode = 'signup'">Sign up</button>
          <button type="submit" :disabled="!valid">Login</button>
        </template>

        <template v-else-if="mode === 'signup'">
          <button type="button" class="secondary" @click="mode = 'login'">Back to login</button>
          <button type="submit" :disabled="!valid">Create account</button>
        </template>
      </div>
    </SmartForm>


  </main>
</template>

<script>
  export default {
    data() {
      return {
        mode: 'signup',
        username: '',
        password: '',
        password2: '',
        email: ''
      };
    },

    computed: {
      title() {
        switch (this.mode) {
          case 'login':
            return 'Login';
          case 'signup':
            return 'Create a new account';
        }
      },

      retypePasswordError() {
        return this.password2 && this.password !== this.password2;
      },

      signupValid() {
        return this.password2 && this.email && !this.retypePasswordError;
      },
      valid() {
        return (
          this.username &&
          this.password &&
          (this.mode !== 'signup' || this.signupValid)
        );
      }
    },

    methods: {
      async operation() {
        await this[this.mode]();
      },
      async login() {
        this.$state.user = await this.$fetch('login', {
          method: 'POST',
          body: JSON.stringify({
            username: this.username,
            password: this.password
          })
        });
        this.$router.push({name: 'home'});
      },
      async signup() {
        await this.$fetch('signup', {
          method: 'POST',
          body: JSON.stringify({
            username: this.username,
            password: this.password,
            email: this.email
          })
        });
        this.mode = 'login';
      }
    }
  };
</script>

<style lang="stylus" scoped>
  .form
    >>> .content
      max-width 400px
</style>
