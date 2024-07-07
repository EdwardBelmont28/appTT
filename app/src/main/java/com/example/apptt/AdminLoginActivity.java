package com.example.apptt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    public void onAdminRegisterClick(View view) {
        Intent intent = new Intent(AdminLoginActivity.this, AdminRegisterActivity.class);
        startActivity(intent);
    }

    public void onBackToLoginClick(View view) {
        Intent intent = new Intent(AdminLoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
