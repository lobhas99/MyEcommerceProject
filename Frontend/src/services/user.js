import axios from "axios";
import { createUrl, createError } from "./utils";
export async function signupUser(
  firstName,
  lastName,
  email,
  password,
  mobileNo,
  birthDate
) {
  try {
    const url = createUrl("user/signup");
    const body = {
      firstName,
      lastName,
      email,
      password,
      mobileNo,
      birthDate,
    };
    const response = await axios.post(url, body);
    return response;
  } catch (ex) {
    return createError(ex);
  }
}

export async function findUserByEmail(email) {
  try {
    const url = createUrl("api/user/" + email);
    const response=await axios.get(url)
    return response;
  } catch (ex) {
    return createError(ex);
  }
}
