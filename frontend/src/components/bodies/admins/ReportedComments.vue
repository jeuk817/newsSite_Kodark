<template>
   <div class="ReportedCommentComponent">
  <h1 class="title">Reported Comment List</h1>
  <v-simple-table
  fixed-header
  >
  <template v-slot:default>
    <thead>
      <tr>
        <th class="text-left">
          Reported At
        </th>
        <th class="text-left">
          Comment
        </th>
        <th class="text-left" style="width: 49px;">
          Writer
        </th>
        <th class="text-left">
          Reason
        </th>
        <th class="text-left">
          Reporter
        </th>
        <th class="text-left">
          Blind
        </th>
        <th class="text-left">
          Stop
        </th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="article in reportedArticles"
        :key="article.id"
      >
        <td style="width: 85px;">{{article.createdAt}}</td>
        <td class="articleTitle"><router-link :to="`/en/article?articleId=${article.id}` ">{{article.article.title}}</router-link></td>
        <td style="width: 150px;">{{article.reporter.email}}</td>
        <td>{{article.reason}}</td>
        <td>{{article.reason}}</td>
        <td>  
          <v-checkbox
          v-model="checkbox"
          
          >
          </v-checkbox>
        </td>
         <td>  
          <v-btn
          depressed
          small
          color="primary"
          >
          Mail
          </v-btn>
        </td>
      </tr>
    </tbody>
  </template>
  </v-simple-table>
   <div class="text-center page">
    <v-pagination
      v-model="page"
      :length="6"
    ></v-pagination>
  </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    reportedArticles: [],
    page: 1,
    checkbox: true,
  }),
  async created () {
    const { status, data } = await this.$store.dispatch('admin/getReportedComments', {commentStartedId:this.page}, );
    if(status === 200) {
      console.log('created')
      console.log(data)
      this.reportedArticles= data
    }
    
      
    if(status === 500){
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
.articleTitle a{
  color: black;
}
.page{
  margin-top: 50px;
}
</style>