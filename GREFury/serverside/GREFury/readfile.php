<?php

function parseString($string){
	//echo $string."<br/>";
	$word=strtok($string,"$#-");
	
 // echo $tokens."<br />";
 // $word = strtok("$#-");
  $def = strtok("$#-");
  $sentence = strtok("$#-");
   echo $word."<br/>";
   echo $def."<br/>";
   echo $sentence."<br/>";
	
	 $sql="INSERT into words(word,definition,sentence)values("."'".$word."','".$def."','".$sentence."')";
		echo $sql;
echo "<br/>---------------<br/>";	
	 mysql_query($sql) or die(mysql_error()); 
}
$file = fopen("wordlist.txt", "r") or exit("Unable to open file!");
//Output a line of the file until the end is reached
$tok="";
//$i=0;
 mysql_connect("127.0.0.1","root","snowy");

 mysql_select_db("GREFury");

     
while(!feof($file) )
  //while($i<10)
  {
  //$i++;
  $txt=fgets($file);
    if($tok=="" && $txt!="\n"){
   $tok=$txt."#";
   }
   else{
	if($txt!="\n")
    $tok=$tok.$txt;
	}
  if(strchr($tok,"$")){
  $replacedtok=str_replace("'","\'",$tok);
	parseString($replacedtok);
	$tok="";
  }
 // echo "#<br/>";
  }
fclose($file);
?>