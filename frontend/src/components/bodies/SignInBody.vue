<template>
  <div class="singComponent">
    <v-text-field
      ref="email"
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
      ref="password"
      label="Password"
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
      <v-btn class="text-capitalize" text depressed x-small>
        Forgot your password?
      </v-btn>
    </div>
    <div class="signInBtn">
      <v-btn
        class="white--text"
        depressed
        large
        color="black"
        width="100%"
        @click="signIn"
        :loading="signningIn"
      >
      Sign In
      </v-btn>
    </div>
    <div class="createAccountBtn">
      <div>
        <span>
          Dont't have a KoDark account?
        </span>
        <v-btn class="font-weight-black text-capitalize text-decoration-underline" text height="24px" width="100px" depressed to="/ko/auth/signUp">
          Create one
        </v-btn>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data: () => ({
    email: '',
    emailRules: [
      v => !!v || 'Email Address is required',
      v => /.+@.+/.test(v) || 'Email Address must be valid'
    ],
    password: '',
    passwordRules: [
      v => !!v || 'Password is required',
      v => v.length >= 8 || 'At least 8 characters'
    ],
    show: false,
    signningIn: false
  }),
  methods: {
    signIn () {
      if(!this.$refs.email.validate(true) && !this.$refs.password.validate(true)) return
      console.log('signningIn')
      this.signningIn = true
    }
  }
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

.createAccountBtn {
  padding-top: 12px;
  display: grid;
  justify-items: center;
  font-size: 14px;
}

</style>
