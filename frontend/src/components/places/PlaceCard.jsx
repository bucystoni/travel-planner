
export default function PlaceCard({ place }) {
    return (

        <div className="place-card">
            <p> Name: {place.name} </p>
            <p> Address: {place.address} </p>
            {place.url && <a href={place.url} target="_blank" rel="noopener noreferrer">Website</a>}
        </div>
        )
    }