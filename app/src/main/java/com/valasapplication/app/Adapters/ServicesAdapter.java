package com.valasapplication.app.Adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.valasapplication.app.Constansts;
import com.valasapplication.app.Fragments.CatalogueServiceDetailFragment;
import com.valasapplication.app.Models.ServicePrefere;
import com.valasapplication.app.Models.Services;
import com.valasapplication.app.Navigation.NavigationActivityFragment;
import com.valasapplication.app.R;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.CatalogueViewHolder> {
    private Context context = null;
    private List<Services> catalogueList ;
    private int layout ;
    private ServicePrefere servicePrefere;


    public void filterList(List<Services> filteredList) {
        catalogueList = filteredList;
        notifyDataSetChanged();
    }

    public ServicesAdapter(List<Services> catalogueList ,int layout) {
        this.catalogueList = catalogueList;
        this.layout=layout ;
    }

    public ServicesAdapter( List<Services> catalogueList) {
        this.catalogueList = catalogueList;
    }
    @Override
    public int getItemCount() {
        return catalogueList.size();
    }
    public static final class CatalogueViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView title,categorie,price ;
        Button favbtn ;

        public CatalogueViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.title);
            categorie =itemView.findViewById(R.id.categorie);
            price =itemView.findViewById(R.id.price);
            imageView =itemView.findViewById(R.id.imageUrl);
            favbtn=itemView.findViewById(R.id.favbtn);

        }
    }

    @NonNull
    @NotNull
    @Override
    public CatalogueViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {
        if(context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.catalogue_row_item, parent,false); ;
        if(layout == R.layout.catalogue_row_item_2){
             view = LayoutInflater.from(context).inflate(R.layout.catalogue_row_item_2, parent,false);
        }
        return new CatalogueViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull @NotNull CatalogueViewHolder catalogueViewHolder, int i) {
        Services service = catalogueList.get(i);
        catalogueViewHolder.title.setText(service.getNom());
        catalogueViewHolder.categorie.setText(service.getCategorie());
        String price = "à partir de " + service.getPrix();
        catalogueViewHolder.price.setText(price);
        Picasso.get().load(service.getImageUrl()).into(catalogueViewHolder.imageView);
        catalogueViewHolder.imageView.setOnClickListener(v -> {
            NavigationActivityFragment.replaceFragment(context, new CatalogueServiceDetailFragment(service));
        });
        if (service.getFavorite())
            catalogueViewHolder.favbtn.setBackgroundResource(R.drawable.baseline_favorite_orange);
        else
            catalogueViewHolder.favbtn.setBackgroundResource(R.drawable.baseline_favorite_24);

        catalogueViewHolder.favbtn.setOnClickListener(v -> {

            if (service.getFavorite()){

                v.setBackgroundResource(R.drawable.baseline_favorite_24); // Couleur non favori
                updateFavorite(context, "produit", service.getId(), 0); // Mettre à jour la base de données
                service.setFavorite(false); // Définir l'état de favori comme faux
                PreferencesAddRemove(context,service.getId(),1,Constansts.URL_REMOVE_PRODUITPREFERE);

            } else {

                v.setBackgroundResource(R.drawable.baseline_favorite_orange); // Couleur favori
                updateFavorite(context, "produit", service.getId(), 1); // Mettre à jour la base de données
                service.setFavorite(true); // Définir l'état de favori comme vrai
                PreferencesAddRemove(context,service.getId(),1,Constansts.URL_INSERER_PRODUITPREFERE);
            }
        });
    }

    private void updateFavorite(Context context, String type, int id, int isFavorite) {
        String url = Constansts.URL_UPDATE_FAVOURIT;
        RequestQueue requestQueue;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> System.out.println("ServicesAdapter.updateFavorite  "+ response),
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
                params.put("type", "product");
                params.put("itemId", String.valueOf(id));
                params.put("isFavorite", String.valueOf(isFavorite)); // Use the actual isFavorite value
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }






    private void PreferencesAddRemove(Context context, int idProduit, int idClient,String url) {
        RequestQueue requestQueue;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> Toast.makeText(context, response, Toast.LENGTH_LONG).show(),
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
                params.put("id_produit", String.valueOf(idProduit));
                params.put("id_client", String.valueOf(idClient));
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
