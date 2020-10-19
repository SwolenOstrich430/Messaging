import React from "react";
import "./index.css";
import LoginForm from "../LoginForm";
import SignupForm from "../SignupForm";
import { withRouter } from "react-router-dom";
import { useSelector } from "react-redux";
import { connect } from "react-redux";
import { showLogin } from "../../actions/user";

function Home(props: any) {
    const token = useSelector((state: any) => state.user.token);
    const showLogin: boolean = useSelector((state: any) => state.user.showLogin);

    const formChangeText = showLogin ? "Sign Up" : "Login";
    console.log("is authenticated: " + token);
    if(token && token.length > 0) {
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
                <button className="home-change-auth-form" onClick={() => props.showLogin(!showLogin)}>
                    {formChangeText}
                </button>
            </div>
        </div>
    )
}

export default connect(null, { showLogin })(withRouter(Home));