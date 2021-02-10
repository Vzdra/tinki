import React from 'react';
import {Container, Header, Table, Item, Button, Segment} from 'semantic-ui-react'
import {Link} from "react-router-dom";

const TeamProfile = (props) =>{
    return(
        <Container textAlign="left">
            <Header as="h1">Welcome!</Header>
            <Segment.Group>
                <Segment>
                    <Header>Team</Header>
                </Segment>
                <Segment>
                    <Header as="h3">Team Name:</Header>
                    {props.data.name}
                </Segment>
                <Segment>
                    <Header as="h3">Team e-mail:</Header>
                    {props.data.email}
                </Segment>
                <Segment><b>Number of members:</b> {props.data.members}</Segment>
            </Segment.Group>
            <Button primary as={Link} to={"/team/edit"}>Edit</Button>
        </Container>
    );
}

export default TeamProfile;