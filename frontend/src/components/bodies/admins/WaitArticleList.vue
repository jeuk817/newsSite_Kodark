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
          Article Title
        </th>
        <th class="text-left">
          Reporter Name
        </th>
        <th class="text-left">
          Email
        </th>
        <th class="text-left">
          Article ID
        </th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="article in waitArticles"
        :key="article.id"
      >
        <td class="acticleTitle"><router-link :to="`/en/article?articleId=${article.id}` ">{{article.title}}</router-link></td>
        <td>{{article.name}}</td>
        <td>{{article.email}}</td>
        <td>{{article.id}}</td>
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
  async created () {
    const { status, data } = await this.$store.dispatch('admin/getWaitArticles');
    if(status === 200) {
      for(let i=0; i<data.length; i++){
        const waitArticleItem = new Object();
        waitArticleItem = Object.assign({},data[i].article,data[i].reporter);
        this.waitArticles.push(waitArticleItem)
      }
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