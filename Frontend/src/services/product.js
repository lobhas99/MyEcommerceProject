import axios from "axios";
import { createError, createUrl } from "./utils";

export async function getAllProducts() {
  try {
    const url = createUrl("admin/product/all");
    const headers = {
      headers: {
        token: sessionStorage["token"],
      },
    };
    const response = await axios.get(url, headers);
    return response;
  } catch (ex) {
    return createError(ex);
  }
}

export async function addProduct(isbn,title,description,author,price,releasedDate,stock,category
  ){
  try{
    const url=createUrl("admin/product/add")
    const body={
      isbn,title,description,author,price,releasedDate,stock,category
    }
    // const headers={
    //   headers:
    //   {
    //     token: sessionStorage["token"],
    //   },
    // };
    const response=await axios.post(url,body)
    return response;
  }
    catch(ex){
      return createError(ex);
    }
  }

  export async function addProductImg(data,id)
  {
    try{
      const url=createUrl("admin/product/images/"+id)
       const headers={
        'Content-Type': "multipart/form-data"
      }
      const response=await axios.post(url,data,headers)
      return response;
    }
    catch(ex){
      return createError(ex);
    }
  }

  