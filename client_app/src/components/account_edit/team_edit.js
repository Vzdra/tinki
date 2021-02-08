import React from 'react';
import 'semantic-ui-react';
import {Button, Container, Form} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import {Component} from "react/cjs/react.production.min";
import AccountEdit from "../../repository/edit_account_repo";

class EditTeam extends Component  {
    constructor(props){
        super(props);
        this.state = {
            email: props.oldUser.email,
            name: props.oldUser.name,
            members: props.oldUser.members,
            error: null,
            success: null,
            id: props.oldUser.id,
            oldEmail: props.oldUser.email,
            type: props.oldUser.type
        }
        this.attemptTeamEdit = this.attemptTeamEdit.bind(this);
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    attemptTeamEdit = () => {
        AccountEdit.teamEdit(
            this.state.id,
            this.state.oldEmail,
            this.state.email,
            this.state.name,
            this.state.members
        ).then(res =>{
            this.setState({
                success: true,
                error: null
            })
            this.props.updateUser(res.data);
        }).catch(err => {
            this.setState({
                error: "Team already exists!",
                success: null
            })
        })
    }

    componentDidMount(){
        this.props.message(null);
    }

    render() {
        if(this.state.success!=null){
            return(
                <Redirect to={"/profile"}/>
            );
        }

        if(this.state.type!=="TEAM"){
            return(
                <Redirect to={"/profile"}/>
            );
        }

        return (
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptTeamEdit}>
                    <Form.Input id="email" name="email" type='email' required fluid label='E-mail'
                                placeholder='Enter e-mail.' value={this.state.email} onChange={this.handleCheck}/>
                    <Form.Input id="name" name="name" type='text' required fluid label='Name'
                                placeholder='Enter company name.' value={this.state.name} onChange={this.handleCheck}/>
                    <Form.Input id="members" name="members" type="number" required fluid label="Number of members"
                                placeholder="Enter member count." value={this.state.members} onChange={this.handleCheck}/>
                    <Form.Field control={Button}>Confirm</Form.Field>
                </Form>
            </Container>
        );
    }
}

export default EditTeam;