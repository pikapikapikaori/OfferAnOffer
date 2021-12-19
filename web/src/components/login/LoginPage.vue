<template>
  <div class="login-container">
    <el-form :model="ruleForm" :rules="rules"
             status-icon
             ref="ruleForm"
             label-position="left"
             label-width="0px"
             class="demo-ruleForm login-page">
      <h3 class="title">用户登录</h3>
      <el-form-item prop="username">
        <el-input type="text"
                  v-model="ruleForm.username"
                  auto-complete="off"
                  placeholder="登录邮箱"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
                  v-model="ruleForm.password"
                  auto-complete="off"
                  placeholder="登录密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="logining">登录</el-button>
      </el-form-item>
      <el-link href="/auth/register" target="_blank">
        没有账号？点击这里注册
      </el-link>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'LoginPage',
  data () {
    return {
      logining: false,
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [{required: true, message: '请输入登录邮箱', trigger: 'blur'}],
        password: [{required: true, message: '请输入登录密码', trigger: 'blur'}]
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
    async login (email, password) {
      const axios = require('axios')

      let res = await axios.get(
        this.constant.data().loginBaseUrl + '/auth/login', {
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

          await this.login(this.ruleForm.username, this.ruleForm.password)

          if (this.targetInfo.code === 1) {
            this.logining = false
            this.cookieopera.cookieOperations.setCookie(this.targetInfo.id, this.targetInfo.email, this.targetInfo.password, 1)
            await this.$router.push({path: '/'})
          } else {
            this.logining = false
            this.$notify.error({
              title: '登录失败',
              message: '账号或密码错误'
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
