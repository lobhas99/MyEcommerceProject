import NavBar from "../../components/NavBar";
import "./EmptyCart.css";

export function EmptyCart() {
  return (<>
  <body>
    
    <NavBar/>
    <div>
      <h1 style={{ textAlign: "center" }}>The Cart is Empty!!!!!</h1>
    </div>
  </body>
  </>
  );
}

export default EmptyCart;
