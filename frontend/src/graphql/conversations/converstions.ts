import { gql } from "@apollo/client";


export const GET_CONVERSATIONS_QUE = gql`
    query GetConversations {
        getConversations {
            id 
            messages {
                id
                text
                timeSent
                senderId
            }
            users {
                id
                firstName
                lastName
                username
            }
        }
    }
`;

export const CREATED_CONVERSATION_SUB = gql`
    subscription CreatedConversation {
        createdConversation {
            id
            messages {
                id 
                text
                timeSent
                senderId
            }
            users {
                id
                firstName
                lastName
                username
            }
        }
    }
`;

export const FIND_USER_BY_USERNAME = gql`
    query FindUserByUsername($username: String!) {
        findUserByUsername(username: $username) {
            id
            username
            firstName
            lastName
            email
        }
    }
`;

export const CREATE_CONVERSATION_MUT = gql`
    mutation CreateConversation($recipientIds: [ID]) {
        createConversation(recipientIds: $recipientIds) {
            id
            users {
                id
            } 
            messages {
                id
            }
        }
    }
`;