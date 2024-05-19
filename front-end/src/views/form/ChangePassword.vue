<template>
    <div class="overlay" v-if="showSpinner"></div>
    <div class="spinner" v-if="showSpinner">

        <img src="@/assets/ajax-loader.gif" alt="Loading...">
    </div>
    <div class="change-password container-fluid d-flex justify-content-center align-item-center"
        style="background-color: #EEEEEE;">
        <div class=" forgot-password-form  col-4" style="background-color: white; border-radius: 20px; ">

            <div class="mt-2" style="border-bottom: 1px solid #EEEEEE;">
                <h3> Đặt lại mật khẩu</h3>
            </div>
            <div class="text-left mt-4">
                <h5>Vui lòng nhập mã OTP.</h5>
                <input @blur="validate()" v-model="changePassword.otp" type="text" class=" form-control "
                    placeholder="Enter OTP code" style="width: 100%;">
                <span v-show="errors.otp" style="color: red;">{{errors.otp}}</span>
            </div>
            <div class="text-left mt-4">
                <h5>Vui lòng nhập mật khẩu</h5>
                <input @blur="validate()" v-model="changePassword.password" type="password" class=" form-control mb-2"
                    placeholder="Enter your password" style="width: 100%; ">
                <span v-show="errors.password" style="color: red;">{{errors.password}}</span>
            </div>
            <div class="text-left mt-4">
                <h5>Vui lòng nhập lại mật khẩu</h5>
                <input @blur="validate()" v-model="repeatPassword" type="password" class=" form-control mb-2"
                    placeholder="Repeat your password" style="width: 100%; ">
                <span v-show="errors.repeatPassword" style="color: red;">{{errors.repeatPassword}}</span>

            </div>
            <div class="d-flex justify-content-end mt-4 mb-4">
                <router-link to="/forgot-password" class="btn "
                    style="background-color: #EEEEEE; color:black">Hủy</router-link>
                <div @click="change()" class="btn ml-2 text-white" style="background-color: #473C8B;">Reset password
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        name: "ForgotPassword",
        data() {
            return {
                showSpinner: false,
                repeatPassword: '',
                changePassword: {
                    otp: '',
                    password: '',
                    email: ''

                },
                errors: {
                    otp: '',
                    password: '',
                    repeatPassword: ''
                }


            }
        },
        created() {
            this.changePassword.email = this.$route.params.email
            console.log(this.changePassword.email)

        },
        methods: {
            validate() {
                this.errors = {
                    otp: '',
                    password: '',
                    repeatPassword: ''
                }
                if (!this.changePassword.otp) {
                    this.errors.otp = 'OTP is required'
                    return false
                }
                if (!/^\d{6}$/.test(this.changePassword.otp)) {
                    this.errors.otp = 'OTP must be a 6-digit number';
                    return false;
                }
                if (!this.changePassword.password) {
                    this.errors.password = 'Password is required'
                    return false
                }
                if (!this.repeatPassword) {
                    this.errors.repeatPassword = 'Please repeat your password'
                    return false
                }
                if (this.changePassword.password !== this.repeatPassword) {
                    this.errors.repeatPassword = 'Repeated password do not match'
                    return false
                }
                return true
            },
            change() {
                console.log("reset password")
                if (this.validate()) {
                    this.showSpinner = true
                    console.log(this.changePassword)
                    this.$request.post('http://localhost:8080/api/user/reset-password', this.changePassword).then((res) => {
                        console.log(res.data)
                        this.showSpinner = false
                        this.$swal.fire("Reset password successfully", "now you can login with new password!<br>", "success");
                        this.$router.push('/login')
                    }).catch((err) => {
                        console.log(err)
                        this.showSpinner = false
                        this.$swal.fire({
                            icon: "error",
                            title: "Oops...",
                            text: err.response.data,

                        });
                    })
                }

            }
        }
    }
</script>
<style scoped>
    .change-password {

        padding-top: 100px;
        padding-bottom: 250px;
    }
</style>