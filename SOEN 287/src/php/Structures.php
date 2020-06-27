<?php

/**
 *  These structures must be the same as the javascript once
 * 
 */

class Item {
	private $id = -1;
	public $name = "";
	public $category = "";
	public $image = "";
	public $cost = 0.0;
	public $quantity = 0;
    
	function __construct($int_id, $string_name, $string_category, $string_image, $double_cost, $int_quantity) {
		$id = $int_id;
		$name = $string_name;
		$category = $string_category;
		$image = $string_image;
		$cost = $double_cost;
		$quantity = $int_quantity;
	 }

	 function getID()	{
		 return $id;
	 }
}

class User	{
	private $id = -1;
	public $username = "";
	public $password = "";
	public $email = "";
	public $cart = "";

	function __construct($int_id, $string_username, $string_password, $string_email, $string_cart) {
		$id = $int_id;
		$username = $string_username;
		$password = $string_password;
		$email = $string_email;
		$cart = $string_cart;
	 }

	 function getID()	{
		 return $id;
	 }
}

?>