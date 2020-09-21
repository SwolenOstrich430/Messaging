import testUser from "../resources/userToCreate.json";
import { CREATE_USER } from "../../../graphql/users/users";
import client from "../../../graphql/index";


test("when all inputs valid, returns created user", async function() {
    let res = await client.mutate({
        mutation: CREATE_USER, 
        variables: {newUser: testUser}
    });
        
    const { username, email } = res.data.createUser;
    expect(username).toEqual(testUser.username);
    expect(email).toEqual(testUser.email);
});