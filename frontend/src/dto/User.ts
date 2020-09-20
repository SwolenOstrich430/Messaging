export default class User {

    private id: number;
    private username: string;
    private email: string;
    private firstName: string;
    private lastName: string;

    constructor(id: number, username: string, email: string, firstName: string, lastName: string) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    /**
     * Getter $id
     * @return {number}
     */
	public getId(): number {
		return this.id;
	}

    /**
     * Getter $username
     * @return {string}
     */
	public getUsername(): string {
		return this.username;
	}

    /**
     * Setter $username
     * @param {string} value
     */
	public setUsername(value: string) {
		this.username = value;
	}


    /**
     * Getter $email
     * @return {string}
     */
	public getEmail(): string {
		return this.email;
	}

    /**
     * Setter $email
     * @param {string} value
     */
    public setEmail(value: string) {
		this.email = value;
	}

    /**
     * Getter $firstName
     * @return {string}
     */
    public getFirstName(): string {
		return this.firstName;
	}

    /**
     * Setter $firstName
     * @param {string} value
     */
	public setFirstName(value: string) {
		this.firstName = value;
	}

    /**
     * Getter $lastName
     * @return {string}
     */
	public getLastName(): string {
		return this.lastName;
	}

    /**
     * Setter $lastName
     * @param {string} value
     */
	public setLastName(value: string) {
		this.lastName = value;
	}


}