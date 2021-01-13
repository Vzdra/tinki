import axios from '../custom_axios/axios';

const UserRegister = {
    userRegister: (email, password, name, surname, retainedSkills, skillsToLearn) => {
        return axios.post("/register/user", {
            "email": email,
            "password": password,
            "name": name,
            "surname": surname,
            "retainedSkills": retainedSkills,
            "skillsToLearn": skillsToLearn
        });
    },
    teamRegister: (email, password, name, members) => {
        return axios.post("/register/team",{
            "email": email,
            "password": password,
            "name": name,
            "members": members
        });
    },
    companyRegister: (email, password, name, country, city, street) => {
        return axios.post("/register/company",{
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