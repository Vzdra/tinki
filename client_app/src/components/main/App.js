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
import RegisterUser from "../register/register_user_form";
import RegisterCompany from "../register/register_company_form";
import RegisterTeam from "../register/register_team_form";
import Search from "../filter/search";
import EditUser from "../account_edit/user_edit";
import EditCompany from "../account_edit/company_edit";
import EditTeam from "../account_edit/team_edit";
import JobRegister from "../work_register/job_register";
import InternshipRegister from "../work_register/internship_register";
import ProjectRegister from "../work_register/project_register";
import EditJob from "../work_edit/test";

class App extends Component{
  constructor(props) {
    super(props);
    this.state = {
        logged: false,
        error: null,
        success: null,
        currentUser: {
        }
    }
  }

    render(){
          return(
              <Router>
                  <HeaderComp acc={this.state.currentUser.email} accType={this.state.currentUser.type} name={this.state.currentUser.name} removeState={this.removeState}/>
                  <main>
                      <Route path={"/login"} render={() => <Login success={this.state.success} error={this.state.error} onCompleteForm={this.attemptLogin} loggedIn={this.state.logged}/>}/>
                      <Route path={"/profile"} render={() => <Profile userProfile={this.state.currentUser}/>}/>
                      <Route path={"/user/jobs"} render={() => <UserJobs userProfile={this.state.currentUser}/>}/>
                      <Route path={"/user/internships"} render={() => <UserInternships userProfile={this.state.currentUser}/>}/>
                      <Route path={"/user/projects"} render={() => <UserProjects userProfile={this.state.currentUser}/>}/>
                      <Route path={"/team/jobs"} render={() => <TeamJobs userProfile={this.state.currentUser}/>}/>
                      <Route path={"/team/projects"} render={() => <TeamProjects userProfile={this.state.currentUser}/>}/>
                      <Route path={"/company/jobs"} render={() => <CompanyJobs userProfile={this.state.currentUser}/>}/>
                      <Route path={"/company/internships"} render={() => <CompanyInternships userProfile={this.state.currentUser}/>}/>
                      <Route path={"/register/user"} render={() => <RegisterUser message={this.setSuccess} />}/>
                      <Route path={"/register/company"} render={() => <RegisterCompany message={this.setSuccess} />}/>
                      <Route path={"/register/team"} render={() => <RegisterTeam message={this.setSuccess}/>}/>
                      <Route path={"/user/search"} render={() => <Search loggedIn={this.state.logged}/>}/>
                      <Route path={"/user/edit"} render={() => <EditUser oldUser={this.state.currentUser} updateUser={this.updateUser} message={this.setSuccess}/>}/>
                      <Route path={"/company/edit"} render={() => <EditCompany oldUser={this.state.currentUser} updateUser={this.updateUser} message={this.setSuccess}/>}/>
                      <Route path={"/team/edit"} render={() => <EditTeam oldUser={this.state.currentUser} updateUser={this.updateUser} message={this.setSuccess}/>}/>
                      <Route path={"/register/job"} render={() => <JobRegister accountId={this.state.currentUser.id} type={this.state.currentUser.type} updateJobs={this.updateJobs} />}/>
                      <Route path={"/register/internship"} render={() => <InternshipRegister accountId={this.state.currentUser.id} type={this.state.currentUser.type} updateInternships={this.updateInternships} />}/>
                      <Route path={"/register/project"} render={() => <ProjectRegister accountId={this.state.currentUser.id} type={this.state.currentUser.type} updateProjects={this.updateProjects} />}/>
                      <Route path={"/edit/job"} render={(props) => <EditJob {...props}  editJob={this.editJob}/>} />
                      {/*<Route path={"/edit/internship"} render={(props) => <EditInternship {...props}  editJob={this.editInternship}/>} />*/}
                      {/*<Route path={"/edit/project"} render={(props) => <EditProject {...props}  editJob={this.editProject}/>} />*/}
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

  updateUser = (user) =>{
      this.setState({
          currentUser: user
      })
  }

  setSuccess = (message) => {
      this.setState({
          success: message,
          error: null
      })
  }

  updateJobs = (job) => {
      this.setState(prevState => ({
         currentUser:{
             ...prevState.currentUser,
             jobs: [...prevState.currentUser.jobs, job]
         }
      }))
  }

    updateInternships = (internship) => {
        this.setState(prevState => ({
            currentUser:{
                ...prevState.currentUser,
                internships: [...prevState.currentUser.internships, internship]
            }
        }))
    }

    updateProjects = (project) => {
        this.setState(prevState => ({
            currentUser:{
                ...prevState.currentUser,
                projects: [...prevState.currentUser.projects, project]
            }
        }))
    }

    editJob = (job, index) => {
      this.setState(prevState => ({
          currentUser:{
              ...prevState.currentUser,
              jobs: Object.assign([],this.state.jobs,{[index]: job})
          }
      }))
    }

    editInternship = (internship, index) => {
        this.setState(prevState => ({
            currentUser:{
                ...prevState.currentUser,
                internships: Object.assign([],this.state.jobs,{[index]: internship})
            }
        }))
    }

    editProject = (project, index) => {
        this.setState(prevState => ({
            currentUser:{
                ...prevState.currentUser,
                projects: Object.assign([],this.state.jobs,{[index]: project})
            }
        }))
    }

  attemptLogin = (email, password, type) => {
      UserLogin.login(email, password, type).then((res) =>{
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
