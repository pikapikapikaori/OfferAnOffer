<template>
  <el-container>
    <el-header>
      <NavBar></NavBar>
    </el-header>

    <el-main>
      <el-container>
        <el-header>
          <h1>
            这里放个人中心的header
          </h1>
        </el-header>

        <el-main>
          <el-row>
            <el-col
              :xs="24"
              :sm="24"
              :md="4"
              :lg="4"
              :xl="4"
            >
              <div>
                这里放个人中心的左侧导航栏
              </div>
            </el-col>
            <el-col
              :xs="24"
              :sm="24"
              :md="16"
              :lg="16"
              :xl="16">
              <div>
                <MainStatus></MainStatus>
              </div>
            </el-col>
            <el-col
              :xs="24"
              :sm="24"
              :md="4"
              :lg="4"
              :xl="4">
              <div>
                这里放申请的职位信息
              </div>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-main>

    <el-footer>

    </el-footer>
  </el-container>
</template>

<script>
import MainStatus from './pageComponents/MainStatus'
import NavBar from '../common/NavBar'

export default {
  name: 'ProcessPage',
  components: {
    MainStatus,
    NavBar
  }
}
</script>

<style scoped>

</style>
