<template>
  <div>
    <AppBarHeader />
    <SectionHeader :sectionName="sectionName" />
    <SectionPopularNews :popularNews="popularNews" />
  </div>
</template>

<script>
import AppBarHeader from '../components/headers/AppBarHeader'
import SectionHeader from '../components/headers/SectionHeader'
import SectionPopularNews from '../components/bodies/article/SectionPopularNews'
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
      popularNews: [],
      allLatestNews: []
    }
  },
  components: {
    AppBarHeader,
    SectionHeader,
    SectionPopularNews
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
      console.log(popularNews)
      this.popularNews = popularNews
    }
  },
  async created() {
    const { status, popularNews } = await this.$store.dispatch(
      'article/popular', { category: this.sectionName })
    
    this.popularNews = popularNews
    // window.addEventListener('scroll', this.handleScroll)
  },
}
</script>

<style scoped>

</style>
