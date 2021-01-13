import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Redirect} from 'react-router-dom';
import Login from '../login/login';
import Profile from "../profiles/profile";
import 'semantic-ui-css/semantic.min.css';
import HeaderComp from '../template/header';
import './App.css';
import UserLogin from "../../repository/login_repo";
import UserJobs from "../work/user_jobs";
import UserInternships from "../work/user_internships";
import UserProjects from "../work/user_projects";
import CompanyJobs from "../work/company_jobs";
import TeamProjects from "../work/team_projects";
import TeamJobs from "../work/team_jobs";
import CompanyInternships from "../work/company_internships";
import UserRegister from "../../repository/register_repo";
import RegisterUser from "../register/register_user_form";

class App extends Component{
  constructor(props) {
    super(props);
    this.state = {
        logged: false,
        error: null,
        currentUser: {
        }
    }
  }

  render(){
          return(
              <Router>
                  <HeaderComp acc={this.state.currentUser.email} accType={this.state.currentUser.type} name={this.state.currentUser.name} removeState={this.removeState}/>
                  <main>
                      <Route path={"/login"} render={() => <Login error={this.state.error} onCompleteForm={this.attemptLogin} loggedIn={this.state.logged}/>}/>
                      <Route path={"/profile"} render={() => <Profile userProfile={this.state.currentUser}/>}/>
                      <Route path={"/user/jobs"} render={() => <UserJobs userProfile={this.state.currentUser}/>}/>
                      <Route path={"/user/internships"} render={() => <UserInternships userProfile={this.state.currentUser}/>}/>
                      <Route path={"/user/projects"} render={() => <UserProjects userProfile={this.state.currentUser}/>}/>
                      <Route path={"/team/jobs"} render={() => <TeamJobs userProfile={this.state.currentUser}/>}/>
                      <Route path={"/team/projects"} render={() => <TeamProjects userProfile={this.state.currentUser}/>}/>
                      <Route path={"/company/jobs"} render={() => <CompanyJobs userProfile={this.state.currentUser}/>}/>
                      <Route path={"/company/internships"} render={() => <CompanyInternships userProfile={this.state.currentUser}/>}/>
                      <Route path={"/register/user"} render={() => <RegisterUser error={null} success={null}/>}/>
                      <Route path={"/logout"} render={() => <Redirect to={"/login"}/>}/>
                      <Route path={"/"} render={() => <Redirect to={"/login"}/>}/>
                  </main>
              </Router>
          );
  }

  removeState = () => {
      this.setState({
          logged: false,
          error: null,
          currentUser: {}
      })
  }


  attemptLogin = (email, password, type) => {
      UserLogin.login(email, password, type).then((res) =>{
          console.log(res.data);
          if(res.data.email==null){
              this.setState({
                  logged: false,
                  error: res.data.error,
              });
          }else{
              this.setState({
                  logged: true,
                  currentUser: res.data,
                  error: null,
              });
          }
      });

      if(this.state.currentUser.email!=null){
          return <Redirect to={"/profile"}/>;
      }
  }
}

export default App;
