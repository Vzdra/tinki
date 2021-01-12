import {Card, Container} from "semantic-ui-react";
import {Redirect} from "react-router-dom";
import React from "react";

const UserProjects = (props) =>{
    var itemsArray = [];

    if(props.userProfile.type==="USER"){
        if(props.userProfile.email!=null){
            props.userProfile.projects.forEach(item => {
                let obj = {}
                obj.header = item.title;
                obj.description = item.description + "\n" + item.accountEmail + " for contact!";
                obj.meta = "Team: " + item.accountName + " / Salary: " + item.salary;

                itemsArray.push(obj);
            });

            return(
                <Container textAlign="left">
                    <h1>Projects for you!</h1>
                    <Card.Group items={itemsArray} />
                </Container>
            );
        }
    }

    return (
        <Redirect to={"/login"}/>
    );
}

export default UserProjects;