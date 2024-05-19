<template>
    <div class="signup d-flex justify-content-center container-fluid pb-5 mb-5">
        <div class="col-5">

            <form>
                <div class="text-center mb-3">
                    <h1>Sign up with:</h1>
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
                <div>
                    <div>

                        <!-- Name input -->
                        <div class="form-outline mb-4 row align-items-center">
                            <label class="form-label text-left col-1" for="registerName"><i
                                    class="far fa-user fa-2x"></i></label>
                            <input @blur="validate()" type="text" v-model="user.name" id="registerName"
                                class="form-control col-11" placeholder="Your Name" />
                            <p class="ml-5" style="color: red;">{{errors.name}}</p>
                        </div>

                        <!-- Username input -->
                        <div class="form-outline mb-4 row">
                            <label class="form-label col-1" for="registerUsername"><i
                                    class="fas fa-user fa-2x"></i></label>

                            <input @blur="validate()" v-model="user.username" type="text" id="registerUsername"
                                class="form-control col-11" placeholder="Your Username" />
                            <p class="ml-5" style="color: red;">{{errors.username}}</p>
                        </div>

                        <!-- Email input -->
                        <div class="form-outline mb-4 row">
                            <label class="form-label col-1" for="registerEmail"><i
                                    class="far fa-envelope fa-2x"></i></label>

                            <input @blur="validate()" v-model="user.email" type="email" id="registerEmail"
                                class="form-control col-11" placeholder="Your Email" />
                            <p class="ml-5" style="color: red;">{{errors.email}}</p>
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-4 row">
                            <label class="form-label col-1" for="registerPassword"><i
                                    class="fas fa-unlock-alt fa-2x"></i></label>

                            <input @blur="validate()" v-model="user.password" type="password" id="registerPassword"
                                class="form-control col-11" placeholder="Your Password" />
                            <p class="ml-5" style="color: red;">{{errors.password}}</p>
                        </div>

                        <!-- Repeat Password input -->
                        <div class="form-outline mb-4 row">
                            <label class="form-label col-1" for="registerRepeatPassword"><i
                                    class="fas fa-unlock-alt fa-2x"></i></label>

                            <input @blur="validate()" v-model="repeatPassword" type="password"
                                id="registerRepeatPassword" class="form-control col-11"
                                placeholder="Repeat Your Password" />
                        </div>
                        <p class="text-left ml-5" style="color: red;">{{errors.repeatPassword}}</p>
                        <!-- <p class="text-left ml-5 " v-if="!passwordsMatch" style="color: red;">Password do not match!</p> -->
                    </div>

                    <!-- Checkbox -->
                    <div class="form-check mb-4 row ">
                        <label class="form-check-label col-6" for="registerCheck">
                            I have read and agree to the terms
                        </label>
                        <input @blur="validate()" class="form-check-input me-2 col-2" type="checkbox" value=""
                            id="registerCheck" checked aria-describedby="registerCheckHelpText" />
                    </div>
                </div>


                <!-- Submit button -->
                <div class="ml-5">

                    <div>
                        <button @click="register" type="submit" class="btn btn-block mb-3 text-white"
                            style="background-color: #473C8B;">Sign
                            up</button>
                    </div>
                    <div class="text-center">
                        <p>Already have account? <router-link to="/login"> Sign in</router-link></p>
                    </div>
                </div>


            </form>
            <!-- <div>
                <label>Subjects:</label>
                <div v-for="subject in subjects" :key="subject.id">
                    <input type="checkbox" :id="'subject_' + subject.id" :value="subject.id" v-model="selectedSubjects">
                    <label :for="'subject_' + subject.id">{{ subject.name }}</label>
                </div>
                <button class="btn" @click="show">Create Course</button>

            </div> -->
            <!-- <div>
                <div>
                    <h2>Text Area</h2>
                    <textarea v-model="text" @input="processText"></textarea>
                    <div v-if="lines.length > 0">
                        <h3>Lines:</h3>
                        <ul>
                            <li v-for="(line, index) in lines" :key="index">{{ line }}</li>
                        </ul>
                    </div>
                </div>
            </div> -->

        </div>
    </div>
</template>
<script>
    export default {
        name: 'register-form',
        data() {
            return {
                // text: '',
                // lines: [],
                subjects: [
                    {
                        id: 1,
                        name: "Java Script"
                    },
                    {
                        id: 2,
                        name: "Vuejs"
                    },
                    {
                        id: 3,
                        name: "Java"
                    },

                ],
                selectedSubjects: [],
                repeatPassword: '',
                user: {
                    name: '',
                    username: '',
                    password: '',
                    email: '',
                    // phone: ''
                },
                errors: {
                    name: '',
                    username: '',
                    password: '',
                    repeatPassword: '',
                    email: ''

                },
            }
        },
        methods: {
            register() {
                console.log("Register")
                if (this.validate()) {
                    console.log("User: " + this.user)
                    this.$request.post("http://localhost:8080/api/auth/signup", this.user).then(res => {
                        console.log(res);

                        this.$swal.fire("Registed", "", "success")
                        this.$router.push('/')

                    }).catch((err) => {
                        console.log(err);

                        this.$swal.fire({
                            icon: "error",
                            title: "Oops...",
                            text: err.response.data.message,

                        });
                    });

                }




            },
            validate() {
                this.errors = {
                    name: '',
                    username: '',
                    email: '',
                    password: '',
                    repeatPassword: ''

                }
                if (!this.user.name) {
                    this.errors.name = "Name is require"
                    return false
                }
                if (!this.user.username) {
                    this.errors.username = "Username is require"
                    return false
                }
                if (!this.user.email) {
                    this.errors.email = "Email is require"
                    return false
                }
                if (!this.user.password) {
                    this.errors.password = "Password is require"
                    return false
                }
                // if (!this.user.repeatPassword) {
                //     this.errors.repeatPassword = "Repeat Password is require"
                //     return false
                // }
                if (this.repeatPassword !== this.user.password && this.repeatPassword !== '' && this.user.password !== '') {
                    this.errors.repeatPassword = "Repeat password do not match"
                    return false
                }

                return true
            },
            show() {
                console.log(this.selectedSubjects)
                console.log(this.lines)
            }, processText() {
                // Chia chuỗi thành các dòng bằng dấu xuống dòng
                this.lines = this.text.split('\n');
            }

        },

        // computed: {
        //     passwordsMatch() {
        //         return this.user.password === this.repeatPassword;
        //     }
        // }

    }
</script>
<style scoped>
    .signup {

        margin-top: 100px;
        min-height: 600px;
    }
</style>