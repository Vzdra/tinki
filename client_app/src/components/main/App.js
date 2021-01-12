import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Redirect} from 'react-router-dom';
import Login from '../login/login';
import Profile from "../data/profile";
import 'semantic-ui-css/semantic.min.css';
import HeaderComp from '../template/header';
import './App.css';
import UserLogin from "../../repository/login_repo";
import UserJobs from "../data/components/user_jobs";
import UserInternships from "../data/components/user_internships";
import UserProjects from "../data/components/user_projects";
import CompanyJobs from "../data/components/company_jobs";
import TeamProjects from "../data/components/team_projects";
import TeamJobs from "../data/components/team_jobs";
import CompanyInternships from "../data/components/company_internships";

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
                      <Route path={"/login"} render={() => <Login error={this.state.error} onCompleteForm={this.attemptLogin} loggedIn={this.state.logged}/>} />
                      <Route path={"/profile"} render={() => <Profile userProfile={this.state.currentUser}/>} />
                      <Route path={"/user/jobs"} render={() => <UserJobs userProfile={this.state.currentUser}/>} />
                      <Route path={"/user/internships"} render={() => <UserInternships userProfile={this.state.currentUser}/>} />
                      <Route path={"/user/projects"} render={() => <UserProjects userProfile={this.state.currentUser}/>} />
                      <Route path={"/team/jobs"} render={() => <TeamJobs userProfile={this.state.currentUser} />}/>
                      <Route path={"/team/projects"} render={() => <TeamProjects userProfile={this.state.currentUser} />}/>
                      <Route path={"/company/jobs"} render={() => <CompanyJobs userProfile={this.state.currentUser} />}/>
                      <Route path={"/company/internships"} render={() => <CompanyInternships userProfile={this.state.currentUser}/>} />
                      <Route path={"/profile/edit"} />
                      <Route path={"/job/edit"} />
                      <Route path={"/internship/edit"}/>
                      <Route path={"/project/edit"} />
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
