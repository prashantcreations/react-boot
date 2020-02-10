import axios from "axios";
import {GET_ERROR,GET_BACKLOG,GET_PROJECT_TASK,DELETE_PROJECT_TASK} from "./type" ;


export const addProjectTask = (backlog_id,project_task,history)=> async dispatch =>{
    try{
        await axios.post(`/api/backlog/${backlog_id}`,project_task);
        history.push(`/projectBoard/${backlog_id}`);
        dispatch({
            type:GET_ERROR,
            payload:{}
        })
    }catch(err){
        dispatch({
            type:GET_ERROR,
            payload:err.response.data
        })
    }}

export const getBacklog = (backlog_id) => async dispatch=>{
    try{
        const res = await axios.get(`/api/backlog/${backlog_id}`);
        dispatch({
            type:GET_BACKLOG,
            payload:res.data
        })

    }catch(err){
        dispatch({
            type:GET_ERROR,
            payload:err.response.data
        })
    }
}
    export const getProjectTask = (backlog_id,pt_id) => async dispatch =>{
        try{
            const res = await axios.get(`/api/backlog/${backlog_id}/${pt_id}`);
            dispatch({
                type:GET_PROJECT_TASK,
                payload:res.data
            })
        }catch(err){

        }
    }

export const updateProjectTask = (backlog_id,pt_id,project_task,history)=>async dispatch =>{
    try{
        const res = await axios.patch(`/api/backlog/${backlog_id}/${pt_id}`,project_task);
        history.push(`/projectBoard/${backlog_id}`);
        dispatch({
            type:GET_ERROR,
            payload:{}
        })
    }catch(err){
        dispatch({
            type:GET_ERROR,
            payload:err.response.data
         })
    }
}

export const deleteProjectTask = (backlog_id,pt_id,project_task,history)=>async dispatch =>{
    console.log(backlog_id);
    if(window.confirm("do you really want to delete this task ?")){
        await axios.delete(`/api/backlog/${backlog_id}/${pt_id}`);
        dispatch({
            type:DELETE_PROJECT_TASK,
            payload:pt_id
        })
    }
}
    



