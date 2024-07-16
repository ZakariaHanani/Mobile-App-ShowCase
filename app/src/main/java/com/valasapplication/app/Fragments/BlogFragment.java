package com.valasapplication.app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.valasapplication.app.Adapters.BlogAdapter;
import com.valasapplication.app.Models.Article;
import com.valasapplication.app.Models.Categorie;
import com.valasapplication.app.Helpers.PopupMenuHelper;
import com.valasapplication.app.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BlogFragment extends Fragment {

    private RecyclerView blogview;
    private BlogAdapter blogAdapter;
    private ImageView imagemenu;
    private TextView titre;
    public BlogFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view =inflater.inflate(R.layout.fragment_blog, container, false);
            blogview =view.findViewById(R.id.recyclerArticle);
            imagemenu=view.findViewById(R.id.setting);
            titre=view.findViewById(R.id.titre);
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context =view.getContext() ;
        titre.setText("Blog");
        imagemenu.setOnClickListener(v -> PopupMenuHelper.showPopupMenu(v,context));
        ArrayList<Article> articles =HomeFragment.articlesList;
        setBlogView(articles,view);
    }
    private void setBlogView(ArrayList<Article> articles,View v) {
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL, false);
        blogview.setLayoutManager(layoutManager);
        blogAdapter = new BlogAdapter(v.getContext(),articles);
        blogview.setAdapter(blogAdapter);
    }
}