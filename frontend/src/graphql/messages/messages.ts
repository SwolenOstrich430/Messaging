import { gql } from "@apollo/client";

export const CREATE_MESSAGE = gql`
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