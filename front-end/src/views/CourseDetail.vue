<template>
    <div class="course-detail container-fluid pb-5 mb-5 px-0" style="margin-top: 3% ">
        <div class="overlay" v-if="showSpinner"></div>
        <div class="spinner" v-if="showSpinner">
            <!-- Thêm ảnh GIF spinner hoặc nội dung spinner vào đây -->
            <img src="../assets/ajax-loader.gif" alt="Loading...">
        </div>
        <div class="side-bar" style="border-radius: 10px;">
            <div class="preview row d-flex justify-content-center mb-2 mt-2">
                <img :src="course.image" alt="" style="border-radius: 5px; width:88%; height: 200px;">
            </div>
            <div class="buy-box ">
                <div class="price ml-2">
                    <span>${{course.price}}</span>
                </div>
                <!-- <div class="buy-box-btn">
                    <button class="add-to-cart" data-toggle="modal" data-target="#register"><span></span>
                    </button>
                </div> -->
                <div class="buy-box-btn">
                    <button class="buy-now" data-toggle="modal" data-target="#buyNowModal"
                        style="border-radius: 5px;"><span><i class="fas fa-shopping-cart"></i> Đăng kí ngay</span>
                    </button>
                </div>


                <span class="money-back" style="font-style: italic;">Đảm bảo hoàn tiền trong 30 ngày</span>
            </div>
            <div class="incentives">
                <h5>Khóa học này bao gồm:</h5>
                <ul class="list-incentives" style="font-size: 80%;">
                    <li><i class="fas fa-tv"></i> Truy cập trên thiết bị di động và TV</li>
                    <li><i class="fas fa-infinity"></i> Quyền truy cập đầy đủ suốt đời</li>
                    <li><i class="fas fa-certificate"></i> Giấy chứng nhận hoàn thành</li>

                </ul>
            </div>
            <div>
                <div>
                    <div class="modal" id="buyNowModal">
                        <div class="modal-dialog d-flex justify-content-center" style="max-width: none;">
                            <div class="modal-content" style="width: 700px">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title"> Đăng kí khoá học</h4>

                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <div>
                                        <div>
                                            <h4>Tên khóa học: {{ course.name }}</h4>
                                        </div>
                                        <div class="text-left mt-5">
                                            <div class="form-group d-flex">
                                                <label class="col-3" for="registerName">
                                                    <h5>Name</h5>
                                                </label>
                                                <div style="width: 100%;">
                                                    <input @blur="validate()" type="email" class="form-control"
                                                        id="registerName" v-model="register.name">
                                                    <span style="color: red;"> {{ errors.name }} </span>
                                                </div>


                                            </div>

                                            <div class="form-group d-flex ">
                                                <label class="col-3" for="registerEmail">
                                                    <h5>Email</h5>
                                                </label>
                                                <div style="width: 100%;">
                                                    <input @blur="validate()" type="email" class="form-control"
                                                        id="registerEmail" v-model="register.email">
                                                    <span style="color: red;"> {{ errors.email }} </span>
                                                </div>

                                            </div>
                                            <div class="form-group d-flex">
                                                <label class="col-3" for="registerPhone">
                                                    <h5>Phone number</h5>
                                                </label>
                                                <div style="width: 100%;">
                                                    <input @blur="validate()" type="text" class="form-control"
                                                        id="registerPhone" v-model="register.phone">
                                                    <span style="color: red;"> {{ errors.phone }} </span>
                                                </div>

                                            </div>
                                            <div class="form-group d-flex">
                                                <label class="col-3" for="registerAddress">
                                                    <h5>Address</h5>
                                                </label>
                                                <input type="text" class="form-control" id="registerAddress"
                                                    v-model="register.address">
                                            </div>
                                            <div class="form-group d-flex">
                                                <div class="col-3">
                                                    <h5>Chọn lịch học: </h5>
                                                </div>
                                                <div style="width: 100%;">
                                                    <select @change="validate()" v-model="register.schedule"
                                                        class="multi-select form-control" style="font-size: larger;">
                                                        <option v-for="(schedule, id) in course.schedules"
                                                            :value="schedule" :key="id">{{ schedule.dayOne }} |
                                                            {{ schedule.dayTwo }}
                                                        </option>
                                                    </select>
                                                    <span style="color: red;"> {{ errors.schedule }} </span>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="text-left">
                                        </div>
                                    </div>

                                </div>
                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn text-white " style="background-color:#473C8B;"
                                        @click="pay">Send</button>
                                    <button type="button" class="btn " data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="header d-flex align-items-center  mt-4" style="width: 100%;">
            <div class="col-7 ml-5" style="text-align: left;">
                <div class="name"> {{ course.name }}</div>
                <div class="description ">{{ course.shortDescription }}</div>
                <div class="last-update mt-3" style="font-size: smaller;"><i class="far fa-calendar-check"></i> Lần
                    cập
                    nhật gần đây nhất
                    {{created}}
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center col-10" style="min-height: 1000px;">
            <div class="col-10">
                <div class="main-content">
                    <div class="lesson-content container mt-5">
                        <div style="border-color:black; border-width: 1px; border-style: solid;">
                            <div class="text-left pl-2 mt-3 mb-3">
                                <h2>Nội dung bài học</h2>

                            </div>
                            <div class="d-flex justify-content-center">

                                <ul class="what-you-will-learn d-flex row col-10" style="text-align: left;">
                                    <li v-for="(content, index) in lessonContent" :key="index"
                                        class="col-6 mt-3 d-flex">
                                        <div class="mr-2">
                                            <i class="fas fa-check"></i>
                                        </div>
                                        <div>
                                            {{ content }}
                                        </div>
                                    </li>
                                </ul>

                            </div>
                        </div>

                        <div class="course-content container">
                            <div class="text-left mt-3 mb-3">
                                <h2>Chi tiết khóa học</h2>
                            </div>
                            <div>
                                <div class="accordion" id="accordionExample">
                                    <div :key="index" v-for="(subject,index) in course.subjects" class="card">
                                        <div class="card-header" id="headingOne">
                                            <h2 class="mb-0">
                                                <button class="btn btn-link btn-block text-left" type="button"
                                                    data-toggle="collapse" :data-target="'#collapse' +subject.id"
                                                    aria-expanded="true" :aria-controls="'#collapse' +subject.id">
                                                    {{subject.name}}
                                                </button>
                                            </h2>
                                        </div>

                                        <div :id="'collapse' +subject.id" class="collapse ">
                                            <div class="card-body">
                                                <!-- {{subject.content}} -->
                                                <ul>
                                                    <li class="mt-2"
                                                        v-for="(content, index) in subject.content.split('\n') "
                                                        :key="index">
                                                        <i class="fab fa-youtube"></i> {{ content }}

                                                    </li>
                                                </ul>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="requirement">
                            <div class="text-left mt-3 mb-3">
                                <h2>Yêu cầu</h2>

                            </div>
                            <!-- <ul>
                                <li>Học viên cần có chút kiến thức cơ bản về lập trình, có kiên trì và sự nỗ lực để hoàn
                                    thành các bài tập và dự án trong
                                    khóa học</li>
                            </ul> -->
                            <div class="text-left ml-5 mb-2">
                                {{course.requirement}}
                            </div>

                        </div>
                        <div class="short-description mt-3  text-left">
                            <div class="mb-3">
                                <h2>Mô tả</h2>
                            </div>
                            <div class="description" style="font-size: 15px;">
                                {{course.description}}

                            </div>
                        </div>
                    </div>
                </div>


                <!-- <div class="footer"></div> -->
            </div>
        </div>
    </div>


</template>
<script>



    export default {


        name: 'courseDetail',

        data() {
            return {
                isLoaded: false,
                // selectSchedule: '',
                isLoading: false, // Biến để theo dõi trạng thái của request
                showSpinner: false, // Biến để hiển thị hoặc ẩn spinner và overlay
                course: {},
                lessonContent: [],
                subjects: [],
                created: '',
                register: {
                    name: '',
                    email: '',
                    phone: '',
                    address: '',
                    schedule: '',
                    courseId: ''
                },
                errors: {
                    name: '',
                    email: '',
                    phone: '',
                    schedule: ''

                }
            }
        },

        created() {
            let courseId = this.$route.params.id
            this.register.courseId = courseId
            this.getCourse(courseId)
            // this.getSubject(courseId)
        },

        methods: {

            getCourse(id) {
                this.$request.get("http://localhost:8080/api/course/get/" + id).then((response) => {
                    console.log(response.data)
                    this.course = response.data
                    this.course.schedules.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                    this.course.subjects.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0));
                    this.created = this.course.created.slice(0, 10)
                    this.lessonContent = this.course.lessonContent.split("\n")

                }).catch(err => {
                    console.log(err);
                })
                console.log("Course id: " + id)
            },

            addToCart() {
                this.$swal.fire("Added to cart!", "", "success")

            },
            pay() {
                if (this.validate()) {
                    this.showSpinner = true;
                    console.log(this.register)

                    this.$request.post("http://localhost:8080/api/register/register", this.register).then(res => {
                        this.showSpinner = false
                        // Xử lý response
                        this.isLoading = false;
                        console.log(res);

                        this.$swal.fire({
                            title: "Đăng kí thành công",
                            html: "Vui lòng kiểm tra email!<br>",
                            icon: "success"
                        });

                    }).catch((err) => {
                        this.showSpinner = false;
                        console.log(err.status);

                        // this.$swal.close();

                        this.$swal.fire({
                            icon: "error",
                            title: "Oops...",
                            text: err.response.data.message,

                        });
                    });

                }

            },
            validate() {
                this.errors = {
                    name: '',
                    email: '',
                    phone: '',
                    schedule: ''

                }
                if (!this.register.name) {

                    this.errors.name = "Please enter your name"
                    return false
                }
                if (!this.register.email) {

                    this.errors.email = "Please enter your email"
                    return false
                }
                if (!this.register.phone) {

                    this.errors.phone = "Please enter your phone number"
                    return false
                }
                if (!this.register.schedule) {

                    this.errors.schedule = "Please select your class schedule"
                    return false
                }
                return true
            }
        }

    }

</script>
<style scoped>
    .side-bar {

        position: sticky;
        background-color: #E8E8E8;
        height: fit-content;
        position: absolute;
        width: 20%;
        right: 3%;
        top: 17%
    }

    .side-bar img {
        width: 90%;


    }

    .name {
        font-size: xx-large;
        font-weight: 700;
    }

    .description {
        font-weight: 500;
        font-size: larger;
    }

    .header {
        height: 200px;
        background-color: #333333;
        color: white;
    }



    .price {
        text-align: left;
        font-weight: 900;
        font-size: 30px;
        margin-bottom: 10px;
    }

    .buy-box-btn {
        display: flex;
        text-align: center;
        justify-content: center;
        margin-bottom: 20px;
        height: 50px;

    }

    .buy-box-btn button {
        font-weight: bolder;
        font-size: larger;

        width: 95%;
    }

    .buy-now {
        color: white;
        background-color: #473C8B;

    }

    .money-back {
        font-size: 14px;

    }

    .incentives {
        margin-left: 5px;
        margin-top: 10px;
        text-align: left;
    }

    .list-incentives,
    .what-you-will-learn {
        font-size: 13px;
        list-style-type: none;
    }

    .incentives h5 {
        font-weight: 700;
    }

    h2 {
        font-weight: 700;
    }

    .btn-link {
        text-decoration: none;
        color: black;
        font-weight: 700;
    }

    .card-body ul {
        text-align: left;
        list-style-type: none;

    }

    .modal-content {
        width: 900px;
    }

    .payment:hover {
        transform: translateY(-5px);
        /* Di chuyển phần tử lên trên khi di chuột qua */
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        /* Tạo bóng đổ */

    }

    .description {
        font-size: 15px;
        white-space: pre-line;
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

    .requirement {
        white-space: pre-line;
        line-height: 3;
    }

    .description {
        line-height: 2;
    }
</style>