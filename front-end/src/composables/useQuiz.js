import axios from "axios";
import { ref } from "vue";



const isPending = ref(false);

async function getSubjectQuestions(id) {
    isPending.value = true;

    try {
        const response = await axios.get("http://localhost:8080/api/subject/get-list-questions/" + id);
        return response.data;
    } finally {

        isPending.value = false
    }

}
async function submitQuiz(subjectId, classId, userId, answers) {
    isPending.value = true;
    try {
        // console.log("subject, class, userid", subjectId, classId, userId)
        // console.log(answers)
        const response = await axios.post("http://localhost:8080/api/question/submit-quiz?subjectId=" + subjectId + "&classId=" + classId + "&userId=" + userId, answers);

        return response.data;
    } finally {

        isPending.value = false
    }
}

async function getAllQuestion(newPage) {

    const response = await axios.get("http://localhost:8080/api/question/get?pageNo=0" + newPage);
    return response

}
async function addNewQuestion(newQuestion) {
    const response = await axios.post("http://localhost:8080/api/question/add-new-question", newQuestion);
    return response
}
async function addSubjectQuestion(subjectId, questionIds) {
    const response = await axios.post("http://localhost:8080/api/subject/add-questions/" + subjectId, questionIds);
    return response
}
async function getAllQuestionByTopic(topic, newPage) {
    const response = await axios.get("http://localhost:8080/api/question/get-by-topic?pageNo=" + newPage + "&topic=" + topic)
    return response
}
async function getQuestion(id) {
    const response = await axios.get("http://localhost:8080/api/question/get/" + id)
    return response
}
async function deleteQuestion(id) {
    const response = await axios.delete("http://localhost:8080/api/question/delete/" + id)
    return response
}


export function useQuiz() {
    return {
        isPending, getSubjectQuestions, submitQuiz, getAllQuestion, addNewQuestion, addSubjectQuestion, getAllQuestionByTopic,
        getQuestion, deleteQuestion
    }
}
