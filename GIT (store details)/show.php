<html>
<title>Product Info</title>
<body>
<?php

$conn= mysql_connect("localhost","root","sonu");
if(!$conn){
	echo "Unable to connect to Database";
}else{
	echo "Connected to database";
}
mysql_select_db("stock");
$sql= "SELECT * FROM products";
$result=mysql_query($sql);
mysql_close();
?>

<table width="500" border="1" cellpadding="2" cellspacing="2">
	<tr>
	<th> Product Id</th>
	<th> Product name</th>
	<th> Product Sold </th>
	<th> Product in Stock</th>
	</tr>
	
<?php
while($productinfo= mysql_fetch_assoc($result)){
	
	echo "<tr>";
	echo "<td>".$productinfo['prd_id']."</td>";
	echo "<td>".$productinfo['product_name']."</td>";
	echo "<td>".$productinfo['psold']."</td>";
	echo "<td>".$productinfo['pstock']."</td>";
	echo "</tr>";
}

?>

</table>

<div class="footer">
<?php include 'footer.php';  ?>
</div>
</body>

</html>