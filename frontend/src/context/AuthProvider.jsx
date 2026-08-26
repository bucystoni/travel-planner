import { useState} from "react";

export function AuthProvider({children}) {
    const [token, setToken] = useState(() => localStorage.getItem("token"));

    function login(newToken) {
        setToken(newToken);
        localStorage.setItem("token", newToken);
    }

    function logout() {
        setToken(null);
        localStorage.removeItem("token");
    }

    const value = { token, login, logout}

    return (
        <AuthProvider.Provider value={value}>
            {children}
        </AuthProvider.Provider>
    )
}