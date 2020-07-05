<html>
	<head>
<link rel="stylesheet" type="text/css" href="css/main.css">
		<title>Store main page</title>
		<script src="scripts/item.js"></script>
		<script src="scripts/sales.js"></script>
		<script type="module" src="scripts/main.js"></script>
<!-- Load on sale items to display them-->
<?php
	include "php/Util.php";

	$result = ItemsArray_getItem("onsale", 1);
	
	// Convert this to JS array
	$js_array = "[";

	// foreach ($result as $value)	{
		
	// }

	for ($i = 0; $i < count($result); $i++) {
		$js_array = $js_array . "new Item(". $result[$i]->toString() ."), ";
	 }

	$js_array = $js_array . "]";

	// Exceute script
	echo "<script>window.addEventListener('load', function()	{
			Sales.void_processSales($js_array);
	});</script>";
?>
	</head>
	<body>
		<div id="top_banner">
			<a class="white" href="login.php" title="Login to your account">Login</a>
			|
			<a class="white" href="register.php" title="First time user? Register now!">Register</a>

			<!-- cart -->
			<a href="cart.php">
				<button id="cart_button">
					<img src="../assets/Icons/cart.png" style="float:left; margin-right:0.5em" width="25" height="25">
					<span id="cart_total_value">
						$0.00
					</span>
				</button>
			</a>
		</div>
		
		<!-- This div is resposible for the display of the items on Sale -->
		<div id="slidshow" style="">
			This text exists to show the div only
			<div id="slidshow_price">
				$0.00
			</div>
		</div>
	</body>
</html>
