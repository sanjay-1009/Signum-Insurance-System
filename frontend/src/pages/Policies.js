import { useEffect, useState } from "react";
import API from "../services/api";

function Policies() {

    const [policies, setPolicies] =
        useState([]);

    useEffect(() => {

        loadPolicies();

    }, []);

    const loadPolicies = async () => {

        try {

            const response =
                await API.get(
                    "/getPolicies"
                );

            setPolicies(
                response.data
            );

        } catch(error) {

            console.log(error);
        }
    };

    return (

        <div className="container mt-4">

            <h2 className="mb-4">
                Available Policies
            </h2>

            <table className="table table-bordered table-striped">

                <thead>

                    <tr>

                        <th>ID</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Coverage</th>
                        <th>Premium</th>

                    </tr>

                </thead>

                <tbody>

                    {policies.map(
                        (policy) => (

                        <tr
                            key={
                                policy.policyId
                            }
                        >

                            <td>
                                {policy.policyId}
                            </td>

                            <td>
                                {policy.policyName}
                            </td>

                            <td>
                                {policy.policyType}
                            </td>

                            <td>
                                ₹{
                                  policy.coverageAmount
                                }
                            </td>

                            <td>
                                ₹{
                                  policy.premiumAmount
                                }
                            </td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </div>
    );
}

export default Policies;