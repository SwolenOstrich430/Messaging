import Conversation from "../../graphql/conversations/Conversation"


export const sortByLastMessageSent = (conv1: any, conv2: any): number => {
    console.log("got in here");
    let conv1MessagesLength: number = conv1.messages.length;
    let conv2MessagesLength: number = conv2.messages.length;

    if(conv1MessagesLength === 0 && conv2MessagesLength === 0) return 0;
    if(conv1MessagesLength === 0) return -1;
    if(conv2MessagesLength === 0) return 1;
    console.log(conv1MessagesLength);
    console.log(conv2MessagesLength); 
    let conv1MessageTimeSent: Date = conv1.messages[conv1MessagesLength - 1].timeSent;
    let conv2MessageTimeSent: Date = conv2.messages[conv2MessagesLength - 1].timeSent;
    console.log(conv1MessageTimeSent);
    console.log(conv2MessageTimeSent);
    console.log(conv1MessageTimeSent > conv2MessageTimeSent);
    return conv1MessageTimeSent.getTime() - conv2MessageTimeSent.getTime();
}