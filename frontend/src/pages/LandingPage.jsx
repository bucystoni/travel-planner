import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCity } from "../hooks/useCity.js";
import { get } from "../api/client.js"


export default function LandingPage() {
    const [search, setSearch] = useState("");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const { setCity } = useCity();

    async function handleSubmit(e) {
        e.preventDefault();
        setLoading(true);
        setError(null);

        try {
            const response = await get("/destinations", {name: search});

            setCity(await response.json());
            navigate("/accommodations", { replace: true });
        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    }


    return (
        <div>
            <h1>Landing Page</h1>
            <p>Please type the destination where you would like to go:</p>
            {error && <p role={"alert"}>{error}</p>}

            <form onSubmit={handleSubmit}>

                <label htmlFor={"city"}>Search for a city</label>
                <input id={"city"} value={search}
                    onChange={(e) => setSearch(e.target.value)} />

                <button type="submit" disabled={loading}>Search</button>
            </form>
        </div>
    )
}