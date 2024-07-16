package com.valasapplication.app.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.valasapplication.app.Fragments.CatalogueFragment;
import com.valasapplication.app.Fragments.SelectedCategorieFragment;
import com.valasapplication.app.Models.Categorie;
import com.valasapplication.app.Navigation.NavigationActivityFragment;
import com.valasapplication.app.R;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.CategorieViewHolder> {
    private Context context ;
    private List<Categorie> categorieList;
    private String where ;
    public CategorieAdapter( List<Categorie> categorieList,String where) {
        this.categorieList = categorieList;
        this.where =where;
    }

    public CategorieAdapter( List<Categorie> categorieList,String where ,Context context) {
        this.categorieList = categorieList;
        this.where =where;
        this.context =context ;
    }
    @NonNull
    @NotNull
    @Override
    public CategorieViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {
        View view ;
        if (context ==null){
            context= parent.getContext();
        }
        view = LayoutInflater.from(context).inflate(R.layout.categorie_row_item, parent, false);
        if(where.equals("c")) {
        view = LayoutInflater.from(context).inflate(R.layout.categories_row_item_fragment, parent, false);
        }
        return new CategorieViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull CategorieViewHolder categorieViewHolder, int i) {
        Categorie categorie = categorieList.get(i);
        categorieViewHolder.title.setText(categorie.getNom());
        Picasso.get().load(categorie.getImageUrl()).into(categorieViewHolder.imageView);
        if (context != null && categorieViewHolder.imageView != null)
            categorieViewHolder.imageView.setOnClickListener(v -> {
               //Toast.makeText(context,"your choose "+categorie.getNom()+" !!" ,Toast.LENGTH_LONG) ;
                NavigationActivityFragment.replaceFragment(context,new SelectedCategorieFragment(categorie.getNom()));
            });
        else if(context == null) {
            Log.e("AdapterError", "Null context ");
        }
        else if(categorieViewHolder.imageView == null){
            Log.e("AdapterError", "Null imageGo");
        }
        else{
           //Toast.makeText(context,"your choose "+categorie.getNom()+" !!" ,Toast.LENGTH_LONG) ;
            Log.e("AdapterError", "Context or Fragment type error");
        }

    }
    @Override
    public int getItemCount() {
        return categorieList.size();
    }
    public static final class CategorieViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ,imageGo ;
        ConstraintLayout constraintLayoutCategorie ;
        TextView title;
        public CategorieViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.nom);
            imageView =itemView.findViewById(R.id.imageUrl);
            constraintLayoutCategorie=itemView.findViewById(R.id.constraint_categorie) ;
            imageGo =itemView.findViewById(R.id.imageGo);
        }
    }
}