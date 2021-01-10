import React, {Component} from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import Login from '../login/login';
import Profile from "../data/profile";
import 'semantic-ui-css/semantic.min.css';
import HeaderComp from '../template/header';
import './App.css';
import UserLogin from "../../repository/login_repo";

class App extends Component{
  constructor(props) {
    super(props);
    this.state = {
      currentUser: {}
    }
  }

  render(){
      return(
          <Router>
              <HeaderComp acc={this.state.currentUser.email} accType={this.state.currentUser.type} name={this.state.currentUser.name}/>
              <main>
                  <Route path={"/login"} render={() => <Login onCompleteForm={this.attemptLogin}/>}/>
                  <Route path={"/profile"} render={() => <Profile userProfile={this.state.currentUser}/>}/>
                  <Route path={"/"}/>
                  <Route path={"/jobs"}/>
                  <Route path={"/internships"}/>
              </main>
          </Router>
      );
  }

  attemptLogin = (email, password, type) => {

      UserLogin.login(email, password, type).then((res) =>{
          console.log(res.data);
          this.setState({
              currentUser: res.data
          });
      });
  }
}

export default App;
