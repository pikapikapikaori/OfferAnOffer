<template>
  <div>
    <p align="left" style="margin-left: 10%">
      {{interviewSta}}
    </p>

    <div v-show="identity === 'hr'" align="right" style="margin-right: 10%">
      <br>
      <el-button type="primary" @click="dialogFormVisible = true">
        填写面试评价
      </el-button>
    </div>

    <el-dialog title="填写面试评价" :visible.sync="dialogFormVisible">
      <el-form :model="formData">
        <el-form-item label="请填写面试评价">
          <el-input type="textarea" v-model="formData.tmpeva"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpdate">确 定</el-button>
      </div>
    </el-dialog>

    <div v-show="statusData.interviewStatus === 'completed'">
      <h3 align="left" style="margin-left: 15%">
        面试评价
      </h3>
      <p align="left" style="margin-left: 10%">
        {{evaluation}}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'OfferStatus',
  data () {
    return {
      interviewSta: '等待面试官与HR进行评价',
      statusData: {
        id: '',
        resumeId: '',
        interviewStatus: 'waiting'
      },
      evaluation: '',
      identity: '',
      dialogFormVisible: false,
      formData: {
        tmpeva: ''
      }
    }
  },
  methods: {
    setStatusName (status) {
      if (status === 'waiting') {
        return '等待面试官与HR进行评价'
      } else if (status === 'completed') {
        return '面试官与HR已完成评价'
      }
    },
    getEvaluation () {
      const axios = require('axios')

      axios.get(
        this.constant.data().processBaseUrl + '/public/get_evaluation', {
          params: {
            id: this.cookieutils.cookieMethods.getTargetId(this.identity),
            resumeId: this.cookieutils.cookieMethods.getTargetResumeId()
          }
        }
      ).then(
        (res) => {
          this.evaluation = res.data.evaluation
        }
      ).catch(
        (err) => {
          this.evaluation = ''
        }
      )
    },
    async submitUpdate () {
      this.dialogFormVisible = false

      const axios = require('axios')
      await axios.post(
        this.constant.data().processBaseUrl + '/hr/update_evaluation', null, {
          params: {
            id: this.cookieutils.cookieMethods.getTargetId(this.identity),
            resumeId: this.cookieutils.cookieMethods.getTargetResumeId(),
            evaluation: this.formData.tmpeva
          }
        }
      ).then(
        (res) => {
          if (res.data.code !== 0) {
            this.$message({
              showClose: true,
              message: '提交面试评价成功',
              type: 'success'
            })

            this.statusData.interviewStatus = res.data.interviewStatus
            this.evaluation = res.data.evaluation
          } else {
            this.$message({
              showClose: true,
              message: '提交面试评价失败',
              type: 'error'
            })
          }
        }
      ).catch(
        (err) => {
          this.$message({
            showClose: true,
            message: '提交面试评价失败',
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
      this.constant.data().processBaseUrl + '/public/get_interviewStatus', {
        params: {
          id: this.cookieutils.cookieMethods.getTargetId(),
          resumeId: this.cookieutils.cookieMethods.getTargetResumeId()
        }
      }
    ).then(
      (res) => {
        this.statusData = res.data

        this.resumeSta = this.setStatusName(this.statusData.interviewStatus)
      }
    ).catch(
      (err) => {
        this.interviewSta = '等待面试官与HR进行评价'
      }
    )

    this.getEvaluation()
  }
}
</script>

<style scoped>

</style>
