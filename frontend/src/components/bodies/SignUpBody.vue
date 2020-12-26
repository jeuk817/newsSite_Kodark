<template>
  <div>
    <div class="verifyEmailGuide">
      <p class="text-h6">
        Verify your email address
      </p>
      <p>
        To make sure this email is yours, Kodark will send you a text message with a 6-digit verification code.
      </p>
    </div>
    <div class="verifyEmailForm">
      <v-text-field
        ref="emailAddress"
        label="Email Address"
        outlined
        color="black"
        height="45px"
        dense=true
        v-model="email"
        :rules="emailRules"
        required
      ></v-text-field>
      <v-btn class="text-capitalize white--text" height="45px" depressed color="black" @click="sendCode">
        Send
      </v-btn>
    </div>
    <div class="verifyEmailForm">
      <v-text-field
        label="Enter verification code"
        hint="Verification code was sent.(Valid time 30 minutes)"
        outlined
        height="45px"
        dense=true
        :persistent-hint="didSend"
        :disabled="!didSend"
      ></v-text-field>
      <v-btn class="text-capitalize white--text" height="45px" depressed color="black" :disabled="!didSend">
        Verify
      </v-btn>
    </div>
    <v-text-field
      ref="password"
      label="Password"
      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show1 ? 'text' : 'password'"
      @click:append="show1 = !show1"
      outlined
      color="black"
      height="45px"
      dense=true
      v-model="password"
      :rules="passwordRules"
      required
    ></v-text-field>
    <v-text-field
      ref="confirmPassword"
      label="Confirm password"
      :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show2 ? 'text' : 'password'"
      @click:append="show2 = !show2"
      outlined
      color="black"
      height="45px"
      dense=true
      v-model="confirmPassword"
      :rules="[confirmRule]"
      :error-messages="errorMessages"
      required
    ></v-text-field>
    <v-btn
      class="text-capitalize white--text font-weight-black"
      height="45px" width="100%" depressed color="black"
      @click="createAccount"
    >
      Create Account
    </v-btn>
    <div class="createAccountBtn">
      <div>
        <span>
          Already have a KoDark account?
        </span>
        <v-btn class="font-weight-black text-capitalize text-decoration-underline" text height="24px" width="70px" depressed to="/ko/auth/signIn">
          Sign in
        </v-btn>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    didSend: false,
    email: '',
    emailRules: [
      v => !!v || 'Email Address is required',
      v => /.+@.+/.test(v) || 'Email Address must be valid'
    ],
    password: '',
    passwordRules: [
      v => !!v || 'Password is required',
      v => (v.length >= 8 && /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test(v)) || 'Use 8 or more characters with a mix of letters, numbers & symbols'
    ],
    show1: false,
    confirmPassword: '',
    show2: false,
    errorMessages: ''
  }),
  methods: {
    confirmRule () {
      this.errorMessages = this.password === this.confirmPassword ? '' : "Those passwords didn't match"
      return this.password === this.confirmPassword
    },
    sendCode () {
      if (!this.$refs.emailAddress.validate(true)) return
      console.log('ddd')
    },
    createAccount () {
      console.log('aaaaaa')
      this.$store.dispatch('test', 'hello')
      console.log(this.$refs.emailAddress.validate())
      console.log(this.$refs.password.validate())
      console.log(this.$refs.confirmPassword.validate())
    }
  }
}
</script>

<style scoped>
.verifyEmailGuide {
  padding-top: 16px;
}

.verifyEmailForm {
  display: grid;
  grid-template-columns: 6fr 1fr;
  column-gap: 8px;
}

.createAccountBtn {
  padding: 12px 0;
  display: grid;
  justify-items: center;
  font-size: 14px;
}
</style>
