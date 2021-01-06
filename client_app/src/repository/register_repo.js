import axios from '../custom_axios/axios';

const UserRegister = {
    userRegister: (email ,password ,name , surname, skillsRetained, skillsToLearn) => {
        return axios.post("/user/register", {
            "email": email,
            "password": password,
            "name": name,
            "surname": surname,
            "type": 0,
            "skillsRetained": skillsRetained,
            "skillsToLearn": skillsToLearn
        });
    },
    teamRegister:(email ,password,name) => {
        return axios.post("/team/register",{
            "email": email,
            "password": password,
            "name": name
        });
    },
    companyRegister:(email ,password,name, country ,city, street) => {
        return axios.post("/company/register",{
            "email": email,
            "password": password,
            "name": name,
            "country": country,
            "city": city,
            "street": street
        });
    },
}

export default UserRegister;