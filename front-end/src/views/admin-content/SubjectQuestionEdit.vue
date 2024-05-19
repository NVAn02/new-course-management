<template>
    <div class="main-content container pt-5 pb-5">
        <h1>Select Question</h1>
        <div class="d-flex justify-content-center mt-2 mb-2 ">
            <div class="d-flex justify-content-center align-items-baseline col-10">
                <div class="">
                    <h5>Topic: </h5>
                </div>
                <select @change="filter()" class="form-control ml-5 col-5" name="topic" id="topic" v-model="topic">

                    <option value="all"> Tất cả chủ đề</option>
                    <option value="Java Core">Java Core</option>
                    <option value="JavaScript">JavaScript</option>
                    <option value="VueJs">VueJs</option>
                    <option value="HTML">HTML</option>
                    <option value="CSS">CSS</option>
                </select>
            </div>
            <div class="btn btn-primary" @click="save()"> <i class="fas fa-check"></i> Save</div>
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
                <tr :key="index" v-for="(question,index) in allQuestions">
                    <!-- <td>{{ course.id }}</td> -->
                    <td>{{index +1}}</td>
                    <td>{{ question.question }}</td>
                    <td>{{ question.topic }}</td>


                    <td class="d-flex justify-content-center">
                        <input class="custom-checkbox" type="checkbox" v-model="subjectQuestion" :value="question.id">
                    </td>
                </tr>
            </tbody>
        </table>
        <pagination v-model="page" :records="pageResponse.totalElements" :per-page="pageResponse.pageSize"
            @paginate="myCallback" />
    </div>
</template>
<script>
    import { ref } from "vue";
    import { useRoute } from 'vue-router'
    import { useQuiz } from "@/composables/useQuiz"
    import Pagination from 'v-pagination-3';
    import Swal from 'sweetalert2';
    export default {
        name: "SubjectQuestion",
        components: { Pagination },
        setup() {
            const { getAllQuestion, addSubjectQuestion, getSubjectQuestions, getAllQuestionByTopic } = useQuiz()
            const route = useRoute()
            const subjectId = route.params.subjectId;
            const allQuestions = ref([])
            const subjectQuestion = ref([])
            const topic = ref("all")

            const pageResponse = ref({
                content: '',
                pageNo: '',
                pageSize: '',
                totalElements: 0,
                totalPages: 0,
                last: ''
            });


            const page = ref(1);

            myCallback(page.value)
            getSubjectQuestions(subjectId).then((res) => {
                console.log("subject questions: ", res)
                subjectQuestion.value = res.map(ele => ele.id);
                console.log("subject question ids: ", subjectQuestion.value)
            })

            function myCallback(newPage) {
                var page = newPage - 1
                if (topic.value === "all") {

                    getAllQuestion(page).then((res) => {
                        allQuestions.value = res.data.content
                        pageResponse.value = res.data
                    }).catch((err) => {
                        console.log(err)
                    })
                } else {

                    getAllQuestionByTopic(topic.value, page).then((res) => {
                        allQuestions.value = res.data.content
                        pageResponse.value = res.data
                    }).catch((err) => {
                        console.log(err)
                    })
                }

            }
            function save() {
                console.log(subjectId)
                console.log(subjectQuestion.value)
                addSubjectQuestion(subjectId, subjectQuestion.value).then((res) => {
                    // Swal.fire(res.data, "", "success")
                    Swal.fire({
                        title: res.data,
                        icon: 'success',
                        width: '600px'
                    });
                    myCallback(page.value)
                }).catch((err) => {
                    console.log(err)
                })
            }
            function filter() {
                page.value = 1
                console.log(topic.value)
                myCallback(page.value)

            }

            return {
                subjectId, allQuestions, subjectQuestion, pageResponse, myCallback, page, save, topic, filter
            }
        }
    }
</script>
<style scoped>
    .custom-checkbox {
        transform: scale(1.5);
        /* Điều chỉnh kích thước */
        margin-right: 10px;
        /* Điều chỉnh khoảng cách giữa checkbox và nội dung xung quanh */
    }

    .main-content {
        min-height: 1000px;
    }
</style>