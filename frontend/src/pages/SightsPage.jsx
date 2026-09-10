import useCity from "../hooks/useCity";
import { get } from "../api/client.js";
import { useState } from "react";
import PlaceResult from "../components/places/PlaceResult.jsx";

export default function SightsPage() {
    const { city } = useCity();

    const [search, setSearch] = useState(city ? city.name : "");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const [sights, setSights] = useState(null);
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
            const data = await get("/sights", {
                destinationName: search
            });
            setSights(await data);

        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="page page-sights">
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
            <PlaceResult places={sights} loading={loading} />
        </div>
    );
}