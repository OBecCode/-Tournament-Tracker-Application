import axios from 'axios';

export default {
    getAllHolidays() {
        return axios.get('/test');
    }
}