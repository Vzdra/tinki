import React from 'react';
import {Container, Header, Table, Item, Button} from 'semantic-ui-react'
import {Link} from "react-router-dom";

const TeamProfile = (props) =>{
    return(
        <Container textAlign="left">
            <Header>Team:</Header>
            <h3>{props.data.name}</h3>
            <h2>E-mail: {props.data.email}</h2>
            <h3>Members: {props.data.members}</h3>
            <Button primary as={Link} to={"/profile/edit"}>Edit</Button>
        </Container>
    );
}

export default TeamProfile;