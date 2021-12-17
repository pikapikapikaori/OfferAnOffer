<template>
  <div>
    <el-descriptions title="面试信息" :column="1">
      <el-descriptions-item label="面试主题">

      </el-descriptions-item>
      <el-descriptions-item label="面试时间">

      </el-descriptions-item>
      <el-descriptions-item label="面试地点">

      </el-descriptions-item>
      <el-descriptions-item label="注意事项">
        <p align="left">
          <br>
          请务必确认好面试的时间与地点。
          <br>
          我们期待与您的见面。
        </p>
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
export default {
  name: 'InterviewStatus',
  data () {
    return {
      interviewData: {
        id: '',
        topic: '',
        time: '',
        place: ''
      },
      identity: ''
    }
  },
  created: async function () {
    this.identity = await this.cookieutils.cookieMethods.getCurrentIdentity(this.cookieutils.cookieMethods.getCurrentId())

    // 这里是调用获得面试信息的实现
  }
}
</script>

<style scoped>

</style>
