package com.valasapplication.app.Helpers;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import com.valasapplication.app.Activites.ApropreNous;
import com.valasapplication.app.Activites.SupportActivity;
import com.valasapplication.app.Fragments.MonCompteFragment;
import com.valasapplication.app.Navigation.NavigationActivityFragment;
import com.valasapplication.app.R;

public class PopupMenuHelper {
    public static void showPopupMenu(View view, Context context) {
        // Step 3: Create and show the Popup Menu
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.top_setting_menu, popupMenu.getMenu());

        // Step 4: Handle menu item selections
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.setting) {
                    // Handle menu item 1 click
                    NavigationActivityFragment.replaceFragment(context,new MonCompteFragment());
                    return true;
                } else if (itemId == R.id.support) {
                    // Handle menu item 2 click
                    NavigationActivityFragment.navigateTo(SupportActivity.class ,context);
                    return true;
                } else if (itemId == R.id.info) {
                    // Handle menu item 3 click
                    NavigationActivityFragment.navigateTo(ApropreNous.class,context);
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}

