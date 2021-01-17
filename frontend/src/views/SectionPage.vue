<template>
  <div>
    <AppBarHeader />
    <SectionHeader />
    section page
    <HelloWorld />
    <HelloWorld />
    <HelloWorld />
  </div>
</template>

<script>
import AppBarHeader from '../components/headers/AppBarHeader'
import SectionHeader from '../components/headers/SectionHeader'
import { utils } from '../components/mixins/utils'
import HelloWorld from '../components/HelloWorld'

export default {
  mixins: [utils],
  components: {
    AppBarHeader,
    SectionHeader,
    HelloWorld
  },
  async created() {
    const {status, popularNews} = await this.$store.dispatch('article/popular', {category: 'all'})
    this.popularNews = popularNews
    window.addEventListener('scroll', this.handleScroll)
  },
}
</script>

<style scoped>

</style>
