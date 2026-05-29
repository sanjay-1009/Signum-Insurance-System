<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Policy Management</title>

<style>

body{
    font-family: Arial;
    background-color: #f2f2f2;
}

.container{

    width: 400px;
    margin: 50px auto;
    background: white;
    padding: 30px;
    border-radius: 10px;
}

input{

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

<h2>Add Policy</h2>

<form action="addPolicy" method="post">

<input
type="text"
name="policyName"
placeholder="Policy Name"
required>

<input
type="text"
name="policyType"
placeholder="Policy Type"
required>

<input
type="number"
step="0.01"
name="coverageAmount"
placeholder="Coverage Amount"
required>

<input
type="number"
step="0.01"
name="premiumAmount"
placeholder="Premium Amount"
required>

<button type="submit">
Add Policy
</button>

</form>

</div>

</body>
</html>