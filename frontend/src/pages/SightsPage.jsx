import useCity from "../hooks/useCity";
import { get } from "../api/client.js";
import { useEffect, useState } from "react";

export default function SightsPage() {
    const { city } = useCity();

    const [search, setSearch] = useState("");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    
    useEffect(() => {
        if (city) {
            setSearch(city.name);
        }
    }, [city]);

async function handleSubmit(e) {
        e.preventDefault();
        setLoading(true);
        setError(null);

        if (!search) {
            setError("Please choose a city");
            setLoading(false);
            return;
        }

        try {
            const response = await get("/sights", {
                destinationName: search
            });

            console.log(response);

        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <h1>Restaurants</h1>
            <p>
                Please choose a different city if it differs from the flight destination
            </p>

            {error && <p role={"alert"}>{error}</p>}

            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="City"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />

                <button type="submit" disabled={loading}>
                    Search
                </button>

            </form>
        </div>
    );
}