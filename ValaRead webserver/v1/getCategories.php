<?php
require_once '../includes/DBOperations.php' ;
if($_SERVER['REQUEST_METHOD']=='GET'){
    $operation =new DBOperations();
    $operation->getAllCategories(); 
   
}
else {
    // Handle other HTTP methods if needed
   http_response_code(405); // Method Not Allowed
   json_encode(array("message" => "Method Not Allowed"));
}
