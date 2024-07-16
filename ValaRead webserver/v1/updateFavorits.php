<?php
require_once '../includes/DBOperations.php';

// Check if the required POST parameters are set
if (isset($_POST['type'], $_POST['itemId'],$_POST['isFavorite'])) {
    $type= $_POST['type']; // 'product' or 'article'
    $isFavorite =$_POST['isFavorite'];
    $itemId =$_POST['itemId']; // 1 for favorite, 0 for not favorite

    $operation = new DBOperations();
    $operation->updateFavorite($type, $isFavorite, $itemId);
} else {
    echo "Missing POST parameters" ;
}
?>
