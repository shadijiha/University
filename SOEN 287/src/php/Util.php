<?php

include "Structures.php"

# This file contains main untility functions to communicate with server
function void_updateDataBase($string_DBName, $string_tableName, $int_idOfRow, $string_fieldName, $any_newValue)	{
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = $string_DBName;
	
	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}
	
	$sql = "UPDATE " . $string_tableName . " SET " . $string_fieldName . "='" . $any_newValue . "' WHERE id=" . $int_idOfRow . "";
	
	if ($conn->query($sql) === TRUE) {
	  echo "Record updated successfully";
	} else {
	  echo "Error updating record: " . $conn->error;
	}
	
	$conn->close();
}

function void_deleteFromDateBase($string_DBName, $string_tableName, $int_idRow)	{
	$servername = "localhost";
	$username = "username";
	$password = "password";
	$dbname = $string_DBName;

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}

	// sql to delete a record
	$sql = "DELETE FROM " . $string_tableName . " WHERE id=" . $int_idOfRow . "";

	if ($conn->query($sql) === TRUE) {
		echo "Record deleted successfully";
	} else {
		echo "Error deleting record: " . $conn->error;
	}

	$conn->close();
}

function void_addUser($User_userObject)	{
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "soen_287_project";

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
	die("Connection failed: " . $conn->connect_error);
	}

	$sql = "INSERT INTO users (id, username, password, email, cart)
	VALUES (, $User_userObject->$username, $User_userObject->$password, $User_userObject->email, $User_userObject->cart)";

	if ($conn->query($sql) === TRUE) {
		echo "New record created successfully";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}

	$conn->close();
}
?>