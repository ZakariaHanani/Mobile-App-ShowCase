package com.valasapplication.app.Activites;

import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.valasapplication.app.Navigation.NavigationActivityFragment;
import com.valasapplication.app.R;

public class MainActivity extends AppCompatActivity {
    Button button ;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =findViewById(R.id.button);
        button.setOnClickListener(v -> {
            NavigationActivityFragment.navigateTo(HomeActivity.class ,v.getContext());
            finish();
        });
    }
}
