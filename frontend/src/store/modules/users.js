
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
      console.log(res.headers.links)
      const links = util.parseLinks(res.headers.links)
      console.log(links)
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
  },
  async getUserDetail() {
    try {
      const res = await axios.get('/users/my-page/detail',  {
        headers: {
          'Content-Type': 'application/json'
          }
        })
      const data = res.data
      const links = util.parseLinks(res.headers.links)
      return {status: res.status, userDetail: data, links};
    } catch(err) {
      return {status: err.response.status}
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
