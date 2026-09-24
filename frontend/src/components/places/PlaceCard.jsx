import { useState } from "react";
import useTrip from "../../hooks/useTrip.js";

export default function PlaceCard({ place, type, city }) {
    const { trip, addToTrip } = useTrip();
    const [saving, setSaving] = useState(false);
    const [saved, setSaved] = useState(false);
    const [error, setError] = useState(null);
    const canSave = trip !== null && trip.destination === city;
    const alreadySaved = trip !== null && trip.savedPlaces[type].includes(place.id);

    async function handleSave() {
        setSaving(true);
        setError(null);

        try {
            await addToTrip(type, place.id);
            setSaved(true);
        } catch (error) {
            setError(error.message);
        } finally {
            setSaving(false);
        }
    }

    return (

        <div className="place-card">
            <p> {place.name} </p>
            <p> Address: {place.address} </p>
            {place.url && <a href={place.url} target="_blank" rel="noopener noreferrer">Check out the website here</a>}
            <div>
                <button onClick={handleSave} disabled={!canSave || saving || saved || alreadySaved }>
                    {saved || alreadySaved ? "Saved ✓" : saving ? "Saving…" : "Save to my trip"}
                </button>

                {!trip && <p>Pick a flight first, or continue a saved trip</p>}
                {trip && !canSave && <p>Your trip is to {trip.destination} — save a flight to {city} first</p>}
                {error && <p role="alert">{error}</p>}
            </div>
        </div>
        )
    }