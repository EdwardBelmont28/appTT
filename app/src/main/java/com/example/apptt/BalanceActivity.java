package com.example.apptt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        // Inicializar el TextView para la descripción de la actividad Balance
        final TextView tvDescripcionBalance = findViewById(R.id.tv_descripcion_balance);
        tvDescripcionBalance.setText("Aquí podrás escribir el texto de la descripción de la actividad Balance...");

        // Inicializar los Spinners de Categoría y Tipo
        Spinner spinnerCategoria = findViewById(R.id.spinner_categoria);
        Spinner spinnerTipo = findViewById(R.id.spinner_tipo);

        // Configurar el adaptador para el Spinner de Categoría
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this, R.array.opciones_categoria, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        // Manejar la selección de Categoría
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccionCategoria = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Categoría seleccionada: " + seleccionCategoria, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

        // Configurar el adaptador para el Spinner de Tipo
        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(this, R.array.opciones_tipo, android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapterTipo);

        // Manejar la selección de Tipo
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccionTipo = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Tipo seleccionado: " + seleccionTipo, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

        // Inicializar el campo de texto para ingresar el monto
        EditText etMonto = findViewById(R.id.et_monto);

        // Manejar el botón para guardar el monto
        Button btnGuardarMonto = findViewById(R.id.btn_guardar_monto);
        btnGuardarMonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monto = etMonto.getText().toString();
                if (!monto.isEmpty()) {
                    Toast.makeText(BalanceActivity.this, "Monto guardado: $" + monto, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BalanceActivity.this, "Por favor, ingrese un monto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inicializar los botones de navegación de la parte inferior
        Button btnEnciclopedia = findViewById(R.id.btn_enciclopedia);
        Button btnBalance = findViewById(R.id.btn_balance);
        Button btnAhorros = findViewById(R.id.btn_ahorros);
        Button btnEndeudamiento = findViewById(R.id.btn_endeudamiento);

        // Navegar a la actividad "Enciclopedia"
        btnEnciclopedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BalanceActivity.this, EncyclopediaActivity.class);
                startActivity(intent);
            }
        });

        // Recargar la actividad "Balance"
        btnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate(); // Recargar la actividad Balance
            }
        });

        // Navegar a la actividad "Ahorros"
        btnAhorros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BalanceActivity.this, AhorroActivity.class);
                startActivity(intent);
            }
        });

        // Navegar a la actividad "Endeudamiento"
        btnEndeudamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BalanceActivity.this, EndeudamientoActivity.class);
                startActivity(intent);
            }
        });
    }
}
