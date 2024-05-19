<template>
    <div class="container-fluid pt-5">
        <h1>{{course.name }}</h1>
        <div>
            <h4>Lịch học: </h4>
            <p>{{ schedule.dayOne }} - {{ schedule.dayTwo }}</p>

        </div>
        <div class="d-flex justify-content-start mt-3 mb-3">
            <h4>Danh sách đăng kí</h4>
        </div>
        <table class="table" style="max-height: 600px; overflow-y: auto;">
            <thead class="bg-dark text-white">
                <tr>
                    <th>No</th>
                    <th> Họ tên </th>
                    <th> Username</th>
                    <th> Email</th>
                    <th> Ngày đăng kí</th>
                    <!-- <th>Lịch học</th>
                    <th>Trạng thái</th>
                    <th>Chờ xếp lớp</th>
                    <th>Hành động</th> -->
                </tr>
            </thead>
            <tbody>
                <tr :key="index" v-for="(courseRegister, index) in courseRegisters">
                    <td>{{ index + 1 }}</td>
                    <td>{{ courseRegister.user.name }}</td>
                    <td>{{ courseRegister.user.username }}</td>
                    <td>{{ courseRegister.user.email }}</td>
                    <td>{{ courseRegister.formattedCreated }}</td>
                </tr>
            </tbody>
        </table>

    </div>
</template>
<script>

export default {
    name:'CourseRegister',
    data(){
        return{
            courseId:'',
            scheduleId:'',
            courseRegisters:[],
            course:'',
            schedule:''
        }
    },
    
    created() {
        this.courseId = this.$route.params.courseId
        this.scheduleId = this.$route.params.scheduleId
        this.getAllCourseRegister()


    },
    methods:{
        
        getAllCourseRegister(){
            console.log(this.courserId)
            var token = localStorage.getItem('token');

            var headers = {
                'Authorization': 'Bearer ' + token
            };
            this.$request.get('http://localhost:8080/api/register/get-all-course-register?courseId=' + this.courseId + '&scheduleId=' + this.scheduleId, { headers: headers }).then((res) =>{
                this.courseRegisters = res.data.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                this.course = this.courseRegisters[0].course
                this.schedule = this.courseRegisters[0].schedule
                console.log(this.courseRegisters)
            }).catch((err) => {
                console.log(err);
            })

        }
    }
}
</script>