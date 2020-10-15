import { 
    CREATING_CONVERSATION, 
    ADD_RECIPIENT, 
    ADD_RECIPIENT_ERROR,
    GET_CONVERSATIONS, 
    CREATED_CONVERSATION, 
    CREATED_MESSAGE, 
    FOCUS_ON_CONVERSATION
} from "../actions/types";
import Conversation from "../graphql/conversations/Conversation";
import Message from "../graphql/messages/Message";

const NEW_MESSAGE_TEXT = "New Message";

const getConverationsAfterNewMessage = (newMessage: Message, conversations: Array<Conversation>) => {
    let copyOfConversations = conversations.filter(conv => conv.id !== newMessage.conversationId);
    let conversationWithNewMessage;

    for(let conversation of conversations) {
        if(conversation.id === newMessage.conversationId) {
            console.log("got in if statement");
            let { id, title, users, messages } = conversation;
            conversationWithNewMessage = new Conversation(id, title, [newMessage, ...messages], users);
            console.log(conversationWithNewMessage);
        }
    }
    console.log(copyOfConversations);
    return copyOfConversations.length > 0 ? 
            [conversationWithNewMessage, ...copyOfConversations] : 
            [conversationWithNewMessage];
}

const initialState = {
    creatingConversation: false, 
    focusedConversation: {} as Conversation, 
    conversations: Array<Conversation>(), 
    addRecipientError: {}
}


export default function(state=initialState, action: any) {
    const { type, payload } = action;

    switch(type) {
        case GET_CONVERSATIONS:
            console.log(payload.conversations);
            console.log(payload.conversations[0]); 
            return {
                ...state, 
                conversations: payload.conversations, 
                focusedConversation: payload.conversations[0]
            }
        
        case CREATED_CONVERSATION:
            const currConversations = state.creatingConversation ? state.conversations.slice(1) : state.conversations; 
            return {
                ...state, 
                conversations: [payload.createdConversation, ...currConversations],
                focusedConversation: payload.createdConversation, 
                creatingConversation: false, 
                addRecipientError: {}
            }

        case CREATED_MESSAGE:
            console.log("got in created message");
            let conversationsAfterNewMessage = getConverationsAfterNewMessage(payload.createdMessage, state.conversations);
            
            return {
                ...state, 
                conversations: conversationsAfterNewMessage, 
            }

        case CREATING_CONVERSATION: 
            if(state.creatingConversation) return state;

            let newConversation: Conversation = Conversation.fromTitle(NEW_MESSAGE_TEXT);
            
            return {
                ...state, 
                creatingConversation: payload.creatingConversation, 
                focusedConversation: newConversation, 
                conversations: [newConversation, ...state.conversations]
            }
        
        case ADD_RECIPIENT: 
            if(!state.creatingConversation) return state;
            let conversation: Conversation = Object.assign({}, state.conversations[0]);
            conversation.users.unshift(payload.newRecipient);

            let conversations: Array<Conversation> = [...state.conversations];
            conversations[0] = conversation;

            return {
                ...state, 
                conversations: conversations,
                addRecipientError: {}
            }

        case ADD_RECIPIENT_ERROR: 
            if(!state.creatingConversation) return state;

            return {
                ...state, 
                addRecipientError: payload.error
            }

        case FOCUS_ON_CONVERSATION: 
            return {
                ...state, 
                focusedConversation: payload.focusedConversation
            }

        default: {
            return state
        }
    }
}