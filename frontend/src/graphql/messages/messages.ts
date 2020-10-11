import { gql } from "@apollo/client";

export const CREATE_MESSAGE_MUT = gql`
    mutation CreateMessage($text: String!, $conversationId: ID!) {
        createMessage(text: $text conversationId: $conversationId) {
            id
            text
            conversationId
            senderId
            timeSent
        }
    }
`

export const CREATED_MESSAGE_SUB = gql`
    subscription SentMessage {
        sentMessage {
            id
            text
            conversationId
            senderId
            timeSent
        }
    }
`;