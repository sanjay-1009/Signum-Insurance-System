import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function Dashboard() {

    const navigate = useNavigate();

    const [stats, setStats] = useState({
        totalPolicies: 0,
        totalClaims: 0,
        approvedClaims: 0,
        rejectedClaims: 0,
        pendingClaims: 0
    });

    useEffect(() => {

        loadStats();

    }, []);

    const loadStats = async () => {

        try {

            const response =
                await API.get("/dashboard");

            setStats(response.data);

        } catch(error) {

            console.log(error);
        }
    };

    return (
  <div className="container mt-5">

    <h1 className="text-center mb-4">
      Insurance Claim Processing System
    </h1>

    <div className="row">

      <div className="col-md-4 mb-3">
        <div className="card text-center">
          <div className="card-body">
            <h5>Total Policies</h5>
            <h2>{stats.totalPolicies}</h2>
          </div>
        </div>
      </div>

      <div className="col-md-4 mb-3">
        <div className="card text-center">
          <div className="card-body">
            <h5>Total Claims</h5>
            <h2>{stats.totalClaims}</h2>
          </div>
        </div>
      </div>

      <div className="col-md-4 mb-3">
        <div className="card text-center">
          <div className="card-body">
            <h5>Approved Claims</h5>
            <h2>{stats.approvedClaims}</h2>
          </div>
        </div>
      </div>

    </div>

    <div className="text-center mt-4">

      <button
        className="btn btn-primary me-2"
        onClick={() => navigate("/policy")}
      >
        Add Policy
      </button>

      <button
        className="btn btn-success me-2"
        onClick={() => navigate("/claim")}
      >
        Submit Claim
      </button>

      <button
        className="btn btn-warning me-2"
        onClick={() => navigate("/approval")}
      >
        Approve Claims
      </button>

      <button
        className="btn btn-info"
        onClick={() => navigate("/reports")}
      >
        Reports
      </button>

    </div>

  </div>
);
}

export default Dashboard;