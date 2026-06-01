import { useState } from "react";
import API from "../services/api";

function Policy() {

    const [policyName, setPolicyName] = useState("");
    const [policyType, setPolicyType] = useState("");
    const [coverageAmount, setCoverageAmount] = useState("");
    const [premiumAmount, setPremiumAmount] = useState("");

    const addPolicy = async () => {

        try {

            const formData = new URLSearchParams();

            formData.append("policyName", policyName);
            formData.append("policyType", policyType);
            formData.append("coverageAmount", coverageAmount);
            formData.append("premiumAmount", premiumAmount);

            const response = await API.post(
                "/addPolicy",
                formData
            );

            alert(response.data);

        } catch(error) {

            console.log(error);
            alert("Failed to Add Policy");
        }
    };

    return (

        <div style={{
            textAlign: "center",
            marginTop: "50px"
        }}>

            <h1>Add Policy</h1>

            <input
                type="text"
                placeholder="Policy Name"
                onChange={(e) =>
                    setPolicyName(e.target.value)}
            />

            <br /><br />

            <input
                type="text"
                placeholder="Policy Type"
                onChange={(e) =>
                    setPolicyType(e.target.value)}
            />

            <br /><br />

            <input
                type="number"
                placeholder="Coverage Amount"
                onChange={(e) =>
                    setCoverageAmount(e.target.value)}
            />

            <br /><br />

            <input
                type="number"
                placeholder="Premium Amount"
                onChange={(e) =>
                    setPremiumAmount(e.target.value)}
            />

            <br /><br />

            <button onClick={addPolicy}>
                Add Policy
            </button>

        </div>
    );
}

export default Policy;