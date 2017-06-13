<html>
<head></head>
		<body>
			<form method="post" action="<?php $_PHP_SELF?>">
				<table width="600" cellpadding="2" cellspacing="2">
				<tr>
				<td>Product Id:</td>
				<td><input type="text" name="prd_id"></td>
				</tr>
				<tr>
				<td> Product Name:</td>
				<td><input type="text" name="product_name"></td>
				</tr>
				<tr>
				<td> Products sold:</td>
				<td><input type="text" name="psold"></td>
				</tr>
				<tr>
				<td>Products in Stock</td>
				<td><input type="text" name="pstock"></td>
				</tr>
				<tr>
				<td><input type="submit" name="add"></td>
				</tr>
			</form>
			
			<?php
			if(isset($_POST['add'])){
			$conn= mysql_connect("localhost","root","sonu");
			if(!$conn){
				echo "Not able to connect to Database";
			}else{
				echo "Connected to Database";
			}
			mysql_select_db("stock");
			$prd_id=$_POST['prd_id'];
			$product_name=$_POST['product_name'];
			$psold=$_POST['psold'];
			$pstock=$_POST['pstock'];
			
			$sql= "INSERT INTO products (prd_id,product_name,psold,pstock) VALUES ('$prd_id','$product_name','$psold','$pstock')";
			$result = mysql_query($sql);
			if(!$result){
				echo "Couldnt able to insert the product";
			}else {
				echo "Inserted the product successfully";
			}
			mysql_close();
			}
			?>
			<div class="footer">
			<?php include 'footer.php'?>
			</div>
		</body>
		
</html>