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
                    {props.userProfile.projects.map((item,index) =>{
                        return <Projects
                            key={item.id}
                            index={index}
                            item={item}
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