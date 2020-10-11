import MessageDTO from "./MessageDTO";

export default class Message {
    public readonly id: number;
    public text: string;
    public readonly conversationId: number;
    public readonly senderId: number;
    public readonly timeSent: Date; 


    public static fromMessageDTO(messageDTO: MessageDTO) {
        return new Message(
            messageDTO.id, 
            messageDTO.text,
            messageDTO.conversationId, 
            messageDTO.senderId, 
            new Date(messageDTO.timeSent)
        );
    }

	constructor(id: number, text: string, conversationId: number, senderId: number, timeSent: Date) {
        this.id = id;
        this.text = text;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.timeSent = timeSent; 
	}

}