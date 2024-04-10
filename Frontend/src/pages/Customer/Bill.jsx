import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import NavBar from "../../components/NavBar";
import { getAllOrderProducts } from "../../services/order";

export function Bill() {
  const [products, setBooks] = useState([]);
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const oId = queryParams.get("oId");

  useEffect(() => {
    fetchProducts();
  }, [oId]);

  const fetchProducts = async () => {
    const fetchedProducts = await getAllOrderProducts(oId);
    console.log(fetchedProducts);
    setBooks(fetchedProducts || []);
  };
  return (
    <>
      <NavBar />
      <div className="container my-5">
        <div
          className="card card-1"
          style={{ maxWidth: "600px", margin: "auto" }}
        >
          <div className="card-header bg-white">
            <div className="row justify-content-between">
              <div className="col-8">
                <h4 className="mb-0" style={{ marginTop: 15 }}>
                  Thanks for your Order,{" "}
                  <span className="change-color">
                    {sessionStorage.getItem("firstName")}
                  </span>
                  !
                </h4>
              </div>
            </div>
          </div>

          <div className="col-auto" style={{ marginTop: 15 }}>
            {" "}
            <h4
              className="color-1 mb-0 change-color ml-2"
              style={{ marginLeft: "15px" }}
            >
              Receipt
            </h4>{" "}
          </div>
          <div className="card-body">
            {products.map((orderItem, index) => (
              <div key={index} className="card mb-3">
                <div className="row mb-3">
                  <div className="col-3">
                    <img
                      className="img-fluid"
                      src={"data:image/jpg;base64," + orderItem.product.image}
                      alt={orderItem.product.title}
                      style={{ width: "80px", height: "90px" }}
                    />
                  </div>

                  <div  className="col-3 mt-3">
                    <h5>{orderItem.product.title}</h5>
                  </div>
                  <div className="col-2 mt-3">
                    <p>Price: &#8377;{orderItem.product.price}</p>
                  </div>
                  <div className="col-2 mt-3">
                    <p>Quantity: {orderItem.quantity}</p>
                  </div>
                  <div className="col-2 mt-3">
                    <p>
                      Subtotal: {orderItem.quantity * orderItem.product.price}
                    </p>
                  </div>
                </div>
              </div>
            ))}

            <div className="col">
              <div className="row justify-content-between">
                <div className="col-auto">
                  <p className="mb-1 text-dark">
                    <b>Order Details</b>
                  </p>
                </div>
                <div className="flex-sm-col text-right col">
                  {" "}
                  <p className="mb-1">
                    <b>Total</b>
                  </p>{" "}
                </div>
                <div className="flex-sm-col col-auto">
                  {" "}
                  <p className="mb-1">
                    &#8377;
                    {products.reduce(
                      (total, orderItem) =>
                        total + orderItem.product.price * orderItem.quantity,
                      0
                    )}
                  </p>{" "}
                </div>
              </div>
              <div className="row justify-content-between">
                <div className="flex-sm-col text-right col">
                  <p className="mb-1">
                    <b>Delivery Charges</b>
                  </p>
                </div>
                <div className="flex-sm-col col-auto">
                  <p className="mb-1">&#8377;50</p>
                </div>
              </div>
            </div>
          </div>
          <div className="card-footer">
            <div className="jumbotron-fluid">
              <div className="row justify-content-between">
                <div className="col-auto my-auto">
                  <h2 className="mb-0">TOTAL PAID</h2>
                </div>
                <div className="col-auto my-auto ml-auto">
                  <h1 className="display-3">
                    &#8377;{" "}
                    {products.reduce(
                      (total, orderItem) =>
                        total + orderItem.product.price * orderItem.quantity,
                      50
                    )}
                  </h1>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Bill;
