import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import ProcessPage from '@/components/process/ProcessPage'
import LoginPage from '@/components/login/LoginPage'
import RegisterPage from '@/components/login/RegisterPage'
import UploadResumePage from '@/components/resume/userResume/UploadResumePage'
import PersonalCenter from "@/components/resume/userResume/PersonalCenter";
import HRPersonalCenter from "@/components/resume/hrResume/HRPersonalCenter";
import HRClickinPersonalCenter from "@/components/resume/hrResume/HRClickinPersonalCenter";


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
    },
    {
      path: '/user/upload-resume',
      name:'UploadResumePage',
      component: UploadResumePage
    },
    {
      path: '/user/personalcenter',
      name: 'PersonalCenter',
      component: PersonalCenter
    },
    {
      path: '/user/hrpersonalcenter',
      name: 'HRPersonalCenter',
      component: HRPersonalCenter
    },
    {
      path: '/user/hrclickinpersonalcenter',
      name: 'HRClickinPersonalCenter',
      component: HRClickinPersonalCenter
    }
  ]
})
