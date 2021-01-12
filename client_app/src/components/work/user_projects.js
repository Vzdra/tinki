import {Card, Container} from "semantic-ui-react";
import {Redirect} from "react-router-dom";
import React from "react";
import Projects from "../item_components/project_component";

const UserProjects = (props) =>{
    if(props.userProfile.type==="USER"){
        if(props.userProfile.email!=null){
            return(
                <Container>
                    <h1>Projects for you!</h1>
                    {props.userProfile.projects.map(item =>{
                        return <Projects
                            title={item.title}
                            description={item.description}
                            accountName={item.accountName}
                            accountEmail={item.accountEmail}
                            skills={item.skillsRequired}
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

export default UserProjects;