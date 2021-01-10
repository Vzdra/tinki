import React from 'react';
import {useHistory} from 'react-router-dom';
import {render} from "@testing-library/react";

const Profile = (props) => {
    const history = useHistory();
    const userProfile = React.useState({
        userProfile: props.userProfile
    });

    if(props.userProfile.email==null){
        history.push("/");
    }

    return(
        <h1>?</h1>
    );
}

export default Profile;