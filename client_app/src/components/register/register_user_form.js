import React from 'react';
import { Button, Checkbox, Container, Form } from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import SkillFetch from "../../repository/skill_repo";
import UserRegister from "../../repository/register_repo";

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

    render() {
        return (
            <Container>
                <h1 style={{color: "red"}}>{this.state.error}</h1>
                <Form onSubmit={this.attemptRegister}>
                    <Form.Input id="email" name="email" type='email' required fluid label='E-mail'
                                placeholder='Enter e-mail.' onChange={handleCheck}/>
                    <Form.Input id="password" name="password" type='password' required fluid label='Password'
                                placeholder='Enter password.' onChange={handleCheck}/>
                                
                    <Button type="submit">Register</Button>
                </Form>
            </Container>
        );
    }

    componentDidMount(){
        SkillFetch.fetchAll().then((data) =>{
            this.setState({
                allSkills: data
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