<template>
  <el-container>
    <PositionPage></PositionPage>
  </el-container>
</template>

<script>
import NavBar from './common/NavBar'
import PositionPage from './position/PositionPage'
export default {
  name: 'Home',
  components: {
    NavBar,
    PositionPage,
  }

}
</script>

<style scoped>

</style>
