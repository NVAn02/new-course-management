<template>
    <div class="overlay" v-if="showSpinner"></div>
    <div class="spinner" v-if="showSpinner">
        <!-- Thêm ảnh GIF spinner hoặc nội dung spinner vào đây -->
        <img src="@/assets/ajax-loader.gif" alt="Loading...">
    </div>
    <div class="forgot-password container-fluid d-flex justify-content-center align-item-center"
        style="background-color: #EEEEEE;">
        <div class=" forgot-password-form  col-4" style="background-color: white; border-radius: 20px; height: 300px;">
            <div class="mt-2" style="border-bottom: 1px solid #EEEEEE;">
                <h3> Tìm tài khoản của bạn</h3>
            </div>
            <div class="text-left mt-2">
                <p>Vui lòng nhập email hoặc số di động để tìm kiếm tài khoản của bạn.</p>
                <input @blur="validate()" v-model="email" type="text" class=" form-control mb-2"
                    placeholder="Enter your email" style="width: 100%; height: 50px;">
                <span v-show="errors" style="color: red;">
                    {{ errors }}</span>
            </div>
            <div class="d-flex mt-5 justify-content-end">
                <router-link to="/login" class="btn " style="background-color: #EEEEEE; color:black">Hủy</router-link>
                <div @click="send()" class="btn ml-2 text-white" style="background-color: #473C8B;">Tìm Kiếm</div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name:"ForgotPassword",
    data(){
        return{
            email:'',
            showSpinner:false,
            errors:""
        }
    },
    methods:{
        validate(){
            this.errors = ""
            if(this.email === ''){
                this.errors = "Email is required";
                return false
            }
            return true
        },
        send(){
            if(this.validate()){
                this.showSpinner = true
                console.log(this.email)
                this.$request.post('http://localhost:8080/api/user/forgot-password/' + this.email).then((res) => {
                    console.log(res.data)
                    this.showSpinner = false
                    this.$swal.fire({
                        title: "Gửi mã OTP thành công",
                        html: "Vui lòng kiểm tra email!<br>",
                        icon: "success"
                    });
                    this.$router.push({
                        path: '/change-password/' + this.email
                    });
                }).catch((err) => {
                    this.showSpinner = false
                    console.log(err)
                    this.$swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: err.response.data,

                    });

                })
            }
            
        }
    }
}
</script>
<style scoped>
.forgot-password{
    min-height: 800px;
}
.forgot-password-form{
    margin-top: 130px;
}
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            /* Màu nền đen với độ mờ */
            z-index: 9999;
            /* Đảm bảo nó hiển thị trên tất cả các phần khác */
        }
    
        /* CSS để căn giữa spinner */
        .spinner {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 10000;
            /* Đảm bảo nó hiển thị trên lớp overlay */
        }
</style>