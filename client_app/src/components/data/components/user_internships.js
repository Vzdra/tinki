import {Card, Container} from "semantic-ui-react";
import {Redirect} from "react-router-dom";
import React from "react";
import Internships from "./item_components/internship_component";

const UserInternships = (props) =>{
    if(props.userProfile.type==="USER"){
        if(props.userProfile.email!=null){
            return(
                <Container>
                <h1>Internships for you!</h1>
                {props.userProfile.internships.map(item =>{
                    return <Internships
                        title={item.title}
                        description={item.description}
                        accountName={item.accountName}
                        accountEmail={item.accountEmail}
                        skills={item.skillsTrained}
                        type={props.userProfile.type}
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