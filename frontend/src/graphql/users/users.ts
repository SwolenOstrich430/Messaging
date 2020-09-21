import { gql, useMutation } from "@apollo/client";


export const CREATE_USER = gql`
    mutation CreateUser($newUser: UserDTO) {
        createUser(newUser: $newUser) {
            username
            email
            firstName
            lastName
            id
        }
    }
`;
