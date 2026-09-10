
import { useEffect, useState } from "react";
import { get } from "../api/client.js";
import { formatPrice } from "../utils/flightUtils.js";

export default function TripsPage() {
    const [trips, setTrips] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

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
                                          <td>{trip.restaurant.map((r) => r.name).join(", ") || "—"}</td>
                                          <td>{trip.accommodation.map((a) => a.name).join(", ") || "—"}</td>
                                          <td>{trip.sight.map((s) => s.name).join(", ") || "—"}</td>
                                      </tr>
                                  ))}
                              </tbody>
                          </table>
                          </div>}
        </div>
    );
}