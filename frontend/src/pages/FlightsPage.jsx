import useCity from "../hooks/useCity.js"
import { get } from "../api/client.js"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import findAirports from "../utils/airportUtils.js";


export default function FlightsPage() {
    const { city } = useCity();
    const navigate = useNavigate();
    const [departureOptions, setDepartureOptions] = useState([]);
    const [destinationOptions, setDestinationOptions] = useState([]);

    const [destinationIata, setDestinationIata] = useState("")
    const [departureIata, setDepartureIata] = useState("")

    const [date, setDate] = useState("");
    const [departure, setDeparture] = useState("");
    const [destination, setDestination] = useState(city ? city.name : "");

    const [flights, setFlights] = useState(null);

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const [prevCity, setPrevCity] = useState(city);
    if (city !== prevCity) {
        setPrevCity(city);
        setDestination(city ? city.name : "");
    }

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
                destinationIataCode: destinationIata,
                departureIataCode: departureIata,
                date
            });
            setFlights(await response.json());

        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    }

    function handleDepartureChange(e) {
        const value = e.target.value;

        setDeparture(value);
        setDepartureOptions(findAirports(value));
    }

    function handleDestinationChange(e) {
        const value = e.target.value;

        setDestination(value);
        setDestinationOptions(findAirports(value));
    }

    function handleDepartureSelect(airport) {
        setDeparture(`${airport.name} — ${airport.city} (${airport.iata})`);
        setDepartureIata(airport.iata);
        setDepartureOptions([]);
    }

    function handleDestinationSelect(airport) {
        setDestination(`${airport.name} — ${airport.city} (${airport.iata})`);
        setDestinationIata(airport.iata);
        setDestinationOptions([]);
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

            <div>
                <input
                    type="text"
                    value={departure}
                    onChange={handleDepartureChange}
                />

                {departureOptions.length > 0 && (
                    <div>
                        {departureOptions.map((airport) => (
                            <div
                                key={airport.iata}
                                onClick={() => handleDepartureSelect(airport)}
                            >
                                {airport.name} — {airport.city} ({airport.iata})
                            </div>
                        ))}
                    </div>
                )}
            </div>

            <div>
                <input
                    type="text"
                    value={destination}
                    onChange={handleDestinationChange}
                />

                {destinationOptions.length > 0 && (
                    <div>
                        {destinationOptions.map((airport) => (
                            <div
                                key={airport.iata}
                                onClick={() => handleDestinationSelect(airport)}
                            >
                                {airport.name} — {airport.city} ({airport.iata})
                            </div>
                        ))}
                    </div>
                )}
            </div>

            <button type="submit" disabled={loading}>Search</button>

            <button type="button" onClick={() => navigate("/accommodations")}>
                Accommodations
            </button>

            {flights && (
                <pre>
                    {JSON.stringify(flights, null, 2)}
                </pre>
            )}

        </form>
    </div>
}