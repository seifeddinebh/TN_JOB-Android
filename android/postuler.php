<?php


	
	$connect=mysql_connect('localhost','root','');

mysql_select_db('tnjob',$connect);
mysql_query("SET NAMES 'utf8'");
 $sql=mysql_query("INSERT INTO postulation VALUES (".$_POST['idoffre'].", ".$_POST['id_client'].", '2013-12-23')");
  
if($sql) 
{
  print("1");
}
else  print("0");
?>