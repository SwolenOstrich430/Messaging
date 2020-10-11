/// <reference types="react-scripts" />

declare global {
    namespace NodeJS {
        interface ProcessEnv {
            TEST_API_URL: "http://localhost:8080/grpahql"
            PUBLIC_URL: "https://localhost:8080/graphql"
        }
    }
}