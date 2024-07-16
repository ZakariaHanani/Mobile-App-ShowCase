package com.valasapplication.app.Activites;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.valasapplication.app.R;

public class ApropreNous extends AppCompatActivity {
    private TextView visitSiteTextView;
    private TextView visitYouTubeTextView;
    private TextView visitFacebookTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apropre_nous);
        visitSiteTextView = findViewById(R.id.visitSiteTextView);
        visitYouTubeTextView = findViewById(R.id.visitYouTubeTextView);
        visitFacebookTextView = findViewById(R.id.visitFacebookTextView);

        visitSiteTextView.setOnClickListener(v -> openWebsite("https://www.vala.ma/"));

        visitYouTubeTextView.setOnClickListener(v -> openWebsite("https://www.youtube.com/user/valableuagadir"));

        visitFacebookTextView.setOnClickListener(v -> openWebsite("https://web.facebook.com/valableu.ma/"));
    }

    private void openWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(ApropreNous.this, "Aucun navigateur trouvé", Toast.LENGTH_SHORT).show();
        }
    }

}