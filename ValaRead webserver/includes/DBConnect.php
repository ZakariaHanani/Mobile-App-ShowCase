<?php


class DBConnect {
private $con; 

    function __construct(){

    }

        function connect(){
        include_once dirname(__FILE__).'/constants.php';
        try{
        $this->con =new PDO("mysql:host=".DB_HOST.";dbname=".DB_NAME.";charset=utf8",DB_USER ,DB_PASSWORD);
        return $this->con ;
            }catch(PDOException $e){
                echo 'Erreur' .$e->getMessage() .'<br>';
                }
        }
}

?>