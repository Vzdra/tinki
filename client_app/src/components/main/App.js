import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Redirect} from 'react-router-dom';
import Login from '../login/login';
import Profile from "../data/profile";
import 'semantic-ui-css/semantic.min.css';
import HeaderComp from '../template/header';
import './App.css';
import UserLogin from "../../repository/login_repo";
import UserJobs from "../data/components/user_jobs";

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
                  <HeaderComp acc={this.state.currentUser.email} accType={this.state.currentUser.type} name={this.state.currentUser.name}/>
                  <main>
                      <Route path={"/login"} render={() => <Login error={this.state.error} onCompleteForm={this.attemptLogin} loggedIn={this.state.logged}/>}/>
                      <Route path={"/profile"} render={() => <Profile userProfile={this.state.currentUser}/>}/>
                      <Route path={"/jobs"} render={() => <UserJobs userProfile={this.state.currentUser}/>}/>
                      <Route path={"/"} render={() => <Redirect to={"/login"}/>}/>
                  </main>
              </Router>
          );
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
