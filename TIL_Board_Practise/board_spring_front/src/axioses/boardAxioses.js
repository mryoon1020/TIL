import axios from 'axios';
import { ref } from 'vue';

const boardCreate = () => {
    axios.get('')
}

const boardList = ref([]);
const boardContentNo = ref(0);

const test = async () => {
    try {
        const res = await axios.get('http://localhost:8080');
        boardList.value = res.data;
        boardContentNo.value = res.data.length
    } catch (err) {
        console.log(err);
    }
}

export default test;