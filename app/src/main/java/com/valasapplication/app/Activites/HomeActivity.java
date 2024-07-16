package com.valasapplication.app.Activites;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.valasapplication.app.Fragments.HomeFragment;
import com.valasapplication.app.Navigation.NavbarBottom;
import com.valasapplication.app.Navigation.NavigationActivityFragment;
import com.valasapplication.app.R;
import com.valasapplication.app.modules.signin.ui.SignInActivity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class HomeActivity extends AppCompatActivity {
    private static MeowBottomNavigation bottomNavigation ;

    public static MeowBottomNavigation getBottomNavigation() {
        return bottomNavigation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.show(1, true);
        NavigationActivityFragment.replaceFragment(this, new HomeFragment(bottomNavigation));
        new NavbarBottom(bottomNavigation, this);
    }


    @Override
    public void onBackPressed() {
        if(true){
        NavigationActivityFragment.replaceFragment(this, new HomeFragment(bottomNavigation));
        }
        else{
        super.onBackPressed();
        }
    }


    @Nullable
    public static Intent getIntent(@NotNull SignInActivity signInActivity, @Nullable Void nothing) {
        return null;
    }
}


