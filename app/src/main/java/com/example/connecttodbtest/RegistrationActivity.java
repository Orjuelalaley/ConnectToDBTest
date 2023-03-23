package com.example.connecttodbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.connecttodbtest.databinding.ActivityRegistrationBinding;
import com.example.connecttodbtest.utils.AlertUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.registration.setOnClickListener(v -> {
            binding.loading.setVisibility(View.VISIBLE);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = "http://192.168.1.105/ConnectToDBTest/register.php";
            @SuppressLint("SetTextI18n")
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                if (response.equals("success")) {
                    binding.loading.setVisibility(View.GONE);
                    binding.errorLogin.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    binding.errorLogin.setVisibility(View.VISIBLE);
                    binding.errorLogin.setText("Registration Failed Please try again");
                }
            }, error -> {
                binding.errorLogin.setVisibility(View.VISIBLE);
                binding.errorLogin.setText("Registration Failed Please try again");
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", Objects.requireNonNull(binding.name.getText()).toString());
                    params.put("email", Objects.requireNonNull(binding.email.getText()).toString());
                    params.put("password", Objects.requireNonNull(binding.password.getText()).toString());
                    return params;
                }
            };
            queue.add(stringRequest);
        });
    }
}