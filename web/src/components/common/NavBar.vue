<template>
  <el-menu default-active="$route.path" class="el-menu-vertical-demo" mode="horizontal" router="true">
    <el-menu-item index="/user/personalcenter">
      <i class="el-icon-user"></i>
      <span slot="title">个人中心</span>
    </el-menu-item>
    <el-menu-item index="/">
      主页
    </el-menu-item>
    <el-menu-item index="/user/process">
      流程
    </el-menu-item>
    <el-menu-item index="/user/process">
      流程
    </el-menu-item>
    <el-menu-item @click="logout">
      点击这里重新登录
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'Home',
  methods: {
    logout () {
      this.cookieopera.cookieOperations.clearCookie()
      sessionStorage.clear()
      localStorage.clear()
      this.$router.push('/auth/login')
    }
  }
}
</script>

<style scoped>

</style>

