<template>
    <div class="container pt-5">
        <div>
            <h1>Teacher Management</h1>
        </div>
        <div>
            <div class="d-flex justify-content-end">
                <div class="btn btn-primary mb-2" data-toggle="modal" data-target="#addNewTeacher"><i
                        class="fas fa-plus"></i> Add teacher</div>
                <div class="modal" id="addNewTeacher">
                    <div class="modal-dialog">
                        <div class="modal-content ">

                            <div class="modal-header">
                                <h4 class="modal-title">Create new teacher</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="ml-2">
                                <div class="d-flex mb-4">
                                    <div class="col-3">
                                        <h5>Name: </h5>
                                    </div>
                                    <div class="col-9"> <input type="text" class="form-control"
                                            v-model="newTeacher.name"></div>

                                </div>
                                <div class="d-flex mb-4">
                                    <div class="col-3">
                                        <h5>Email: </h5>
                                    </div>
                                    <div class="col-9"> <input type="text" class="form-control"
                                            v-model="newTeacher.email"></div>

                                </div>
                                <div class="d-flex mb-4">
                                    <div class="col-3">
                                        <h5>Phone: </h5>
                                    </div>
                                    <div class="col-9"> <input type="text" class="form-control"
                                            v-model="newTeacher.phone"></div>

                                </div>
                                <div class="d-flex mb-4">
                                    <div class="col-3">
                                        <h5>Address: </h5>
                                    </div>
                                    <div class="col-9"> <input type="text" class="form-control"
                                            v-model="newTeacher.address"></div>

                                </div>



                            </div>

                            <div class="modal-footer">
                                <div class="btn btn-primary" data-dismiss="modal" @click="register()">Save</div>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <table class="table text-left">
                    <thead class="bg-dark text-white">
                        <tr>
                            <th>No</th>
                            <th>Họ tên </th>
                            <th>Email</th>
                            <th>Trạng thái</th>
                            <th>Hành động</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr :key="index" v-for="(teacher, index) in teachers">
                            <td :class="{ 'text-secondary': teacher.status === 1 }">{{ index +1 }}</td>
                            <!-- <td>{{ index + 1 }}</td> -->
                            <td :class="{ 'text-secondary': teacher.status === 1 }">{{ teacher.name }}</td>
                            <td :class="{ 'text-secondary': teacher.status === 1 }">{{ teacher.email }}</td>
                            <td :class="{ 'text-secondary': teacher.status === 1 }">
                                <span v-if="teacher.status === 1">Disable</span>
                                <span v-else>Enable</span>
                            </td>
                            <td>
                                <router-link :to="{ name: 'AdminTeacherDetail', params:{id: teacher.id}}"
                                    class="btn btn-primary"><i class="far fa-edit"></i> Edit</router-link>&nbsp;
                                <div class="btn text-white" style="background-color: #A020F0"
                                    @click="changeStatus(teacher.id)">
                                    <i class="fas fa-exchange-alt"></i> Change status
                                </div>
                            </td>

                        </tr>
                    </tbody>
                </table>
                <pagination v-model="page" :records="pageResponse.totalElements" :per-page="pageResponse.pageSize"
                    @paginate="myCallback" />
            </div>

        </div>
    </div>

</template>
<script>
    import Pagination from 'v-pagination-3';
    export default {
        name: 'AdminTeacherManager',
        data() {
            return {
                teachers: [],
                newTeacher: {
                    name: '',
                    email: '',
                    phone: '',
                    address: ''

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
        computed: {
            headers() {
                var token = localStorage.getItem('token');
                return {
                    'Authorization': 'Bearer ' + token
                };
            }
        },

        created() {
            this.myCallback(this.page)
        },
        methods: {
            myCallback(newPage) {
                var page = newPage - 1

                var token = localStorage.getItem('token');
                // console.log("token : " + token);
                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.get('http://localhost:8080/api/teacher/get?pageNo=' + page, { headers: headers }).then((res) => {
                    // this.teachers = res.data.content
                    this.pageResponse = res.data


                    this.teachers = this.pageResponse.content
                    console.log(res.data)
                })

            },
            register() {
                console.log(this.newTeacher)
                this.$request.post('http://localhost:8080/api/teacher/add', this.newTeacher, { headers: this.headers }).then((res) => {
                    console.log(res.data)
                    this.$swal.fire('Added new teacher', '', 'success')
                    this.myCallback(this.page)
                })

            },
            // getAllTeacher() {
            //     console.log("get all teacher")
            //     this.$request.get('http://localhost:8080/api/teacher/get', { headers: this.headers }).then((res) => {

            //         this.pageResponse = res.data


            //         this.teachers = this.pageResponse.content
            //     })
            // },
            changeStatus(id) {
                this.$swal.fire({
                    title: "Do you want to change status?",
                    // showDenyButton: true,
                    showCancelButton: true,
                    confirmButtonText: "Change",

                }).then((result) => {
                    /* Read more about isConfirmed, isDenied below */
                    if (result.isConfirmed) {


                        this.$request.put('http://localhost:8080/api/teacher/change-status/' + id, { headers: this.headers }).then((response) => {
                            console.log(response.data)
                            this.$swal.fire("Changed!", "", "success");
                            this.getAllTeacher()
                        }).catch((err) => {
                            console.log(this.changeStatus(id))
                            console.log(err);
                            this.$swal.fire({
                                icon: "error",
                                title: "Oops...",
                                text: err.response.data,

                            });
                        })
                    }
                });
            }
        }
    }
</script>
<style scoped>

</style>