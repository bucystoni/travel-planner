import { Link } from "react-router-dom";

export default function NavBar() {

    return (
        <div>
            <nav>
                <Link to={"/registration"}>Register</Link>
                <Link to={"/login"}>Login</Link>
                <Link to={"/flights"}>Flights</Link>
                <Link to={"/accommodations"}>Accommodations</Link>
                <Link to={"/restaurants"}>Restaurants</Link>
                <Link to={"/sights"}>Sights</Link>
            </nav>
        </div>
    )
};