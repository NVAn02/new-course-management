<template>
    <div class="container pt-5">
        <div>
            <h1>Subject Management</h1>
        </div>
        <div class="d-flex justify-content-end mb-2">
            <div class="btn btn-primary" data-toggle="modal" data-target="#createSubject"><i class="fas fa-plus"></i>
                Add new subject</div>
        </div>
        <div class="d-flex justify-content-end mb-3">
            <div class="modal" id="createSubject">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Create new subject</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <!-- Modal body -->
                        <div class=" text-left ">
                            <div class="d-flex mt-4">
                                <div class="col-12">
                                    <div class="name">
                                        <div>
                                            <h4>Subject name:</h4>
                                        </div>
                                        <div>
                                            <input class="form-control" type="text" v-model="newSubject.name">
                                        </div>
                                        <div class="mt-3">
                                            <h4>Subject content:</h4>
                                        </div>
                                        <div>
                                            <textarea class="form-control" v-model="newSubject.content"
                                                style="min-height: 100px;"></textarea>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <div class="btn btn-primary" data-dismiss="modal" @click="save()">Save</div>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <table class="table">
                <thead class="bg-dark text-white">
                    <tr class="text-left">
                        <th class="col-1">No</th>
                        <th class="col-7">Tên bài học</th>
                        <!-- <th>Nội dung</th> -->
                        <th class="col-4">Hành động</th>
                    </tr>
                </thead>
                <tbody class="text-left">
                    <tr :key="index" v-for="(subject, index) in subjects">

                        <td>{{ index + 1 }}</td>
                        <td>{{ subject.name }}</td>

                        <td class="d-flex justify-content-center">
                            <div class="btn btn-primary mr-2" data-toggle="modal" data-target="#editSubject"
                                @click="getSubject(subject.id)"> <i class="far fa-edit"></i> Edit</div>&nbsp;
                            <div class="btn btn-danger mr-2" @click="onDeleteSubject(subject.id)"><i
                                    class="far fa-trash-alt"></i> Delete</div>
                            &nbsp;
                            <router-link :to="{ name: 'SubjectQuestion', params: { subjectId: subject.id } }"
                                class="btn btn-primary"><i class="far fa-question-circle"></i>
                                Quiz
                            </router-link>
                            <div class="modal" id="editSubject">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <!-- Modal Header -->
                                        <div class="modal-header">
                                            <h4 class="modal-title">Edit subject</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <!-- Modal body -->
                                        <div class=" text-left ">
                                            <div class="d-flex mt-4">
                                                <div class="col-12">
                                                    <div class="name">
                                                        <div>
                                                            <h4>Subject name:</h4>
                                                        </div>
                                                        <div>
                                                            <input class="form-control" type="text"
                                                                v-model="editSubject.name">
                                                        </div>
                                                        <div class="mt-3">
                                                            <h4>Subject content:</h4>
                                                        </div>
                                                        <div>
                                                            <textarea class="form-control" v-model="editSubject.content"
                                                                style="min-height: 100px;"></textarea>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Modal footer -->
                                            <div class="modal-footer">
                                                <div class="btn btn-primary" data-dismiss="modal" @click="update()">Save
                                                </div>
                                                <button type="button" class="btn btn-danger"
                                                    data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>


        </div>
        <pagination v-model="page" :records="pageResponse.totalElements" :per-page="pageResponse.pageSize"
            @paginate="myCallback" />
    </div>
</template>
<script>
    import Pagination from 'v-pagination-3';

    export default {
        name: "AdminSubjectManager",
        data() {
            return {
                newSubject: {
                    name: '',
                    content: ''
                },
                subjects: [],
                editSubject: {},
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
            // this.getSubjects()
            this.myCallback(this.page)

        },
        methods: {
            myCallback(newPage) {
                var page = newPage - 1
                console.log('Page changed to:', page);

                var token = localStorage.getItem('token');
                // console.log("token : " + token);
                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.get('http://localhost:8080/api/subject/get-page?pageNo=' + page, { headers: headers }).then((response) => {
                    this.subjects = response.data.content
                    this.subjects.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                    this.pageResponse = response.data
                    this.totalElements = response.data.totalElements
                    this.pageSize = response.data.pageSize
                    console.log(this.subjects)
                }).catch(err => {
                    // what now?
                    console.log(err);
                })

            },

            update() {
                this.$request.put('http://localhost:8080/api/subject/update', this.editSubject, { headers: this.headers }).then((res) => {
                    console.log(res.data)
                    this.getSubjects()
                    this.$swal.fire("Updated subject", "", "success")

                }).catch(err => {
                    // what now?
                    console.log(err);
                })
            },

            save() {
                console.log(this.newSubject)
                this.$request.post('http://localhost:8080/api/subject/add', this.newSubject, { headers: this.headers }).then((res) => {

                    console.log(res.data)
                    this.myCallback(this.page)
                    this.$swal.fire("Create new subject", "", "success")

                }).catch(err => {

                    console.log(err);
                })

            },
            onDeleteSubject(id) {
                this.$request.delete('http://localhost:8080/api/subject/delete/' + id, { headers: this.headers }).then((res) => {

                    console.log(res.data)
                    this.myCallback(this.page)
                    this.$swal.fire(res.data, "", "success")

                }).catch(err => {
                    // what now?
                    this.$swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: err.response.data,

                    });
                })

            },
            getSubject(id) {
                this.$request.get('http://localhost:8080/api/subject/get/' + id).then((res) => {
                    this.editSubject = res.data;
                }).catch(err => {
                    console.log(err)
                })
            }


        }


    }
</script>
<style scoped>
    .subject-content p {
        /* display: inline-block; */
        /* width: 100%; */
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;
        overflow: hidden;
        /* overflow: hidden; */
        /* text-overflow: ellipsis; */
    }
</style>