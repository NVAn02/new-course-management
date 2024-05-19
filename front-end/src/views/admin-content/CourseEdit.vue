<template>
    <div class="container-fluid mt-5">
        <h1 class="mb-5">{{course.name}}</h1>
        <div class="d-flex" id="courseInfo">
            <div class="col-4 mt-3">
                <div class="mb-2">
                    <div>
                        <h4>Name:</h4>
                    </div>
                    <input type="text" class="form-control" v-model="course.name">
                </div>
                <div class="mb-2">
                    <div>
                        <h4>Price:</h4>
                    </div>
                    <input type="text" class="form-control" v-model="course.price">
                </div>
            </div>
            <div class="col-6  mt-3">
                <div class="mb-2">
                    <div>
                        <h4>Image:</h4>
                    </div>
                    <input type="text" class="form-control" v-model="course.image">
                </div>
                <div class="mb-2">
                    <div>
                        <h4>Short Description:</h4>
                    </div>
                    <input type="text" class="form-control" v-model="course.shortDescription">
                </div>

            </div>
        </div>
        <div class="mt-3">
            <div>

                <h4>Chọn lịch học:</h4>

                <div>
                    <div class="d-flex justify-content-end">
                        <div class="btn btn-primary mb-2" data-toggle="modal" data-target="#myModal"><i
                                class="fas fa-plus"></i> Add
                            schedule</div>
                    </div>
                    <div class="modal" id="myModal">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content ">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Choose schedule</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <!-- Modal body -->
                                <div class="modal-body" style="max-height: 300px; overflow-y: auto;">
                                    <div class=" d-flex justify-content-center" v-for="(schedule, index) in schedules "
                                        :key="index">
                                        <div class="col-6 text-left">
                                            {{ schedule.dayOne }} - {{ schedule.dayTwo }}
                                        </div>
                                        <div class="col-4">
                                            <input style=" transform: scale(1.6)" type="checkbox"
                                                :id="'checkbox' + index" :value="schedule" v-model="course.schedules">
                                        </div>

                                    </div>


                                </div>
                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <!-- <div class="btn btn-primary" @click="saveLesson()" data-dismiss="modal"> Save</div> -->
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table">
                        <thead class="bg-dark text-white">
                            <tr class="text-left">
                                <th>No</th>
                                <th>Buổi 1</th>
                                <th>Buổi 2</th>
                                <!-- <th>Hành động</th> -->
                            </tr>
                        </thead>
                        <tbody class="text-left">
                            <tr :key="index" v-for="(schedule, index) in course.schedules">

                                <td>{{ index + 1 }}</td>
                                <td>{{ schedule.dayOne }}</td>
                                <td>{{ schedule.dayTwo }}</td>

                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div>
                <div>
                    <h4>List Lesson</h4>
                </div>
            </div>
            <div>
                <div class="d-flex justify-content-end">
                    <div class="btn btn-primary mb-2" data-toggle="modal" data-target="#lessonList"><i
                            class="fas fa-plus"></i> Add
                        lesson</div>
                </div>
                <div class="modal" id="lessonList">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content ">
                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Choose lesson</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <!-- Modal body -->
                            <div class="modal-body" style="max-height: 300px; overflow-y: auto;">
                                <div class=" d-flex justify-content-center" v-for="(subject, index) in subjects "
                                    :key="index">
                                    <div class="col-6 text-left">
                                        {{ subject.name }}
                                    </div>
                                    <div class="col-4">
                                        <input style=" transform: scale(1.6)" type="checkbox" :id="'checkbox' + index"
                                            :value="subject" v-model="course.subjects">
                                    </div>
                                </div>
                            </div>
                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <!-- <div class="btn btn-primary" @click="saveLesson()" data-dismiss="modal"> Save</div> -->
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>

                </div>
                <table class="table">
                    <thead class="bg-dark text-white">
                        <tr class="text-left">
                            <th>No</th>
                            <th>Tên bài học</th>
                            <th>Nội dung</th>
                            <!-- <th>Hành động</th> -->
                        </tr>
                    </thead>
                    <tbody class="text-left">
                        <tr :key="index" v-for="(subject,index) in course.subjects">
                            <!-- <td>{{ course.id }}</td> -->
                            <td>{{index +1}}</td>
                            <td>{{ subject.name }}</td>
                            <td>{{ subject.content }}</td>
                            <!-- <td class="d-flex justify-content-center">
                                <div class="btn btn-primary mr-2"> <i class="far fa-edit"></i> Edit</div>&nbsp;
                                <div class="btn btn-danger mr-2" @click="deleteLesson(subject.id)"> Delete</div>
                                &nbsp;

                                
                            </td> -->
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div>
            <div class="mb-2">
                <div>
                    <h4>Lesson Content:</h4>
                </div>
                <textarea class="form-control" v-model="course.lessonContent" style="height: 200px;"></textarea>
            </div>
            <div class="mb-2">
                <div>
                    <h4>Requirement</h4>
                </div>
                <textarea class="form-control" v-model="course.requirement" style="height: 200px;"></textarea>
            </div>
            <div class="mb-2">

                <div>
                    <h4>Description</h4>
                </div>
                <textarea class="form-control" v-model="course.description" style="height: 200px;"></textarea>
            </div>


        </div>
        <div class="text-left mt-5 mb-5">
            <div class="btn text-white " style="background-color: #A020F0; width: 100px;" @click="save()"> <i
                    class="fas fa-bookmark"></i> Save</div>

        </div>

    </div>





</template>
<script>
    export default {
        name: 'CourseEdit',
        data() {
            return {
                schedules: [],
                subjects: [],
                courseId: null,
                course: {

                },
                newLesson: {
                    name: '',
                    content: ''
                }
            }
        },
        created() {
            this.courseId = this.$route.params.id
            this.getCourse()
            this.getSubjects()
            this.getSchedules()

        },
        methods: {
            save() {
                console.log(this.course)
                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.put('http://localhost:8080/api/course/update', this.course, { headers: headers }).then((res) => {

                    console.log(res.data)
                    this.$swal.fire("Updated!", "", "Success")

                }).catch(err => {
                    // what now?
                    console.log(err);
                })
            },
            getSchedules() {
                this.$request.get('http://localhost:8080/api/schedule/get').then((res) => {
                    this.schedules = res.data
                    this.schedules.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                    console.log(this.schedules)

                }).catch(err => {
                    // what now?
                    console.log(err);
                })
            },
            getCourse() {
                this.$request.get('http://localhost:8080/api/course/get/' + this.courseId).then((res) => {
                    this.course = res.data
                    this.course.subjects.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                    console.log(this.course.classes)

                }).catch(err => {
                    // what now?
                    console.log(err);
                })
            },
            // saveLesson() {
            //     console.log(this.newLesson)
            //     this.$request.put('http://localhost:8080/api/course/update-lesson/' + this.courseId, this.newLesson).then((res) => {
            //         this.$swal.fire("Added lesson", "", "success")

            //         console.log(res.data)
            //         this.getCourse()

            //     }).catch(err => {
            //         // what now?
            //         console.log(err);
            //     })

            // },
            // deleteLesson(id) {
            //     console.log("Delete lesson")
            //     this.$request.delete('http://localhost:8080/api/course/delete-lesson/' + id).then((res) => {
            //         this.$swal.fire("Deleted lesson", "", "success")

            //         console.log(res.data)
            //         this.getCourse()

            //     }).catch(err => {
            //         // what now?
            //         console.log(err);
            //     })

            // },
            getSubjects() {
                this.$request.get('http://localhost:8080/api/subject/get').then((res) => {
                    this.subjects = res.data

                    this.subjects.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                    console.log(this.subjects)

                }).catch(err => {
                    // what now?
                    console.log(err);
                })
            },


        }

    }
</script>
<style scoped>
    input {
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

    #courseInfo {
        border: 1px solid black;
    }

    /* .table {
        max-height: 100px;
        overflow-y: auto;
    } */
</style>>