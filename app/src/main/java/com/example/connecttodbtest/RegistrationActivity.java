package com.example.connecttodbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.connecttodbtest.databinding.ActivityRegistrationBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    TextInputEditText name;
    TextInputEditText email;
    TextInputEditText password;

    TextView error_login;

    MaterialButton register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = binding.name;
        email = binding.email;
        password = binding.password;
        register = binding.registration;
        error_login = binding.errorLogin;
        register.setOnClickListener(v -> {
            String name = this.name.getText().toString();
            String email = this.email.getText().toString();
            String password = this.password.getText().toString();
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                return;
            }else{
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.105/ConnectToDBTest/";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        response -> {
                            error_login.setVisibility(TextView.VISIBLE);
                        }, error -> {
                    Log.d("Error is: ", error.toString());
                }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name);
                        params.put("email", email);
                        params.put("password", password);
                        return params;
                    }
                };
            }
        });
    }
}