import React from 'react';
import {Header, List, Segment, Button} from 'semantic-ui-react';
import {Link} from "react-router-dom";

const Jobs = (props) => {
    return(
        <Segment>
            <Header as="h3">{props.item.title}</Header>
            {!props.search ? <Button primary as={Link} to={{
                pathname: "/edit/job",
                state: {
                    index: props.index,
                    item: props.item
                }
            }}>Edit</Button> : <span> </span>}
            <Segment>{props.item.description}</Segment>
            <Segment>
                <Header as="h5" color="grey">{props.item.accountName}</Header>
                <Header as="h4" color="blue">Owner Mail: {props.item.accountEmail}</Header>
            </Segment>
            <Segment>
                <Header as="h3">Skills Required:</Header>
                <List>
                    {props.item.skillsRequired.map(skill => {
                        return <List.Item key={skill.id}>{skill.name}</List.Item>
                    })}
                </List>
            </Segment>
        </Segment>
    );
}

export default Jobs;