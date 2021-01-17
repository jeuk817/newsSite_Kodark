<template>
  <div class="home">
    <AppBarHeader />
    <HomeHeader />
    <HomePopularNews :popularNews="popularNews" />
    <div class="allLatestNews" v-if="allLatestNews">
      <LatestNews1 v-for="(latestNews, i) in allLatestNews" :key="i"
      :latestNews="latestNews" />
    </div>
  </div>
</template>

<script>
import AppBarHeader from '../components/headers/AppBarHeader'
import HomeHeader from '../components/headers/HomeHeader'
import HelloWorld from '../components/HelloWorld'
import LatestNews1 from '../components/bodies/article/LatestNews1'
import layout2 from '../components/test/layout2'
import Footer from '../components/footer/Footer'
import HomePopularNews from '../components/bodies/article/HomePopularNews'

export default {
  name: 'Home',
  data: () => ({
    popularNews: null,
    allLatestNews: null
  }),
  components: {
    AppBarHeader,
    HomeHeader,
    HelloWorld,
    LatestNews1,
    layout2,
    HomePopularNews,
    Footer
  },
  methods: {
    async handleScroll(){
      if(this.allLatestNews === null && this.getCurrentScrollPercentage() > 90) {
        console.log('start')
        const {status, latestAll} = await this.$store.dispatch('article/latestAll')
        this.allLatestNews = latestAll
      }
    },
    getCurrentScrollPercentage() {
      return (window.scrollY + window.innerHeight) / document.body.clientHeight * 100
    }
  },
  async created() {
    const {status, popularNews} = await this.$store.dispatch('article/popular', {category: 'all'})
    this.popularNews = popularNews
    window.addEventListener('scroll', this.handleScroll)
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll)
  }
}
</script>

<style scoped>
.allLatestNews{
  margin-top: 200px;
}
</style>
