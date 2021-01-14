import React, { useEffect, useState } from 'react';
import { Button, Checkbox, Container, Form } from "semantic-ui-react";
import { Redirect } from 'react-router-dom';
import Jobs from "../item_components/job_component";
import JobSearch from "../../repository/search_repo";
import Internships from "../item_components/internship_component";

const Search = (props) => {
    const [formData, updateData] = useState({
        text: "",
        type: "0",
        items: [],
        searchedType: "0"
    })

    const handleCheck = (e, {value}) => {
        updateData({
            ...formData,
            [e.target.name]: value
        })
    }

    useEffect(() => {
        updateData({
            ...formData,
            text: "",
            type: "0"
        })
    }, [formData.items]);

    const onFormSubmit = (e) =>{
        e.preventDefault();
        updateData({
            ...formData,
            searchedType: formData.type
        })

        if(formData.type==="0"){
            JobSearch.job(formData.text).then(res => {
                updateData({
                    items: res.data
                });
            })
        }else if(formData.type==="1"){
            JobSearch.internship(formData.text).then(res => {
                updateData({
                    items: res.data
                });
            })
        }else{
            JobSearch.project(formData.text).then(res => {
                updateData({
                    items: res.data
                });
            })
        }
    }

    if(props.loggedIn){
        return(
            <Container>
                <Form onSubmit={onFormSubmit}>
                    <Form.Input id="text" name="text" type="text" value={formData.text} fluid label='Enter keyword:' placeholder='Keyword...' onChange={handleCheck} />
                    <Form.Field
                        control={Checkbox}
                        radio
                        label='Job'
                        id="0"
                        name="type"
                        value="0"
                        checked={formData.type === "0"}
                        onChange={handleCheck}
                    />
                    <Form.Field
                        control={Checkbox}
                        radio
                        label='Internship'
                        id="1"
                        name="type"
                        value="1"
                        checked={formData.type === "1"}
                        onChange={handleCheck}
                    />
                    <Form.Field
                        control={Checkbox}
                        radio
                        label='Project'
                        id="2"
                        name="type"
                        value="2"
                        checked={formData.type === "2"}
                        onChange={handleCheck}
                    />
                    <Form.Field control={Button}>Search</Form.Field>
                </Form>

                {formData.items.map(item => {
                    return  (item.skillsTrained && <Internships
                            title={item.title}
                            description={item.description}
                            accountName={item.accountName}
                            accountEmail={item.accountEmail}
                            skills={item.skillsTrained}
                    />) || (<Jobs
                        title={item.title}
                        description={item.description}
                        accountName={item.accountName}
                        accountEmail={item.accountEmail}
                        skills={item.skillsRequired}
                    />)
                })}
            </Container>
        );
    }

    return(
        <Redirect to={"/profile"}/>
    );

}

export default Search;