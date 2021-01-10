import React from 'react';
import { Button, Checkbox, Container, Form } from "semantic-ui-react";
import { Redirect } from 'react-router-dom';

const Login = (props) => {
    const [formData, updateFormData] = React.useState({
        email: "",
        password: "",
        type: "0"
    })

    const handleCheck = (e, {value}) => {
        updateFormData({
            ...formData,
            [e.target.name]: value
        })
    }

    const onFormSubmit = (e) =>{
        e.preventDefault();
        const email = formData.email;
        const password = formData.password;
        const type = formData.type;

        props.onCompleteForm(email,password,type);
    }

    if(!props.loggedIn){
        return(
            <Container>
                <h1 style={{color: "red"}}>{props.error}</h1>
                <Form onSubmit={onFormSubmit}>
                    <Form.Input id="email" name="email" type='text' required fluid label='E-mail' placeholder='Enter e-mail.' onChange={handleCheck} />
                    <Form.Input id="password" name="password" type='password' required fluid label='Password' placeholder='Enter password.' onChange={handleCheck} />
                    <Form.Field
                        control={Checkbox}
                        radio
                        label='User'
                        id="0"
                        name="type"
                        value="0"
                        checked={formData.type === "0"}
                        onChange={handleCheck}
                    />
                    <Form.Field
                        control={Checkbox}
                        radio
                        label='Team'
                        id="1"
                        name="type"
                        value="1"
                        checked={formData.type === "1"}
                        onChange={handleCheck}
                    />
                    <Form.Field
                        control={Checkbox}
                        radio
                        label='Company'
                        id="2"
                        name="type"
                        value="2"
                        checked={formData.type === "2"}
                        onChange={handleCheck}
                    />
                    <Form.Field control={Button}>LogIn</Form.Field>
                </Form>
            </Container>
        );
    }

    return(
        <Redirect to={"/profile"}/>
    );

}

export default Login;