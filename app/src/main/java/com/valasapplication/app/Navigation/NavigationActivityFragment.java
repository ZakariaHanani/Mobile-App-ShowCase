package com.valasapplication.app.Navigation;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.valasapplication.app.R;

public class NavigationActivityFragment {

    public static void replaceFragment(Context context , Fragment fragment){
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager() ;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction() ;
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    public static void replaceFragmentDetail(Context context , Fragment fragment){
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager() ;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction() ;
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.constraint_home,fragment);
        fragmentTransaction.commit();
    }
    public static void navigateTo(Class<?> destinationActivity,Context context) {
        Intent intent = new Intent(context, destinationActivity);
        context.startActivity(intent);
    }
}
