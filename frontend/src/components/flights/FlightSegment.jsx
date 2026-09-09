import { formatDepartureArrivalTime, formatDuration } from "../../utils/flightUtils.js";

export default function FlightSegment({ segment }) {


    return (
            <div className="segment">
                <p> Carrier:  {segment.carrier} </p>
                <p> Flight number : {segment.flightNumber} </p>
                <p> Aircraft: {segment.aircraft} </p>
                <p> Departure: {segment.departureAirport} - {formatDepartureArrivalTime(segment.departureTime)} </p>
                <p> Arrival: {segment.arrivalAirport} - {formatDepartureArrivalTime(segment.arrivalTime)}</p>
                <p> Duration: {formatDuration(segment.durationMinutes)} </p>
            </div>
        )

    }