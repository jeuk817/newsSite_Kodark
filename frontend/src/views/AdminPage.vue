<template>
  <div>
    <MyPageHeader />
    <div class="adminPage">
      <MyPageHeader/>
      <IntroduceSideBar :sideMenu="sideMenu" />
    </div>
  </div>
</template>

<script>
import MyPageHeader from '../components/headers/MyPageHeader'
import IntroduceSideBar from '../components/sidebars/IntroduceSideBar'

export default {
  components: {
    MyPageHeader,
    IntroduceSideBar
  },
  computed: {
    sideMenu() {
      const sideMenu = []
      sideMenu.push({ type: 'sub', text: 'Account', link: '/ko/users/my-page' })
      sideMenu.push({ type: 'sub', text: 'Profile', link: '/ko/users/my-page/profile' })
      sideMenu.push({ type: 'sub', text: 'Subscription', link: '/ko/users/my-page/subscription' })
      return sideMenu
    }
  }
}
</script>

<style scoped>
.adminPage {
  display: grid;
  grid-template-columns: 1fr 3fr;
  padding-top: 120px;
  padding-bottom: 120px;
}
</style>
