<template>
    <div class="user-info container pb-5 mb-5">
        <!-- <div class="title">
            <h1>Thông tin người dùng</h1>
        </div> -->
        <div class="main-content d-flex justify-content-center mt-5">
            <div class="left-content col-3">
                <div class="avatar">
                    <img src="../assets/avataruser.png" alt="">
                    <!-- <span><a class="text-white">Chỉnh sửa</a></span> -->
                    <div class="btn text-white " style="background-color:#473C8B ;" data-toggle="modal"
                        data-target="#myModal"> Đổi mật khẩu</div>

                </div>
                <div class="modal" id="myModal">
                    <div class="modal-dialog ">
                        <div class="modal-content p-3 ">
                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Đổi mật khẩu</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <!-- Modal body -->
                            <div class="modal-body">
                                <div class="text-left mt-4">
                                    <h5>Vui lòng nhập mật khẩu hiện tại</h5>
                                    <input @blur="validate()" v-model="changePassword.oldPassword" type="password"
                                        class=" form-control mb-2" placeholder="Enter your password"
                                        style="width: 100%; ">
                                    <span v-show="errors.oldPassword" style="color: red;">{{ errors.oldPassword}}</span>
                                </div>
                                <div class="text-left mt-4">
                                    <h5>Vui lòng nhập mật khẩu mới</h5>
                                    <input @blur="validate()" v-model="changePassword.newPassword" type="password"
                                        class=" form-control mb-2" placeholder="Enter your password"
                                        style="width: 100%; ">
                                    <span v-show="errors.newPassword" style="color: red;">{{ errors.newPassword
                                        }}</span>
                                </div>
                                <div class="text-left mt-4">
                                    <h5>Vui lòng nhập lại mật khẩu mới</h5>
                                    <input @blur="validate()" v-model="repeatPassword" type="password"
                                        class=" form-control mb-2" placeholder="Enter your password"
                                        style="width: 100%; ">
                                    <span v-show="errors.repeatPassword" style="color: red;">{{ errors.repeatPassword
                                        }}</span>
                                </div>
                            </div>
                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <div @click="change()" class="btn text-white" style="background-color: #473C8B; ">
                                    Change</div>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>



            </div>
            <div class="right-content col-9 text-left ">
                <div class="border-bottom d-flex justify-content-center">
                    <h3>Information</h3>
                </div>

                <div class="d-flex mt-4">
                    <div class="col-6 ">
                        <div class="name">
                            <div>
                                <h4>Name</h4>
                            </div>
                            <div>{{user.name}}</div>
                            <div class="mt-5">
                                <h4>Email</h4>
                            </div>
                            <div>{{user.email}}</div>
                            <div class="mt-5">
                                <h4>Phone</h4>
                            </div>
                            <div>{{user.phone}}</div>
                            <div class="mt-5">
                                <h4>Address</h4>
                            </div>
                            <div>{{user.address}}</div>

                        </div>

                    </div>
                    <div class="col-6 ">
                        <div>
                            <h4>User Name</h4>
                        </div>
                        <div>{{user.username}} </div>
                        <div class="mt-5">
                            <h4>Class: </h4>
                            <div v-for="(classItem, index) in userClasses" :key="index" class="mt-2">
                                {{ classItem.name }} <span style="color:green; font-weight: bolder;"
                                    v-if="classItem.status === 0"> Đang hoạt động</span>
                                <span style="color:red; font-weight: bolder;" v-else> Đã dừng họat động</span>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="mt-4">
                <h4>Lịch học</h4>
                <div id="timetable">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th></th>
                                <th v-for="(day, index) in days" :key="index">{{ day }} </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="timeSlot in timeSlots" :key="timeSlot">
                                <td class="col-2">{{ timeSlot }}</td>
                                <td v-for="day in days" :key="day">
                                    <p class="d-flex justify-content-center text-align-center">{{
                                        schedule[timeSlot][day] }}
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</template>
<script>
    export default {
        name: 'UserInfo',
        data() {
            return {
                errors: {
                    oldPassword: '',
                    newPassword: '',
                    repeatPassword: ''
                },
                repeatPassword: '',
                changePassword: {
                    email: '',
                    oldPassword: '',
                    newPassword: ''
                },
                user: {
                    name: '',
                    username: '',
                    email: '',
                    phone: '',
                    address: '',
                    birthday: ''
                },
                userClasses: [],
                days: ['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', "Thứ 7", "Chủ Nhật"],
                timeSlots: ["8:00-10:00", "10:00-12:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00"],
                schedule: []
            }
        },
        created() {
            this.getUser()
            this.schedule = this.timeSlots.reduce((acc, timeSlot) => {
                acc[timeSlot] = {};
                this.days.forEach(day => {

                    acc[timeSlot][day] = "";

                });
                return acc;
            }, {});

        },
        methods: {
            validate() {
                this.errors = {
                    oldPassword: '',
                    newPassword: '',
                    repeatPassword: ''
                }
                if (!this.changePassword.oldPassword) {
                    this.errors.oldPassword = 'Please enter your current password'
                    return false
                }
                if (!this.changePassword.newPassword) {
                    this.errors.newPassword = 'Please enter your new password'
                    return false
                }
                if (!this.repeatPassword) {
                    this.errors.repeatPassword = 'Please repeat your new password'
                    return false
                }
                if (this.changePassword.newPassword !== this.repeatPassword) {
                    this.errors.repeatPassword = 'Repeated password do not match'
                    return false
                }
                return true

            },
            change() {
                if (this.validate()) {
                    this.changePassword.email = this.user.email
                    console.log(this.changePassword)
                    this.$request.post('http://localhost:8080/api/user/change-password', this.changePassword).then((res) => {
                        console.log(res.data)
                        this.$swal.fire("Change password successfully", "now you can login with new password!<br>", "success");

                    }).catch((err) => {
                        this.$swal.fire({
                            icon: "error",
                            title: "Oops...",
                            text: err.response.data,

                        });

                    })

                }
            },
            getUser() {
                var userId = localStorage.getItem('userId');
                // console.log("userid : " + userId);
                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                console.log(headers)
                console.log(userId)

                this.$request.get("http://localhost:8080/api/user/get/" + userId, { headers: headers }).then((response) => {
                    console.log(response.data)
                    this.user = response.data
                    // console.log("user: " + this.user)

                }).catch(err => {
                    // what now?
                    console.log(err);
                })
                this.$request.get(`http://localhost:8080/api/class/get-all-user-classes/` + userId, { headers: headers }).then((response) => {
                    console.log(response.data)
                    // this.userClasses = response.data;
                    this.userClasses = response.data
                    console.log("Get user classes")
                    this.schedule = this.timeSlots.reduce((acc, timeSlot) => {
                        acc[timeSlot] = {};
                        this.days.forEach(day => {
                            const schedule = day + " " + timeSlot
                            const classFound = this.userClasses.filter(function (cls) {
                                return cls.status === 0;
                            }).find(classItem =>
                                classItem.schedule.dayOne === schedule || classItem.schedule.dayTwo === schedule
                            );

                            if (classFound) {
                                acc[timeSlot][day] = classFound.name;
                            }
                        });
                        return acc;
                    }, {});
                }).catch(err => {
                    // what now?
                    console.log(err);
                })

            }
        }

    }
</script>
<style scoped>
    .user-info {
        margin-top: 30px;
        min-height: 700px;

    }

    .left-content {
        background-color: #473C8B;
        height: 500px;
        border-top-left-radius: 20px;
        border-bottom-left-radius: 20px;

    }



    .right-content {
        background-color: #EEEEEE;


        /* height: 500px; */
        border-top-right-radius: 20px;
        border-bottom-right-radius: 20px;
    }

    .avatar img {
        margin-top: 10%;
        margin-bottom: 5%;
        width: 100%;
        border-radius: 100%;
    }

    .title h1 {
        font-weight: bolder;
    }

    h4 {
        font-weight: bolder;
    }

    #timetable th,
    #timetable td {
        width: 150px;
        height: 70px;


        /* Đường viền */
        text-align: center;
        /* Canh giữa nội dung */
    }
</style>