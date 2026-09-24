
import { useEffect, useState } from "react";
import { get, del } from "../api/client.js";
import { formatPrice } from "../utils/flightUtils.js";
import useTrip from "../hooks/useTrip.js";

function PlaceList({ places }) {
    if (places.length === 0) {
        return "—";
    }

    return (
        <ul className="trip-items">
            {places.map((place) => (
                <li key={place.id}>{place.name}</li>
            ))}
        </ul>
    );
}

export default function TripsPage() {
    const [trips, setTrips] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);
    const { trip: activeTrip, clearTrip, selectTrip } = useTrip();

    useEffect(() => {
        async function loadTrips() {
            try {
                const data = await get("/trips");
                setTrips(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        }
        loadTrips();
    }, []);

    async function handleDelete(id) {
        try {
            await del(`/trips/${id}`);
            setTrips((prev) => prev.filter((t) => t.id !== id));

            if (activeTrip && activeTrip.id === id) {
                clearTrip();
            }
        } catch (error) {
            setError(error.message);
        }
    }

    return (
        <div className="page page-trips">
            <h1>My trips</h1>

            {loading
                ? <p>Loading…</p>
                : error
                    ? <p role="alert">{error}</p>
                    : trips.length === 0
                        ? <p>You have no saved trips yet.</p>
                        : <div className="trips-table-wrap">
                              <table className="trips-table">
                              <thead>
                                  <tr>
                                      <th>Destination</th>
                                      <th>Departure</th>
                                      <th>Flight</th>
                                      <th>Restaurants</th>
                                      <th>Accommodations</th>
                                      <th>Sights</th>
                                      <th></th>
                                      <th></th>
                                  </tr>
                              </thead>
                              <tbody>
                                  {trips.map((trip) => (
                                      <tr key={trip.id}>
                                          <td>{trip.destination}</td>
                                          <td>{trip.departureDate}</td>
                                          <td>
                                              {trip.flightTicket
                                                  ? `${trip.flightTicket.origin} → ${trip.flightTicket.destination} (${formatPrice(trip.flightTicket.price, trip.flightTicket.currency)})`
                                                  : "—"}
                                          </td>
                                          <td><PlaceList places={trip.restaurant} /></td>
                                          <td><PlaceList places={trip.accommodation} /></td>
                                          <td><PlaceList places={trip.sight} /></td>
                                          <td>
                                              {activeTrip && activeTrip.id === trip.id
                                                  ? <span className="trip-active">Active</span>
                                                  : <button className="trip-continue" onClick={() => selectTrip(trip)}>Continue</button>}
                                          </td>
                                          <td>
                                              <button
                                                  onClick={() => handleDelete(trip.id)}
                                                  aria-label={`Delete trip to ${trip.destination}`}
                                                  title="Delete trip"
                                              >
                                                  <svg viewBox="0 0 24 24" width="23" height="23" fill="none"
                                                       stroke="currentColor" strokeWidth="2"
                                                       strokeLinecap="round" strokeLinejoin="round" aria-hidden="true">
                                                      <path d="M3 6h18" />
                                                      <path d="M8 6V4a1 1 0 0 1 1-1h6a1 1 0 0 1 1 1v2" />
                                                      <path d="M19 6l-1 14a2 2 0 0 1-2 2H8a2 2 0 0 1-2-2L5 6" />
                                                      <line x1="10" y1="11" x2="10" y2="17" />
                                                      <line x1="14" y1="11" x2="14" y2="17" />
                                                  </svg>
                                              </button>
                                          </td>
                                      </tr>
                                  ))}
                              </tbody>
                          </table>
                          </div>}
        </div>
    );
}