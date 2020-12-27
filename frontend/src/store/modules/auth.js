
// 참고: https://github.com/vuejs/vuex/blob/dev/examples/shopping-cart/store/modules/cart.js
import axios from 'axios'

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
  }

}

export default {
  namespaced: true,
  actions
}
