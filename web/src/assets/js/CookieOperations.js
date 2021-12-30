export default {
  cookieOperations : {
    setCookie (id, email, password, exdays, identity) {
      var d = new Date()
      d.setTime(d.getTime()+(exdays*24*60*60*1000))
      var expires = 'expires=' + d.toGMTString()
      document.cookie = 'id=' + id + '; ' + 'email=' + email + '; ' + 'password=' + password + '; ' + 'identity=' + identity + '; '  + expires
    },
    getCookie (cname) {
      var name = cname + "="
      var ca = document.cookie.split(';')
      for (var i=0; i<ca.length; i++) {
        var c = ca[i].trim()
        if (c.indexOf(name)===0) {
          return c.substring(name.length,c.length)
        }
      }
      return ''
    },
    clearCookie () {
      document.cookie = "expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
  }
}
