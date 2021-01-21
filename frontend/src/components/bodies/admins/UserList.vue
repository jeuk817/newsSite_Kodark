<template>
  <div class="UserListComponent">
      <h1 class="title">User List</h1>
      <v-simple-table
    >
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">
              ID
            </th>
            <th class="text-left">
              E-mail
            </th>
            <th class="text-left" style="padding-left: 32px;">
              Stop
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="user in users"
            :key="user.id"
          >
            <td>{{ user.id }}</td>
            <td>{{ user.email }}</td>
            <td style="padding-left: 32px">
              <span class="material-icons stopBtn" @click="onSuspend">block</span>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <div class="text-center paging">
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
          value="JohnDoe@gmail.com"
          filled
          readonly
          height="40px"
          class="email"
        ></v-text-field>
      </div>
      <div class="suspendReason">
        <v-select
          :items="items"
          filled
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
      users : [],
      items: [
              'Inappropriate comments',
              'Defamation of others',
              'Continue writing the same comment',
            ],
      page: 1,

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
      console.log('created')
      const { status, data } = await this.$store.dispatch('admin/getUsers', {startIndex:1} );
      if(status === 200) {
        console.log(data)
        this.users = data;
      }  
      if(status === 500){
        
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

.stopBtn{
  cursor: pointer;
}

.paging{
  margin: 60px 175px 0 0;
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