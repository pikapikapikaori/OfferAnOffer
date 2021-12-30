<template>
  <el-container>
    <el-header height="10%">
      这里放总的导航栏
    </el-header>

    <el-main>
      <el-row>
        <HRPersonalCenterHeader></HRPersonalCenterHeader>
      </el-row>
      <el-row>
        <h3>
          求职者姓名：{{ targetName }}
          <br>
        </h3>
      </el-row>
      <el-row>
        <el-col
          :xs="24"
          :sm="24"
          :md="4"
          :lg="4"
          :xl="4"
        >
          <HRPersonalCenterNavi></HRPersonalCenterNavi>
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
                label="简历Id"
                prop="resumeId"
                style="width: 50%">
              </el-table-column>
              <el-table-column
                label="简历"
                prop="resumeName"
                style="width: 90%">
              </el-table-column>
              <el-table-column
                label="职位"
                prop="jobName"
                style="width: 90%">
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
import HRPersonalCenterNavi from "./HRPersonalCenterNavi";
import HRPersonalCenterHeader from "./HRPersonalCenterHeader";

export default {
  name: 'HRClickinPersonalCenter',
  data() {
    return {
      tableData: [],
      identity: '',
      targetName: ''
    }
  },
  methods: {
    handleEdit(resumeId) {
      localStorage.setItem("targetResumeId", resumeId)

      this.$router.push('/user/process')
    }
  },
  created: async function() {
    this.targetName = localStorage.getItem("targetName")

    console.log(this.targetName)

    const axios = require('axios')

    await axios.get(Constant.data().resumeBaseUrl + '/user/upload-resume/get_personal_center_data', {
      params: {
        id: this.cookieutils.cookieMethods.getTargetId('hr')
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
    HRPersonalCenterNavi,
    HRPersonalCenterHeader
  }
}
</script>
