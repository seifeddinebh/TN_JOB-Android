<?php


	$connect=mysql_connect('localhost','root','');

mysql_select_db('tnjob',$connect);
mysql_query("SET NAMES 'utf8'");
 $sql=mysql_query("SELECT id,username,username_canonical,email,email_canonical FROM user WHERE email='".$_POST['login']."' and email_canonical='".$_POST['mpt']."'");

while($row=mysql_fetch_assoc($sql)) $output[]=$row;
  
  
  print(json_encode($output));

?>