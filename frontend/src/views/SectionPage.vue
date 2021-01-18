<template>
  <div>
    <AppBarArticleHeader />
    <SectionHeader :sectionName="sectionName" />
    <SectionPopularNews :popularNews="popularNews" />
    <div class="latestNews">
      <LatestNews2 :latestNews="latestNews" />
    </div>
  </div>
</template>

<script>
import AppBarArticleHeader from '../components/headers/AppBarArticleHeader'
import SectionHeader from '../components/headers/SectionHeader'
import SectionPopularNews from '../components/bodies/article/SectionPopularNews'
import LatestNews2 from '../components/bodies/article/LatestNews2'
import { utils } from '../components/mixins/utils'

export default {
  mixins: [utils],
  // data: () => ({
  //   popularNews: [],
  //   allLatestNews: [],
  //   route: this.$route.fullPath,
  // }),
  data() {
    return {
      popularNews: null,
      latestNews: null
    }
  },
  components: {
    AppBarArticleHeader,
    SectionHeader,
    SectionPopularNews,
    LatestNews2
  },
  methods: {
    async handleScroll(){
      if(this.latestNews === null && this.getCurrentScrollPercentage() > 90) {
        const {status, latest} = await this.$store.dispatch(
          'article/latest', { category: this.sectionName })
        
        if(status === 200) this.latestNews = latest
      }
    },
    getCurrentScrollPercentage() {
      return (window.scrollY + window.innerHeight) / document.body.clientHeight * 100
    }
  },
  computed: {
    sectionName() {
      return this.toUpperCaseFirstChar(this.currentRoute) === 'It' ? 'IT' : this.toUpperCaseFirstChar(this.currentRoute)
    }
  },
  watch: {
    async sectionName() {
      const { status, popularNews } = await this.$store.dispatch(
        'article/popular', { category: this.sectionName })

      this.popularNews = popularNews
      this.latestNews = null
    }
  },
  async created() {
    const { status, popularNews } = await this.$store.dispatch(
      'article/popular', { category: this.sectionName })
    
    this.popularNews = popularNews
    window.addEventListener('scroll', this.handleScroll)
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll)
  }
}
</script>

<style scoped>
.latestNews {
  margin-top: 200px;
}
</style>
