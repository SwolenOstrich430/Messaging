import React, { useState, FormEvent } from "react";
import "./index.css";
import { useSelector, connect } from "react-redux";
import Conversation from "../../graphql/conversations/Conversation";
import { addRecipient, createConversation, cancelCreatingConversation } from "../../actions/conversations";
import conversations from "../../reducers/conversations";


function MessageHeader(props: any) {
    const creatingConversation = useSelector((state: any) => state.conversations.creatingConversation);
    const conversation: Conversation = useSelector((state: any) => state.conversations.focusedConversation);
    const addRecipientError = useSelector((state: any) => state.conversations.addRecipientError);
    const currUserId = useSelector((state: any) => state.user.currUserId);
    const [newRecipient, setNewRecipient] = useState("");
    console.log(conversation);
    
    const addNewRecipient = (event: FormEvent, username: string) => {
        event.preventDefault();
        
        props.addRecipient(username);
        setNewRecipient("");
    }

    const cancelConversation = (event: FormEvent) => {
        
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
            {addRecipientError.incorrectUsername && addRecipientError.message && 
                <span className="message-recipient-display add-recipient-error-message">
                    {addRecipientError.message}
                </span>
            }
            {conversation !== undefined && Object.keys(conversation).length > 0 && conversation.users.map(recipient => (
                (recipient.id != currUserId) && 
                <span className="message-recipient-display message-recipient" key={recipient.id}>
                    {recipient.firstName} {recipient.lastName}
                </span>
            ))}
            <div className="creating-conversation-button-container">
            {creatingConversation && 
                <button className="submit-conversation-button" onClick={props.cancelCreatingConversation}>
                    Cancel
                </button> 
            }
            {creatingConversation && conversation.users.length > 0 &&
                <button className="submit-conversation-button" onClick={e => createNewConversation(e, conversation)}>
                    Create 
                </button>
            }
            </div>
        </header>
    )
}

export default connect(null, { addRecipient, createConversation, cancelCreatingConversation })(MessageHeader);