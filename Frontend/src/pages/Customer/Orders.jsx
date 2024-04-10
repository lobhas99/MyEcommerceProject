import { useEffect, useState } from "react";
import NavBar from "../../components/NavBar";
import { getAllOrders } from "../../services/order";
import { Table, Container } from "react-bootstrap";
import { Link } from "react-router-dom";

export function Orders() {
  const [orders, setOrders] = useState([]);
  const Cid = sessionStorage.getItem("Cid");

  useEffect(() => {
    fetchOrders();
  }, [Cid]);

  const fetchOrders = async () => {
    const fetchedOrders = await getAllOrders(Cid);
    setOrders(fetchedOrders || []);
  };
  console.log(orders);

  return (
    <>
      <NavBar />
      <div className="container">
        <h1 className="title">Orders</h1>
        <Container className="my-5" style={{ background: "white" }}>
          <Table responsive bordered hover>
            <thead>
              <tr>
                <th>Order ID</th>
                <th>Total Amount (₹)</th>
                <th>Status</th>
                <th>Order Date</th>
                <th>Delivery Date</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {orders &&
                orders.map((order) => (
                  <tr key={order.id}>
                    <td>{order.id}</td>
                    <td>{order.totalAmount}</td>
                    <td>{order.orderStatus}</td>
                    <td>{order.orderDate}</td>
                    <td>{order.deliveryDate}</td>
                    <td>
                      <Link to={`/bill?oId=${order.id}`}>
                        <button className="btn btn-primary">View Bill</button>
                      </Link>
                    </td>
                  </tr>
                ))}
            </tbody>
          </Table>
        </Container>
      </div>
    </>
  );
}
export default Orders;
