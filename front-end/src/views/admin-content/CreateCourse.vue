<template>
    <div class="container text-left">
        <div>
            <h1>Create new course</h1>
        </div>

        <div>
            <div class="d-flex">
                <div class="col-4">
                    <div class="mb-2">
                        <div>
                            <h4>Name:</h4>
                        </div>
                        <input type="text" class="form-control" v-model="newCourse.name">
                    </div>
                    <div class="mb-2">
                        <div>
                            <h4>Price:</h4>
                        </div>
                        <input type="text" class="form-control" v-model="newCourse.price">
                    </div>
                </div>
                <div class="col-6">
                    <div class="mb-2">
                        <div>
                            <h4>Image:</h4>
                        </div>
                        <input type="text" class="form-control" v-model="newCourse.image">
                    </div>
                    <div class="mb-2">
                        <div>
                            <h4>Short Description:</h4>
                        </div>
                        <input type="text" class="form-control" v-model="newCourse.shortDescription">
                    </div>


                </div>
            </div>
            <div class="mb-2">
                <label for="multi-select">
                    <h4>Chọn lịch học:</h4>
                </label>
                <div>

                    <div class="d-flex justify-content-end">
                        <div class="btn btn-primary mb-2" data-toggle="modal" data-target="#myModal"><i
                                class="fas fa-plus"></i> Add schedule</div>
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
                                                :id="'checkbox' + index" :value="schedule"
                                                v-model="newCourse.schedules">
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
                    <div class="d-flex justify-content-center">
                        <table class="table " s>
                            <thead class="bg-dark text-white">
                                <tr class="text-left">
                                    <th>No</th>
                                    <th>Buổi 1</th>
                                    <th>Buổi 2</th>
                                    <!-- <th>Hành động</th> -->
                                </tr>
                            </thead>
                            <tbody class="text-left">
                                <tr :key="index" v-for="(schedule, index) in newCourse.schedules">

                                    <td>{{ index + 1 }}</td>
                                    <td>{{ schedule.dayOne }}</td>
                                    <td>{{ schedule.dayTwo }}</td>

                                </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>

            <div>
                <div>
                    <div>
                        <h4>List Lesson</h4>
                    </div>
                </div>
                <div>
                    <div class="d-flex justify-content-end">
                        <div class="btn btn-primary mb-2" data-toggle="modal" data-target="#lessonList"><i
                                class="fas fa-plus"></i> Add lesson</div>
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
                                            <input style=" transform: scale(1.6)" type="checkbox"
                                                :id="'checkbox' + index" :value="subject" v-model="newCourse.subjects">
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
                            <tr :key="index" v-for="(subject, index) in newCourse.subjects">

                                <td>{{ index + 1 }}</td>
                                <td>{{ subject.name }}</td>
                                <td>{{ subject.content }}</td>
                                <!-- <td class="d-flex justify-content-center">
                                    <div class="btn btn-primary mr-2"> <i class="far fa-edit"></i> Edit</div>&nbsp;
                                    <div class="btn btn-danger mr-2" @click="onDeleteSubject(subject.id)"> Delete</div>
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
                    <textarea class="form-control" v-model="newCourse.lessonContent" style="height: 200px;"></textarea>
                </div>
                <div class="mb-2">
                    <div>
                        <h4>Requirement</h4>
                    </div>
                    <textarea class="form-control" v-model="newCourse.requirement" style="height: 200px;"></textarea>
                </div>
                <div class="mb-2">

                    <div>
                        <h4>Description</h4>
                    </div>
                    <textarea class="form-control" v-model="newCourse.description" style="height: 200px;"></textarea>
                </div>


            </div>
            <div class="text-left mt-5">
                <div class="btn text-white " style="background-color: #A020F0; width: 100px;" @click="save()">Save</div>

            </div>

        </div>
    </div>



</template>
<script>
export default {
    name:'CreateNewCourse',
    data(){
        return{
            subjects:[],
            schedules:[],
            // newLesson:{
            //     name:'',
            //     content:''
            // },
            newCourse:{
                name:'',
                image:'',
                price:'',
                shortDescription:'',
                lessonContent:'',
                requirement:'',
                description:'',
                subjects:[],
                schedules:[]
            }
        }
    },
    created() {
        this.getSchedule()
        this.getSubjects()
    },

    methods:{
        saveLesson() {
            // var newLesson = Object.assign({}, this.newLesson);
            // this.newCourse.subjects.push(newLesson)
            
        },
        save(){
            console.log(this.newCourse)
            var token = localStorage.getItem('token');

            var headers = {
                'Authorization': 'Bearer ' + token
            };
            this.$request.post('http://localhost:8080/api/course/add-new-course', this.newCourse, { headers: headers }).then((res) => {
                console.log(res.data)
                this.$swal.fire("Created new course", "", "success")
                this.$router.push('/admin-course-manager')

            }).catch(err => {
                // what now?
                console.log(err);
            })


        },

        
        onDeleteSubject(id){
            var subjects = this.newCourse.subjects;
            for (var i = 0; i < subjects.length; i++) {
                if (subjects[i].id === id) {
                    subjects.splice(i, 1);
                    break; // Dừng vòng lặp sau khi xóa phần tử
                }
            }

        },
        onDeleteSchedule(id) {
            var schedules = this.newCourse.schedules;
            for (var i = 0; i < schedules.length; i++) {
                if (schedules[i].id === id) {
                    schedules.splice(i, 1);
                    break; // Dừng vòng lặp sau khi xóa phần tử
                }
            }

        },
        getSchedule(){
            this.$request.get('http://localhost:8080/api/schedule/get').then((res) => {
                this.schedules = res.data
                this.schedules.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                console.log(this.schedules)

            }).catch(err => {
                // what now?
                console.log(err);
            })
        },
        getSubjects() {
            this.$request.get('http://localhost:8080/api/subject/get').then((res) => {
                this.subjects = res.data
                this.subjects.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                console.log(this.subjects)

            }).catch(err => {
                // what now?
                console.log(err);
            })
        }

    }

}
</script>
<style scoped>
h4 {
    text-align: left;
    font-weight: bold;
}

</style>