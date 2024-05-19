<template>
    <div class="nav-bar">
        <div>
            <nav v-if="isLogin" class="navbar navbar-expand-sm " style="background-color:WhiteSmoke;">

                <img v-if="!isAdmin" alt="Vue logo" class="nav-brand" src="../assets/logo.png" style="width:40px">

                <ul class="navbar-nav justify-content-start ml-5 col-5">
                    <div v-if="!isAdmin" class="d-flex align-items-center"
                        style="font-size: large; font-weight: bolder;">
                        <li class="nav-item mr-1 h-100">
                            <router-link to="/mycourse" class=" h-100 d-flex align-items-center"><a>QUẢN LÝ
                                    KHÓA HỌC</a></router-link>
                        </li>
                    </div>
                </ul>
                <div class="col-4">
                    <div class="form">
                        <i class="fa fa-search"></i>
                        <input type="text" class="form-control form-input" placeholder="Search anything..."
                            style="border-radius: 40px;">
                    </div>
                </div>
                <div class="col-3 user-info d-flex align-items-center ">
                    <div class="mr-3 ">Welcome {{ username}}</div>
                    <div class="btn bg-white " style="border-radius: 100%;" data-toggle="dropdown"> <i
                            class="far fa-user fa-2x"></i>
                    </div>
                    <div class="dropdown-menu">
                        <router-link to="/userinfo" class="dropdown-item">Thông tin tài khoản</router-link>
                        <router-link v-if="isAdmin" to="/mail-template" class="dropdown-item">Mail
                            template</router-link>
                        <router-link v-if="isAdmin" to="/mail-account" class="dropdown-item">
                            Email Account</router-link>
                        <a class="dropdown-item" @click="logout()">Đăng xuất</a>
                    </div>
                </div>

            </nav>
        </div>




        <!-- <nav v-show="!isAdmin" class="navbar navbar-expand-sm bg-dark fixed-top"> -->
        <nav v-if="!isLogin" class="navbar navbar-expand-sm  fixed-top" style="background-color:WhiteSmoke;">
            <router-link to="/">
                <img alt="Vue logo" class="nav-brand"
                    src="../assets/logo-edu.png" style="width:160px">


            </router-link>
            <ul class="navbar-nav d-flex align-items-center  col-5" style="font-size: large; font-weight:100;">
                <!-- <div class="col-12 " style="font-size: large; font-weight: bolder;"> -->
                <li class="nav-item  h-100">
                    <router-link to="/" class=" h-100 d-flex align-items-center"><a>Home</a></router-link>
                </li>
                <!-- <li class="nav-item  h-100">
                    <router-link to="/" class=" h-100 d-flex align-items-center"><a>Browse
                            Course</a></router-link>
                </li> -->
                <li class="nav-item  h-100">
                    <router-link to="/" class="h-100 d-flex align-items-center"><a>About</a></router-link>
                </li>
                <li class="nav-item  h-100">
                    <router-link to="/" class=" h-100 d-flex align-items-center"><a>Blog</a></router-link>
                </li>
                <li class="nav-item  h-100">
                    <router-link to="/" class=" h-100 d-flex align-items-center"><a>Contact</a></router-link>
                </li>


                <!-- </div> -->
            </ul>
            <div class="col-4 mr-5">
                <div class="form">
                    <i class="fa fa-search"></i>
                    <input type="text" class="form-control form-input" placeholder="Search anything..."
                        style="border-radius: 40px;">
                </div>
            </div>

            <div v-if="!isLogin" class="col-1 d-flex justify-content-end">
                <router-link to="/login">
                    <div class="loggin-btn btn text-white d-flex justify-content-center align-items-center  "
                        style="background-color:#473C8B ; width: 100px;height: 50px; ">
                        <span>Sign in</span>
                    </div>
                </router-link>
            </div>
        </nav>





    </div>
</template>
<script>



    export default {
        name: 'HeaderComponent',


        data() {
            return {


            }
        },
        computed: {
            isLogin() {
                console.log("isLogin: " + this.$store.state.isLogin)
                return this.$store.state.isLogin
            },
            username() {
                // console.log("username in localstorage: " + localStorage.getItem('username'))
                // console.log("user name is: " + this.$store.state.username)
                return this.$store.state.username
            },
            isAdmin() {
                // console.log("role in localStorage: " + localStorage.getItem('role'))
                console.log("isRoleAdmin : " + this.$store.state.isRoleAdmin)
                return this.$store.state.isRoleAdmin
            }

        },

        methods: {
            logout() {

                this.$swal.fire({
                    title: "Do you want to log out?",

                    icon: "warning",

                    confirmButtonColor: "#d33",

                    confirmButtonText: "Log out"
                }).then((result) => {
                    if (result.isConfirmed) {
                        sessionStorage.clear()
                        localStorage.clear()
                        this.$store.commit('logout')
                        this.$swal.fire({
                            text: "Signed out!",
                            icon: "success"
                        });

                        this.$router.push('/login')
                    }
                });

            }
        }

    }
</script>
<style scoped>
    .nav-item {
        font-size: 23px;
    }

    /* .login-content {
        position: relative;
        left: 200px;

    } */

    nav a {

        font-weight: 550;

        color: #555555
    }


    .nav-item {
        transition: all 0.3s ease;
    }

    .nav-item:hover {
        transform: translateY(-3px);
        border-bottom: 1px solid;
    }

    a {
        text-decoration: none;
    }

    /* .search-btn {
        background-color: #A020F0;
        color: white;
    } */

    /* .loggin-btn :hover,
    .register-btn :hover {
        color: #A020F0;

    } */

    .dropdown-item:hover {
        background-color: #DCDCDC;
    }

    .navbar {
        background-color: #333;
        /* Màu nền của navbar */
        padding: 10px;
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.3);
        /* Đổ bóng nhẹ */
    }

    .loggin-btn {
        position: relative;
        display: inline-block;
    }

    /* .loggin-btn::after {
        content: '';
        position: absolute;
        left: 0;
        bottom: -3px;
        width: 100%;
        border-bottom: 1px solid transparent;
        transition: border-color 0.3s ease;
    }

    .loggin-btn:hover::after {
        border-color: #A020F0;
    } */


    .form {

        position: relative;
    }

    .form .fa-search {

        position: absolute;
        top: 20px;
        left: 20px;
        color: #9ca3af;

    }

    .form span {

        position: absolute;
        right: 17px;
        top: 13px;
        padding: 2px;
        border-left: 1px solid #d1d5db;

    }

    /* .left-pan {
        padding-left: 7px;
    }

    .left-pan i {

        padding-left: 10px;
    } */

    .form-input {

        height: 55px;
        text-indent: 33px;
        border-radius: 10px;
    }

    .form-input:focus {

        box-shadow: none;
        border: none;
    }

    .btn {
        border-radius: 10px;
    }
</style>