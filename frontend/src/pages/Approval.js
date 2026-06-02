import { useState } from "react";
import API from "../services/api";

function Approval() {


const [claimId, setClaimId] = useState("");
const [status, setStatus] = useState("");

const [claim, setClaim] =
    useState(null);

const loadClaim = async () => {

    if (Number(claimId) <= 0) {

        alert("Enter Valid Claim ID");
        return;
    }

    try {

        const response =
            await API.get(
                "/getClaim?claimId=" +
                claimId
            );

        if (
            response.data.message ===
            "Claim Not Found"
        ) {

            alert("Claim Not Found");
            setClaim(null);

        } else {

            setClaim(
                response.data
            );
        }

    } catch(error) {

        console.log(error);

        alert(
            "Failed to Load Claim"
        );
    }
};

const updateStatus = async () => {

    if (!claim) {

        alert(
            "Load a claim first"
        );

        return;
    }

    if (status === "") {

        alert(
            "Select Status"
        );

        return;
    }

    try {

        const formData =
            new URLSearchParams();

        formData.append(
            "claimId",
            claimId);

        formData.append(
            "status",
            status);

        const response =
            await API.post(
                "/approveClaim",
                formData
            );

        alert(response.data);

        if (
            response.data.includes(
                "Updated"
            )
        ) {

            setClaimId("");
            setStatus("");
            setClaim(null);
        }

    } catch(error) {

        console.log(error);

        alert(
            "Failed to Update Claim"
        );
    }
};

return (

    <div className="container mt-5">

        <div className="row justify-content-center">

            <div className="col-md-8">

                <div className="card shadow">

                    <div className="card-header bg-warning">

                        <h3>
                            Claim Approval
                        </h3>

                    </div>

                    <div className="card-body">

                        <label className="form-label">
                            Claim ID
                        </label>

                        <div className="d-flex gap-2 mb-3">

                            <input
                                className="form-control"
                                type="number"
                                min="1"
                                value={claimId}
                                onChange={(e) =>
                                    setClaimId(
                                        e.target.value
                                    )
                                }
                            />

                            <button
                                className="btn btn-primary"
                                onClick={
                                    loadClaim
                                }
                            >
                                Load Claim
                            </button>

                        </div>

                        {claim && (

                            <div className="card mb-3 border-info">

                                <div className="card-header bg-info text-white">

                                    Claim Details

                                </div>

                                <div className="card-body">

                                    <p>
                                        <strong>
                                            Policy ID:
                                        </strong>{" "}
                                        {claim.policyId}
                                    </p>

                                    <p>
                                        <strong>
                                            Claimant:
                                        </strong>{" "}
                                        {claim.claimantName}
                                    </p>

                                    <p>
                                        <strong>
                                            Claim Amount:
                                        </strong>{" "}
                                        ₹{claim.claimAmount}
                                    </p>

                                    <p>
                                        <strong>
                                            Incident Date:
                                        </strong>{" "}
                                        {claim.incidentDate}
                                    </p>

                                    <p>
                                        <strong>
                                            Description:
                                        </strong>{" "}
                                        {claim.description}
                                    </p>

                                    <p>
                                        <strong>
                                            Current Status:
                                        </strong>{" "}
                                        {claim.status}
                                    </p>

                                </div>

                            </div>

                        )}

                        <label className="form-label">
                            Update Status
                        </label>

                        <select
                            className="form-select mb-3"
                            value={status}
                            onChange={(e) =>
                                setStatus(
                                    e.target.value
                                )
                            }
                        >

                            <option value="">
                                Select Status
                            </option>

                            <option value="Approved">
                                Approved
                            </option>

                            <option value="Rejected">
                                Rejected
                            </option>

                        </select>

                        <button
                            className="btn btn-warning w-100"
                            onClick={
                                updateStatus
                            }
                        >
                            Update Claim Status
                        </button>

                    </div>

                </div>

            </div>

        </div>

    </div>
);


}

export default Approval;
