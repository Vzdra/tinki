import React from 'react';
import UserDetails from "./components/user_profile";
import { Redirect } from "react-router-dom";

const Profile = (props) => {

    if(props.userProfile.type==="USER"){
        return (
          <UserDetails data={{
               email: props.userProfile.email,
               name: props.userProfile.name,
               surname: props.userProfile.surname,
               retained: props.userProfile.retained,
               toLearn: props.userProfile.toLearn
          }}/>
        );
    }

    return(
        <Redirect to={"/login"}/>
    );
}

export default Profile;