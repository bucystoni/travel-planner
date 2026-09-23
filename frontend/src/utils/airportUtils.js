import airports from "../data/airports_with_iata.json";

export function findAirports(city) {
    const airportList = Object.values(airports);

    return airportList
        .filter(
            airport =>
                airport.city.toLowerCase().includes(city.toLowerCase())
        )
        .map(airport => ({
            name: airport.name,
            city: airport.city,
            iata: airport.iata
        }));
}

export function findCityByIata(iata) {
    const airport = Object.values(airports).find((a) => a.iata === iata);

    return airport ? airport.city : null;
}