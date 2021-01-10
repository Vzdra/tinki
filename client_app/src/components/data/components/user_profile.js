import React from 'react';
import { Container, Header, List } from 'semantic-ui-react'

const UserDetails = (props) =>{
    return(
        <Container>
            <Header>Welcome</Header>
            <br />
            <h3>User: {props.data.name}</h3>
            <table>
                <tr>
                    <td>
                        <h3>Retained skills</h3>
                        <ul>
                        {props.data.retained.map((value, index) => {
                            return <li key={index}>{value.name}</li>
                        })}
                        </ul>
                    </td>
                    <td>
                        <h3>Skills you want to learn</h3>
                        <ul>
                            {props.data.toLearn.map((value, index) => {
                                return <li key={index}>{value.name}</li>
                            })}
                        </ul>
                    </td>
                </tr>
            </table>
        </Container>
    );
}

export default UserDetails;