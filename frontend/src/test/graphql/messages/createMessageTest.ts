import { CREATE_MESSAGE_MUT } from "../../../graphql/messages/messages";
import client from "../../../graphql/index";
import messageTestData from "../resources/messagesToSend.json";

test("create message with valid inputs", async function() {
    localStorage.setItem("token", messageTestData.jwt);
    const textToSend: string = "some text"; 
    
    let res = await client.mutate({ 
        mutation: CREATE_MESSAGE_MUT, 
        variables: {
            conversationId: messageTestData.conversationId, 
            text: textToSend
        }
    });

    const { senderId, text, conversationId } = res.data.createMessage; 
    expect(parseInt(senderId)).toBe(8);
    expect(parseInt(conversationId)).toBe(messageTestData.conversationId);
    expect(text).toBe(textToSend);
})