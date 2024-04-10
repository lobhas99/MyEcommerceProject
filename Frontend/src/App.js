import { Route, Routes } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AdminAddProduct from "./pages/Admin/AdminAddProduct";
import AdminAddProductImage from "./pages/Admin/AdminAddProductImage";
import Cart from "./pages/Customer/Cart";
import Home from "./pages/Customer/Home";
import Orders from "./pages/Customer/Orders";
import Signin from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import AdminAllOrdersDisplay from "./pages/Admin/AdminAllOrdersDisplay";
import AdminAllCustomerDisplay from "./pages/Admin/AdminAllCustomersDisplay";
import AdminDashboard from "./pages/Admin/AdminDashboard";
import Bill from "./pages/Customer/Bill";

function App() {
  return (
    <div className="container-fluid">
      <Routes>
        <Route index element={<Signin />} />
        <Route path="/" element={<Signin />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/home" element={<Home />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/orders" element={<Orders />} />
        <Route path="/product/add" element={<AdminAddProduct />} />
        <Route path="/addproductimg" element={<AdminAddProductImage />} />
        <Route path="/admin/orders" element={<AdminAllOrdersDisplay />} />
        <Route path="/admin/customers" element={<AdminAllCustomerDisplay />} />
        <Route path="/admin/dashboard" element={<AdminDashboard />} />
        <Route path="/bill" element={<Bill />} />
      </Routes>
      <ToastContainer />
    </div>
  );
}

export default App;
