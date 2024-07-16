package com.valasapplication.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.valasapplication.app.R;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;


public class FonctionalitesAdapter extends RecyclerView.Adapter<FonctionalitesAdapter.FonctionalitesViewHolder> {
    private Context context ;
    private final ArrayList<String> fonactionaliteList ;

    public FonctionalitesAdapter( ArrayList<String> fonactionaliteList,Context context) {
        this.fonactionaliteList = fonactionaliteList;
        this.context =context ;

    }

    @NonNull
    @NotNull
    @Override
    public FonctionalitesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {
        View view ;
        if(context == null){
            context=parent.getContext() ;
        }
            view = LayoutInflater.from(context).inflate(R.layout.fonctionalite_item_layout, parent, false);
        return new FonctionalitesViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull FonctionalitesViewHolder fonactionaliteViewHolder, int i) {
        String fonctionalite = fonactionaliteList.get(i);
        fonactionaliteViewHolder.fonctionalite.setText(fonctionalite);
    }

    @Override
    public int getItemCount() {
        return fonactionaliteList.size();
    }

    public static final class FonctionalitesViewHolder extends RecyclerView.ViewHolder{

        TextView fonctionalite;

        public FonctionalitesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            fonctionalite =itemView.findViewById(R.id.fonctionalite_txt);
        }
    }


}
