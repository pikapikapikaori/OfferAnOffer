<template>
  <el-container>
    <el-header>
      <NavBar></NavBar>
    </el-header>

    <el-main>
      <el-row class="picklist" :gutter="20">
        <el-col :span="8">
          <el-cascader
            v-model="workYearValue"
            placeholder="请选择工作经验"
            :props="cascaderProps"
            :options="workYearOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-col>
        <el-col :span="8">
          <el-cascader
            v-model="degreeValue"
            placeholder="请选择学历"
            :props="cascaderProps"
            :options="degreeOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-col>
        <el-col :span="8">
          <el-cascader
            v-model="jobCategoryValue"
            placeholder="请选择岗位"
            :props="cascaderProps"
            :options="jobCategoryOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-col>
      </el-row>
      <el-row class="picklist" :gutter="20">
        <el-col :span="8">
          <el-cascader
            v-model="locationValue"
            placeholder="请选择工作地点"
            :props="cascaderProps"
            :options="locationOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-col>
        <el-col :span="8">
          <el-cascader
            v-model="departmentValue"
            placeholder="请选择目标部门"
            :props="cascaderProps"
            :options="departmentOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-col>
        <el-col :span="8">
          <el-button
            type="primary"
            v-show="identity === 'hr'"
            @click="postPositionDialogVisible = true"
            >发布岗位</el-button
          >
        </el-col>
      </el-row>
    </el-main>
    <el-main>
      <el-row
        class="position-list-item"
        v-for="(item, index) in jobs"
        :key="index"
      >
        <el-col :span="18">
          <div>
            <el-button
              type="text"
              class="title"
              @click="handleDetailButtonClick(item)"
              >{{ item.title }}</el-button
            >
          </div>
          <div>
            <span class="department info">{{
              item.department.departmentValue
            }}</span>
          </div>
          <div>
            <span class="location info">{{ item.jobCategory.value }}</span
            >&nbsp;&nbsp;|&nbsp;&nbsp;
            <span class="location info">{{ item.location.value }}</span
            >&nbsp;&nbsp;|&nbsp;&nbsp;
            <span class="degree info">{{ item.degree.degreeValue }}</span
            >&nbsp;&nbsp;|&nbsp;&nbsp;
            <span class="work-year info">{{
              item.workYear.workYearValue
            }}</span>
          </div>
        </el-col>
      </el-row>
    </el-main>

    <el-footer> </el-footer>

    <el-dialog
      :title="dialogItem.title"
      :visible.sync="dialogVisible"
      width="80%"
      :before-close="handleClose"
    >
      <div>
        <span class="location info">{{ dialogItem.jobCategory.value }}</span
        >&nbsp;&nbsp;|&nbsp;&nbsp;
        <span class="location info">{{ dialogItem.location.value }}</span
        >&nbsp;&nbsp;|&nbsp;&nbsp;
        <span class="degree info">{{ dialogItem.degree.degreeValue }}</span
        >&nbsp;&nbsp;|&nbsp;&nbsp;
        <span class="work-year info">{{
          dialogItem.workYear.workYearValue
        }}</span>
      </div>
      <div class="description">{{ dialogItem.description }}</div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>
        <el-button type="primary" @click="onApplyButtonClick"
          >投递简历</el-button
        >
      </span>
    </el-dialog>

    <el-dialog
      title="发布职位"
      :visible.sync="postPositionDialogVisible"
      width="80%"
      :before-close="handleClose"
    >
      <el-form :model="newPositionForm" label-width="120px">
        <el-form-item label="岗位名称">
          <el-input v-model="newPositionForm.title"></el-input>
        </el-form-item>

        <el-form-item label="工作经验">
          <el-cascader
            v-model="newPositionForm.workYearId"
            placeholder="请选择工作经验"
            :props="cascaderProps"
            :options="workYearOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-form-item>

        <el-form-item label="学历">
          <el-cascader
            v-model="newPositionForm.degreeId"
            placeholder="请选择学历"
            :props="cascaderProps"
            :options="degreeOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-form-item>

        <el-form-item label="岗位类别">
          <el-cascader
            v-model="newPositionForm.jobCategoryId"
            placeholder="请选择岗位"
            :props="cascaderProps"
            :options="jobCategoryOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-form-item>

        <el-form-item label="工作地点">
          <el-cascader
            v-model="newPositionForm.locationId"
            placeholder="请选择工作地点"
            :props="cascaderProps"
            :options="locationOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-form-item>

        <el-form-item label="需求部门">
          <el-cascader
            v-model="newPositionForm.departmentId"
            placeholder="请选择目标部门"
            :props="cascaderProps"
            :options="departmentOptions"
            @change="handlePicklistChange"
            collapse-tags
            clearable
          ></el-cascader>
        </el-form-item>

        <el-form-item label="具体描述">
          <el-input type="textarea" v-model="newPositionForm.desc"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="postPositionDialogVisible = false">关闭</el-button>
        <el-button type="primary" @click="handlePostPositionCommit"
          >提交</el-button
        >
      </span>
    </el-dialog>
  </el-container>
</template>

<script>
import NavBar from "../common/NavBar";
import PositionAbstrct from "./positionComponent/PositionAbstract";

export default {
  name: "PositionPage",
  components: {
    NavBar,
    PositionAbstrct
  },
  data() {
    return {
      identity: "",
      dialogVisible: false,
      postPositionDialogVisible: false,
      dialogItem: {
        title: "",
        description: "",
        location: {
          value: ""
        },
        degree: {
          degreeValue: ""
        },
        workYear: {
          workYearValue: ""
        },
        jobCategory: {
          value: ""
        }
      },
      jobs: [],
      newPositionForm: {
        title: "",
        desc: "",
        workYearId: 0,
        degreeId: 0,
        departmentId: 0,
        locationId: 0,
        jobCategoryId: 0
      },
      originalJobs: [],
      workYearValue: [],
      workYearOptions: [],
      locationValue: [],
      locationOptions: [],
      degreeValue: [],
      degreeOptions: [],
      departmentValue: [],
      departmentOptions: [],
      jobCategoryValue: [],
      jobCategoryOptions: [],
      cascaderProps: { multiple: true }
    };
  },
  created: async function() {
    const filterEmptyField = el => {
      Object.keys(el).forEach(key => {
        if (Array.isArray(el[key])) {
          filterEmptyFieldForArray(el[key]);
        } else if (typeof el[key] === "object") {
          filterEmptyField(el[key]);
        }
        if (!el[key] || (Array.isArray(el[key]) && el[key].length === 0)) {
          delete el[key];
        } else {
        }
      });
      return el;
    };

    const filterEmptyFieldForArray = arr => {
      arr.forEach(el => filterEmptyField(el));
      return arr;
    };

    const axios = require("axios");

    const promises = [];
    promises.push(
      axios
        .get(this.constant.data().positionBaseUrl + "/position/job-info")
        .then(res => {
          console.log(res.data);
          this.originalJobs = res.data;
          this.jobs = this.originalJobs;
        })
        .catch(err => {
          console.log(err);
        })
    );

    promises.push(
      axios
        .get(this.constant.data().positionBaseUrl + "/position/locations")
        .then(res => {
          console.log(res.data);
          this.locationOptions = filterEmptyFieldForArray(res.data);
        })
    );

    promises.push(
      axios
        .get(this.constant.data().positionBaseUrl + "/position/job-categories")
        .then(res => {
          console.log(res.data);
          this.jobCategoryOptions = filterEmptyFieldForArray(res.data);
        })
    );

    promises.push(
      axios
        .get(this.constant.data().positionBaseUrl + "/position/work-year")
        .then(res => {
          console.log(res.data);
          this.workYearOptions = res.data.map(src => {
            return {
              value: src.id,
              label: src.workYearValue
            };
          });
        })
    );

    promises.push(
      axios
        .get(this.constant.data().positionBaseUrl + "/position/degrees")
        .then(res => {
          console.log(res.data);
          this.degreeOptions = res.data.map(src => {
            return {
              value: src.id,
              label: src.degreeValue
            };
          });
        })
    );

    promises.push(
      axios
        .get(this.constant.data().positionBaseUrl + "/position/departments")
        .then(res => {
          console.log(res.data);
          this.departmentOptions = res.data.map(src => {
            return {
              value: src.id,
              label: src.departmentValue
            };
          });
        })
    );

    await Promise.all(promises);
    this.identity = await this.cookieutils.cookieMethods.getCurrentIdentity(
      this.cookieutils.cookieMethods.getCurrentId()
    );
  },
  methods: {
    handleClose: function(done) {
      this.$confirm("确认离开本职位信息？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    handleDetailButtonClick(item) {
      console.log(item);
      this.dialogItem = item;
      this.dialogVisible = true;
    },
    handlePicklistChange() {
      console.log("handle change");
      this.jobs = this.originalJobs
        .filter(
          job =>
            !this.workYearValue ||
            this.workYearValue.length === 0 ||
            (this.workYearValue.length > 0 &&
              this.workYearValue.flat().indexOf(job.workYear.id) >= 0)
        )
        .filter(
          job =>
            !this.degreeValue ||
            this.degreeValue.length === 0 ||
            (this.degreeValue.length > 0 &&
              this.degreeValue.flat().indexOf(job.degree.id) >= 0)
        )
        .filter(
          job =>
            !this.jobCategoryValue ||
            this.jobCategoryValue.length === 0 ||
            (this.jobCategoryValue.length > 0 &&
              this.jobCategoryValue.flat().indexOf(job.jobCategory.id) >= 0)
        )
        .filter(
          job =>
            !this.locationValue ||
            this.locationValue.length === 0 ||
            (this.locationValue.length > 0 &&
              this.locationValue.flat().indexOf(job.location.id) >= 0)
        )
        .filter(
          job =>
            !this.departmentValue ||
            this.departmentValue.length === 0 ||
            (this.departmentValue.length > 0 &&
              this.departmentValue.flat().indexOf(job.department.id) >= 0)
        );
    },
    handlePostPositionButtonClick() {},
    async handlePostPositionCommit() {
      const axios = require("axios");
      await axios
        .post(
          this.constant.data().positionBaseUrl + "/position/job-info",
          null,
          {
            params: {
              title: this.newPositionForm.title,
              description: this.newPositionForm.desc,
              workYearId: Number.parseInt(this.newPositionForm.workYearId[0]),
              degreeId: Number.parseInt(this.newPositionForm.degreeId[0]),
              departmentId: Number.parseInt(
                this.newPositionForm.departmentId[0]
              ),
              locationId: Number.parseInt(this.newPositionForm.locationId[0]),
              jobCategoryId: Number.parseInt(
                this.newPositionForm.jobCategoryId[0]
              )
            }
          }
        )
        .then(res => {
          console.log(res);
          this.$message({
            showClose: true,
            message: "发送成功",
            type: "success"
          });
        })
        .catch(err => {
          console.log(err);
          this.$message({
            showClose: true,
            message: "发送失败",
            type: "error"
          });
        });
    },
    async onApplyButtonClick() {
      localStorage.setItem("jobId", this.dialogItem.id);
      localStorage.setItem("jobName", this.dialogItem.title);
      await this.$router.push({ path: "/user/upload-resume" });
    }
  }
};
</script>

<style scoped>
.el-row.picklist {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}

.position-list-item {
  background-color: #ffffff;
  height: 100px;
  cursor: pointer;
  text-align: left;
  padding: 0px 12px 0px 12px;
}

.position-list-item:hover {
  background-color: #fffcf0;
}

.position-list-item .title {
  font-size: 28px;
  color: #d3a300;
}

.info {
  font-size: 14px;
  color: #81878c;
  line-height: 20px;
  margin-bottom: 12px;
}

.position-list-item:hover .title {
  color: #996b0f;
}

.el-form-item__content {
  text-align: left;
}
</style>
