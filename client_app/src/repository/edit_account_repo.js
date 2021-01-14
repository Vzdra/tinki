import axios from '../custom_axios/axios';

const AccountEdit = {
    userEdit: (id, email, newEmail, name, surname, retainedSkills, skillsToLearn) => {
        return axios.post("/edit/account/user/" + id + "/" + email, {
            "email": newEmail,
            "name": name,
            "surname": surname,
            "retainedSkills": retainedSkills,
            "skillsToLearn": skillsToLearn
        });
    },
    companyEdit: (id, email, newEmail, name, country, city, street) => {
        return axios.post("/edit/account/company/" + id + "/" + email, {
            "email": newEmail,
            "name": name,
            "country": country,
            "city": city,
            "street": street
        });
    },
    teamEdit: (id, email, newEmail, name, members) => {
        return axios.post("/edit/account/team/" + id + "/" + email, {
            "email": newEmail,
            "name": name,
            "members": members
        });
    }
}

export default AccountEdit;