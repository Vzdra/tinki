import React, {Component} from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import Login from '../login/login';
import 'semantic-ui-css/semantic.min.css';
import HeaderComp from '../template/header';
import './App.css';
import UserLogin from "../../repository/login_repo";

class App extends Component{
  constructor(props) {
    super(props);
    this.state = {
      id: null,
      email: null,
      name: null,
      type: null
    }
  }

  render(){
      return(
          <Router>
              <HeaderComp acc={this.state.email} accType={this.state.type} name={this.state.name}/>
              <main>
                  <Route path={"/login"} render={() => <Login onCompleteForm={this.attemptLogin}/>}/>
                  <Route path={"/"}/>
                  <Route path={"/jobs"}/>
                  <Route path={"/internships"}/>
              </main>
          </Router>
      );
  }

  attemptLogin = (username, password, type) => {
      UserLogin.login(username, password, type).then((res) =>{
          console.log(res.data);
          this.setState({
            id: res.data.id,
            email: res.data.email,
            name: res.data.name,
            type: res.data.type
          });
      });
  }
}

export default App;
