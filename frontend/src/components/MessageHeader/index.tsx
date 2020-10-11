import React, { useState, FormEvent } from "react";
import "./index.css";
import { useSelector, connect } from "react-redux";
import Conversation from "../../graphql/conversations/Conversation";
import { addRecipient, createConversation } from "../../actions/conversations";


function MessageHeader(props: any) {
    const creatingConversation = useSelector((state: any) => state.conversations.creatingConversation);
    const conversation: Conversation = useSelector((state: any) => state.conversations.focusedConversation);
    const addRecipientError = useSelector((state: any) => state.conversations.addRecipientError);
    const [newRecipient, setNewRecipient] = useState("");

    
    const addNewRecipient = (event: FormEvent, username: string) => {
        event.preventDefault();
        
        props.addRecipient(username);
        setNewRecipient("");
    }

    const createNewConversation = (event: FormEvent, conversation: Conversation) => {
        event.preventDefault();

        const recipientIds: Array<number> = conversation.users.map(user => user.id);
        props.createConversation(recipientIds);
    } 

    return (
        <header className="message-header">
            <p className="message-recipient-display">To: </p>
            {creatingConversation && 
                <form className="add-recipient-form" onSubmit={e => addNewRecipient(e, newRecipient)}>
                    <input 
                     className="add-recipient-input" 
                     type="text" 
                     placeholder="Add recipient"
                     value={newRecipient}
                     onChange={e => setNewRecipient(e.target.value)}
                    />
                </form>
            }
            {addRecipientError.incorrectUsername &&
                <span className="message-recipient-display add-recipient-error-message">
                    {addRecipientError.message}
                </span>
            }
            {conversation.users && conversation.users.map(recipient => (
                <span className="message-recipient-display message-recipient" key={recipient.id}>
                    {recipient.firstName} {recipient.lastName}
                </span>
            ))}
            {creatingConversation && conversation.users.length > 0 &&
                <button className="submit-conversation-button" onClick={e => createNewConversation(e, conversation)}>
                    Create 
                </button>

            }
        </header>
    )
}

export default connect(null, { addRecipient, createConversation })(MessageHeader);