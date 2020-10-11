import Message from "../messages/Message";
import User from "../users/User";


export default class Conversation {
    public readonly id: number;
    public title: string;
    public messages: Array<Message>;
    public users: Array<User>;

    public static fromTitle(title: string): Conversation {
        return new Conversation(0, title, new Array<Message>(), new Array<User>());
    }

    constructor(id: number, title: string, messages: Array<Message>, users: Array<User>) {
        this.id = id;
        this.title = title;
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
    
    public getTitle(): string {
        return this.title;
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
	public getUsers() {
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