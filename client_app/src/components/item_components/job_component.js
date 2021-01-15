import React from 'react';
import {Header, List, Segment, Button} from 'semantic-ui-react';
import {Link} from "react-router-dom";

const Jobs = (props) => {
    return(
        <Segment>
            <Header as="h3">{props.title}</Header>
            {props.type!=="USER" ? <Button primary as={Link} to={{
                pathname: "/edit/job",
                state: {
                    jobId: props.id,
                    accId: props.accId,
                    type: props.type
                }
            }}>Edit</Button> : <span> </span>}
            <Segment>{props.description}</Segment>
            <Segment>
                <Header as="h5" color="grey">{props.accountName}</Header>
                <Header as="h4" color="blue">Owner Mail: {props.accountEmail}</Header>
            </Segment>
            <Segment>
                <Header as="h3">Skills Required:</Header>
                <List>
                    {props.skills.map(skill => {
                        return <List.Item key={skill.id}>{skill.name}</List.Item>
                    })}
                </List>
            </Segment>
        </Segment>
    );
}

export default Jobs;