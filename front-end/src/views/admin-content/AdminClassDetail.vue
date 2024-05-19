<template>
    <div class="container pt-5">
        <div class="overlay" v-if="showSpinner"></div>
        <div class="spinner" v-if="showSpinner">
            <!-- Thêm ảnh GIF spinner hoặc nội dung spinner vào đây -->
            <img src="@/assets/ajax-loader.gif" alt="Loading...">
        </div>

        <h1> Class Detail</h1>
        <!-- <h4>Class id: {{classId}}</h4> -->
        <div id="classInfor">
            <div class="d-flex text-left mt-4">
                <div class="col-4 mr-5 d-flex">
                    <div class="mb-2">
                        <div>
                            <h4>Name:</h4>
                        </div>
                        <input type="text" class="form-control" v-model="courseClass.name">
                    </div>
                    <div class="mb-2">
                        <div>
                            <h4>Opening Day:</h4>
                        </div>
                        <input type="date" class="form-control" v-model="courseClass.openingDay">
                    </div>


                </div>
                <div class="col-6 d-flex">

                    <div class="mb-2">
                        <div>
                            <h4>Schedule:</h4>
                        </div>
                        <div>
                            <p>{{ courseClass.schedule.id }}. {{ courseClass.schedule.dayOne }} -
                                {{ courseClass.schedule.dayTwo }}</p>
                        </div>
                    </div>

                </div>
            </div>
            <div class="mb-2 d-flex ml-2">
                <div>
                    <h4>Teacher:</h4>
                </div>
                <!-- <input type="text" class="form-control" v-model="courseClass.teacher.name"> -->
                <div class="select-teacher ml-2 col-4">
                    <select class="form-control" name="teacher" id="teacher" v-model="courseClass.teacher">
                        <option value="" disabled selected style="text-align: center;">{{ courseClass.teacher.name }}
                        </option>
                        <option :value="teacher" v-for="(teacher, index) in availableTeacher" :key="index">
                            {{ teacher.name
                            }}
                        </option>
                    </select>
                </div>
            </div>
            <div class="col-6 d-flex justify-content-start mb-3">

                <div class="btn btn-primary" @click="save"><i class="fas fa-bookmark"></i> Save</div>

            </div>
        </div>

        <div class="mt-5">
            <h4>Danh sách học sinh</h4>
            <div class="d-flex justify-content-end ">
                <div class="btn btn-primary mb-2" @click="getUserRegister()" data-toggle="modal"
                    data-target="#userRegister">
                    <i class="fas fa-plus"></i> Thêm học sinh
                </div>
                <div class="modal" id="userRegister">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Danh sách đăng kí</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body" style="max-height: 300px; overflow-y: auto;">
                                <div class=" d-flex justify-content-center" v-for="(register, index) in registerList "
                                    :key="index">
                                    <div class="col-6 text-left">
                                        {{index + 1}}. {{register.user.name}}
                                    </div>
                                    <div class="col-3 text-left">
                                        {{ register.user.username }}
                                    </div>
                                    <div class="col-2">
                                        <input style=" transform: scale(1.6)" type="checkbox" :id="'checkbox' + index"
                                            :value="register.id" v-model="selectUserIds">
                                    </div>

                                </div>


                            </div>
                            <div class="modal-footer">
                                <div class="btn btn-primary" @click="addUsers()" data-dismiss="modal"> Save</div>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table">
                <thead class="bg-dark text-white">
                    <tr class="text-left">
                        <th>No</th>
                        <th>Tên học sinh</th>
                        <th>Email</th>
                        <!-- <th>Trạng thái</th> -->
                        <!-- <th>Điểm</th> -->
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody class="text-left">
                    <tr :key="index" v-for="(student,index) in pageStudent">
                        <!-- <td>{{ course.id }}</td> -->
                        <td>{{index +1}}</td>
                        <td>{{ student.name }}</td>
                        <td>{{ student.email }}</td>

                        <td class="d-flex justify-content-center">
                            <button class="btn btn-danger" @click="onDelete(student.id)"> <i
                                    class="far fa-trash-alt"></i> Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <pagination v-model="page" :records="totalElements" :per-page="pageSize" @paginate="myCallback" />
        </div>
    </div>

</template>
<script>
    import Pagination from 'v-pagination-3';
    export default {
        name: 'AdminClassDetail',
        data() {
            return {
                isLoading: false, // Biến để theo dõi trạng thái của request
                showSpinner: false,
                availableTeacher: [],
                courseName: '',
                classId: null,
                courseId: null,
                courseClass: {
                    id: '',
                    name: '',
                    openingDay: '',
                    schedule: '',
                    students: [],
                    teacher: {
                        id: '',
                        name: ''
                    }

                },
                schedule: {},
                registerList: [],
                selectUserIds: [],

                pageStudent: [],
                totalElements: 0,
                totalPages: 0,
                pageSize: 10,

                page: 1
            }
        },
        components: {
            Pagination

        },
        created() {
            this.classId = this.$route.params.classId
            this.courseId = this.$route.params.courseId
            this.getClass()



        },
        methods: {
            myCallback(newPage) {
                var page = newPage - 1
                var studentsPerPage = 10;
                var startIndex = page * studentsPerPage;
                var endIndex = startIndex + studentsPerPage;
                this.pageStudent = this.courseClass.students.slice(startIndex, endIndex);

            },
            save() {
                console.log("saved!")
                console.log(this.courseClass)
                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.post('http://localhost:8080/api/class/update', this.courseClass, { headers: headers }).then((res) => {
                    console.log(res.data)
                    this.$swal.fire('updated!', '', 'success')
                })
            },
            getAvailableTeacher() {
                console.log("get teachers ")
                console.log(this.courseClass.schedule)
                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.get("http://localhost:8080/api/teacher/get-teachers-by-schedule/" + this.courseClass.schedule.id, { headers: headers }).then((response) => {
                    console.log(response.data)
                    this.availableTeacher = response.data
                    this.availableTeacher.push(this.courseClass.teacher)



                }).catch(err => {
                    console.log(err);
                })

            },
            onDelete(userId) {
                var token = localStorage.getItem('token');
                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.delete('http://localhost:8080/api/class/delete-student?classId=' + this.classId + '&userId=' + userId, { headers: headers }).then((res) => {
                    console.log(res.data)
                    this.$swal.fire("Deleted! ", "", "success")
                    this.getClass()


                }).catch(err => {
                    this.$swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: err.response.data
                    });
                })


            },
            getClass() {
                this.$request.get('http://localhost:8080/api/class/get/' + this.classId).then((res) => {
                    this.courseClass = res.data
                    this.courseClass.students = this.courseClass.students.sort((a, b) => a.id - b.id);
                    this.totalElements = this.courseClass.students.length

                    this.totalPages = Math.ceil(this.totalElements / 10);


                    this.myCallback(this.page)
                    console.log(this.courseClass)
                    this.getAvailableTeacher()


                }).catch(err => {
                    // what now?
                    console.log(err);
                })

            },
            getUserRegister() {
                // this.showSpinner = true;
                // this.isLoading = true;
                this.$request.get('http://localhost:8080/api/user/get-user-register?courseId=' + this.courseId + '&scheduleId=' + this.courseClass.schedule.id + '&classId=' + this.courseClass.id).then((res) => {
                    this.registerList = res.data
                    console.log(this.registerList)
                }).catch(err => {
                    // what now?
                    console.log(err);
                }).finally(() => {

                    // this.showSpinner = false;
                    // this.isLoading = false;
                });

            },
            addUsers() {
                this.showSpinner = true;
                this.isLoading = true;
                console.log(this.selectUserIds)
                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.post('http://localhost:8080/api/class/add-users/' + this.classId, this.selectUserIds, { headers: headers }).then((res) => {
                    console.log(res.data)
                    this.getClass()
                    // this.showSpinner = false;
                    // this.isLoading = false;
                    this.$swal.fire("Added students! ", "", "success")

                }).catch(err => {
                    // this.showSpinner = false;
                    // this.isLoading = false;
                    this.$swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: err.response.data
                    });
                }).finally(() => {

                    this.showSpinner = false;
                    this.isLoading = false;
                });
            }
        }
    }
</script>
<style scoped>
    input,
    select {
        border: none;
        /* Nếu bạn muốn xóa cả viền đường kẻ bên dưới khi input có focus */
        border-bottom: none;
        /* Nếu bạn muốn xóa viền khi input có focus */
        outline: none;
    }

    h4 {
        text-align: left;
        font-weight: bold;
    }

    #classInfor {
        border: 1px solid black;
    }

    .overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        /* Màu nền đen với độ mờ */
        z-index: 9999;
        /* Đảm bảo nó hiển thị trên tất cả các phần khác */
    }

    /* CSS để căn giữa spinner */
    .spinner {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 10000;
        /* Đảm bảo nó hiển thị trên lớp overlay */
    }
</style>