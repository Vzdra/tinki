import {Card, Container} from "semantic-ui-react";
import {Redirect} from "react-router-dom";
import React from "react";

const UserInternships = (props) =>{
    var itemsArray = [];

    if(props.userProfile.type==="USER"){
        if(props.userProfile.email!=null){
            props.userProfile.internships.forEach(item => {
                let obj = {}
                obj.header = item.title;
                obj.description = item.description + "\n" + item.accountEmail + " for contact!";
                obj.meta = "Company: " + item.accountName + " / Salary: " + item.salary;

                itemsArray.push(obj);
            });

            return(
                <Container textAlign="left">
                    <h1>Internships for you!</h1>
                    <Card.Group items={itemsArray} />
                </Container>
            );
        }
    }

    return (
        <Redirect to={"/login"}/>
    );
}

export default UserInternships;