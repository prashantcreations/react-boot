import React, { Component } from 'react';
import {Link} from "react-router-dom";
import BackLog from "./BackLog";
import {connect} from "react-redux";
import PropTypes from "prop-types";
import {getBacklog} from "./../../actions/BackLogActions"


class ProjectBoard extends Component {

    constructor(){
        super();
        this.state={
            errors:{}
        };
    }

    componentDidMount(){
        const{id} = this.props.match.params;
        this.props.getBacklog(id);//call action method
    }

    componentWillReceiveProps(nextProps){
        if(nextProps.errors){
            this.setState({errors:nextProps.errors});
        }
    }

    render() {
        //this id we are take from dashboard
        const {id} = this.props.match.params;
        const {project_tasks} = this.props.backlog // this getting from state for iterate
        return (
            <div className="container">
                    <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
                        <i className="fas fa-plus-circle"> Create Project Task</i>fdbdf
                    </Link>
                    <br />
                    <hr />
                    <BackLog project_tasks_prop={project_tasks}/>
                </div>
               )
        }
}

ProjectBoard.propTypes={
    backlog: PropTypes.object.isRequired,
    getBacklog: PropTypes.func.isRequired,
    errors : PropTypes.object.isRequired
}


const mapStatetoProps = state =>({
    backlog:state.backlog,
    errors:state.errors
})


export default connect(mapStatetoProps,{getBacklog}) (ProjectBoard);
