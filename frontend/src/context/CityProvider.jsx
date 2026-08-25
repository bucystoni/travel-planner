import {useEffect, useState} from "react";
import { CityContext } from './CityContext';

export function CityProvider({children}) {
    const [city, setCity] = useState(null);
    const value = { city, setCity };

    useEffect(() => console.log(city), [city]);

    return (
        <CityContext.Provider value={value}>{children}</CityContext.Provider>
    )
}