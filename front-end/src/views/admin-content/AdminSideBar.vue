<template>
    <div class="side-bar">
        
            <div><img alt="Vue logo" class="nav-brand" src="@/assets/logo.png" style="width:90%"></div>
            <ul class="nav nav-pills mb-3  flex-column pl-2" id="pills-tab" role="tablist">
                <li class="nav-item mb-4 text-left">
                    <router-link to="/admin-home" class="item" id="pills-home-tab" data-toggle="pill" role="tab"
                        aria-controls="pills-home" aria-selected="true"> <i class="fas fa-home"></i> Home</router-link>
                </li>
                <li class="nav-item mb-4 text-left">
                    <router-link to="/admin-course-manager" class=" item " data-toggle="pill" role="tab"
                        aria-controls="pills-profile" aria-selected="false"><i class="fas fa-book-open"></i>
                        Quản Lý
                        Khóa Học</router-link>
                </li>

                <li class="nav-item mb-4 text-left">
                    <router-link to="/admin-subject-manager" class=" item " id="pills-contact-tab" data-toggle="pill"
                        role="tab" aria-controls="pills-contact" aria-selected="false"><i class="fas fa-list-ul"></i>
                        Quản
                        Lý Môn Học</router-link>
                </li>
                <li class="nav-item mb-4 text-left">
                    <router-link to="/admin-quiz-manager" class=" item " id="pills-contact-tab" data-toggle="pill"
                        role="tab" aria-controls="pills-contact" aria-selected="false"><i class="far fa-question-circle"></i>
                        Quản
                        Lý Quiz Test</router-link>
                </li>
                <li class="nav-item mb-4 text-left">
                    <router-link to="/admin-score-manager" class=" item " id="pills-contact-tab" data-toggle="pill"
                        role="tab" aria-controls="pills-contact" aria-selected="false"><i
                            class="fas fa-graduation-cap"></i> Quản
                        Lý Điểm</router-link>
                </li>
                <li class="nav-item mb-4 text-left">
                    <router-link to="/admin-teacher-manager" class=" item " id="pills-contact-tab" data-toggle="pill"
                        role="tab" aria-controls="pills-contact" aria-selected="false"><i
                            class="fas fa-chalkboard-teacher"></i> Quản
                        Lý Giáo Viên</router-link>
                </li>
                <li class="nav-item mb-4 text-left">
                    <router-link to="/admin-user-manager" class=" item " id="pills-contact-tab" data-toggle="pill"
                        role="tab" aria-controls="pills-contact" aria-selected="false"><i class="fas fa-users"></i> Quản
                        Lý Học Sinh</router-link>
                </li>
            </ul>
        
    </div>

</template>
<script>
export default {
    name: 'AdminSideBar'
}
</script>
<style scoped>
.side-bar {
    
    /* min-height: 100vh; */

    /* border-bottom-left-radius: 20px;
        border-top-left-radius: 20px; */
    /* background-color: #8A2BE2 */
    background-color: #473C8B;
    margin-bottom: 50px;
}

.nav-item {
    margin-left: 3%;
        width: 90%;
        font-size: 100;
        font-weight: bolder;
        border-bottom: 1px solid #EEEEEE;
        transition: opacity 0.3s ease;

    }

    .nav-item:hover {

        opacity: 0.5;
        
    }

    .nav-item .btn {
        width: 100%;
    }

    .item {
        text-decoration: none;


    }

    .nav-item a {
        color: white;

        font-weight: bold;
    }
    .side-bar {
            position: sticky;
            /* Đảm bảo sidebar không scroll ra khỏi màn hình */
            top: 0;
            /* Đặt sidebar ở đầu trang */
            height: calc(100vh - 10px);
            /* Chiều cao tối đa của sidebar, với 60px là chiều cao của footer */
            /* overflow-y: auto;
            Thêm thanh cuộn khi sidebar quá cao */
    }

</style>