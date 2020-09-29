import React from "react";
import "./index.css";
import ConversationContainer from "../ConversationContainer";
import MessagingContainer from "../MessagingContainer";

export default function Messaging() {
    return (
        <div className="messaging-container">
            <ConversationContainer/>
            <MessagingContainer/>
        </div>
    )
}