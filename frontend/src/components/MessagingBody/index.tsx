import React, { useState } from "react";
import "./index.css";
import Message from "../../graphql/messages/Message";
import User from "../../graphql/users/User";
import MessageDisplay from "../MessageDispaly";
import { useSelector } from "react-redux";
import Conversation from "../../graphql/conversations/Conversation";


export default function MessagingBody(props: any) {
    const conversations: Array<Conversation> = useSelector((state: any) => state.conversations.conversations);
    const focusedConversationId: number = useSelector((state: any) => state.conversations.focusedConversation.id);
    const currUserId: number = useSelector((state: any) => state.user.currUserId);
    const [isScrolling, setIsScrolling] = useState(false);

    const getFocusedMessageConversations = (conversations: Array<Conversation>, conversationId: number) => {
        for(const conv of conversations) {
            if(conv.id === conversationId) return conv.messages;
        }
    }

    const handleScroll = (event: any) => {
        setIsScrolling(true);
    }

    const getStyle = () => {
        let scrollStyle = isScrolling ? "scroll" as "scroll" : "auto" as "auto";
        
        return {
            "overflowY": scrollStyle
        }
    }

    let messages = getFocusedMessageConversations(conversations, focusedConversationId);
    console.log("curr user id: " + currUserId);
    return (
        <main style={getStyle()} className="messaging-body" onScroll={handleScroll}>
            {messages && messages.map(message => <MessageDisplay key={message.id} sent={message.senderId === currUserId} {...message}/>)}
        </main>
    )
}