
// 참고: https://github.com/vuejs/vuex/blob/dev/examples/shopping-cart/store/modules/cart.js
import axios from 'axios'
import Util from '../../utils/util'

const util = new Util()

const actions = {
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

  async signIn({}, { email, pwd }) {
    try {
      const res = await axios.post('/auth/sign-in', { email, pwd }, {
        headers: {
          'Content-Type': 'application/json'
        }
      })

      const links = util.parseLinks(res.headers.links)
      return { status: res.status, links }
    } catch(err) {
      return { status: err.response.status }
    }
  },

  async signOut({ commit }) {
    try {
      const res = await axios.delete('/auth/sign-out')
      commit('deleteAccount', null, { root: true })
      return res.status
    } catch(err) {
      return err.response.status
    }
  }

}

export default {
  namespaced: true,
  actions
}
