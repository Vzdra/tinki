import React from 'react';
import {Container, Header, Table, Item, Button} from 'semantic-ui-react'
import {Link} from "react-router-dom";

const CompanyProfile = (props) =>{
    return(
        <Container textAlign="left">
            <Header>Company:</Header>
            <h3>{props.data.name}</h3>
            <h2>E-mail: {props.data.email}</h2>
            <h3>Address: {props.data.address.country} {props.data.address.city} {props.data.address.street}</h3>
            <Button primary as={Link} to={"/profile/edit"}>Edit</Button>
        </Container>
    );
}

export default CompanyProfile;