<template>
  <div class="WaitArticleComponent">
  <h1 class="title">Wait Article List</h1>
  <v-simple-table
  fixed-header
  height="500px"
  >
  <template v-slot:default>
    <thead>
      <tr>
        <th class="text-left">
          Title
        </th>
        <th class="text-left">
          Reporter
        </th>
        <th class="text-left">
          Email
        </th>
        <th class="text-left">
          ID
        </th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="article in waitArticles"
        :key="article.id"
      >
        <td class="acticleTitle">
        <router-link :to="`/en${article._link.href}` ">
          {{ formatedTitle(article.article.title) }}
        </router-link>
        </td>
        <td>{{article.reporter.name}}</td>
        <td>{{article.reporter.email}}</td>
        <td>{{article.article.id}}</td>
      </tr>
    </tbody>
  </template>
  </v-simple-table>
  </div>
</template>

<script>
export default {
  data: () => ({
    waitArticles: []
  }),
  methods: {
    formatedTitle(title) {
      return title.length <= 45 ? title : title.substring(0, 45) + '...'
    }
  }, 
  async created () {
    const { status, data } = await this.$store.dispatch('admin/getArticles');
    if(status === 200) {
      console.log(data)
      this.waitArticles = data;
    }
    if(status === 404){
      //에러처리필요
    }
  }
}
</script>

<style scoped>
.title{
  padding: 10px 10px 10px 0 ;
  border-bottom: 1px solid black;
  margin-bottom: 50px;
}
.acticleTitle a{
  color: black;
}
</style>