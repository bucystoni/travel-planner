import { useState } from "react";
import { CityContext } from './CityContext';

export function CityProvider({children}) {
    const [city, setCity] = useState(null);
    const value = { city, setCity };

    return (
        <CityContext.Provider value={value}>{children}</CityContext.Provider>
    )
}