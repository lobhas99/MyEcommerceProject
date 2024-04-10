import { useDispatch, useSelector } from "react-redux";
import { Route } from "react-router-dom";
import Popup from "./Popup";

function UserProtectedRoutes(props) {
    const isCustomer = useSelector((state) => state.credential.isCustomer);
    const isAdmin = useSelector((state) => state.credential.isAdmin);

    const dispatch = useDispatch();
    var token = window.sessionStorage.getItem("jwt");

    var onClose = () => {
        setTimeout(() => { window.location.replace('/signin') }, 5000);
        return true;
    }


    if (token !== null && token !== undefined && (isCustomer || isAdmin)) {
        return <Route path={props.path} exact
            component={props.component} />
    }
    else {
        return <Popup onClose={onClose} />
    }
}

export default UserProtectedRoutes;