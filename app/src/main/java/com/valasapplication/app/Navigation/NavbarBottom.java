package com.valasapplication.app.Navigation;
import android.content.Context;
import androidx.core.app.ActivityCompat;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.valasapplication.app.Fragments.*;
import com.valasapplication.app.R;

public class NavbarBottom extends ActivityCompat {


    public NavbarBottom(MeowBottomNavigation bottomNavigation ,Context context) {

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.catalogue_copy));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.categories));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.blog_copy));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.outilpratique));
        bottomNavigation.add(new MeowBottomNavigation.Model(6, R.drawable.profile_compte));


        bottomNavigation.setOnClickMenuListener(model -> {
            switch (model.getId()) {
                case 1:
                    NavigationActivityFragment.replaceFragment(context,new HomeFragment(bottomNavigation));
                    // navigateTo(AccueilActivity.class);
                    break;
                case 2:
                    NavigationActivityFragment.replaceFragment(context,new CatalogueFragment());
                    // navigateTo(CatalogueActivity.class);
                    break;
                case 3:
                    NavigationActivityFragment.replaceFragment(context,new CategoriesFragment());
                    // navigateTo(CategoriesActivity.class);
                    break;
                case 4:
                    NavigationActivityFragment.replaceFragment(context,new BlogFragment());
                    // navigateTo(BlogActivity.class);
                    break;
                case 5:
                    //  navigateTo(OutilActivity.class);
                    NavigationActivityFragment.replaceFragment(context,new OutilFragment());
                    break;
                case 6:
                    NavigationActivityFragment.replaceFragment(context,new MonCompteFragment());
                    break;
            }
            return null;
        });
    }
}
