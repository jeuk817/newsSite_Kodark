<template>
  <div>
    <AppBarArticleHeader />
    article page
    <HelloWorld />
    <HelloWorld />
  </div>
</template>

<script>
import AppBarArticleHeader from '../components/headers/AppBarArticleHeader'
import HelloWorld from '../components/HelloWorld'

export default {
  components: {
    AppBarArticleHeader,
    HelloWorld
  }
}
</script>

<style scoped>

</style>
