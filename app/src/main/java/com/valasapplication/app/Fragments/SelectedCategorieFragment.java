package com.valasapplication.app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.valasapplication.app.Adapters.ServicesAdapter;
import com.valasapplication.app.Models.Services;
import com.valasapplication.app.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectedCategorieFragment#} factory method to
 * create an instance of this fragment.
 */
public class SelectedCategorieFragment extends Fragment {

    private RecyclerView servicesView;
    private ServicesAdapter servicesAdapter ;
    private ArrayList<Services>  servicesList;
    private ArrayList<Services>  filtredlist;
    private String categorie ;

    private TextView titre ;

    public SelectedCategorieFragment(String categorie) {
        this.categorie =categorie ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_categorie, container, false);
        servicesView = view.findViewById(R.id.recycler_selected_view);
        titre =view.findViewById(R.id.titre);
        return  view ;
    }
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titre.setText(categorie);

        ArrayList fonctionality = new ArrayList<>();
        fonctionality.add("Espace disque 50 Go SSD ");
        servicesList =HomeFragment.servicesList;
        filtredlist =new ArrayList<>();
        for(Services item :servicesList){
            if(item.getCategorie().toLowerCase().equals(categorie.toLowerCase())){
                filtredlist.add(item);
            }
        }
        setServicesView(filtredlist, R.layout.catalogue_row_item_2);
    }
    private void setServicesView(List<Services> servicesList, int layout) {
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        servicesView.setLayoutManager(layoutManager);
        servicesAdapter = new ServicesAdapter(servicesList,layout);
        servicesView.setAdapter(servicesAdapter);
    }
}