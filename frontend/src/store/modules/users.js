
import axios from 'axios'
import Util from '../../utils/util'
const util = new Util()

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
  }
}

export default {
  namespaced: true,
  actions
}
