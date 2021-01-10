import React from 'react';
import {Link} from "react-router-dom";
import {Menu, MenuItem} from "semantic-ui-react";


const HeaderComp = (props) => {
    if(props.accType == null){
        return(
            <Menu inverted position="right">
                <MenuItem as={Link} to='/login'>
                    Login
                </MenuItem>
            </Menu>
        );
    }else{
        if(props.accType==="COMPANY"){
            return(
                <Menu inverted position="right">
                    <Menu.Item as={Link} to='/profile'>
                        Profile
                    </Menu.Item>
                    <Menu.Item as={Link} to='/jobs'>
                        Jobs
                    </Menu.Item>
                    <Menu.Item as={Link} to='/internships'>
                        Internships
                    </Menu.Item>
                </Menu>
            );
        }else if(props.accType==="TEAM"){
            return(
                <Menu inverted position="right">
                    <Menu.Item as={Link} to='/profile'>
                        Profile
                    </Menu.Item>
                    <Menu.Item as={Link} to='/jobs'>
                        Jobs
                    </Menu.Item>
                    <Menu.Item as={Link} to='/projects'>
                        Projects
                    </Menu.Item>
                </Menu>
            );
        }else if(props.accType==="USER"){
            return(
                <Menu inverted position="right">
                    <Menu.Item as={Link} to='/profile'>
                        Profile
                    </Menu.Item>
                    <Menu.Item as={Link} to='/jobs'>
                        Jobs
                    </Menu.Item>
                    <Menu.Item as={Link} to='/internships'>
                        Internships
                    </Menu.Item>
                    <Menu.Item as={Link} to='/projects'>
                        Projects
                    </Menu.Item>
                    <Menu.Item as={Link} to='/search'>
                        Search
                    </Menu.Item>
                </Menu>
            );
        }
    }
}

export default HeaderComp;