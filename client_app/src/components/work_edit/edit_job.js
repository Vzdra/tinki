import React,{Component} from 'react';
import {Button, Container, Dropdown, Form, Label} from "semantic-ui-react";
import {Redirect} from "react-router-dom";
import WorkEdit from "../../repository/edit_work_repo";

class EditJob extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: props.location.state.item.id,
            index: props.location.state.index,
            title: props.location.state.item.title,
            description: props.location.state.item.description,
            salary: props.location.state.item.salary,
            accountId: props.location.state.item.accountId,
            accountType: props.location.state.item.accountType,
            success: false,
            error: false,
        }

        this.attemptJobEdit = this.attemptJobEdit.bind(this);
    }

    handleCheck = (e, {value}) => {
        this.setState({
            ...this.state,
            [e.target.name]: value
        })
    }

    attemptJobEdit(){
        WorkEdit.jobEdit(
            this.state.id,
            this.state.title,
            this.state.description,
            this.state.accountId,
            this.state.salary
        ).then(res => {
            if(res.data.error===null){
                this.props.editJob(res.data, this.state.index);
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
        if(this.state.accountId==null || this.state.accountType==="USER"){
            return(
                <Redirect to={"/login"}/>
            );
        }

        if(this.state.success===true){
            if(this.state.accountType==="COMPANY"){
                return(
                    <Redirect to={"/company/jobs"}/>
                );
            }else{
                return(
                    <Redirect to={"/team/jobs"}/>
                );
            }
        }

        return(
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptJobEdit}>
                    <Form.Input id="title" name="title" type='text' value={this.state.title} required fluid label='Title'
                                placeholder='Enter title...' onChange={this.handleCheck}/>
                    <Form.Input id="description" name="description" value={this.state.description} type='text' required fluid label='Description'
                                placeholder='Enter description...' onChange={this.handleCheck}/>
                    <Form.Input id="salary" name="salary" type='number' value={this.state.salary} required fluid label='Salary'
                                placeholder='Enter salary...' onChange={this.handleCheck}/>
                    <Button type="submit">Confirm</Button>
                </Form>
            </Container>
        );
    }
}

export default EditJob;