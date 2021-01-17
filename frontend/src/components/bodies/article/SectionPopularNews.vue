<template>
  <div>
    <PopularNews2 :newsData="mainData" />
    <PopularNewsCarousels :newsData="subData" />
  </div>
</template>

<script>
import PopularNews2 from './PopularNews2'
import PopularNewsCarousels from './PopularNewsCarousels'

export default {
  props: ['popularNews'],
  data: () => ({
    mainData: [],
    subData: []
  }),
  components: {
    PopularNews2,
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
