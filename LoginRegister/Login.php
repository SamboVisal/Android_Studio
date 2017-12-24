<?php
	$con = mysql_connect("127.0.0.1","root","","android_database");
	$username = $_POST["username"];
	$password = $_POST["password"];

	$statement = mysqli_prepare($con,"SELECT * FROM  users WHERE name = ? AND password = ?");
	mysqli_stmt_bind_param($statement,"ss",$username,$password);
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement,$userID,$name,$age,$email,$password);

	$response = array();
	$response["success"] = false;
	while (mysqli_stmt_fetch($statement)) {
		$response["success"] = true;
		$response["name"] = $name;
		$response["age"] = $age;
		$response["email"] = $email;
		$response["password"] = $password;
	}
	echo json_encode($response);
?>