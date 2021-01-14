import axios from '../custom_axios/axios';

const JobSearch = {
    job: (text) => {
        return axios.get("/search/job", {
            params: {
                "text": text
            }
        });
    },
    internship: (text) => {
        return axios.get("/search/internship", {
            params: {
                "text": text
            }
        });
    },
    project: (text) => {
        return axios.get("/search/project", {
            params: {
                "text": text
            }
        });
    },
}

export default JobSearch;