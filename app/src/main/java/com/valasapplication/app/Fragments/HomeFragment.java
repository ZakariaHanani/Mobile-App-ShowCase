package com.valasapplication.app.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.valasapplication.app.Adapters.BlogAdapter;
import com.valasapplication.app.Adapters.CategorieAdapter;
import com.valasapplication.app.Adapters.ServicesAdapter;
import com.valasapplication.app.Constansts;
import com.valasapplication.app.Models.Article;
import com.valasapplication.app.Models.Categorie;
import com.valasapplication.app.Models.Services;
import com.valasapplication.app.Navigation.NavigationActivityFragment;
import com.valasapplication.app.Helpers.PopupMenuHelper;
import com.valasapplication.app.R;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment {
    private RecyclerView servicesView ,categorieView,blogView;

    private ServicesAdapter servicesAdapter ;
    ProgressBar progressBar ;
    private ImageView imagemenu ;
    private ScrollView scrollView ;
    public static ArrayList<Services> servicesList ;
    static ArrayList<Categorie> categorieList ;
    public static ArrayList<Article> articlesList;
    private TextView nom_client,voir_catalogue,voir_categorie ,voir_blog;
    private MeowBottomNavigation bottomNavigation ;

    public HomeFragment(MeowBottomNavigation bottomNavigation ) {
        this.bottomNavigation = bottomNavigation ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        nom_client=view.findViewById(R.id.nom_person);
        imagemenu =view.findViewById(R.id.setting);
        servicesView = view.findViewById(R.id.recyclerCatalogue);
        categorieView =view.findViewById(R.id.recyclerCategorie) ;
        blogView =view.findViewById(R.id.recyclerBlog);
        progressBar=view.findViewById(R.id.idPB);
        voir_catalogue =view.findViewById(R.id.voir_catalogue);
        voir_categorie =view.findViewById(R.id.voir_categorie);
        voir_blog =view.findViewById(R.id.voir_blog);
        scrollView =view.findViewById(R.id.scrollView3);
        return  view ;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context =view.getContext() ;

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String firstName = sharedPreferences.getString("first_name", null);
        String lastName = sharedPreferences.getString("last_name", null);
        if (firstName != null && lastName != null) {

            nom_client.setText(firstName +" "+lastName);
            Log.d("User Info", "First Name: " + firstName);
            Log.d("User Info", "Last Name: " + lastName);
        } else {
            Log.d("User Info", "No user information found in SharedPreferences.");
        }
        bottomNavigation.show(1, true);
        servicesList =new ArrayList<>() ;
        categorieList =new ArrayList<>();
        articlesList=new ArrayList<>();
        if(servicesList.isEmpty() || categorieList.isEmpty()|| articlesList.isEmpty()) {
            getDataCategories(context);
            getDataProduits(context);
            getDataArticels(context);
        }else{
            setCategorieView("horizontal");
            setServicesView("horizontal");
            setBlogView(context);
        }

        imagemenu.setOnClickListener(v -> PopupMenuHelper.showPopupMenu(v,context));
        voir_catalogue.setOnClickListener(v -> {
            bottomNavigation.show(2, true);
            NavigationActivityFragment.replaceFragment(v.getContext(), new CatalogueFragment());
        });
        voir_categorie.setOnClickListener(v -> {
            bottomNavigation.show(3, true);
            NavigationActivityFragment.replaceFragment(v.getContext(), new CategoriesFragment());

        });
        voir_blog.setOnClickListener(v -> {
            bottomNavigation.show(4, true);
            NavigationActivityFragment.replaceFragment(v.getContext(), new BlogFragment());
        });
    }
    private void setBlogView(Context context){
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        blogView.setLayoutManager(layoutManager);
        BlogAdapter blogAdapter;
        if (articlesList.size()>4){
            ArrayList<Article> articleArrayList =new ArrayList<>();
            for (int i=0 ;i<=4;i++){
                articleArrayList.add(articlesList.get(i));
            }
            blogAdapter =new BlogAdapter(context,articleArrayList,R.layout.blog_article_row_item) ;
        }
        else {
            blogAdapter = new BlogAdapter(context,articlesList,R.layout.blog_article_row_item);
        }
        blogView.setAdapter(blogAdapter);
    }
    private void setCategorieView(String orientation){
        RecyclerView.LayoutManager layoutManager ;
        if(orientation.equals("vertical")) {
            layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        }else {
            layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false);
        }

        categorieView.setLayoutManager(layoutManager);
        CategorieAdapter categorieAdapter;
        if (categorieList.size()>5){
            ArrayList<Categorie> categorieArrayList =new ArrayList<>();
            for (int i=0 ;i<=4;i++){
                categorieArrayList.add(categorieList.get(i));
            }
            categorieAdapter =new CategorieAdapter(categorieArrayList,"h") ;
        }
        else {
            categorieAdapter = new CategorieAdapter(categorieList, "h");
        }
        categorieView.setAdapter(categorieAdapter);
    }
    private void setServicesView(String orientation){
        RecyclerView.LayoutManager layoutManager ;
        if(orientation.equals("vertical")) {
            layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        }else {
            layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false);
        }
        servicesView.setLayoutManager(layoutManager);
        if(servicesList.size()>4){
            ArrayList<Services> servicesCatalogue =new ArrayList<>();
            for (int i = 0; i <3 ; i++) {
            servicesCatalogue.add(servicesList.get(i));
                servicesAdapter =new ServicesAdapter(servicesCatalogue);
            }}
        else {
            servicesAdapter = new ServicesAdapter(servicesList);
        }
        servicesView.setAdapter(servicesAdapter);
    }

    private  void getDataProduits(Context context){
        String url = Constansts.URL_GET_PRODUITSERVICES ;
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        JsonArrayRequest jsonArrayRequest;
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsoncategorie = response.getJSONObject(i);
                            int ID_PRODUIT = jsoncategorie.getInt("ID_PRODUIT");
                            String DESCRIPTION_PROD = jsoncategorie.getString("DESCRIPTION_PROD");
                            String NOM_PRODUIT = jsoncategorie.getString("NOM_PRODUIT");
                            Double PRIX = jsoncategorie.getDouble("PRIX");
                            String IMAGE_PRODUIT = jsoncategorie.has("IMAGE_PRODUIT")?jsoncategorie.getString("IMAGE_PRODUIT") : null;
                            int fav = jsoncategorie.getInt("isFavorite");
                            Boolean IS_FAVORITE = fav !=0 ;
                            // Check if the key exists before retrieving its value
                            String FONCTIONALITES = jsoncategorie.has("FONCTIONNALITES_PROD") ? jsoncategorie.getString("FONCTIONNALITES_PROD") : "";
                            String[] FONCTIONNALITES_PROD = FONCTIONALITES.split(",|\\n");
                            ArrayList<String> foncationalites = new ArrayList(Arrays.asList(FONCTIONNALITES_PROD));
                            int ID_CATEGORIE = jsoncategorie.getInt("ID_CATEGORIE");
                            String  nom_categorie ="aucune";
                            for (Categorie categorie:categorieList) {
                                if(categorie.getId()==ID_CATEGORIE)
                                    nom_categorie =categorie.getNom();
                            }
                            servicesList.add(new Services(ID_PRODUIT,NOM_PRODUIT,DESCRIPTION_PROD,foncationalites,PRIX,nom_categorie,IMAGE_PRODUIT,IS_FAVORITE));
                            setServicesView("horizental");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }
                }, error -> {

            errorsRequest(error,context);
        });
        requestQueue.add(jsonArrayRequest);
    }

    private  void getDataCategories(Context context){
        String url =Constansts.URL_GET_CATEGORIES;
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        JsonArrayRequest jsonArrayRequest;
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsoncategorie = response.getJSONObject(i);
                            int id = jsoncategorie.getInt("ID_CATEGORIE");
                            String nom = jsoncategorie.getString("NOM_CATEGORIE");
                            String imageUrl = jsoncategorie.getString("IMAGE_CATEGORIE");
                            categorieList.add(new Categorie(id, nom, imageUrl));
                            setCategorieView("horizontal");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }
                }, error -> {
            errorsRequest(error,context);
        });
        requestQueue.add(jsonArrayRequest);
    }

    private  void getDataArticels(Context context){
        String url = Constansts.URL_GET_ARTICLES ;
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        JsonArrayRequest jsonArrayRequest;
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsoncategorie = response.getJSONObject(i);
                            int ID_ARTICLE = jsoncategorie.getInt("ID_ARTICLE");
                            String CONTENU = jsoncategorie.getString("CONTENU");
                            String TITRE = jsoncategorie.getString("TITRE");
                            String DATE_PUBLICATION = jsoncategorie.getString("DATE_PUBLICATION");
                            int ISFAVORITE =jsoncategorie.getInt("isFavorite");
                            String IMAGE_ARTICLE = jsoncategorie.has("IMAGE_ARTICLE")? jsoncategorie.getString("IMAGE_ARTICLE") : null;
                            articlesList.add(new Article(ID_ARTICLE,TITRE,CONTENU,DATE_PUBLICATION,IMAGE_ARTICLE,ISFAVORITE));
                            setBlogView(context);
                        } catch (JSONException e){
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }
                }, error -> errorsRequest(error,context));
        requestQueue.add(jsonArrayRequest);
    }












    public void errorsRequest(VolleyError error,Context context){
        if (error instanceof TimeoutError) {
            Log.e(TAG, "Volley Timeout Error: " + error.getMessage(), error);
            Toast.makeText(context, "R.string.error_network_timeout", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        } else if (error instanceof ServerError) {
            Log.e(TAG, "Volley Server Error: " + error.getMessage(), error);
            Toast.makeText(context, "R.string.error_server", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        } else if (error instanceof NetworkError) {
            Log.e(TAG, "Volley Network Error: " + error.getMessage(), error);
            Toast.makeText(context, " R.string.error_network", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        } else if (error instanceof ParseError) {
            Log.e(TAG, "Volley Parse Error: " + error.getMessage(), error);
            Toast.makeText(context, "R.string.parse_error", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        } else {
            Log.e(TAG, "Volley Error: " + error.getMessage(), error);
            Toast.makeText(context, "R.string.error_generic", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }
    }


}

