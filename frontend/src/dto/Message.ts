export default class Message {
    private id: number;
    private text: string;
    private conversationId: number;
    private senderId: number;


	constructor(id: number, text: string, conversationId: number, senderId: number) {
        this.id = id;
        this.text = text;
        this.conversationId = conversationId;
        this.senderId = senderId; 
	}


    /**
     * Getter $id
     * @return {number}
     */
	public getId(): number {
		return this.id;
	}

    /**
     * Getter $text
     * @return {string}
     */
	public getText(): string {
		return this.text;
	}

    /**
     * Setter $text
     * @param {string} value
     */
	public setText(value: string) {
		this.text = value;
	}

    /**
     * Getter $conversationId
     * @return {number}
     */
	public getConversationId(): number {
		return this.conversationId;
	}

    /**
     * Setter $conversationId
     * @param {number} value
     */
	public setConversationId(value: number) {
		this.conversationId = value;
	}

    /**
     * Getter $senderId
     * @return {number}
     */
	public getSenderId(): number {
		return this.senderId;
	}

    /**
     * Setter $senderId
     * @param {number} value
     */
	public setSenderId(value: number) {
		this.senderId = value;
	}

}