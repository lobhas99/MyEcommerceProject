import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { addProductImg } from "../../services/product";

export function AdminAddProductImage() {
  const [image, setImage] = useState("");
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const id = queryParams.get("id");
  console.log(id);
  const navigate = useNavigate();
  const saveBookImgInfo = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("image", image);

    const result=addProductImg(formData, id).then((response) => {
      console.log('Book Image added successfully', response);
      toast.success('Book image uploaded successfully', {
        position: 'top-right',
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
      navigate('/product/add')

    })
    .catch((error) => {
      alert(error);
      console.error('Error uploading image:', error);
    });
    console.log(result);
      if (result!=null) {
        toast.success("Successfully Added the Image")
        navigate('/')
      } else toast.error(result["error"]);
    
  };

  return (
    <div className="container">
      <h1 className="logo-font">Pustakam</h1>
      <div className="form signup-form">
        <h2 className="form-title">Add Product Image</h2>
        <form
          method="POST"
          className="register-form"
          id="register-form"
          onSubmit={saveBookImgInfo}
        >
          <div className="form-group">
            <label htmlFor="image"></label>
            <input
              type="file"
              accept="image/*"
              name="image"
              id="image"
              onChange={(e) => setImage(e.target.files[0])}
            />
          </div>
          <button type="submit" className="btn btn-success">
            Add Book Image
          </button>
        </form>
      </div>
    </div>
  );
}

export default AdminAddProductImage;
