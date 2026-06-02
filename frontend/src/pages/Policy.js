import { useState } from "react";
import API from "../services/api";

function Policy() {


const [policyName, setPolicyName] = useState("");
const [policyType, setPolicyType] = useState("");
const [coverageAmount, setCoverageAmount] = useState("");
const [premiumAmount, setPremiumAmount] = useState("");

const addPolicy = async () => {

    if (policyName.trim() === "") {

        alert("Enter Policy Name");
        return;
    }

    if (policyType.trim() === "") {

        alert("Enter Policy Type");
        return;
    }

    if (Number(coverageAmount) <= 0) {

        alert(
            "Coverage Amount must be greater than 0"
        );

        return;
    }

    if (Number(premiumAmount) <= 0) {

        alert(
            "Premium Amount must be greater than 0"
        );

        return;
    }

    try {

        const formData =
            new URLSearchParams();

        formData.append(
            "policyName",
            policyName);

        formData.append(
            "policyType",
            policyType);

        formData.append(
            "coverageAmount",
            coverageAmount);

        formData.append(
            "premiumAmount",
            premiumAmount);

        const response =
            await API.post(
                "/addPolicy",
                formData
            );

        alert(response.data);

        if (
            response.data.includes(
                "Successfully"
            )
        ) {

            setPolicyName("");
            setPolicyType("");
            setCoverageAmount("");
            setPremiumAmount("");
        }

    } catch(error) {

        console.log(error);

        alert(
            "Failed to Add Policy"
        );
    }
};

return (

    <div className="container mt-5">

        <div className="row justify-content-center">

            <div className="col-md-6">

                <div className="card shadow">

                    <div className="card-header bg-primary text-white">

                        <h3>
                            Add Policy
                        </h3>

                    </div>

                    <div className="card-body">

                        <label className="form-label">
                            Policy Name
                        </label>

                        <input
                            className="form-control mb-3"
                            type="text"
                            value={policyName}
                            onChange={(e) =>
                                setPolicyName(
                                    e.target.value
                                )
                            }
                        />

                        <label className="form-label">
                            Policy Type
                        </label>

                        <input
                            className="form-control mb-3"
                            type="text"
                            value={policyType}
                            onChange={(e) =>
                                setPolicyType(
                                    e.target.value
                                )
                            }
                        />

                        <label className="form-label">
                            Coverage Amount
                        </label>

                        <input
                            className="form-control mb-3"
                            type="number"
                            min="1"
                            value={coverageAmount}
                            onChange={(e) =>
                                setCoverageAmount(
                                    e.target.value
                                )
                            }
                        />

                        <label className="form-label">
                            Premium Amount
                        </label>

                        <input
                            className="form-control mb-3"
                            type="number"
                            min="1"
                            value={premiumAmount}
                            onChange={(e) =>
                                setPremiumAmount(
                                    e.target.value
                                )
                            }
                        />

                        <button
                            className="btn btn-success w-100"
                            onClick={addPolicy}
                        >
                            Add Policy
                        </button>

                    </div>

                </div>

            </div>

        </div>

    </div>
);


}

export default Policy;
