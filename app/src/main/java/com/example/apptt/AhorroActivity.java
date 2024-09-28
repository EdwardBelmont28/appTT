package com.example.apptt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AhorroActivity extends AppCompatActivity {

    private EditText etIngresosMensuales, etAhorroMensual;
    private TextView tvHistorial;
    private StringBuilder historial = new StringBuilder();
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "HistorialPrefs";
    private static final String HISTORIAL_KEY = "HistorialAhorro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorro);

        final TextView tvDescripcion2 = findViewById(R.id.tv_descripcion2);
        tvDescripcion2.setText("Aquí podrás escribir el texto de la descripción...");

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Inicializar los campos de ingreso y ahorro
        etIngresosMensuales = findViewById(R.id.et_ingresos_mensuales);
        etAhorroMensual = findViewById(R.id.et_ahorro_mensual);

        // Inicializar el TextView para el historial
        tvHistorial = findViewById(R.id.tv_historial_ahorro);

        // Cargar historial guardado si existe
        String historialGuardado = sharedPreferences.getString(HISTORIAL_KEY, "");
        historial.append(historialGuardado);
        tvHistorial.setText(historial.toString());

        // Inicializar el botón para calcular
        Button btnCalcular = findViewById(R.id.btn_calcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularAhorro();
            }
        });

        // Inicializar el botón para borrar el historial
        Button btnBorrarHistorial = findViewById(R.id.btn_borrar_historial);
        btnBorrarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarHistorial();
            }
        });

        // Manejar los botones de navegación en la parte inferior
        Button btnEnciclopedia = findViewById(R.id.btn_enciclopedia);
        Button btnBalance = findViewById(R.id.btn_balance);
        Button btnAhorros = findViewById(R.id.btn_ahorros);
        Button btnEndeudamiento = findViewById(R.id.btn_endeudamiento);

        // Navegar a la actividad "Enciclopedia"
        btnEnciclopedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AhorroActivity.this, EncyclopediaActivity.class);
                startActivity(intent);
            }
        });

        // Navegar a la actividad "Balance"
        btnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AhorroActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });

        // Recargar la actividad "Ahorros"
        btnAhorros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate(); // Recargar la actividad Ahorros
            }
        });

        // Navegar a la actividad "Endeudamiento"
        btnEndeudamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AhorroActivity.this, EndeudamientoActivity.class);
                startActivity(intent);
            }
        });
    }

    // Método para calcular el ahorro
    private void calcularAhorro() {
        String ingresosStr = etIngresosMensuales.getText().toString();
        String ahorroStr = etAhorroMensual.getText().toString();

        if (!ingresosStr.isEmpty() && !ahorroStr.isEmpty()) {
            double ingresos = Double.parseDouble(ingresosStr);
            double ahorro = Double.parseDouble(ahorroStr);

            double porcentajeAhorro = (ahorro / ingresos) * 100;

            // Agregar información al historial
            historial.append("Ingresos Mensuales: $").append(ingresos).append("\n")
                    .append("Ahorro Mensual: $").append(ahorro).append("\n")
                    .append("Tasa de Ahorro: ").append(String.format("%.2f", porcentajeAhorro)).append("%\n\n");

            // Guardar el historial en SharedPreferences
            guardarHistorial();

            // Actualizar el TextView del historial
            tvHistorial.setText(historial.toString());

            Toast.makeText(this, "Tasa de ahorro calculada: " + String.format("%.2f", porcentajeAhorro) + "%", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Por favor, ingrese ambos valores", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para guardar el historial en SharedPreferences
    private void guardarHistorial() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(HISTORIAL_KEY, historial.toString());
        editor.apply();
    }

    // Método para borrar el historial
    private void borrarHistorial() {
        historial.setLength(0);  // Limpiar el StringBuilder
        tvHistorial.setText("Historial:");

        // Limpiar SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(HISTORIAL_KEY);
        editor.apply();

        Toast.makeText(this, "Historial borrado", Toast.LENGTH_SHORT).show();
    }
}
