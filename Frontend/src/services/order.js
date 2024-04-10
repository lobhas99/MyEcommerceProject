import axios from "axios";
import { createError, createUrl } from "./utils";

export async function placeOrder(Cid) {
  try {
    const url = createUrl("customer/orders/" + Cid);
    const response = await axios.post(url);
    return response.data;
  } catch (ex) {
    return createError(ex);
  }
}

export async function getAllOrders(Cid) {
  try {
    const url = createUrl("customer/orders/" + Cid);
    const response = await axios.get(url);
    return response.data;
  } catch (ex) {
    return createError(ex);
  }
}

export async function getAllOrderProducts(oId){
  try {
    const url=createUrl("customer/orders/product/"+oId);
    const response=await axios.get(url);
    return response.data;
  } catch (ex) {
    return createError(ex)
  }
}