<?php
	$con = mysql_connect("127.0.0.1","root","","android_database");

	$name = $_POST["name"];
	$age = $_POST["age"];
	$email = $_POST["email"];
	$password = $_POST["password"];

	$statement = mysqli_prepare($con,"INSERT INTO users(name,age,email,password) VALUES(?,?,?,?)");
	mysqli_stmt_bind_param($statement,"siss",$name,$age,$email,$password);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>