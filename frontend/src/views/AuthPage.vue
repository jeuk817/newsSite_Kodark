<template>
  <div>
    <LogoHeader />
    <div class="authBody">
      <div class="authContent">
        <template v-if="currentPath === 'signIn'">
          <OauthSign auth="signIn" />
          <SignInBody />
        </template>
        <template v-if="currentPath === 'signUp'">
          <OauthSign auth="signUp" />
          <SignUpBody />
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import LogoHeader from '../components/headers/LogoHeader'
import SignInBody from '../components/bodies/SignInBody'
import SignUpBody from '../components/bodies/SignUpBody'
import OauthSign from '../components/units/OauthSign'
// import HelloWorld from '../components/HelloWorld'

export default {
  data: () => ({
    currentPath: ''
  }),
  components: {
    LogoHeader,
    SignInBody,
    SignUpBody,
    OauthSign
    // HelloWorld
  },
  created () {
    console.log('---------')
    const routeArr = this.$route.fullPath.split('/')
    console.log(routeArr[routeArr.length - 1])
    this.currentPath = routeArr[routeArr.length - 1]
  }
}
</script>
<style scoped>
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
