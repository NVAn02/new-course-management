<template>
    <div class="container-fluid">
        <div>
            <h1>Create new class for course: {{course.name}}</h1>
        </div>
        <div class=" text-left mt-4">
            <div class="col-4 mr-5">

                <div class="mb-2">
                    <div>
                        <h4>Name:</h4>
                    </div>
                    <input type="text" class="form-control" v-model="newClass.name">
                </div>
                <div class="mb-2">
                    <div>
                        <h4>Opening Day:</h4>
                    </div>
                    <input type="date" class="form-control" v-model="newClass.openingDay">
                </div>


            </div>
            <div class="col-6">

                <div class="mb-2">
                    <label for="multi-select">
                        <h4>Chọn lịch học:</h4>
                    </label>
                    <div class="select select--multiple  ">
                        <select @change="getAvailableTeacher" v-model="this.selectedSchedule" class=" form-control"
                            style="width: 100%; font-size: larger;">
                            <option v-for="(schedule,id) in schedules" :value="schedule" :key="id">
                                {{schedule.dayOne}} |
                                {{schedule.dayTwo}}
                            </option>
                        </select>
                        <span class="focus"></span>
                    </div>
                </div>
                <div class="mb-2">
                    <div>
                        <h4>Teacher:</h4>
                    </div>
                    <!-- <input type="text" class="form-control" v-model="newClass.teacher"> -->
                    <div class="select-teacher">
                        <select v-model="selectedTeacher" class="form-control" style="width: 100%; font-size: larger;">
                            <option v-for="(teacher,id) in availableTeacher" :value="teacher" :key="id">
                                {{teacher.name}}
                            </option>
                        </select>
                        <span class="focus"></span>
                    </div>
                </div>
                <div class="d-flex justify-content-start mt-5">
                    <div @click="addNewClass" class="btn text-white " style="background-color:#A020F0 ; width: 100px;">
                        Save</div>
                </div>
            </div>

        </div>

    </div>

</template>
<script>
    export default {
        name: 'CreateClass',
        data() {
            return {
                courseId: null,
                schedules: [],
                selectedSchedule: "",
                selectedTeacher: "",
                // newClass: {
                //     name: '',
                //     teacher: '',
                //     openingDay: '',
                //     schedule: ''
                // },
                newClass: {
                    name: '',
                    teacher: '',
                    openingDay: '',
                    schedule: ''
                },
                course: '',
                availableTeacher: []
            }
        }, created() {
            this.courseId = this.$route.params.id
            this.getCourse()

        },
        computed: {

        },
        methods: {
            getAvailableTeacher() {
                console.log("get teachers ")
                console.log(this.selectedSchedule)
                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.get("http://localhost:8080/api/teacher/get-teachers-by-schedule/" + this.selectedSchedule.id, { headers: headers }).then((response) => {
                    console.log(response.data)
                    this.availableTeacher = response.data


                }).catch(err => {
                    console.log(err);
                })

            },

            getCourse() {

                this.$request.get("http://localhost:8080/api/course/get/" + this.courseId).then((response) => {
                    console.log(response.data)

                    response.data.schedules.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                    this.course = response.data;
                    this.schedules = response.data.schedules
                    console.log(this.schedules)


                }).catch(err => {
                    console.log(err);
                })

            },
            addNewClass() {

                this.newClass.schedule = this.selectedSchedule
                this.newClass.teacher = this.selectedTeacher
                // console.log("schedule: " + this.newClass.schedule)
                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };

                console.log(this.newClass)
                this.$request.post("http://localhost:8080/api/class/add/" + this.courseId, this.newClass, { headers: headers }).then((response) => {
                    console.log(response.data)
                    this.$swal.fire("Added new class! ", "", "success")
                    this.$router.push('/admin-course-detail/' + this.courseId)

                }).catch(err => {
                    console.log(err);
                })
            }
        }

    }
</script>
<style scoped>


</style>