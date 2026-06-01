function Reports() {

    return (

        <div
            style={{
                width: "100%",
                height: "100vh"
            }}
        >

            <h1
                style={{
                    textAlign: "center"
                }}
            >
                Insurance Claim Reports
            </h1>

            <iframe
                title="Reports"
                src="http://localhost:8080/InsuranceClaimSystem/reports"
                width="100%"
                height="90%"
                style={{
                    border: "none"
                }}
            />

        </div>
    );
}

export default Reports;