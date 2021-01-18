<template>
  <div>
    <AppBarArticleHeader />
    <div class="articleBody">
      <ArticleTitle :title="title" :subTitle="subTitle" /> 
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
  data() {
    return {
      title: '',
      subTitle: ''
    }
  },
  async created() {
    const articleId = this.$route.query.articleId
    const { status, article, links } = await this.$store.dispatch('article/article', { articleId })
    console.log(status)
    console.log(article)
    console.log(links)
    if(status === 200) {
      this.title = article.title
      this.subTitle = article.subTitle
    }
  }
}
</script>

<style scoped>
.articleBody {
  padding-top: 100px;
}
</style>
