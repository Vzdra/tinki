import React from 'react';
import 'semantic-ui-react';
import {Button, Container, Dropdown, Form, Label} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import SkillFetch from "../../repository/skill_repo";
import {Component} from "react/cjs/react.production.min";
import WorkRegister from "../../repository/work_register_repo";

class JobRegister extends Component {
    constructor(props) {
        super(props);
        this.state = {
            title: "",
            description: "",
            accountId: props.accountId,
            salary: 0,
            type: props.type,
            skillsRequired: [],
            error: null,
            success: null,
            sortedOptions: []
        }
        this.attemptJobRegister = this.attemptJobRegister.bind(this);
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    setSkills = (e, {value}) =>{
        this.setState({
            skillsRequired: value
        })
    }

    attemptJobRegister(){
        WorkRegister.jobRegister(
            this.state.title,
            this.state.description,
            this.state.accountId,
            this.state.salary,
            this.state.type,
            this.state.skillsRequired
        ).then(res => {
            this.props.updateJobs(res.data);
            this.setState({
                success: true,
                error: null
            })
        }).catch(err => {
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
            if(this.state.type==="COMPANY"){
                return(
                    <Redirect to={"/company/jobs"}/>
                );
            }else{
                return(
                    <Redirect to={"/team/jobs"}/>
                );
            }
        }

        return (
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptJobRegister}>
                    <Form.Input id="title" name="title" type='text' required fluid label='Title'
                                placeholder='Enter title...' onChange={this.handleCheck}/>
                    <Form.Input id="description" name="description" type='text' required fluid label='Description'
                                placeholder='Enter description...' onChange={this.handleCheck}/>
                    <Form.Input id="salary" name="salary" type='number' required fluid label='Salary'
                                placeholder='Enter salary...' onChange={this.handleCheck}/>
                    <Label>Select required skills:</Label>
                    <Dropdown placeholder="Skills needed for the job..." fluid multiple selection options={this.state.sortedOptions} onChange={this.setSkills}/>
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

export default JobRegister;