import { Link } from "react-router-dom";

function Navbar() {

    const logout = () => {

        localStorage.removeItem("loggedIn");

        window.location.href = "/";
    };

    return (

        <nav className="navbar navbar-dark bg-dark">

            <div className="container">

                <span className="navbar-brand">
                    Insurance Claim System
                </span>

                <div>

                    <Link
                        className="btn btn-outline-light me-2"
                        to="/dashboard"
                    >
                        Dashboard
                    </Link>

                    <button
                        className="btn btn-danger"
                        onClick={logout}
                    >
                        Logout
                    </button>

                </div>

            </div>

        </nav>
    );
}

export default Navbar;