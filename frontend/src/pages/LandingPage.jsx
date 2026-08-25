import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {useCity} from "../hooks/useCity.js";


export default function LandingPage() {
    const [search, setSearch] = useState("");
    const [error, setError] = useState(null);
    const [submitting, setSubmitting] = useState(false);
    const navigate = useNavigate();
    const { setCity } = useCity();

    async function handleSubmit(e) {
        e.preventDefault();
        setSubmitting(true);
        setError(null);

        try {
            const response = await fetch(`http://localhost:8080/api/destinations?name=${search}`);

            if (!response.ok) {
                if (response.status === 404) {
                    setError(`City not found: ${search}`);
                } else {
                    setError("Search failed. Please try again.");
                }
                return;
            }

            setCity(await response.json());
            navigate("/accommodations", { replace: true });
        } catch {
            setError("Cannot reach the server. Is the backend running?");
        } finally {
            setSubmitting(false);
        }
    }


    return (
        <div>
            <h1>Landing Page</h1>
            {error && <p role={"alert"}>{error}</p>}
            <form onSubmit={handleSubmit}>
                <label htmlFor={"city"}>Search for a city</label>
                <input id={"city"} value={search}
                       onChange={(e) => setSearch(e.target.value)} />
                <button type="submit" disabled={submitting}>Search</button>
            </form>
        </div>
    )
}