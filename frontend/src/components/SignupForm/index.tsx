import React, { useState } from "react";
import "./index.css";


export default function SignupForm() {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");


    return (
        <form className="auth-form">
            <h2 className="auth-form-header">Sign Up</h2>
            <label className="auth-form-label">First name</label>
            <input 
             className="auth-form-text-input" 
             type="text"
             value={firstName}
             onChange={e => setFirstName(e.target.value)}
            />
            <label className="auth-form-label">Last name</label>
            <input 
             className="auth-form-text-input" 
             type="password"
             value={lastName}
             onChange={e => setLastName(e.target.value)}
            />
            <label className="auth-form-label">Username</label>
            <input 
             className="auth-form-text-input" 
             type="text"
             value={username}
             onChange={e => setUsername(e.target.value)}
            />
            <label className="auth-form-label">Password</label>
            <input 
             className="auth-form-text-input" 
             type="password"
             value={password}
             onChange={e => setPassword(e.target.value)}
            />
            <label className="auth-form-label">Email</label>
            <input 
             className="auth-form-text-input" 
             type="password"
             value={email}
             onChange={e => setEmail(e.target.value)}
            />
            <button className="auth-form-submit-button">Sign Up</button>
        </form>
    )
}