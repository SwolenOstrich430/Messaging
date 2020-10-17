import React, { useState, FormEvent, Fragment } from "react";
import { useSelector, connect } from "react-redux";
import "./index.css";
import Message from "../../graphql/messages/Message";
import { createMessage } from "../../actions/messages";
import LogoutButton from "../LogoutButton";


function MessagingForm(props: any) {
    const creatingConversation = useSelector((state: any) => state.conversations.creatingConversation);
    const focusedConverstaion = useSelector((state: any) => state.conversations.focusedConversation);
    const [newMessage, setNewMessage] = useState("");

    const handleSubmit = (event: FormEvent) => {
        event.preventDefault();
        if(creatingConversation) return;
        props.createMessage(newMessage, focusedConverstaion.id);
    }

    const handleSetMessage = (newMessage: string) => {
        if(creatingConversation) return;
        setNewMessage(newMessage);
    }

    return (
        <div className="messaging-form-container">
            <form className="messaging-form" onSubmit={handleSubmit}>
                <input 
                 className="messaging-input" 
                 type="text" 
                 placeholder="uMessage"
                 value={newMessage}
                 onChange={e => handleSetMessage(e.target.value)}
                />
            </form>
            <LogoutButton/>
        </div>
    )
}

export default connect(null, { createMessage })(MessagingForm);