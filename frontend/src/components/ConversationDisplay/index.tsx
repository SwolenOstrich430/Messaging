import React from "react";
import "./index.css";
import { dateToConversationDisplay } from "../../helpers/time/dateUtils";


export default function ConversationDisplay(props: any) {
    const {title, lastMessage, users} = props;

    return (
        <div className="conversation-display-container">
            <div className="contact-image-container">
                <img className="contact-image-main"/>
            </div>
            <div className="conversation-details-container">
                <div className="conversation-title-time-container">
                    <p className="conversation-title">{title}</p>
                    <p className="conversation-time-display">
                        {dateToConversationDisplay(lastMessage.timeSent)}
                    </p>
                </div>
                <p className="conversation-last-message-display">
                    {lastMessage.text}
                </p>
            </div>
        </div>
    )
}