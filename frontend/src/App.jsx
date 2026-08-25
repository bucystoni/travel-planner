import { Route, Routes} from "react-router-dom";
import RegisterPage from "./pages/RegisterPage.jsx";
import LoginPage from "./pages/LoginPage.jsx";
import FlightsPage from "./pages/FlightsPage.jsx";
import AccommodationsPage from "./pages/AccommodationsPage.jsx";
import SightsPage from "./pages/SightsPage.jsx";
import NotFoundPage from "./pages/NotFoundPage.jsx";
import RestaurantsPage from "./pages/RestaurantsPage.jsx";
import LandingPage from "./pages/LandingPage.jsx";
import Layout from "./components/Layout.jsx";
import {CityProvider} from "./context/CityProvider.jsx";

function App() {


  return (
    <>
        <CityProvider>
        <Routes>
            <Route element={<Layout />}>
                <Route index element={<LandingPage />} />
                <Route path={"/registration"} element={<RegisterPage />} />
                <Route path={"/login"} element={<LoginPage />} />
                <Route path={"/destinations"} element={<LandingPage/>} />
                <Route path={"/flights"} element={<FlightsPage/>}/>
                <Route path={"/accommodations"} element={<AccommodationsPage/>} />
                <Route path={"/restaurants"} element={<RestaurantsPage/>} />
                <Route path={"/sights"} element={<SightsPage/>} />
                <Route path={"*"} element={<NotFoundPage />} />
            </Route>
        </Routes>
        </CityProvider>

    </>
  )
}

export default App
