import React from 'react';
import {Header, List, Segment, Button} from 'semantic-ui-react';

const Internships = (props) => {
    return(
        <Segment>
            <Header as="h3">{props.title}</Header>
            {props.type!=="USER" ? <Button primary>Edit</Button> : <span> </span>}
            <Segment>{props.description}</Segment>
            <Segment>
                <Header as="h5" color="grey">{props.accountName}</Header>
                <Header as="h4" color="blue">Owner Mail: {props.accountEmail}</Header>
            </Segment>
            <Segment>
                <Header as="h3">Skills Trained:</Header>
                <List>
                    {props.skills.map(skill => {
                        return <List.Item key={skill.id}>{skill.name}</List.Item>
                    })}
                </List>
            </Segment>
        </Segment>
    );
}

export default Internships;