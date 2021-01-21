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
        <th class="text-left" style="width: 100px;">
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
        <th class="text-left" style="width: 76px;">
              Stop
        </th>
      </tr>
    </thead>
    <tbody>
       <tr
        v-for="comment in reportedComments"
        :key="comment.id"
      >
        <td style="width: 85px;">{{(comment.createdAt).substring(0,10)}}</td>
        <td class="articleTitle">{{comment.comment.content}}</td>
        <td style="width: 180px;">{{comment.user.email}}</td>
        <td>{{comment.reason}}</td>
        <td>{{comment.reason}}</td>
        <td>  
          <v-checkbox
          v-model="checkbox"
          
          >
          </v-checkbox>
        </td>
         <td>  
        <span class="material-icons stopBtn">
          block
        </span>
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
    page: 1,
    checkbox: true,
  }),
  async created () {
    console.log('created')
    const { status, data } = await this.$store.dispatch('admin/getReportedComments', {commentStartedId:6, doneFlag:'F'} );
    if(status === 200) {
      console.log(data)
      this.reportedComments = data;
      

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
.stopBtn{
  cursor: pointer;
}
</style>