<?php

$hostname_localhost="localhost";
$username_localhost="root";
$userpass_localhost="snowy";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$userpass_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);

$databasename_localhost="GREFury";
mysql_select_db($databasename_localhost, $localhost);

$username=$_POST['username'];
$password=$_POST['password'];
$message="";
$response=array();
$check_username="select * from users where username='".$username."'";
$check_username_exec = mysql_query($check_username) or die(mysql_error());
$rows = mysql_num_rows($check_username_exec);
if($rows==0){
	$message="Username does not exist.";
	}
else{
	while ($row = mysql_fetch_array($check_username_exec, MYSQL_NUM)) {
    if($row[1]==$password){
    $message="Login successful!";
    }
    else{
    $message="Invalid password!";
    }
}
}
$response['message']=$message;
echo json_encode($response);

?>