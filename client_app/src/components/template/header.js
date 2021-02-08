import React from 'react';
import {Link} from "react-router-dom";
import {Menu, MenuItem } from "semantic-ui-react";
import logo from "../../assets/logo.png";

const HeaderComp = (props) => {
    if(props.accType == null){
        return(
            <Menu inverted>
                <MenuItem>
                    <div className="ui mini image">
                        <img src={logo}/>
                    </div>
                </MenuItem>
                <MenuItem  as={Link} to='/register/user'>
                    Register User
                </MenuItem>
                <MenuItem as={Link} to='/register/company'>
                    Register Company
                </MenuItem>
                <MenuItem as={Link} to='/register/team'>
                    Register Team
                </MenuItem>
                <MenuItem position="right" as={Link} to='/login'>
                    Login
                </MenuItem>
            </Menu>
        );
    }else{
        if(props.accType==="COMPANY"){
            return(
                <Menu inverted>
                    <MenuItem>
                        <div className="ui mini image">
                            <img src={logo}/>
                        </div>
                    </MenuItem>
                    <Menu.Item as={Link} to='/profile'>
                        Profile
                    </Menu.Item>
                    <Menu.Item as={Link} to='/company/jobs'>
                        Jobs
                    </Menu.Item>
                    <Menu.Item as={Link} to='/company/internships'>
                        Internships
                    </Menu.Item>
                    <MenuItem onClick={props.removeState} position="right" as={Link} to='/logout'>
                        Logout
                    </MenuItem>
                </Menu>
            );
        }else if(props.accType==="TEAM"){
            return(
                <Menu inverted>
                    <MenuItem>
                        <div className="ui mini image">
                            <img src={logo}/>
                        </div>
                    </MenuItem>
                    <Menu.Item as={Link} to='/profile'>
                        Profile
                    </Menu.Item>
                    <Menu.Item as={Link} to='/team/jobs'>
                        Jobs
                    </Menu.Item>
                    <Menu.Item as={Link} to='/team/projects'>
                        Projects
                    </Menu.Item>
                    <MenuItem onClick={props.removeState} position="right" as={Link} to='/logout'>
                        Logout
                    </MenuItem>
                </Menu>
            );
        }else if(props.accType==="USER"){
            return(
                <Menu inverted>
                    <MenuItem>
                        <div className="ui mini image">
                            <img src={logo}/>
                        </div>
                    </MenuItem>
                    <Menu.Item as={Link} to='/profile'>
                        Profile
                    </Menu.Item>
                    <Menu.Item as={Link} to='/user/jobs'>
                        Jobs
                    </Menu.Item>
                    <Menu.Item as={Link} to='/user/internships'>
                        Internships
                    </Menu.Item>
                    <Menu.Item as={Link} to='/user/projects'>
                        Projects
                    </Menu.Item>
                    <Menu.Item as={Link} to='/user/search'>
                        Search
                    </Menu.Item>
                    <MenuItem onClick={props.removeState} position="right" as={Link} to='/logout'>
                        Logout
                    </MenuItem>
                </Menu>
            );
        }
    }
}

export default HeaderComp;