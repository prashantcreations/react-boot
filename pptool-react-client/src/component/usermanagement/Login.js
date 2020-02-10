import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {login} from '../../actions/SecurityAction';
import classnames from "classnames";



class Login extends Component {
    constructor(){
        super();
        this.state={
           userName: "",
           password:"",
           errors:{}
        }
    }

    onChange=(e)=>{
        this.setState({[e.target.name]:e.target.value});
    }

    onSubmit=(e)=>{
        e.preventDefault();
        const loginRequest={
           userName: this.state.userName,
           password:this.state.password
        }

        this.props.login(loginRequest);
    }

    componentWillReceiveProps(nextProps){
        if(nextProps){
            this.setState({errors:nextProps.errors})
        }
        if(nextProps.security.validToken){
            this.props.history.push("/dashboard");
        }
    }

    componentDidMount(){
        if(this.props.security.validToken){
            this.props.history.push("/dashboard");
        }
    }


    render() {
        const{errors}= this.state;
        return (
    <div className="login">
            <div className="container">
                <div className="row">
                    <div className="col-md-8 m-auto">
                        <h1 className="display-4 text-center">Log In</h1>
                        <form onSubmit={this.onSubmit}>
                            <div className="form-group">
                                <input type="email" className={classnames("form-control form-control-lg",{"is-invalid":errors.userName})} placeholder="Email Address" name="userName" onChange={this.onChange.bind(this)} />
                            {errors.userName && (
                                    <div className="invalid-feedback">{errors.userName}</div>
                            )}                            
                            </div>
                            <div className="form-group">
                                <input type="password" className={classnames("form-control form-control-lg",{"is-invalid":errors.password})} placeholder="Password" name="password" onChange={this.onChange.bind(this)} />
                                {errors.password && (
                                        <div className="invalid-feedback">{errors.password}</div>
                                )}                            
                            </div>
                            <input type="submit" className="btn btn-info btn-block mt-4" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
        )
    }
}
Login.propTypes={
    login:PropTypes.func.isRequired,
    errors:PropTypes.object.isRequired,
    security:PropTypes.object.isRequired
}


const mapStateToProps=state=>({
    errors:state.errors,
    security:state.security
})

export default connect(mapStateToProps,{login}) (Login);