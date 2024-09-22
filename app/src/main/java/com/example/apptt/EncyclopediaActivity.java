package com.example.apptt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EncyclopediaActivity extends AppCompatActivity {

    private boolean[] isExpanded = new boolean[9]; // Controla el estado de expansión de cada cuadro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);

        // Inicializar y configurar todos los cuadros (9 en total)
        configurarCuadro(R.id.image_concept1, R.id.tv_concept_description1, 0);
        configurarCuadro(R.id.image_concept2, R.id.tv_concept_description2, 1);
        configurarCuadro(R.id.image_concept3, R.id.tv_concept_description3, 2);
        configurarCuadro(R.id.image_concept4, R.id.tv_concept_description4, 3);
        configurarCuadro(R.id.image_concept5, R.id.tv_concept_description5, 4);
        configurarCuadro(R.id.image_concept6, R.id.tv_concept_description6, 5);
        configurarCuadro(R.id.image_concept7, R.id.tv_concept_description7, 6);
        configurarCuadro(R.id.image_concept8, R.id.tv_concept_description8, 7);
        configurarCuadro(R.id.image_concept9, R.id.tv_concept_description9, 8);

        // Inicializar el TextView para la descripción de la introducción
        final TextView tvDescripcion = findViewById(R.id.tv_descripcion);
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
                recreate(); // Recargar la actividad
            }
        });

        // Manejar el clic en el botón "Balance" (navegar a BalanceActivity)
        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncyclopediaActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });

        // Manejar el clic en el botón "Ahorros" (navegar a AhorroActivity)
        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncyclopediaActivity.this, AhorroActivity.class);
                startActivity(intent);
            }
        });

        // Manejar el clic en el botón "Endeudamiento" (navegar a EndeudamientoActivity)
        btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncyclopediaActivity.this, EndeudamientoActivity.class);
                startActivity(intent);
            }
        });
    }

    // Método para configurar cada cuadro con la funcionalidad de expansión
    private void configurarCuadro(int imageId, final int descriptionId, final int index) {
        ImageView imageConcept = findViewById(imageId);
        final TextView tvConceptDescription = findViewById(descriptionId);

        // Inicializar la descripción con un texto largo, pero solo se muestran 2 líneas al inicio
        tvConceptDescription.setText("Esta es una descripción completa del Concepto " + (index + 1) + ". Antes de expandir solo se mostrará una parte del texto...");

        // Manejar el clic en la imagen para expandir o colapsar la descripción
        imageConcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded[index]) {
                    // Si está expandido, colapsar la descripción a 2 líneas
                    tvConceptDescription.setMaxLines(2);
                    tvConceptDescription.setEllipsize(android.text.TextUtils.TruncateAt.END);
                    isExpanded[index] = false;
                } else {
                    // Si está colapsado, expandir para mostrar toda la descripción
                    tvConceptDescription.setMaxLines(Integer.MAX_VALUE);
                    tvConceptDescription.setEllipsize(null);
                    isExpanded[index] = true;
                }
            }
        });
    }
}
