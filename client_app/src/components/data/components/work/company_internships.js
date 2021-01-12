import React from 'react';
import {Link, Redirect} from 'react-router-dom';
import { Container, Card, Button } from 'semantic-ui-react';
import Internships from "../item_components/internship_component";


const CompanyInternships = (props) =>{
    if(props.userProfile.type==="COMPANY"){
        if(props.userProfile.email!=null){
            return(
                <Container textAlign="left">
                    <Button as={Link} to={"/internships/add"} primary>Add</Button>
                    <h1>Registered Jobs:</h1>
                    {props.userProfile.internships.map(item =>{
                        return <Internships
                            name={item.name}
                            description={item.description}
                            accountName={item.accountName}
                            accountEmail={item.accountEmail}
                            skills={item.skillsTrained}
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

export default CompanyInternships;