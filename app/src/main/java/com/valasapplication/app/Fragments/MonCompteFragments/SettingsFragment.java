package com.valasapplication.app.Fragments.MonCompteFragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.valasapplication.app.R;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private RadioGroup radioGroupLanguage;
    private RadioGroup radioGroupTheme;
    private Button btnSaveSettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parametres, container, false);

        radioGroupLanguage = view.findViewById(R.id.radioGroupLanguage);
        radioGroupTheme = view.findViewById(R.id.radioGroupTheme);
        btnSaveSettings = view.findViewById(R.id.btnSaveSettings);

        btnSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });

        return view;
    }

    private void saveSettings() {
        // Get selected language
        RadioButton selectedLanguageRadioButton = getView().findViewById(radioGroupLanguage.getCheckedRadioButtonId());
        String selectedLanguage = selectedLanguageRadioButton.getText().toString();

        // Get selected theme
        RadioButton selectedThemeRadioButton = getView().findViewById(radioGroupTheme.getCheckedRadioButtonId());
        String selectedTheme = selectedThemeRadioButton.getText().toString();

        // Save the selectedLanguage and selectedTheme to preferences or data storage
        // For demonstration, we'll just show a toast message
        String message = "Language: " + selectedLanguage + "\nTheme: " + selectedTheme;
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
