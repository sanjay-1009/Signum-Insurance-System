import { useEffect, useState } from "react";
import API from "../services/api";

function Reports() {


const [claims, setClaims] =
    useState([]);

const [search, setSearch] =
    useState("");

useEffect(() => {

    loadReports();

}, []);

const loadReports = async () => {

    try {

        const response =
            await API.get(
                "/reports"
            );

        setClaims(
            response.data
        );

    } catch(error) {

        console.log(error);
    }
};

const filteredClaims =
    claims.filter((claim) =>
        claim.claimantName
            .toLowerCase()
            .includes(
                search.toLowerCase()
            )
    );

return (

    <div className="container mt-4">

        <div className="card shadow">

            <div className="card-header bg-dark text-white">

                <h2>
                    Insurance Claim Reports
                </h2>

            </div>

            <div className="card-body">

                <input
                    className="form-control mb-3"
                    placeholder="Search Claimant..."
                    value={search}
                    onChange={(e) =>
                        setSearch(
                            e.target.value
                        )
                    }
                />

                <div className="table-responsive">

                    <table className="table table-striped table-hover">

                        <thead className="table-dark">

                            <tr>

                                <th>ID</th>
                                <th>Policy</th>
                                <th>Claimant</th>
                                <th>Amount</th>
                                <th>Date</th>
                                <th>Description</th>
                                <th>Status</th>

                            </tr>

                        </thead>

                        <tbody>

                            {filteredClaims.map(
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
                                        {
                                            claim.claimantName
                                        }
                                    </td>

                                    <td>
                                        ₹
                                        {
                                            claim.claimAmount
                                        }
                                    </td>

                                    <td>
                                        {
                                            claim.incidentDate
                                        }
                                    </td>

                                    <td>
                                        {
                                            claim.description
                                        }
                                    </td>

                                    <td>

                                        {
                                            claim.status ===
                                            "Approved"
                                            ?

                                            <span className="badge bg-success">
                                                Approved
                                            </span>

                                            :

                                            claim.status ===
                                            "Rejected"
                                            ?

                                            <span className="badge bg-danger">
                                                Rejected
                                            </span>

                                            :

                                            <span className="badge bg-warning text-dark">
                                                Pending
                                            </span>
                                        }

                                    </td>

                                </tr>
                            ))}

                        </tbody>

                    </table>

                </div>

            </div>

        </div>

    </div>
);


}

export default Reports;
