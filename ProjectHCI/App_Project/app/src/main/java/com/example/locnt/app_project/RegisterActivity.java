package com.example.locnt.app_project;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    private View registerView;
    private View progressView;
    private EditText username, fullname, password, confirmPassword, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerView = findViewById(R.id.register_form);
        progressView = findViewById(R.id.register_progess);
        username = findViewById(R.id.username);
        fullname = findViewById(R.id.fullname);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
    }


    public void clickToSaveForRegister(View view) {
        if (username.getText().toString().length() == 0) {
            Toast.makeText(this, "Tên Đăng nhập không được trống", Toast.LENGTH_LONG).show();
        }
       else if (phone.getText().toString().length() == 0) {
            Toast.makeText(this, "Số điện thoại không được trống", Toast.LENGTH_LONG).show();
        } else if (password.getText().toString().length() == 0) {
            Toast.makeText(this, "Mật khẩu không được trống", Toast.LENGTH_LONG).show();
        } else if (confirmPassword.getText().toString().length() == 0) {
            Toast.makeText(this, "Xác nhập mật khẩu không được trống", Toast.LENGTH_LONG).show();
        } else if (password.getText().toString().length() > 0 && confirmPassword.getText().toString().length() > 0) {
            if (!password.getText().toString().equalsIgnoreCase(confirmPassword.getText().toString())) {
                Toast.makeText(this, "Xác Nhận mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
            }
            else {
                new CountDownTimer(3000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        showProgress(true);
                    }

                    @Override
                    public void onFinish() {
                        showProgress(false);
                    }
                }.start();
                Toast.makeText(this, "Đăng Kí Thành Công", Toast.LENGTH_LONG).show();
                onBackPressed();
                finish();
            }
        }


    }

    public void clickToBack(View view) {
        onBackPressed();
        finish();
    }

    private void showProgress(boolean param) {
        progressView.setVisibility(param ? View.VISIBLE : View.GONE);
        registerView.setVisibility(param ? View.GONE : View.VISIBLE);
    }
}
