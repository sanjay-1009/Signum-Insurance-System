import { useState } from "react";
import API from "../services/api";

function Claim() {

    const [policyId, setPolicyId] = useState("");
    const [claimantName, setClaimantName] = useState("");
    const [claimAmount, setClaimAmount] = useState("");
    const [incidentDate, setIncidentDate] = useState("");
    const [description, setDescription] = useState("");

    const submitClaim = async () => {

        try {

            const formData = new URLSearchParams();

            formData.append("policyId", policyId);
            formData.append("claimantName", claimantName);
            formData.append("claimAmount", claimAmount);
            formData.append("incidentDate", incidentDate);
            formData.append("description", description);

            const response = await API.post(
                "/submitClaim",
                formData
            );

            alert(response.data);

        } catch(error) {

            console.log(error);
            alert("Failed to Submit Claim");
        }
    };

    return (

        <div style={{
            textAlign: "center",
            marginTop: "50px"
        }}>

            <h1>Submit Claim</h1>

            <input
                type="number"
                placeholder="Policy ID"
                onChange={(e)=>
                    setPolicyId(e.target.value)}
            />

            <br /><br />

            <input
                type="text"
                placeholder="Claimant Name"
                onChange={(e)=>
                    setClaimantName(e.target.value)}
            />

            <br /><br />

            <input
                type="number"
                placeholder="Claim Amount"
                onChange={(e)=>
                    setClaimAmount(e.target.value)}
            />

            <br /><br />

            <input
                type="date"
                onChange={(e)=>
                    setIncidentDate(e.target.value)}
            />

            <br /><br />

            <textarea
                placeholder="Description"
                rows="4"
                cols="40"
                onChange={(e)=>
                    setDescription(e.target.value)}
            />

            <br /><br />

            <button onClick={submitClaim}>
                Submit Claim
            </button>

        </div>
    );
}

export default Claim;