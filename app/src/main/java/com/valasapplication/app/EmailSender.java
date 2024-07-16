package com.valasapplication.app;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EmailSender {

    public static void sendEmail(Context context, String name, String email, String message) {
        String url = Constansts.URL_SEND_EMAIL;

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                }, error -> {
                    Toast.makeText(context, "Failed to send email: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("message", message);
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
