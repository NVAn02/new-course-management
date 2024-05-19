<template>
    <div class="login d-flex justify-content-center container-fluid">
        <div class="col-4">

            <form @submit.prevent='login()'>
                <div class="text-center mb-3">
                    <h1>Sign in with:</h1>
                    <button type="button" class="btn btn-link btn-floating mx-1">
                        <i class="fab fa-facebook-f"></i>
                    </button>

                    <button type="button" class="btn btn-link btn-floating mx-1">
                        <i class="fab fa-google"></i>
                    </button>

                    <button type="button" class="btn btn-link btn-floating mx-1">
                        <i class="fab fa-twitter"></i>
                    </button>

                    <button type="button" class="btn btn-link btn-floating mx-1">
                        <i class="fab fa-github"></i>
                    </button>
                </div>

                <p class="text-center">or:</p>

                <!-- Email input -->
                <div class="form-outline mb-4 text-left">
                    <label class="form-label" for="loginName">Username: </label>

                    <input @blur="validate()" v-model="user.username" type="text" id="loginName" class="form-control"
                        placeholder="Your user name or email" />
                    <p style="color: red;">{{errors.usernameOrEmail}}</p>
                </div>

                <!-- Password input -->
                <div class="form-outline mb-4 text-left">
                    <label class="form-label " for="loginPassword">Password: </label>

                    <input @blur="validate()" v-model="user.password" type="password" id="loginPassword"
                        class="form-control" placeholder="Your password" />
                    <p style="color: red;">{{errors.password}}</p>
                </div>

                <!-- 2 column grid layout -->
                <div class="row mb-4">
                    <div class="col-md-6 d-flex justify-content-center">
                        <!-- Checkbox -->
                        <div class="form-check mb-3 mb-md-0">
                            <input class="form-check-input" type="checkbox" value="" id="loginCheck" checked />
                            <label class="form-check-label" for="loginCheck"> Remember me </label>
                        </div>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center">
                        <!-- Simple link -->
                        <router-link to="/forgot-password">Forgot password?</router-link>
                    </div>
                </div>

                <!-- Submit button -->
                <button type="submit" class="btn btn-block mb-4 text-white" style="background-color: #473C8B;">Sign
                    in</button>

                <!-- Register buttons -->
                <div class="text-center">
                    <p>Not a member? <router-link to="/register"> Register</router-link></p>
                </div>
            </form>
        </div>
    </div>
</template>
<script>
    export default {
        name: 'login-form',
        data() {
            return {
                user: {
                    username: '',
                    password: ''
                },
                errors: {

                    usernameOrEmail: '',
                    password: ''


                },
            }
        },
        methods: {
            login() {
                if (this.validate()) {
                    console.log(this.user)


                    this.$request.post("http://localhost:8080/api/auth/signin", this.user).then(res => {
                        console.log(res)

                        localStorage.setItem('userId', res.data.id);
                        localStorage.setItem('username', res.data.username);
                        console.log("Username: " + localStorage.getItem('username'))

                        localStorage.setItem('token', res.data.token);

                        // localStorage.setItem('role', res.data.roles[0])
                        sessionStorage.setItem('role', res.data.roles[0])
                        console.log("ROLE: " + res.data.roles[0])
                        this.$store.commit('login')
                        console.log(this.$store.state.isLogin)
                        this.$swal.fire("Login", "", "success")
                        if (res.data.roles[0] === 'ROLE_ADMIN') {
                            this.$router.push('/admin')
                        } else {
                            this.$router.push('/mycourse')

                        }
                    }).catch((err) => {
                        console.log(err);

                        this.$swal.fire({
                            icon: "error",
                            title: "Oops...",
                            text: "Thông tin đăng nhập không chính xác",

                        });
                    });
                }

            },
            validate() {
                this.errors = {
                    usernameOrEmail: '',
                    password: '',

                }
                if (!this.user.username) {
                    console.log("Username is require")
                    this.errors.usernameOrEmail = "Username is require"
                    return false
                }
                if (!this.user.password) {
                    console.log("Password is require")
                    this.errors.password = "Password is require"
                    return false
                }
                return true
            }
        }
    }
</script>
<style scoped>
    .gradient-custom-2 {
        /* fallback for old browsers */
        background: #fccb90;

        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);

        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
    }

    @media (min-width: 768px) {
        .gradient-form {
            height: 100vh !important;
        }
    }

    @media (min-width: 769px) {
        .gradient-custom-2 {
            border-top-right-radius: .3rem;
            border-bottom-right-radius: .3rem;
        }
    }

    .login {
        margin-top: 100px;
        min-height: 700px;
    }
</style>