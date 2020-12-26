import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import Util from '../utils/util'

const util = new Util()

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
    async getVerifCode({}, email) {
      try {
        const res = await axios.post('/auth', {email}, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        return res.status
      } catch(err) {
        return err.response.status
      }
    },

    async sendVerifCode({}, { email, authString }) {
      try {
        const res = await axios.patch('/auth/verify', { email, authString }, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        return res.status
      } catch(err) {
        return err.response.status
      }
    },

    async createAccount({}, {email, pwd}) {
      try {
        const res = await axios.post('/users/sign-up', { email, pwd }, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        const linksStr = res.headers.links
        const links = util.parseLinks(linksStr)
        return { status: res.status, links }
      } catch(err) {
        return { status: err.response.status}
      }
    }

  },
  modules: {
  }
})
