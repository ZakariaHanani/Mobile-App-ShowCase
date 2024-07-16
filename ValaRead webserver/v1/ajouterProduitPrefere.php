<?php
require_once '../includes/DBOperations.php' ;
if($_SERVER['REQUEST_METHOD']=='POST'){
    if (isset($_POST["id_produit"], $_POST["id_client"])) {
$idproduit =$_POST["id_produit"];
$idclient =$_POST["id_client"];
$operation= new DBOperations();
$operation->InsertProduitPreferences($idproduit,$idclient) ;
    }else {
        echo "Missing POST parameters" ;
}
}else {
     echo "Invalid Request Methode";
}
//curl -X POST -d "ID_PRODUIT=4&id_client=4" http://localhost/ValaRead/v1/ajouterProduitPrefere.php