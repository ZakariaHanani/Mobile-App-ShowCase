package com.valasapplication.app.Fragments;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.valasapplication.app.Adapters.ServicesAdapter;
import com.valasapplication.app.Constansts;
import com.valasapplication.app.Models.Categorie;
import com.valasapplication.app.Models.Services;

import com.valasapplication.app.Helpers.PopupMenuHelper;
import com.valasapplication.app.R;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CatalogueFragment extends Fragment {

    private RecyclerView servicesView;
    private ServicesAdapter servicesAdapter ;
    private TextView titre ;
    private ImageView imagemenu ;
    private List<Services> servicesList;
    ProgressBar progressBar ;
    private List<Services> filteredList;
    private SearchView searchView;

    public CatalogueFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogue, container, false);
        servicesView = view.findViewById(R.id.recyclerCatalogue);
        titre =view.findViewById(R.id.titre);
        imagemenu=view.findViewById(R.id.setting);
        searchView=view.findViewById(R.id.searchview);
        progressBar=view.findViewById(R.id.idPB) ;
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = view.getContext();
        titre.setText("Catalogues");
        servicesList = HomeFragment.servicesList;
        ArrayList fonctionality = new ArrayList<>();
        fonctionality.add("Espace disque 50 Go SSD ");
        setServicesView(R.layout.catalogue_row_item_2);
        progressBar.setVisibility(View.GONE);
        servicesView.setVisibility(View.VISIBLE);
        //getData(context);
        imagemenu.setOnClickListener(v -> PopupMenuHelper.showPopupMenu(v,context));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }
        });
    }
    private void setServicesView(int layout) {
        LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        servicesView.setLayoutManager(layoutManager);
        servicesAdapter = new ServicesAdapter(servicesList,layout);
        servicesView.setAdapter(servicesAdapter);
    }
    private void filterData(String query) {
        filteredList = new ArrayList<>();
        if (TextUtils.isEmpty(query)) {
            filteredList.addAll(servicesList);
        } else {
            String searchText = query.toLowerCase().trim();
            for (Services item : servicesList) {
                if ( (item.getNom().toLowerCase().contains(searchText) || item.getCategorie().toLowerCase().contains(searchText))|| item.getDescription().toLowerCase().contains(searchText)) {
                    filteredList.add(item);
                }
            }
        }
        servicesAdapter.filterList(filteredList);
    }
}