import { 
    CREATING_CONVERSATION, 
    ADD_RECIPIENT, 
    ADD_RECIPIENT_ERROR, 
    GET_CONVERSATIONS, 
    CREATED_CONVERSATION, 
    FOCUS_ON_CONVERSATION, 
    CANCEL_CREATING_CONVERSATION
} from "./types";
import { 
    FIND_USER_BY_USERNAME, 
    CREATE_CONVERSATION_MUT, 
    GET_CONVERSATIONS_QUE, 
    CREATED_CONVERSATION_SUB 
} from "../graphql/conversations/converstions";
import client from "../graphql/index";
import User from "../graphql/users/User";
import Conversation from "../graphql/conversations/Conversation";
import Message from "../graphql/messages/Message";


export const getConversations = () => (dispatch: Function) => {
    client.query({
        query: GET_CONVERSATIONS_QUE
    })
    .then(res => {
        dispatch({
            type: GET_CONVERSATIONS, 
            payload: {
                conversations: res.data.getConversations
            }
        })
    })
    .catch(error => {
        // TODO: ACTUAL ERROR HANDLING HERE
        console.log(error);
    });
}

export const createdConversation = () => (dispatch: Function) => {
    client.subscribe({
        query: CREATED_CONVERSATION_SUB
    })
    .subscribe({
        next(payload) {
            const { id, users, messages } = payload.data.createdConversation;
            let newConversation = new Conversation(id, "", new Array<Message>(), users);

            dispatch({
                type: CREATED_CONVERSATION, 
                payload: {
                    createdConversation: newConversation
                }
            })
        }, 
        error(err) { 
            // TODO: ACTUALY ERROR HANDLING HERE
            console.log(err)
        }
    })
}

export const creatingConversation = (creatingConversation: boolean) => (dispatch: Function) => {
    return dispatch({
        type: CREATING_CONVERSATION, 
        payload: {
            creatingConversation
        }
    })
}

export const cancelCreatingConversation = () => (dispatch: Function) => {
    return dispatch({
        type: CANCEL_CREATING_CONVERSATION
    })
}

export const addRecipient = (username: string) => (dispatch: Function) => {
    client.query({
        query: FIND_USER_BY_USERNAME,
        variables: {
            username: username
        }
    })
    .then(res => {
        const { id, username, email, firstName, lastName } = res.data.findUserByUsername;
        const newRecipient: User = new User(id, username, email, firstName, lastName);
        
        dispatch({
            type: ADD_RECIPIENT, 
            payload: {
                newRecipient: newRecipient
            }
        })
    })
    .catch(error => {
        error.incorrectUsername = username;
        error.message = "Could not find " + username;
        dispatch({
            type: ADD_RECIPIENT_ERROR, 
            payload: {
                error: error, 
            }
        })
    })
}

export const createConversation = (recipientIds: Array<number>) => (dispatch: Function) => {
    client.mutate({
        mutation: CREATE_CONVERSATION_MUT,
        variables: {
            recipientIds: recipientIds
        }
    })
    .catch(error => {
        dispatch({
            type: ADD_RECIPIENT_ERROR, 
            payload: {
                error: error, 
            }
        })
    })
}

export const focusOnConversation = (conversation: Conversation) => (dispatch: Function) => {
    dispatch({
        type: FOCUS_ON_CONVERSATION, 
        payload: {
            focusedConversation: conversation
        }
    })
}
