import React from 'react';
import 'semantic-ui-react';
import {Button, Container, Dropdown, Form, Label} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import SkillFetch from "../../repository/skill_repo";
import {Component} from "react/cjs/react.production.min";
import AccountEdit from "../../repository/edit_account_repo";

class EditUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: props.oldUser.email,
            name: props.oldUser.name,
            surname: props.oldUser.surname,
            retainedSkills: props.oldUser.retained,
            skillsToLearn: props.oldUser.toLearn,
            type: props.oldUser.type,
            error: null,
            success: null,
            sortedOptions:[],
            id: props.oldUser.id,
            oldEmail: props.oldUser.email,
            toLearn: [],
            retained: []
        }
        this.attemptEdit = this.attemptEdit.bind(this);
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    setKnown = (e, {value}) =>{
        this.setState({
            retained: value
        })
    }

    setToKnow = (e, {value}) =>{
        this.setState({
            toLearn: value
        })
    }

    setIds = () => {
        let k = [];
        let m = [];

        this.state.skillsToLearn.forEach(item => {
            k.push(item.id);
        })

        this.state.retainedSkills.forEach(item=>{
            m.push(item.id);
        })

        this.setState({
            toLearn: k,
            retained: m
        })
    }

    attemptEdit(){
        AccountEdit.userEdit(
            this.state.id,
            this.state.oldEmail,
            this.state.email,
            this.state.name,
            this.state.surname,
            this.state.retained,
            this.state.toLearn
        ).then(res => {
            this.setState({
                success: true,
                error: null
            })
            this.props.updateUser(res.data);
        }).catch(err => {
            this.setState({
                success: null,
                error: "Error editing user.",
            })
        });
    }

    render() {
        if(this.state.success!=null){
            return(
                <Redirect to={"/profile"}/>
            );
        }

        if(this.state.type!=="USER"){
            return(
                <Redirect to={"/profile"}/>
            );
        }

        return (
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptEdit}>
                    <Form.Input id="email" name="email" type='email' required fluid label='E-mail'
                                placeholder='Enter e-mail.' value={this.state.email} onChange={this.handleCheck}/>
                    <Form.Input id="name" name="name" type='text' required fluid label='Name'
                                placeholder='Enter name.' value={this.state.name} onChange={this.handleCheck}/>
                    <Form.Input id="surname" name="surname" type='text' required fluid label='Surname'
                                placeholder='Enter surname.' value={this.state.surname} onChange={this.handleCheck}/>
                    <Label>Select skills you know:</Label>
                    <Dropdown placeholder="Skills you know..." fluid multiple selection value={this.state.retained} options={this.state.sortedOptions} onChange={this.setKnown}/>
                    <br/>
                    <Label>Select skills you want to know:</Label>
                    <Dropdown placeholder="Skills you want to learn..." fluid multiple value={this.state.toLearn} selection options={this.state.sortedOptions} onChange={this.setToKnow}/>
                    <br/>
                    <Button primary type="submit">Edit</Button>
                </Form>
            </Container>
        );
    }

    componentDidMount(){
        this.props.message(null);

        SkillFetch.fetchAll().then((data) =>{
            let sorted = [];
            data.data.forEach(item => {
                let obj = {
                    key: item.id,
                    text: item.name,
                    value: item.id
                }
                sorted.push(obj);
            });

            this.setState({
                sortedOptions: sorted
            })
        });

        this.setIds();
    }


}

export default EditUser;