import React, { useState } from "react";
import "./index.css";
import LoginForm from "../LoginForm";
import SignupForm from "../SignupForm";
import { withRouter } from "react-router-dom";
import { useSelector } from "react-redux";

function Home(props: any) {
    const token = useSelector((state: any) => state.user.token);
    const [showLogin, setShowLogin] = useState(true);
    const formChangeText = showLogin ? "Sign Up" : "Login";
    console.log("is authenticated: " + token);
    if(token.length > 0) {
        console.log("got inhere");
        props.history.push("/messaging")
    }

    return (
        <div className="home-page">
            <div className="home-auth-form-container">
                {
                    showLogin ? 
                    <LoginForm/> :
                    <SignupForm/>
                }
                <button className="home-change-auth-form" onClick={() => setShowLogin(!showLogin)}>
                    {formChangeText}
                </button>
            </div>
        </div>
    )
}

export default withRouter(Home);