<template>
  <div>
    <AppBarArticleHeader />
    <ArticleBody />
  </div>
</template>

<script>
import AppBarArticleHeader from '../components/headers/AppBarArticleHeader'
import ArticleBody from '../components/bodies/article/ArticleBody'

export default {
  components: {
    AppBarArticleHeader,
    ArticleBody
  }
}
</script>

<style scoped>

</style>
