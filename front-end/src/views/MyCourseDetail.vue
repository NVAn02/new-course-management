<template>
    <div class="my-course-detail container-fluid pb-5" style="background-color:#EEEEEE;">


        <div v-for="(userClass,index) in sortedListClass" :key="index" class="container px-0 mb-5 bg-white"
            style="min-height: 300px;">
            <div class=" text-white text-left p-5 d-flex mb-5" style="background-color: #333333; border-radius: 10px;">
                <div class="col-8">
                    <h1>{{course.name}}</h1>
                    <h5>{{course.shortDescription}}</h5>


                    <div style="font-size: smaller; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
                        <div><br>
                            <a>Trạng thái đăng kí:</a>
                            <span v-if="userClass.courseRegister.status === 0" style="color:red; font-weight: bolder;">
                                Pending</span>
                            <span v-else style="color:green; font-weight: bolder;"> Đã xếp lớp</span>
                        </div>

                        Lịch học đã đăng kí: {{userClass.courseRegister.schedule.dayOne }} - {{
                        userClass.courseRegister.schedule.dayTwo }}
                        <div v-if="userClass.courseRegister.courseClass">

                            <div>Bạn được xếp vào lớp: {{userClass.courseRegister.courseClass.name}}</div>
                            <div>Ngày Khai Giảng: {{userClass.courseRegister.courseClass.openingDay}}</div>
                            <div>Giáo Viên: {{userClass.courseRegister.courseClass.teacher.name}}</div>
                            <div>Trạng thái lớp:
                                <span style="color:green; font-weight: bolder;"
                                    v-if="userClass.courseRegister.courseClass.status === 0">
                                    Đang hoạt động</span>
                                <span style="color:red; font-weight: bolder;" v-else> Đã dừng họat động</span>
                            </div>
                        </div>
                    </div>
                    <div v-if="userClass.courseRegister.status === 0" class="btn btn-danger my-2"
                        @click="cancelRegistration(userClass.courseRegister.id)">
                        <i class="fas fa-times"></i> Hủy đăng ký
                    </div>
                </div>
                <div class="col-4 ">
                    <img :src="userClass.courseRegister.course.image" class="w-100" alt="" style="border-radius: 20px;">
                </div>

            </div>



            <div v-if="userClass.courseRegister.status === 1" class="d-flex justify-content-center pb-5">
                <div class="col-9">
                    <div class="text-left ">
                        <h4 style="font-size: xx-large; ">Danh sách môn học</h4>
                    </div>
                    <table class="table">
                        <!-- <thead class="bg-dark text-white" data-toggle="collapse" :data-target="`#target${index}`"> -->
                        <thead class="bg-dark text-white">
                            <tr>
                                <th class="col-1">No</th>
                                <th class="col-7">Tên môn học </th>
                                <th class="col-2">Hành động</th>
                                <th class="col-2">Điểm</th>
                            </tr>
                        </thead>
                        <!-- <tbody :id="`target${index}`" class="collapse"> -->
                        <tbody>
                            <tr :key="index" v-for="(score, index) in userClass.scoresSortedById">
                                <td>{{ index + 1 }}</td>
                                <td>{{ score.subject.name }}</td>
                                <td>
                                    <router-link
                                        :to="!score.score ? { name: 'Quiz', params: { subjectId: score.subject.id, classId:userClass.courseRegister.courseClass.id } } : ''"
                                        :class="{ 'disable-link': score.score }">
                                        quiz test
                                    </router-link>
                                </td>
                                <td>
                                    <span>{{score.score}}</span>
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
        name: "myCourseDetail",
        data() {
            return {
                courseId: "",
                course: "",
                scores: "",

                listClass: [],
                courseClass: {
                    name: "",
                    openingDay: "",
                    teacher: ""

                }
            }
        },
        created() {
            this.courseId = this.$route.params.id
            // var userId = localStorage.getItem('userId')
            this.getRegisterCourseDetail(this.courseId)
            // this.getClass(courseId, userId)
            this.getCourse(this.courseId)
        },
        computed: {
            sortedListClass() {
                return this.listClass.map(classItem => {
                    if (!classItem.scores || !Array.isArray(classItem.scores)) {
                        return classItem;
                    }
                    return {
                        ...classItem,
                        scoresSortedById: classItem.scores.sort((a, b) => {
                            if (!a.subject || !b.subject) {
                                return 0;
                            }
                            return a.subject.id - b.subject.id;
                        })
                    };
                });
            }
        },

        methods: {
            getRegisterCourseDetail(courseId) {
                console.log("get register")
                var token = localStorage.getItem('token');
                var headers = {
                    'Authorization': 'Bearer ' + token
                };

                this.$request.get("http://localhost:8080/api/course/get-my-course-detail?courseId=" + courseId + "&token=" + token, { headers: headers }).then((response) => {
                    console.log(response.data)
                    if (response.data.length === 0) {
                        this.$router.push({ name: "mycourse" });
                    }
                    this.listClass = response.data.sort((a, b) => {
                        return new Date(b.courseRegister.created) - new Date(a.courseRegister.created);
                    });
                    console.log("Get courses detail")
                    console.log(this.listClass)


                }).catch(err => {
                    // what now?
                    console.log(err);
                })
            },

            getCourse(id) {
                this.$request.get("http://localhost:8080/api/course/get/" + id).then((response) => {
                    console.log(response.data)
                    this.course = response.data

                }).catch(err => {
                    // what now?
                    console.log(err);
                })

            },
            cancelRegistration(id) {

                console.log("cancel register id: ", id)


                var token = localStorage.getItem('token');
                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$swal.fire({
                    title: "Do you want to cancel registration?",
                    showCancelButton: true,
                    confirmButtonText: "Cancel",
                    width: '600px'
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.$request.delete("http://localhost:8080/api/register/delete-registration/" + id, { headers: headers }).then((response) => {
                            console.log(response.data)
                            this.getRegisterCourseDetail(this.courseId)
                            this.$swal.fire(response.data, '', 'success')

                        }).catch(err => {
                            // what now?
                            console.log(err);
                            this.$swal.fire({
                                icon: "error",
                                title: "Oops...",
                                text: err.response.data,

                            });
                        })
                    }



                })
            }


        }
    }
</script>
<style scoped>
    .my-course-detail {
        min-height: 700px;
        padding-top: 50px;
    }

    .disable-link {
        text-decoration: none;
        color: #BBBBBB;
    }
</style>