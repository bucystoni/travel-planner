import { useState } from "react";
import { TripContext } from "./TripContext";
import { post, put } from "../api/client.js";

function collectPlaceIds(tripData) {
    const restaurantIds = [];
    const accommodationIds = [];
    const sightIds = [];

    for (const restaurant of tripData.restaurant) {
        restaurantIds.push(restaurant.id);
    }

    for (const accommodation of tripData.accommodation) {
        accommodationIds.push(accommodation.id);
    }

    for (const sight of tripData.sight) {
        sightIds.push(sight.id);
    }

    return {
        restaurant: restaurantIds,
        accommodation: accommodationIds,
        sight: sightIds,
    };
}

function toItems(ids) {
    const items = [];

    for (const id of ids) {
        items.push({ id: id });
    }

    return items;
}

export function TripProvider({ children }) {
    const [trip, setTrip] = useState(() => {
        const stored = localStorage.getItem("trip");

        if (!stored) {
            return null;
        }

        const parsed = JSON.parse(stored);

        if (!parsed.savedPlaces) {
            parsed.savedPlaces = { restaurant: [], accommodation: [], sight: [] };
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
        const ids = {
            restaurant: Array.from(trip.savedPlaces.restaurant),
            accommodation: Array.from(trip.savedPlaces.accommodation),
            sight: Array.from(trip.savedPlaces.sight),
        };

        ids[type].push(id);

        const body = {
            destination: trip.destination,
            departureDate: trip.departureDate,
            restaurant: toItems(ids.restaurant),
            accommodation: toItems(ids.accommodation),
            sight: toItems(ids.sight),
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

    function selectTrip(tripData) {
        const newTrip = {
            id: tripData.id,
            destination: tripData.destination,
            departureDate: tripData.departureDate,
            savedPlaces: collectPlaceIds(tripData),
        };

        setTrip(newTrip);
        localStorage.setItem("trip", JSON.stringify(newTrip));
    }

    const value = { trip, saveTrip, addToTrip, clearTrip, selectTrip };

    return (
        <TripContext.Provider value={value}>
            {children}
        </TripContext.Provider>
    );
}