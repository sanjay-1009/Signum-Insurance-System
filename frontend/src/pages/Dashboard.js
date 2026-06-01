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

        <div style={{
            textAlign:"center",
            marginTop:"30px"
        }}>

            <h1>
                Insurance Claim Processing System
            </h1>

            <h2>Dashboard Statistics</h2>

            <h3>
                Total Policies :
                {stats.totalPolicies}
            </h3>

            <h3>
                Total Claims :
                {stats.totalClaims}
            </h3>

            <h3>
                Approved Claims :
                {stats.approvedClaims}
            </h3>

            <h3>
                Rejected Claims :
                {stats.rejectedClaims}
            </h3>

            <h3>
                Pending Claims :
                {stats.pendingClaims}
            </h3>

            <br/>

            <button
                onClick={() =>
                    navigate("/policy")
                }>
                Add Policy
            </button>

            <br/><br/>

            <button
                onClick={() =>
                    navigate("/claim")
                }>
                Submit Claim
            </button>

            <br/><br/>

            <button
                onClick={() =>
                    navigate("/approval")
                }>
                Approve Claims
            </button>
            <br/><br/>

            <button
    onClick={() =>
        navigate("/reports")
    }
>
    Reports
</button>

        </div>
    );
}

export default Dashboard;