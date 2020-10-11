import "./index.css";
import React from "react";
import MessagingHeader from "../MessageHeader";
import MessagingBody from "../MessagingBody";
import MessagingForm from "../MessagingForm";


export default function MessagingContainer() {
    return (
        <div className="messaging-container">
            <MessagingHeader/>
            <MessagingBody/>
            <MessagingForm/>
        </div>
    )
}