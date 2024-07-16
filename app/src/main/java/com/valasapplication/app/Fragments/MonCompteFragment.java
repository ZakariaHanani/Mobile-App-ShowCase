package com.valasapplication.app.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.valasapplication.app.Activites.*;
import com.valasapplication.app.Fragments.MonCompteFragments.*;
import com.valasapplication.app.Navigation.NavigationActivityFragment;
import com.valasapplication.app.R;
import com.valasapplication.app.modules.signin.ui.SignInActivity;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonCompteFragment} factory method to
 * create an instance of this fragment.
 */
public class MonCompteFragment extends Fragment {

    private TextView nomComplet,emailProfil ;
    private ConstraintLayout monprofil ,support ,parametres ,monpreferences ,localisation,aproprenous ,deconnecter ;
    private  Context context;
    public MonCompteFragment() {
        // Required empty public constructo
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_mon_compte, container, false);
        nomComplet=view.findViewById(R.id.Nom_profil);
        emailProfil=view.findViewById(R.id.Email_profil);
        monprofil =view.findViewById(R.id.profil_const);
        support =view.findViewById(R.id.support_const);
        parametres =view.findViewById(R.id.setting_const);
        monpreferences =view.findViewById(R.id.favourites_const);
        localisation =view.findViewById(R.id.location_const);
        aproprenous =view.findViewById(R.id.info_const);
        deconnecter =view.findViewById(R.id.logout_const);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String firstName = sharedPreferences.getString("first_name", null);
        String lastName = sharedPreferences.getString("last_name", null);
        String email = sharedPreferences.getString("email", null);

        if (firstName != null && lastName != null && email != null) {

            nomComplet.setText(firstName +" "+ lastName);
            emailProfil.setText(email);

            Log.d("User Info", "Name : " + firstName +" "+lastName);
            Log.d("User Info", "Email: " + email);
        } else {
            Log.d("User Info", "No user information found in SharedPreferences.");
        }


        monprofil.setOnClickListener(v -> NavigationActivityFragment.navigateTo(ProfilActivity.class,context));
        support.setOnClickListener(v -> NavigationActivityFragment.navigateTo(SupportActivity.class,context));
        parametres.setOnClickListener(v -> NavigationActivityFragment.replaceFragment(context,new SettingsFragment()));
        monpreferences.setOnClickListener(v -> NavigationActivityFragment.replaceFragment(context,new PreferencesFragment()));
        localisation.setOnClickListener(v -> NavigationActivityFragment.navigateTo(OfficeLocationActivity.class,context));
        deconnecter.setOnClickListener(v -> deconnecter());
        aproprenous.setOnClickListener(v -> NavigationActivityFragment.navigateTo(ApropreNous.class,context));
    }

    public  void deconnecter(){
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Confirmation")
                .setMessage("Êtes-vous sûr de se deconnecter  ?")
                .setPositiveButton("Yes", (dialog, which) -> {
                            requireActivity().finish() ;
                            SharedPreferences.Editor editor =getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(getActivity(), SignInActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                )
                .setNegativeButton("No", null)
                .show();
    }
}
