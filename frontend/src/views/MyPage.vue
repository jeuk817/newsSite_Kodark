<template>
  <div>
    <MyPageHeader />
    <div class="myPageBody">
      <!-- <Navi />
      <Content /> -->
    </div>
  </div>
</template>

<script>
import MyPageHeader from '../components/headers/MyPageHeader'

export default {
  components: {
    MyPageHeader
  }
}
</script>

<style scoped>
.myPageBody {
  padding-top: 48px;
}
</style>
