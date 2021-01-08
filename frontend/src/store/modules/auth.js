
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
      console.log(res.headers.links)
      const links = util.parseLinks(res.headers.links)
      console.log(links)
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
  },

  async google({ commit }) {
    try {
      const xsrf = commit('getXsrf', null, { root: true })
      const res = await axios.post('/auth/google', null, {
        headers: {
          'X-XSRF-TOKEN': xsrf
        }
      })

      const links = util.parseLinks(res.headers.links)
      return { status: res.status, links }
    } catch(err) {
      const links = util.parseLinks(err.response.headers.links)
      return { status: err.response.status, links}
    }
  },

  async kakao({ commit }) {
    try {
      const xsrf = commit('getXsrf', null, { root: true })
      const res = await axios.post('/auth/kakao', null, {
        headers: {
          'X-XSRF-TOKEN': xsrf
        }
      })

      const links = util.parseLinks(res.headers.links)
      return { status: res.status, links }
    } catch(err) {
      const links = util.parseLinks(err.response.headers.links)
      return { status: err.response.status, links}
    }
  }

}

export default {
  namespaced: true,
  actions
}
