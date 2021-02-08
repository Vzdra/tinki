import React,{Component} from 'react';
import {Button, Container, Dropdown, Form, Label} from "semantic-ui-react";
import {Redirect} from "react-router-dom";
import WorkEdit from "../../repository/edit_work_repo";

class EditInternship extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: props.location.state.item.id,
            index: props.location.state.index,
            title: props.location.state.item.title,
            description: props.location.state.item.description,
            salary: props.location.state.item.salary,
            openSpots: props.location.state.item.openSpots,
            accountId: props.location.state.item.accountId,
            accountType: props.location.state.item.accountType,
            success: false,
            error: false,
        }

        this.attemptInternshipEdit = this.attemptInternshipEdit.bind(this);
    }

    handleCheck = (e, {value}) => {
        console.log(e.target.name);
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    attemptInternshipEdit(){
        WorkEdit.internshipEdit(
            this.state.id,
            this.state.title,
            this.state.description,
            this.state.accountId,
            this.state.salary,
            this.state.openSpots
        ).then(res => {
            console.log(res.data);
            if(res.data.error===null){
                this.props.editInternship(res.data, this.state.index);
                this.setState({
                    success: true,
                    error: null
                })
            }else{
                this.setState({
                    success: false,
                    error: res.data.error
                })
            }
        }).catch(err => {
            this.setState({
                error: "Error editing job!",
                success: null
            })
        });
    }

    render(){
        if(this.state.accountId==null || this.state.accountType==="USER" || this.state.accountType==="TEAM"){
            return(
                <Redirect to={"/login"}/>
            );
        }

        if(this.state.success===true){
            if(this.state.accountType==="COMPANY"){
                return(
                    <Redirect to={"/company/internships"}/>
                );
            }
        }

        return(
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptInternshipEdit}>
                    <Form.Input id="title" name="title" type='text' value={this.state.title} required fluid label='Title'
                                placeholder='Enter title...' onChange={this.handleCheck}/>
                    <Form.Input id="description" name="description" value={this.state.description} type='text' required fluid label='Description'
                                placeholder='Enter description...' onChange={this.handleCheck}/>
                    <Form.Input id="salary" name="salary" type='number' value={this.state.salary} required fluid label='Salary'
                                placeholder='Enter salary...' onChange={this.handleCheck}/>
                    <Form.Input id="openSpots" name="openSpots" type='openSpots' value={this.state.openSpots} required fluid label='Open Spots'
                                placeholder='Enter open spots...' onChange={this.handleCheck}/>
                    <Button type="submit">Confirm</Button>
                </Form>
            </Container>
        );
    }
}

export default EditInternship;