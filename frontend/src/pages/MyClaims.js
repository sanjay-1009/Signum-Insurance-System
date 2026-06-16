import { useEffect, useState } from "react";
import API from "../services/api";

function MyClaims() {

    const [claims, setClaims] =
        useState([]);

    useEffect(() => {

        loadClaims();

    }, []);

    const loadClaims = async () => {

        try {

            const userId =
    localStorage.getItem(
        "userId"
    );

const response =
    await API.get(
        "/myClaims",
        {
            params: {
                userId
            }
        }
    );

            setClaims(
                response.data
            );

        } catch(error) {

            console.log(error);
        }
    };

    const getBadge = (status) => {

        if(status === "Approved")
            return "success";

        if(status === "Rejected")
            return "danger";

        return "warning";
    };

    return (

        <div className="container mt-4">

            <h2>
                My Claims
            </h2>

            <table className="table table-bordered">

                <thead>

                    <tr>

                        <th>Claim ID</th>
                        <th>Policy ID</th>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Status</th>

                    </tr>

                </thead>

                <tbody>

                    {claims.map(
                        (claim) => (

                        <tr
                            key={
                                claim.claimId
                            }
                        >

                            <td>
                                {claim.claimId}
                            </td>

                            <td>
                                {claim.policyId}
                            </td>

                            <td>
                                ₹{claim.amount}
                            </td>

                            <td>
                                {claim.date}
                            </td>

                            <td>

                                <span
                                    className={
                                      `badge bg-${getBadge(claim.status)}`
                                    }
                                >
                                    {claim.status}
                                </span>

                            </td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </div>
    );
}

export default MyClaims;