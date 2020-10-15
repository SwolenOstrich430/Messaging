import { CREATE_MESSAGE, CREATED_MESSAGE } from "./types";
import { CREATE_MESSAGE_MUT, CREATED_MESSAGE_SUB } from "../graphql/messages/messages";
import client from "../graphql/index";
import { CREATE_CONVERSATION_MUT } from "../graphql/conversations/converstions";
import Message from "../graphql/messages/Message";


export const createMessage = (text: string, conversationId: number) => (dispatch: Function) => {
    client.mutate({
        mutation: CREATE_MESSAGE_MUT, 
        variables: {
            text: text, 
            conversationId: conversationId
        }
    })
    .then(res => {
        console.log(res);
    })
    .catch(error => {
        console.log(error);
    })
   
}

export const createdMessage = () => (dispatch: Function) => {
    console.log("got inc reated message");
    client.subscribe({
        query: CREATED_MESSAGE_SUB
    })
    .subscribe({
        next(payload) {
            const { id, text, timeSent, conversationId, senderId } = payload.data.sentMessage;
            let newMessage = new Message(id, text, conversationId, senderId, timeSent);
            console.log("got in created message sub")
            console.log(newMessage);
            dispatch({
                type: CREATED_MESSAGE, 
                payload: {
                    createdMessage: newMessage
                }
            })
        }, 
        error(err) { console.log(err)}
    })
}