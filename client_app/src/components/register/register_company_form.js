import React from 'react';
import 'semantic-ui-react';
import {Button, Container, Form} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import UserRegister from "../../repository/register_repo";
import {Component} from "react/cjs/react.production.min";

class RegisterCompany extends Component  {
    constructor(props){
        super(props);
        this.state = {
            email: "",
            password: "",
            name:"",
            members: "",
            city: "",
            street: "",
            error: null,
            success: null
        }
        this.attemptCompanyRegister = this.attemptCompanyRegister.bind(this);
    }

    handleCheck = (e, {value}) => {
            this.setState({
                ...this.state,
                [e.target.name]: value
            })
    }

    attemptCompanyRegister = () => {
        UserRegister.companyRegister(
            this.state.email,
            this.state.password,
            this.state.name,
            this.state.country
            ,this.state.city,
            this.state.street
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
                error: "Company already exists!",
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
                <Redirect to={"/login"}/>
            );
        }

            return (
                <Container>
                    <h1 style={{color: "red"}}>{this.state.error}</h1>
                    <Form onSubmit={this.attemptCompanyRegister}>
                        <Form.Input id="email" name="email" type='email' required fluid label='E-mail'
                                    placeholder='Enter e-mail.' onChange={this.handleCheck}/>
                        <Form.Input id="password" name="password" type='password' required fluid label='Password'
                                    placeholder='Enter password.' onChange={this.handleCheck}/>
                        <Form.Input id="name" name="name" type='text' required fluid label='Name'
                                    placeholder='Enter company name.' onChange={this.handleCheck}/>
                        <Form.Input id="country" name="country" type='text' required fluid label='Country'
                                    placeholder='Enter your country.' onChange={this.handleCheck}/>
                        <Form.Input id="city" name="city" type='text' required fluid label='City'
                                    placeholder='Enter your city.' onChange={this.handleCheck}/>
                        <Form.Input id="street" name="street" type='text' required fluid label='Street'
                                    placeholder='Enter address street.' onChange={this.handleCheck}/>
                        <Form.Field control={Button}>Register</Form.Field>
                    </Form>
                </Container>
            );
    }
}

export default RegisterCompany;