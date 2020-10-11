import UserInterface from "./UserInterface";

export default class User implements UserInterface {

    public readonly id: number;
    public username: string;
    public email: string;
    public firstName: string;
    public lastName: string;

    constructor(id: number, username: string, email: string, firstName: string, lastName: string) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }


  


}