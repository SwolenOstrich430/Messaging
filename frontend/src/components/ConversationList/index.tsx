import "./index.css";
import React from "react";
import Conversation from "../../graphql/conversations/Conversation";
import Message from "../../graphql/messages/Message";
import User from "../../graphql/users/User";
import ConversationDisplay from "../ConversationDisplay";


const conversations: Array<Conversation>= new Array<Conversation>();
const mess1: Message = new Message(1, "some message", 2, 2, new Date());
const user1: User = new User(1, "blah", "blahj", "Other", "User");
const conv1: Conversation = new Conversation(1, [mess1], [user1]);
const conv2: Conversation = new Conversation(2, [mess1], [user1]);
conversations.push(conv1);
conversations.push(conv2)



export default function ConversationList() {
    const conversationToComponent = (conversation: Conversation) => {
        const users: Array<User> = conversation.getUsers();
        const lastMessage: Message = conversation.getMessages()[0];
        
        return <ConversationDisplay 
                users={users} 
                lastMessage={lastMessage} 
                title="title"
               />
    }

    return (
        <div className="conversation-list">
            {conversations.map(conversationToComponent)}
        </div>
    )
}