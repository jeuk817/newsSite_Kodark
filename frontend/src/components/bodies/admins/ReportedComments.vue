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
        v-for="user in reportUsers"
        :key="user.id"
      >
       <tr
        v-for="comment in comments"
        :key="comment.id"
      >
        <td style="width: 85px;">{{comment.createdAt}}</td>
        <td class="articleTitle">{{comment.content}}</td>
        <td style="width: 150px;">{{user.email}}</td>
        <td>{{comment.reason}}</td>
        <td>{{comment.reason}}</td>
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
    reportedComments: [],
    reportUsers: [],
    comments: [],

    page: 1,
    checkbox: true,
  }),
  async created () {
    console.log('created')
    const { status, data } = await this.$store.dispatch('admin/getReportedComments', {commentStartedId:6, doneFlag:'F'} );
    if(status === 200) {
      console.log(data)
     
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