package com.valasapplication.app.Fragments;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.valasapplication.app.Adapters.CategorieAdapter;
import com.valasapplication.app.Constansts;
import com.valasapplication.app.Models.Categorie;
import com.valasapplication.app.Helpers.PopupMenuHelper;
import com.valasapplication.app.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CategoriesFragment extends Fragment {
    private ImageView imagemenu ;
    public static ArrayList<Categorie> categorieList;
    private RecyclerView categorieView;
    private CategorieAdapter categorieAdapter;
    private TextView titre ;
    private ProgressBar progressBar ;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        categorieView = view.findViewById(R.id.recyclerCatalogue);
        titre =view.findViewById(R.id.titre);
        imagemenu=view.findViewById(R.id.setting) ;
        progressBar=view.findViewById(R.id.idPB);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context =view.getContext();
        titre.setText("Categories");
           categorieList = HomeFragment.categorieList;
           progressBar.setVisibility(View.GONE);
           categorieView.setVisibility(View.VISIBLE);
        setCategorieView("vertical",context);
        imagemenu.setOnClickListener(v -> PopupMenuHelper.showPopupMenu(v,context));
    }
    private void setCategorieView(String orientation,Context context) {
        LayoutManager layoutManager;
        if (orientation.equals("vertical")){
            layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        } else {
            layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        }
        categorieView.setLayoutManager(layoutManager);
        categorieAdapter = new CategorieAdapter(categorieList,"c",context);
        categorieView.setAdapter(categorieAdapter);
    }

}