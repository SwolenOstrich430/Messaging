import React from "react";
import "./index.css";
import { dateToConversationDisplay } from "../../helpers/time/dateUtils";
import { connect } from "react-redux";
import User from "../../graphql/users/User";
import { focusOnConversation } from "../../actions/conversations";


function ConversationDisplay(props: any) {
    const {title, messages } = props.conversation;
    let conversationContainerClass = "conversation-display-container";

    const getContainerClassName = (isFocused: boolean, baseClassName: string) => {
        if(isFocused) {
            baseClassName += " focused"
        }

        return baseClassName;
    }

    const getInitialsFromUser = (user: User) => {
        if(!user) return "";
        return user.firstName.substring(0, 1).toUpperCase() + user.lastName.substring(0, 1).toUpperCase();
    }

    return (
        <div className={getContainerClassName(props.focused, conversationContainerClass)} 
             onClick={() => props.focusOnConversation(props.conversation)}>
            <div className="contact-image-container">
                <div className="contact-main">
                    <p className="contact-initials">
                        {getInitialsFromUser(props.displayUser)}
                    </p>
                </div>
            </div>
            <div className="conversation-details-container">
                <div className="conversation-title-time-container">
                    <p className="conversation-title">{title || `${props.displayUser.firstName} ${props.displayUser.lastName}`}</p>
                    <p className="conversation-time-display">
                        {messages.length > 0 && dateToConversationDisplay(messages[0].timeSent)}
                    </p>
                </div>
                <p className="conversation-last-message-display">
                    {messages.length > 0 && messages[0].text}
                </p>
            </div>
        </div>
    )
}


export default connect(null, { focusOnConversation })(ConversationDisplay);