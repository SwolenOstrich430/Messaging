import React from "react";
import "./index.css";
import ConversationContainer from "../ConversationContainer";
import MessagingContainer from "../MessagingContainer";
import { withRouter } from "react-router-dom";
import { useSelector } from "react-redux";

function Messaging(props: any) {
    const token = useSelector((state: any) => state.user.token);

    if(localStorage.getItem("token") === null || !token) {
        props.history.push("/");
    }

    return (
        <div className="messaging-page">
            <ConversationContainer/>
            <MessagingContainer/>
        </div>
    )
}

export default withRouter(Messaging);