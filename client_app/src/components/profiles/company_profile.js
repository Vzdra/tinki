import React from 'react';
import {Container, Header, Table, Item, Button, Segment} from 'semantic-ui-react'
import {Link} from "react-router-dom";

const CompanyProfile = (props) =>{
    return(
        <Container textAlign="left">
            <Header as="h1">Welcome!</Header>
            <Segment.Group>
                <Segment>
                    <Header>Company</Header>
                </Segment>
                <Segment>
                    <Header as="h3">Company Name:</Header>
                    {props.data.name}
                </Segment>
                <Segment>
                    <Header as="h3">Company e-mail:</Header>
                    {props.data.email}
                </Segment>
                <Segment><b>Address: </b> {props.data.address.country}-{props.data.address.city}-{props.data.address.street}</Segment>
            </Segment.Group>
            <Button primary as={Link} to={"/company/edit"}>Edit</Button>
        </Container>
    );
}

export default CompanyProfile;