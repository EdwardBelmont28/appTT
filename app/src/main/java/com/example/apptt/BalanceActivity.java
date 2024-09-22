package com.example.apptt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        // Inicializar el TextView para la descripción de la actividad Balance
        final TextView tvDescripcionBalance = findViewById(R.id.tv_descripcion_balance);
        tvDescripcionBalance.setText("Aquí podrás escribir el texto de la descripción de la actividad Balance...");

        // Inicializar los botones
        Button btnEnciclopedia = findViewById(R.id.btn_enciclopedia);
        Button btnBalance = findViewById(R.id.btn_balance);
        Button btnAhorros = findViewById(R.id.btn_ahorros);
        Button btnEndeudamiento = findViewById(R.id.btn_endeudamiento);

        // Manejar el clic en el botón "Enciclopedia" (navegar a EncyclopediaActivity)
        btnEnciclopedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BalanceActivity.this, EncyclopediaActivity.class);
                startActivity(intent);
            }
        });

        // Manejar el clic en el botón "Balance" (recargar la actividad actual)
        btnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate(); // Recargar la actividad Balance
            }
        });

        // Manejar el clic en el botón "Ahorros" (navegar a AhorroActivity)
        btnAhorros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BalanceActivity.this, AhorroActivity.class);
                startActivity(intent);
            }
        });

        // Manejar el clic en el botón "Endeudamiento" (navegar a EndeudamientoActivity)
        btnEndeudamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BalanceActivity.this, EndeudamientoActivity.class);
                startActivity(intent);
            }
        });
    }
}
