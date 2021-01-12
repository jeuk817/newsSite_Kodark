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
            <th class="text-left">
              Suspended
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="item in desserts"
            :key="item.name"
          >
            <td>{{ item.id }}</td>
            <td>{{ item.calories }}</td>
            <td>
              <span class="material-icons stopBtn" @click="onSuspend">stop</span>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
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
</template>

<script>
export default {
 data: () => ({
       desserts: [
          {
            id: 1,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            id: 2,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            id: 3,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            id: 4,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            id: 5,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
             {
            id: 1,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            id: 2,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            id: 3,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            id: 4,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            id: 5,
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
        ]
    }),
    methods: {
      onSuspend() {
        const sendMailForm = document.querySelector('.sendMailForm');
        sendMailForm.style.display = 'block';
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
.submitBtn{
  text-align: right;
  margin-right: 60px;
}
.submitBtn span{
  color: white;
}
</style>