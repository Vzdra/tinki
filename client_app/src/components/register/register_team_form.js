import React from 'react';
import 'semantic-ui-react';
import {Button, Container, Form} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import UserRegister from "../../repository/register_repo";
import {Component} from "react/cjs/react.production.min";

class RegisterTeam extends Component  {
    constructor(props){
        super(props);
        this.state = {
            email: "",
            password: "",
            name:"",
            members: 0,
            error: null,
            success: null
        }
        this.attemptTeamRegister = this.attemptTeamRegister.bind(this);
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    attemptTeamRegister = () => {
        UserRegister.teamRegister(
            this.state.email,
            this.state.password,
            this.state.name,
            this.state.members
        ).then(res =>{
            if(res.data.success!=null){
                this.setState({
                    success: res.data.success,
                    error: null
                })
                this.props.message(this.state.success);
            }else{
                this.setState({
                    error: res.data.error,
                    success: null
                })
            }
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
        if (this.state.success != null) {
            return (
                <Redirect to={"/login"}/>
            );
        }

        return (
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptTeamRegister}>
                    <Form.Input id="email" name="email" type='email' required fluid label='E-mail'
                                placeholder='Enter e-mail.' onChange={this.handleCheck}/>
                    <Form.Input id="password" name="password" type='password' required fluid label='Password'
                                placeholder='Enter password.' onChange={this.handleCheck}/>
                    <Form.Input id="name" name="name" type='text' required fluid label='Name'
                                placeholder='Enter company name.' onChange={this.handleCheck}/>
                    <Form.Input id="members" name="members" type="number" required fluid label="Number of members"
                                placeholder="Enter member count." onChange={this.handleCheck}/>
                    <Form.Field control={Button}>Register</Form.Field>
                </Form>
            </Container>
        );
    }
}

export default RegisterTeam;