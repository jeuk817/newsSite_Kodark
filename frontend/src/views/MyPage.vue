<template>
  <div class="myPage">
    <div class="myPageHeader">
      <MyPageHeader/>
    </div>
    <div class="myPageContent">
      <IntroduceSideBar />
      <!-- <AdminSideBar /> -->
      <!-- <ProfileEditBody /> -->
      <!-- <MyPageBody /> -->
      <!-- <ArticleWriteBody /> -->
      <SubscribeListBody />
      <!-- <Content /> -->
      <!-- <AdminWaitArticleList /> -->
    </div>
    <Footer />
  </div>
</template>

<script>
import MyPageHeader from '../components/headers/MyPageHeader'
import IntroduceSideBar from '../components/sidebars/IntroduceSideBar'
import AdminSideBar from '../components/sidebars/AdminSideBar'
import MyPageBody from '../components/bodies/MyPageBody'
import ProfileEditBody from '../components/bodies/ProfileEditBody'
import SubscribeListBody from '../components/bodies/SubscribeListBody'
import ArticleWriteBody from '../components/bodies/ArticleWriteBody'
import AdminWaitArticleList from '../components/bodies/AdminWaitArticleList'
import Footer from '../components/footer/Footer'
export default {
  components: {
    MyPageHeader,
    IntroduceSideBar,
    ArticleWriteBody,
    AdminSideBar,
    MyPageBody,
    ProfileEditBody,
    SubscribeListBody,
    AdminWaitArticleList,
    Footer
  },
  computed: {
    
    // 현재 라우트 반환
    currentRoute () {
      const routeArr = this.$route.fullPath.split('/')
      console.log('efeefef')
      console.log(routeArr)
      return routeArr[routeArr.length - 1]
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
