<template>
  <div class="footer pt-5 pb-5  text-white" style="background-color: #444444;">
    <!-- <div class="d-flex">
      <div class="col-4 mt-3">
        <img src="../assets/logo.png" alt="" style="width:150px ;">
      </div>
      <div class="col-6 row mt-3">
        <ul v-for=" (item, index) in footerContent" :key="index">
          <li class="col-12">{{ item.text }}</li>
        </ul>
      </div>
    </div> -->
    <div class="d-flex" style="width: 100%;">
      <div class="col-3 pl-5 d-flex justify-content-center">
        <div class=" text-left">
          <div>
            <img src="https://preview.colorlib.com/theme/onedu/assets/img/logo/logo2_footer.png.webp" alt="">
          </div>
          <div class="mt-3">
            <p>The automated process starts as soon as your clothes go into the machine.</p>
          </div>
          <div class="d-flex mt-3">
            <i class="fab fa-twitter fa-2x mr-3"></i>
            <i class="fab fa-facebook-square fa-2x mr-3"></i>
            <i class="fab fa-pinterest fa-2x mr-3"></i>
          </div>
        </div>
      </div>
      <div class="col-2">
        <div class="text-left">
          <h4>Subjects</h4>
          <p>Design & creatives</p>
          <p>Telecommunication</p>
          <p>Restaurant</p>
          <p>Programing</p>
          <p>Architecture</p>
        </div>
      </div>
      <div class="col-2">
        <div class="text-left">
          <h4>Community</h4>
          <p>Design & creatives</p>
          <p>Telecommunication</p>
          <p>Restaurant</p>
          <p>Programing</p>
          <p>Architecture</p>
        </div>
      </div>
      <div class="col-2">
        <div class="text-left">
          <h4>Company</h4>
          <p>Design & creatives</p>
          <p>Telecommunication</p>
          <p>Restaurant</p>
          <p>Programing</p>
          <p>Architecture</p>
        </div>
      </div>
      <div class="col-3 pr-5">
        <div class="text-left">
          <h4>Newsletter</h4>
          <p>Subscribe our newsletter to get updates.</p>
          <input type="text" name="" id="" class="form-control" placeholder="Enter your email"
            style="height: 50px; width: 300px;">
          <div class="btn mt-2 d-flex align-items-center justify-content-center"
            style="background-color:#FF4500; height: 50px; width: 300px;">
            Subscribe
          </div>
        </div>
      </div>

    </div>


    <div class="mt-5">
      Copyright ©2024 All rights reserved | This template is made with <i class="fas fa-heart"></i> by Colorlib
    </div>

  </div>



</template>
<script>
export default{
    name:'FooterComponent',
    data() {
        return{
            footerContent:[
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                {text:'Udemy Business'},
                
            ]
        }
    }
}
</script>
<style scoped>
.footer p{
  font-size: smaller;
  font-weight: lighter;
}
/* .footer{
  margin-top:100px;
} */



</style>