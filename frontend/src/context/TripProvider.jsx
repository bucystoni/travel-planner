import { useState } from "react";
import { TripContext } from "./TripContext";
import { post, put } from "../api/client.js";

export function TripProvider({ children }) {
    const [trip, setTrip] = useState(() => {
        const stored = localStorage.getItem("trip");
        return stored ? JSON.parse(stored) : null;
    });

    async function saveTrip({ destination, departureDate, flightTicket }) {
        const created = await post("/trips", {
            body: { destination, departureDate, flightTicket },
        });

        const newTrip = { id: created.id, destination, departureDate };
        setTrip(newTrip);
        localStorage.setItem("trip", JSON.stringify(newTrip));

        return created;
    }

    async function addToTrip(partial) {
        const body = {
            destination: trip.destination,
            departureDate: trip.departureDate,
            ...partial,
        };

        return await put(`/trips/${trip.id}`, { body });
    }

    function clearTrip() {
        setTrip(null);
        localStorage.removeItem("trip");
    }

    const value = { trip, saveTrip, addToTrip, clearTrip };

    return (
        <TripContext.Provider value={value}>
            {children}
        </TripContext.Provider>
    );
}