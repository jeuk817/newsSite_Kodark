<template>
  <div class="myPage">
    <div class="myPageHeader">
      <MyPageHeader/>
    </div>
    <div class="myPageContent">
      <IntroduceSideBar :sideMenu="sideMenu" />
      <MyPageBody />
    </div>
  </div>
</template>

<script>
import MyPageHeader from '../components/headers/MyPageHeader'
import IntroduceSideBar from '../components/sidebars/IntroduceSideBar'
import AdminSideBar from '../components/sidebars/AdminSideBar'
import MyPageBody from '../components/bodies/MyPageBody'
import ProfileEditBody from '../components/bodies/ProfileEditBody'
import SubscribeListBody from '../components/bodies/SubscribeListBody'

export default {
  data: () => ({
    // sideMenu: []
  }),
  components: {
    MyPageHeader,
    IntroduceSideBar,
    MyPageBody,
    ProfileEditBody,
    SubscribeListBody,
  },
  computed: {
    sideMenu() {
      const sideMenu = []
      sideMenu.push({ type: 'sub', text: 'Account', link: '/en/users/my-page' })
      sideMenu.push({ type: 'sub', text: 'Profile', link: '/en/users/my-page/profile' })
      sideMenu.push({ type: 'sub', text: 'Subscription', link: '/en/users/my-page/subscription' })
      return sideMenu
    }
  }
}
</script>

<style scoped>
.myPageContent {
  display: grid;
  grid-template-columns: 1fr 3fr;
  padding-top: 120px;
  padding-bottom: 120px;
}
</style>
