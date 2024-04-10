import axios from "axios";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { credentialsActions } from "../React-Redux-Component/credentials-slice";
import { findUserByEmail } from "../services/user";
import { createUrl } from "../services/utils";

function Signin() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [credentials, setCredentials] = useState({
    email: "",
    password: "",
    role: "",
  });

  const OnTextChanged = (args) => {
    var copyOfCredentials = { ...credentials };
    copyOfCredentials[args.target.name] = args.target.value;
    setCredentials(copyOfCredentials);
  };

  const resetCredentials = () => {
    setCredentials({
      email: "",
      password: "",
      role: "",
    });
  };

  const validateInput = () => {
    if (credentials.email === "" || credentials.email === null) {
      toast.error("Email cannot be empty");
      return false;
    }
    if (credentials.password === "" || credentials.password === null) {
      toast.error("Password cannot be empty");
      return false;
    }

    return true;
  };

  var validateLogin = () => {
    const url = createUrl("user/signin");

    axios
      .post(url, credentials)
      .then((response) => {
        var replyReceived = response.data;
        console.log(replyReceived);
        if (replyReceived.mesg.match("successful Authentication")) {
          var tokenReceived = replyReceived.jwt;
          console.log(tokenReceived);
          window.sessionStorage.setItem("jwt", tokenReceived);
          console.log(credentials);
          const role = sessionStorage.getItem("role");
          if (role.match("ROLE_CUSTOMER")) {
            dispatch(credentialsActions.setCustomerStatus(true));
            dispatch(credentialsActions.setAdminStatus(false));
            toast.success("Login Success");
            navigate("/home");
          } else {
            dispatch(credentialsActions.setCustomerStatus(false));
            dispatch(credentialsActions.setAdminStatus(true));
            toast.success("Login Success");
            navigate("/product/add");
          }
        }
      })
      .catch((error) => {
        if (error.message.match("Bad credentials")) toast.error(error.message);
        else toast.error("Internal Error Please Try Again");
        console.log(error);
        resetCredentials();
        dispatch(credentialsActions.setCustomerStatus(false));
        dispatch(credentialsActions.setAdminStatus(false));
      });
  };

  var Login = () => {
    if (!validateInput()) return;
    findUserByEmail(credentials.email).then((response) => {
      sessionStorage.setItem("role", response.data.role);
      sessionStorage.setItem("Cid", response.data.id);
    });
    validateLogin();
  };
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3">
          <div className="card my-5" style={{ background: "#FAF5E6" }}>
            <form className="card-body cardbody-color p-lg-5">
              <div className="mb-3">
                <label htmlFor="email">Email</label>
                <input
                  type="email"
                  required
                  className="form-control"
                  name="email"
                  id="email"
                  placeholder="Email"
                  value={credentials.email}
                  onChange={OnTextChanged}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="password">Password</label>
                <input
                  type="password"
                  className="form-control"
                  name="password"
                  id="password"
                  placeholder="Password"
                  value={credentials.password}
                  onChange={OnTextChanged}
                />
              </div>

              <div className="text-center">
                <button
                  type="button"
                  className="btn btn-primary btn-block mb-4 col-sm-6"
                  onClick={Login}
                >
                  Login
                </button>
              </div>
              <div
                id="emailHelp"
                className="form-text text-center mb-5 text-dark"
              >
                Not Registered?
                <Link style={{ color: "gray", hover: "purple" }} to="/signup">
                  <span className="text-dark fw-bold"> Create an Account</span>
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Signin;
