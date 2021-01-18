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
  }
}
</script>

<style scoped>
.articleBody {
  padding-top: 45px;
}
</style>
