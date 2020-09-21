import Message from "../messages/Message";
import User from "../users/User";


export default class Conversation {
    private id: number;
    private messages: Array<Message>;
    private users: Array<User>;


    constructor(id: number, messages: Array<Message>, users: Array<User>) {
        this.id = id;
        this.messages = messages;
        this.users = users;
    }

    /**
     * Getter $id
     * @return {number}
     */
	public getId(): number {
		return this.id;
	}

    /**
     * Getter $messages
     * @return {Array<Message>}
     */
	public getMessages(): Array<Message> {
		return this.messages;
	}

    /**
     * Getter $users
     * @return {Array<Users>}
     */
	public get $users(): Array<User> {
		return this.users;
	}
    
    /**
     * Setter $messages
     * @param {Array<Message>} value
     */
	public setMessages(value: Array<Message>) {
		this.messages = value;
	}

    /**
     * Setter $users
     * @param {Array<Users>} value
     */
	public setUsers(value: Array<User>) {
		this.users = value;
	}

}