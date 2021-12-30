<template>
  <div class="login-container">
    <el-form :model="ruleForm" :rules="rules"
             status-icon
             ref="ruleForm"
             label-position="left"
             label-width="0px"
             class="demo-ruleForm login-page">
      <h3 class="title">用户注册</h3>
      <el-form-item prop="username">
        <el-input type="text"
                  v-model="ruleForm.username"
                  auto-complete="off"
                  placeholder="请输入邮箱"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
                  v-model="ruleForm.password"
                  auto-complete="off"
                  placeholder="请输入密码"
        ></el-input>
      </el-form-item>
      <el-form-item prop="repassword">
        <el-input type="password"
                  v-model="ruleForm.repassword"
                  auto-complete="off"
                  placeholder="请再次输入密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="logining">注册</el-button>
      </el-form-item>
      <el-link href="/auth/login" target="_blank">
        已有账号？点击这里登录
      </el-link>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'RegisterPage',
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      logining: false,
      ruleForm: {
        username: '',
        password: '',
        repassword: ''
      },
      rules: {
        username: [{required: true, message: '请输入登录邮箱', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        repassword: [{required: true, validator: validatePass, trigger: 'blur'}]
      },
      targetInfo: {
        code: 0,
        id: '',
        email: '',
        password: '',
        identity: ''
      }
    }
  },
  methods: {
    async register (email, password) {
      const axios = require('axios')

      let res = await axios.post(
        this.constant.data().loginBaseUrl + '/auth/register', null, {
          params: {
            email: email,
            password: password
          }
        }
      )

      this.targetInfo = res.data
    },
    async handleSubmit (event) {
      this.$refs.ruleForm.validate(async (valid) => {
        if (valid) {
          this.logining = true

          await this.register(this.ruleForm.username, this.ruleForm.password)

          if (this.targetInfo.code === 1) {
            this.logining = false
            this.cookieopera.cookieOperations.setCookie(this.targetInfo.id, this.targetInfo.email, this.targetInfo.password, 1, this.targetInfo.identity)
            await this.$router.push({path: '/user/personalcenter'})
          } else {
            this.logining = false
            this.$notify.error({
              title: '注册失败'
            })
          }

        } else {
          console.log('提交失败')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
</style>

