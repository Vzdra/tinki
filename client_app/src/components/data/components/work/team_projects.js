import React from 'react';
import {Link, Redirect} from 'react-router-dom';
import { Container, Card, Button } from 'semantic-ui-react';
import Projects from "../item_components/project_component";


const TeamProjects = (props) =>{
    if(props.userProfile.type==="TEAM"){
        if(props.userProfile.email!=null){
            return(
                <Container textAlign="left">
                    <Button as={Link} to={"/project/add"} primary>Add</Button>
                    <h1>Registered Jobs:</h1>
                    {props.userProfile.projects.map(item =>{
                        return <Projects
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

export default TeamProjects;