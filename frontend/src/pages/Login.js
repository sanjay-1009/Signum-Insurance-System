import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const login = async () => {

        try {

            const formData = new URLSearchParams();

            formData.append("username", username);
            formData.append("password", password);

            const response = await API.post(
                "/login",
                formData
            );

            console.log("Server Response:", response.data);

            if (
    response.data === "SUCCESS" ||
    response.data.includes("SUCCESS")
) {

    localStorage.setItem(
        "loggedIn",
        "true"
    );

    navigate("/dashboard");

} else {

                alert("Invalid Username or Password");
            }

        } catch (error) {

            console.log(error);
            alert("Login Error");
        }
    };

   return (

<div className="container mt-5">
  <div className="row justify-content-center">

    <div className="col-md-4">

      <div className="card p-4 shadow">

        <h2 className="text-center mb-4">
          Login
        </h2>

        <input
          className="form-control mb-3"
          type="text"
          placeholder="Username"
          onChange={(e) =>
            setUsername(e.target.value)}
        />

        <input
          className="form-control mb-3"
          type="password"
          placeholder="Password"
          onChange={(e) =>
            setPassword(e.target.value)}
        />

        <button
          className="btn btn-primary w-100"
          onClick={login}
        >
          Login
        </button>

      </div>

    </div>

  </div>
</div>

);
}

export default Login;