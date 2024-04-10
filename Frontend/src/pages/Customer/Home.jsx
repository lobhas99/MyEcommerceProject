import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import NavBar from "../../components/NavBar";
import { addItemToCart } from "../../services/Cart";
import { getAllProducts } from "../../services/product";
import { useNavigate } from "react-router-dom";
// import { useDispatch } from "react-redux";

function Product({ item }) {
  //const dispatch = useDispatch();
  const navigate=useNavigate();
  const Cid = sessionStorage.getItem("Cid");
  const onAddItemToCart = async () => {
    if (Cid == null) {
      toast.error("Please Login First");
      navigate('/')
    } else {
      await addItemToCart(Cid, item.id).then((response) => {
        toast.success(response.data.message);
      });
    }
  };

  return (
    <div className="card" style={{ height: 350 }}>
      <div style={{ textAlign: "center" }}>
        <img
          style={{ width: 150 ,marginTop:"10px"}}
          className="card-img-top"
          src={"data:image/jpg;base64," + item.image}
          alt=""
        />
      </div>
      <div className="card-body">
        <div style={{ fontWeight: "bold", fontSize: 19 ,textAlign:"center"}}>{item.title}</div>
        <div style={{ textAlign: "center" }}>
          Price:{" "}
          <span style={{ fontWeight: "bold", fontSize: 17 }}>
            ₹{item.price}
          </span>
        </div>
        <div className="mt-2" style={{ textAlign: "center" }}>
          <button
            onClick={() => onAddItemToCart(Cid, item.id)}
            className="btn btn-success btn-sm"
          >
            Add
          </button>
        </div>
      </div>
    </div>
  );
}

export function Home() {
  const [items, setItems] = useState([]);

  const loadAllProducts = async () => {
    const result = await getAllProducts();
    if (result != null)
     setItems(result["data"]);
    else {
      toast.error("No Products Found");
    }
  };
  useEffect(() => {
    loadAllProducts();
  }, []);

  return (
    <>
      <NavBar />
      <div className="container">
        <h1 className="title">Home</h1>

        <div className="row">
          {items.map((item) => {
            return (
              <div key={item["id"]} className="col">
                <Product item={item} />
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
}

export default Home;
