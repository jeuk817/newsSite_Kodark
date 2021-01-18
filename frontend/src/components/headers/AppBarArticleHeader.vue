<template>
  <v-app-bar
    absolute
    color="white"
    elevate-on-scroll
    height="45px"
    id="appBarArticleHeader"
  >
    <div class="appBarArticleHeaderContainer">
      <div>
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
        <v-icon>search</v-icon>
      </div>
      <div class="appBarArticleHeaderLogo">
        <router-link to="/en/home">
          <img src="../../assets/img/kodark.svg">
        </router-link>
      </div>
      <div class="appBarArticleHeaderRight">
        <template v-if="!account">
          <v-btn depressed to="/en/auth/signIn">Sign in</v-btn>
        </template>
        <template v-else>
          <AccountMenu v-bind="account" />
        </template>
      </div>
    </div>
  </v-app-bar>
</template>

<script>
import AccountMenu from '../units/AccountMenu'
import { mapGetters } from 'vuex'

export default {
  components: {
    AccountMenu
  },
  computed: {
    ...mapGetters({
      account: 'getAccount'
    })
  },
  created () {
    this.$store.dispatch('users/getUserData')
  }
}
</script>

<style>
#appBarArticleHeader > div {
   padding: 0;
}
</style>

<style scoped>
#appBarArticleHeader {
  position: fixed;
}

.appBarArticleHeaderContainer{
  width: 1200px;
  height: 100%;
  margin: 0 auto;
  padding: 0;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  align-content: center;
}

.appBarArticleHeaderLogo {
  display: grid;
  justify-items: center;
  align-items: center;
}

.appBarArticleHeaderLogo img {
  /* width: 300px; */
  height: 25px;
}

.appBarArticleHeaderRight {
  display: grid;
  justify-items: end;
  align-items: center;
}
</style>
