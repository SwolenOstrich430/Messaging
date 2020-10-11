import { gql, useMutation } from "@apollo/client";


export const CREATE_USER_MUT = gql`
    mutation CreateUser($newUser: UserDTO) {
        createUser(newUser: $newUser) {
            username
            firstName
            lastName
            id
        }
    }
`;

export const AUTHENTICATE_USER_QUE = gql`
    query AuthenticateUser($authenticationRequest: AuthenticationRequest!) {
        authenticateUser(authenticationRequest: $authenticationRequest) {
            token
            id
        }
    }
`;