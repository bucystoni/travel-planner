
import { Link, useNavigate } from "react-router-dom";
import useAuth from "../hooks/useAuth.js";
import useTrip from "../hooks/useTrip.js";

export default function NavBar() {
    const { token, logout } = useAuth();
    const { clearTrip } = useTrip();
    const navigate = useNavigate();

    function handleLogout() {
        logout();
        clearTrip();
        navigate("/destinations");
    }

    return (
        <div>
            <nav>
                {token ? (
                    <>
                        <Link to="/trips">My trips</Link>
                        <button onClick={handleLogout}>Logout</button>
                    </>
                ) : (
                    <>
                        <Link to="/registration">Register</Link>
                        <Link to="/login">Login</Link>
                    </>
                )}
                <Link to={"/flights"}>Flights</Link>
                <Link to={"/accommodations"}>Accommodations</Link>
                <Link to={"/restaurants"}>Restaurants</Link>
                <Link to={"/sights"}>Sights</Link>
            </nav>
        </div>
    )
};