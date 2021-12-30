<template>
  <el-container>
    <el-header height="10%">
      这里放总的导航栏
    </el-header>

    <el-main>
      <el-container>

        <el-main>
          <el-row>
            <PersonalCenterHeader></PersonalCenterHeader>
          </el-row>
          <el-row>
            <el-col
              :xs="24"
              :sm="24"
              :md="4"
              :lg="4"
              :xl="4"
            >
              <div>
                <PersonalCenterNavi></PersonalCenterNavi>
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
                <br>
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
import PersonalCenterNavi from '../resume/userResume/PersonalCenterNavi'
import PersonalCenterHeader from '../resume/userResume/PersonalCenterHeader'

export default {
  name: 'ProcessPage',
  components: {
    MainStatus,
    PersonalCenterNavi,
    PersonalCenterHeader
  }
}
</script>

<style scoped>

</style>
