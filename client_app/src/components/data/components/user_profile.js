import React from 'react';
import { Container, Header, Table, Item } from 'semantic-ui-react'

const UserDetails = (props) =>{
    return(
        <Container textAlign="left">
            <Header>Welcome</Header>
            <h3>{props.data.name} {props.data.surname}</h3>
            <h2>E-mail: {props.data.email}</h2>
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
        </Container>
    );
}

export default UserDetails;