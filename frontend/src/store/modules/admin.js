
import axios from 'axios'
import Util from '../../utils/util'

const util = new Util()

const actions = {
  async createReporter({}, { email, pwd, nickName, name, local, birth, gender, image}) {
    try {
      console.log('createReporter axios')
      const formData = new FormData();
      formData.append('email', email)
      formData.append('pwd', pwd)
      formData.append('nickName', nickName)
      formData.append('name', name)
      formData.append('local', local)
      formData.append('birth', birth)
      formData.append('gender', gender)
      formData.append('image', image)
      const res = await axios.post('/admin/reporters', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      
      return { status: res.status}
    } catch(err) {
      return { status: err.response.status }
    }
  },

}

export default {
  namespaced: true,
  actions
}
