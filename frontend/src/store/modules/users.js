
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
      const links = util.parseLinks(res.headers.links)
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
  },
  async updateDetail({}, {nickName, name, local, birth, gender, image} ) {
    try {
      const formData = new FormData();
      formData.append('nickName', nickName)
      formData.append('name', name)
      formData.append('local', local)
      formData.append('birth', birth)
      formData.append('gender', gender)
      formData.append('image', image)
      const res = await axios.post('/users/detail', formData,
      {
        headers: {
          'Content-Type': 'application/multipart/form-data'
        }
      })
      return {status: res.status};
    } catch(err) {
      return {status: err.response.status}
    }
  },

  async changeEmail({}, { email, verifPwd }) {
    try {
      const res = await axios.patch('/users/email', { email, verifPwd }, {
        headers: {
          'Content-Type': 'application/json'
        }
      })

      return { status: res.status }
    } catch(err) {
      return { status: err.response.status }
    }
  },

  async changePwd({}, { pwd, verifPwd }) {
    try {
      const res = await axios.patch('/users/pwd', { pwd, verifPwd }, {
        headers: {
          'Content-Type': 'application/json'
        }
      })

      return { status: res.status }
    } catch(err) {
      return { status: err.response.status }
    }
  },

  async createComment({}, { articleId, content }) {
    try {
      const res = await axios.post(`/users/comment?articleId=${articleId}`, { content }, {
        headers: {
          'Content-Type': 'application/json'
        }
      })

      return { status: res.status }
    } catch(err) {
      return { status: err.response.status }
    }
  },

  async chooseEmotion({}, { articleId, emotion }) {
    try {
      const res = await axios.put(`/users/emotion?articleId=${articleId}&emotion=${emotion}`)
      
      const chooseResult = res.data
      return { status: res.status, chooseResult }
    } catch(err) {
      return { status: err.response.status }
    }
  },

  async chooseReputation({}, { commentId, reputation }) {
    try {
      const res = await axios.post(`/users/comment/reputation?commentId=${commentId}`, { reputation }, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      
      const chooseReputation = res.data
      return { status: res.status, chooseReputation }
    } catch(err) {
      return { status: err.response.status }
    }
  },

  async subscribeReporter({}, { id }) {
    try {
      const res = await axios.post('/users/subscription', { id }, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      
      return { status: res.status }
    } catch(err) {
      return { status: err.response.status }
    }
  },

  async createCommentReply({}, { articleId, commentId, content }) {
    try {
      const res = await axios.post(`/users/comment/reply?articleId=${articleId}&commentId=${commentId}`, { content }, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      
      return { status: res.status }
    } catch(err) {
      return { status: err.response.status }
    }
  },

  async reportComment({}, { commentId, reason }) {
    try {
      const res = await axios.post(`/users/comment/report?commentId=${commentId}`, { reason }, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      
      return { status: res.status }
    } catch(err) {
      return { status: err.response.status }
    }
  },
}

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions
}
