package com.valasapplication.app.Fragments;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.valasapplication.app.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class OutilFragment extends Fragment {

    private EditText etPasswordLength, etAmount, etNumber;
    private EditText etPlainText, etEncryptionKey, etEncryptedText, etDecryptionKey;
    private TextView tvGeneratedPassword,tvEncryptedText, tvDecryptedText,titre;
    private Spinner spinnerEncryptionAlgorithm, spinnerDecryptionAlgorithm;
    private Button btnGeneratePassword, btnEncrypt, btnDecrypt;

    private EditText hsetPlainText;
    private TextView tvHashedText;
    private Spinner spinnerHashAlgorithm;
    private Button btnHash;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outils_pratiques, container, false);

        titre=view.findViewById(R.id.titre);
        etPasswordLength = view.findViewById(R.id.et_password_length);
        tvGeneratedPassword = view.findViewById(R.id.tv_generated_password);
        btnGeneratePassword = view.findViewById(R.id.btn_generate_password);

        etPlainText = view.findViewById(R.id.et_plain_text);
        etEncryptionKey = view.findViewById(R.id.et_encryption_key);
        spinnerEncryptionAlgorithm = view.findViewById(R.id.spinner_encryption_algorithm);
        btnEncrypt = view.findViewById(R.id.btn_encrypt);
        tvEncryptedText = view.findViewById(R.id.tv_encrypted_text);

        etEncryptedText = view.findViewById(R.id.et_encrypted_text);
        etDecryptionKey = view.findViewById(R.id.et_decryption_key);
        spinnerDecryptionAlgorithm = view.findViewById(R.id.spinner_decryption_algorithm);
        btnDecrypt = view.findViewById(R.id.btn_decrypt);
        tvDecryptedText = view.findViewById(R.id.tv_decrypted_text);
        etPlainText = view.findViewById(R.id.hset_plain_text);
        tvHashedText = view.findViewById(R.id.tv_hashed_text);
        spinnerHashAlgorithm = view.findViewById(R.id.spinner_hash_algorithm);
        btnHash = view.findViewById(R.id.btn_hash);
        titre.setText("Outils Pratique");
        btnHash.setOnClickListener(v -> hashText());

        btnGeneratePassword.setOnClickListener(v -> generatePassword());
        btnEncrypt.setOnClickListener(v -> encryptText());
        btnDecrypt.setOnClickListener(v -> decryptText());

        setupSpinners();

        return view;
    }

    private void setupSpinners() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.hash_algorithms_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHashAlgorithm.setAdapter(adapter);

        ArrayAdapter<CharSequence> encryptionAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.encryption_algorithms_array, android.R.layout.simple_spinner_item);
        encryptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEncryptionAlgorithm.setAdapter(encryptionAdapter);
        spinnerDecryptionAlgorithm.setAdapter(encryptionAdapter);
    }

    private void generatePassword() {
        String lengthStr = etPasswordLength.getText().toString();
        if (TextUtils.isEmpty(lengthStr)) {
            Toast.makeText(getContext(), "Veuillez entrer une longueur", Toast.LENGTH_SHORT).show();
            return;
        }

        int length = Integer.parseInt(lengthStr);
        String generatedPassword = generateRandomPassword(length);
        tvGeneratedPassword.setText("Mot de passe généré: " + generatedPassword);
    }

    private String generateRandomPassword(int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return password.toString();
    }



    private void hashText() {
        String plainText = etPlainText.getText().toString();
        String algorithm = spinnerHashAlgorithm.getSelectedItem().toString();

        if (TextUtils.isEmpty(plainText)) {
            Toast.makeText(getContext(), "Veuillez entrer le texte", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = digest.digest(plainText.getBytes());
            String hashedText = bytesToHex(hashBytes);
            tvHashedText.setText("Texte haché: " + hashedText);
        } catch (NoSuchAlgorithmException e) {
            Toast.makeText(getContext(), "Erreur de hachage", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    private void encryptText() {
        String plainText = etPlainText.getText().toString();
        String encryptionKey = etEncryptionKey.getText().toString();
        String algorithm = spinnerEncryptionAlgorithm.getSelectedItem().toString();

        if (TextUtils.isEmpty(plainText) || TextUtils.isEmpty(encryptionKey)) {
            Toast.makeText(getContext(), "Veuillez entrer le texte et la clé", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String encryptedText = encrypt(plainText, encryptionKey, algorithm);
            tvEncryptedText.setText("Texte chiffré: " + encryptedText);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erreur de cryptage", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void decryptText() {
        String encryptedText = etEncryptedText.getText().toString();
        String decryptionKey = etDecryptionKey.getText().toString();
        String algorithm = spinnerDecryptionAlgorithm.getSelectedItem().toString();

        if (TextUtils.isEmpty(encryptedText) || TextUtils.isEmpty(decryptionKey)) {
            Toast.makeText(getContext(), "Veuillez entrer le texte chiffré et la clé", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String decryptedText = decrypt(encryptedText, decryptionKey, algorithm);
            tvDecryptedText.setText("Texte déchiffré: " + decryptedText);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erreur de décryptage", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private String encrypt(String plainText, String encryptionKey, String algorithm) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    private String decrypt(String encryptedText, String decryptionKey, String algorithm) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(decryptionKey.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.decode(encryptedText, Base64.DEFAULT));
        return new String(decryptedBytes);
    }
}
