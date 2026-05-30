<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Claim Management</title>

<style>

body{
    font-family: Arial;
    background-color: #f2f2f2;
}

.container{

    width: 450px;
    margin: 50px auto;
    background: white;
    padding: 30px;
    border-radius: 10px;
}

input, textarea{

    width: 100%;
    padding: 10px;
    margin-top: 10px;
}

button{

    width: 100%;
    padding: 10px;
    margin-top: 20px;
    background: green;
    color: white;
    border: none;
}

</style>

</head>

<body>

<div class="container">

<h2>Submit Insurance Claim</h2>

<form action="submitClaim" method="post">

<input
type="number"
name="policyId"
placeholder="Policy ID"
required>

<input
type="text"
name="claimantName"
placeholder="Claimant Name"
required>

<input
type="number"
step="0.01"
name="claimAmount"
placeholder="Claim Amount"
required>

<input
type="date"
name="incidentDate"
required>

<textarea
name="description"
placeholder="Incident Description"
required>
</textarea>

<button type="submit">
Submit Claim
</button>

</form>

</div>

</body>

</html>