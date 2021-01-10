import axios from '../custom_axios/axios';

const UserLogin = {
    login: (email, password, type) => {
        return axios.post("/login", {
            "email": email,
            "password": password,
            "type": type
        });
    }
}

export default UserLogin;