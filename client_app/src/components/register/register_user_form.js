import React from 'react';
import 'semantic-ui-react';
import {Button, Container, Form} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import SkillFetch from "../../repository/skill_repo";
import UserRegister from "../../repository/register_repo";
import {Component} from "react/cjs/react.production.min";

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
            allSkills: [],
            error: props.error
        }
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    render() {
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
                                <label>Skills you know:</label>
                    <select multiple="" class="ui dropdown">
                        {this.state.allSkills.map(item => {
                            return <option value={item.id}>{item.name}</option>
                        })}
                    </select>
                    <Button type="submit">Register</Button>
                </Form>
            </Container>
        );
    }

    componentDidMount(){
        SkillFetch.fetchAll().then((data) =>{
            this.setState({
                allSkills: data.data
            })
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
        ).then(data => {
            if(data.success){
                return <Redirect to={"/login"}/>
            }else{
                return <Redirect to={"/user/register"}  error={data.error}/>
            }
        })
    }
}

export default RegisterUser;