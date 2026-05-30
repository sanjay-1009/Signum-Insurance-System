<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Claim Approval</title>

<style>

body{
    font-family: Arial;
    background:#f2f2f2;
}

.container{

    width:400px;
    margin:50px auto;
    background:white;
    padding:30px;
    border-radius:10px;
}

input,select{

    width:100%;
    padding:10px;
    margin-top:10px;
}

button{

    width:100%;
    padding:10px;
    margin-top:20px;
    background:green;
    color:white;
    border:none;
}

</style>

</head>

<body>

<div class="container">

<h2>Claim Approval</h2>

<form action="approveClaim"
      method="post">

<input
type="number"
name="claimId"
placeholder="Claim ID"
required>

<select name="status">

<option value="APPROVED">
APPROVED
</option>

<option value="REJECTED">
REJECTED
</option>

</select>

<button type="submit">

Update Status

</button>

</form>

</div>

</body>

</html>