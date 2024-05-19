<template>
    <div class="container pt-5">
        <div class="overlay" v-if="showSpinner"></div>
        <div class="spinner" v-if="showSpinner">
            <!-- Thêm ảnh GIF spinner hoặc nội dung spinner vào đây -->
            <img src="@/assets/ajax-loader.gif" alt="Loading...">
        </div>
        <h1>Email Account</h1>
        <div>
            <div class="d-flex justify-content-end mt-4 mb-4">
                <div class="btn btn-primary" data-toggle="modal" data-target="#myModal"><i class="fas fa-plus"></i> Add
                    mail account
                </div>
                <div class="modal " id="myModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Add new account</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <!-- Modal body -->
                            <div class="modal-body text-left">
                                <div class="d-flex mb-4">
                                    <div class="col-4">
                                        <h4>Name: </h4>
                                    </div>

                                    <input type="text" class="form-control" v-model="newAccount.name">
                                </div>
                                <div class="d-flex mb-4">
                                    <div class="col-4">
                                        <h4>Host: </h4>
                                    </div>

                                    <input type="text" class="form-control" v-model="newAccount.host">
                                </div>
                                <div class="d-flex mb-4">
                                    <div class="col-4">
                                        <h4>Port: </h4>
                                    </div>

                                    <input type="text" class="form-control" v-model="newAccount.port">
                                </div>
                                <div class="d-flex mb-4">
                                    <div class="col-4">
                                        <h4>Email: </h4>
                                    </div>

                                    <input type="text" class="form-control" v-model="newAccount.email">
                                </div>
                                <div class="d-flex  mb-4">
                                    <div class="col-4">
                                        <h4>Password: </h4>
                                    </div>
                                    <input type="text" class="form-control" v-model="newAccount.password">
                                </div>

                            </div>
                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <div class="btn btn-primary" @click="save" data-dismiss="modal">Save</div>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table">
                <thead class="bg-dark text-white">
                    <tr>
                        <th>No</th>
                        <th>Name </th>
                        <th>Email</th>
                        <!-- <th>Password</th> -->
                        <th>Hành động</th>

                    </tr>
                </thead>
                <tbody>
                    <tr :key="index" v-for="(mailAccount, index) in mailAccounts">
                        <td>{{ index + 1 }}</td>

                        <td>{{ mailAccount.name }}</td>
                        <td>{{ mailAccount.email }}</td>


                        <td>
                            <div class="btn text-white" @click="testConnect(mailAccount)"
                                style="background-color: #A020F0;"><i class="fas fa-wifi"></i>
                                Test Connection</div>
                            &nbsp;
                            <div class="btn btn-primary" data-toggle="modal" data-target="#edit"
                                @click="getAccount(mailAccount.id)"><i class="far fa-edit"></i> Edit</div>
                            <div class="modal" id="edit">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <!-- Modal Header -->
                                        <div class="modal-header">
                                            <h4 class="modal-title">Edit mail account</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <!-- Modal body -->
                                        <div class="modal-body text-left">

                                            <div class="d-flex  mb-4">
                                                <div class="col-4">
                                                    <h4>Name: </h4>
                                                </div>
                                                <input type="text" class="form-control" v-model="editAccount.name">
                                            </div>
                                            <div class="d-flex  mb-4">
                                                <div class="col-4">
                                                    <h4>Host: </h4>
                                                </div>
                                                <input type="text" class="form-control" v-model="editAccount.host">
                                            </div>
                                            <div class="d-flex  mb-4">
                                                <div class="col-4">
                                                    <h4>Port: </h4>
                                                </div>
                                                <input type="text" class="form-control" v-model="editAccount.port">
                                            </div>
                                            <div class="d-flex  mb-4">
                                                <div class="col-4">
                                                    <h4>Email: </h4>
                                                </div>
                                                <input type="text" class="form-control" v-model="editAccount.email">
                                            </div>
                                            <div class="d-flex  mb-4">
                                                <div class="col-4">
                                                    <h4>Password: </h4>
                                                </div>
                                                <input type="text" class="form-control" v-model="editAccount.password">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <div class="btn btn-primary" @click="update(editAccount)"
                                                data-dismiss="modal">
                                                Save</div>
                                            <button type="button" class="btn btn-danger"
                                                data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                    <!-- Modal footer -->

                                </div>
                            </div>
                            &nbsp;
                            <div @click="onDelete(mailAccount.id)" class="btn btn-danger"> Delete</div>

                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

    </div>
</template>
<script>
    export default {
        setup() {

        },
        name: 'EmailAccount',
        data() {
            return {
                isLoading: false,
                showSpinner: false,
                mailAccounts: [],
                newAccount: {
                    name: '',
                    email: '',
                    password: ''
                },
                editAccount: ''
            }
        },
        computed: {
            headers() {
                var token = localStorage.getItem('token');
                return {
                    'Authorization': 'Bearer ' + token
                };
            }
        },
        created() {
            this.getAllMailAccount()
        },
        methods: {
            getAllMailAccount() {
                // var token = localStorage.getItem('token');

                // var headers = {
                //     'Authorization': 'Bearer ' + token
                // };
                this.$request.get('http://localhost:8080/api/mail-account/get', { headers: this.headers }).then((res) => {
                    console.log("Get all mail account")
                    this.mailAccounts = res.data;
                    console.log(this.mailAccounts)
                })
            },
            getAccount(id) {
                this.$request.get('http://localhost:8080/api/mail-account/get/' + id, { headers: this.headers }).then((res) => {
                    console.log("Get mail account")
                    this.editAccount = res.data;
                    console.log(this.editAccount)
                })
            },
            save() {
                console.log("save")
                console.log(this.newAccount)
                this.$request.post('http://localhost:8080/api/mail-account/add', this.newAccount, { headers: this.headers }).then((res) => {
                    console.log(res.data)
                    this.getAllMailAccount()
                    this.$swal.fire('added', '', 'success')
                })
            },
            update(editAccount) {
                console.log("update")
                console.log(this.editAccount)
                this.$request.put('http://localhost:8080/api/mail-account/update', editAccount, { headers: this.headers }).then((res) => {
                    console.log(res.data)
                    this.$swal.fire('updated!', '', 'success')
                    this.getAllMailAccount()
                })
            },
            testConnect(account) {
                this.showSpinner = true;
                console.log(account)
                this.$request.post('http://localhost:8080/api/mail-account/test-account', account, { headers: this.headers }).then((res) => {
                    this.$swal.fire(res.data, '', 'success')
                    this.showSpinner = false;

                    this.isLoading = false;
                }).catch(err => {
                    this.showSpinner = false;
                    this.$swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: err.response.data

                    });
                })

            },
            onDelete(id) {
                console.log("delete")
                console.log(id)
                this.$request.delete('http://localhost:8080/api/mail-account/delete/' + id, { headers: this.headers }).then((res) => {
                    this.$swal.fire(res.data, '', 'success')
                    this.getAllMailAccount()
                }).catch(err => {
                    this.showSpinner = false;
                    this.$swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: err.response.data

                    });
                })

            }
        }
    }
</script>
<style scoped>
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