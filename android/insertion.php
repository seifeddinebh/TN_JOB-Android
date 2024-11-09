<?php


	
	$connect=mysql_connect('localhost','root','');

mysql_select_db('tnjob',$connect);
mysql_query("SET NAMES 'utf8'");
 $sql=mysql_query("insert into job(category, user, type, position, location, description) values(1,".$_POST['id_client'].",'".$_POST['type']."','".$_POST['pos']."','".$_POST['loc']."','".$_POST['des']."')");
  
if($sql) 
{
  print("1");
}
else  print("0");
?>
