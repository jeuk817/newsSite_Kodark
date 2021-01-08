<template>
  <div class="ProfileEditComponent">
      <h1 class="title">Profile Detail</h1>
      <div class="userDetailInfo">
        <div class="userDetailContainer">
            <div class="userInfoTitle" style="margin-right: 20px">
              <p>Nick Name</p>
            </div>
            <div class="userInfoContent">
              <p>닉네임입니다</p> 
            </div>
            <div class="EditBtn">
                <v-btn
                  depressed
                  color="indigo"
                  @click="InputShow"
                >
                  Edit
                </v-btn>
            </div>
        </div>
        <div class="userDetailContainer">
            <div class="userInfoTitle" style="margin-right: 20px">
              <p>Name</p>
            </div>
            <div class="userInfoContent">
              <p>이푸름</p> 
            </div>
        </div>
        <div class="userDetailContainer">
            <div class="userInfoTitle" style="margin-right: 20px">
              <p>Local</p>
            </div>
            <div class="userInfoContent">
              <p>Seoul</p> 
            </div>
        </div>
        <div class="userDetailContainer">
            <div class="userInfoTitle" style="margin-right: 20px">
              <p>Birth</p>
            </div>
            <div class="userInfoContent">
              <p>1991-02-18</p> 
            </div>
        </div>
        <div class="userDetailContainer">
            <div class="userInfoTitle" style="margin-right: 20px">
              <p>Gender</p>
            </div>
            <div class="userInfoContent">
              <p>Male</p> 
            </div>
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
              v-model="nickName"
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
              v-model="nickName"
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
              v-model="nickName"
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
      </div>
  </div>

        <!-- <div class="Btns">
          <v-btn depressed
          class="saveBtn">
              Save
          </v-btn>
          <v-btn depressed
          class="delAccountBtn">
              Delete Account
          </v-btn>
         -->
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
  margin-top: 10px;
  flex-basis: 70%;
}
.userInfoTitle{
  margin-top: 10px;
  flex-basis: 30%;
}
.EditBtn{
  position: absolute;
  right: 300px;
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