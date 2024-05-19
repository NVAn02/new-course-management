<template>
  <HeaderComponent></HeaderComponent>
  <div class="d-flex ">
    <div v-if="isAdmin" class="col-2 " style="position: relative; left: -1%;">
      <AdminSideBar></AdminSideBar>
    </div>
    <router-view />
  </div>
  <FooterComponent></FooterComponent>
</template>

<script>

  import FooterComponent from './/views/FooterComponent';
  import HeaderComponent from './/views/HeaderComponent';
  import AdminSideBar from './/views/admin-content/AdminSideBar';


  export default {
    name: 'App',
    components: {
      FooterComponent,
      HeaderComponent,
      AdminSideBar
    },
    computed: {
      isAdmin() {
        // console.log("role in localStorage: " + localStorage.getItem('role'))
        console.log("isRoleAdmin : " + this.$store.state.isRoleAdmin)
        return this.$store.state.isRoleAdmin
      }
    },

  }
</script>


<style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  /* nav {
    padding: 30px;
  }

  nav a {
    font-weight: bold;
    color: #2c3e50;
  }


  .nav-item {
    transition: all 0.3s ease;
  }

  .nav-item:hover {
    transform: translateY(-3px);
  }

  a {
    text-decoration: none;
  }

  .search-btn {
    background-color: #A020F0;
    color: white;
  }

  .loggin-btn :hover,
  .register-btn :hover {
    color: #A020F0;

  } */

  table {
    border-collapse: collapse;
    width: 100%;
  }

  th,
  td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
  }

  th {
    color: #333333;
    background-color: #f2f2f2;
  }

  table {
    border-collapse: collapse;
    /* Đảm bảo các ô trong bảng không tạo khoảng trắng */
  }

  table,
  th,
  td {
    border: none;
    /* Bỏ viền cho bảng, ô tiêu đề, và ô dữ liệu */
  }
</style>