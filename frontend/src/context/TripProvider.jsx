import { useState } from "react";
import { TripContext } from "./TripContext";
import { post, put } from "../api/client.js";

function collectPlaceIds(tripData) {
    const ids = [];

    for (const restaurant of tripData.restaurant) {
        ids.push(`restaurant-${restaurant.id}`);
    }

    for (const accommodation of tripData.accommodation) {
        ids.push(`accommodation-${accommodation.id}`);
    }

    for (const sight of tripData.sight) {
        ids.push(`sight-${sight.id}`);
    }

    return ids;
}

export function TripProvider({ children }) {
    const [trip, setTrip] = useState(() => {
        const stored = localStorage.getItem("trip");

        if (!stored) {
            return null;
        }

        const parsed = JSON.parse(stored);

        if (!parsed.savedPlaces) {
            parsed.savedPlaces = [];
        }

        return parsed;
    });

    async function saveTrip({ destination, departureDate, flightTicket }) {
        const created = await post("/trips", {
            body: { destination, departureDate, flightTicket },
        });

        const newTrip = {
            id: created.id,
            destination: destination,
            departureDate: departureDate,
            savedPlaces: collectPlaceIds(created),
        };
        setTrip(newTrip);
        localStorage.setItem("trip", JSON.stringify(newTrip));

        return created;
    }

    async function addToTrip(type, id) {
        const body = {
            destination: trip.destination,
            departureDate: trip.departureDate,
            [type]: [{ id: id }],
        };

        const updated = await put(`/trips/${trip.id}`, { body });

        const newTrip = {
            id: trip.id,
            destination: trip.destination,
            departureDate: trip.departureDate,
            savedPlaces: collectPlaceIds(updated),
        };

        setTrip(newTrip);
        localStorage.setItem("trip", JSON.stringify(newTrip));

        return updated;
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