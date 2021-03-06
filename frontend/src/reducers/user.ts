import { AUTHENTICATE_USER, LOGOUT_USER, CREATE_USER, SHOW_LOGIN, AUTH_ERROR} from "../actions/types";

let initialId: string | null = localStorage.getItem("id");

const initialState = {
    currUserId: initialId ? parseInt(initialId) : 0, 
    token: localStorage.getItem("token"), 
    authError: {}, 
    showLogin: true
}


export default function(state=initialState, action: any) {
    const { type, payload } = action;
    console.log(state);
    switch(type) {
        case AUTHENTICATE_USER:
            console.log("got in authenticate user");
            localStorage.setItem("token", payload.token);
            localStorage.setItem("id", payload.currUserId);
            console.log("setting local storage done");
            return {
                ...state,
                currUserId: payload.currUserId,
                token: payload.token, 
                authError: {}
            }

        case LOGOUT_USER:
            console.log("got in logout user"); 
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
            return {
                ...state, 
            }
    }
}