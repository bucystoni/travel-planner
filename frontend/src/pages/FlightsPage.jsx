import { useState } from "react";
import { useNavigate } from "react-router-dom";
import  useCity  from "../hooks/useCity"
import {get} from "../api/client.js"


export default function FlightsPage() {
    const { city } = useCity();
    const [date, setDate] = useState(null);
    const [departure, setDeparture] = useState(null);
    const [destination, setDestination] = useState(city ? city.name : "");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);


    async function handleSubmit(e) {
        e.preventDefault();
        setLoading(true);
        setError(null);

        try {
            const response = await get("/flights", { destination, departure, date });
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