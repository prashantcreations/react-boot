import React, { Component } from 'react'
import ProjectTask from "./ProjectTasks/ProjectTask"

class BackLog extends Component {
    render() {
        const{project_tasks_prop} = this.props;  //whic we are passing from las page as an argument
        const tasks = project_tasks_prop.map(projecttask=>(
            <ProjectTask key={projecttask.id} project_task={projecttask} />
        ))

        let todo = [];
        let inProgress = [];
        let done = [];

        for(let i=0 ;i< tasks.length ; i++){
            console.log(tasks[i].props.project_task.status);
            if(tasks[i].props.project_task.status === "TO_DO"){
                todo.push(tasks[i]);
            }
            if(tasks[i].props.project_task.status === "IN_PROGRESS"){
                inProgress.push(tasks[i]);
            }
            if(tasks[i].props.project_task.status === "DONE"){
                done.push(tasks[i]);
            }
        }

        return (
       <div className="container">
            <div className="row">
                <div className="col-md-4">
                    <div className="card text-center mb-2">
                        <div className="card-header bg-secondary text-white">
                            <h3>TO DO</h3>
                        </div>
                    </div>
                    {todo}
                </div>
                <div className="col-md-4">
                    <div className="card text-center mb-2">
                        <div className="card-header bg-primary text-white">
                            <h3>In Progress</h3>
                        </div>
                    </div>
                    {inProgress}
            </div>
                <div className="col-md-4">
                    <div className="card text-center mb-2">
                        <div className="card-header bg-success text-white">
                            <h3>Done</h3>
                        </div>
                    </div>
                    {done}
                </div>
            </div>
        </div>            
        )
    }
}
export default BackLog;