import "./index.css";
import React, { useEffect } from "react";
import { useSelector } from "react-redux";
import { connect } from "react-redux";
import { getConversations, createdConversation } from "../../actions/conversations";
import { createdMessage } from "../../actions/messages";
import Conversation from "../../graphql/conversations/Conversation";
import ConversationDisplay from "../ConversationDisplay";


function ConversationList(props: any) {
    const conversations = useSelector((state: any) => state.conversations.conversations);
    const focusedConversation = useSelector((state: any) => state.conversations.focusedConversation);
    const currUserId = useSelector((state: any) => state.user.currUserId);

    useEffect(() => {
        console.log("in use effect where all the stuff is triggered");
        console.log(localStorage.getItem("token"));
        console.log(localStorage.getItem("id"));
        props.getConversations();
        props.createdConversation();
        props.createdMessage();
    }, [])
    
    const getDisplayUser = (currUserId: number, conversation: Conversation) => {
        let nonUserSenders = conversation.users.filter((user: any) => user.id != currUserId);
        let lastNonUserSenderId;

        for(const currMessage of conversation.messages) {
            if(currMessage.senderId != currUserId) {
                lastNonUserSenderId = currMessage.senderId;
            }
        }

        if(conversation.users.length === 2 || conversation.messages.length === 0 || !lastNonUserSenderId) {
            return nonUserSenders[0];
        }

        for(const user of nonUserSenders) {
            if(user.id == lastNonUserSenderId) return user;
        }
    }
    
    const conversationToComponent = (currUserId: number, conversation: Conversation) => {
        return <ConversationDisplay 
                key={conversation.id}
                conversation={conversation}
                focused={conversation.id === focusedConversation.id}
                displayUser={getDisplayUser(currUserId, conversation)}
               />
    }

    return (
        <div className="conversation-list">
            {conversations.length > 0 && conversations.map((conv: Conversation) => conversationToComponent(currUserId, conv))}
        </div>
    )
}

export default connect(null, { getConversations, createdConversation, createdMessage })(ConversationList);