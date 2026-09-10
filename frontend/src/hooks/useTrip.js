import { useContext } from 'react';
import { TripContext } from '../context/TripContext';

export default function useTrip() {
    const context = useContext(TripContext);
    if (context === null) throw new Error("useTrip must be used within a TripProvider");
    return context;
}