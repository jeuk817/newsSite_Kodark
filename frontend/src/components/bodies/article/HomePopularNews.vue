<template>
  <div>
    <PopularNews1 :newsData="mainData" />
    <PopularNewsCarousels :newsData="subData" />
  </div>
</template>

<script>
import PopularNews1 from './PopularNews1'
import PopularNewsCarousels from './PopularNewsCarousels'

export default {
  props: ['popularNews'],
  data: () => ({
    mainData: [],
    subData: []
  }),
  components: {
    PopularNews1,
    PopularNewsCarousels
  },
  watch: {
    popularNews() {
      this.mainData = this.popularNews.slice(0, 4)
      this.subData = this.popularNews.slice(4)
    }
  }
}
</script>

<style scoped>

</style>
