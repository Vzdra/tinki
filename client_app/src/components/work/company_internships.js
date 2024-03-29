import React from 'react';
import {Link, Redirect} from 'react-router-dom';
import { Container, Card, Button } from 'semantic-ui-react';
import Internships from "../item_components/internship_component";


const CompanyInternships = (props) =>{
    if(props.userProfile.type==="COMPANY"){
        if(props.userProfile.email!=null){
            return(
                <Container textAlign="left">
                    <Button as={Link} to={"/register/internship"} primary>Add</Button>
                    <h1>Registered Internships:</h1>
                    {props.userProfile.internships.map((item, index) =>{
                        return <Internships
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

export default CompanyInternships;