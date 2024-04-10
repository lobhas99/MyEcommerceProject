import axios from "axios";
import { createError, createUrl } from "./utils";

export async function addItemToCart(Cid, id)
  {
    try{
      const url=createUrl("cartitem/cart/"+Cid+"/"+id)
      const response=await axios.post(url)
      return response;
    }
    catch(ex){
      return createError(ex);
    }
  }

  export async function loadAllcartItems(Cid)
  {
    try{
      const url=createUrl("cartitem/cart/"+Cid)
      const response=await axios.get(url)
      console.log(response)
      return response;
    }
    catch(ex){
      return createError(ex);
    }
  }

  export async function updateProductQuantity(qty)
  {
    try{
      const url=createUrl("cartitem/cart/qty")
      const response=await axios.put(url,qty)
      return response;
    }
    catch(ex){
      return createError(ex);
    }
  }

  export async function deleteFromCart(Cid, id)
  {
    try{
      const url=createUrl("cartitem/cart/"+Cid+"/"+id)
      const response=await axios.delete(url)
      return response;
    }
    catch(ex){
      return createError(ex);
    }
  }

  