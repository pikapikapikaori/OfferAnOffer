<el-upload
  class="upload-demo"
  action
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :before-remove="beforeRemove"
  multiple
  :limit="3"
  :on-exceed="handleExceed"
  :file-list="fileList">
<el-button size="small" type="primary">点击上传</el-button>
<div slot="tip" class="el-upload__tip">只能上传pdf文件，且不超过2MB</div>
</el-upload>
<script>
import Constant from "../../../assets/js/Constant";

export default {
  name: 'UploadResumePage',
  data() {
    return {
      fileList: []
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList)
      this.fileList = [] // 文件列表置空
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning('很抱歉当前支持最大上传文件个数为 1 个！')
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    created: async function() {
      const axios = require('axios')

      await axios.post(Constant.data().resumeBaseUrl + '/user/upload-resume/get_personal_center_data', {
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


  }
}
</script>
