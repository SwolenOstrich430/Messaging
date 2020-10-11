import "./index.css";
import React from "react";
import CreateConversationIcon from "../CreateConversationIcon";
import { creatingConversation } from "../../actions/conversations";
import { connect } from "react-redux";


function ConversationHeader(props: any) {

    const createConversation = (isCreateConversation: boolean) => {
        props.creatingConversation(isCreateConversation)
    }
    
    return (
        <div className="conversation-header-container">
            <form className="conversation-search-form">
                <input className="conversation-search-input" type="text" placeholder="Search"/>
            </form>
            <button className="create-conversation-button" onClick={() => createConversation(true)}>
                <CreateConversationIcon/>
            </button>

        </div>
    )
}



export default connect(null, { creatingConversation })(ConversationHeader);