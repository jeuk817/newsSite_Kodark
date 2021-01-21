<template>
   <div class="ReportedArticleComponent">
  <h1 class="title">Reported Article List</h1>
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
          Title
        </th>
        <th class="text-left" style="width: 49px;">
          Reporter
        </th>
        <th class="text-left">
          Reason
        </th>
        <th class="text-left">
          Blind
        </th>
        <th class="text-left">
          Send Mail
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
        <td>  
          <v-checkbox
          v-model="checkbox"
          
          >
          </v-checkbox>
        </td>
         <td>  
          <!-- <v-btn
          depressed
          small
          color="primary"
          @click="onSuspend"
          >
          Mail
          </v-btn> -->
            <span 
            class="material-icons mailBtn" 
            style="padding-top: 4px;" 
            @click="onSuspend">
              mail_outline
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

  <!-- Email Form -->
    <div class="sendMailForm">
      <div class="userEmail">
        <!-- <input type="text" value="TO: userEmail" disabled="disabled"> -->
        <v-text-field
          value="TO: JohnDoe@gmail.com"
          filled
          readonly
          dense="false"
          height="40px"
          class="email"
        ></v-text-field>
      </div>
      <div class="suspendReason">
        <v-select
          :items="items"
          filled
          dense="false"
          height="25px"
          label="Suspended Reasons" 
          class="reasonSelect"
        ></v-select>
      </div>
      <div class="content">
        <v-textarea
        filled
        name="input-7-4"
        height="250px"
        label="Write Detail Content"
        value="The Woodman set to work at once, and so sharp was his axe that the tree was soon chopped nearly through."
        ></v-textarea>
      </div>
      <div class="btns">
        <div class="cancleBtn">
          <v-btn
          depressed
          color="indigo"
          @click="hideForm"
          >
            <span>Cancle</span>
          </v-btn>
        </div>
    
        <div class="submitBtn">
          <v-btn
          depressed
          color="indigo"
          >
            <span>SubMit</span>
          </v-btn>
        </div>
      </div>
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
  methods: {
      onSuspend() {
        const sendMailForm = document.querySelector('.sendMailForm');
        sendMailForm.style.display = 'block';
      },
      hideForm() {
        const sendMailForm = document.querySelector('.sendMailForm');
        sendMailForm.style.display = 'none';
      }
  },
  async created () {
    const commentStratId = this.page;
    const { status, data } = await this.$store.dispatch('admin/getReportedArticles');
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

.sendMailForm{
  display: none;
  margin-top: 30px;
  border-radius: 5px;
  border: 1px solid black;
  height: 600px;
  background-color: #f0f0f0;
}

.userEmail .email,
.reasonSelect{
  margin: 20px 0 10px 40px;
  padding-left: 10px;
  width: 450px;
  height: 40px;
  border-radius: 5px;
  /* background-color: #f7f7f5; */
}
.content{
  margin: 50px 0 10px 40px;
  padding-left: 10px;
  width: 800px;
  height: 300px;
}
.btns{
  display: grid;
  grid-template-columns: 4fr 1fr;
}
.mailBtn{
  cursor: pointer;
}
.submitBtn{
  justify-self: end;
  margin-right: 60px;
}
.cancleBtn{
  justify-self: end;
}

.submitBtn span,
.cancleBtn span{
  color: white;
}
</style>