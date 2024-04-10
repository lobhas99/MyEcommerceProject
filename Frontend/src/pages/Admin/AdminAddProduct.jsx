/* eslint-disable eqeqeq */
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { addProduct } from "../../services/product";
import AdminNavBar from "../../components/AdminNavBar";


export function AdminAddProduct() {
  const [isbn, setIsbn] = useState("");
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [author, setAuthor] = useState("");
  const [price, setPrice] = useState("");
  const [stock, setStock] = useState("");
  const [category, setCategory] = useState("");
  const [releasedDate, setReleasedDate] = useState("");
 
  const navigate = useNavigate();
  
   const onAddProduct = async () => {
    if (isbn.length === 0) {
      toast.warn("Enter ISBN");
    } else if (title.length === 0) {
      toast.warn("Enter Title");
    } else if (description.length === 0) {
      toast.warn("Enter the Description");
    } else if (author.length === 0) {
      toast.warn("Enter Author");
    } else if (stock.length === 0) {
      toast.warn("Enter Stock");
    } else if (price.length === 0) {
      toast.warn("Enter Price");
    } else {
      const result = await addProduct(
        isbn,
        title,
        description,
        author,
        price,
        releasedDate,
        stock,category
      );
      console.log(result);
      if (result.status=='201') {
        const id=result.data.id;
        console.log(id)
        toast.success("Successfully Added the Product");
        navigate("/addproductimg?id=" + id)
      } else toast.error(result["error"]);
    }
  };
  return (
    <>
        <AdminNavBar />
      <h1 className="title">Add Product</h1>
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <div className="form">
            <div className="mb-3">
              <label htmlFor="">Isbn</label>
              <input
                onChange={(e) => {
                  setIsbn(e.target.value);
                }}
                type="text"
                placeholder="Book ISBN"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Title</label>
              <input
                onChange={(e) => {
                  setTitle(e.target.value);
                }}
                type="text"
                placeholder="Book Title"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Description</label>
              <input
                onChange={(e) => {
                  setDescription(e.target.value);
                }}
                type="text"
                placeholder="Enter Book Description"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Author</label>
              <input
                onChange={(e) => {
                  setAuthor(e.target.value);
                }}
                type="text"
                placeholder="Name of Author"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Price</label>
              <input
                onChange={(e) => {
                  setPrice(e.target.value);
                  
                }}
                type="number"
                placeholder="Book Price"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Stock</label>
              <input
                onChange={(e) => {
                  setStock(e.target.value);
                }}
                type="number"
                placeholder="Book Stock"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="">Released Date</label>
              <input
                onChange={(e) => {
                  setReleasedDate(e.target.value);
                }}
                type="date"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="category"></label>
              <select
                name="category"
                id="category"
                placeholder="Book Category"
                value={category}
                onChange={(e) => setCategory(e.target.value)}
              >
                <option value="" disabled selected>
                  Select The Book Category
                </option>
                <option value="FICTION">FICTION</option>
                <option value="HORROR">HORROR</option>
                <option value="FANTASY">FANTASY</option>
                <option value="MANGA">MANGA</option>
                <option value="NON_FICTION">SELFHELP</option>
              </select>
            </div>
            <div className="mb-3">
              <div>
                Already have an Account?<Link to="/">SignIn Here</Link>
              </div>
              <button onClick={onAddProduct} className="btn btn-primary mt-2">
                Add Product
              </button>
            </div>
          </div>
        </div>
        <div className="col"></div>
      </div>
    </>
  );
}
export default AdminAddProduct;
