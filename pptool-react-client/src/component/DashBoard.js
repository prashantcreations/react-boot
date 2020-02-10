import React, { Component } from 'react';
import ProjectItem from "./Projects/ProjectItem";
import CreateProjectButton from "./Projects/CreateProjectButton";
import {connect} from "react-redux";
import {getProjects} from "../actions/projectActions"
import PropTypes from "prop-types";


class DashBoard extends Component {
    // create project object 

    componentDidMount(){
        this.props.getProjects();
    }

    render() {
    const {projects} = this.props.projects

        return (
   <div className="projects">
        <div className="container">
            <div className="row">
                <div className="col-md-12">
                    <h1 className="display-4 text-center">Projects</h1>
                    <br />
                    <CreateProjectButton/>
                    <br />
                    <hr />
                   {
                        projects.map(project=>(
                            <ProjectItem key={project.id}  project={project}/>
                        ))
                    }
                </div>
            </div>
        </div>
    </div>            
        )
    }
} 

DashBoard.propTypes={
    projects:PropTypes.object.isRequired,
    getProjects:PropTypes.func.isRequired
}

const mapStatetoProps =state=>({

    projects:state.projects,// state.project coming from root reduces

})
export default connect(mapStatetoProps,{getProjects}) (DashBoard);
