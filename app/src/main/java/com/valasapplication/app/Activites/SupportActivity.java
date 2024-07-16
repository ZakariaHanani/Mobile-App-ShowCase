package com.valasapplication.app.Activites;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.valasapplication.app.EmailSender;
import com.valasapplication.app.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SupportActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText messageEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        messageEditText = findViewById(R.id.message_edit_text);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String message = messageEditText.getText().toString().trim();

            // Valider les données du formulaire
            if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                Toast.makeText(SupportActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Envoyer les données à votre système de support (ou afficher un message de confirmation)
                EmailSender.sendEmail(this,name,email,message);
                Toast.makeText(SupportActivity.this, "Demande d'assistance envoyée", Toast.LENGTH_SHORT).show();
                // Réinitialiser le formulaire après l'envoi
                nameEditText.setText("");
                emailEditText.setText("");
                messageEditText.setText("");
            }
        });
    }
}



