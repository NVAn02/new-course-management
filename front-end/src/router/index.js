import { createRouter, createWebHistory } from 'vue-router'

const requireAuthUser = (to, from, next) => {
  const role = sessionStorage.getItem('role')

  if (role !== "ROLE_USER") {
    next({ name: "login", params: {} })
  }
  else next();

};
const requireAuthAdmin = (to, from, next) => {
  const role = sessionStorage.getItem('role')

  if (role !== "ROLE_ADMIN") {
    next({ name: "login", params: {} })
  }
  else next();

};



const routes = [
  {
    path: '/',
    name: 'course',
    component: () => import('../views/CourseManage.vue')
  },


  {
    path: '/course/detail/:id',
    name: 'detail.course',

    component: () => import('../views/CourseDetail.vue')
  },
  {
    path: '/login',
    name: 'login',

    component: () => import('../views/form/LoginForm.vue')
  },
  {
    path: '/register',
    name: 'register',

    component: () => import('../views/form/RegisterForm.vue')
  },
  {
    path: '/userinfo',
    name: 'userinfo',

    component: () => import('../views/UserInformation.vue')
  },
  {
    path: '/admin',
    name: 'admin',
    component: () => import('../views/admin-content/AdminHome.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/mycourse',
    name: 'mycourse',
    component: () => import('../views/MyCourse.vue'),
    beforeEnter: requireAuthUser
  },
  {
    path: '/mycoursedetail/:id',
    name: 'mycoursedetail',
    component: () => import('../views/MyCourseDetail.vue'),
    beforeEnter: requireAuthUser
  },
  {
    path: '/admin-course-manager',
    name: 'AdminCourseManager',
    component: () => import('../views/admin-content/AdminCourseManager.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-user-manager',
    name: 'UserManager',
    component: () => import('../views/admin-content/UserManager.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-home',
    name: 'AdminHome',
    component: () => import('../views/admin-content/AdminHome.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-course-detail/:id',
    name: 'AdminCourseDetail',
    component: () => import('../views/admin-content/AdminCourseDetail.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-course-edit/:id',
    name: 'AdminCourseEdit',
    component: () => import('../views/admin-content/CourseEdit.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-class-detail/:classId/:courseId',
    name: 'AdminclassDetail',
    component: () => import('../views/admin-content/AdminClassDetail.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-create-class/:id',
    name: 'AdminCreateClass',
    component: () => import('../views/admin-content/CreateClass.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-create-course',
    name: 'AdminCreateCourse',
    component: () => import('../views/admin-content/CreateCourse.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-subject-manager',
    name: 'AdminSubjectManager',
    component: () => import('../views/admin-content/AdminSubjectManager.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-score-manager',
    name: 'AdminScoreManager',
    component: () => import('../views/admin-content/AdminScoreManager.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-teacher-manager',
    name: 'AdminTeacherManager',
    component: () => import('../views/admin-content/AdminTeacherManager.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/admin-teacher-detail/:id',
    name: 'AdminTeacherDetail',
    component: () => import('../views/admin-content/AdminTeacherDetail.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/mail-template',
    name: 'MailTemplate',
    component: () => import('../views/admin-content/MailTemplate.vue'),
    beforeEnter: requireAuthAdmin
  },

  {
    path: '/mail-account',
    name: 'EmailAccount',
    component: () => import('../views/admin-content/EmailAccount.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/course-register/:courseId/:scheduleId',
    name: 'CourseRegister',
    component: () => import('../views/admin-content/AdminRegisterCourseDetail.vue'),
    beforeEnter: requireAuthAdmin
  },
  {
    path: '/forgot-password',
    name: 'ForgotPassword',
    component: () => import('../views/form/ForgotPassword.vue')
  },
  {
    path: '/change-password/:email',
    name: 'ChangePassword',
    component: () => import('../views/form/ChangePassword.vue')
  },
  {
    path: '/admin-quiz-manager',
    name: 'AdminQuizManager',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/admin-content/AdminQuizManager.vue'),
    beforeEnter: requireAuthAdmin

  },
  {
    path: '/quiz/:subjectId/:classId',
    name: 'Quiz',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/QuizTest.vue'),
    beforeEnter: requireAuthUser

  },
  {
    path: '/subject-question-edit/:subjectId',
    name: 'SubjectQuestion',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/admin-content/SubjectQuestionEdit.vue'),
    beforeEnter: requireAuthAdmin

  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior() {
    // always scroll to top
    return { top: 0 }
  }

})

export default router
