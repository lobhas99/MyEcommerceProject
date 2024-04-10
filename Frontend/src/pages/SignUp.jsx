import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { signupUser } from "../services/user";

export function Signup() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [birthDate, setbirthDate] = useState("");
  const [mobileNo, setMobileNo] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const navigate=useNavigate()

  const onSignup = async () => {
    if (firstName.length === 0) {
      toast.warn("Enter First Name");
    } else if (lastName.length === 0) {
      toast.warn("Enter Last Name");
    } else if (mobileNo.length === 0) {
      toast.warn("Enter Mobile No");
    } else if (email.length === 0) {
      toast.warn("Enter Email");
    } else if (password.length === 0) {
      toast.warn("Enter Password");
    } else if (password !== confirmPassword) {
      toast.error("Password Does Not Match");
    } else {
      const result = await signupUser(
        firstName,
        lastName,
        email,
        password,
        mobileNo,
        birthDate
      )
      console.log(result);
      if (result.status=='201') {
        toast.success("Successfully Registered the User")
        navigate('/')
      } else toast.error(result["error"]);
    }
  };
  return (
    <div className="container">
      <h1 className="title">Sign Up</h1>
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <div className="form">
            <div className="mb-3">
              <label htmlFor="">First Name</label>
              <input
                onChange={(e) => {
                  setFirstName(e.target.value);
                }}
                type="text"
                placeholder="John"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Last Name</label>
              <input
                onChange={(e) => {
                  setLastName(e.target.value);
                }}
                type="text"
                placeholder="Doe"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Birth Date</label>
              <input
                onChange={(e) => {
                  setbirthDate(e.target.value);
                }}
                type="date"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Mobile No</label>
              <input
                onChange={(e) => {
                  setMobileNo(e.target.value);
                }}
                type="text"
                placeholder="9999999999"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Email</label>
              <input
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
                type="email"
                placeholder="abc@gmail.com"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Password</label>
              <input
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
                type="password"
                placeholder="********"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Confirm Password</label>
              <input
                onChange={(e) => {
                  setConfirmPassword(e.target.value);
                }}
                type="password"
                placeholder="********"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <div>
                Already have an Account?<Link to="/">SignIn Here</Link>
              </div>
              <button onClick={onSignup} className="btn btn-primary mt-2">
                Sign Up
              </button>
            </div>
          </div>
        </div>
        <div className="col"></div>
        </div>
      </div>
  );
}
export default Signup;
