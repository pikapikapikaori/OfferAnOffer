<template>
  <div>
    <el-card>
      <!-- 上传文件 -->
      <div class="div-label"><label>上传文件，仅支持<span style="font-weight: bold">.pdf</span>格式的PDF文件，且大小不超过<span
        style="font-weight: bold;color: red">10MB</span></label></div>
      <el-upload
        class="upload-demo"
        ref="upload"
        action
        accept=".pdf"
        :limit="1"
        :file-list="fileList"
        :auto-upload="false"
        :on-exceed="handleExceed"
        :on-preview="handlePreview"
        :on-change="handleChange"
        :on-remove="handleRemove"
        :before-upload="beforeUpload"
        :http-request="httpRequest"
        :on-success="handleSuccess"
        :on-error="handleError">
        <el-button slot="trigger" size="medium" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="medium" type="success" @click="submitUpload">上传到服务器</el-button>
      </el-upload>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Upload',
  data () {
    return {
      fileList: []
    }
  },
  methods: {
    // 超过文件上传最大个数
    handleExceed (files, fileList) {
      this.$message.warning('很抱歉当前支持最大上传文件个数为 1 个！')
    },
    // 文件上传到服务器之前的文件校验
    beforeUpload (file) {
      const extension = file.name.substring(file.name.lastIndexOf('.') + 1)
      const size = file.size / 1024 / 1024
      if (extension !== 'pdf') { // 校验文件格式
        this.$message.warning('只支持上传后缀名为.pdf的PDF文件')
      }
      if (size > 10) { // 校验文件大小
        this.$message.warning('文件大小不能超过10MB')
      }
    },
    // 文件状态改变
    handleChange (file, fileList) {
      if (file) {
        this.fileList = fileList.slice(-3)
      }
    },
    // 文件删除时
    handleRemove (file, fileList) {
      console.log(file, fileList)
      this.fileList = [] // 文件列表置空
    },
    // 点击文件列表中已上传的文件时的钩子
    handlePreview (file) {
      console.log(file)
    },
    // 文件上传成功
    handleSuccess (response, file, fileList) {
      console.log('-------handleSuccess-------')
      console.log(response)
      this.$message.success('文件 [' + file.name + '] 上传成功')
    },
    // 文件上传失败
    handleError (err, file, fileList) {
      console.log('-------handleError-------')
      console.log(err)
      this.$message.error('文件上传失败')
    },
    // 覆盖默认的上传行为，可以自定义上传的实现
    httpRequest (param) {
      console.log(param)
      const fileObj = param.file // 获取file文件
      const fileName = param.file.filename
      const formData = new FormData() // FormData对象
      formData.append('file', fileObj) // file封装到FormData里
      this.axios.post('/user/upload-resume/add_resume_name', fileName )
      // 请求后台上传数据的接口
      this.axios.post('/user/upload-resume/add_resume', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
        // url: '/upload',
        // data: formData
        // method: 'post'
      }).then(res => {
        console.log('-------res--------')
        console.log(res)
        if (res.data.meta.status === '200') {
          this.$message.success(res.data.meta.msg)
          // 清空文件列表
          this.fileList = []
        } else {
          this.$message.error(res.data.meta.msg)
        }
      }, err => {
        console.log('-------err--------')
        console.log(err)
        this.$message.error('上传文件内容有问题，请保证上传文件字段均不为空且正确')
      })
    },
    // 上传文件
    submitUpload () {
      if (this.fileList.length === 0) { // 上传文件列表为空
        this.$message.warning('请选择一个上传文件')
      } else { // 执行上传操作
        this.$refs.upload.submit()
      }
    }
  }
}
</script>

<style scoped>
.div-label {
  padding: 20px 0;
  width: 100%;
}
</style>
