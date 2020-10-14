import { CREATE_USER, AUTHENTICATE_USER, AUTH_ERROR, LOGOUT_USER } from "./types";
import { AUTHENTICATE_USER_QUE, CREATE_USER_MUT } from "../graphql/users/users";
import AuthenticationRequest from "../graphql/users/AuthenticationRequest";
import client from "../graphql/index";
import UserDTO from "../graphql/users/UserDTO";
import { CREATE_MESSAGE_MUT } from "../graphql/messages/messages";


export const authenticateUser = (authenticationRequest: AuthenticationRequest) => (dispatch: Function) => {
    console.log(authenticationRequest);
    client.query({
        query: AUTHENTICATE_USER_QUE, 
        variables: {
            authenticationRequest: authenticationRequest
        }
    })
    .then(res => {
        console.log(res);
        const { token, id } = res.data.authenticateUser;
        localStorage.setItem("token", token);
        localStorage.setItem("id", id);
        
        dispatch({
            type: AUTHENTICATE_USER, 
            payload: {
                currUserId: id
            }
        })
    })
    .catch(error => {
        console.log(error);
    })
}

export const createUser = (newUser: UserDTO) => (dispatch: Function) => {
    client.mutate({
        mutation: CREATE_MESSAGE_MUT, 
        variables: {
            newUser: newUser
        }
    })
    .then(res => {
        console.log(res);
    })
    .catch(error => {
        console.log(error);
    })
}

export const logoutUser = () => (dispatch: Function) => {
    dispatch({
        type: LOGOUT_USER
    })
}