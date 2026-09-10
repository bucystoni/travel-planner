import { formatDuration, formatPrice } from "../../utils/flightUtils.js";
import FlightSegment from "./FlightSegment.jsx";
import { useState } from "react";
import useCity from "../../hooks/useCity.js";
import useTrip from "../../hooks/useTrip.js";

export default function FlightCard({ offer }) {
    const { city } = useCity();
    const { saveTrip } = useTrip();
    const [saving, setSaving] = useState(false);
    const [saved, setSaved] = useState(false);
    const [error, setError] = useState(null);

    const stops = offer.segments.length - 1;

    let stopsText = "Direct";
    if (stops === 1) {
        stopsText = "1 stop";
    } else if (stops > 1) {
        stopsText = `${stops} stops`;
    }

async function handleSave() {
    setSaving(true);
    setError(null);

    try {
        await saveTrip({
            destination: city.name,
            departureDate: offer.departureDate,
            flightTicket: offer,
        });
        setSaved(true);
    } catch (error) {
        setError(error.message);
    } finally {
        setSaving(false);
    }
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
            <button onClick={handleSave} disabled={saving || saved || !city}>
                {saved ? "Saved ✓" : saving ? "Saving…" : "Save to my trip"}
            </button>
            {error && <p role="alert">{error}</p>}
        </div>
        </div>
    );
}