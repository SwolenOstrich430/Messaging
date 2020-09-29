import "./index.css";
import React from "react";


export default function ConversationHeader() {
    return (
        <div className="conversation-header-container">
            <form className="conversation-search-form">
                <input className="conversation-search-input" type="text"/>
            </form>
            <button className="create-conversation-button">
                +
            </button>

        </div>
    )
}