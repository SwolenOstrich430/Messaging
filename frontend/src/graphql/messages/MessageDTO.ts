export default interface MessageDTO {
    id: number;
    text: string;
    senderId: number 
    conversationId: number;
    timeSent: string;
}