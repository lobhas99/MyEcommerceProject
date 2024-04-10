import { Link, useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { credentialsActions } from "../React-Redux-Component/credentials-slice";
import { toast } from "react-toastify";

export function NavBar() {
  const navigate = useNavigate();
  const dispatch=useDispatch();

  const onLogout = () => {
    navigate('/');
    window.sessionStorage.clear();
    dispatch(credentialsActions.setCustomerStatus(false));
    dispatch(credentialsActions.setAdminStatus(false));
    dispatch(credentialsActions.setCredentials({}));
    toast.success("Logged Out");
}
  return (
    <nav className="navbar navbar-expand-lg  bg-dark" data-bs-theme="dark">
      <div className="container-fluid">
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link" aria-current="page" to="/home">
                Home
              </Link>
            </li>
            <li>
              <Link className="nav-link" aria-current="page" to="/cart">
                Cart {/* Cart ({cart.items.length}) */}
              </Link>
            </li>
            <li>
              <Link className="nav-link" aria-current="page" to="/orders">
                Orders
              </Link>
            </li>
            <li>
              <button
                onClick={onLogout}
                className="nav-link"
                aria-current="page"
              >
                Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default NavBar;
