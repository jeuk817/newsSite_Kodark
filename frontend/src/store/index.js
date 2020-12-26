import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
    test({}, email) {
      console.log('test')
      console.log(email)
    }
  },
  modules: {
  }
})
