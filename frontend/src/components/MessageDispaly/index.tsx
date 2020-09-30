import React from "react";
import "./index.css";


export default function MessageDisplay(props: any) {
    const getClassName = (isSender: boolean): string => {
        let className: string = "message-text-display ";
        let senderClassName = isSender ? "sender" : "recipient";

        return className + senderClassName;
    }


    return (
        <div className="message-display">
            <p className={getClassName(props.senderId == 2)}>
                {props.text}
            </p>
        </div>
    )
}