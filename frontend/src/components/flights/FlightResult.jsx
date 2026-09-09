
import FlightCard from "./FlightCard.jsx"
import { buildOfferKey }  from "../../utils/flightUtils.js";

export default function FlightResult({ flights, loading }) {
    return loading
        ? <p>Searching for flights…</p>
        : !flights
            ? null
            : flights.length === 0
                ? <p>No flights found.</p>
                : <ul className="flight-list">
                      {flights.map((offer) => (
                          <li key={buildOfferKey(offer)}><FlightCard offer={offer} /></li>
                      ))}
                  </ul>;

    }