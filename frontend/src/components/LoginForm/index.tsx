import React, { useState, FormEvent } from "react";
import { connect, useSelector } from "react-redux";
import "./index.css";
import { authenticateUser } from "../../actions/user";


function LoginForm(props: any) {
    const authError: Error = useSelector((state: any) => state.user.authError);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (event: FormEvent) => {
        event.preventDefault();
        console.log("in handle submit");
        props.authenticateUser({email, password});
    }

    console.log(authError);
    return (
        <form className="auth-form" onSubmit={handleSubmit}>
            <h2 className="auth-form-header">Login</h2>
            {authError.message && <p className="auth-error-message">{authError.message}</p>}
            <label className="auth-form-label">Email</label>
            <input 
             className="auth-form-text-input" 
             type="text"
             value={email}
             onChange={e => setEmail(e.target.value)}
            />
            <label className="auth-form-label">Password</label>
            <input 
             className="auth-form-text-input" 
             type="password"
             value={password}
             onChange={e => setPassword(e.target.value)}
            />
            <button className="auth-form-submit-button">Login</button>
        </form>
    )
}

export default connect(null, { authenticateUser })(LoginForm);