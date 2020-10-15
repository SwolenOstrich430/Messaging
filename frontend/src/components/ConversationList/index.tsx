import "./index.css";
import React, { useEffect } from "react";
import { useSelector } from "react-redux";
import { connect } from "react-redux";
import { getConversations, createdConversation } from "../../actions/conversations";
import { createdMessage } from "../../actions/messages";
import Conversation from "../../graphql/conversations/Conversation";
import Message from "../../graphql/messages/Message";
import User from "../../graphql/users/User";
import ConversationDisplay from "../ConversationDisplay";


function ConversationList(props: any) {
    const conversations = useSelector((state: any) => state.conversations.conversations);
    const focusedConversation = useSelector((state: any) => state.conversations.focusedConversation);


    useEffect(() => {
        props.getConversations();
        props.createdConversation();
        props.createdMessage();
    }, [])
    
    const conversationToComponent = (conversation: Conversation) => {
        return <ConversationDisplay 
                key={conversation.id}
                conversation={conversation}
                focused={conversation.id === focusedConversation.id}
               />
    }

    return (
        <div className="conversation-list">
            {conversations.length > 0 && conversations.map(conversationToComponent)}
        </div>
    )
}

export default connect(null, { getConversations, createdConversation, createdMessage })(ConversationList);