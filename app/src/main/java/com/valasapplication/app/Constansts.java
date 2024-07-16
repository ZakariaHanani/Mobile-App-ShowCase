package com.valasapplication.app;
public class Constansts {
    private final static String URL_WEBSERVER ="http://192.168.56.1/ValaRead/v1/";
    public final static String URL_SIGNIN =URL_WEBSERVER+"signin.php";
    public final static String URL_SIGNUP =URL_WEBSERVER+"signup.php";
    public final static String URL_GET_CATEGORIES =URL_WEBSERVER+"getCategories.php";
    public final static String URL_GET_ARTICLES =URL_WEBSERVER+"getArticles.php";
    public final static String URL_GET_PRODUITSERVICES =URL_WEBSERVER+"getProduitsServices.php";
    public final static String URL_GET_PRODUITPREFERE =URL_WEBSERVER+"getProduitPrefere.php?id_client=";
    public final static String URL_GET_ARTICLEPREFERE =URL_WEBSERVER+"getArticlePrefere.php?id_client=";
    public final static String URL_INSERER_PRODUITPREFERE =URL_WEBSERVER+"ajouterProduitPrefere.php";
    public final static String URL_REMOVE_PRODUITPREFERE =URL_WEBSERVER+"deleteProduitPreferences.php";
    public final static String URL_INSERER_ARTICLEPREFERE =URL_WEBSERVER+"ajouterArticlePrefere.php";
    public final static String URL_REMOVE_ARTICLEPREFERE =URL_WEBSERVER+"deleteArticlePrefere.php";
    public final static String URL_SEND_EMAIL =URL_WEBSERVER+"contactSupport.php";
    public final static String URL_MODIFIER_INFORMATIONS =URL_WEBSERVER+"modifierInformation.php";
    public final static String URL_UPDATE_FAVOURIT =URL_WEBSERVER+"updateFavorits.php";
}
