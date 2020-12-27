
import axios from 'axios'
import Util from '../../utils/util'
const util = new Util()

const state = () => ({
})

const getters = {
}

const mutations = {
}

const actions = {
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
  },

  async getUserData({ commit }) {
    try {
      const res = await axios.get('/users')
      const data = res.data
      const links = util.parseLinks(res.headers.links)
      commit('setAccount', { userData: data, links }, { root: true })
      return res.status
    } catch(err) {
      return err.response.status
    }
  }
}

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions
}
