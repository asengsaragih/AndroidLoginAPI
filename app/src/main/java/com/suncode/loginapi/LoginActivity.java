package com.suncode.loginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.suncode.loginapi.helper.BaseGenerator;
import com.suncode.loginapi.model.Login;
import com.suncode.loginapi.service.BaseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailEditText = findViewById(R.id.editTextEmail);
        mPasswordEditText = findViewById(R.id.editTextPassword);
        mLoginButton = findViewById(R.id.buttonLogin);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        if (TextUtils.isEmpty(mEmailEditText.getText().toString()) || TextUtils.isEmpty(mPasswordEditText.getText().toString())) {
            shortToast("ISI FORM");
            return;
        }

        BaseService baseService = BaseGenerator.build().create(BaseService.class);
        Call<Login> call = baseService.loginUser(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
//                Login data = response.body();
//                Log.d("CHECKTAG", data.getError());
//                Log.d("CHECKTAG", data.getMessage());
//                Login.User user = data.getUser();
//                Log.d("CHECKTAG", user.getEmail());

                Log.d("CHECKTAG", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }

    private void shortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}