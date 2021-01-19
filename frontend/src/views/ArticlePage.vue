<template>
  <div>
    <AppBarArticleHeader />
    <div class="articleBody">
      <ArticleTitle :title="title" :subTitle="subTitle" :reporter="reporter" :editedAt="editedAt" />
      <div class="articleTitleMargin">
        <ArticleSubFunction />
      </div>
      <div class="articleContentContainer" id="articleContent"></div>
      <div class="reporterInfo">
        <p>
          By Reporter {{ reporter.name }}
        </p>
        <p>
          Email : {{ reporter.email }}
        </p>
        <p>
          Created at {{ createdAt }}
        </p>
        <p>
          Edited at {{ editedAt }}
        </p>
        <p>
          <ArticleSubFunction />
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import AppBarArticleHeader from '../components/headers/AppBarArticleHeader'
import ArticleTitle from '../components/bodies/article/ArticleTitle'
import ArticleSubFunction from '../components/bodies/article/ArticleSubFunction'
import HelloWorld from '../components/HelloWorld'

export default {
  components: {
    AppBarArticleHeader,
    ArticleTitle,
    ArticleSubFunction,
    HelloWorld
  },
  data() {
    return {
      title: '',
      subTitle: '',
      content: '',
      createdAt: '',
      editedAt: '',
      reporter: {}
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
      this.content = article.content
      this.reporter = article.reporter
      this.createdAt = article.createdAt
      this.editedAt = article.editedAt
      document.getElementById('articleContent').innerHTML = this.content
    }
  }
}
</script>

<style>

.articleContentContainer p
, .articleContentContainer div
, .articleContentContainer h1
, .articleContentContainer h2
, .articleContentContainer h3
{
  position: relative;
  max-width: 600px;
  width: 600px;
  font-size: 18px;
  margin: 0 auto;
  display: grid;
  justify-content: center;
}

.articleContentContainer img {
  max-width: 1200px;
  object-fit: cover;
  margin: 43px auto;
}

</style>

<style scoped>
.articleBody {
  padding-top: 100px;
}

.articleTitleMargin {
  margin-left: calc((100% - 600px) / 2);
  margin-right: auto;
  padding: 20px 0;
}

.reporterInfo {
  position: relative;
  max-width: 600px;
  width: 600px;
  left: calc((100% - 600px) / 2);
  padding-top: 43px;
  font-size: 16px;
  font-style: italic;
  border-bottom: 1px solid black;
  margin-bottom: 40px;
}
</style>
