import useCity from "../hooks/useCity";
import { get } from "../api/client.js";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function RestaurantsPage() {
    const { city } = useCity();
    const navigate = useNavigate();

    const [search, setSearch] = useState(city ? city.name : "");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const [restaurants, setRestaurants] = useState(null);
    const [prevCity, setPrevCity] = useState(city);
    if (city !== prevCity) {
        setPrevCity(city);
        setSearch(city ? city.name : "");
    }


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
            const response = await get("/restaurants", {
                destinationName: search
            });
            setRestaurants(response);

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

                <button
                    type="button"
                    onClick={() => navigate("/sights")}
                >
                    Sights
                </button>

            </form>

            {restaurants && (
                <pre>
                    {JSON.stringify(restaurants, null, 2)}
                </pre>
            )}
        </div>
    );
}