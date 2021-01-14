import React from 'react';
import 'semantic-ui-react';
import SemanticDatePicker from 'react-semantic-ui-datepickers';
import {Button, Container, Dropdown, Form, Label} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import SkillFetch from "../../repository/skill_repo";
import {Component} from "react/cjs/react.production.min";
import WorkRegister from "../../repository/work_register_repo";

class ProjectRegister extends Component {
    constructor(props) {
        super(props);
        this.state = {
            title: "",
            description: "",
            accountId: props.accountId,
            salary: 0,
            type: props.type,
            validUntil: "",
            skillsRequired: [],
            error: null,
            success: null,
            sortedOptions: []
        }
        this.attemptProjectRegister = this.attemptProjectRegister.bind(this);
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }
    editValid = (e, {value}) => {
        this.setState({
            validUntil: value
        })
    }

    setSkills = (e, {value}) =>{
        this.setState({
            skillsRequired: value
        })
    }

    attemptProjectRegister(){
        WorkRegister.projectRegister(
            this.state.title,
            this.state.description,
            this.state.accountId,
            this.state.salary,
            this.state.type,
            this.state.validUntil,
            this.state.skillsRequired
        ).then(res => {
            this.props.updateProjects(res.data);
            this.setState({
                success: true,
                error: null
            })
        }).catch(err => {
            console.log(err);
            this.setState({
                error: "Error registering job!",
                success: null
            })
        });
    }

    render() {
        if(this.state.accountId==null || this.state.type==="USER"){
            return(
                <Redirect to={"/login"}/>
            );
        }

        if(this.state.success===true){
            return (
                <Redirect to={"/team/projects"}/>
            );
        }

        return (
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptProjectRegister}>
                    <Form.Input id="title" name="title" type='text' required fluid label='Title'
                                placeholder='Enter title...' onChange={this.handleCheck}/>
                    <Form.Input id="description" name="description" type='text' required fluid label='Description'
                                placeholder='Enter description...' onChange={this.handleCheck}/>
                    <Form.Input id="salary" name="salary" type='number' required fluid label='Salary'
                                placeholder='Enter salary...' onChange={this.handleCheck}/>
                                <SemanticDatePicker required local="en_US" format='YYYY-MM-DD' onChange={this.editValid} type="basic"/>
                                <br />
                    <Label>Select required skills:</Label>
                    <Dropdown placeholder="Skills needed for the project..." fluid multiple selection options={this.state.sortedOptions} onChange={this.setSkills}/>
                    <br/>
                    <Button type="submit">Add</Button>
                </Form>
            </Container>
        );
    }

    componentDidMount(){
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

export default ProjectRegister;