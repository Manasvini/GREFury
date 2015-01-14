<?php

$hostname_localhost="localhost";
$username_localhost="root";
$userpass_localhost="snowy";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$userpass_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);

$databasename_localhost="GREFury";
mysql_select_db($databasename_localhost, $localhost);

//if(isset($_POST['username'])){
$username=$_POST['username'];
//echo "here";}
//else echo "not set";
$password=$_POST['password'];
$email=$_POST['email'];
$message="";
$response=array();
$flag=1;
$check_username="select * from users where username='".$username."'";
$check_username_exec = mysql_query($check_username) or die(mysql_error());
$rows = mysql_num_rows($check_username_exec);
if($rows!=0){
$flag=0;
	$message="Username already exists.";
	}
	
$check_email="select * from users where  email='".$email."'";
$check_email_exec = mysql_query($check_email) or die(mysql_error());
$rows2 = mysql_num_rows($check_email_exec);
if($rows2!=0){
$flag=0;
	$message="Email already exists.";
	$response['success']=0;
	}

$insert_query="insert into users values('".$username."','".$password."','".$email."')";

$insert_query_exec = mysql_query($insert_query) or die(mysql_error());
if($flag==1){
	$message="Registered Succesfully!";
	$response['success']=1;
	}
$response['message']=$message;

echo json_encode($response);
?>