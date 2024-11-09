<?php


	$connect=mysql_connect('localhost','root','');

mysql_select_db('tnjob',$connect);
mysql_query("SET NAMES 'utf8'");
 $sql=mysql_query("SELECT *  FROM  job where id=".$_POST['idoffre']);
  while($row=mysql_fetch_assoc($sql)) $output[]=$row;
  
  
  print(json_encode($output));

?>