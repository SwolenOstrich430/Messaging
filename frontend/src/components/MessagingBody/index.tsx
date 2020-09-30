import React from "react";
import "./index.css";
import Message from "../../graphql/messages/Message";
import User from "../../graphql/users/User";
import MessageDisplay from "../MessageDispaly";

const messages: Array<Message> = [];
const mess1: Message = new Message(1, "3 some message", 2, 2, new Date());
const mess2: Message = new Message(1, "2 some message", 3, 3, new Date());
const mess3: Message = new Message(1, "1 some message", 2, 2, new Date());
const user: User = new User(2, "someUser", "email", "first", "last");
messages.push(mess1, mess2, mess3);


export default function MessagingBody(props: any) {
    return (
        <main className="messaging-body">
            {messages.map(message => <MessageDisplay {...message}/>)}
        </main>
    )
}