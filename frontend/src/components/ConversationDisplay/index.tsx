import React from "react";
import "./index.css";
import { dateToConversationDisplay } from "../../helpers/time/dateUtils";
import { useSelector, connect } from "react-redux";
import Conversation from "../../graphql/conversations/Conversation";
import User from "../../graphql/users/User";
import Message from "../../graphql/messages/Message";
import conversations from "../../reducers/conversations";
import { focusOnConversation } from "../../actions/conversations";


function ConversationDisplay(props: any) {
    const creatingConversation = useSelector((state: any) => state.conversations.creatingConversation);
    const currUserId = useSelector((state: any) => state.user.currUserId);
    const {title, messages, users} = props.conversation;
    let conversationContainerClass = "conversation-display-container";

    const getContainerClassName = (isFocused: boolean, baseClassName: string) => {
        if(isFocused) {
            baseClassName += " focused"
        }

        return baseClassName;
    }

    const getConversationInitials = (currUserId: number, messages: Array<Message>, users: Array<User>) => {
        let nonUserSenders = users.filter(user => user.id !== currUserId);
        let lastNonUserSenderId;

        for(const currMessage of messages) {
            if(currMessage.senderId !== currUserId) {
                lastNonUserSenderId = currMessage.senderId;
            }
        }

        if(users.length === 2 || messages.length === 0 || !lastNonUserSenderId) {
            return getInitialsFromUser(nonUserSenders[0]);
        }

        for(const user of nonUserSenders) {
            if(user.id === lastNonUserSenderId) return getInitialsFromUser(user);
        }
    }

    const getInitialsFromUser = (user: User) => {
        return user.firstName.substring(0, 1).toUpperCase() + user.lastName.substring(0, 1).toUpperCase();
    }

    return (
        <div className={getContainerClassName(props.focused, conversationContainerClass)} 
             onClick={() => props.focusOnConversation(props.conversation)}>
            <div className="contact-image-container">
                <div className="contact-main">
                    <p className="contact-initials">
                        {!creatingConversation && getConversationInitials(currUserId, messages, users)}
                    </p>
                </div>
            </div>
            <div className="conversation-details-container">
                <div className="conversation-title-time-container">
                    <p className="conversation-title">{title || `${users[0].firstName} ${users[0].lastName}`}</p>
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