import { 
    CREATING_CONVERSATION, 
    ADD_RECIPIENT, 
    ADD_RECIPIENT_ERROR,
    GET_CONVERSATIONS, 
    CREATED_CONVERSATION, 
    CREATED_MESSAGE, 
    FOCUS_ON_CONVERSATION,
    CANCEL_CREATING_CONVERSATION,
    CLEAR_CONVERSATIONS
} from "../actions/types";
import Conversation from "../graphql/conversations/Conversation";
import Message from "../graphql/messages/Message";


const NEW_MESSAGE_TEXT = "New Message";

const getConverationsAfterNewMessage = (newMessage: Message, conversations: Array<Conversation>) => {
    let copyOfConversations = conversations.filter(conv => conv.id !== newMessage.conversationId);
    let conversationWithNewMessage;

    for(let conversation of conversations) {
        if(conversation.id === newMessage.conversationId) {
            let { id, title, users, messages } = conversation;
            conversationWithNewMessage = new Conversation(id, title, [...messages, newMessage], users);
        }
    }
  
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

        case CANCEL_CREATING_CONVERSATION: 
            if(!state.creatingConversation) return;
            let newConversations = state.conversations.filter(conv => conv.id != 0);
            return {
                ...state, 
                conversations: newConversations, 
                focusedConversation: newConversations[0], 
                creatingConversation: false
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
        
        case CLEAR_CONVERSATIONS: 
            return {
                ...initialState
            }

        default: {
            return state
        }
    }
}