import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AuthPage from '../views/AuthPage'

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
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
