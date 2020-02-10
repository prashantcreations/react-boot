import axios from "axios";
import {GET_ERROR , GET_PROJECTS,GET_PROJECT,DELETE_PROJECT} from "./type" ;

export const createProject = (project,history) => async dispatch =>{

    try{
        // will take the prefix URL from package.jason i.e proxy
        await axios.post("/ppmtool/projects",project);
        history.push("/dashboard");
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
};

export const getProjects = () => async dispatch =>{
    try{
        const res = await axios.get("/ppmtool/projects/");
        dispatch({
              type:GET_PROJECTS,
              payload:res.data 
        })

    }catch(err){

    }

}


export const getProject = (id,history) => async dispatch =>{
try{
    const res = await axios.get(`/ppmtool/projects/${id}`);
    dispatch({
        type:GET_PROJECT,
        payload:res.data

    })

}catch(err){
    history.push("/dashboard");//passed id not present return back to dashboard 

}}

export const deleteProject = id => async dispatch=>{
    if(window.confirm("Really you want to delete this project?")){
        await axios.delete(`/ppmtool/projects/${id}`);
        dispatch ({
            type:DELETE_PROJECT,
            payload:id
        })
    }else{
}}




