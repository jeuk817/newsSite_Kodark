
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
  async uploadImage({}, { image }) {
    try {
      const formData = new FormData();
      formData.append('image', image)
      const res = await axios.post('/reporters/image', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      
      return { status: res.status, imageUrl: res.data.imageUrl }
    } catch(err) {
      return { status: err.response.status }
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
