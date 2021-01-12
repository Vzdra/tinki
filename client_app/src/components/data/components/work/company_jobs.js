import React from 'react';
import {Link, Redirect} from 'react-router-dom';
import { Container, Card, Button } from 'semantic-ui-react';
import Jobs from '../item_components/job_component';


const CompanyJobs = (props) =>{
    if(props.userProfile.type==="COMPANY"){
        if(props.userProfile.email!=null){
            return(
                <Container textAlign="left">
                    <Button as={Link} to={"/job/add"} primary>Add</Button>
                    <h1>Registered Jobs:</h1>
                    {props.userProfile.jobs.map(item =>{
                        return <Jobs
                            name={item.name}
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

export default CompanyJobs;