<template>
  <div>
    <el-steps :active="mainStatus" finish-status="success" align-center>
      <el-step title="已投递简历">
      </el-step>
      <el-step title="已通过简历筛选"></el-step>
      <el-step title="已完成面试"></el-step>
      <el-step title="已结束"></el-step>
    </el-steps>

    <div v-show="identity === 'hr'" align="right" style="margin-right: 10%">
      <br>
      <el-button type="primary" @click="dialogFormVisible = true">
        修改当前状态
      </el-button>
    </div>

    <el-dialog title="更改当前状态" :visible.sync="dialogFormVisible">
      <el-form :model="formData">
        <el-form-item label="更改后的状态" :label-width="'40%'">
          <el-select v-model="formData.tmpSta" placeholder="请选择状态">
            <el-option label="已投递简历" value="resume"></el-option>
            <el-option label="已通过简历筛选" value="interview"></el-option>
            <el-option label="已完成面试" value="offer"></el-option>
            <el-option label="已结束" value="finished"></el-option>
          </el-select>
        </el-form-item>
    </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpdate">确 定</el-button>
      </div>
    </el-dialog>

    <div v-show="mainStatus === 0">
      <br>
      <ResumeStatus></ResumeStatus>
    </div>

    <div v-show="mainStatus === 1">
      <br>
      <InterviewStatus></InterviewStatus>
    </div>

    <div v-show="mainStatus === 2">
      <br>
      <OfferStatus></OfferStatus>
    </div>

    <div v-show="mainStatus === 3">
      <br>
      <FinalStatus></FinalStatus>
    </div>
  </div>
</template>

<script>
import ResumeStatus from './ResumeStatus'
import InterviewStatus from './InterviewStatus'
import OfferStatus from './OfferStatus'
import FinalStatus from './FinalStatus'

export default {
  name: 'MainStatus',
  data () {
    return {
      mainStatus: 0,
      statusData: {
        id: '',
        resumeId: '',
        status: 'resume'
      },
      identity: 'interviewee',
      dialogFormVisible: false,
      formData: {
        tmpSta: 'resume'
      }
    }
  },
  methods: {
    setStatusName (status) {
      if (status === 'resume') {
        return 0
      } else if (status === 'interview') {
        return 1
      } else if (status === 'offer') {
        return 2
      } else if (status === 'finished') {
        return 3
      }
    },
    async submitUpdate () {
      this.dialogFormVisible = false

      const axios = require('axios')
      await axios.post(
        this.constant.data().processBaseUrl + '/hr/update_status', null, {
          params: {
            id: this.cookieutils.cookieMethods.getTargetId(this.identity),
            resumeId: this.cookieutils.cookieMethods.getTargetResumeId(),
            status: this.formData.tmpSta
          }
        }
      ).then(
        (res) => {
          console.log(res)
          if (res.data.code !== 0) {
            this.$message({
              showClose: true,
              message: '修改成功',
              type: 'success'
            })

            this.statusData.status = this.formData.tmpSta
            this.mainStatus = this.setStatusName(this.statusData.status)
          } else {
            this.$message({
              showClose: true,
              message: '修改失败',
              type: 'error'
            })
          }
        }
      ).catch(
        (err) => {
          console.log(err)
          this.$message({
            showClose: true,
            message: '修改失败',
            type: 'error'
          })
        }
      )
    }
  },
  created: async function () {
    this.identity = await this.cookieutils.cookieMethods.getCurrentIdentity(this.cookieutils.cookieMethods.getCurrentId())

    const axios = require('axios')

    await axios.get(
      this.constant.data().processBaseUrl + '/public/get_status', {
        params: {
          id: this.cookieutils.cookieMethods.getTargetId(),
          resumeId: this.cookieutils.cookieMethods.getTargetResumeId()
        }
      }
    ).then(
      (res) => {
        this.statusData = res.data

        this.mainStatus = this.setStatusName(this.statusData.status)
      }
    ).catch(
      (err) => {
        this.mainStatus = 0
      }
    )
  },
  components: {
    ResumeStatus,
    InterviewStatus,
    OfferStatus,
    FinalStatus
  }
}
</script>

<style scoped>

</style>
