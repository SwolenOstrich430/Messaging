import { CREATE_USER, AUTHENTICATE_USER, AUTH_ERROR, LOGOUT_USER, SHOW_LOGIN } from "./types";
import { AUTHENTICATE_USER_QUE, CREATE_USER_MUT } from "../graphql/users/users";
import AuthenticationRequest from "../graphql/users/AuthenticationRequest";
import client from "../graphql/index";
import UserDTO from "../graphql/users/UserDTO";


export const authenticateUser = (authenticationRequest: AuthenticationRequest) => (dispatch: Function) => {
    client.query({
        query: AUTHENTICATE_USER_QUE, 
        variables: {
            authenticationRequest: authenticationRequest
        }
    })
    .then(res => {
        const { token, id } = res.data.authenticateUser;
        
        dispatch({
            type: AUTHENTICATE_USER, 
            payload: {
                currUserId: id, 
                token: token
            }
        })
    })
    .catch(error => {
        dispatch({
            type: AUTH_ERROR, 
            payload: {
                error: error
            }
        })
    })
}

export const createUser = (newUser: UserDTO) => (dispatch: Function) => {
    client.mutate({
        mutation: CREATE_USER_MUT, 
        variables: {
            newUser: newUser
        }
    })
    .then(res => {
        dispatch({
            type: CREATE_USER
        })
    })
    .catch(error => {
        console.log(error);
        dispatch({
            type: AUTH_ERROR, 
            payload: {
                error: error
            }
        })
    })
}

export const logoutUser = () => (dispatch: Function) => {
    console.log("got in logout link");
    dispatch({
        type: LOGOUT_USER
    })
}

export const showLogin = (showLogin: boolean) => (dispatch: Function) => {
    dispatch({
        type: SHOW_LOGIN, 
        payload: {
            showLogin: showLogin
        }
    })
}