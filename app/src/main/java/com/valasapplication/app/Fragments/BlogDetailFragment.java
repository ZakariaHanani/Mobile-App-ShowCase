package com.valasapplication.app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.valasapplication.app.Constansts;
import com.valasapplication.app.Models.Article;
import com.valasapplication.app.R;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class BlogDetailFragment extends Fragment {

    private static Article article ;
    private ImageView imageView ;
    private Button favbtn ;
    private TextView titre ,date ,contenu ;

   public BlogDetailFragment(Article article){
       BlogDetailFragment.article =article;
   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blog_detail, container, false);
        titre =view.findViewById(R.id.titre) ;
        date=view.findViewById(R.id.date) ;
        contenu=view.findViewById(R.id.contenu);
        imageView =view.findViewById(R.id.imageurlblog) ;
        favbtn=view.findViewById(R.id.btnfav);
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context =view.getContext();
        Picasso.get().load(article.getImageUrl()).into(imageView);
        titre.setText(article.getTitre());
        date.setText(article.getDate_publication());
        contenu.setText(article.getContenu());
        if (article.getIsFavorite()!=0)
            favbtn.setBackgroundResource(R.drawable.baseline_favorite_orange); // Couleur non favori
        if (article.getIsFavorite()==0)
            favbtn.setBackgroundResource(R.drawable.baseline_favorite_24);

        favbtn.setOnClickListener(v -> {

           if (article.getIsFavorite()!=0){

               v.setBackgroundResource(R.drawable.baseline_favorite_24); // Couleur non favori
               updateFavorite(context, article.getArtId(), 0); // Mettre à jour la base de données
               article.setIsFavorite(0); // Définir l'état de favori comme faux
               PreferencesAddRemove(context,article.getArtId(),1,Constansts.URL_REMOVE_ARTICLEPREFERE);

           } else {
               v.setBackgroundResource(R.drawable.baseline_favorite_orange); // Couleur favori
               updateFavorite(context, article.getArtId(), 1); // Mettre à jour la base de données
               article.setIsFavorite(1); // Définir l'état de favori comme vrai
               PreferencesAddRemove(context,article.getArtId(),1,Constansts.URL_INSERER_ARTICLEPREFERE);
           }
        });
    }

    private void updateFavorite(Context context, int id, int isFavorite) {
        String url = Constansts.URL_UPDATE_FAVOURIT;
        RequestQueue requestQueue;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response ->  System.out.println("BlogDetailFragment.updateFavorite "+ response),
                error -> {
                    if (error instanceof TimeoutError) {
                        Toast.makeText(context, "Network timeout", Toast.LENGTH_LONG).show();
                    } else if (error instanceof ServerError) {
                        Toast.makeText(context, "Server error", Toast.LENGTH_LONG).show();
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
                    } else if (error instanceof ParseError) {
                        Toast.makeText(context, "Parse error", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Generic error", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("type", "article");
                params.put("itemId", String.valueOf(id));
                params.put("isFavorite", String.valueOf(isFavorite));
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }






    private void PreferencesAddRemove(Context context, int idArticle, int idClient,String url) {
        RequestQueue requestQueue;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> System.out.println("BlogDetailFragment.PreferencesAddRemove "+ response),
                error -> {
                    if (error instanceof TimeoutError) {
                        Toast.makeText(context, "Network timeout", Toast.LENGTH_LONG).show();
                    } else if (error instanceof ServerError) {
                        Toast.makeText(context, "Server error", Toast.LENGTH_LONG).show();
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
                    } else if (error instanceof ParseError) {
                        Toast.makeText(context, "Parse error", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Generic error", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_article", String.valueOf(idArticle));
                params.put("id_client", String.valueOf(idClient));
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}