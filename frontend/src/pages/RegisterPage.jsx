import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { post } from "../api/client.js"

export default function RegisterPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    async function handleSubmit(e) {
        e.preventDefault();
        setLoading(true);
        setError(null);

        try {
            const body = { email, password, username };
            await post("/auth/register", { body : body, headers: {} });
            navigate("/login", { replace: true });

        } catch (error) {
            setError(error.message);

        } finally {
            setLoading(false);
        }
    }

    return (<div>
        <h1>Registration</h1>
        {error && <p role={"alert"}>{error}</p>}

        <form onSubmit={handleSubmit}>
            <label htmlFor={"username"}>Username</label>
            <input id={"username"} value={username}
                onChange={(e) => setUsername(e.target.value)} />

            <label htmlFor={"email"}>Email</label>
            <input id={"email"} value={email}
                onChange={(e) => setEmail(e.target.value)} />

            <label htmlFor={"password"}>Password</label>
            <input id={"password"} value={password} type="password"
                onChange={(e) => setPassword(e.target.value)} />

            <button type="submit" disabled={loading}>Register</button>
        </form>
    </div>)
}