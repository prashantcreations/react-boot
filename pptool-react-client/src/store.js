
import {createStore, applyMiddleware , compose} from "redux"
import thunk from "redux-thunk" // saga , flux
import rootReducer from "./reducers"

const initalState = {};
const middleware = [thunk];
let store;


// this is we are doing because if any client have no react and redux dev extention

const reactReduxDevTool = window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()


if(window.navigator.userAgent.includes("Chrome")&&reactReduxDevTool){
store = createStore(rootReducer,
initalState,
compose(
    applyMiddleware(...middleware),
    reactReduxDevTool
    ));
}else{
store = createStore(rootReducer,
initalState,
compose(applyMiddleware(...middleware)));    
}
export default store;