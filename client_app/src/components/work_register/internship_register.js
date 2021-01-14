import React from 'react';
import 'semantic-ui-react';
import {Button, Container, Dropdown, Form, Label} from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import SkillFetch from "../../repository/skill_repo";
import {Component} from "react/cjs/react.production.min";
import WorkRegister from "../../repository/work_register_repo";

class InternshipRegister extends Component {
    constructor(props) {
        super(props);
        this.state = {
            title: "",
            description: "",
            accountId: props.accountId,
            salary: 0,
            type: props.type,
            openSpots: 0,
            skillsTrained: [],
            error: null,
            success: null,
            sortedOptions: []
        }
        this.attemptInternshipRegister = this.attemptInternshipRegister.bind(this);
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    setSkills = (e, {value}) =>{
        this.setState({
            skillsTrained: value
        })
    }

    attemptInternshipRegister(){
        WorkRegister.internshipRegister(
            this.state.title,
            this.state.description,
            this.state.accountId,
            this.state.salary,
            this.state.type,
            this.state.openSpots,
            this.state.skillsTrained
        ).then(res => {
            this.props.updateInternships(res.data);
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
                    <Redirect to={"/company/internships"}/>
                );
            }else{
                return(
                    <Redirect to={"/profile"}/>
                );
            }
        }

        return (
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptInternshipRegister}>
                    <Form.Input id="title" name="title" type='text' required fluid label='Title'
                                placeholder='Enter title...' onChange={this.handleCheck}/>
                    <Form.Input id="description" name="description" type='text' required fluid label='Description'
                                placeholder='Enter description...' onChange={this.handleCheck}/>
                    <Form.Input id="salary" name="salary" type='number' required fluid label='Salary'
                                placeholder='Enter salary...' onChange={this.handleCheck}/>
                    <Form.Input id="openSpots" name="openSpots" type='number' required fluid label='Open Spots'
                                placeholder='Enter open spots...' onChange={this.handleCheck}/>
                    <Label>Select trained skills:</Label>
                    <Dropdown placeholder="Skills trained on this internship..." fluid multiple selection options={this.state.sortedOptions} onChange={this.setSkills}/>
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

export default InternshipRegister;