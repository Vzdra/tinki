import React from 'react';
import { Redirect } from 'react-router-dom';
import { Container, Card } from 'semantic-ui-react';
import Jobs from '../components/item_components/job_component';


const UserJobs = (props) =>{
    if(props.userProfile.type==="USER"){
        if(props.userProfile.email!=null){
            return(
                <Container textAlign="left">
                    <h1>Jobs for you!</h1>
                    {props.userProfile.jobs.map(item =>{
                        return <Jobs
                            title={item.title}
                            description={item.description}
                            accountName={item.accountName}
                            accountEmail={item.accountEmail}
                            skills={item.skillsRequired}
                            type={props.userProfile.type}
                        />
                    })}
                </Container>
            );
        }
    }

    return (
      <Redirect to={"/login"}/>
    );
}

export default UserJobs;