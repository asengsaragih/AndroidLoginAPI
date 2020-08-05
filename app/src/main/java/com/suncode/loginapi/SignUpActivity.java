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
import com.suncode.loginapi.model.Signup;
import com.suncode.loginapi.service.BaseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText mFullnameEditText, mEmailEditText, mPasswordEditText;
    private Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFullnameEditText = findViewById(R.id.editTextSignupFullname);
        mEmailEditText = findViewById(R.id.editTextSignupEmail);
        mPasswordEditText = findViewById(R.id.editTextSignupPassword);

        mSignupButton = findViewById(R.id.buttonSignup);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        if (TextUtils.isEmpty(mEmailEditText.getText().toString()) || TextUtils.isEmpty(mPasswordEditText.getText().toString()) || TextUtils.isEmpty(mPasswordEditText.getText().toString())) {
            shortToast("ISI FORM");
            return;
        }

        BaseService service = BaseGenerator.build().create(BaseService.class);
        Call<Signup> call = service.signupUser(mFullnameEditText.getText().toString(), mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());

        call.enqueue(new Callback<Signup>() {
            @Override
            public void onResponse(Call<Signup> call, Response<Signup> response) {
                Signup data = response.body();
                Log.d("CHECKTAG", data.getError());
                Log.d("CHECKTAG", data.getMessage());

                try {
                    Signup.User user = data.getUser();
                    Log.d("CHECKTAG", user.getEmail());

                    shortToast("Berhasil daftar");
                    finish();
                } catch (NullPointerException e) {
                    Log.d("CHECKTAG", "signup failed");
                }
            }

            @Override
            public void onFailure(Call<Signup> call, Throwable t) {

            }
        });
    }

    private void shortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}