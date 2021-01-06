import Vue from 'vue'
import Vuex from 'vuex'
import auth from './modules/auth'
import users from './modules/users'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    account: null
  },
  getters: {
    getAccount: (state) => {
      return state.account
    }
  },
  mutations: {
    setAccount(state, account) {
      state.account = account
    },
    deleteAccount(state) {
      state.account = null
    }
  },
  actions: {
  },
  modules: {
    auth,
    users
  }
})
