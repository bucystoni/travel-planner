import { formatDuration, formatPrice } from "../../utils/flightUtils.js";
import FlightSegment from "./FlightSegment.jsx";

export default function FlightCard({ offer }) {
    const stops = offer.segments.length - 1;

    let stopsText = "Direct";
    if (stops === 1) {
        stopsText = "1 stop";
    } else if (stops > 1) {
        stopsText = `${stops} stops`;
    }

    return (
        <div className="flight-card">
            <div className="flight-card-header">
                <p> Price: {formatPrice(offer.price, offer.currency)} </p>
                <p> Total duration: {formatDuration(offer.totalDurationMinutes)} </p>
                <p> Cabin class: {offer.cabinClass} </p>
                <p> {stopsText} </p>
            </div>

            {offer.requiresSelfTransfer && <p> Self-transfer required </p>}

            {offer.segments.map((segment) => (
                <FlightSegment key={`${segment.carrier}${segment.flightNumber}`} segment={segment} />
            ))}
        <div>
            <button>Book flight</button>
        </div>
        </div>
    );
}