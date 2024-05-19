<template>
    <div class="container pt-5 pb-5">
        <div>
            <h1>Teacher Detail </h1>
        </div>
        <div style="border: 1px solid black; " class="pb-3">
            <div class="d-flex col-12 mt-2 pt-3 ">
                <div class="d-flex mr-3">
                    <div class="col-3">
                        <h4>Name: </h4>
                    </div>
                    <div class="col-9"><input type="text" class="form-control" v-model="teacher.name"></div>
                </div>
                <div class="d-flex">
                    <div class="col-2">
                        <h4>Email: </h4>
                    </div>
                    <div class="col-10"><input type="text" class="form-control" v-model="teacher.email"></div>
                </div>

            </div>
            <div class="d-flex mt-5 mb-5 col-12">
                <div class="d-flex">
                    <div class="col-3">
                        <h4>Phone: </h4>
                    </div>
                    <div class="col-9"><input type="text" class="form-control" v-model="teacher.phone"></div>
                </div>
                <div class="d-flex">
                    <div class="col-3">
                        <h4>Address: </h4>
                    </div>
                    <div class="col-9 ml-2"><input type="text" class="form-control" v-model="teacher.address"></div>
                </div>
                <div class="d-flex text-align-center">
                    <div class="col-3">
                        <h4>Status: </h4>
                    </div>
                    <div class="col-9 ml-2 mt-1">
                        {{ teacher.status === 1 ? 'Disable' : 'Enable' }}
                    </div>
                </div>
            </div>
            <div class=" mb-3 ml-4 ">

                <div>
                    <h4>Classes: </h4>
                </div>
                <div class=" text-left" v-for="(teacherClass, index) in teacher.classes" :key="index">
                    <div class="d-flex">
                        <div class="col-2">{{ teacherClass.name }}</div>
                        <div>
                            <span style="color:green; font-weight: bolder;" v-if="teacherClass.status === 0"> Đang hoạt
                                động</span>
                            <span style="color:red; font-weight: bolder;" v-else> Đã dừng họat động</span>
                        </div>
                    </div>
                </div>

            </div>
            <div class="d-flex justify-content-start ml-4 mb-2">
                <div class="btn btn-primary" @click="updateTeacher()">
                    <i class="far fa-bookmark"></i> Save

                </div>
            </div>

        </div>
        <div class="mt-4">
            <h4>Lịch dạy</h4>
            <div id="timetable">
                <table>
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
                                <p class="d-flex justify-content-center text-align-center">{{ schedule[timeSlot][day] }}
                                </p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</template>
<script>
    export default {
        name: 'AdminTeacherDetail',
        data() {
            return {
                teacherId: '',
                teacher: {
                    name: '',
                    email: '',
                    phone: '',
                    address: '',
                    status: '',
                    classes: []
                },
                days: ['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', "Thứ 7", "Chủ Nhật"],
                timeSlots: ["8:00-10:00", "10:00-12:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00"],
                schedule: []
            }
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
            this.teacherId = this.$route.params.id
            this.getTeacher(this.teacherId)

            this.schedule = this.timeSlots.reduce((acc, timeSlot) => {
                acc[timeSlot] = {};
                this.days.forEach(day => {

                    acc[timeSlot][day] = "";



                });
                return acc;
            }, {});


        },
        methods: {

            getTeacher(id) {
                this.$request.get('http://localhost:8080/api/teacher/get/' + id, { headers: this.headers }).then((res) => {
                    this.teacher = res.data
                    console.log(this.teacher)
                    console.log("classes: ")
                    console.log(this.teacher.classes)
                    this.schedule = this.timeSlots.reduce((acc, timeSlot) => {
                        acc[timeSlot] = {};
                        this.days.forEach(day => {
                            const schedule = day + " " + timeSlot
                            const classFound = this.teacher.classes
                                .filter(function (cls) {
                                    return cls.status === 0;
                                })
                                .find(classItem =>
                                    classItem.schedule.dayOne === schedule || classItem.schedule.dayTwo === schedule
                                );

                            if (classFound) {
                                acc[timeSlot][day] = classFound.name;
                            }
                        });
                        return acc;
                    }, {});

                })



            },
            updateTeacher() {
                console.log(this.teacher)
                this.$request.put('http://localhost:8080/api/teacher/update', this.teacher, { headers: this.headers }).then((res) => {
                    console.log(res.data);
                    this.$swal.fire('updated', '', 'success')
                    this.getTeacher(this.teacher.id)
                })
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

    #timetable {
        font-family: Arial, sans-serif;
    }

    table {
        border-collapse: collapse;
        width: 100%;
    }

    th,
    td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }

    #timetable table {
        width: auto;
        border-collapse: collapse;
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