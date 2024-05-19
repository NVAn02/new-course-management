<template>

    <div class="user-manager container pt-5 ">
        <h1>Student Management</h1>
        <div class="d-flex justify-content-end mb-2">
            <div>
                <div class="btn btn-primary" data-toggle="modal" data-target="#addNewUser"> <i class="fas fa-plus"></i>
                    Add New Student
                </div>
                <div class="modal" id="addNewUser">
                    <div class="modal-dialog">
                        <div class="modal-content ">

                            <div class="modal-header">
                                <h4 class="modal-title">Create new user</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="ml-2">
                                <div class="form-outline mb-4 row">
                                    <label class="form-label col-1" for="registerName"><i
                                            class="far fa-user fa-2x"></i></label>

                                    <input @blur="validate()" type="text" v-model="editUser.name" id="registerName"
                                        class="form-control col-9" placeholder="Your Name" />
                                    <p class="ml-5" style="color: red;">{{errors.name}}</p>
                                </div>


                                <div class="form-outline mb-4 row">
                                    <label class="form-label col-1" for="registerUsername"><i
                                            class="fas fa-user fa-2x"></i></label>

                                    <input @blur="validate()" v-model="editUser.username" type="text"
                                        id="registerUsername" class="form-control col-9" placeholder="Your Username" />
                                    <p class="ml-5" style="color: red;">{{errors.username}}</p>
                                </div>


                                <div class="form-outline mb-4 row">
                                    <label class="form-label col-1" for="registerEmail"><i
                                            class="far fa-envelope fa-2x"></i> </label>

                                    <input @blur="validate()" v-model="editUser.email" type="email" id="registerEmail"
                                        class="form-control col-9" placeholder="Your Email" />
                                    <p class="ml-5" style="color: red;">{{errors.email}}</p>
                                </div>


                                <div class="form-outline mb-4 row">
                                    <label class="form-label col-1" for="registerPassword"><i
                                            class="fas fa-unlock-alt fa-2x"> </i></label>

                                    <input @blur="validate()" v-model="editUser.password" type="password"
                                        id="registerPassword" class="form-control col-9" placeholder="Your Password" />
                                    <p class="ml-5" style="color: red;">{{errors.password}}</p>
                                </div>


                                <!-- <div class="form-outline mb-4 row">
                                    <label class="form-label col-1" for="registerRepeatPassword"><i
                                            class="fas fa-unlock-alt fa-2x"></i></label>

                                    <input @blur="validate()" v-model="repeatPassword" type="password"
                                        id="registerRepeatPassword" class="form-control col-11"
                                        placeholder="Repeat Your Password" />
                                </div>
                                <p class="text-left ml-5" style="color: red;">{{errors.repeatPassword}}</p> -->

                            </div>

                            <div class="modal-footer">
                                <div class="btn btn-primary" data-dismiss="modal" @click="register()">Save</div>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>



        </div>
        <!-- <div style="max-height: 600px; overflow-y: auto;"> -->
        <div>
            <!-- <div> -->
            <table class="table" id="datatable">
                <thead class="bg-dark text-white  sticky-header">
                    <tr class="text-left">
                        <th class="col-1">No</th>
                        <th class="col-2">Họ tên </th>
                        <!-- <th>Số điện thoại</th> -->
                        <th class="col-3">Email</th>
                        <th class="col-2">Username</th>
                        <th>Trạng thái</th>
                        <th class="col-3">Hành động</th>
                    </tr>
                </thead>

                <tbody class="text-left">
                    <tr :key="index" v-for="(user,index) in users">
                        <!-- <td>{{ student.id }}</td> -->
                        <td :class="{ 'text-secondary': user.status === 1 }">{{index +1}}</td>
                        <td :class="{ 'text-secondary': user.status === 1 }">{{ user.name }}</td>
                        <!-- <td>{{ user.phone }}</td> -->
                        <td :class="{ 'text-secondary': user.status === 1 }">{{ user.email }}</td>
                        <td :class="{ 'text-secondary': user.status === 1 }">{{ user.username }}</td>
                        <td :class="{ 'text-secondary': user.status === 1 }">
                            <span v-if="user.status === 1"> Disable</span>
                            <span v-if="user.status === 0">Enable</span>
                        </td>
                        <td>
                            <div class="btn btn-primary" data-toggle="modal" data-target="#myModal"
                                @click="getUser(user.id)"> <i class="far fa-edit"></i> Edit
                            </div>
                            <div class="modal" id="myModal">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <!-- Modal Header -->
                                        <div class="modal-header">
                                            <h4 class="modal-title">User Information</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <!-- Modal body -->
                                        <div class=" text-left ">
                                            <div class="d-flex mt-4">
                                                <div class="col-4 ">
                                                    <div class="name">
                                                        <div>
                                                            <h4>Name</h4>
                                                        </div>
                                                        <div>
                                                            <input class="form-control" type="text"
                                                                v-model="editUser.name">
                                                        </div>
                                                        <div class="mt-5">
                                                            <h4>Birthday</h4>
                                                        </div>
                                                        <div>
                                                            <!-- <div><span>{{user.birthday}}</span></div> -->
                                                            <input class="form-control" type="date"
                                                                v-model="editUser.birthday">
                                                        </div>
                                                        <div class="mt-5">
                                                            <h4>Phone</h4>
                                                        </div>
                                                        <div><input class="form-control" type="text"
                                                                v-model="editUser.phone">
                                                        </div>
                                                        <div class="mt-5">
                                                            <h4>Address</h4>
                                                        </div>
                                                        <div><input class="form-control" type="text"
                                                                v-model="editUser.address">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-6 ml-5">
                                                    <div>
                                                        <h4>User Name</h4>
                                                    </div>
                                                    <div><input class="form-control" type="text"
                                                            v-model="editUser.username" readonly>
                                                    </div>
                                                    <div class="mt-5">
                                                        <h4>Email</h4>
                                                    </div>
                                                    <div><input class="form-control" type="text"
                                                            v-model="editUser.email" readonly>
                                                    </div>
                                                    <!-- <div class="mt-5 ">
                                                        <h4> Class: </h4>
                                                        <div v-for="(classItem, index) in userClasses" :key="index"
                                                            class="mt-2">
                                                            {{ classItem.name }}
                                                        </div>
                                                    </div> -->

                                                    <div class="mt-5">
                                                        <h4>Class: </h4>
                                                        <div v-for="(classItem, index) in userClasses" :key="index"
                                                            class="mt-2 d-flex">
                                                            <div class="col-4">
                                                                {{ classItem.name }}
                                                            </div>
                                                            <div>
                                                                <span style="color:green; font-weight: bolder;"
                                                                    v-if="classItem.status === 0"> Đang hoạt
                                                                    động</span>
                                                                <span style="color:red; font-weight: bolder;" v-else> Đã
                                                                    dừng họat động</span>
                                                            </div>

                                                        </div>
                                                    </div>


                                                </div>
                                            </div>
                                        </div>
                                        <!-- Modal footer -->
                                        <div class="modal-footer">
                                            <div class="btn btn-primary" data-dismiss="modal" @click="save()">Save</div>
                                            <button type="button" class="btn btn-danger"
                                                data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            &nbsp;
                            <div class="btn text-white" style="background-color: #A020F0"
                                @click="changeStatus(user.id)"><i class="fas fa-exchange-alt"></i> Change status</div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <pagination v-model="page" :records="pageResponse.totalElements" :per-page="pageResponse.pageSize"
                @paginate="myCallback" />


        </div>

    </div>



</template>
<script>
    import Pagination from 'v-pagination-3';




    export default {


        name: 'UserManager',


        data() {
            return {


                userClasses: [],
                users: [],
                editUser: {
                    id: '',
                    name: '',
                    phone: '',
                    address: '',
                    birthday: '',
                    username: '',
                    email: ''

                },
                errors: {
                    name: '',
                    phone: '',
                    address: '',
                    email: '',
                    birthday: '',
                    password: ''

                },


                pageResponse: {
                    content: '',
                    pageNo: '',
                    pageSize: '',
                    totalElements: 0,
                    totalPages: 0,
                    last: ''
                },
                page: 1
            }
        },
        components: {
            Pagination

        },
        created() {
            // this.getAll()
            this.myCallback(this.page)
        },

        methods: {
            myCallback(newPage) {
                var page = newPage - 1

                console.log("current page", this.page)
                console.log('Page changed to:', page);

                var token = localStorage.getItem('token');
                // console.log("token : " + token);
                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.get('http://localhost:8080/api/user/get?pageNo=' + page, { headers: headers }).then((response) => {
                    this.pageResponse = response.data
                    this.users = response.data.content
                    console.log(response.data)
                })

            },



            changeStatus(id) {
                console.log("user id: " + id)
                var token = localStorage.getItem('token');
                // console.log("token : " + token);
                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$swal.fire({
                    title: "Do you want to change status?",
                    showCancelButton: true,
                    confirmButtonText: "Change",
                }).then((result) => {
                    /* Read more about isConfirmed, isDenied below */
                    if (result.isConfirmed) {
                        this.$request.put(`http://localhost:8080/api/user/change-status/` + id, { headers: headers }).then((res) => {
                            this.myCallback(this.page)
                            console.log(res.data)
                            this.$swal.fire({
                                title: "Change student status successfully",
                                text: "",
                                icon: "success",
                                width: '600px', // Thay đổi giá trị này để điều chỉnh chiều rộng
                            });

                        }).catch((err) => {
                            this.$swal.fire({
                                icon: "error",
                                title: "Oops...",
                                text: err.response.data
                            });
                        });
                    }
                });

            },
            getUser(id) {
                console.log("user id: " + id)
                var token = localStorage.getItem('token');
                // console.log("token : " + token);
                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.get(`http://localhost:8080/api/user/get/` + id, { headers: headers }).then((response) => {
                    console.log(response.data.data)
                    this.editUser = response.data;
                    console.log("Get user detail")
                }
                )
                this.$request.get(`http://localhost:8080/api/class/get-all-user-classes/` + id, { headers: headers }).then((response) => {
                    console.log(response.data)
                    this.userClasses = response.data;
                    console.log("Get user classes")
                }
                )

            },

            save() {
                console.log("Update User id : " + this.editUser.id)
                console.log(this.editUser)


                var token = localStorage.getItem('token');
                // console.log("token : " + token);
                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.put("http://localhost:8080/api/user/update?id=" + this.editUser.id, this.editUser, { headers: headers }).then((response) => {

                    console.log(response.data)
                    this.myCallback(this.page)
                    this.$swal.fire("Updated! ", "", "success")

                }).catch(err => {
                    // what now?
                    console.log(err);
                })

            },
            validate() {
                this.errors = {
                    name: '',
                    username: '',
                    email: '',
                    password: '',
                    repeatPassword: ''

                }
                if (!this.editUser.name) {
                    this.errors.name = "Name is require"
                    return false
                }
                if (!this.editUser.username) {
                    this.errors.username = "Username is require"
                    return false
                }
                if (!this.editUser.email) {
                    this.errors.email = "Email is require"
                    return false
                }
                if (!this.editUser.password) {
                    this.errors.password = "Password is require"
                    return false
                }
                // if (!this.user.repeatPassword) {
                //     this.errors.repeatPassword = "Repeat Password is require"
                //     return false
                // }
                // if (this.repeatPassword !== this.user.password && this.repeatPassword !== '' && this.user.password !== '') {
                //     this.errors.repeatPassword = "Repeat password do not match"
                //     return false
                // }

                return true
            },
            register() {
                console.log("Register")
                if (this.validate()) {
                    console.log("User: ", this.editUser)
                    this.$request.post("http://localhost:8080/api/auth/signup", this.editUser).then(res => {
                        console.log(res);

                        this.$swal.fire("Created new user", "", "success")
                        this.myCallback(this.page)


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

        },

    }
</script>
<style scoped>
    .modal-content {
        width: 900px;
        margin: auto;
    }

    input {
        border: none;
        /* Nếu bạn muốn xóa cả viền đường kẻ bên dưới khi input có focus */
        border-bottom: none;
        /* Nếu bạn muốn xóa viền khi input có focus */
        outline: none;
    }

    .sticky-header {
        position: sticky;
        top: 0;

        /* Bạn có thể thay đổi màu nền của phần thead tại đây */
    }
</style>