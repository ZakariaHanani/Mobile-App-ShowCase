package com.valasapplication.app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.valasapplication.app.Adapters.FonctionalitesAdapter;
import com.valasapplication.app.Models.Services;
import com.valasapplication.app.Helpers.PopupMenuHelper;
import com.valasapplication.app.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogueServiceDetailFragment factory method to
 * create an instance of this fragment.
 */


public class CatalogueServiceDetailFragment extends Fragment {
    private RecyclerView fonctionaliteView;
    private FonctionalitesAdapter fonctionalitesAdapter;

    private Services catalogueService ;
    private ImageView imagemenu ;
    private TextView title ;
    private TextView titreNavbar ;
    private TextView description ;
    private ArrayList<String> fonctionalites =new ArrayList<>();
    private TextView prix;
    private Integer imageUrl ;

    public CatalogueServiceDetailFragment(Services catalogueService) {
         this.catalogueService =catalogueService ;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_catalogue_service_detail, container, false);
        title=view.findViewById(R.id.title);
        imagemenu =view.findViewById(R.id.setting);
        titreNavbar =view.findViewById(R.id.titre);
        fonctionaliteView =view.findViewById(R.id.fonctionalits);
        description =view.findViewById(R.id.description);
        prix=view.findViewById(R.id.prix);
        return view ;

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context =view.getContext();
        titreNavbar.setText("Détails du Service");
        title.setText(catalogueService.getNom());
        description.setText((catalogueService.getDescription()));
        prix.setText(catalogueService.getPrix().toString());
        ArrayList  srt=new ArrayList<>() ;
        setFonctionalitesView(catalogueService.getFonctionalites(),view);
        imagemenu.setOnClickListener(v -> PopupMenuHelper.showPopupMenu(v,context));


    }

    private void setFonctionalitesView(ArrayList<String> fonctionaliteList,View v) {
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        fonctionaliteView.setLayoutManager(layoutManager);
        fonctionalitesAdapter = new FonctionalitesAdapter(fonctionaliteList,v.getContext());
        fonctionaliteView.setAdapter(fonctionalitesAdapter);
    }
}