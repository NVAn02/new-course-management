<template>
   <div class="course-manager container-fluid" style="margin-top: 80px;">
      <!-- <h1>QUẢN LÝ KHÓA HỌC</h1> -->
      <div class="mb-5">
         <Splide :options="{ rewind: true,autoplay: true,interval: 4000, pagination: false }"
            aria-label="My Favorite Images" s>
            <SplideSlide :key="index" v-for="(slide,index) in slides">
               <div class="d-flex justify-content-center">
                  <img :src="slide.image" alt="" style=" width:100% ;border-radius:5px">
                  <div class="d-flex justify-content-center align-items-center"
                     style="position: absolute; background-color: white; width: 40%; height: 160px; top:50%; left:7%">
                     <div class="text-left ml-3">
                        <h2 style="font-weight: bolder;">{{slide.h1}}</h2>
                        <p>{{slide.p}}</p>
                     </div>
                  </div>
               </div>
            </SplideSlide>
         </Splide>
      </div>
      <div class="container  mb-5">
         <div class=" mb-5 ">
            <h1 style="font-weight: bolder;">The world's largest selection of courses</h1>
            <h5 style="font-weight: lighter;">Choose from 130,000 online video courses with new additions published
               every month</h5>
         </div>
         <div class="row d-flex justify-content-center container ">
            <div style="width: 1500px;">
               <Splide :options="splideOptions" aria-label="My Favorite Images" style="width: 700px;">
                  <SplideSlide v-for="(course, index) in courses" :key="index" class="d-flex justify-content-center">
                     <div style="width: 80%;">
                        <router-link :to="{ name: 'detail.course', params:{id: course.id}}"
                           style="text-decoration:none">
                           <div class="d-flex justify-content-center">
                              <img :src="course.image" alt="courseimg"
                                 style="width: 100%; height: 150px; border-radius: 5px;">
                           </div>
                           <div class="d-flex justify-content-center">
                              <div style="width: 100%;">
                                 <div class="name">
                                    <a>{{course.name}}</a>
                                 </div>
                                 <div class="description">
                                    <a> {{course.shortDescription}}</a>
                                 </div>
                                 <div class="price">
                                    <a>${{course.price}}</a>
                                 </div>
                              </div>
                           </div>
                        </router-link>
                     </div>
                  </SplideSlide>
               </Splide>
            </div>
         </div>

      </div>
      <div class="d-flex justify-content-center mt-5 mb-5">
         <div class="">
            <img src="https://preview.colorlib.com/theme/onedu/assets/img/gallery/about1.jpg" alt=""
               style="width: 100%;">
         </div>
         <div class="col-7 d-flex justify-content-center align-items-center" style="background-color: #F5FFFA;">
            <div class="text-left col-8 browse-course">
               <h1>The world’s largest selection of online courses</h1>
               <p style="font-size: larger; font-weight: lighter; color: #696969; line-height: 2;">Millions of people
                  have used Kingster
                  to decide which
                  online course to take. We aggregate courses
                  from
                  many universities
                  to help you find the best courses on almost any subject, wherever they exist. Our goal is to make
                  online education work
                  for everyone.</p>
               <div class="mt-5 btn d-flex justify-content-center align-items-center text-white"
                  style="height: 60px;  width: 180px; background-color: #473C8B;">
                  Browse Course
               </div>
            </div>
         </div>
      </div>
      <div class="mt-5 mb-5 container">
         <h1 style="font-weight: bolder;">Explore top categoriesn</h1>
         <div class="d-flex row justify-content-center mt-3">
            <div v-for="(category, index) in categoriesn" :key="index" class=" image-container col-3">
               <img :src="category.image" alt="" class="category-image">

               <h5 class="category-text" style="font-weight: bolder;  position: relative; top: -50%; color: white;">
                  {{category.text}}</h5>
            </div>
         </div>
      </div>
      <div class="d-flex text-left justify-content-center " style="background-color: #F0F8FF; padding: 80px;">
         <div class="col-5 mr-5 mt-5">
            <h1 style="font-weight: bolder; ">Become an Instructor</h1>
            <p style="font-weight: lighter; font-size: larger; line-height: 2">The automated process all your website
               tasks. Discover
               tools and techniques to engage effectively with
               vulnerable
               children and young people.</p>

            <p>
               <i class="fas fa-check"></i> Techniques to engage effectively with vulnerable children and young
               people.
            </p>

            <p>
               <i class="fas fa-check"></i> Join millions of people from around the world learning together.
            </p>

            <p>
               <i class="fas fa-check"></i> Join millions of people from around the world learning together.
            </p>

            <div class="mt-5">
               <div class="btn  d-flex justify-content-center align-items-center text-white"
                  style="width: 200px; height: 60px;background-color:  #473C8B;">
                  Become a Instructor
               </div>
            </div>

         </div>
         <div class="ml-5">
            <img src="https://preview.colorlib.com/theme/onedu/assets/img/gallery/about2.jpg" alt="">
         </div>
      </div>
   </div>

</template>
<script>


   export default {

      name: 'ListCourse',

      data() {
         return {
            categoriesn: [
               {
                  text: 'Programing',
                  image: 'https://preview.colorlib.com/theme/onedu/assets/img/gallery/categories1.jpg'
               },
               {
                  text: 'VFX',
                  image: 'https://preview.colorlib.com/theme/onedu/assets/img/gallery/categories2.jpg'
               },
               {
                  text: 'App Development',
                  image: 'https://preview.colorlib.com/theme/onedu/assets/img/gallery/categories3.jpg'
               },
               {
                  text: 'Technology',
                  image: 'https://preview.colorlib.com/theme/onedu/assets/img/gallery/categories4.jpg'
               },
               {
                  text: 'Graphics Design ',
                  image: 'https://preview.colorlib.com/theme/onedu/assets/img/gallery/categories5.jpg'
               },
               {
                  text: 'Music',
                  image: 'https://preview.colorlib.com/theme/onedu/assets/img/gallery/categories6.jpg'
               },
               {
                  text: 'Product Design',
                  image: 'https://preview.colorlib.com/theme/onedu/assets/img/gallery/categories7.jpg'
               },
               {
                  text: 'Video Ediiting',
                  image: 'https://preview.colorlib.com/theme/onedu/assets/img/gallery/categories8.jpg'
               },
            ],
            courses: [],
            slides: [
               {
                  image: 'https://img-c.udemycdn.com/notices/web_carousel_slide/image/80830e23-3f55-45f3-a18a-7851f9a7d6e0.jpg',
                  h1: "Prep for your IT certificate",
                  p: "Explore a future in IT. Start learning toward AWS certification, CompTIA A+ certification, and more."

               },
               {
                  image: 'https://img-c.udemycdn.com/notices/featured_carousel_slide/image/34c63aef-8d1f-483e-b0ea-0ead94879e56.jpg',
                  h1: "Learn from anywhere",
                  p: "On the couch, from the backyard, or on your commute. Our app lets you decide."
               },
               {
                  image: 'https://img-c.udemycdn.com/notices/featured_carousel_slide/image/487fb3b7-4b6e-4c2f-a3fe-67eb51016502.jpg',
                  h1: "Learn from anywhere",
                  p: "On the couch, from the backyard, or on your commute. Our app lets you decide."
               },
               {
                  image: 'https://img-c.udemycdn.com/notices/web_carousel_slide/image/d8c0d960-18d6-4cd1-9733-96217eae0104.jpg',
                  h1: "Sale ends today",
                  p: "Have a dream? Start learning your way to it with courses from ₫249,000."
               },
            ],
            splideOptions: {
               rewindSpeed: 1000,
               rewind: true,
               autoplay: true,
               interval: 3000,
               type: 'loop',
               perPage: 6,
               perMove: 1,
               pagination: false,
               arrows: false,
            }

         }
      },


      created() {
         this.getAll()
      },
      methods: {
         getAll() {

            this.$request.get("http://localhost:8080/api/course/get").then((response) => {
               console.log(response.data)
               this.courses = response.data
               console.log("Get all courses")


            }).catch(err => {
               // what now?
               console.log(err);
            })


         },
         
      }
   }
</script>
<style scoped>
   .name {
      color: black;
      font-weight: bolder;
   }

   .description {
      font-size: smaller;
      color: black;
   }

   .price {
      font-weight: bolder;
   }

   .course-item {
      margin-bottom: 200px;
      text-decoration: none;
   }

   .section {
      margin-top: 100px;
   }

   .image-container {
      position: relative;
      overflow: hidden;

   }

   .category-image {
      transition: filter 0.3s ease;

   }

   .category-text {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      color: white;
      font-weight: bold;

      transition: opacity 0.3s ease;

   }

   .category-image {
      filter: brightness(70%);

   }

   .image-container:hover .category-image {
      transform: scale(1.05);

   }

   .category-image {
      transition: transform 0.3s ease;

   }

   .name a,
   .description a {
      color: black;

   }



   .btn {
      border-radius: 10px;
   }

   .description a {
      display: inline-block;
      width: 100%;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      overflow: hidden;
      overflow: hidden;
      text-overflow: ellipsis;

   }
</style>