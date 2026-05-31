import { useState } from "react";
import API from "./services/api";

function App() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const login = async () => {

    try {

      const formData = new URLSearchParams();

      formData.append("username", username);
      formData.append("password", password);

      const response =
        await API.post(
          "/login",
          formData
        );

      alert(response.data);

    } catch(error) {

      alert("Login Error");
      console.log(error);
    }
  };

  return (

    <div style={{
      textAlign:"center",
      marginTop:"100px"
    }}>

      <h1>
        Insurance Claim Processing System
      </h1>

      <h2>Login Page</h2>

      <input
        type="text"
        placeholder="Username"
        onChange={(e)=>
          setUsername(e.target.value)}
      />

      <br /><br />

      <input
        type="password"
        placeholder="Password"
        onChange={(e)=>
          setPassword(e.target.value)}
      />

      <br /><br />

      <button onClick={login}>
        Login
      </button>

    </div>
  );
}

export default App;