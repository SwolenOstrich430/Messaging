import React, { useState, useRef, useEffect, ReactElement, MutableRefObject } from "react";
import "./index.css";
import MessageDisplay from "../MessageDispaly";
import { useSelector } from "react-redux";
import Conversation from "../../graphql/conversations/Conversation";


export default function MessagingBody(props: any) {
    const conversations: Array<Conversation> = useSelector((state: any) => state.conversations.conversations);
    const focusedConversation: Conversation = useSelector((state: any) => state.conversations.focusedConversation);
    const currUserId: number = useSelector((state: any) => state.user.currUserId);
    const [isScrolling, setIsScrolling] = useState(false);

    let scrollElem = useRef(null);

    useEffect(() => scrollToBottom(scrollElem));

    const scrollToBottom = (elem: any) => {
        if(elem === undefined) return;
        console.log("scroll to bottom");
        elem.current.scrollIntoView();
    }

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

    let messages = focusedConversation && getFocusedMessageConversations(conversations, focusedConversation.id);

    return (
        <main style={getStyle()} className="messaging-body" onScroll={handleScroll}>
            {messages && messages.map(message => {
                return (
                    <MessageDisplay key={message.id} sent={parseInt(message.senderId.toString()) === currUserId} {...message}/>
                )
            })}
            <div className="messaging-body-dummy-scroll" ref={scrollElem}></div>
        </main>
    )
}