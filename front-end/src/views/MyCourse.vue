<template>
    <div class="my-course container-fluid pb-5 ">
        <div class="container bg-white p-0">

            <div class="bg-dark text-white w-100 py-3">
                <h1>Khóa học của tôi</h1>
            </div>
            <div class="row d-flex justify-content-start">
                <router-link :to="{ name: 'mycoursedetail', params:{id: course.id}}" :key="index"
                    v-for="(course,index) in courses" class="course-item col-3 ml-5 mb-5 mt-5">
                    <div class="d-flex justify-content-center">
                        <img :src="course.image" alt="courseimg" style="width: 100%; height:200px; border-radius: 5px;">
                    </div>
                    <div class="name">
                        <a>{{course.name}}</a>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        name: 'myCourse',
        data() {
            return {
                courses: []
            }
        },
        created() {
            this.getAll()
        },
        methods: {
            getAll() {

                var token = localStorage.getItem('token');

                var headers = {
                    'Authorization': 'Bearer ' + token
                };

                this.$request.get("http://localhost:8080/api/course/get-my-course", { headers: headers }).then((response) => {
                    console.log(response.data)
                    this.courses = response.data
                    console.log("Get all my courses")


                }).catch(err => {
                    // what now?
                    console.log(err);
                })

            },

        }
    }
</script>
<style scoped>
    .my-course {
        padding-top: 50px;
        min-height: 700px;
    }

    .course-item {
        text-decoration: none;
        color: black;
        font-size: larger;
        font-weight: bolder;
    }
</style>