import useCity from "../hooks/useCity.js"
import { get } from "../api/client.js"
import { useEffect, useState } from "react";



export default function FlightsPage() {
    const { city } = useCity();
    const [date, setDate] = useState("");
    const [departure, setDeparture] = useState("");
    const [destination, setDestination] = useState("");

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (city) {
            setDestination(city.name);
        }
    }, [city]);

    async function handleSubmit(e) {
        e.preventDefault();
        setLoading(true);
        setError(null);

        if (!date || !departure || !destination) {
            setError("Please fill in all fields.");
            setLoading(false);
            return;
        }

        try {
            const response = await get("/flights", {
                destinationIataCode: destination,
                departureIataCode: departure,
                date
            });
            console.log(response);

        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    }

    return <div>
        <h1>Flights</h1>
        <p>Please select a date and an airport where you would like to go:</p>
        {error && <p role={"alert"}>{error}</p>}

        <form onSubmit={handleSubmit}>

            <input
                type="date"
                onChange={(e) => setDate(e.target.value)}
            />

            <input
                type="text"
                placeholder="Departure IATA"
                value={departure}
                onChange={(e) => setDeparture(e.target.value)}
            />

            <input
                type="text"
                placeholder="Destination IATA"
                value={destination}
                onChange={(e) => setDestination(e.target.value)}
            />

            <button type="submit" disabled={loading}>Search</button>

        </form>
    </div>
}