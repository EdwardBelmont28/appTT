package com.example.apptt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EncyclopediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);

        // Inicializar el TextView para la descripción
        TextView tvDescripcion = findViewById(R.id.tv_descripcion);
        tvDescripcion.setText("Aquí podrás escribir el texto de la descripción...");

        // Inicializar los botones
        Button btnReload = findViewById(R.id.btn_reload);
        Button btnOption2 = findViewById(R.id.btn_option2);
        Button btnOption3 = findViewById(R.id.btn_option3);
        Button btnOption4 = findViewById(R.id.btn_option4);

        // Manejar el clic en el botón Recargar
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones a realizar cuando se presione el botón "Recargar"
                Toast.makeText(EncyclopediaActivity.this, "Recargando...", Toast.LENGTH_SHORT).show();
                // Aquí puedes agregar lógica adicional para recargar los datos
            }
        });

        // Manejar el clic en los otros botones
        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones para el botón Opción 2
                Toast.makeText(EncyclopediaActivity.this, "Opción 2 seleccionada", Toast.LENGTH_SHORT).show();
            }
        });

        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones para el botón Opción 3
                Toast.makeText(EncyclopediaActivity.this, "Opción 3 seleccionada", Toast.LENGTH_SHORT).show();
            }
        });

        btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones para el botón Opción 4
                Toast.makeText(EncyclopediaActivity.this, "Opción 4 seleccionada", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
