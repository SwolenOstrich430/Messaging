import { AUTHENTICATE_USER } from "../actions/types";


const initialState = {
    currUserId: localStorage.getItem("id"), 
    token: localStorage.getItem("token"), 
}


export default function(state=initialState, action: any) {
    const { type, payload } = action;
    console.log("got in user reducer");
    console.log(initialState);
    switch(type) {
        case AUTHENTICATE_USER: 
            return {
                ...state,
                currUserId: payload.currUserId,
            }
        
        default:
            console.log("in default"); 
            console.log(payload);
            return {
                ...state, 
            }
    }
}