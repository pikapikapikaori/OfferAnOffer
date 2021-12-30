<template>
  <div>
    <p align="left" style="margin-left: 10%">
      {{offerSta}}
    </p>

    <div v-show="identity === 'hr'" align="right" style="margin-right: 10%">
      <br>
      <el-button type="primary" @click="dialogFormVisible = true">
        发送offer
      </el-button>
    </div>

    <el-dialog title="发送offer" :visible.sync="dialogFormVisible">
      <el-form :model="formData">
        <el-form-item label="发送后的状态" :label-width="'40%'">
          <el-select v-model="formData.tmpSta" placeholder="请选择状态">
            <el-option label="撤回offer" value="waiting"></el-option>
            <el-option label="发送offer" value="hired"></el-option>
            <el-option label="拒绝" value="rejected"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpdate">确 定</el-button>
      </div>
    </el-dialog>

    <div v-show="statusData.offerStatus === 'waiting'">
      <p align="left" style="margin-left: 10%">
        <br>
        恭喜您，已完成面试！请等待面试结果。
      </p>
    </div>

    <div v-show="statusData.offerStatus === 'hired'">
      <p align="left" style="margin-left: 10%">
        <br>
        恭喜您，已通过面试！我们很期待未来与您共事。
        <br>
        请等待进一步的入职通知。
      </p>
    </div>

    <div v-show="statusData.offerStatus === 'rejected'">
      <p align="left" style="margin-left: 10%">
        <br>
        很抱歉，您未能通过面试！
        <br>
        感谢您的支持与兴趣，如有疑问欢迎进一步联系。
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FinalStatus',
  data () {
    return {
      offerSta: '等待面试结果',
      statusData: {
        id: '',
        resumeId: '',
        offerStatus: 'waiting'
      },
      identity: '',
      dialogFormVisible: false,
      formData: {
        tmpSta: 'waiting'
      }
    }
  },
  methods: {
    setStatusName (status) {
      if (status === 'waiting') {
        return '等待面试结果'
      } else if (status === 'hired') {
        return '恭喜您通过面试'
      } else if (status === 'rejected') {
        return '很遗憾，您未能通过面试'
      }
    },
    async submitUpdate () {
      this.dialogFormVisible = false

      const axios = require('axios')
      await axios.post(
        this.constant.data().processBaseUrl + '/hr/update_offerStatus', null, {
          params: {
            id: this.cookieutils.cookieMethods.getTargetId(this.identity),
            resumeId: this.cookieutils.cookieMethods.getTargetResumeId(),
            offerStatus: this.formData.tmpSta
          }
        }
      ).then(
        (res) => {
          if (res.data.code !== 0) {
            this.$message({
              showClose: true,
              message: '发送成功',
              type: 'success'
            })

            this.statusData.offerStatus = this.formData.tmpSta
            this.offerSta = this.setStatusName(this.statusData.resumeStatus)
          } else {
            this.$message({
              showClose: true,
              message: '发送失败',
              type: 'error'
            })
          }
        }
      ).catch(
        (err) => {
          this.$message({
            showClose: true,
            message: '发送失败',
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
      this.constant.data().processBaseUrl + '/public/get_offerStatus', {
        params: {
          id: this.cookieutils.cookieMethods.getTargetId(),
          resumeId: this.cookieutils.cookieMethods.getTargetResumeId()
        }
      }
    ).then(
      (res) => {
        this.statusData = res.data

        this.offerSta = this.setStatusName(this.statusData.offerStatus)
      }
    ).catch(
      (err) => {
        this.offerSta = '等待面试结果'
      }
    )
  }
}
</script>

<style scoped>

</style>
