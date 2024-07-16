<?php
require_once '../includes/DBOperations.php';
if($_SERVER['REQUEST_METHOD']=='GET'){
    $operation =new DBOperations();
    $operation->getAllProduitService(); 
}
else {
   http_response_code(405);
   echo json_encode(array("message" => "Method Not Allowed"));
}