import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AuthPage from '../views/AuthPage'
import IntroducePage from '../views/IntroducePage'
import MyPage from '../views/MyPage'


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
    path: '/ko/users/my-page/detail',
    component: MyPage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
