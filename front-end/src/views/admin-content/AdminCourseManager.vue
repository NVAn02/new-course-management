<template>
   <div class="container pt-5">
      <div>
         <h1>Course Management</h1>
      </div>
      <div class="course-table">
         <div class=" mb-2 d-flex justify-content-end">
            <RouterLink to="/admin-create-course" class="btn btn-primary"> <i class="fas fa-plus"></i> Add New Course
            </RouterLink>
         </div>
         <table class="table">
            <thead class="bg-dark text-white">
               <tr class="text-left">
                  <th>No</th>
                  <th>Tên khóa học</th>
                  <th>Số lớp vận hành</th>
                  <th>Số học sinh đang học</th>
                  <th>Hành động</th>
               </tr>
            </thead>
            <tbody class="text-left">
               <tr :key="index" v-for="(course,index) in courses">
                  <!-- <td>{{ course.id }}</td> -->
                  <td>{{index +1}}</td>
                  <td>{{ course.name }}</td>

                  <td>{{ course.classes.length }}</td>
                  <td>{{ course.classes.reduce((total, courseClass) => total + courseClass.students.length, 0) }}</td>

                  <td class="d-flex justify-content-center">
                     <router-link :to="{ name: 'AdminCourseEdit', params:{id: course.id}}" class="btn btn-primary mr-2">
                        <i class="far fa-edit"></i> Edit</router-link>&nbsp;

                     <router-link :to="{ name: 'AdminCourseDetail', params:{id: course.id}}" class="btn text-white "
                        style="background-color: #A020F0;"><i class="fas fa-list-ul"></i> Danh sách lớp</router-link>
                     <!-- <button class="btn btn-danger" @click="onDelete(course.id)"> Delete</button> -->
                  </td>
               </tr>
            </tbody>
         </table>
         <pagination v-model="page" :records="pageResponse.totalElements" :per-page="pageResponse.pageSize"
            @paginate="myCallback" />
      </div>

   </div>


</template>
<script>
   import Pagination from 'v-pagination-3';
   export default {
      name: 'AdminCourseManager',
      data() {
         return {
            courses: [],
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
      created() {
         this.myCallback(this.page)

      },
      methods: {
         myCallback(newPage) {
            var page = newPage - 1
            console.log('Page changed to:', page);

            var token = localStorage.getItem('token');
            // console.log("token : " + token);
            var headers = {
               'Authorization': 'Bearer ' + token
            };
            this.$request.get('http://localhost:8080/api/course/get-page?pageNo=' + page, { headers: headers }).then((response) => {
               this.courses = response.data.content;
               this.pageResponse = response.data
            }).catch((err) => {
               console.log(err)
            })

         },
         // getAllCourse() {
         //    var token = localStorage.getItem('token');
         //    // console.log("token : " + token);s
         //    var headers = {
         //       'Authorization': 'Bearer ' + token
         //    };
         //    this.$request.get(`http://localhost:8080/api/course/get-page`, { headers: headers }).then((response) => {
         //       console.log(response.data)
         //       this.courses = response.data.content;
         //       this.pageResponse = response.data

         //       console.log("Get all course")
         //    }
         //    )

         // }
      }
   }
</script>
<style scoped>

</style>