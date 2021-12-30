<template>
  <el-container>
    <el-header height="10%">
      <NavBar></NavBar>
    </el-header>

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
          <PersonalCenterNavi></PersonalCenterNavi>
        </el-col>
        <el-col
          :xs="24"
          :sm="24"
          :md="3"
          :lg="3"
          :xl="3">
          <br>
        </el-col>
        <el-col
          :xs="24"
          :sm="24"
          :md="10"
          :lg="10"
          :xl="10">
          <div>
            <el-table
              :data="tableData"
              style="width: 100%">
              <el-table-column
                label="简历 Id"
                prop="resumeId">
              </el-table-column>
              <el-table-column
                label="简历"
                prop="resumeName">
              </el-table-column>
              <el-table-column
                label="职位"
                prop="jobName">
              </el-table-column>
              <el-table-column
                align="right">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="handleEdit(scope.row.resumeId)">查看流程</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-col>
        <el-col
          :xs="24"
          :sm="24"
          :md="7"
          :lg="7"
          :xl="7">
          <br>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import Constant from "../../../assets/js/Constant";
import PersonalCenterNavi from "./PersonalCenterNavi";
import PersonalCenterHeader from "./PersonalCenterHeader";
import NavBar from "../../common/NavBar";

export default {
  name: 'PersonalCenter',
  data() {
    return {
      tableData: [],
      identity: ''
    }
  },
  methods: {
    handleEdit(resumeId) {
      sessionStorage.setItem("targetResumeId", resumeId)
      sessionStorage.setItem("targetId", this.cookieutils.cookieMethods.getCurrentId())

      this.$router.push('/user/process')
    }
  },
  created: async function() {

    this.identity = await this.cookieutils.cookieMethods.getCurrentIdentity(this.cookieutils.cookieMethods.getCurrentId())

    console.log(this.cookieutils.cookieMethods.getCurrentId())
    if (this.cookieutils.cookieMethods.getCurrentId() === '') {
      await this.$router.push('/auth/login')
    }

    if (this.identity === 'hr') {
      await this.$router.push('/user/hrpersonalcenter')
    }

    const axios = require('axios')

    await axios.get(Constant.data().resumeBaseUrl + '/user/upload-resume/get_personal_center_data', {
      params: {
        id: this.cookieutils.cookieMethods.getCurrentId()
      }
    }).then(
      (res) => {
        this.tableData = res.data
      }
    ).catch(
      (err) => {
        this.tableData = []
      }
    )
  },
  components: {
    PersonalCenterNavi,
    PersonalCenterHeader,
    NavBar
  }
}
</script>
