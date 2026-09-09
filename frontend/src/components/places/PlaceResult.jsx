import PlaceCard from "./PlaceCard.jsx";

export default function PlaceResult({ places, loading }) {
    return loading
            ? <p>Searching…</p>
            : !places
                ? null
                : places.length === 0
                    ? <p>Not found.</p>
                    : <ul className="place-list">
                          {places.map((place) => (
                              <li key={place.id}><PlaceCard place={place} /></li>
                          ))}
                      </ul>;

    }