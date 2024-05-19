<template>
    <div class="admin-home container pt-5 pb-5">
        <div class="main ">
            <div class="main-content">
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                        aria-labelledby="pills-home-tab">
                        <h1>Dashboard</h1>
                    </div>
                    <div>
                        <div class="text-left mt-5 mb-2">
                            <div>
                                <h2>Số người đăng kí</h2>
                            </div>

                        </div>

                        <table class="table" style="max-height: 600px; overflow-y: auto;">
                            <thead class="bg-dark text-white">
                                <tr>
                                    <th>No</th>
                                    <th class="col-3">Tên khóa học </th>
                                    <th>Lịch học đăng kí</th>
                                    <th>Trạng thái</th>
                                    <th>Chờ xếp lớp</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr :key="index" v-for="(numberRegister, index) in numberCourseRegisters">
                                    <!-- <td>{{ student.id }}</td> -->
                                    <td v-if="numberRegister.numRegister >0">{{ index + 1 }}</td>
                                    <td v-if="numberRegister.numRegister >0">{{ numberRegister.course.name}}</td>
                                    <td v-if="numberRegister.numRegister >0">{{numberRegister.schedule.dayOne}} ~
                                        {{numberRegister.schedule.dayTwo}}</td>
                                    <td v-if="numberRegister.numRegister >0">
                                        <span v-show="numberRegister.numRegister  >= 5"
                                            style="color:green; font-weight: bolder;  text-shadow: 0 0 5px greenyellow;">
                                            Đã
                                            đủ số lượng</span>

                                        <span v-show="numberRegister.numRegister  < 5"> Chưa đủ số lượng</span>
                                    </td>
                                    <td v-if="numberRegister.numRegister >0">
                                        <span style=" font-weight: bolder;">{{numberRegister.numRegister}}</span>
                                    </td>
                                    <td v-if="numberRegister.numRegister >0">
                                        <router-link
                                            :to="{ name: 'CourseRegister', params:{courseId: numberRegister.course.id, scheduleId: numberRegister.schedule.id }}"
                                            class="btn btn-primary">Chi
                                            tiết</router-link>
                                    </td>

                                </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</template>
<script>

    export default {
        name: 'AdminHome',
        data() {
            return {
                numberCourseRegisters: []
            }
        },
        created() {
            this.getNumberCourseRegister()
        },
        methods: {
            getNumberCourseRegister() {
                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };
                this.$request.get("http://localhost:8080/api/register/get-number-register", { headers: headers }).then((response) => {

                    console.log(response.data)
                    this.numberCourseRegisters = response.data
                    this.numberCourseRegisters.sort((a, b) => (a.numRegister < b.numRegister) ? 1 : ((b.numRegister < a.numRegister) ? -1 : 0));

                }).catch(err => {
                    // what now?
                    console.log(err);
                })

            }
        }

    }
</script>
<style scoped>
    .admin-home {
        margin-left: 2%;



    }

    .side-bar {
        min-height: 800px;
        /* border-bottom-left-radius: 20px;
        border-top-left-radius: 20px; */
        background-color: #8A2BE2
    }



    .user-info {
        position: relative;

        left: 80%;
    }

    .nav-item {
        width: 100%;
        font-size: 100%;
        font-weight: bolder;
        border-bottom: 1px solid #EEEEEE;
        transition: opacity 0.3s ease;

    }

    .nav-item:hover {

        opacity: 0.5;
    }

    .nav-item .btn {
        width: 100%;
    }

    .item {
        text-decoration: none;


    }

    .nav-item a {
        color: white;

        font-weight: bold;
    }
</style>