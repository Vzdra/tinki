import axios from '../custom_axios/axios';

const SkillFetch = {
    fetchAll: () => {
        return axios.get("/skills");
    }
}

export default SkillFetch;