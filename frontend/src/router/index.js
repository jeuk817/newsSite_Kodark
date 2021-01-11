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
    redirect: '/en/home'
  },
  {
    path: '/en/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/en/auth/:sign',
    component: AuthPage
  },
  {
    path: '/en/introduce',
    component: IntroducePage
  },
  {
    path: '/en/users/my-page/detail',
    component: MyPage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
