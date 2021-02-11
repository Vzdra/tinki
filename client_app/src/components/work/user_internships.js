import {Container} from "semantic-ui-react";
import {Redirect} from "react-router-dom";
import React from "react";
import Internships from "../item_components/internship_component";

const UserInternships = (props) =>{
    if(props.userProfile.type==="USER"){
        if(props.userProfile.email!=null){
            console.log(props.userProfile.internships);
            return(
                <Container>
                <h1>Internships for you!</h1>
                {props.userProfile.internships.map((item, index) =>{
                    return <Internships
                        search={true}
                        key={item.id}
                        index={index}
                        item={item}
                    />})
                }
                </Container>
            );
        }
    }

    return (
        <Redirect to={"/login"}/>
    );
}

export default UserInternships;