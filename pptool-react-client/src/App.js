import React from 'react';
import './App.css';
import DashBoard from "./component/DashBoard";
import "bootstrap/dist/css/bootstrap.min.css"; 
import Header from "./component/layout/Header";
import {BrowserRouter as Router,Route,Switch} from "react-router-dom"// hashrouter is static router
import AddProject from "./component/Projects/AddProject";
import UpdateProject from "./component/Projects/UpdateProject"
import ProjectBoard from "./component/ProjectBoard/ProjectBoard"
import AddProjectTask from "./component/ProjectBoard/ProjectTasks/AddProjectTask"
import UpdateProjectTask from "./component/ProjectBoard/ProjectTasks/UpdateProjectTask"
import Landing from "./component/layout/Landing"
import Login from "./component/usermanagement/Login";
import Registor from "./component/usermanagement/Registor"

import {Provider} from "react-redux";
import store from "./store";
import {SET_CURRENT_USER} from "./actions/type";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtilities/setJWTToken";
import {logout} from "./actions/SecurityAction";
import SecuredRoute  from "./securityUtilities/SecuredRoute"

//for every refresh we have to reset the token from local storage 
const jwtToken =localStorage.JWTToken;
if(jwtToken){
  setJWTToken(jwtToken);
  const decode =jwt_decode(jwtToken);
  store.dispatch({
    type:SET_CURRENT_USER,
    payload:decode
  });
  const currentTime = Date.now() / 1000;
  if(decode.exp < currentTime){
    store.dispatch(logout());
    window.location.href="/";
  }
}
// end code for security token set for every refresh

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header/>
          {
            //public routing
          }
            <Route exact path="/" component={Landing}/>
            <Route exact path="/login" component={Login}/>        
            <Route exact path="/registor" component={Registor}/>
          
          <Switch>
              {
                //Private Routing
              }
              <SecuredRoute exact path="/dashboard" component={DashBoard}/>
              <SecuredRoute exact path="/addProject" component={AddProject}/>
              <SecuredRoute exact path="/updateProject/:id" component={UpdateProject}/>
              <SecuredRoute exact path="/projectBoard/:id" component={ProjectBoard}/>
              <SecuredRoute exact path="/addProjectTask/:id" component={AddProjectTask}/>
              <SecuredRoute exact path="/updateProjectTask/:backlog_id/:pt_id" component={UpdateProjectTask}/>
          </Switch>            
        </div>
      </Router>    
    </Provider>

  );
}

export default App;
