package com.example.apptt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AdminRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
    }

    public void onAdminRegisterClick(View view) {
        // Implementar la lógica de registro del administrador aquí
        Intent intent = new Intent(AdminRegisterActivity.this, AdminLoginActivity.class);
        startActivity(intent);
    }
}
