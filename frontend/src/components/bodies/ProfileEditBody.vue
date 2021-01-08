<template>
  <div class="ProfileEditComponent">
      <h1 class="title">Profile Detail</h1>
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
        <div class="Btns">
          <v-btn depressed
          class="saveBtn">
              Save
          </v-btn>
          <v-btn depressed
          class="delAccountBtn">
              Delete Account
          </v-btn>
        </div>

  </div>
</template>

<script>
export default {
    data: () => ({
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
    },
  
}
</script>

<style scoped>
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