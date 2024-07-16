<?php
require_once '../includes/DBOperations.php' ;
if($_SERVER['REQUEST_METHOD']=='GET'){
    if (isset($_GET["id_client"])) {
$idclient =$_GET["id_client"];
$operation= new DBOperations();
$response =$operation->getServicePrefere($idclient) ;
echo json_encode($response) ;
    }else {
   echo  json_encode(array("message" => "Missing POST parameters"));
}
}else {
   echo json_encode(array("message" => "Invalid Request Methode"));
}