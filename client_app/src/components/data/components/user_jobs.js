import React from 'react';
import { Redirect } from 'react-router-dom';
import { Container, Card } from 'semantic-ui-react'


const UserJobs = (props) =>{
    var itemsArray = [];

    if(props.userProfile.email!=null){
        props.userProfile.jobs.forEach(item => {
            let obj = {}
            obj.header = item.title;
            obj.description = item.description + "\n" + "Contact: " + item.accountEmail;
            if(item.accountType=="TEAM"){
                obj.meta = "Team: " + item.accountName + " / Salary: " + item.salary
            }else{
                obj.meta = "Company: " + item.accountName + " / Salary: " + item.salary
            }

            itemsArray.push(obj);
        });

        return(
            <Container textAlign="left">
                <h1>Jobs for you!</h1>
                <Card.Group items={itemsArray} />
            </Container>
        );
    }

    return (
      <Redirect to={"/login"}/>
    );
}

export default UserJobs;