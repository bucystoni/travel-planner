import { useContext } from 'react';
import { CityContext } from '../context/CityContext';

export function useCity() {
    const context = useContext(CityContext);
    if (context === null) throw new Error("useCity must be used within a CityProvider");
    return context;
}