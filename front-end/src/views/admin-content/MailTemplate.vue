<template>
    <div class="container pt-5">
        <h1>Mail Template Setting</h1>

        <div>
            <div class="d-flex justify-content-end mt-4 mb-4">
                <div class="btn btn-primary" data-toggle="modal" data-target="#myModal"><i class="fas fa-plus"></i> Add
                    template
                </div>
                <div class="modal " id="myModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Add new template</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <!-- Modal body -->
                            <div class="modal-body text-left">
                                <div class="d-flex mb-4">
                                    <div class="col-4">
                                        <h4>Email: </h4>
                                    </div>

                                    <select class="form-control" name="teacher" id="teacher"
                                        v-model="newTemplate.mailAccount">
                                        <!-- <option value="" disabled selected style="text-align: center;">{{
                                            template.mailAccount.name }}
                                        </option> -->
                                        <option :value="mailAccount" v-for="(mailAccount, index) in mailAccounts"
                                            :key="index">
                                            {{ mailAccount.name
                                            }}
                                        </option>
                                    </select>
                                </div>

                                <div class="d-flex  mb-4">
                                    <div class="col-4">
                                        <h4>Name: </h4>
                                    </div>
                                    <input type="text" class="form-control" v-model="newTemplate.name">
                                </div>
                                <div class="d-flex  mb-4">
                                    <div class="col-4">
                                        <h4>Subject: </h4>
                                    </div>
                                    <input type="text" class="form-control" v-model="newTemplate.subject">
                                </div>
                                <div>
                                    <div class="col-4">
                                        <h4>Body:</h4>
                                    </div>
                                    <textarea class="form-control" name="" id="" cols="30" rows="20"
                                        v-model="newTemplate.body">

                                    </textarea>
                                </div>
                            </div>
                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <div class="btn btn-primary" @click="save">Save</div>
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
                        <th>Subject</th>
                        <th>Email</th>
                        <th>Hành động</th>

                    </tr>
                </thead>
                <tbody>
                    <tr :key="index" v-for="(template, index) in allTemplate">
                        <td>{{ index +1 }}</td>
                        <!-- <td>{{ index + 1 }}</td> -->
                        <td>{{ template.name }}</td>
                        <td>{{ template.subject }}</td>
                        <td>{{ template.mailAccount.name }}</td>

                        <td>
                            <div class="btn btn-primary" data-toggle="modal" data-target="#edit"
                                @click="getTemplate(template.id)"><i class="far fa-edit"></i> Edit</div>
                            <div class="modal" id="edit">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <!-- Modal Header -->
                                        <div class="modal-header">
                                            <h4 class="modal-title">Edit mail template</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <!-- Modal body -->
                                        <div class="modal-body text-left">
                                            <div class="d-flex mb-4">
                                                <div class="col-4">
                                                    <h4>Email: </h4>
                                                </div>

                                                <select class="form-control" name="teacher" id="teacher"
                                                    v-model="editTemplate.mailAccount">
                                                    <option :value="mailAccount"
                                                        v-for="(mailAccount, index) in mailAccounts" :key="index">
                                                        {{ mailAccount.name}}
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="d-flex  mb-4">
                                                <div class="col-4">
                                                    <h4>Name: </h4>
                                                </div>
                                                <input disabled type="text" class="form-control"
                                                    v-model="editTemplate.name">
                                            </div>
                                            <div class="d-flex  mb-4">
                                                <div class="col-4">
                                                    <h4>Subject: </h4>
                                                </div>
                                                <input type="text" class="form-control" v-model="editTemplate.subject">
                                            </div>
                                            <div>
                                                <div class="col-4">
                                                    <h4>Body:</h4>
                                                </div>
                                                <textarea class="form-control" name="" id="" cols="30" rows="20"
                                                    v-model="editTemplate.body">
                                                </textarea>
                                            </div>
                                        </div>
                                        <!-- Modal footer -->
                                        <div class="modal-footer">
                                            <div class="btn btn-primary" @click="update(editTemplate)"
                                                data-dismiss="modal">
                                                Save</div>
                                            <button type="button" class="btn btn-danger"
                                                data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>

                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
    export default {
        name: 'MailTemplate',
        data() {
            return {
                allTemplate: [],
                newTemplate: {
                    id: '',
                    name: '',
                    subject: '',
                    body: ''
                },
                editTemplate: {},
                mailAccounts: []
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
            this.getAllTemplate()
            this.getAllMailAccount()
        },
        methods: {
            getAllMailAccount() {
                this.$request.get('http://localhost:8080/api/mail-account/get', { headers: this.headers }).then((res) => {
                    console.log("Get all mail account")
                    this.mailAccounts = res.data;
                    console.log(this.mailAccounts)
                })
            },
            getTemplate(id) {
                this.$request.get('http://localhost:8080/api/mail/get/' + id, { headers: this.headers }).then((res) => {
                    this.editTemplate = res.data;
                    console.log(this.editTemplate)
                })
            },
            getAllTemplate() {
                this.$request.get('http://localhost:8080/api/mail/get', { headers: this.headers }).then((res) => {
                    this.allTemplate = res.data
                })
            },
            save() {
                console.log("save")
                console.log(this.newTemplate)
                this.$request.post('http://localhost:8080/api/mail/add', this.newTemplate, { headers: this.headers }).then((res) => {
                    console.log(res.data)
                    this.getAllTemplate()
                    this.$swal.fire('Created new template', '', 'success')
                }).catch((err) => {
                    console.log(err)
                })
            },
            update(template) {
                console.log("update")
                console.log(this.newTemplate)
                this.$request.put('http://localhost:8080/api/mail/update', template, { headers: this.headers }).then((res) => {
                    console.log(res.data)
                    this.getAllTemplate()
                    this.$swal.fire('updated!', '', 'success')
                    this.$router.push("/mail-template")
                }).catch((err) => {
                    console.log(err)
                })
            }
        }

    }
</script>