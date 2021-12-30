<template>
  <div>
    <p align="left" style="margin-left: 10%">
      {{resumeSta}}
      <br>
      <br>
      <el-link href="">
        <!-- 这里插入简历模块的前端地址 -->
        点击查看已投递的简历信息
      </el-link>
    </p>

    <div v-show="identity === 'hr'" align="right" style="margin-right: 10%">
      <br>
      <el-button type="primary" @click="dialogFormVisible = true">
        筛选此简历
      </el-button>
    </div>

    <el-dialog title="筛选简历" :visible.sync="dialogFormVisible">
      <el-form :model="formData">
        <el-form-item label="筛选后的状态" :label-width="'40%'">
          <el-select v-model="formData.tmpSta" placeholder="请选择状态">
            <el-option label="待定" value="waiting"></el-option>
            <el-option label="接受简历" value="accepted"></el-option>
            <el-option label="拒绝简历" value="refused"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpdate">确 定</el-button>
      </div>
    </el-dialog>

    <div v-show="statusData.resumeStatus === 'accepted'">
      <p align="left" style="margin-left: 10%">
        <br>
        恭喜您，已通过简历筛选！
        <br>
        请等待进一步的面试通知。
      </p>
    </div>

    <div v-show="statusData.resumeStatus === 'refused'">
      <p align="left" style="margin-left: 10%">
        <br>
        很抱歉，您未能通过简历筛选！
        <br>
        本次申请即将结束，如有疑问欢迎进一步联系。
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ResumeStatus',
  data () {
    return {
      resumeSta: '等待HR筛选简历',
      statusData: {
        id: '',
        resumeId: '',
        resumeStatus: 'waiting'
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
        return '等待HR筛选简历'
      } else if (status === 'accepted') {
        return '您已通过简历筛选，等待HR确定面试时间'
      } else if (status === 'refused') {
        return '很抱歉，您未能通过简历筛选'
      }
    },
    async submitUpdate () {
      this.dialogFormVisible = false

      const axios = require('axios')
      await axios.post(
        this.constant.data().processBaseUrl + '/hr/update_resumeStatus', null, {
          params: {
            id: this.cookieutils.cookieMethods.getTargetId(this.identity),
            resumeId: this.cookieutils.cookieMethods.getTargetResumeId(),
            resumeStatus: this.formData.tmpSta
          }
        }
      ).then(
        (res) => {
          if (res.data.code !== 0) {
            this.$message({
              showClose: true,
              message: '筛选成功',
              type: 'success'
            })

            this.statusData.resumeStatus = this.formData.tmpSta
            this.resumeSta = this.setStatusName(this.statusData.resumeStatus)
          } else {
            this.$message({
              showClose: true,
              message: '筛选失败',
              type: 'error'
            })
          }
        }
      ).catch(
        (err) => {
          this.$message({
            showClose: true,
            message: '筛选失败',
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
      this.constant.data().processBaseUrl + '/public/get_resumeStatus', {
        params: {
          id: this.cookieutils.cookieMethods.getTargetId(),
          resumeId: this.cookieutils.cookieMethods.getTargetResumeId()
        }
      }
    ).then(
      (res) => {
        this.statusData = res.data

        this.resumeSta = this.setStatusName(this.statusData.resumeStatus)
      }
    ).catch(
      (err) => {
        this.resumeSta = '等待HR筛选简历'
      }
    )
  }
}
</script>

<style scoped>

</style>
