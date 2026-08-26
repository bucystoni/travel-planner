import { useState } from "react";
import { useNavigate } from "react-router-dom";
import {useAuth} from "../hooks/useAuth.js";
import {post} from "../api/client.js";

export default function LoginPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const { login } = useAuth();

    async function handleSubmit(e) {
        e.preventDefault();
        setLoading(true);
        setError(null);

        try {
            const body = { username, password };
            const response = await post("/auth/login", body);
            const data = await response.json();
            login(data.jwt)
            navigate("/destinations", { replace: true });

        } catch (error) {
            setError(error.message);
            
        } finally {
            setLoading(false);
        }
    }

    return (<div>
        <h1>Login</h1>
        {error && <p role={"alert"}>{error}</p>}

        <form onSubmit={handleSubmit}>
            <label htmlFor={"username"}>Username</label>
            <input id={"username"} value={username}
                   onChange={(e) => setUsername(e.target.value)} />

            <label htmlFor={"password"}>Password</label>
            <input id={"password"} value={password} type="password"
                   onChange={(e) => setPassword(e.target.value)} />
                   
            <button type="submit" disabled={loading}>Login</button>
        </form>
    </div>)
}