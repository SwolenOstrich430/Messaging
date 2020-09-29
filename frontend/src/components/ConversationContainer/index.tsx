import "./index.css";
import React, { Fragment } from "react";
import ConversationHeader from "../ConversationHeader";
import ConversationList from "../ConversationList";

export default function ConversationContainer() {
    return (
        <div className="conversation-container">
            <ConversationHeader/>
            <ConversationList/>
        </div>
    )
}
