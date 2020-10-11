import React from "react";
import "./index.css";
import { dateToConversationDisplay } from "../../helpers/time/dateUtils";


export default function ConversationDisplay(props: any) {
    const {title, lastMessage, users} = props;
    
    return (
        <div className="conversation-display-container">
            <div className="contact-image-container">
                <div className="contact-main">
                    <p className="contact-initials">
                        {users[0].firstName.substring(0, 1).toUpperCase()}{users[0].lastName.substring(0, 1).toUpperCase()}
                    </p>
                </div>
            </div>
            <div className="conversation-details-container">
                <div className="conversation-title-time-container">
                    <p className="conversation-title">{title || `${users[0].firstName} ${users[0].lastName}`}</p>
                    <p className="conversation-time-display">
                        {lastMessage && dateToConversationDisplay(lastMessage.timeSent)}
                    </p>
                </div>
                <p className="conversation-last-message-display">
                    {lastMessage && lastMessage.text}
                </p>
            </div>
        </div>
    )
}