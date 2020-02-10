import React, { Component } from 'react';
import {Link} from "react-router-dom";
import PropTypes from 'prop-types';
import {connect} from "react-redux";
import {logout} from "../../actions/SecurityAction"


class Header extends Component {

    logout(){
        this.props.logout();
        window.location.href="/";        
    }
    render() {
        const{user,validToken} = this.props.security;

       const isUserNotAuthenticated=(
            <div className="collapse navbar-collapse" id="mobile-nav">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/register">
                            Dashboard
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/login"   className="nav-link">                     
                            Login
                        </Link>
                    </li>
                </ul>
            </div>
        );





        const isUserAuthenticated=(
            <div className="collapse navbar-collapse" id="mobile-nav">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/dashboard">
                            Dashboard
                        </Link>
                    </li>
                </ul>

                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link to="/dashboard"   className="nav-link">                     
                          <i className="fas.fa-user-ci rcle mr-l">{user.fullName}</i>  
                        </Link>                            
                    </li>
                    <li className="nav-item">
                        <Link to="/login"   className="nav-link" onClick={this.logout.bind(this)}>                     
                        Logout
                        </Link>
                    </li>
                </ul>
            </div>
        );


        let headerLink ;
        if(user && validToken){
            headerLink = isUserAuthenticated;
        }else{
            headerLink = isUserNotAuthenticated;
        }
        


        return (
   <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
        <div className="container">
            <Link to="/" className="navbar-brand">
                Personal Project Management Tool
            </Link>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
                <span className="navbar-toggler-icon" />
            </button>
                {headerLink}
        </div>
    </nav>            
        )
    }
}

Header.propTypes={
    security:PropTypes.object.isRequired,
    logout:PropTypes.func.isRequired
}

const mapSatetoProps= state =>({
    security:state.security
})

export default connect(mapSatetoProps,{logout}) (Header)