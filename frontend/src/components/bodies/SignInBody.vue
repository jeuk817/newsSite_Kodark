<template>
  <div class="singComponent">
    <v-text-field
      label="Email Address"
      outlined
      color="black"
      height="45px"
      dense=true
      v-model="email"
      :rules="emailRules"
      required
    ></v-text-field>
    <v-text-field
      label="Password"
      hint="At least 8 characters"
      :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show ? 'text' : 'password'"
      @click:append="show = !show"
      outlined
      color="black"
      height="45px"
      dense=true
      v-model="password"
      :rules="passwordRules"
      required
    ></v-text-field>
    <div class="findPwd">
      <v-btn text depressed x-small light="true">Forgot your password?</v-btn>
    </div>
    <div class="signInBtn">
      <v-btn
        depressed
        large
        dark
        color="black"
        width="100%"
      >
      Sign In
      </v-btn>
    </div>
  </div>
</template>

<script>

export default {
  data: () => ({
    email: '',
    emailRules: [
      v => !!v || 'Email Address is required.',
      v => /.+@.+/.test(v) || 'Email Address must be valid.'
    ],
    password: '',
    passwordRules: [
      v => !!v || 'Password is required.',
      v => v.length >= 8 || 'Min 8 characters.'
    ],
    show: false
  })
}
</script>
<style scoped>
.singComponent {
  padding-top: 24px;
}
.findPwd {
  display: grid;
  justify-items: end;
}
.signInBtn {
  margin-top: 8px;
}
</style>
