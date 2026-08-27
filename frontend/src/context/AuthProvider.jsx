import { useState} from "react";
import {AuthContext} from "./AuthContext.jsx";

export function AuthProvider({children}) {
    const [token, setToken] = useState(() => localStorage.getItem("jwt"));

    function login(newToken) {
        setToken(newToken);
        localStorage.setItem("jwt", newToken);
    }

    function logout() {
        setToken(null);
        localStorage.removeItem("jwt");
    }

    const value = { token, login, logout}

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    )
}