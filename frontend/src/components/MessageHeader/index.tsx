import React from "react";
import "./index.css";


export default function MessageHeader(props: any) {
    const user = {firstName: "Charleigh", lastName: "Smith"};

    return (
        <header className="message-header">
            <p className="message-recipient-display">To:  
                <span className="message-recipient">
                    {user.firstName} {user.lastName}
                </span>
            </p>
        </header>
    )
}