import axios from '../custom_axios/axios';

const WorkRegister = {
    jobRegister: (title, description, accId, salary, accType, skillsRequired) => {
        return axios.post("/register/work/job", {
            "title": title,
            "description": description,
            "accountId": accId,
            "salary": salary,
            "type": accType,
            "skillsRequired": skillsRequired
        });
    },
    internshipRegister:(title, description, accId, salary, accType, openSpots, skillsTrained) => {
        return axios.post("/register/work/internship",{
            "title": title,
            "description": description,
            "accountId": accId,
            "salary": salary,
            "type": accType,
            "openSpots": openSpots,
            "skillsTrained": skillsTrained
        });
    },
    projectRegister:(title, description, accId, salary, accType, validUntil, skillsRequired) => {
        return axios.post("/register/work/project",{
            "title": title,
            "description": description,
            "accountId": accId,
            "salary": salary,
            "type": accType,
            "validUntil": validUntil,
            "skillsRequired": skillsRequired
        });
    },
}

export default WorkRegister;