<template>
  <div>
    <AppBarArticleHeader />
    <div class="articleBody">
      <ArticleTitle /> 
      article page
      <HelloWorld />
      <HelloWorld />
    </div>
  </div>
</template>

<script>
import AppBarArticleHeader from '../components/headers/AppBarArticleHeader'
import ArticleTitle from '../components/bodies/article/ArticleTitle'
import HelloWorld from '../components/HelloWorld'

export default {
  components: {
    AppBarArticleHeader,
    ArticleTitle,
    HelloWorld
  },
  async created() {
    const articleId = this.$route.query.articleId
    const { status, article, links } = await this.$store.dispatch('article/article', { articleId })
    console.log(status)
    console.log(article)
    console.log(links)
  }
}
</script>

<style scoped>
.articleBody {
  padding-top: 100px;
}
</style>
