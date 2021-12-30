<template>
  <el-menu default-active="$route.path" class="el-menu-vertical-demo" mode="horizontal" router="true">
    <el-menu-item index="/">
      <i class="el-icon-menu"></i>
      <span slot="title">主页</span>
    </el-menu-item>
    <el-menu-item index="/user/process">
      <i class="el-icon-menu"></i>
      <span slot="title">流程</span>
    </el-menu-item>
    <el-menu-item index="/auth/login">
      <i class="el-icon-menu"></i>
      <span slot="title">登录</span>
    </el-menu-item>
    <el-menu-item index="/auth/register">
      <i class="el-icon-menu"></i>
      <span slot="title">注册</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style scoped>

</style>

