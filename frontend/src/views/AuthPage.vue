<template>
  <div class="authPage">
    <LogoHeader />
    <div class="authBody">
      <div class="authContent">
        <template v-if="currentRoute === 'signIn'">
          <OauthSign auth="signIn" />
          <SignInBody />
        </template>
        <template v-if="currentRoute === 'signUp'">
          <OauthSign auth="signUp" />
          <SignUpBody />
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import LogoHeader from '../components/headers/LogoHeader'
import SignInBody from '../components/bodies/auth/SignInBody'
import SignUpBody from '../components/bodies/auth/SignUpBody'
import OauthSign from '../components/bodies/auth/OauthSign'
import { utils } from '../components/mixins/utils'

export default {
  mixins: [utils],
  data: () => ({
    currentPath: ''
  }),
  components: {
    LogoHeader,
    SignInBody,
    SignUpBody,
    OauthSign
  }
}
</script>
<style scoped>
.authPage {
  height: 100%;
}

.authBody {
 padding-top: 64px;
}

.authContent {
  /* border: 1px solid red; */
  max-width: 450px;
  margin: 40px auto 0;
  padding: 0 24px;
}
</style>
