<?php
require_once '../includes/DBOperations.php' ;
if($_SERVER['REQUEST_METHOD']=='GET'){
    $operation =new DBOperations();
    $articles =$operation->getAllArticles(); 
    echo json_encode($articles);
}else {
    // Handle other HTTP methods if needed
    http_response_code(405); // Method Not Allowed
    echo json_encode(array("message" => "Method Not Allowed"));
}
