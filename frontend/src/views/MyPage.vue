<template>
  <div class="myPage">
    <div class="myPageHeader">
      <MyPageHeader/>
    </div>
    <div class="myPageContent">
      <!-- <IntroduceSideBar /> -->
      <AdminSideBar />
      <!-- <ProfileEditBody /> -->
      <!-- MyPageBody -->
      <SubscribeListBody />
      <!-- <Content /> -->
    </div>
    <Footer />
  </div>
</template>

<script>
import MyPageHeader from '../components/headers/MyPageHeader'
// import IntroduceSideBar from '../components/sidebars/IntroduceSideBar'
import AdminSideBar from '../components/sidebars/AdminSideBar'
import MyPageBody from '../components/bodies/MyPageBody'
import ProfileEditBody from '../components/bodies/ProfileEditBody'
import SubscribeListBody from '../components/bodies/SubscribeListBody'
import Footer from '../components/footer/Footer'
export default {
  components: {
    MyPageHeader,
    // IntroduceSideBar,
    AdminSideBar,
    // MyPageBody,
    // ProfileEditBody,
    SubscribeListBody,
    Footer
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
