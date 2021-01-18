<template>
  <div>
    <MyPageHeader />
    <div class="reporterPage">
      <IntroduceSideBar :sideMenu="sideMenu" />
      <template v-if="currentRoute === 'reporters'">
        <ProfileEditBody />
      </template>
      <template v-if="currentRoute === 'new-post'">
        <ArticleWriteBody />
      </template>
    </div>
  </div>
</template>

<script>
import MyPageHeader from '../components/headers/MyPageHeader'
import IntroduceSideBar from '../components/sidebars/IntroduceSideBar'
import ProfileEditBody from '../components/bodies/ProfileEditBody'
import ArticleWriteBody from '../components/bodies/reporters/ArticleWriteBody'
import { utils } from '../components/mixins/utils'

export default {
  mixins: [utils],
  components: {
    MyPageHeader,
    IntroduceSideBar,
    ProfileEditBody,
    ArticleWriteBody
  },
  computed: {
    // side menu
    sideMenu() {
      const sideMenu = []
      sideMenu.push({ type: 'title', text: 'Reporter' })
      sideMenu.push({ type: 'sub', text: 'Profile', link: '/en/reporters' })
      sideMenu.push({ type: 'title', text: 'Article' })
      sideMenu.push({ type: 'sub', text: 'Article list', link: '/en/reporters/article' })
      sideMenu.push({ type: 'sub', text: 'Post article', link: '/en/reporters/new-post' })
      return sideMenu
    }
  }
}
</script>

<style>
.reporterPage {
  display: grid;
  grid-template-columns: 1fr 3fr;
  padding-top: 120px;
  padding-bottom: 120px;
}
/* .admin_title{
    font-weight: 300;
    font-size: 50px;
    line-height: 56px;
    margin-bottom: 24px;
}
.admin_subTitle{
    border-bottom: 1px solid #bdbdbd;
    font-weight: 700;
    font-size: 17px;
    margin-bottom: 9px;
}
.admin_content {
  max-width: 600px;
  margin-top: 35px;
  margin-bottom: 60px;
} */
</style>
