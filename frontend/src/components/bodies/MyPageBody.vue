<template>
  <div class="profile">
        <h1 class="profile__title">Profile</h1>
        <h2 class="profile__subTitle">Manage profile</h2>
        <div class="profileContent">
            <div class="userProfile">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis enim ratione nostrum vitae itaque quisquam, bland
                   
                </p>
            </div>
            <div class="editProfileBtns">
                <v-btn depressed class="editBtn"
                :disabled="!didEdit"
                @click="emailFormShow">
                    Edit
                </v-btn>
                <v-btn depressed class="editBtn"
                :disabled="!didpwdChange"
                 @click="pwdFormShow">
                    change Password
                </v-btn>
            </div>
        </div>
        <div class="editEmail show">
            <div class="editEmail__container">
                <div class="emailInput__container">
                    <div class="emailInput__title">Email</div>
                    <v-text-field
                        label="Email Address"
                        outlined
                        class="emailInput"
                    ></v-text-field>
                    <div class="emailInput__title">Current Password</div>
                    <v-text-field
                        label="Current Password"
                        outlined
                        class="emailInput"
                    ></v-text-field>
                </div>
                <div class="editProfileBtns">
                    <v-btn depressed class="editBtn">
                        Save
                    </v-btn>
                    <v-btn depressed class="editBtn" @click="emailFormCancle">
                        Cancle
                    </v-btn>
                </div>
            </div>
        </div>
        <div class="changePwd show">
            <div class="editEmail__container">
                <div class="changePwd__container">
                    <div class="emailInput__title">Current Password</div>
                    <v-text-field
                        label="Current Password"
                        outlined
                        class="emailInput"
                    ></v-text-field>
                    <div class="emailInput__title">New Password</div>
                    <v-text-field
                        label="New Password"
                        outlined
                        type="password"
                        class="emailInput"
                        v-model="newPassword"
                        :rules="passwordRules"
                        required
                    ></v-text-field>
                    <div class="emailInput__title">Confirm Password</div>
                    <v-text-field
                        label="Confirm Password"
                        type="password"
                        outlined
                        class="emailInput"
                        v-model="confirmPassword"
                        :rules="[confirmRule]"
                        :error-messages="errorMessages"
                        required
                    ></v-text-field>
                </div>
                <div class="editProfileBtns">
                    <v-btn depressed class="editBtn"
                    :disabled="didConfirmPassword"
                    >
                        Save
                    </v-btn>
                    <v-btn depressed class="editBtn" @click="pwdFormCancle">
                        Cancle
                    </v-btn>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data: () => ({
        didEdit: true,
        didpwdChange: true,
        didConfirmPassword: false,
        newPassword: '',
        confirmPassword: '',
        passwordRules: [
            v => !!v || 'Password is required',
            v => (v.length >= 8 && /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test(v)) || 'Use 8 or more characters with a mix of letters, numbers & symbols'
        ],
        errorMessages: ''
    }),
    methods: {
        emailFormShow () {
            const emailForm = document.querySelector('.editEmail');
            emailForm.classList.remove('show')
            this.didEdit = false
        },
        emailFormCancle () {
            const emailForm = document.querySelector('.editEmail');
            emailForm.classList.add('show')
            this.didEdit = true
        },
        pwdFormShow () {
            const changePwdForm = document.querySelector('.changePwd');
            changePwdForm.classList.remove('show')
            this.didpwdChange = false
        },
        pwdFormCancle () {
            const changePwdForm = document.querySelector('.changePwd');
            changePwdForm.classList.add('show')
            this.didpwdChange = true
        },
     // password와 confirmPassword가 일치하는지 확인하는 메소드
        confirmRule () {
            this.errorMessages = this.newPassword === this.confirmPassword ? '' : "Those passwords didn't match"
            if(this.newPassword === this.confirmPassword){
                this.didConfirmPassword = true;
            }
            return this.newPassword === this.confirmPassword
        },
    }
}
</script>

<style scoped>
.show{
    display: none;
}
.profile__title{
    font-weight: 300;
    font-size: 50px;
    line-height: 56px;
    margin-bottom: 24px;
}
.profile__subTitle{
    border-bottom: 1px solid #bdbdbd;
    font-weight: 700;
    font-size: 17px;
    margin-bottom: 9px;
}
.profileContent, .editEmail__container{
    display: grid;
    grid-template-columns: 3fr 1fr;
    margin-top: 30px;
}

.userProfile{
    padding-top: 10px;
    width: 490px;
}
button{
    display: block;
    padding: 10px;
    margin-top: 10px;
}

.editProfileBtns{
    justify-self: end;
}

.editBtn{
    width: 180px;
    height: 17px;
}
.editEmail, .changePwd{
    margin-top: 100px;
    border-top: 1px solid black;
}

.emailInput{
    width:320px;
}

</style>