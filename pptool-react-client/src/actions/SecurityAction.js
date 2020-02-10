import axios from "axios";
import {GET_ERROR,SET_CURRENT_USER} from "./type";
import setJWTToken from "../securityUtilities/setJWTToken";
import jwt_decode from "jwt-decode"

export const createUser = (newUser,history) => async dispatch =>{

    try{
        // will take the prefix URL from package.jason i.e proxy
        await axios.post("/api/user/register",newUser);
        history.push("/");
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

export const login = loginRequest => async dispatch=>{
    try{
    //login request
    const res = await axios.post("/api/user/login",loginRequest);
    //extract the token from res
    const{token} = res.data;
    //store the token in local storage 
    localStorage.setItem("JWTToken",token);
    //set the token in header 
    setJWTToken(token);
    // decode the token
    const decode = jwt_decode(token);
    //dispatch the token to our security reducers
    dispatch({
        type:SET_CURRENT_USER,
        payload:decode
    })
    }catch(err){
        dispatch({
            type:GET_ERROR,
            payload:err.response.data
        })
    }
}

export const logout = () => dispatch=>{
    localStorage.removeItem("JWTToken");
    setJWTToken(false);

    dispatch({
        type:SET_CURRENT_USER,
        payload:{}
    })
}