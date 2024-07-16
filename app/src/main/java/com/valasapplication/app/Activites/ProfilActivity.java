package com.valasapplication.app.Activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.valasapplication.app.Constansts;
import com.valasapplication.app.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class ProfilActivity extends AppCompatActivity {

    private EditText nomEditText, prenomEditText,telephoneEditText ,emailEditText;
    private  String email;
    private Button enregistrerButton;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        nomEditText = findViewById(R.id.nom);
        prenomEditText = findViewById(R.id.prenom);
        telephoneEditText = findViewById(R.id.telephone);
        emailEditText= findViewById(R.id.email);
        enregistrerButton = findViewById(R.id.enregistrerbtn);


        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String firstName = sharedPreferences.getString("first_name", null);
        String lastName = sharedPreferences.getString("last_name", null);
        email = sharedPreferences.getString("email", null);

        if (firstName != null && lastName != null && email != null) {

            nomEditText.setText(firstName);
            prenomEditText.setText(lastName);
            emailEditText.setText(email);

            Log.d("User Info", "First Name: " + firstName);
            Log.d("User Info", "Last Name: " + lastName);
            Log.d("User Info", "Email: " + email);
        } else {
            Log.d("User Info", "No user information found in SharedPreferences.");
        }
        enregistrerButton.setOnClickListener(v -> modifierInformations());
    }

    private void modifierInformations() {
        final String nom = nomEditText.getText().toString().trim();
        final String prenom = prenomEditText.getText().toString().trim();
        final String telephone = telephoneEditText.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constansts.URL_MODIFIER_INFORMATIONS,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        String message = jsonObject.getString("message");

                        if (success) {
                            Toast.makeText(ProfilActivity.this, message, Toast.LENGTH_SHORT).show();
                            updateSharedPreferences(nom,prenom);
                        } else {
                            Toast.makeText(ProfilActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(ProfilActivity.this, "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(ProfilActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nom", nom);
                params.put("prenom", prenom);
                params.put("telephone", telephone);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void updateSharedPreferences(String firstName, String lastName){
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("first_name", firstName);
        editor.putString("last_name", lastName);
        editor.apply();
    }
}