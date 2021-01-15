import axios from '../custom_axios/axios';

const WorkEdit = {
    jobEdit: (jobId, title, description, accId, salary) => {
        return axios.post("/edit/work/job/" + jobId, {
            "title": title,
            "description": description,
            "accountId": accId,
            "salary": salary
        });
    },
    projectEdit: (projectId, title, description, accId, salary) => {
        return axios.post("/edit/work/project/" + projectId, {
            "title": title,
            "description": description,
            "accountId": accId,
            "salary": salary
        });
    },
    internshipEdit: (internshipId, title, description, accId, salary, openSpots) => {
        return axios.post("/edit/work/internship/" + internshipId, {
            "title": title,
            "description": description,
            "accountId": accId,
            "salary": salary,
            "openSpots": openSpots
        });
    }
}

export default WorkEdit;