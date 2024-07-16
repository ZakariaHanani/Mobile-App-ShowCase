package com.valasapplication.app;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SignUpTask extends AsyncTask<String, Void, String> {
    private SignUpListener listener;

    public SignUpTask(SignUpListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = "";
        String urlSignup=Constansts.URL_SIGNUP;
        try {
            String email = params[0];
            String firstName = params[1];
            String lastName = params[2];
            String password = params[3];

            URL url = new URL(urlSignup);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Prepare data
            String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") +
                    "&" + URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") +
                    "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") +
                    "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            // Send data
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            os.close();

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                response += line;
            }
            in.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            response = "Error: " + e.getMessage();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.has("message")) {
                listener.onSignUpResult("Sign Up successful");
            } else if (jsonObject.has("error")) {
                String errorMessage = jsonObject.getString("error");
                listener.onSignUpResult(errorMessage);
            } else {
                listener.onSignUpResult("Unknown error occurred");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onSignUpResult("Error parsing response");
        }
    }

    public interface SignUpListener {
        void onSignUpResult(String result);
    }
}
