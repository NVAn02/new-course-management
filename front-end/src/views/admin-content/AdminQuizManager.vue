<template>
    <div class="main-content container pt-5 pb-5">
        <h1>Quiz Questions Manager</h1>
        <div class="course-table">
            <div class=" mb-2 pt-2 d-flex justify-content-between">
                <div class="d-flex col-9 justify-content-center align-items-baseline">
                    <h5>Topic: </h5>
                    <select @change="filter()" class="form-control ml-5 col-5" name="topic" id="topic" v-model="topic">

                        <option value="all"> Tất cả chủ đề</option>
                        <option value="Java Core">Java Core</option>
                        <option value="JavaScript">JavaScript</option>
                        <option value="VueJs">VueJs</option>
                        <option value="HTML">HTML</option>
                        <option value="CSS">CSS</option>
                    </select>
                </div>

                <div class="btn btn-primary" data-toggle="modal" data-target="#addNewQuestion"> <i
                        class="fas fa-plus"></i> Add New
                    Question
                </div>
            </div>
            <div>
                <div class="modal" id="addNewQuestion">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content ">

                            <div class="modal-header">
                                <h4 class="modal-title">Create new question</h4>
                                <button type="button" class="close" data-dismiss="modal"
                                    @click="removeQuestion()">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="d-flex align-items-baseline mt-2 mb-2">
                                    <div class="col-3">
                                        <h5>Topic: </h5>
                                    </div>
                                    <select class="form-control ml-5" name="topic" id="topic"
                                        v-model="newQuestion.topic">
                                        <option value="" disabled selected style="text-align: center;">-- Chọn chủ đề --
                                        </option>
                                        <option value="Java Core">Java Core</option>
                                        <option value="JavaScript">JavaScript</option>
                                        <option value="VueJs">VueJs</option>
                                        <option value="HTML">HTML</option>
                                        <option value="CSS">CSS</option>
                                    </select>
                                </div>
                                <div class=" d-flex align-items-baseline mt-2 mb-2">
                                    <div class="col-3">
                                        <h5>Question: </h5>
                                    </div>
                                    <input v-model="newQuestion.question" type="text" class="form-control ml-5">

                                </div>
                                <div class=" d-flex align-items-baseline mt-2 mb-2">
                                    <div class="col-3">
                                        <h5>Option 1:</h5>
                                    </div>
                                    <input v-model="newQuestion.option1" type="text" class="form-control ml-5 ">
                                </div>
                                <div class="d-flex align-items-baseline mt-2 mb-2">
                                    <div class="col-3">
                                        <h5>Option 2:</h5>
                                    </div>
                                    <input v-model="newQuestion.option2" type="text" class="form-control ml-5">
                                </div>
                                <div class=" d-flex align-items-baseline mt-2 mb-2">
                                    <div class="col-3">
                                        <h5>Option 3:</h5>
                                    </div>
                                    <input v-model="newQuestion.option3" type="text" class="form-control ml-5">
                                </div>
                                <div class="d-flex align-items-baseline mt-2 mb-2">
                                    <div class="col-3">
                                        <h5>Option 4:</h5>
                                    </div>
                                    <input v-model="newQuestion.option4" type="text" class="form-control ml-5">
                                </div>
                                <div class="d-flex align-items-baseline mt-2 mb-2">
                                    <div class="col-3">
                                        <h5>Correct answer:</h5>
                                    </div>
                                    <select v-model="newQuestion.correctAnswer" class="form-control ml-5">
                                        <option v-if="newQuestion.option1" :value="newQuestion.option1">
                                            {{newQuestion.option1}}</option>
                                        <option v-if="newQuestion.option2" :value="newQuestion.option2">
                                            {{newQuestion.option2}}</option>
                                        <option v-if="newQuestion.option3" :value="newQuestion.option3">
                                            {{newQuestion.option3}}</option>
                                        <option v-if="newQuestion.option4" :value="newQuestion.option4">
                                            {{newQuestion.option4}}</option>
                                    </select>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <div class="btn btn-primary" data-dismiss="modal" @click="addQuestion()">Save</div>
                                <button type="button" class="btn btn-danger" data-dismiss="modal"
                                    @click="removeQuestion()">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <table class="table">
            <thead class="bg-dark text-white">
                <tr class="text-left">
                    <th>No</th>
                    <th class="col-8">Câu hỏi</th>
                    <th class="col-2">Topic</th>
                    <th class="col-2">Hành động</th>
                </tr>
            </thead>
            <tbody class="text-left">
                <tr :key="index" v-for="(question,index) in questions">
                    <!-- <td>{{ course.id }}</td> -->
                    <td>{{index +1}}</td>
                    <td>{{ question.question }}</td>

                    <td>{{ question.topic }}</td>


                    <td class="d-flex justify-content-center">
                        <div class="btn btn-primary mr-2" data-toggle="modal" data-target="#addNewQuestion"
                            @click="editQuestion(question.id)"> <i class="far fa-edit"></i> Edit</div>&nbsp;
                        <button class="btn btn-danger" @click="onDelete(question.id)"> Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
        <pagination v-model="page" :records="pageResponse.totalElements" :per-page="pageResponse.pageSize"
            @paginate="myCallback" />
    </div>

</template>
<script>
    import { ref } from "vue"
    import { useQuiz } from "@/composables/useQuiz"
    import Pagination from 'v-pagination-3';
    import Swal from 'sweetalert2';
    export default {
        name: "QuizManager",
        components: { Pagination },
        setup() {
            const { getAllQuestion, getAllQuestionByTopic, addNewQuestion, getQuestion, deleteQuestion } = useQuiz()
            const newQuestion = ref({
                topic: "",
                question: "",
                option1: "",
                option2: "",
                option3: "",
                option4: "",
                correctAnswer: ""
            })

            const topic = ref("all")
            const questions = ref([])
            const pageResponse = ref({
                content: '',
                pageNo: '',
                pageSize: 0,
                totalElements: 0,
                totalPages: 0,
                last: ''
            });

            const page = ref(1);

            myCallback(page.value)

            function myCallback(newPage) {
                var page = newPage - 1
                if (topic.value === "all") {

                    getAllQuestion(page).then((res) => {
                        questions.value = res.data.content
                        pageResponse.value = res.data
                    }).catch((err) => {
                        console.log(err)
                    })
                } else {

                    getAllQuestionByTopic(topic.value, page).then((res) => {
                        questions.value = res.data.content
                        pageResponse.value = res.data
                    }).catch((err) => {
                        console.log(err)
                    })
                }

            }
            function filter() {
                page.value = 1
                console.log(topic.value)
                myCallback(page.value)

            }
            function removeQuestion() {
                newQuestion.value = {
                    topic: "",
                    question: "",
                    option1: "",
                    option2: "",
                    option3: "",
                    option4: "",
                    correctAnswer: ""
                }
            }
            function addQuestion() {
                console.log(newQuestion.value)
                addNewQuestion(newQuestion.value).then((res) => {
                    Swal.fire(res.data, "", "success")
                    myCallback(page.value)
                    removeQuestion()
                }).catch((err) => {
                    let htmlContent = ""; // Khởi tạo chuỗi HTML trống

                    // Kiểm tra và thêm từng trường vào chuỗi HTML nếu nó không phải là null
                    if (err.response.data.question) {
                        htmlContent += `${err.response.data.question} <br>`;
                    }
                    if (err.response.data.option1) {
                        htmlContent += `${err.response.data.option1} <br>`;
                    }
                    if (err.response.data.option2) {
                        htmlContent += `${err.response.data.option2} <br>`;
                    }
                    if (err.response.data.option3) {
                        htmlContent += `${err.response.data.option3} <br>`;
                    }
                    if (err.response.data.option4) {
                        htmlContent += `${err.response.data.option4} <br>`;
                    }
                    if (err.response.data.correctAnswer) {
                        htmlContent += `${err.response.data.correctAnswer} <br>`;
                    }
                    if (err.response.data.topic) {
                        htmlContent += `${err.response.data.topic}`;
                    }
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        html: htmlContent
                    });
                })

            }
            function editQuestion(id) {
                getQuestion(id).then((res) => {
                    console.log(res.data)
                    newQuestion.value = res.data
                }).catch((err) => {
                    console.log(err)
                })

            }
            function onDelete(id) {
                console.log("Delete: " + id)
                deleteQuestion(id).then((res) => {
                    Swal.fire(res.data, "", "success")
                    myCallback(page.value)

                }).catch((err) => {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        html: `${err.response.data}`
                    });

                })
            }

            return {
                myCallback, topic, page, pageResponse, questions, newQuestion, addQuestion, editQuestion, onDelete, removeQuestion, filter
            }
        }
    }
</script>
<style scoped>
    .main-content {
        min-height: 800px;
    }
</style>