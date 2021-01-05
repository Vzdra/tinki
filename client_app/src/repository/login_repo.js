import axios from '../custom_axios/axios';

const UserLogin = {
    login: (username, password, type) => {
        return axios.post("/login", {
            "account": username,
            "password": password,
            "type": type
        });
    }
}

export default UserLogin;