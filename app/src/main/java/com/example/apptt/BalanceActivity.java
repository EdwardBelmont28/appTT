package com.example.apptt;

import android.content.Intent;
import android.content.SharedPreferences;
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

    // Variable para almacenar el historial y los totales de ingresos y gastos
    private StringBuilder historial = new StringBuilder();
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "HistorialPrefs";
    private static final String HISTORIAL_KEY = "Historial";
    private double totalIngresos = 0.0;
    private double totalGastos = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Inicializar el TextView del Historial y el Balance Mensual
        final TextView tvHistorial = findViewById(R.id.tv_historial);
        final TextView tvBalanceMensual = findViewById(R.id.tv_balance_mensual);

        // Cargar historial guardado si existe
        String historialGuardado = sharedPreferences.getString(HISTORIAL_KEY, "");
        historial.append(historialGuardado);
        tvHistorial.setText(historial.toString());

        // ---------- INICIA SECCIÓN DE INGRESOS ----------

        // Inicializar Spinners para Categoría y Tipo de Ingresos
        Spinner spinnerCategoria = findViewById(R.id.spinner_categoria);
        Spinner spinnerTipo = findViewById(R.id.spinner_tipo);

        // Configurar el adaptador para el Spinner de Categoría
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this,
                R.array.opciones_categoria_ingresos, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        // Configurar el adaptador para el Spinner de Tipo de Ingresos
        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(this,
                R.array.opciones_tipo_ingresos, android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapterTipo);

        // Inicializar el campo de texto para ingresar el monto de Ingresos
        EditText etMonto = findViewById(R.id.et_monto);

        // Manejar el botón para guardar el monto de Ingresos
        Button btnGuardarMonto = findViewById(R.id.btn_guardar_monto);
        btnGuardarMonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = spinnerCategoria.getSelectedItem().toString();
                String tipo = spinnerTipo.getSelectedItem().toString();
                String montoStr = etMonto.getText().toString();

                if (!montoStr.isEmpty()) {
                    double monto = Double.parseDouble(montoStr);
                    totalIngresos += monto;

                    // Agregar información al historial
                    historial.append("Ingreso: \n")
                            .append("Categoría: ").append(categoria)
                            .append(", Tipo: ").append(tipo)
                            .append(", Monto: $").append(monto)
                            .append("\n");

                    // Actualizar el balance mensual
                    actualizarBalance(tvBalanceMensual);

                    // Guardar el historial en SharedPreferences
                    guardarHistorial();

                    // Actualizar el TextView del historial
                    tvHistorial.setText(historial.toString());
                    Toast.makeText(BalanceActivity.this, "Monto de Ingresos guardado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BalanceActivity.this, "Por favor, ingrese un monto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ---------- INICIA SECCIÓN DE GASTOS ----------

        // Inicializar Spinners para Categoría y Tipo de Gastos
        Spinner spinnerCategoriaGastos = findViewById(R.id.spinner_categoria_gastos);
        Spinner spinnerTipoGastos = findViewById(R.id.spinner_tipo_gastos);

        // Configurar el adaptador para el Spinner de Categoría de Gastos
        ArrayAdapter<CharSequence> adapterCategoriaGastos = ArrayAdapter.createFromResource(this,
                R.array.opciones_categoria_gastos, android.R.layout.simple_spinner_item);
        adapterCategoriaGastos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoriaGastos.setAdapter(adapterCategoriaGastos);

        // Configurar el adaptador para el Spinner de Tipo de Gastos
        ArrayAdapter<CharSequence> adapterTipoGastos = ArrayAdapter.createFromResource(this,
                R.array.opciones_tipo_gastos, android.R.layout.simple_spinner_item);
        adapterTipoGastos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoGastos.setAdapter(adapterTipoGastos);

        // Inicializar el campo de texto para ingresar el monto de Gastos
        EditText etMontoGastos = findViewById(R.id.et_monto_gastos);

        // Manejar el botón para guardar el monto de Gastos
        Button btnGuardarMontoGastos = findViewById(R.id.btn_guardar_monto_gastos);
        btnGuardarMontoGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoriaGastos = spinnerCategoriaGastos.getSelectedItem().toString();
                String tipoGastos = spinnerTipoGastos.getSelectedItem().toString();
                String montoStrGastos = etMontoGastos.getText().toString();

                if (!montoStrGastos.isEmpty()) {
                    double montoGastos = Double.parseDouble(montoStrGastos);
                    totalGastos += montoGastos;

                    // Agregar información al historial
                    historial.append("Gasto: \n")
                            .append("Categoría: ").append(categoriaGastos)
                            .append(", Tipo: ").append(tipoGastos)
                            .append(", Monto: $").append(montoGastos)
                            .append("\n");

                    // Actualizar el balance mensual
                    actualizarBalance(tvBalanceMensual);

                    // Guardar el historial en SharedPreferences
                    guardarHistorial();

                    // Actualizar el TextView del historial
                    tvHistorial.setText(historial.toString());
                    Toast.makeText(BalanceActivity.this, "Monto de Gastos guardado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BalanceActivity.this, "Por favor, ingrese un monto de Gastos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ---------- INICIA SECCIÓN DEL BOTÓN BORRAR HISTORIAL ----------

        // Manejar el botón para borrar el historial
        Button btnBorrarHistorial = findViewById(R.id.btn_borrar_historial);
        btnBorrarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpiar historial y resetear los totales
                historial.setLength(0);
                totalIngresos = 0.0;
                totalGastos = 0.0;

                // Actualizar el TextView para mostrar el historial vacío y el balance en 0
                tvHistorial.setText("Historial:");
                actualizarBalance(tvBalanceMensual);

                // Limpiar el historial de SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(HISTORIAL_KEY);
                editor.apply();

                Toast.makeText(BalanceActivity.this, "Historial borrado", Toast.LENGTH_SHORT).show();
            }
        });

        // ---------- INICIA SECCIÓN DE NAVEGACIÓN ----------

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

    // Método para actualizar el balance mensual
    private void actualizarBalance(TextView tvBalanceMensual) {
        double balanceMensual = totalIngresos - totalGastos;
        tvBalanceMensual.setText(String.format("$ %.2f", balanceMensual));
    }

    // Método para guardar el historial en SharedPreferences
    private void guardarHistorial() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(HISTORIAL_KEY, historial.toString());
        editor.apply();
    }
}
