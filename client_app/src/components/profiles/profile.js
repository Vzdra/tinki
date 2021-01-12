import React from 'react';
import UserDetails from "./user_profile";
import { Redirect } from "react-router-dom";
import CompanyProfile from "./company_profile";
import TeamProfile from "./team_profile";

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

    if(props.userProfile.type==="COMPANY"){
        return (
            <CompanyProfile data={{
                email: props.userProfile.email,
                name: props.userProfile.name,
                address: props.userProfile.address
            }}/>
        );
    }

    if(props.userProfile.type==="TEAM"){
        return (
            <TeamProfile data={{
                email: props.userProfile.email,
                name: props.userProfile.name,
                members: props.userProfile.members
            }}/>
        );
    }

    return(
        <Redirect to={"/login"}/>
    );
}

export default Profile;