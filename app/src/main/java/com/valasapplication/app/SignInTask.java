package com.valasapplication.app;

import android.os.AsyncTask;
import com.valasapplication.app.Activites.HomeActivity;
import com.valasapplication.app.Navigation.NavigationActivityFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class SignInTask extends AsyncTask<String, Void, String> {
    private SignInListener listener;

    public SignInTask(SignInListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = "";
        String urlSignin=Constansts.URL_SIGNIN;
        try {
            URL url = new URL(urlSignin);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Prepare data
            String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8") +
                    "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");

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
        } catch (IOException e){
            e.printStackTrace();
            response = "{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener != null) {
            listener.onSignInResult(result);
        }
    }

    public interface SignInListener {
        void onSignInResult(String result);
    }
}
