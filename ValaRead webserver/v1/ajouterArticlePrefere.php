<?php
require_once '../includes/DBOperations.php' ;
if($_SERVER['REQUEST_METHOD']=='POST'){
    if (isset($_POST["id_article"], $_POST["id_client"])) {
$idarticle =$_POST["id_article"];
$idclient =$_POST["id_client"];
$operation= new DBOperations();
$operation->InsertArticlePreferences($idarticle,$idclient) ;
    }else {
      echo "Missing POST parameters" ;
}
}else {
    echo "Invalid Request Methode";
}