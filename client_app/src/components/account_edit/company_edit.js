import React from 'react';
import 'semantic-ui-react';
import {Button, Container, Form} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import UserRegister from "../../repository/register_repo";
import {Component} from "react/cjs/react.production.min";
import AccountEdit from "../../repository/edit_account_repo";

class EditCompany extends Component  {
    constructor(props){
        super(props);
        this.state = {
            email: props.oldUser.email,
            name: props.oldUser.name,
            country: props.oldUser.address.country,
            city: props.oldUser.address.city,
            street: props.oldUser.address.street,
            error: null,
            success: null,
            id: props.oldUser.id,
            oldEmail: props.oldUser.email,
            type: props.oldUser.type
        }
        this.attemptCompanyEdit = this.attemptCompanyEdit.bind(this);
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    attemptCompanyEdit = () => {
        AccountEdit.companyEdit(
            this.state.id,
            this.state.oldEmail,
            this.state.email,
            this.state.name,
            this.state.country,
            this.state.city,
            this.state.street
        ).then(res =>{
            this.setState({
                success: true,
                error: null
            })
            this.props.updateUser(res.data);
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
                <Redirect to={"/profile"}/>
            );
        }

        if(this.state.type!=="COMPANY"){
            return(
                <Redirect to={"/profile"}/>
            );
        }

        return (
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptCompanyEdit}>
                    <Form.Input id="email" name="email" type='email' required fluid label='E-mail'
                                placeholder='Enter e-mail.' value={this.state.email} onChange={this.handleCheck}/>
                    <Form.Input id="name" name="name" type='text' required fluid label='Name'
                                placeholder='Enter company name.' value={this.state.name} onChange={this.handleCheck}/>
                    <Form.Input id="country" name="country" type='text' required fluid label='Country'
                                placeholder='Enter your country.' value={this.state.country} onChange={this.handleCheck}/>
                    <Form.Input id="city" name="city" type='text' value={this.state.city} required fluid label='City'
                                placeholder='Enter your city.' onChange={this.handleCheck}/>
                    <Form.Input id="street" name="street" type='text' required fluid label='Street'
                                placeholder='Enter address street.' value={this.state.street} onChange={this.handleCheck}/>
                    <Form.Field control={Button}>Confirm</Form.Field>
                </Form>
            </Container>
        );
    }
}

export default EditCompany;