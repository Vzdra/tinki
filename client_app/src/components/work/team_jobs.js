import React from 'react';
import {Link, Redirect} from 'react-router-dom';
import { Container, Card, Button } from 'semantic-ui-react';
import Jobs from '../item_components/job_component';


const TeamJobs = (props) =>{
    if(props.userProfile.type==="TEAM"){
        if(props.userProfile.email!=null){
            return(
                <Container textAlign="left">
                    <Button as={Link} to={"/register/job"} primary>Add</Button>
                    <h1>Registered Jobs:</h1>
                    {props.userProfile.jobs.map(item =>{
                        return <Jobs
                            item={item}
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

export default TeamJobs;