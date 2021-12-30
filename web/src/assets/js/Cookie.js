import Constant from './Constant'
import CookieOperations from './CookieOperations'

export default {
  cookieMethods: {
    // 返回当前登录中的用户的id
    getCurrentId () {
      return CookieOperations.cookieOperations.getCookie('id')
    },
    // 返回要查看的简历的id，应该是存储在sessionStorage中
    getTargetResumeId () {
      return '1'
    },
    // 返回当前登录中用户的身份
    async getCurrentIdentity (id) {
      var identity = 'interviewee'
      const axios = require('axios')
      let res = await axios.get(
        Constant.data().processBaseUrl + '/public/get_user', {
          params: {
            id: id
          }
        }
      )

      identity = res.data.identity

      return identity
    },
    getTargetId (identity) {
      if (identity === 'hr') {
        // 返回HR所要查看的申请者的id，存储在sessionStorage中
        return '9'
      } else {
        return this.getCurrentId()
      }
    }
  }
}
