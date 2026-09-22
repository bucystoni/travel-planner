import PlaceCard from "./PlaceCard.jsx";

export default function PlaceResult({ places, loading, type }) {
    return loading
            ? <p>Searching…</p>
            : !places
                ? null
                : places.pointsOfInterest.length === 0
                    ? <p>Not found.</p>
                    : <ul className="place-list">
                          {places.pointsOfInterest.map((place) => (
                              <li key={place.id}><PlaceCard place={place} type={type} city={places.city.name}/></li>
                          ))}
                      </ul>;

    }