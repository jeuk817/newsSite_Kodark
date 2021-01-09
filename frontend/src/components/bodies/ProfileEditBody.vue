<template>
  <div class="ProfileEditComponent">
      <h1 class="title">Profile Detail</h1>
      <div class="userDetailInfo">
        <div class="userDetailContainer">
            <p class="userInfoTitle">
              Nick Name
            </p>
            <div class="userInfoContent">
              <p>이푸름</p> 
            </div>
        </div>
        <div class="userDetailContainer">
            <div class="userInfoTitle">
              <p>Name</p>
            </div>
            <div class="userInfoContent">
              <p>이푸름</p> 
            </div>
        </div>
        <div class="userDetailContainer">
            <div class="userInfoTitle">
              <p>Local</p>
            </div>
            <div class="userInfoContent">
              <p>Seoul</p> 
            </div>
        </div>
        <div class="userDetailContainer">
            <div class="userInfoTitle">
              <p>Birth</p>
            </div>
            <div class="userInfoContent">
              <p>1991-02-18</p> 
            </div>
        </div>
        <div class="userDetailContainer">
            <div class="userInfoTitle">
              <p>Gender</p>
            </div>
            <div class="userInfoContent">
              <p>Male</p> 
            </div>
        </div>
        <div class="EditBtn">
          <v-btn
            depressed
            color="indigo"
            @click="InputShow"
          >
            <span>Edit</span>
          </v-btn>
        </div>
        <div class="delBtn">
          <v-btn
              depressed
              color="indigo"
              @click="userDelete"
            >
              <span>Delete</span> 
            </v-btn>
        </div>
      </div>
      <div class="editInputContainer inputHide">
        <div class="inputContainer">
            <div class="inputTitle" style="margin-right: 20px">
              <p>Nick Name</p>
            </div>
            <v-text-field
              ref="nickName"
              label="NickName"
              height="40px"
              outlined

              required
              dense=true
          >
          </v-text-field>
        </div>
        <div class="inputContainer">
            <div class="inputTitle" style="margin-right: 76px">Name</div>
            <v-text-field
              ref="name"
              label="Name"
              height="40px"
              outlined
              required
              dense=true
          >
          </v-text-field>
        </div>
        
        <div class="inputContainer">
            <div class="inputTitle" style="margin-right: 76px">Local</div>
            <v-text-field
              ref="local"
              label="Local"
              height="40px"
              outlined
              required
              dense=true
          >
          </v-text-field>

        </div>
          <div class="inputContainer">
            <div class="inputTitle" style="margin-right: 70px; line-height:70px ">Birth</div>
              <v-menu
                      ref="menu"
                      v-model="menu"
                      :close-on-content-click="false"
                      transition="scale-transition"
                      offset-y
                      min-width="290px"
                  >
                  <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                      v-model="date"
                      label="Birthday date"
                      prepend-icon="mdi-calendar"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                  ></v-text-field>
                  </template>
                  <v-date-picker
                      ref="picker"
                      v-model="date"
                      :max="new Date().toISOString().substr(0, 10)"
                      min="1950-01-01"
                      @change="save"
                  ></v-date-picker>
              </v-menu>
          </div>

          <div class="inputContainer gender">
            <div class="inputTitle" style="margin-right: 76px; line-height: 65px">Gender</div>
              <v-checkbox
                  input-value="true"
                  label="Male"
                  value
                  class="maleCheck"
              ></v-checkbox>
                <v-checkbox
                  input-value="true"
                  label="Female"
                  value
              ></v-checkbox>
          </div>
        <div class="submitBtn">
            <v-btn
              depressed
              color="indigo"
              @click="userDetailSubmit"
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
      nickName: "test",
      date: null,
      menu: false,
    }),
    watch: {
      menu (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
    },
    methods: {
      save (date) {
        this.$refs.menu.save(date)
      },
      InputShow () {
        const userDetailInfo = document.querySelector('.userDetailInfo');
        const inputHide = document.querySelector('.inputHide');
        userDetailInfo.style.display ="none";
        inputHide.style.visibility="visible";
        
      }
    },
  
}
</script>

<style scoped>

/* UserDetailInfo */
.userDetailContainer{
  display: flex;
  margin-top: 20px;
  position: relative;
  
  /* justfico */
}
.userInfoContent{
  width: 250px;
  height: 40px;
  border: 1px solid black;
  /* margin-top: 10px; */
  /* flex-basis: 70%; */
}

.userInfoContent p{
  margin-left: 5px;
  padding-top: 8px;
}

.userInfoTitle{
  margin-top: 10px;
  flex-basis: 20%;
}

.EditBtn span,
.delBtn span,
.submitBtn span{
  color: white;
}

.EditBtn{
  position: absolute;
  margin: 70px 10px 0 100px;
}

.delBtn, .submitBtn{
  margin: 70px 0 0 230px;
}

.submitBtn{

}

.inputHide{
  visibility: hidden;
}
.title{
  padding: 10px 10px 10px 0 ;
  border-bottom: 1px solid black;
  margin-bottom: 50px;
}
.inputContainer{
    margin-top: 35px;
    width: 450px;
    height: 30px;
    display: flex;
}

.inputTitle{
    margin-right: 60px;
    line-height: 40px;
    height: 45px;
    font-size: 13px;
    flex-basis: 10%;
}

.inputTitle p{
  line-height: 50px;
  width: 100px;
}
.inputBox{
    width: 350px;
    height: 40px;
    flex-basis: 95%;
}

.maleCheck{
  margin-right: 40px;
}
.Btns{
  margin-top: 140px;
}
.saveBtn{
    display: block;
    margin-top: 50px;
    margin-left: 212px;
    width: 100px;
    /* margin: auto; */

}
.delAccountBtn{
    display: block;
    margin-top: 50px;
    margin-left: 190px;
    width: 150px;
}
</style>