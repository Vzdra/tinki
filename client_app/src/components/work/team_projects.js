import React from 'react';
import {Link, Redirect} from 'react-router-dom';
import { Container, Card, Button } from 'semantic-ui-react';
import Projects from "../item_components/project_component";


const TeamProjects = (props) =>{
    if(props.userProfile.type==="TEAM"){
        if(props.userProfile.email!=null){
            return(
                <Container textAlign="left">
                    <Button as={Link} to={"/register/project"} primary>Add</Button>
                    <h1>Registered Projects:</h1>
                    {props.userProfile.projects.map((item, index) =>{
                        return <Projects
                            key={item.id}
                            index={index}
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

export default TeamProjects;