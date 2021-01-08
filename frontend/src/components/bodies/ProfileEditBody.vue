<template>
  <div class="ProfileEditComponent">
      <h1 class="title">Profile Detail</h1>
      <div class="inputContainer">
          <div class="inputTitle">닉네임</div>
          <v-text-field
            ref="nickName"
            label="NickName"
            height="45px"
            outlined
            v-model="nickName"
            required
            dense=true
        >
        </v-text-field>
      </div>
      <div class="inputContainer">
          <div class="inputTitle" style="margin-right: 76px">이름</div>
          <v-text-field
            ref="name"
            label="Name"
            height="45px"
            
            outlined
            v-model="nickName"
            required
            dense=true
        >
        </v-text-field>
      </div>
      <div class="inputContainer">
          <div class="inputTitle" style="margin-right: 76px">지역</div>
          <v-text-field
            ref="local"
            label="Local"
            height="45px"
            outlined
            v-model="nickName"
            required
            dense=true
        >
        </v-text-field>
      </div>
        <div class="inputContainer">
          <div class="inputTitle" style="margin-right: 40px">생년월일</div>
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

        <div class="inputContainer">
          <div class="inputTitle" style="margin-right: 76px; line-height: 65px">성별</div>
            <v-checkbox
                input-value="true"
                label="Male"
                value
            ></v-checkbox>
               <v-checkbox
                input-value="true"
                label="Female"
                value
            ></v-checkbox>
        </div>
        <v-btn depressed
        class="saveBtn">
            Save
        </v-btn>
        <v-btn depressed
        class="delAccountBtn">
            Delete Account
        </v-btn>

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

.inputContainer{
    margin-top: 35px;
    width: 450px;
    height: 30px;
    display: flex;
}
.inputTitle{
    margin-right: 60px;
    line-height: 45px;
    height: 45px;
}
.inputBox{
    width: 350px;
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