package com.valasapplication.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.valasapplication.app.Fragments.BlogDetailFragment;
import com.valasapplication.app.Models.Article;
import com.valasapplication.app.Navigation.NavigationActivityFragment;
import com.valasapplication.app.R;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {

    private Context context;
    private ArrayList<Article>  articleList ;
    private int layout ;

    public BlogAdapter(Context context, ArrayList<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }
    public BlogAdapter(Context context, ArrayList<Article> articleList,int layout) {
        this.layout =layout ;
        this.context = context;
        this.articleList = articleList;
    }
    public static final class BlogViewHolder extends RecyclerView.ViewHolder{
        TextView titre ,date,contenu;
        ImageView imageView ;

        public BlogViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            titre =itemView.findViewById(R.id.titre);
            date =itemView.findViewById(R.id.date);
            contenu =itemView.findViewById(R.id.contenu);
            imageView =itemView.findViewById(R.id.imageUrl);

        }
    }
    @NonNull
    @NotNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {

        View view ;
        if (layout== R.layout.blog_article_row_item){
           view=  LayoutInflater.from(context).inflate(R.layout.blog_article_row_item, parent,false) ;
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.blog_article_row_item_2, parent,false) ;
        }
        return new BlogViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BlogViewHolder blogViewHolder, int i) {
        Article article = articleList.get(i);
        Picasso.get().load(article.getImageUrl()).into(blogViewHolder.imageView);
        blogViewHolder.titre.setText(article.getTitre());
        blogViewHolder.date.setText(article.getDate_publication());
        if (layout == R.layout.blog_article_row_item) {
            blogViewHolder.contenu.setText(null);
        }
        else {
            String[] line_contenu = article.getContenu().split("\n");
            String contenu = line_contenu[0] + "... ";
            blogViewHolder.contenu.setText(contenu);
        }
        blogViewHolder.imageView.setOnClickListener(v -> {
            //Toast.makeText(context,"Detail Blog",Toast.LENGTH_LONG);
           NavigationActivityFragment.replaceFragment(context,new BlogDetailFragment(article));
        });
    }
    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
