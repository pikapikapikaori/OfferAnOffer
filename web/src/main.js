// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/display.css'

import Cookie from './assets/js/Cookie'
import Constant from './assets/js/Constant'
import CookieOperations from './assets/js/CookieOperations'

Vue.config.productionTip = false

Vue.use(ElementUI)

Vue.prototype.cookieutils = Cookie
Vue.prototype.constant = Constant
Vue.prototype.cookieopera = CookieOperations

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
