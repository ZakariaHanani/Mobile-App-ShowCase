<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    require_once '../includes/DBOperations.php' ;
    if (isset($_POST['nom']) && isset($_POST['prenom']) 
        && isset($_POST['telephone']) && isset($_POST['email'])) {

        $nom = htmlspecialchars($_POST['nom']);
        $prenom = htmlspecialchars($_POST['prenom']);
    
        $telephone = htmlspecialchars($_POST['telephone']);
        $email = htmlspecialchars($_POST['email']);

        $operation =new DBOperations();
        $operation->ModifierInformations($nom,$prenom,$telephone,$email); 
    } else {
        $response = array();
        $response["success"] = false;
        $response["message"] = "Parametres manquants";
         json_encode($response);
    }
} else {
    $response = array();
    $response["success"] = false;
    $response["message"] = "Methode de requete invalide";
     json_encode($response);
}
?>
