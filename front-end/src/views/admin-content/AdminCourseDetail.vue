<template>
    <div class="CourseDetail container-fluid pt-5">
        <h1>{{ course.name }}</h1>


        <div class="mt-5">
            <div>
                <h4>Danh sách lớp học</h4>
            </div>
            <div class="d-flex justify-content-end">
                <router-link :to="{ name: 'AdminCreateClass', params:{id: courseId}}" class=" btn btn-primary mb-2"><i
                        class="fas fa-plus"></i> Thêm lớp học
                </router-link>
            </div>
            <table class="table">
                <thead class="bg-dark text-white">
                    <tr class="text-left">
                        <th >No</th>
                        <th class="col-2">Tên lớp học</th>
                        <th>Số học sinh</th>
                        <th >Lịch học</th>
                        <!-- <th>Ngày khai giảng</th> -->
                        <th>Giáo viên</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody class="text-left">
                    <tr :key="index" v-for="(courseClass,index) in sortedClasses">
                        <!-- <td>{{ course.id }}</td> -->
                        <td>{{index +1}}</td>
                        <td>{{ courseClass.name }}</td>
                        <td>{{courseClass.students.length}}</td>

                        <td>{{ courseClass.schedule.dayOne}} - {{ courseClass.schedule.dayTwo}}</td>
                        <!-- <td>{{ courseClass.openingDay }}</td> -->
                        <td>{{ courseClass.teacher.name }}</td>
                        <td>
                            <span v-if="courseClass.status === 0">Enable</span>
                            <span v-else>Disable</span>
                        </td>

                        <td class="d-flex justify-content-center">
                            <router-link
                                :to="{ name: 'AdminclassDetail', params:{classId: courseClass.id,courseId : course.id }}"
                                class="btn btn-primary"><i class="far fa-edit"></i> Edit</router-link>&nbsp;
                            <div class="btn text-white" style="background-color:  #A020F0;" @click="changeStatus(courseClass.id)" ><i class="fas fa-exchange-alt"></i> Change status

                            </div>

                        </td>
                        <!-- <button class="btn btn-danger" @click="onDelete(course.id)"> Delete</button> -->

                    </tr>
                </tbody>
            </table>
        </div>
    </div>



</template>
<script>
    export default {
        name: "AdminCourseDetail",
        data() {
            return {
                courseId: null,
                course: {
                    classes: []
                }
            }
        },
        computed: {
            sortedClasses() {

                const sorted = [...this.course.classes];


                sorted.sort((a, b) => a.id - b.id);

                return sorted;
            }
        },
        created() {
            this.courseId = this.$route.params.id
            this.getCourse()

        },

        methods: {
            getCourse() {
                this.$request.get('http://localhost:8080/api/course/get/' + this.courseId).then((res) => {
                    this.course = res.data
                    console.log(this.course.classes)

                }).catch(err => {
                    // what now?
                    console.log(err);
                })
            },
            changeStatus(id){
                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };

                this.$request.put('http://localhost:8080/api/class/change-status/' + id, {headers:headers}).then((response) =>{
                    this.$swal.fire(response.data, "", "success")
                    this.getCourse()
                }).catch((err) =>{
                    console.log(err)
                })

            }

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
   
</style>