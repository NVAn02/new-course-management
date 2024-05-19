<template>
    <div class="d-flex container-fluid justify-content-center  align-items-center " style="background-color: #EEEEEE;">
        <div class="container pt-5 pb-5 mt-5 mb-5 bg-white">
            <h1>Quiz Test </h1>
            <!-- <div style="font-weight: bolder;">
                <span>Total question: </span> {{totalQuestion}}
            </div> -->

            <div class="question-content pt-3 pl-5 ml-5 " v-for="(question, index) in questions" :key="index">
                <div class="question d-flex align-items-baseline">
                    <div class="mr-2 col-2 d-flex justify-content-end">
                        <h5>Question {{ index + 1 }}: </h5>
                    </div>
                    <p class="text-left">{{ question.question }}</p>
                </div>
                <div class="options ml-5">
                    <label :for="'option-' + index + '1'"
                        :class="{'selected': question.selectedAnswer === question.option1}"
                        class=" d-flex align-items-baseline">
                        <input :id="'option-' + index + '1'" type="radio" :value="question.option1"
                            v-model="question.selectedAnswer">
                        <p class="ml-2">{{ question.option1 }}</p>
                    </label>
                    <label :for="'option-' + index + '2'"
                        :class="{'selected': question.selectedAnswer === question.option2}"
                        class=" d-flex align-items-baseline">
                        <input :id="'option-' + index + '2'" type="radio" :value="question.option2"
                            v-model="question.selectedAnswer">
                        <p class="ml-2">{{ question.option2 }}</p>
                    </label>
                    <label :for="'option-' + index + '3'"
                        :class="{'selected': question.selectedAnswer === question.option3}"
                        class=" d-flex align-items-baseline">
                        <input :id="'option-' + index + '3'" type="radio" :value="question.option3"
                            v-model="question.selectedAnswer">
                        <p class="ml-2">{{ question.option3 }}</p>
                    </label>
                    <label :for="'option-' + index + '4'"
                        :class="{'selected': question.selectedAnswer === question.option4}"
                        class=" d-flex align-items-baseline">
                        <input :id="'option-' + index + '4'" type="radio" :value="question.option4"
                            v-model="question.selectedAnswer">
                        <p class="ml-2">{{ question.option4 }}</p>
                    </label>
                </div>
            </div>
            <div class="mt-5">
                <button @click="submit" class="btn btn-primary">
                    Submit</button>
            </div>

        </div>
    </div>



</template>
<script>
    import { ref } from "vue";
    import { useRoute } from 'vue-router'
    import { useQuiz } from "@/composables/useQuiz";
    import Swal from 'sweetalert2';
    export default {
        name: "quiz-page",
        setup() {


            const userId = ref(localStorage.getItem("userId"));
            const route = useRoute()

            const subjectId = route.params.subjectId;
            const classId = route.params.classId;

            console.log("subject id: ", subjectId, "classId: ", classId, "userId: ", userId.value)


            const questions = ref([])
            const totalQuestion = ref()


            const { getSubjectQuestions, submitQuiz } = useQuiz();
            // const { getSubjectQuestions } = useQuiz();
            getSubjectQuestions(subjectId).then((res) => {
                questions.value = res
                totalQuestion.value = res.length
                console.log("question", questions)

            })

            const submit = () => {
                if (!checkBeforeSubmit()) {
                    Swal.fire({
                        title: "Bạn chưa chọn hết đáp án",
                        showCancelButton: true,
                        confirmButtonText: "Submit",
                    }).then((result) => {
                        /* Read more about isConfirmed, isDenied below */
                        if (result.isConfirmed) {
                            submitQuiz(subjectId, classId, userId.value, questions.value).then((res) => {
                                console.log(res)
                                Swal.fire(res, "", "success")
                                this.$router.go(-1);
                            }).catch((err) => {
                                Swal.fire({
                                    icon: "error",
                                    title: "Oops...",
                                    text: err.response.data,

                                });
                            })

                        }
                    });



                }

                console.log("answers", questions.value)
            }

            function checkBeforeSubmit() {
                console.log("check ")
                let numSelected = 0;

                for (let question of questions.value) {
                    if (question.selectedAnswer) {
                        numSelected += 1;
                    }
                }

                if (numSelected < totalQuestion.value) {
                    return false
                } else {
                    return true
                }
            }
            return {
                questions, submit, subjectId, classId, userId, totalQuestion
            }

        }
    }
</script>
<style scoped>
    .selected {
        background-color: #DCDCDC;
        /* Màu nền bạn muốn thay đổi khi input được chọn */
    }
</style>