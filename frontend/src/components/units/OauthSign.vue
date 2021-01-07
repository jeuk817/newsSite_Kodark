<template>
  <div>
    <div class="authGuide">
      {{authGuideMsg}}
    </div>
    <div class="oauthComponent">
      <v-btn class="oauthBtn text-capitalize" outlined color="black" width="100%" height="45px"
        @click="googleOuath"
      >
        <v-icon>account_circle</v-icon>Continue with Google
      </v-btn>
      <v-btn class="oauthBtn text-capitalize" outlined color="black" width="100%" height="45px"
        @click="kakaoOuath"
      >
        <v-icon>account_circle</v-icon>Continue with Kakao
      </v-btn>
      <div class="authDivider">
        <v-divider />
        <div>
          Or use your email
        </div>
        <v-divider />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['auth'],
  computed: {
    authGuideMsg () {
      return this.auth === 'signIn' ? 'Sign in to your account' : 'Create your free account'
    }
  },
  methods: {
    async googleOuath() {
      console.log('googleOuath')
      const { status, links } = await this.$store.dispatch('auth/google')
      console.log('----------')
      console.log(status)
      if(status === 302) {
        window.location.href = links.next
      }
    },
    kakaoOuath() {
      console.log('kakaoOuath')
    }
  }
}
</script>
<style scoped>
.authGuide{
  display: grid;
  justify-items: center;
  font-size: 22px;
  margin: 8px;
}

.oauthBtn {
  margin-top: 8px;
}

.oauthComponent {
  margin-top: 8px;
}

.authDivider {
  margin-top: 14px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  align-items: center;
  text-align: center;
  font-size: 14px;
}
</style>
