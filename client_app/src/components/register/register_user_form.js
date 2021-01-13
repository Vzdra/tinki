import React from 'react';
import 'semantic-ui-react';
import {Button, Container, Dropdown, Form, Label} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import SkillFetch from "../../repository/skill_repo";
import UserRegister from "../../repository/register_repo";
import {Component} from "react/cjs/react.production.min";
import Login from "../login/login";

class RegisterUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            type: "0",
            name: "",
            surname: "",
            retainedSkills: [],
            skillsToLearn: [],
            error: null,
            success: null,
            sortedOptions:[]
        }
        this.attemptRegister = this.attemptRegister.bind(this);
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    setKnown = (e, {value}) =>{
        this.setState({
            retainedSkills: value
        })
    }

    setToKnow = (e, {value}) =>{
        this.setState({
            skillsToLearn: value
        })
    }

    attemptRegister(){
        UserRegister.userRegister(
            this.state.email,
            this.state.password,
            this.state.name,
            this.state.surname,
            this.state.retainedSkills,
            this.state.skillsToLearn
        ).then(res => {
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
                error: "User already exists!",
                success: null
            })
        });
    }

    render() {
        if(this.state.success!=null){
            return(
                <Redirect to={"/login"}/>
            );
        }

        return (
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptRegister}>
                    <Form.Input id="email" name="email" type='email' required fluid label='E-mail'
                                placeholder='Enter e-mail.' onChange={this.handleCheck}/>
                    <Form.Input id="password" name="password" type='password' required fluid label='Password'
                                placeholder='Enter password.' onChange={this.handleCheck}/>
                    <Form.Input id="name" name="name" type='text' required fluid label='Name'
                                placeholder='Enter name.' onChange={this.handleCheck}/>
                    <Form.Input id="surname" name="surname" type='text' required fluid label='Surname'
                                placeholder='Enter surname.' onChange={this.handleCheck}/>
                                <Label>Select skills you know:</Label>
                    <Dropdown placeholder="Skills you know..." fluid multiple selection options={this.state.sortedOptions} onChange={this.setKnown}/>
                    <br/>
                    <Label>Select skills you want to know:</Label>
                    <Dropdown placeholder="Skills you want to learn..." fluid multiple selection options={this.state.sortedOptions} onChange={this.setToKnow}/>
                    <br/>
                    <Button type="submit">Register</Button>
                </Form>
            </Container>
        );
    }

    componentDidMount(){
        this.props.message(null);

        SkillFetch.fetchAll().then((data) =>{
            var sorted = [];
            data.data.forEach(item => {
               var obj = {
                   key: item.id,
                   text: item.name,
                   value: item.id
               }
               sorted.push(obj);
            });

            this.setState({
                sortedOptions: sorted
            })
        })
    }
}

export default RegisterUser;