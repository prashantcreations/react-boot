import React, { Component } from 'react'
import PropTypes from "prop-types" 
import {connect} from "react-redux"
import {createProject} from "../../actions/projectActions"
import classnames from "classnames"


class AddProject extends Component {

    constructor(){
        super();
            this.state={
                prjectName:"",
                projectIdentification:"",
                desc:"",
                start_date:"",
                end_date:"",
                errors:{}
            };    
            this.onChange = this.onChange.bind(this);    
            this.onSubmit = this.onSubmit.bind(this);
    }

    onChange(e){
        // first way to set change value in the field
//        this.setState({prjectName:e.target.value});
//        this.setState({projectIdentification:e.target.value});
//        this.setState({desc:e.target.value});
//        this.setState({start_date:e.target.value});
//        this.setState({end_date:e.target.value});

        // second was to set chnage value in the field
        // this binding the each name field wiht this and getting change once we change the value 
        this.setState({ [e.target.name]: e.target.value})
    }

    onSubmit(e){
        e.preventDefault();
        const newProject={
                prjectName: this.state.prjectName,
                projectIdentification: this.state.projectIdentification,
                desc:this.state.desc,
                start_date:this.state.start_date,
                end_date:this.state.end_date
        }
        this.props.createProject(newProject,this.props.history)
    }

    //life cycle hooks
    componentWillReceiveProps(nextProps){
        if(nextProps.errors){
            this.setState({errors:nextProps.errors});
        }

    }

    render() {
        const{errors}= this.state

        return (
<div>
  <div className="project">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <h5 className="display-4 text-center">Create Project form</h5>
                    <hr />
                    <form onSubmit={this.onSubmit}>
                        <div className="form-group">
                            <input type="text" className={classnames("form-control form-control-lg",{"is-invalid":errors.prjectName})} placeholder="Project Name" name="prjectName" value={this.state.prjectName} onChange={this.onChange.bind(this)}/>
                            {errors.prjectName && (
                                    <div className="invalid-feedback">{errors.prjectName}</div>
                            )}                            

                        </div>
                        <div className="form-group">
                            <input type="text" className={classnames("form-control form-control-lg",{"is-invalid":errors.projectIdentification})} placeholder="Unique Project ID" name="projectIdentification" value={this.state.projectIdentification} onChange={this.onChange.bind(this)}                                />
                            {errors.projectIdentification && (
                                      <div className="invalid-feedback">{errors.projectIdentification}</div>  

                            )}                            
                        </div>
                        <div className="form-group">
                            <textarea className={classnames("form-control form-control-lg",{"is-invalid":errors.desc})} placeholder="Project Description" name="desc" value={this.state.desc} onChange={this.onChange.bind(this)}></textarea>
                            {errors.desc && (
                                <div className="invalid-feedback">{errors.desc}</div>
                            )}                            
                        </div>
                        <h6>Start Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" name="start_date" name="start_date" value={this.state.start_date} onChange={this.onChange.bind(this)}/>
                        </div>
                        <h6>Estimated End Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" name="end_date" name="end_date" value={this.state.end_date} onChange={this.onChange.bind(this)}/>
                        </div>
                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>            
    </div>
        )
    }
}


AddProject.propTypes = {
createProject : PropTypes.func.isRequired,
errors : PropTypes.object.isRequired
}

const mapStatetoProps = state=>({
    errors: state.errors
});

export default connect(mapStatetoProps,{createProject}) (AddProject);
