import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AuthPage from '../views/AuthPage'
import IntroducePage from '../views/IntroducePage'
import MyPage from '../views/MyPage'
import AdminPage from '../views/AdminPage'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/ko/home'
  },
  {
    path: '/ko/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/ko/auth/:sign',
    component: AuthPage
  },
  {
    path: '/ko/introduce',
    component: IntroducePage
  },
  {
    path: '/ko/users/my-page',
    component: MyPage
  },
  {
    path: '/ko/admin/admin-page',
    component: AdminPage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
