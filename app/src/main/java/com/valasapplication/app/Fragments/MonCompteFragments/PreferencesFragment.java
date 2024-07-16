package com.valasapplication.app.Fragments.MonCompteFragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
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
import com.valasapplication.app.Adapters.BlogAdapter;
import com.valasapplication.app.Adapters.ServicesAdapter;
import com.valasapplication.app.Constansts;
import com.valasapplication.app.Fragments.HomeFragment;
import com.valasapplication.app.Models.Article;
import com.valasapplication.app.Models.Categorie;
import com.valasapplication.app.Models.ServicePrefere;
import com.valasapplication.app.Models.Services;
import com.valasapplication.app.R;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;


public class PreferencesFragment extends Fragment {

     private TextView titre,textViewServicePrefere,textViewArticlePrefere;
     private BlogAdapter blogAdapter;
     private ServicesAdapter servicesAdapter;
     private RecyclerView recyclerView;
    ArrayList<Services> servicesprefere ;
    ArrayList<Article> articlesprefere;
    public PreferencesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_preferences, container, false);
        recyclerView =view.findViewById(R.id.recycler_preference);
        titre =view.findViewById(R.id.titre);
        textViewServicePrefere=view.findViewById(R.id.textView);
        textViewArticlePrefere=view.findViewById(R.id.textView2);
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titre.setText("Mes Préférences");

        getProduitsPrefere(view.getContext(),1);

        textViewServicePrefere.setOnClickListener(v -> {
        setServicesView(servicesprefere, R.layout.catalogue_row_item_2);
         });

        ArrayList<Article> articles =new ArrayList<>();

        textViewArticlePrefere.setOnClickListener(v -> {
            getArticlePrefere(view.getContext(),1);
        });


    }



    private void setBlogView(ArrayList<Article> articles,Context context) {
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        blogAdapter = new BlogAdapter(context,articles);
        recyclerView.setAdapter(blogAdapter);
    }
    private void setServicesView(List<Services> servicesList, int layout) {
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        servicesAdapter = new ServicesAdapter(servicesList,layout);
        recyclerView.setAdapter(servicesAdapter);
    }


    private  void getProduitsPrefere(Context context,int id_client){
        String url = Constansts.URL_GET_PRODUITPREFERE +id_client ;
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        JsonArrayRequest jsonArrayRequest;
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    servicesprefere =new ArrayList<>();
                  if (response.length()!=0){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsoncategorie = response.getJSONObject(i);
                            int ID_PRODUIT = jsoncategorie.getInt("id_produit");

                                for (Services service : HomeFragment.servicesList) {
                                    if (service.getId() == ID_PRODUIT) {
                                        servicesprefere.add(service);
                                    }
                                }
                                setServicesView(servicesprefere, R.layout.catalogue_row_item_2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }
                  }
                  else{
                      try {
                          JSONObject jsoncategorie = response.getJSONObject(0);
                          String message =jsoncategorie.getString("message");
                          Toast.makeText(context,message,Toast.LENGTH_SHORT);
                      } catch (JSONException e) {
                          throw new RuntimeException(e);
                      }
                  }
                },
                error -> {
                    errorsRequest(error,context);
        });
        requestQueue.add(jsonArrayRequest);
    }


    private  void getArticlePrefere(Context context,int id_client){
        String url = Constansts.URL_GET_ARTICLEPREFERE +id_client ;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest;
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    articlesprefere =new ArrayList<>();
                    if (response.length() !=0){
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsoncategorie = response.getJSONObject(i);
                                int id_article = jsoncategorie.getInt("id_article");

                                for (Article article : HomeFragment.articlesList) {
                                    if (article.getArtId() == id_article) {
                                        articlesprefere.add(article);
                                    }
                                }
                                    setBlogView(articlesprefere,context);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    else{
                        Toast.makeText(context,"Aucune Article Prefere",Toast.LENGTH_LONG);
//                        try {
//                            JSONObject jsoncategorie = response.getJSONObject(0);
//                            String message =jsoncategorie.getString("message");
//                            Toast.makeText(context,message,Toast.LENGTH_SHORT);
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
                    }
                },
                error -> {
                   errorsRequest(error,context);
                });
        requestQueue.add(jsonArrayRequest);
    }







    public void errorsRequest(VolleyError error,Context context){
        if (error instanceof TimeoutError) {
            Log.e(TAG, "Volley Timeout Error: " + error.getMessage(), error);
            Toast.makeText(context, "R.string.error_network_timeout", Toast.LENGTH_LONG).show();

        } else if (error instanceof ServerError) {
            Log.e(TAG, "Volley Server Error: " + error.getMessage(), error);
            Toast.makeText(context, "R.string.error_server", Toast.LENGTH_LONG).show();

        } else if (error instanceof NetworkError){
            Log.e(TAG, "Volley Network Error: " + error.getMessage(), error);
            Toast.makeText(context," R.string.error_network", Toast.LENGTH_LONG).show();

        } else if (error instanceof ParseError){
            Log.e(TAG, "Volley Parse Error: " + error.getMessage(), error);
            Toast.makeText(context, "R.string.parse_error", Toast.LENGTH_LONG).show();

        } else {
            Log.e(TAG, "Volley Error: " + error.getMessage(), error);
            Toast.makeText(context, "R.string.error_generic", Toast.LENGTH_LONG).show();

        }
    }
}