import axios from '../custom_axios/axios';

const SkillFetch = {
    fetchAll: () => {
        return axios.get("/api/skills");
    }
}

export default SkillFetch;