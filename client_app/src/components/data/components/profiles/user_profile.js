import React from 'react';
import {Container, Header, Table, Item, Button, Segment} from 'semantic-ui-react'
import {Link} from "react-router-dom";

const UserDetails = (props) =>{
    return(
        <Container textAlign="left">
            <Header as="h1">Welcome</Header>
            <Segment>
                <Header as="h3">{props.data.name} {props.data.surname}</Header>
                <Header as="h4" color="gray">Mail: {props.data.email}</Header>
                <Table celled>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell>Retained Skills:</Table.HeaderCell>
                            <Table.HeaderCell>Skills to Learn:</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        <Table.Row>
                            <Table.Cell active>
                                {props.data.retained.map((value, index) => {
                                    return <Item key={index}>{value.name}</Item>
                                })}
                            </Table.Cell>
                            <Table.Cell active>
                                {props.data.toLearn.map((value, index) => {
                                    return <Item key={index}>{value.name}</Item>
                                })}
                            </Table.Cell>
                        </Table.Row>
                    </Table.Body>
                </Table>
            </Segment>
            <Button icon="settings" primary as={Link} to={"/user/edit"} />
        </Container>
    );
}

export default UserDetails;