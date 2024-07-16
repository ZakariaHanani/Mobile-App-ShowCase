<?php

class DBOperations{

    private $con; 


    function __construct(){
        require_once dirname(__FILE__).'/DBConnect.php';
        $db= new DBConnect();
        $this->con=$db->connect();
    }


    function getAllCategories(){
        $req ="SELECT * FROM CATEGORIE ;" ;
       try{
        $stamt =$this->con->query($req);
        if ($stamt) {
            $reponse=$stamt->fetchAll(PDO::FETCH_ASSOC);
           }
        else {
             echo "error in sql requist or somthing else " ;
        }
         echo json_encode($reponse);
        
    }catch(PDOException $e){
         echo  $e->getMessage() ;
    }
    }

    function getAllProduitService(){
        $req ="SELECT * FROM  produitservice;" ;
        try{
         $stamt =$this->con->query($req);
         if ($stamt) {
             $reponse=$stamt->fetchAll(PDO::FETCH_ASSOC);
             echo json_encode($reponse);
            }
         else {
              echo "requist error " ;
         }
     }catch(PDOException $e){
           echo $e->getMessage() ;
     }
     }


    function getAllArticles(){
        $req ="SELECT * FROM  ARTICLE ;" ;
       try{
        $stamt =$this->con->query($req);
        if ($stamt) {
            $reponse=$stamt->fetchAll(PDO::FETCH_ASSOC);
           }
        else {
            echo   "requist error " ;
        }
        return $reponse;
    }catch(PDOException $e){
        echo $e->getMessage() ;
    }
    }

    function InsertProduitPreferences ($ID_PRODUIT,$id_client){
        $req="insert into preferences(ID_PRODUIT,id_client) values(?,?) ";
        try{
            $stamt =$this->con->prepare($req);
            $data =array($ID_PRODUIT,$id_client);
            $reponse= $stamt->execute($data);
            if($reponse){
                echo "Ajouté aux favoris" ;
            }else{
               echo "Error d'ajouter le produit aux favoris";
            }
        }catch(PDOException $e){
            echo $e->getMessage() ;
        }
    }

    function DeleteProduitPreferences ($ID_PRODUIT,$id_client){
        $req="delete from preferences where ID_PRODUIT =? and id_client =? ";
        try{
            $stamt =$this->con->prepare($req);
            $data =array($ID_PRODUIT,$id_client);
            $reponse= $stamt->execute($data);
            if($reponse){
                echo "Supprimé des favoris";
                
            }else{
                echo "Error de supprimer des favoris";
        
            }
        }catch(PDOException $e){
            echo  $e->getMessage() ;
        }
    }

    function DeleteArticlePreferences ($id_article,$id_client){
        $req="delete from preferences where id_article =? and id_client =? ";
        try{
            $stamt =$this->con->prepare($req);
            $data =array($id_article,$id_client);
            $reponse= $stamt->execute($data);
            if($reponse){
                 echo "Supprimé des favoris";
                
            }else{
                 echo "Error de supprimer des favoris";
        
            }
        }catch(PDOException $e){
             echo $e->getMessage() ;
        }
    }


    function InsertArticlePreferences ($id_Article,$id_client){
        $req="insert into preferences(id_article,id_client) values(?,?) ";
        try{
            $stamt =$this->con->prepare($req);
            $data =array($id_Article,$id_client);
            $reponse= $stamt->execute($data);
            if($reponse){
                echo "Ajouté aux favoris";
            }else{
                echo "Error d'ajouter l'article aux favoris";
            }
        }catch(PDOException $e){
            echo $e->getMessage() ;
        }
    }


    public function getServicePrefere($id_client) {
        $req = "SELECT DISTINCT  id_produit FROM preferences WHERE id_produit IS NOT NULL AND id_client=?";
        try {
            $stmt = $this->con->prepare($req);
            $stmt->execute([$id_client]);
            $response = $stmt->fetchAll(PDO::FETCH_ASSOC);
            if ($response) {
                return $response;
            } else {
                return array("error"=>"true","message" => "No preferences found for this client");
            }
        } catch (PDOException $e) {
            return array("error"=>"true","message" => "Database error: " . $e->getMessage());
        }
    }
    
    public function getArticlePrefere($id_client) {
        $req = "SELECT DISTINCT id_article FROM preferences WHERE id_article IS NOT NULL AND id_client=?";
        try {
            $stmt = $this->con->prepare($req);
            $stmt->execute([$id_client]);
            $response = $stmt->fetchAll(PDO::FETCH_ASSOC);
            if ($response) {
                return $response;
            } else {
                return array("message" => "No preferences found for this client");
            }
        } catch (PDOException $e) {
            return array("message" => "Database error: " . $e->getMessage());
        }
    }



    function updateFavorite($type,$isFavorite,$itemId){
        try {
            if ($type ==='product') {
                $tableName = 'produitservice';
                $idColumnName = 'ID_PRODUIT';
            } elseif ($type == 'article') {
                $tableName = 'article';
                $idColumnName = 'ID_ARTICLE';
            } else {
             echo "Invalid item type";
                exit();
            }
            $stmt = $this->con->prepare("UPDATE $tableName SET isFavorite = :isFavorite WHERE $idColumnName = :itemId");
            

            $stmt->bindParam(':isFavorite', $isFavorite, PDO::PARAM_INT);
            $stmt->bindParam(':itemId', $itemId, PDO::PARAM_INT);
            if ($stmt->execute()) {
                echo "Favorite state updated successfully" ;
            } else {
             
                echo "Failed to update favorite state";
            }
        } catch (PDOException $e) {
             echo "error : ".$e ;
        }
    
    }
    

    function ModifierInformations($nom,$prenom,$telephone,$email){
        $sql = "UPDATE client SET nom = :nom, prenom = :prenom, telephone = :telephone WHERE email = :email";
        $stmt = $this->con->prepare($sql);

        $stmt->bindParam(':nom', $nom);
        $stmt->bindParam(':prenom', $prenom);
        $stmt->bindParam(':telephone', $telephone);
        $stmt->bindParam(':email', $email);

        try {
            $stmt->execute();
            $response = array();
            $response["success"] = true;
            $response["message"] = "Informations modifiees avec succes";
            echo  json_encode($response);
        } catch (PDOException $e) {
            $response = array();
            $response["success"] = false;
            $response["message"] = "Erreur lors de la mise a jour des informations : " . $e->getMessage();
            echo  json_encode($response);
        }

    }



    



}