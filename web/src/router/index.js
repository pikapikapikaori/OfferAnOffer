import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import ProcessPage from '@/components/process/ProcessPage'
import LoginPage from '@/components/login/LoginPage'
import RegisterPage from '@/components/login/RegisterPage'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/auth/login',
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/auth/register',
      name: 'RegisterPage',
      component: RegisterPage
    },
    {
      path: '/user/process',
      name: 'ProcessPage',
      component: ProcessPage
    }
  ]
})
