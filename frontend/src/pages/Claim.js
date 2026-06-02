import { useState } from "react";
import API from "../services/api";

function Claim() {


const [policyId, setPolicyId] = useState("");
const [claimantName, setClaimantName] = useState("");
const [claimAmount, setClaimAmount] = useState("");
const [incidentDate, setIncidentDate] = useState("");
const [description, setDescription] = useState("");

const submitClaim = async () => {

    if (Number(policyId) <= 0) {

        alert("Policy ID must be positive");
        return;
    }

    if (claimantName.trim() === "") {

        alert("Enter Claimant Name");
        return;
    }

    if (Number(claimAmount) <= 0) {

        alert("Claim Amount must be greater than 0");
        return;
    }

    if (incidentDate === "") {

        alert("Select Incident Date");
        return;
    }

    if (description.trim() === "") {

        alert("Enter Description");
        return;
    }

    try {

        const formData =
            new URLSearchParams();

        formData.append(
            "policyId",
            policyId);

        formData.append(
            "claimantName",
            claimantName);

        formData.append(
            "claimAmount",
            claimAmount);

        formData.append(
            "incidentDate",
            incidentDate);

        formData.append(
            "description",
            description);

        const response =
            await API.post(
                "/submitClaim",
                formData
            );

        alert(response.data);

        if (
            response.data.includes(
                "Successfully"
            )
        ) {

            setPolicyId("");
            setClaimantName("");
            setClaimAmount("");
            setIncidentDate("");
            setDescription("");
        }

    } catch(error) {

        console.log(error);

        alert(
            "Failed to Submit Claim"
        );
    }
};

return (

    <div className="container mt-5">

        <div className="row justify-content-center">

            <div className="col-md-6">

                <div className="card shadow">

                    <div className="card-header bg-success text-white">

                        <h3>
                            Submit Claim
                        </h3>

                    </div>

                    <div className="card-body">

                        <label className="form-label">
                            Policy ID
                        </label>

                        <input
                            className="form-control mb-3"
                            type="number"
                            min="1"
                            value={policyId}
                            onChange={(e) =>
                                setPolicyId(
                                    e.target.value
                                )
                            }
                        />

                        <label className="form-label">
                            Claimant Name
                        </label>

                        <input
                            className="form-control mb-3"
                            type="text"
                            value={claimantName}
                            onChange={(e) =>
                                setClaimantName(
                                    e.target.value
                                )
                            }
                        />

                        <label className="form-label">
                            Claim Amount
                        </label>

                        <input
                            className="form-control mb-3"
                            type="number"
                            min="1"
                            value={claimAmount}
                            onChange={(e) =>
                                setClaimAmount(
                                    e.target.value
                                )
                            }
                        />

                        <label className="form-label">
                            Incident Date
                        </label>

                        <input
                            className="form-control mb-3"
                            type="date"
                            value={incidentDate}
                            onChange={(e) =>
                                setIncidentDate(
                                    e.target.value
                                )
                            }
                        />

                        <label className="form-label">
                            Description
                        </label>

                        <textarea
                            className="form-control mb-3"
                            rows="4"
                            value={description}
                            onChange={(e) =>
                                setDescription(
                                    e.target.value
                                )
                            }
                        />

                        <button
                            className="btn btn-success w-100"
                            onClick={submitClaim}
                        >
                            Submit Claim
                        </button>

                    </div>

                </div>

            </div>

        </div>

    </div>
);


}

export default Claim;
