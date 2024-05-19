<template>
    <div class="container pt-5">
        <div>
            <h1>Score Management</h1>
        </div>
        <div>
            <div class="d-flex justify-content-center ">
                <div class="col-3">
                    <select class="form-control" name="course" id="course" v-model="selectedCourse"
                        @change="getAllCourseClasses">
                        <option value="" disabled selected style="text-align: center;">-- Chọn khóa học --</option>
                        <option :value="course.id" v-for="(course, index) in courses" :key="index">{{ course.name }}
                        </option>
                    </select>
                </div>
                <div class="col-3">
                    <select name="class" id="class" class="form-control" v-model="selectedClass">
                        <option value="" disabled selected style="text-align: center;">-- Chọn lớp học --</option>
                        <option :value="courseClass.id" v-for="(courseClass, index) in courseClasses" :key="index">{{
                            courseClass.name }}
                        </option>
                    </select>
                </div>
                <div class="col-3">
                    <select name="subject" id="subject" class="form-control" v-model="selectedSubject">
                        <option value="" disabled selected style="text-align: center;">-- Chọn môn học --</option>
                        <option :value="subject.id" v-for="(subject, index) in subjects" :key="index">
                            {{subject.name }}
                        </option>
                    </select>
                </div>

                <div class="col-2">
                    <div class="btn btn-primary" style="width: 100px;" @click="search()"><i class="fas fa-search"></i>
                        Tìm</div>
                </div>
            </div>
        </div>
        <div>
            <div class="text-left mt-5 mb-2">
                <!-- <h4>Danh sách học sinh</h4> -->
            </div>
            <div class="d-flex justify-content-end mb-2">
                <!-- <div class="btn btn-primary"><i class="far fa-edit"></i> Edit</div> -->

            </div>
            <div>
                <table class="table">
                    <thead class="bg-dark text-white">
                        <tr>
                            <th>No</th>
                            <th>Họ tên </th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Điểm</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr :key="index" v-for="(score, index) in scores">
                            <!-- <td>{{ student.id }}</td> -->
                            <td class="col-1">{{ index + 1 }}</td>
                            <td class="col-3">{{ score.user.name }}</td>
                            <td class="col-3">{{ score.user.username }}</td>
                            <td class="col-3">{{ score.user.email }}</td>

                            <td class="col-2">
                                <input style=" width: 100%;" type="text" v-model="score.score" @blur="save(score)">
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
    name:'AdminScoreManager',
    data(){
        return{
            scores:[
                {
                    user:{},
                },
            ],

            students:[],

            courses:[],
            courseClasses:[],
            subjects:[],
            selectedCourse:'',
            selectedClass:'',
            selectedSubject:'',
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
    created(){
        this.getAllCourse()
        // this.myCallback(this.page)

    },
    methods:{
        getAllCourse() {
            var token = localStorage.getItem('token');
            // console.log("token : " + token);
            var headers = {
                'Authorization': 'Bearer ' + token
            };
            this.$request.get(`http://localhost:8080/api/course/get`, { headers: headers }).then((response) => {
                console.log(response.data)
                this.courses = response.data;
                this.course
                console.log("Get all course")
            }
            )

        },
        myCallback(newPage) {
            var page = newPage - 1
           

            var token = localStorage.getItem('token');
            // console.log("token : " + token);
            var headers = {
               'Authorization': 'Bearer ' + token
            };
            this.$request.get('http://localhost:8080/api/score/get-subject-score?classId=' +this.selectedClass + '&subjectId=' + this.selectedSubject+ '&pageNo=' + page, { headers: headers }).then((response) => {
              this.scores = response.data.content;
               this.pageResponse = response.data
            }).catch((err) => {
               console.log(err)
            })

         },
        getAllCourseClasses(){       
            console.log(this.selectedCourse)
            this.$request.get(`http://localhost:8080/api/course/get/` + this.selectedCourse).then((response) => {
                console.log(response.data)
                this.courseClasses = response.data.classes.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                this.subjects = response.data.subjects.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                console.log("Get all course class")
            }
            )
        },
        search() {
            // var token = localStorage.getItem('token');

            // var headers = {
            //     'Authorization': 'Bearer ' + token
            // };
            if (this.selectedClass === '' || this.selectedSubject === '' ){
                this.$swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text:" Please select all the options ",

                });
            }else{
                // this.$request.get(`http://localhost:8080/api/score/get-subject-score?classId=` + this.selectedClass + `&subjectId=` + this.selectedSubject, { headers: headers }).then((response) => {
                //     console.log(response.data)
                //     this.scores = response.data.sort((a, b) => (a.user.id > b.user.id) ? 1 : ((b.user.id > a.user.id) ? -1 : 0));
                //     console.log("Get all scores")
                // }
                // )
                this.myCallback(this.page)
            }
            
            
        },

        save(score){
            console.log(score)
            var token = localStorage.getItem('token');

            var headers = {
                'Authorization': 'Bearer ' + token
            };
            this.$request.put(`http://localhost:8080/api/score/update`, score, { headers: headers }).then((response) => {
                    console.log(response.data)}).catch((err) =>{
                        this.$swal.fire({
                                icon: "error",
                                title: "Oops...",
                                text: err.response.data
                            });
                    })
        }
        
    }
}
</script>
<style scoped>
input, select {
    border: none;
    border-bottom: none;
    outline: none;
    text-align: center;
}
</style>