import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {createUser} from "../../actions/SecurityAction";
import classnames from "classnames"



class Registor extends Component {

    constructor(){
        super();
        this.state={
                fullName:"",
                userName:"",
                password:"",
               confirmPassword:"",
               errors:{}
        };
    }

    onChange=(e)=>{
         this.setState({ [e.target.name]: e.target.value})
    }

    onSubmit=(e)=>{
         e.preventDefault();
        const newUser={
                fullName:this.state.fullName,
                userName:this.state.userName,
                password:this.state.password,
               confirmPassword:this.state.confirmPassword
        }
        this.props.createUser(newUser,this.props.history);

    }

    componentWillReceiveProps(nextProps){
        if(nextProps.errors){
            this.setState({errors:nextProps.errors})
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
   <div className="register">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <h1 className="display-4 text-center">Sign Up</h1>
                    <p className="lead text-center">Create your Account</p>
                    <form onSubmit={this.onSubmit}>
                        <div className="form-group">
                            <input type="text" className={classnames("form-control form-control-lg",{"is-invalid":errors.fullName})} placeholder="fullName" name="fullName" value={this.state.fullName} onChange={this.onChange.bind(this)}
                                required />
                            {errors.fullName && (
                                    <div className="invalid-feedback">{errors.fullName}</div>
                            )}                                     
                        </div> 
                        <div className="form-group">
                            <input type="email" className={classnames("form-control form-control-lg",{"is-invalid":errors.userName})} placeholder="userName" value={this.state.userName}  name="userName" onChange={this.onChange.bind(this)} />
                            {errors.userName && (
                                    <div className="invalid-feedback">{errors.userName}</div>
                            )}                            
                        </div>
                        <div className="form-group">
                            <input type="password" className={classnames("form-control form-control-lg",{"is-invalid":errors.password})} placeholder="password" value={this.state.password}  name="password" onChange={this.onChange.bind(this)} />
                            {errors.password && (
                                    <div className="invalid-feedback">{errors.password}</div>
                            )}                            
                        </div>
                        <div className="form-group">
                            <input type="password" className={classnames("form-control form-control-lg",{"is-invalid":errors.confirmPassword})} placeholder="Confirm Password" value={this.state.confirmPassword} name="confirmPassword"  onChange={this.onChange.bind(this)} />
                            {errors.confirmPassword && (
                                    <div className="invalid-feedback">{errors.confirmPassword}</div>
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

Registor.propTypes={
   createUser: PropTypes.func.isRequired,
   errors: PropTypes.object.isRequired,
   security:PropTypes.object.isRequired
}


const mapStatetoProp=state=>({
    errors:state.errors,
    security:state.security
})

export default connect(mapStatetoProp,{createUser}) (Registor);