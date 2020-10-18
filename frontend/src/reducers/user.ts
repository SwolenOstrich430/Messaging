import { AUTHENTICATE_USER, LOGOUT_USER, CREATE_USER, SHOW_LOGIN, AUTH_ERROR} from "../actions/types";


const initialState = {
    currUserId: localStorage.getItem("id"), 
    token: localStorage.getItem("token"), 
    authError: {}, 
    showLogin: true
}


export default function(state=initialState, action: any) {
    const { type, payload } = action;
    console.log("got in user reducer");
    console.log(initialState);
    switch(type) {
        case AUTHENTICATE_USER:
            localStorage.setItem("token", payload.token);
            localStorage.setItem("id", payload.currUserId);
            
            return {
                ...state,
                currUserId: payload.currUserId,
                token: payload.token
            }

        case LOGOUT_USER: 
            localStorage.setItem("token", "");
            localStorage.setItem("id", "");
            return {
                ...state, 
                currUserId: 0, 
                token: ""
            }
        
        case CREATE_USER: 
            return {
                ...state, 
                authError: {}, 
                showLogin: true
            }
        
        case AUTH_ERROR: 
            console.log("got in the auth error reducer");
            return {
                ...state, 
                authError: payload.error
            }
        
        case SHOW_LOGIN: 
            return {
                ...state, 
                showLogin: payload.showLogin, 
                authError: {}
            }
        
        default:
            console.log("in default"); 
            console.log(payload);
            return {
                ...state, 
            }
    }
}