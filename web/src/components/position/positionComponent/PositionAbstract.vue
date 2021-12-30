<template>
  <el-container>
    <h1>{{ this.props.title }}</h1>
  </el-container>
</template>

<script>

export default {
  name: "PositionAbstrct",
  components: {},
  props: {
    job: Object,
    title: String,
    department: String,
    location: String,
    degree: String,
    workYear: String
  },
  data() {
    jobAbstract: {
    }
  },
  created: async function() {}
};
</script>

<style scoped></style>
