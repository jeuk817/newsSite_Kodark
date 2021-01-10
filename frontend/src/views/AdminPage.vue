<template>
  <div>
    <MyPageHeader />
    <div class="adminPage">
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
      sideMenu.push({ type: 'title', text: 'Admin' })
      sideMenu.push({ type: 'sub', text: 'Statistics', link: '/en/admin/admin-page' })
      sideMenu.push({ type: 'sub', text: 'Waiting articles', link: '/en/admin/admin-page/article/new' })
      sideMenu.push({ type: 'title', text: 'Users' })
      sideMenu.push({ type: 'sub', text: 'User list', link: '/en/admin/admin-page/users' })
      sideMenu.push({ type: 'title', text: 'Reporter' })
      sideMenu.push({ type: 'sub', text: 'Reporter list', link: '/en/admin/admin-page/reporters' })
      sideMenu.push({ type: 'sub', text: 'Create reporter', link: '/en/admin/admin-page/reporters/new' })
      sideMenu.push({ type: 'title', text: 'Report' })
      sideMenu.push({ type: 'sub', text: 'Reported articles', link: '/en/admin/admin-page/report/article' })
      sideMenu.push({ type: 'sub', text: 'Reported comments', link: '/en/admin/admin-page/report/comment' })
      sideMenu.push({ type: 'title', text: 'Question' })
      sideMenu.push({ type: 'sub', text: 'Question list', link: '/en/admin/admin-page/question' })
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
