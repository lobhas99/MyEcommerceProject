import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import NavBar from "../../components/NavBar";
import {
  deleteFromCart,
  loadAllcartItems,
  updateProductQuantity,
} from "../../services/Cart";
import { placeOrder } from "../../services/order";
import { toast } from "react-toastify";

export function Cart() {
  const navigate = useNavigate();
  const Cid = sessionStorage.getItem("Cid");
  var [cart, setCart] = useState([]);
  const [quantities, setQuantities] = useState({});
  useEffect(() => {
    fetchCart();
  }, [Cid]);

  const fetchCart = async () => {
    const cartItems = await loadAllcartItems(Cid);
    setCart(cartItems.data || []);
    const initialQuantities = {};
    cartItems.data.forEach((item) => {
      initialQuantities[item.id] = item.quantity; // Default quantity is 1
    });
    setQuantities(initialQuantities);
  };
  const setProductQuantity = async (cartItem, newValue) => {
    const qty = {
      productId: cartItem.product.id,
      userId: Cid,
      quantity: newValue,
    };
    await updateProductQuantity(qty);
  };
  const handleQuantityChange = (itemId, newQuantity) => {
    setQuantities((prevQuantities) => ({
      ...prevQuantities,
      [itemId]: newQuantity,
    }));
  };

  const removeProduct = async (Cid, id) => {
    const result = await deleteFromCart(Cid, id);
    console.log(result.data.message);
    if (result.data.message === "Product removed from the cart")
      window.location.reload();
  };

  const bookOrder = async (Cid) => {
    const result = await placeOrder(Cid);
    if(result.id!=null){
      
      toast.success("Order has been Placed Successsfully")
      navigate("/orders")
    }

  else
  toast.error("Insufficent Stock!!!")

    //  // placeOrder(Cid).then((response) => {
    //     console.log(response);
    //     console.log(response.orderId)
    //     toast.success("Order Placed Successfully", {
    //       position: 'top-right',
    //       autoClose: 2000,
    //       hideProgressBar: false,
    //       closeOnClick: true,
    //       pauseOnHover: true,
    //       draggable: true,
    //       progress: undefined,
    //     })
    //     setTimeout(() => history.push(`/bill?oId=${response.orderId}`), 2000);
    // };
  };
  if (cart.length === 0)
   return(
  <>
  <NavBar/>
  <div className="container">
  <h1 style={{textAlign:"center"}}>The Cart is Empty </h1>
  {/* <img src="../assets/cart.jpg" height={"100px"} width={"100px"} alt="pic of cart" /> */}
  </div>
    
  </>
  )

  ;
  else {
    return (
      <>
        <NavBar />
        <div className="container">
          <h1>Your Cart</h1>
          {cart.map((orderItem, index) => (
            <div
              key={index}
              className="card mb-3"
              style={{ backgroundColor: "white" }}
            >
              <div className="row">
                <div className="col">
                  <img
                    className="img-fluid"
                    src={"data:image/jpg;base64," + orderItem.product.image}
                    alt={orderItem.product.title}
                    style={{
                      width: "90px",
                      marginLeft: 20,
                      marginTop: 10,
                      marginBottom: 10,
                    }}
                  />
                </div>

                <div className="col">
                  <h5 style={{ marginTop: "50px" }}>
                    {orderItem.product.title}
                  </h5>
                </div>

                <div className="col">
                  <p style={{ marginTop: "60px" }}>
                    Price: &#8377; {orderItem.product.price}
                  </p>
                </div>

                <div className="col">
                  <p style={{ marginLeft: "-10px", marginTop: "25px" }}>
                    Quantity
                  </p>
                  <select
                    id={`quantity-${orderItem.id}`} // Unique id for each select element
                    value={quantities[orderItem.id]} // Set value from state
                    onChange={(e) => {
                      handleQuantityChange(
                        orderItem.id,
                        parseInt(e.target.value)
                      );
                      setProductQuantity(orderItem, parseInt(e.target.value));
                    }}
                  >
                    {[1, 2, 3].map((value) => (
                      <option
                        key={value}
                        value={value}
                        selected={quantities[orderItem.id] === value}
                      >
                        {value}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="col">
                  <button
                    type="button"
                    style={{ marginTop: "50px" }}
                    className="btn btn-danger "
                    onClick={() => removeProduct(Cid, orderItem.product.id)}
                  >
                    Remove
                  </button>
                </div>
              </div>
            </div>
          ))}
          <div className="buy-btn">
            <button
              type="button"
              className="btn btn-success"
              onClick={() => bookOrder(Cid)}
            >
              Buy Now
            </button>
          </div>
        </div>
      </>
    );
  }
}
export default Cart;
