import React from "react";
import "./index.css";
import { getHoursMinutes } from "../../helpers/time/dateUtils";

export default function MessageDisplay(props: any) {
    const getClassName = (isSender: boolean, className: string): string => {
        let senderClassName = isSender ? " sender" : " recipient ";

        return className + senderClassName;
    }


    return (
        <div className="message-display">
            <p className={getClassName(props.sent, "message-text-display")}>
                {props.text}
            </p>
            <p className={getClassName(props.sent, "message-time-display")}>
                {getHoursMinutes(props.timeSent)}
            </p>
        </div>
    )
}