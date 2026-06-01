import { useState } from "react";
import API from "../services/api";

function Approval() {

    const [claimId, setClaimId] = useState("");
    const [status, setStatus] = useState("Approved");

    const updateStatus = async () => {

        try {

            const formData = new URLSearchParams();

            formData.append("claimId", claimId);
            formData.append("status", status);

            const response = await API.post(
                "/approveClaim",
                formData
            );

            alert(response.data);

        } catch(error) {

            console.log(error);
            alert("Failed to Update Claim");
        }
    };

    return (

        <div
            style={{
                textAlign: "center",
                marginTop: "50px"
            }}
        >

            <h1>Claim Approval</h1>

            <input
                type="number"
                placeholder="Claim ID"
                onChange={(e)=>
                    setClaimId(e.target.value)}
            />

            <br /><br />

            <select
                onChange={(e)=>
                    setStatus(e.target.value)}
            >

                <option value="Approved">
                    Approved
                </option>

                <option value="Rejected">
                    Rejected
                </option>

            </select>

            <br /><br />

            <button onClick={updateStatus}>
                Update Status
            </button>

        </div>
    );
}

export default Approval;