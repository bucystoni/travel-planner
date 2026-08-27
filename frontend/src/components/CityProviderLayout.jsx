import { Outlet } from "react-router-dom";
import { CityProvider } from "../context/CityProvider.jsx";

export default function CityProviderLayout() {
    return (
        <CityProvider>
            <Outlet />
        </CityProvider>
    );
}